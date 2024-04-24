

package com.wedu.modules.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wedu.modules.app.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户
 *
 * @author wedu
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

}
