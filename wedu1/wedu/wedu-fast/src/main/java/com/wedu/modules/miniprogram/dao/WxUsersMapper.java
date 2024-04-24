

package com.wedu.modules.miniprogram.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wedu.modules.miniprogram.entity.WxUsers;
import org.apache.ibatis.annotations.Mapper;

/**
 * 微信用户
 *
 * @author ccl
 * @date 2021-05-21 14:08:07
 */
@Mapper
public interface WxUsersMapper extends BaseMapper<WxUsers> {

}
