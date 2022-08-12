package com.zhangleifeng.crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: crm-project
 * @description: 联系人
 * @author: Zhang_Leifeng
 * @create: 2022-08-11 15:57
 **/
@Controller
public class ContactsController {

    @RequestMapping("/workbench/contacts/index.do")
    public String index(){


        return "workbench/contacts/index";
    }
}
