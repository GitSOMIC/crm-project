package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.workbench.domain.FunnelVO;

import java.util.List;
import java.util.Map;

/**
 * @author Jerry
 * @version 1.0
 * @CreateTime 2023-08-06  16:27
 * @Description TODO
 * @since 1.0
 */
public interface TranService {
    void saveCreateTran(Map<String,Object> map);
    List<FunnelVO> queryCountOfTranGroupByStage();
}
