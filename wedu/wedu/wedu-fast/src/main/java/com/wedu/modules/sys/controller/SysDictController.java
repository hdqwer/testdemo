package com.wedu.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wedu.common.utils.PageUtils;
import com.wedu.common.utils.R;
import com.wedu.modules.sys.entity.SysDictEntity;
import com.wedu.modules.sys.service.SysDictService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;


/**
 * 字典表
 *
 * @author passer
 * @date 2020-05-28 14:07:12
 */
@RestController
@RequestMapping("sys/dict")
public class SysDictController {
    @Autowired
    private SysDictService sysDictService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:dict:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = sysDictService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/childList")
    @RequiresPermissions("sys:dict:list")
    public R childList(@RequestParam Map<String, Object> params) {
        PageUtils page = sysDictService.queryChildPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:dict:info")
    public R info(@PathVariable("id") String id) {
        SysDictEntity sysDict = sysDictService.getById(id);

        return R.ok().put("sysDict", sysDict);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:dict:save")
    public R save(@RequestBody SysDictEntity sysDict) {
        sysDict.setCreateTime(new Date());
        sysDictService.save(sysDict);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:dict:update")
    public R update(@RequestBody SysDictEntity sysDict) {
        sysDictService.updateById(sysDict);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:dict:delete")
    public R delete(@RequestBody String[] ids) {
        for (String id : ids) {
            sysDictService.remove(new QueryWrapper<SysDictEntity>()
                    .eq("code", sysDictService.getById(id).getCode())
            );
        }
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/deleteChild")
    @RequiresPermissions("sys:dict:delete")
    public R deleteChild(@RequestBody String[] ids) {
        sysDictService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
