package com.zhangleifeng.crm.workbench.web.controller;

import com.zhangleifeng.crm.commons.contants.Contants;
import com.zhangleifeng.crm.commons.domain.ReturnObject;
import com.zhangleifeng.crm.commons.utils.DateUtils;
import com.zhangleifeng.crm.commons.utils.HSSFWorkBookUtils;
import com.zhangleifeng.crm.commons.utils.UUIDUtils;
import com.zhangleifeng.crm.settings.domain.User;
import com.zhangleifeng.crm.settings.service.UserService;
import com.zhangleifeng.crm.workbench.domain.Activity;
import com.zhangleifeng.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: crm-project
 * @description:
 * @author: Zhang_Leifeng
 * @create: 2022-08-01 10:31
 **/
@Controller
public class ActivityController {

    @Autowired
    UserService userService;

    @Autowired
    ActivityService activityService;


    @RequestMapping("/workbench/activity/index.do")
    public String index(HttpServletRequest request){
        List<User> userList = userService.selectUserList();
        request.setAttribute("userList",userList);
        return "workbench/activity/index";
    }

    @RequestMapping("/workbench/activity/insertActivity.do")
    @ResponseBody
    public Object insertActivity(Activity activity, HttpSession session){
        User user = (User) session.getAttribute(Contants.SESSION_USER);
        //封装activity信息
        activity.setId(UUIDUtils.getUUID());
        activity.setCreateTime(DateUtils.formatDateTime(new Date()));
        activity.setCreateBy(user.getId());

        ReturnObject returnObject = new ReturnObject();
        //调用activity service
        try {
            int count = activityService.insertActivity(activity);
            if (count == 1) {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙,请稍后再试");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙,请稍后再试");
        }

        return returnObject;
    }

    @RequestMapping("/workbench/activity/selectByConditionForPage.do")
    @ResponseBody
    public Object selectByConditionForPage(String name,String owner,String startDate,String endDate,int pageNo,int pageSize){
        //封装参数
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("owner",owner);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("beginNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        //查询
        List<Activity> activityList = activityService.selectActivityByConditionForPage(map);
        int totalRows = activityService.selectCountOfActivityByCondition(map);
        //封装结果
        Map<String,Object> retMap = new HashMap<String,Object>();
        retMap.put("activityList",activityList);
        retMap.put("totalRows",totalRows);
        return retMap;
    }

    @RequestMapping("/workbench/activity/deleteActivityByIds.do")
    @ResponseBody
    public Object deleteActivityById(String [] id){//String [] id = request.getParameter("id");
        ReturnObject returnObject = new ReturnObject();
        try {
            int count = activityService.deleteActivityById(id);
            if (count == id.length) {
                //删除成功
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙,请稍后再试");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙,请稍后再试");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/activity/selectActivityById.do")
    @ResponseBody
    public Object selectActivityById(String id){
        Activity activity = activityService.selectActivityById(id);
        return activity;
    }

    @RequestMapping("/workbench/activity/updateActivityById.do")
    @ResponseBody
    public Object updateActivityById(Activity activity,HttpSession session){
        User user = (User) session.getAttribute(Contants.SESSION_USER);
        activity.setEditBy(user.getId());
        activity.setEditTime(DateUtils.formatDateTime(new Date()));

        ReturnObject returnObject = new ReturnObject();
        try {
            int count = activityService.updateActivityById(activity);
            if (count == 1){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙,请稍后再试");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙,请稍后再试");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/activity/exportAllActivities.do")
    public void exportAllActivities(HttpServletResponse response) throws Exception{
        //调用service层方法，查询所有的市场活动
        List<Activity> activityList=activityService.selectAllActivities();
        HSSFWorkBookUtils.getActivityWorkBookXls(activityList,response);
    }
    @RequestMapping("/workbench/activity/exportSomeActivitiesByIds.do")
    public void selectSomeActivitiesByIds(String[] id,HttpServletResponse response) throws IOException {//String [] id = request.getParameter("id");
        List<Activity> activityList = activityService.selectActivitiesByIds(id);
        //创建exel文件，并且把activityList写入到excel文件中
        HSSFWorkBookUtils.getActivityWorkBookXls(activityList,response);
    }

}
