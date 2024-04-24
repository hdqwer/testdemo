package com.wedu.modules.sys.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wedu.modules.sys.entity.SysLoginLogEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 系统日志
 *
 * @author passer
 */
@Mapper
public interface SysLoginLogDao extends BaseMapper<SysLoginLogEntity> {

    Long findTotalVisitCount();

    Long findTodayVisitCount(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

    Long findTodayIp(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

    int getNumByTime(@Param("beginTime") LocalDateTime beginTime, @Param("endTime") LocalDateTime endTime, @Param("username") String username);
}
