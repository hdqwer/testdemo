package com.wedu.modules.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wedu.common.utils.PageUtils;
import com.wedu.modules.sys.entity.SysLoginLogEntity;

import java.util.Map;


/**
 * 系统日志
 *
 * @author passer
 */
public interface SysLoginLogService extends IService<SysLoginLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询我的登录日志
     * @param params 分页参数、查询参数
     * @return 分页对象
     */
    PageUtils queryMyLoginPage(Map<String, Object> params);

    Long findTotalVisitCount();

    Long findTodayVisitCount();

    Long findTodayIp();

    Map<String, Object> findLastSevenDaysVisitCount(String username);

    /**
     * 保存登录日志
     *
     * @param loginLog 登录日志
     */
    void saveLoginLog(SysLoginLogEntity loginLog);
}
