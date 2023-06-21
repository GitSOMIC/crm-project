package com.bjpowernode.crm.commons.utils;

import java.util.UUID;

/**
 * @author Jerry
 * @version 1.0
 * @CreateTime 2023-05-27  23:41
 * @Description TODO
 * @since 1.0
 */
public class UUIDUtils {
    /**
     * 获取没有-的uuid值
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
