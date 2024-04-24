package com.wedu.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wedu.modules.sys.entity.SysDictEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 字典表
 *
 * @author passer
 * @date 2020-05-28 14:07:12
 */
@Mapper
public interface SysDictDao extends BaseMapper<SysDictEntity> {

}
