package com.zhangleifeng.crm.workbench.web.controller;

import com.zhangleifeng.crm.commons.contants.Contants;
import com.zhangleifeng.crm.commons.domain.ReturnObject;
import com.zhangleifeng.crm.commons.utils.DateUtils;
import com.zhangleifeng.crm.commons.utils.HSSFWorkBookUtils;
import com.zhangleifeng.crm.commons.utils.UUIDUtils;
import com.zhangleifeng.crm.settings.domain.User;
import com.zhangleifeng.crm.settings.service.UserService;
import com.zhangleifeng.crm.workbench.domain.Activity;
import com.zhangleifeng.crm.workbench.domain.ActivityRemark;
import com.zhangleifeng.crm.workbench.service.ActivityRemarkService;
import com.zhangleifeng.crm.workbench.service.ActivityService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

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

    @Autowired
    ActivityRemarkService activityRemarkService;


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

    @RequestMapping("/workbench/activity/importActivity.do")
    public @ResponseBody Object importActivity(MultipartFile activityFile, String userName, HttpSession session){
        System.out.println("userName="+userName);
        User user=(User) session.getAttribute(Contants.SESSION_USER);
        ReturnObject returnObject=new ReturnObject();
        try {
            InputStream is=activityFile.getInputStream();
            HSSFWorkbook wb=new HSSFWorkbook(is);
            //根据wb获取HSSFSheet对象，封装了一页的所有信息
            HSSFSheet sheet=wb.getSheetAt(0);//页的下标，下标从0开始，依次增加
            //根据sheet获取HSSFRow对象，封装了一行的所有信息
            HSSFRow row=null;
            HSSFCell cell=null;
            Activity activity=null;
            List<Activity> activityList=new ArrayList<>();
            for(int i=1;i<=sheet.getLastRowNum();i++) {//sheet.getLastRowNum()：最后一行的下标
                row=sheet.getRow(i);//行的下标，下标从0开始，依次增加
                activity=new Activity();
                activity.setId(UUIDUtils.getUUID());
                activity.setOwner(user.getId());
                activity.setCreateTime(DateUtils.formatDateTime(new Date()));
                activity.setCreateBy(user.getId());

                for(int j=0;j<row.getLastCellNum();j++) {//row.getLastCellNum():最后一列的下标+1
                    //根据row获取HSSFCell对象，封装了一列的所有信息
                    cell=row.getCell(j);//列的下标，下标从0开始，依次增加

                    //获取列中的数据
                    String cellValue=HSSFWorkBookUtils.getCellValueForStr(cell);
                    if(j==0){
                        activity.setName(cellValue);
                    }else if(j==1){
                        activity.setStartDate(cellValue);
                    }else if(j==2){
                        activity.setEndDate(cellValue);
                    }else if(j==3){
                        activity.setCost(cellValue);
                    }else if(j==4){
                        activity.setDescription(cellValue);
                    }
                }

                //每一行中所有列都封装完成之后，把activity保存到list中
                activityList.add(activity);
            }

            //调用service层方法，保存市场活动
            int ret=activityService.insertActivityByList(activityList);

            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setReturnData(ret);
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试....");
        }

        return returnObject;
    }

    @RequestMapping("/workbench/activity/detailActivity.do")
    public String detailActivity(String id,HttpServletRequest request){
        //调用service层方法，查询数据
        Activity activity=activityService.selectActivityForDetail(id);
        List<ActivityRemark> remarkList=activityRemarkService.selectActivityRemarkForDetailByActivityId(id);
        //把数据保存到request中
        request.setAttribute("activity",activity);
        request.setAttribute("remarkList",remarkList);
        //请求转发
        return "workbench/activity/detail";
    }

    @RequestMapping("/workbench/activity/insertActivityRemarkByRemarkId")
    @ResponseBody
    public Object insertActivityRemarkByRemarkId(){
        return null;
    }
}
