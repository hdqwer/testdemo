

package com.wedu.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wedu.modules.sys.entity.SysUserTokenEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户Token
 *
 * @author wedu
 */
@Mapper
public interface SysUserTokenDao extends BaseMapper<SysUserTokenEntity> {

    SysUserTokenEntity queryByToken(String token);

}
