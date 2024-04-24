

package com.wedu.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wedu.common.utils.MapUtils;
import com.wedu.modules.sys.dao.SysUserRoleDao;
import com.wedu.modules.sys.entity.SysUserRoleEntity;
import com.wedu.modules.sys.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;



/**
 * 用户与角色对应关系
 *
 * @author wedu
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRoleEntity> implements SysUserRoleService {

	//保存或更新用户角色之间的关联关系
	@Override
	public void saveOrUpdate(Long userId, List<Long> roleIdList) {
		//先删除用户与角色关系
		this.removeByMap(new MapUtils().put("user_id", userId));

		if(roleIdList == null || roleIdList.size() == 0){
			return ;
		}

		//保存用户与角色关系
		for(Long roleId : roleIdList){
			SysUserRoleEntity sysUserRoleEntity = new SysUserRoleEntity();
			sysUserRoleEntity.setUserId(userId);
			sysUserRoleEntity.setRoleId(roleId);

			this.save(sysUserRoleEntity);
		}
	}

	//查询与指定用户关联的所有角色列表，这里直接调用baseMapper的方法
	@Override
	public List<Long> queryRoleIdList(Long userId) {
		return baseMapper.queryRoleIdList(userId);
	}

	//批量删除与指定id数组相关的用户与角色关系
	@Override
	public int deleteBatch(Long[] roleIds){
		return baseMapper.deleteBatch(roleIds);
	}

	//通过id查询角色列表
	@Override
	public List<String> queryRoleNameList(String userId) {
		return baseMapper.queryRoleNameList(userId);
	}
}
