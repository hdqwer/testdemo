

package com.wedu.modules.sys.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wedu.common.exception.RRException;
import com.wedu.common.utils.Constant;
import com.wedu.common.utils.PageUtils;
import com.wedu.common.utils.Query;
import com.wedu.modules.sys.dao.SysUserDao;
import com.wedu.modules.sys.entity.SysUserEntity;
import com.wedu.modules.sys.entity.vo.UserInfoVO;
import com.wedu.modules.sys.service.SysRoleService;
import com.wedu.modules.sys.service.SysUserRoleService;
import com.wedu.modules.sys.service.SysUserService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 系统用户
 *
 * @author wedu
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysRoleService sysRoleService;

	//这个方法接收了一个Map类型的分页参数params
	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		String username = (String)params.get("username");
		Long createUserId = (Long)params.get("createUserId");
		//获取姓名和id
		//mybatis-plus
		//这里page方法用来执行分页查询，接收参数（分页信息对象，条件查询对象）
		//1.getone。在创建完实例时候写：queryWrapper.last（“limit 1”），然后再写getOne
		//2.and拼接，需要加表达式
		//3.lambda。。。使用表达式表示实体类
		IPage<SysUserEntity> page = this.page(
			new Query<SysUserEntity>().getPage(params),
			//使用query对象获取分页信息
			new QueryWrapper<SysUserEntity>()
				.like(StringUtils.isNotBlank(username),"username", username)
				.eq(createUserId != null,"create_user_id", createUserId)
				//如果username不为空，添加一个执行条件，查询包含username相关的信息
				//如果createUserId不为空，添加一个等值查询，查询与id相等的记录
		);

		return new PageUtils(page);
		//将查询到的分页结果封装到PageUtils中
	}

	//接收id，查询与这个id的相关的所有权限
	@Override
	public List<String> queryAllPerms(Long userId) {
		return baseMapper.queryAllPerms(userId);
	}

	//返回与id相关的所有菜单id
	@Override
	public List<Long> queryAllMenuId(Long userId) {
		return baseMapper.queryAllMenuId(userId);
	}

	//通过id返回SysUserEntity对象
	@Override
	public SysUserEntity queryByUserName(String username) {
		return baseMapper.queryByUserName(username);
	}

	@Override
	@Transactional
	//开启事务，要么全部执行，要么全部不执行
	//保存用户信息
	public void saveUser(SysUserEntity user) {
		user.setCreateTime(new Date());
		//sha256加密
		String salt = RandomStringUtils.randomAlphanumeric(20);
		//生成一个20位字符串作为 盐值
		user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
		//使用Sha256算法和盐值对user的密码加密，并将加密的密码设置为user的密码
		user.setSalt(salt);
		//将盐值设置到user对象中
		this.save(user);

		//检查角色是否越权
//		checkRole(user);

		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}

	//更新用户信息
	@Override
	@Transactional
	public void update(SysUserEntity user) {
		//如果当前user的密码位空，还是把密码设为空
		if(StringUtils.isBlank(user.getPassword())){
			user.setPassword(null);
		}else{
			//使用Sha256Hash以及盐值对密码加密并保存到user中
			user.setPassword(new Sha256Hash(user.getPassword(), user.getSalt()).toHex());
		}
		this.updateById(user);
		//根据id将user更新到数据库

		//检查角色是否越权
//		checkRole(user);

		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}

	@Override
	public void deleteBatch(Long[] userId) {
		this.removeByIds(Arrays.asList(userId));
	}

	//更新密码
	@Override
	public boolean updatePassword(Long userId, String password, String newPassword) {
		SysUserEntity userEntity = new SysUserEntity();
		//创建一个实体类
		userEntity.setPassword(newPassword);
		//保存新密码
		//这里通过等值查询，查询与id，密码相匹配的信息，然后更新数据库
		return this.update(userEntity,
				new QueryWrapper<SysUserEntity>().eq("user_id", userId).eq("password", password));
	}

	//获取当前登录用户详细信息
	@Override
	public UserInfoVO getLoginUserInfo() {
		//使用SecurityUtils获取用户信息，getSubject方法返回当前登录用户的安全主题对象，getPrincipal方法返回与该主题关联的主体（通常是用户信息）
		SysUserEntity loginUser = (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
		Assert.notNull(loginUser,"获取当前登录人员信息失败！");
		//根据id获取基本信息并封装到UserInfoVO
		UserInfoVO userInfoVO = this.baseMapper.getUserInfoById(loginUser.getUserId());
		// 获取用户对应的角色信息
		userInfoVO.setRoleNames(sysUserRoleService.queryRoleNameList(loginUser.getUserId().toString()));
		return userInfoVO;
	}

	/**
	 * 检查角色是否越权
	 */
	private void checkRole(SysUserEntity user){
		if(user.getRoleIdList() == null || user.getRoleIdList().size() == 0){
			return;
		}
		//如果不是超级管理员，则需要判断用户的角色是否自己创建
		if(user.getCreateUserId() == Constant.SUPER_ADMIN){
			return ;
		}

		//查询用户创建的角色列表
		List<Long> roleIdList = sysRoleService.queryRoleIdList(user.getCreateUserId());

		//判断是否越权
		//检查用户所拥有的角色是否包含当前用户的全部角色列表
		if(!roleIdList.containsAll(user.getRoleIdList())){
			throw new RRException("新增用户所选角色，不是本人创建");
		}
	}
}
