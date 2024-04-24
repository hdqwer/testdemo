package com.wedu.modules.miniprogram.entity.dto;

import lombok.Data;

@Data
public class WxDto {

    /**
     * 错误码
     */
    private String errcode;

    /**
     * 错误信息
     */
    private String errmsg;

    /**
     * session key
     */
    private String sessionKey;

    /**
     * openid
     */
    private String openid;

    /**
     * // cId
     */
    private String cId;
}
