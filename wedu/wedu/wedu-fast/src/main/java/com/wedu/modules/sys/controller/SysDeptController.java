package com.wedu.modules.sys.controller;

import com.wedu.common.utils.R;
import com.wedu.modules.sys.entity.SysDeptEntity;
import com.wedu.modules.sys.service.SysDeptService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


/**
 * 部门表
 *
 * @author passer
 * @date 2019-08-19 14:25:42
 */
@RestController
@RequestMapping("/sys/dept")
public class SysDeptController {
    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:dept:list")
    public List<SysDeptEntity> list() {
        List<SysDeptEntity> sysDeptEntities = sysDeptService.list();
        for (SysDeptEntity sysDeptEntity : sysDeptEntities) {
            SysDeptEntity parentDeptEntity = sysDeptService.getById(sysDeptEntity.getParentId());
            if (parentDeptEntity != null) {
                sysDeptEntity.setParentName(parentDeptEntity.getName());
            }
        }
        return sysDeptEntities;
    }

    /**
     * 列表
     */
    @RequestMapping("/tree")
    @RequiresPermissions("sys:dept:list")
    public List<Map<String, Object>> tree() {
        List<SysDeptEntity> sysDeptEntities = sysDeptService.list();
        List<Map<String, Object>> result = new ArrayList<>();

        for (SysDeptEntity sysDeptEntity : sysDeptEntities) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", sysDeptEntity.getId());
            map.put("label", sysDeptEntity.getName());
            map.put("parentId", sysDeptEntity.getParentId());
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

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:dept:info")
    public R info(@PathVariable("id") String id) {
        SysDeptEntity sysDept = sysDeptService.getById(id);

        return R.ok().put("dept", sysDept);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:dept:save")
    public R save(@RequestBody SysDeptEntity sysDept) {
        sysDept.setCreateTime(new Date());
        sysDeptService.save(sysDept);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:dept:update")
    public R update(@RequestBody SysDeptEntity sysDept) {
        sysDeptService.updateById(sysDept);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:dept:delete")
    public R delete(@RequestBody String[] ids) {
        sysDeptService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
