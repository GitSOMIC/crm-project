package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.workbench.domain.ActivitiesRemark;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jerry
 * @version 1.0
 * @CreateTime 2023-06-13  23:38
 * @Description TODO
 * @since 1.0
 */
public interface ActivityRemarkService {
    List<ActivitiesRemark> queryActivityRemarkForDetailByActivityId(String activityId);
    int saveCreateActivityRemark(ActivitiesRemark remark);
    int deleteActivityRemarkById(String id);
    int saveEditActivityRemark(ActivitiesRemark remark);
}
