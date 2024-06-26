

package com.wedu.modules.app.annotation;

import java.lang.annotation.*;

/**
 * app登录效验
 *
 * @author wedu
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {
}
