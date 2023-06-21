package com.bjpowernode.crm.settings.service;

import com.bjpowernode.crm.settings.domain.DicValue;

import java.util.List;

/**
 * @author Jerry
 * @version 1.0
 * @CreateTime 2023-06-18  22:16
 * @Description TODO
 * @since 1.0
 */
public interface DicValueService {
    List<DicValue> queryDicValueByTypeCode(String typeCode);
}
