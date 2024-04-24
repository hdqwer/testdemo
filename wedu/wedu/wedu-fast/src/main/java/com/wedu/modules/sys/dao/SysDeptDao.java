package com.wedu.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wedu.modules.sys.entity.SysDeptEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 部门表
 *
 * @author passer
 * @date 2019-08-19 14:25:42
 */
@Mapper
public interface SysDeptDao extends BaseMapper<SysDeptEntity> {

}
