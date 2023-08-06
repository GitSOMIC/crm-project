package com.bjpowernode.crm.workbench.service;

import java.util.List;

/**
 * @author Jerry
 * @version 1.0
 * @CreateTime 2023-08-06  01:42
 * @Description TODO
 * @since 1.0
 */
public interface CustomerService {
    List<String> queryAllCustomerName();

    List<String> queryCustomerByName(String customerName);
}
