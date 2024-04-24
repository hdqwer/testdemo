package com.wedu.modules.miniprogram.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wedu.common.utils.HttpUtils;
import com.wedu.common.utils.R;
import com.wedu.modules.miniprogram.entity.MiniProgramProperties;
import com.wedu.modules.miniprogram.entity.dto.WxDto;
import com.wedu.modules.sys.entity.SysLoginLogEntity;
import com.wedu.modules.sys.entity.SysUserEntity;
import com.wedu.modules.sys.service.SysConfigService;
import com.wedu.modules.sys.service.SysLoginLogService;
import com.wedu.modules.sys.service.SysUserService;
import com.wedu.modules.sys.service.SysUserTokenService;
import lombok.SneakyThrows;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 小程序登录相关请求
 * @author santiago
 */
@RestController
@RequestMapping("/miniprogram/login")
public class LoginController {

    @Resource
    private MiniProgramProperties programProperties;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysLoginLogService sysLoginLogService;

    @Autowired
    private SysUserTokenService sysUserTokenService;

    @Autowired
    private SysConfigService sysConfigService;

    /**
     * 根据wx.login获取的code获取openid。并根据openid获取会话token
     * @param map 请求参数
     * @return 登录token
     */
    @RequestMapping(value = "loginByCode", method = RequestMethod.POST)
    @SneakyThrows
    public R loginByCode(@RequestBody Map<String, String> map) {
        String result = HttpUtils.get("https://api.weixin.qq.com/sns/jscode2session?appid=" + programProperties.getAppId() + "&secret=" + programProperties.getAppSecret() + "&js_code=" + map.get("code") + "&grant_type=authorization_code");
        WxDto wxDto = JSON.parseObject(result, WxDto.class);

        if (null != wxDto) {
            String openId = wxDto.getOpenid();
            String sessionKey = wxDto.getSessionKey();
            if (StringUtils.isNotBlank(openId) && StringUtils.isNotBlank(sessionKey)) {
                // 判断是否已存在系统用户
                SysUserEntity sysUserEntity = sysUserService.getOne(new QueryWrapper<SysUserEntity>().eq("mini_openid",openId));
                if (null != sysUserEntity) {
                    // 返回token
                    return this.login(sysUserEntity);
                }else {
                    // 从参数获取微信用户密码
                    String initPassword = sysConfigService.getValue("wx_init_password");

                    // 从参数获取微信用户角色ID
                    String wxRoleId = sysConfigService.getValue("wx_role");

                    // 超级管理员ID
                    String adminId = sysConfigService.getValue("admin_id");

                    // 创建用户
                    SysUserEntity user = new SysUserEntity();
                    user.setUsername(openId);
                    user.setPassword(initPassword);
                    user.setMiniOpenid(openId);
                    user.setStatus(1);
                    List<Long> roleIds = new ArrayList<>();
                    roleIds.add(Long.parseLong(wxRoleId));
                    user.setRoleIdList(roleIds);
                    user.setCreateUserId(Long.parseLong(adminId));
                    sysUserService.saveUser(user);

                    // 返回token
                    return this.login(user);
                }
            } else {
                return R.error(202, "未获取到用户openid 或 session，请联系管理员！");
            }
        } else{
            return R.error(202,"未获取到code，请联系管理员！");
        }
    }

    private R login(SysUserEntity sysUserEntity){
        //账号锁定
        if(sysUserEntity.getStatus() == 0){
            return R.error("账号已被锁定,请联系管理员");
        }

        //生成token，并保存到数据库
        R r = sysUserTokenService.createTokenForMiniProgram(sysUserEntity.getUserId());

        //保存登录日志
        SysLoginLogEntity loginLog = new SysLoginLogEntity();
        loginLog.setUsername(sysUserEntity.getUsername());
        loginLog.setOpSystem("微信");
        loginLog.setOpBrowser("微信小程序");
        sysLoginLogService.saveLoginLog(loginLog);
        return r;
    }


}
