package com.zhangleifeng.crm.workbench.web.controller;

import com.zhangleifeng.crm.commons.contants.Contants;
import com.zhangleifeng.crm.commons.domain.ReturnObject;
import com.zhangleifeng.crm.commons.utils.DateUtils;
import com.zhangleifeng.crm.commons.utils.SessionUserUtils;
import com.zhangleifeng.crm.commons.utils.UUIDUtils;
import com.zhangleifeng.crm.settings.domain.DicValue;
import com.zhangleifeng.crm.settings.domain.User;
import com.zhangleifeng.crm.settings.service.DicValueService;
import com.zhangleifeng.crm.settings.service.UserService;
import com.zhangleifeng.crm.workbench.domain.Activity;
import com.zhangleifeng.crm.workbench.domain.Clue;
import com.zhangleifeng.crm.workbench.domain.ClueActivityRelation;
import com.zhangleifeng.crm.workbench.domain.ClueRemark;
import com.zhangleifeng.crm.workbench.service.ActivityService;
import com.zhangleifeng.crm.workbench.service.ClueActivityRelationService;
import com.zhangleifeng.crm.workbench.service.ClueRemarkService;
import com.zhangleifeng.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @program: crm-project
 * @description:
 * @author: Zhang_Leifeng
 * @create: 2022-08-08 09:34
 **/
@Controller
public class ClueController {

    @Autowired
    UserService userService;

    @Autowired
    DicValueService dicValueService;

    @Autowired
    ClueService clueService;

    @Autowired
    ClueRemarkService clueRemarkService;

    @Autowired
    ActivityService activityService;

    @Autowired
    ClueActivityRelationService clueActivityRelationService;


    @RequestMapping("/workbench/clue/index.do")
    public String index(HttpServletRequest request){
        //调用service层方法，查询动态数据
        List<User> userList=userService.selectUserList();
       // List<Clue> clueList = clueService.selectAllClue();
        List<DicValue> appellationList=dicValueService.selectDicValueByTypeCode("appellation");
        List<DicValue> clueStateList=dicValueService.selectDicValueByTypeCode("clueState");
        List<DicValue> sourceList=dicValueService.selectDicValueByTypeCode("source");
        //把数据保存到request中
        request.setAttribute("userList",userList);
       // request.setAttribute("clueList",clueList);
        request.setAttribute("appellationList",appellationList);
        request.setAttribute("clueStateList",clueStateList);
        request.setAttribute("sourceList",sourceList);
        //请求转发
        return "workbench/clue/index";
    }

    @RequestMapping("workbench/clue/saveCreateClue.do")
    @ResponseBody
    public Object saveCreateClue(Clue clue, HttpSession session){
        clue.setId(UUIDUtils.getUUID());
        clue.setCreateBy(SessionUserUtils.getSessionUserId(session));
        clue.setCreateTime(DateUtils.formatDateTime(new Date()));

        ReturnObject returnObject = new ReturnObject();

        try {
            int count = clueService.insertClue(clue);
            if (count == 1){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙,请稍后再试....");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙,请稍后再试....");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/clue/detailClue.do")
    public String detailClue(String id,HttpServletRequest request){
        //调用service层方法，查询数据
        Clue clue=clueService.selectClueForDetailByClueId(id);
        List<ClueRemark> remarkList=clueRemarkService.selectClueRemarkForDetailByClueId(id);
        List<Activity> activityList=activityService.selectActivityByClueId(id);
        //把数据保存到request中
        request.setAttribute("clue",clue);
        request.setAttribute("remarkList",remarkList);
        request.setAttribute("activityList",activityList);
        //请求转发
        return "workbench/clue/detail";
    }

    @RequestMapping("/workbench/clue/selectClueByConditionForPage.do")
    @ResponseBody
    public Object selectClueByConditionForPage(String fullname,String owner,String company,String phone,
                                               String mphone,String state,String source,int pageNo,int pageSize){
        //封装参数
        Map<String,Object> map = new HashMap<>();
        map.put("fullname",fullname);
        map.put("owner",owner);
        map.put("company",company);
        map.put("phone",phone);
        map.put("mphone",mphone);
        map.put("state",state);
        map.put("source",source);
        map.put("beginNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        //查询
        List<Clue> clueList = clueService.selectClueByConditionForPage(map);
        int totalRows = clueService.selectCountClueByCondition(map);
        //封装结果
        Map<String,Object> retMap = new HashMap<String,Object>();
        retMap.put("clueList",clueList);
        retMap.put("totalRows",totalRows);
        return retMap;
    }

    @RequestMapping("/workbench/clue/queryActivityForDetailByNameClueId.do")
    public @ResponseBody Object queryActivityForDetailByNameClueId(String activityName,String clueId){
        //封装参数
        Map<String,Object> map=new HashMap<>();
        map.put("activityName",activityName);
        map.put("clueId",clueId);
        //调用service层方法，查询市场活动
        List<Activity> activityList=activityService.selectActivityForDetailByNameAndClueId(map);
        //根据查询结果，返回响应信息
        return activityList;
    }
    @RequestMapping("/workbench/clue/saveClueActivityRelation.do")
    @ResponseBody
    public Object saveClueActivityRelation(String[] activityId,String clueId){
        //封装参数
        ClueActivityRelation car=null;
        List<ClueActivityRelation> relationList=new ArrayList<>();
        for(String ai:activityId){
            car=new ClueActivityRelation();
            car.setActivityId(ai);
            car.setClueId(clueId);
            car.setId(UUIDUtils.getUUID());
            relationList.add(car);
        }

        ReturnObject returnObject=new ReturnObject();
        try {
            //调用service方法，批量保存线索和市场活动的关联关系
            int ret = clueActivityRelationService.insertClueActivityRelationByList(relationList);

            if(ret>0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);

                List<Activity> activityList=activityService.selectActivityForDetailByActivityIdArray(activityId);
                returnObject.setReturnData(activityList);
            }else{
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后重试....");
            }
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试....");
        }

        return returnObject;
    }

    @RequestMapping("/workbench/clue/deleteClueActivityRelation.do")
    @ResponseBody
    public Object deleteClueActivityRelation(String activityId,String clueId){
        ClueActivityRelation clueActivityRelation = new ClueActivityRelation();
        clueActivityRelation.setActivityId(activityId);
        clueActivityRelation.setClueId(clueId);
        ReturnObject returnObject = new ReturnObject();
        try {
            int count = clueActivityRelationService.deleteClueActivityRelationByActivityIdAndClueId(clueActivityRelation);
            if (count > 0){
                //成功
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);

            }else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后重试....");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试....");
        }
        return returnObject;

    }

    @RequestMapping("/workbench/clue/toConvert.do")
    public String toConvert(String id,HttpServletRequest request){
        //调用service层方法，查询线索的明细信息
        Clue clue=clueService.selectClueForDetailByClueId(id);
        List<DicValue> stageList=dicValueService.selectDicValueByTypeCode("stage");
        //把数据保存到request中
        request.setAttribute("clue",clue);
        request.setAttribute("stageList",stageList);
        //请求转发
        return "workbench/clue/convert";
    }

    @RequestMapping("/workbench/clue/queryActivityForConvertByNameClueId.do")
    @ResponseBody
    public Object queryActivityForConvertByNameClueId(String activityName,String clueId){
        Map<String,Object> map = new HashMap<>();
        map.put("activityName",activityName);
        map.put("clueId",clueId);
        List<Activity> activityList = activityService.selectActivityForConvertByNameClueId(map);
        return activityList;

    }

    @RequestMapping("/workbench/clue/convertClue.do")
    public @ResponseBody Object convertClue(String clueId,String money,String name,String expectedDate,String stage,String activityId,String isCreateTran,HttpSession session){
        //封装参数
        Map<String,Object> map=new HashMap<>();
        map.put("clueId",clueId);
        map.put("money",money);
        map.put("name",name);
        map.put("expectedDate",expectedDate);
        map.put("stage",stage);
        map.put("activityId",activityId);
        map.put("isCreateTran",isCreateTran);
        map.put(Contants.SESSION_USER,SessionUserUtils.getSessionUser(session));

        ReturnObject returnObject=new ReturnObject();
        try {
            //调用service层方法，保存线索转换
            clueService.saveClueConvert(map);

            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试....");
        }

        return returnObject;
    }


}
