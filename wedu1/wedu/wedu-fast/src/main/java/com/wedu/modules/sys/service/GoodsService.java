package com.wedu.modules.sys.service;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wedu.common.utils.PageUtils;
import com.wedu.common.utils.Query;
import com.wedu.modules.sys.dao.SysUserDao;
import com.wedu.modules.sys.entity.GoodsEntity;
import com.wedu.modules.sys.dao.GoodsDao;
import com.wedu.modules.sys.entity.SysUserEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Service
public class GoodsService extends ServiceImpl<GoodsDao, GoodsEntity> implements SysGoodsService{

    @Autowired
    private GoodsDao goodsDao;



    //通过id获取信息
    public GoodsEntity getById(Long id) {
        return goodsDao.getById(id);
    }

    //保存商品信息
    public boolean save(GoodsEntity goods) {
        goods.setAddTime(new Date());
        return goodsDao.save(goods)>0;
    }


    public void deleteBatch(Long[] ids) {
        this.removeByIds(Arrays.asList(ids));
    }


    // 获取所有商品信息，分页
    public PageUtils queryPage(Map<String, Object> params) {
        String name = (String)params.get("goodsname");
        Long id = (Long)params.get("id");
        System.out.println(name+id);
//        String username = (String)params.get("username");
//        Long createUserId = (Long)params.get("createUserId");
        //获取姓名和id
        //mybatis-plus
        //这里page方法用来执行分页查询，接收参数（分页信息对象，条件查询对象）
        //1.getone。在创建完实例时候写：queryWrapper.last（“limit 1”），然后再写getOne
        //2.and拼接，需要加表达式
        //3.lambda。。。使用表达式表示实体类
        IPage<GoodsEntity> page = this.page(
                new Query<GoodsEntity>().getPage(params),
                //使用query对象获取分页信息
                new QueryWrapper<GoodsEntity>()
                        .like(StringUtils.isNotBlank(name),"name", name)
                        .eq(id != null,"id", id)
                //如果username不为空，添加一个执行条件，查询包含username相关的信息
                //如果createUserId不为空，添加一个等值查询，查询与id相等的记录
        );

        return new PageUtils(page);
        //将查询到的分页结果封装到PageUtils中
    }

}