

package com.wedu.modules.miniprogram.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 微信用户
 *
 * @author ccl
 * @date 2021-05-21 14:08:07
 */
@Data
@TableName("wx_users")
@ApiModel(value = "微信用户")
public class WxUsers implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value="id")
    private String id;

    /**
     * 名字
     */
    @ApiModelProperty(value="名字")
    private String name;

    /**
     * 手机
     */
    @ApiModelProperty(value="手机")
    private String phone;

    /**
     * 微信openid
     */
    @ApiModelProperty(value="微信openid")
    private String wxOpenId;

    /**
     * 微信sessionkey
     */
    @ApiModelProperty(value="微信sessionkey")
    private String wxSessionKey;

    /**
     * city
     */
    @ApiModelProperty(value="city")
    private String city;

    /**
     * country
     */
    @ApiModelProperty(value="country")
    private String country;

    /**
     * 性别
     */
    @ApiModelProperty(value="性别")
    private String gender;

    /**
     * province
     */
    @ApiModelProperty(value="province")
    private String province;
	/**
	 * cId
	 */
	@ApiModelProperty(value="cId")
	private String cId;

    /**
     * createTime
     */
    @ApiModelProperty(value="createTime")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * updateTime
     */
    @ApiModelProperty(value="updateTime")
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
}
