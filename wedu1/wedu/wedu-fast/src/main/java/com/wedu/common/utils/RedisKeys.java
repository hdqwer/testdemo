

package com.wedu.common.utils;

/**
 * Redis所有Keys
 *
 * @author wedu
 */
public class RedisKeys {

    public static String getSysConfigKey(String key){
        return "sys:config:" + key;
    }
}
