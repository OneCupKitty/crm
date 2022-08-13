package com.zhangleifeng.crm.workbench.web.controller;

import com.zhangleifeng.crm.settings.domain.DicValue;
import com.zhangleifeng.crm.settings.service.DicValueService;
import com.zhangleifeng.crm.workbench.domain.Contacts;
import com.zhangleifeng.crm.workbench.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: crm-project
 * @description: 联系人
 * @author: Zhang_Leifeng
 * @create: 2022-08-11 15:57
 **/
@Controller
public class ContactsController {

    @Autowired
    DicValueService dicValueService;

    @Autowired
    ContactsService contactsService;

    @RequestMapping("/workbench/contacts/index.do")
    public String index(HttpServletRequest request){
        List<DicValue> sourceList=dicValueService.selectDicValueByTypeCode("source");
        request.setAttribute("sourceList",sourceList);
        return "workbench/contacts/index";
    }

    @RequestMapping("/workbench/clue/selectContactsByConditionForPage.do")
    @ResponseBody
    public Object selectContactsByConditionForPage(String owner,String fullname,String customerName,String source,
                                                   String nextContactTime,int pageNo,int pageSize){
        //封装参数
        Map<String,Object> map = new HashMap<>();
        map.put("fullname",fullname);
        map.put("owner",owner);
        map.put("customerName",customerName);
        map.put("source",source);
        map.put("nextContactTime",nextContactTime);
        map.put("beginNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        //查询
        List<Contacts> contactsList = contactsService.selectContactsByConditionForPage(map);
        int totalRows = contactsService.selectCountContactsByConditionForPage(map);
        //封装结果
        Map<String,Object> retMap = new HashMap<String,Object>();
        retMap.put("contactsList",contactsList);
        retMap.put("totalRows",totalRows);
        return retMap;
    }
}
