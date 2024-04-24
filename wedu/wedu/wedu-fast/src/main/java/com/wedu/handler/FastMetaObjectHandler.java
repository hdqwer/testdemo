package com.wedu.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FastMetaObjectHandler implements MetaObjectHandler {

	/**
	 * 插入时的填充策略
	 * @param metaObject metaObject
	 */
	@Override
	public void insertFill(MetaObject metaObject) {
		this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
	}

	/**
	 * 更新时的填充策略
	 * @param metaObject metaObject
	 */
	@Override
	public void updateFill(MetaObject metaObject) {
		this.setFieldValByName("updateTime", new Date(), metaObject);
	}
}
