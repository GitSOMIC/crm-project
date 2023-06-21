package com.bjpowernode.crm.settings.service;

import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.workbench.domain.Activity;

import java.util.List;
import java.util.Map;

/**
 * @author Jerry
 * @version 1.0
 * @CreateTime 2023-05-22  13:41
 * @Description TODO
 * @since 1.0
 */
public interface UserService {
    User queryUserByLoginActAndPwd(Map<String, Object> map);
    List<User> queryAllUsers();

}
