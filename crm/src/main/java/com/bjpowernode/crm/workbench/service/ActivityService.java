package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.workbench.domain.Activity;

import java.util.List;
import java.util.Map;

/**
 * @author Jerry
 * @version 1.0
 * @CreateTime 2023-05-27  23:02
 * @Description TODO
 * @since 1.0
 */
public interface ActivityService  {
    int saveCreateActivity(Activity activity);

    List<Activity> queryActivityByConditionForPage(Map<String, Object> map);

    int queryCountOfActivityByCondition(Map<String, Object> map);

    int deleteActivityByIds(String[] ids);

    Activity queryActivityById(String id);

    int saveEditActivity(Activity activity);

    List<Activity> queryAllActivities();

    List<Activity> queryActivityByIds(String[] ids);

    int saveCreateActivityByList(List<Activity> activityList);

    Activity queryActivityForDetailById(String id);
    List<Activity> queryActivityForDetailByClueId(String clueId);
    List<Activity> queryActivityForDetailByNameClueId(Map<String,Object> map);

    List<Activity> queryActivityForDetailByActivityIds(String[] activityIds);
    List<Activity> selectActivityForConvertByNameClueId(Map<String,Object> map);

}
