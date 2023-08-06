package com.bjpowernode.crm.workbench.service.impl;

import com.bjpowernode.crm.workbench.mapper.CustomerMapper;
import com.bjpowernode.crm.workbench.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jerry
 * @version 1.0
 * @CreateTime 2023-08-06  01:44
 * @Description TODO
 * @since 1.0
 */
@Service("CustomerService")
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;
    @Override
    public List<String> queryAllCustomerName() {
        //调用service层方法，查询所有客户名称
        return customerMapper.selectAllCustomerName();
    }

    @Override
    public List<String> queryCustomerByName(String customerName) {
        return customerMapper.selectCustomerByName(customerName);
    }
}
