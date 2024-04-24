package com.wedu.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wedu.common.utils.PageUtils;
import com.wedu.modules.sys.entity.SysDeptEntity;

import java.util.Map;

/**
 * 部门表
 *
 * @author passer
 * @date 2019-08-19 14:25:42
 */
public interface SysDeptService extends IService<SysDeptEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

