package com.zhangleifeng.crm.workbench.web.controller;

import com.zhangleifeng.crm.workbench.domain.Customer;
import com.zhangleifeng.crm.workbench.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: crm-project
 * @description: 客户
 * @author: Zhang_Leifeng
 * @create: 2022-08-11 15:59
 **/
@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping("/workbench/customer/index.do")
    public String index(){


        return "workbench/customer/index";
    }

    @RequestMapping("/workbench/clue/selectCustomerByConditionForPage.do")
    @ResponseBody
    public Object selectCustomerByConditionForPage(String customerName,String customerOwner,String phone,
                                                   String website,int pageNo,int pageSize){
        Map<String,Object> map = new HashMap<>();
        map.put("customerName",customerName);
        map.put("customerOwner",customerOwner);
        map.put("phone",phone);
        map.put("website",website);
        map.put("beginNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        List<Customer> customerList = customerService.selectCustomerByConditionForPage(map);
        int totalRows = customerService.selectCountCustomerByConditionForPage(map);
        //封装结果
        Map<String,Object> retMap = new HashMap<String,Object>();
        retMap.put("customerList",customerList);
        retMap.put("totalRows",totalRows);
        return retMap;
    }

}
