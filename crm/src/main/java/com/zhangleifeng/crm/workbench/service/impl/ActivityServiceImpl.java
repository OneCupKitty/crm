package com.zhangleifeng.crm.workbench.service.impl;

import com.zhangleifeng.crm.workbench.domain.Activity;
import com.zhangleifeng.crm.workbench.mapper.ActivityMapper;
import com.zhangleifeng.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @program: crm-project
 * @description:
 * @author: Zhang_Leifeng
 * @create: 2022-08-01 11:44
 **/
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public int insertActivity(Activity activity) {
        return activityMapper.insertActivity(activity);
    }

    @Override
    public List<Activity> selectActivityByConditionForPage(Map<String, Object> map) {
        return activityMapper.selectActivityByConditionForPage(map);
    }

    @Override
    public int selectCountOfActivityByCondition(Map<String, Object> map) {
        return activityMapper.selectCountOfActivityByCondition(map);
    }

    @Override
    public int deleteActivityById(String[] ids) {
        return activityMapper.deleteActivityByIds(ids);
    }

    @Override
    public Activity selectActivityById(String id) {
        return activityMapper.selectActivityById(id);
    }

    @Override
    public int updateActivityById(Activity activity) {
        return activityMapper.updateActivityById(activity);
    }

    @Override
    public List<Activity> selectAllActivities() {
        return activityMapper.selectAllActivities();
    }

    @Override
    public List<Activity> selectActivitiesByIds(String[] ids) {
        return activityMapper.selectActivitiesByIds(ids);
    }

    @Override
    public int insertActivityByList(List<Activity> activityList) {
        return activityMapper.insertActivityByList(activityList);
    }

    @Override
    public Activity selectActivityForDetail(String id) {
        return activityMapper.selectActivityForDetail(id);
    }

    @Override
    public List<Activity> selectActivityByClueId(String id) {
        return activityMapper.selectActivityByClueId(id);
    }

    @Override
    public List<Activity> selectActivityForDetailByNameAndClueId(Map<String, Object> map) {
        return activityMapper.selectActivityForDetailByNameAndClueId(map);
    }


}
