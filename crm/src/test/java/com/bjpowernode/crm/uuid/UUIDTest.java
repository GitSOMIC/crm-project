package com.bjpowernode.crm.uuid;

import java.util.UUID;

/**
 * @author Jerry
 * @version 1.0
 * @CreateTime 2023-05-27  23:21
 * @Description TODO
 * @since 1.0
 */
public class UUIDTest {
    public static void main(String[] args){
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        System.out.println(uuid);
    }
}
