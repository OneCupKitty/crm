package com.zhangleifeng.crm.commons.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: crm-project
 * @description:
 * @author: Zhang_Leifeng
 * @create: 2022-07-31 15:30
 **/
public class CookieUtils {
    public static Cookie loginAct1;
    public static Cookie loginPwd1;

    public static void deleteCookie(HttpServletResponse response){
        //清除cookie
        loginAct1 = new Cookie("loginAct", "1");
        loginPwd1 = new Cookie("loginPwd", "2");
        loginAct1.setMaxAge(0);
        loginPwd1.setMaxAge(0);
        response.addCookie(loginAct1);
        response.addCookie(loginPwd1);
    }
    public static void addCookie(HttpServletResponse response,String loginAct,String loginPwd){
        loginAct1 = new Cookie("loginAct",loginAct);
        loginPwd1 = new Cookie("loginPwd",loginPwd);
        loginAct1.setMaxAge(10*24*60*60);
        loginPwd1.setMaxAge(10*24*60*60);
        response.addCookie(loginAct1);
        response.addCookie(loginPwd1);
    }
}
