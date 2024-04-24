package com.wedu.modules.sys.controller;


import com.wedu.common.annotation.SysLog;
import com.wedu.common.utils.PageUtils;
import com.wedu.common.utils.R;
import com.wedu.common.validator.ValidatorUtils;
import com.wedu.common.validator.group.AddGroup;
import com.wedu.modules.sys.entity.CategoryEntity;
import com.wedu.modules.sys.entity.GoodsEntity;
import com.wedu.modules.sys.entity.SysDeptEntity;
import com.wedu.modules.sys.entity.SysUserEntity;
import com.wedu.modules.sys.service.CategoryService;
import com.wedu.modules.sys.service.GoodsService;
import oracle.jdbc.proxy.annotation.Post;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/sys/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CategoryService categoryService;


    //获取全部数据以及分页功能
    @GetMapping("/list")
    //Shrio框架的权限控制注解
    @RequiresPermissions("sys:goods:list")
    //@RequestParam表示：从请求参数中获取数据，并将它们放进map中
    public R list(@RequestParam Map<String, Object> params){
        //queryPage用于从数据库中查询分页信息并返回一个封装了 分页信息的PageUtils对象
        PageUtils page = goodsService.queryPage(params);
        System.out.println(page);
        //.put("page", page);将分页数据放入响应体中
        return R.ok().put("page", page);
    }





    //通过id获取商品（暂时没用到）
    @GetMapping("/{id}")
    public GoodsEntity getGoodsById(@PathVariable Long id) {
        return goodsService.getById(id);
    }

    /**
     * 保存用户
     */
    @PostMapping("/save")
    @RequiresPermissions("sys:goods:save")
    public R save(@RequestBody GoodsEntity goods){
//        goods.setAddTime(new Date());

        goodsService.save(goods);

        return R.ok();
    }


    //更新商品
    @PostMapping("/update")
    @RequiresPermissions("sys:goods:update")
    public R updateGoods(@RequestBody GoodsEntity goods) {
        goods.setAddTime(new Date());
        goodsService.updateById(goods);
        System.out.println(goods.toString());
        return R.ok();
    }

    //删除商品
    @PostMapping("/delete")
    @RequiresPermissions("sys:goods:delete")
    public R deleteGoodsById(@RequestBody Long[] ids) {
        goodsService.deleteBatch(ids);
        return R.ok();
    }

    //根据商品ID获取商品信息
    @GetMapping("/info/{goodsId}")
//    @RequiresPermissions("sys:goods:info")
    public R info(@PathVariable("goodsId") Long goodsId){
        // 根据商品ID获取商品信息
        GoodsEntity goods = goodsService.getById(goodsId);


        return R.ok().put("goods", goods);
    }


    // 树型选择器
    @RequestMapping("/tree")
    public List<Map<String, Object>> tree() {
        List<CategoryEntity> categoryEntities = categoryService.list();
        List<Map<String, Object>> result = new ArrayList<>();

        for (CategoryEntity categoryEntity : categoryEntities) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", categoryEntity.getId());
            map.put("label", categoryEntity.getName());
            map.put("parentId", categoryEntity.getParentId());
            result.add(map);
        }
        // 添加顶级菜单
        Map<String, Object> root = new HashMap<>();
        root.put("id", 0L);
        root.put("parentId", -1L);
        root.put("label", "顶级");
        result.add(root);
        return result;
    }



}