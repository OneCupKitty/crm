package com.zhangleifeng.crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: crm-project
 * @description:
 * @author: Zhang_Leifeng
 * @create: 2022-07-29 17:52
 **/
@Controller
public class IndexController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
