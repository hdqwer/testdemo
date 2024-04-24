package com.wedu.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wedu.common.utils.PageUtils;
import com.wedu.common.utils.R;
import com.wedu.modules.sys.entity.SysLoginLogEntity;
import com.wedu.modules.sys.entity.SysUserEntity;
import com.wedu.modules.sys.service.SysDeptService;
import com.wedu.modules.sys.service.SysLoginLogService;
import com.wedu.modules.sys.service.SysUserRoleService;
import com.wedu.modules.sys.service.SysUserService;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 系统日志
 *
 * @author passer
 */
@RestController
@RequestMapping("/sys/loginLog")
public class SysLoginLogController {
    @Autowired
    private SysLoginLogService sysLoginLogService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysDeptService sysDeptService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 列表
     */
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("sys:loginlog:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = sysLoginLogService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 我的登录日志列表
     * @return
     */
    @ResponseBody
    @GetMapping("/myLoginList")
    public R myLoginList(@RequestParam Map<String, Object> params) {
        SysUserEntity loginUser = (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
        params.put("userName",loginUser.getUsername());
        PageUtils page = sysLoginLogService.queryMyLoginPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:loginlog:delete")
    public R delete(@RequestBody String[] ids) {
        sysLoginLogService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 获取登录历史列表
     */
    @ResponseBody
    @GetMapping("/getLogByUser/{userId}/{userName}")
    public R getLogByUser(@PathVariable("userId") String userId, @PathVariable("userName") String username) {
        Map<String, Object> data = Maps.newHashMap();
        // 获取用户角色、所属部门
        String deptId = sysUserService.getById(userId).getDeptId();
        if (StringUtils.isNotBlank(deptId) && !"0".equals(deptId)) {
            data.put("deptName", sysDeptService.getById(deptId).getName());
        } else {
            data.put("deptName", "顶级部门");
        }

        //获取用户所属的角色列表
        List<String> roleIdList = sysUserRoleService.queryRoleNameList(userId);
        data.put("roleList", roleIdList);
        List<SysLoginLogEntity> sysLoginLogEntityList = sysLoginLogService.list(
                new QueryWrapper<SysLoginLogEntity>()
                        .eq("username", username)
                        .orderByDesc("login_time")
        );
        if (sysLoginLogEntityList.size() > 1) {
            data.put("lastLogin", sysLoginLogEntityList.get(1).getLoginTime());
        } else {
            data.put("lastLogin", "您是第一次登录哦~");
        }
        // 获取系统访问记录
        Long totalVisitCount = sysLoginLogService.findTotalVisitCount();
        data.put("totalVisitCount", totalVisitCount);
        Long todayVisitCount = sysLoginLogService.findTodayVisitCount();
        data.put("todayVisitCount", todayVisitCount);
        Long todayIp = sysLoginLogService.findTodayIp();
        data.put("todayIp", todayIp);
        // 获取近期系统访问记录
        data.put("lastSevenVisitCount", sysLoginLogService.findLastSevenDaysVisitCount(username));
        return R.ok().put("data", data);
    }


}
