package com.wedu.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 字典表
 *
 * @author passer
 * @date 2020-05-28 14:07:12
 */
@Data
@TableName("sys_dict")
public class SysDictEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(type = IdType.ASSIGN_UUID)
	private String id;
	/**
	 * 字典编码
	 */
	private String code;
	/**
	 * 1 字典 2字典项
	 */
	private String type;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 字典名称
	 */
	private String name;
	/**
	 * 字典值
	 */
	private String value;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
