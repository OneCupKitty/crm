package com.zhangleifeng.crm.workbench.service;

import com.zhangleifeng.crm.workbench.domain.Activity;

import java.util.List;
import java.util.Map;

public interface ActivityService {
    int insertActivity(Activity activity);

    List<Activity> selectActivityByConditionForPage(Map<String,Object> map);

    int selectCountOfActivityByCondition(Map<String,Object> map);

    int deleteActivityById(String [] ids);

    Activity selectActivityById(String id);

    int updateActivityById(Activity activity);

    List<Activity> selectAllActivities();

    List<Activity> selectActivitiesByIds(String[] ids);

    int insertActivityByList(List<Activity> activityList);

    Activity selectActivityForDetail(String id);

    List<Activity> selectActivityByClueId(String id);

    List<Activity> selectActivityForDetailByNameAndClueId(Map<String,Object> map);

    List<Activity> selectActivityForDetailByActivityIdArray(String [] ids);
}
