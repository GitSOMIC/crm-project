package com.bjpowernode.crm.workbench.service.impl;

import com.bjpowernode.crm.workbench.domain.ActivitiesRemark;
import com.bjpowernode.crm.workbench.mapper.ActivitiesRemarkMapper;
import com.bjpowernode.crm.workbench.service.ActivityRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jerry
 * @version 1.0
 * @CreateTime 2023-06-13  23:39
 * @Description TODO
 * @since 1.0
 */
@Service("activityRemarkService")
public class ActivityRemarkServiceImpl implements ActivityRemarkService {
    @Autowired
    private ActivitiesRemarkMapper activitiesRemarkMapper;
    @Override
    public List<ActivitiesRemark> queryActivityRemarkForDetailByActivityId(String activityId) {
        return activitiesRemarkMapper.selectActivityRemarkForDetailById(activityId);
    }

    @Override
    public int saveCreateActivityRemark(ActivitiesRemark remark) {
        return activitiesRemarkMapper.insertActivityRemark(remark);
    }

    @Override
    public int deleteActivityRemarkById(String id) {
        return activitiesRemarkMapper.deleteActivityRemarkById(id);
    }

    @Override
    public int saveEditActivityRemark(ActivitiesRemark remark) {
        return activitiesRemarkMapper.updateActivityRemark(remark);
    }
}
