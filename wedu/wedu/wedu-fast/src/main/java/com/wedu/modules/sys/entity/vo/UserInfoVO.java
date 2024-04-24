package com.wedu.modules.sys.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * 用户信息展示对象
 * @author liuhx
 * @data 2023-3-7
 */
@Data
public class UserInfoVO {

    /**
     * 用户名称
     */
    private String username;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 联系电话
     */
    private String mobile;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 角色名称
     */
    private List<String> roleNames;


}
