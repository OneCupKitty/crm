package com.zhangleifeng.crm.workbench.service.impl;

import com.zhangleifeng.crm.workbench.domain.Activity;
import com.zhangleifeng.crm.workbench.domain.ActivityRemark;
import com.zhangleifeng.crm.workbench.mapper.ActivityRemarkMapper;
import com.zhangleifeng.crm.workbench.service.ActivityRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: crm-project
 * @description:
 * @author: Zhang_Leifeng
 * @create: 2022-08-06 16:19
 **/
@Service
public class ActivityRemarkServiceImpl implements ActivityRemarkService {

    @Autowired
    ActivityRemarkMapper activityRemarkMapper;


    @Override
    public List<ActivityRemark> selectActivityRemarkForDetailByActivityId(String activityId) {
        return activityRemarkMapper.selectActivityRemarkForDetailByActivityId(activityId);
    }

    @Override
    public int insertActivityRemark(ActivityRemark activityRemark) {
        return activityRemarkMapper.insertActivityRemark(activityRemark);
    }

    @Override
    public int deleteActivityRemarkById(String remarkId) {
        return activityRemarkMapper.deleteActivityRemarkById(remarkId);
    }

    @Override
    public int updateActivity(ActivityRemark remark) {
        return activityRemarkMapper.updateActivity(remark);
    }
}
