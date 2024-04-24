package com.wedu.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wedu.common.utils.PageUtils;
import com.wedu.modules.sys.entity.SysDictEntity;

import java.util.Map;

/**
 * 字典表
 *
 * @author passer
 * @date 2020-05-28 14:07:12
 */
public interface SysDictService extends IService<SysDictEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryChildPage(Map<String, Object> params);
}

