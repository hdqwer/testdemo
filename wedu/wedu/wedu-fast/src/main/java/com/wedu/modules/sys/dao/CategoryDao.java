package com.wedu.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wedu.modules.sys.entity.CategoryEntity;
import com.wedu.modules.sys.entity.GoodsEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
}
