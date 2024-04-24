

package com.wedu.modules.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wedu.common.utils.PageUtils;
import com.wedu.modules.sys.entity.SysLogEntity;

import java.util.Map;


/**
 * 系统日志
 *
 * @author wedu
 */
public interface SysLogService extends IService<SysLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

}
