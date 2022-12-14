package com.zhangleifeng.crm.workbench.service;

import com.zhangleifeng.crm.workbench.domain.Activity;
import com.zhangleifeng.crm.workbench.domain.ActivityRemark;

import java.util.List;

public interface ActivityRemarkService {
    List<ActivityRemark> selectActivityRemarkForDetailByActivityId(String activityId);

    int insertActivityRemark(ActivityRemark activityRemark);

    int deleteActivityRemarkById(String remarkId);

    int updateActivity(ActivityRemark remark);
}
