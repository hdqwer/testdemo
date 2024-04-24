package com.wedu.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 系统日志注解
 *
 * @author wedu
 */
@Target(ElementType.METHOD)
//指定了@SysLog注解只能用于方法上
@Retention(RetentionPolicy.RUNTIME)
//指定了@SysLog的生命周期，RUNTIME表明了这个注解不仅会被保存在编译时的类文件中，而且可以在运行时通过反射机制读取
@Documented
//表示这个注解应当被记录
public @interface SysLog {
//@interface用于声明一个注解类型。
	String value() default "";
	//定义了一个值value，给了value一个默认值“”。当使用@SysLog注解时，可以指定value的值，不指定就是默认值
}
