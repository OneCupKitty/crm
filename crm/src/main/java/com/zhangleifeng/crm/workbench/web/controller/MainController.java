package com.zhangleifeng.crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: crm-project
 * @description:
 * @author: Zhang_Leifeng
 * @create: 2022-07-31 17:49
 **/
@Controller
public class MainController {

    @RequestMapping("/workbench/main/index.do")
    public String index(){
        //加载工作区,资源
        return "workbench/main/index";
    }
}
