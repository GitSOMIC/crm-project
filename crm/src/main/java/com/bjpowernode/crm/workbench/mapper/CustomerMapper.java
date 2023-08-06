package com.bjpowernode.crm.workbench.mapper;

import com.bjpowernode.crm.workbench.domain.Customer;

import java.util.List;

public interface CustomerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_customer
     *
     * @mbg.generated Sat Jul 29 23:13:04 HKT 2023
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_customer
     *
     * @mbg.generated Sat Jul 29 23:13:04 HKT 2023
     */
    int insert(Customer row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_customer
     *
     * @mbg.generated Sat Jul 29 23:13:04 HKT 2023
     */
    int insertSelective(Customer row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_customer
     *
     * @mbg.generated Sat Jul 29 23:13:04 HKT 2023
     */
    Customer selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_customer
     *
     * @mbg.generated Sat Jul 29 23:13:04 HKT 2023
     */
    int updateByPrimaryKeySelective(Customer row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_customer
     *
     * @mbg.generated Sat Jul 29 23:13:04 HKT 2023
     */
    int updateByPrimaryKey(Customer row);

    /**
     * 保存创建的客户
     * @param customer
     * @return
     */
    int insertCustomer(Customer customer);

    /**
     * 查询所有客户名称
     * @return
     */
    List<String> selectAllCustomerName();

    List<String> selectCustomerByName(String customerName);

    Customer selectTureCustomerByName(String name);
}