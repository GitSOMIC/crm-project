package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.workbench.domain.ClueRemark;

import java.util.List;

/**
 * @author Jerry
 * @version 1.0
 * @CreateTime 2023-06-20  23:03
 * @Description TODO
 * @since 1.0
 */
public interface ClueRemarkService {
    List<ClueRemark> queryClueRemarkForDetailByClueId(String clueId);
}
