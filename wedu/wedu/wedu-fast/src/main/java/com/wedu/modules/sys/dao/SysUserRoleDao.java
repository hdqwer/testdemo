

package com.wedu.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wedu.modules.sys.entity.SysUserRoleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户与角色对应关系
 *
 * @author wedu
 */
@Mapper
public interface SysUserRoleDao extends BaseMapper<SysUserRoleEntity> {

	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<Long> queryRoleIdList(Long userId);


	/**
	 * 根据角色ID数组，批量删除
	 */
	int deleteBatch(Long[] roleIds);

	/**
	 * 根据用户ID获取角色名称列表
	 * @param userId 用户ID
	 * @return list
	 */
	List<String> queryRoleNameList(String userId);
}
