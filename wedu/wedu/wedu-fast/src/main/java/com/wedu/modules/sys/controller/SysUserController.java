

package com.wedu.modules.sys.controller;

import com.wedu.common.annotation.SysLog;
import com.wedu.common.utils.PageUtils;
import com.wedu.common.utils.R;
import com.wedu.common.validator.AbstractAssert;
import com.wedu.common.validator.ValidatorUtils;
import com.wedu.common.validator.group.AddGroup;
import com.wedu.common.validator.group.UpdateGroup;
import com.wedu.modules.sys.entity.SysUserEntity;
import com.wedu.modules.sys.form.PasswordForm;
import com.wedu.modules.sys.service.SysUserRoleService;
import com.wedu.modules.sys.service.SysUserService;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author wedu
 */
@RestController
//responseBody+Controller
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserRoleService sysUserRoleService;



	/**
	 * 所有用户列表
	 */
	@GetMapping("/list")
	//Shrio框架的权限控制注解
	@RequiresPermissions("sys:user:list")
	//@RequestParam表示：从请求参数中获取数据，并将它们放进map中
	public R list(@RequestParam Map<String, Object> params){
		//queryPage用于从数据库中查询分页信息并返回一个封装了 分页信息的PageUtils对象
		PageUtils page = sysUserService.queryPage(params);
		System.out.println(page);
		//.put("page", page);将分页数据放入响应体中
		return R.ok().put("page", page);
	}

	/**
	 * 获取登录的用户信息
	 */
	@GetMapping("/info")
	public R info(){
		return R.ok().put("user", getUser());
	}

	/**
	 * 修改登录用户密码
	 */
	//用于记录系统日志的自定义注解，标记这个方法的行为是修改密码
	@SysLog("修改密码")
	@PostMapping("/password")
	//@RequestBody：从请求体中获取json格式数据并反序列化为PasswordForm对象
	public R password(@RequestBody PasswordForm form){
		//确保密码不为空
		AbstractAssert.isBlank(form.getNewPassword(), "新密码不为能空");
		//首先从form中获取原密码和新密码，然后调用getUser().getSalt()获取用户的盐值，然后使用Sha256Hash类对密码进行加密，并将加密后的密码转化为16进制字符串
		//sha256加密
		String password = new Sha256Hash(form.getPassword(), getUser().getSalt()).toHex();
		//sha256加密
		String newPassword = new Sha256Hash(form.getNewPassword(), getUser().getSalt()).toHex();

		//更新密码
		//调用sysUserService.updatePassword方法更新密码，返回一个布尔类型，表示是否更新成功，接收三个参数：用户ID，原密码，新密码
		boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
		if(!flag){
			//如果flag为false
			return R.error("原密码不正确");
		}

		return R.ok();
	}

	/**
	 * 获取当前登录人员的用户id
	 * @return R
	 */
	@GetMapping("getLoginUserInfo")
	public R getLoginUserInfo() {
		return R.ok().put("data",sysUserService.getLoginUserInfo());
	}

	/**
	 * 用户信息
	 */
	@GetMapping("/info/{userId}")
	@RequiresPermissions("sys:user:info")
	//从 url中提取名为userId部分，并将其转化为Long类型的值
	public R info(@PathVariable("userId") Long userId){
		//根据id获取SysUserEntity对象，这个对象包含用户的基本信息
		SysUserEntity user = sysUserService.getById(userId);

		//获取用户所属的角色列表
		List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
		//将获取到的角色列表设置到实体对象user中。这样这个实体类不仅包含了基本信息，也包含了角色信息
		user.setRoleIdList(roleIdList);

		return R.ok().put("user", user);
	}

	/**
	 * 保存用户
	 */
	//系统日志的自定义注解，标记这个方法的行为是保存用户
	@SysLog("保存用户")
	@PostMapping("/save")
	@RequiresPermissions("sys:user:save")
	//从请求体中读取json数据并反序列化为SysUserEntity对象
	public R save(@RequestBody SysUserEntity user){
		//调用ValidatorUtils工具类的validateEntity方法，传入了AddGroup.class作为验证组（一组验证规则），如果验证失败可能会报出异常
		ValidatorUtils.validateEntity(user, AddGroup.class);
		//设置user的setCreateUserId属性，将其设置为当前登录用户的id
		user.setCreateUserId(getUserId());
		//将user保存到数据库中
		sysUserService.saveUser(user);

		return R.ok();
	}

	/**
	 * 修改用户
	 */
	@SysLog("修改用户")
	@PostMapping("/update")
	@RequiresPermissions("sys:user:update")
	public R update(@RequestBody SysUserEntity user){
		ValidatorUtils.validateEntity(user, UpdateGroup.class);
		user.setCreateTime(new Date());
		user.setCreateUserId(getUserId());
		sysUserService.update(user);

		return R.ok();
	}

	/**
	 * 删除用户
	 */
	@SysLog("删除用户")
	@PostMapping("/delete")
	@RequiresPermissions("sys:user:delete")
	public R delete(@RequestBody Long[] userIds){
		//检查这个数组中是否包含管理员id 1L
		if(ArrayUtils.contains(userIds, 1L)){
			return R.error("系统管理员不能删除");
		}

		if(ArrayUtils.contains(userIds, getUserId())){
			return R.error("当前用户不能删除");
		}

		sysUserService.deleteBatch(userIds);

		return R.ok();
	}
}
