package com.wedu.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wedu.modules.sys.dao.CategoryDao;
import com.wedu.modules.sys.dao.GoodsDao;
import com.wedu.modules.sys.entity.CategoryEntity;
import com.wedu.modules.sys.entity.GoodsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends ServiceImpl<CategoryDao, CategoryEntity> implements SysCategoryService{
    @Autowired
    private CategoryDao categoryDao;

}
