package com.bjpowernode.crm.workbench.service.impl;

import com.bjpowernode.crm.commons.constants.Constants;
import com.bjpowernode.crm.commons.utils.DateUtils;
import com.bjpowernode.crm.commons.utils.UUIDUtils;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.workbench.domain.Customer;
import com.bjpowernode.crm.workbench.domain.FunnelVO;
import com.bjpowernode.crm.workbench.domain.Tran;
import com.bjpowernode.crm.workbench.mapper.CustomerMapper;
import com.bjpowernode.crm.workbench.mapper.TranMapper;
import com.bjpowernode.crm.workbench.service.TranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionManager;
import org.springframework.util.unit.DataUnit;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Jerry
 * @version 1.0
 * @CreateTime 2023-08-06  16:28
 * @Description TODO
 * @since 1.0
 */
@Service("TranService")
public class TranServiceImpl implements TranService {
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private TranMapper tranMapper;
    @Override
    public void saveCreateTran(Map<String, Object> map) {
        //根据name精确查询客户
        String customerName = (String) map.get("customerName");
        Customer customer = customerMapper.selectTureCustomerByName(customerName);
        //如果客户不存在新建客户
        User user = (User) map.get(Constants.SESSION_USER);
        String id = user.getId();
        if (customer == null) {
            customer = new Customer();
            customer.setOwner(id);
            customer.setName(customerName);
            customer.setCreateTime(DateUtils.formatDateTime(new Date()));
            customer.setCreateBy(id);
            customer.setId(UUIDUtils.getUUID());
            customerMapper.insertCustomer(customer);
        }
        //保存创建交易
        Tran tran = new Tran();
        tran.setStage((String) map.get("stage"));
        tran.setId(UUIDUtils.getUUID());
        tran.setMoney((String) map.get("money"));
        tran.setName((String) map.get("name"));
        tran.setOwner((String) map.get("owner"));
        tran.setNextContactTime((String) map.get("nextContactTime"));
        tran.setType((String) map.get("type"));
        tran.setExpectedDate((String) map.get("expectedDate"));
        tran.setCustomerId(customerName);
        tran.setCreateBy(id);
        tran.setCreateTime(DateUtils.formatDateTime(new Date()));
        tran.setContactSummary((String) map.get("contactSummary"));
        tran.setContactsId((String) map.get("contactsId"));
        tran.setActivityId((String) map.get("activityId"));
        tran.setDescription((String) map.get("description"));
        tran.setSource((String) map.get("source"));
         tranMapper.insertTran(tran);
    }

    @Override
    public List<FunnelVO> queryCountOfTranGroupByStage() {
        return tranMapper.selectCountOfTranGroupByStage();
    }
}
