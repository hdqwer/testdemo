package com.wedu.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wedu.common.utils.PageUtils;
import com.wedu.common.utils.Query;
import com.wedu.modules.sys.dao.SysDeptDao;
import com.wedu.modules.sys.entity.SysDeptEntity;
import com.wedu.modules.sys.service.SysDeptService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("sysDeptService")
public class SysDeptServiceImpl extends ServiceImpl<SysDeptDao, SysDeptEntity> implements SysDeptService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysDeptEntity> page = this.page(
                new Query<SysDeptEntity>().getPage(params),
                new QueryWrapper<SysDeptEntity>()
        );

        return new PageUtils(page);
    }

}
