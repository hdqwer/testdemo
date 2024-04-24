

package com.wedu.modules.sys.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wedu.modules.sys.entity.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志
 *
 * @author wedu
 */
@Mapper
public interface SysLogDao extends BaseMapper<SysLogEntity> {

}
