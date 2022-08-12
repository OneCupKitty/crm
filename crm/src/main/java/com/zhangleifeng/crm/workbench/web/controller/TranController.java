package com.zhangleifeng.crm.workbench.web.controller;

import com.zhangleifeng.crm.commons.contants.Contants;
import com.zhangleifeng.crm.commons.domain.ReturnObject;
import com.zhangleifeng.crm.commons.utils.SessionUserUtils;
import com.zhangleifeng.crm.settings.domain.DicValue;
import com.zhangleifeng.crm.settings.domain.User;
import com.zhangleifeng.crm.settings.service.DicValueService;
import com.zhangleifeng.crm.settings.service.UserService;
import com.zhangleifeng.crm.workbench.domain.Activity;
import com.zhangleifeng.crm.workbench.domain.Contacts;
import com.zhangleifeng.crm.workbench.domain.Tran;
import com.zhangleifeng.crm.workbench.service.ActivityService;
import com.zhangleifeng.crm.workbench.service.ContactsService;
import com.zhangleifeng.crm.workbench.service.CustomerService;
import com.zhangleifeng.crm.workbench.service.TranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @program: crm-project
 * @description:
 * @author: Zhang_Leifeng
 * @create: 2022-08-11 15:50
 **/
@Controller
public class TranController {
    @Autowired
    private DicValueService dicValueService;

    @Autowired
    private UserService userService;

    @Autowired
    TranService tranService;

    @Autowired
    ContactsService contactsService;

    @Autowired
    ActivityService activityService;

    @Autowired
    CustomerService customerService;


    @RequestMapping("/workbench/transaction/index.do")
    public String index(HttpServletRequest request){
        //调用service层方法，查询动态数据
        List<DicValue> stageList=dicValueService.selectDicValueByTypeCode("stage");
        List<DicValue> transactionTypeList=dicValueService.selectDicValueByTypeCode("transactionType");
        List<DicValue> sourceList=dicValueService.selectDicValueByTypeCode("source");
        //把数据保存到request
        request.setAttribute("stageList",stageList);
        request.setAttribute("transactionTypeList",transactionTypeList);
        request.setAttribute("sourceList",sourceList);
        //请求转发
        return "workbench/transaction/index";
    }


    @RequestMapping("/workbench/transaction/selectTranByConditionForPage.do")
    @ResponseBody
    public Object selectTranByConditionForPage(String owner,String name,String customerName,String stage
                                            ,String myType,String source,String contactName,int pageNo,int pageSize){
        Map<String,Object> map =new HashMap<>();
        map.put("owner",owner);
        map.put("name",name);
        map.put("customerName",customerName);
        map.put("stage",stage);
        map.put("type",myType);
        map.put("source",source);
        map.put("contactName",contactName);
        map.put("beginNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        List<Tran> tranList = tranService.selectTranByConditionForPage(map);
        int totalRows =  tranService.selectCountTranByConditionForPage(map);
        //封装结果
        Map<String,Object> retMap = new HashMap<String,Object>();
        retMap.put("tranList",tranList);
        retMap.put("totalRows",totalRows);
        return retMap;
    }



    @RequestMapping("/workbench/transaction/toSave.do")
    public String toSave(HttpServletRequest request){
        //调用service层方法，查询动态数据
        List<User> userList=userService.selectUserList();
        List<DicValue> stageList=dicValueService.selectDicValueByTypeCode("stage");
        List<DicValue> transactionTypeList=dicValueService.selectDicValueByTypeCode("transactionType");
        List<DicValue> sourceList=dicValueService.selectDicValueByTypeCode("source");
        //把数据保存到request中
        request.setAttribute("userList",userList);
        request.setAttribute("stageList",stageList);
        request.setAttribute("transactionTypeList",transactionTypeList);
        request.setAttribute("sourceList",sourceList);
        //请求转发
        return "workbench/transaction/save";
    }

    @RequestMapping("/workbench/transaction/searchContacts.do")
    @ResponseBody
    public Object searchContacts(String contactsName){
        List<Contacts> contactsList = contactsService.selectContactsForDetailByName(contactsName);
        return contactsList;
    }

    @RequestMapping("/workbench/transaction/searchActivity.do")
    @ResponseBody
    public Object searchActivity(String activityName){
        List<Activity> activityList = activityService.selectActivityForDetailByName(activityName);
        return activityList;
    }

    @RequestMapping("/workbench/transaction/getPossibilityByStage.do")
    @ResponseBody
    public  Object getPossibilityByStage(String stageValue){
        //解析properties配置文件，根据阶段获取可能性
        ResourceBundle bundle=ResourceBundle.getBundle("possibility");
        String possibility=bundle.getString(stageValue);
        //返回响应信息
        return possibility;
    }

    @RequestMapping("/workbench/transaction/queryCustomerNameByName.do")
    @ResponseBody
    public Object queryCustomerNameByName(String customerName){
        List<String> stringList = customerService.selectCustomerByName(customerName);
        return stringList;
    }

    @RequestMapping("/workbench/transaction/saveCreateTran.do")
    public @ResponseBody Object saveCreateTran(@RequestParam Map<String,Object> map, HttpSession session){
        //封装参数
        map.put(Contants.SESSION_USER, SessionUserUtils.getSessionUser(session));

        ReturnObject returnObject=new ReturnObject();
        try {
            //调用service层方法，保存创建的交易
            tranService.saveCreateTran(map);

            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试....");
        }
        return returnObject;
    }



}
