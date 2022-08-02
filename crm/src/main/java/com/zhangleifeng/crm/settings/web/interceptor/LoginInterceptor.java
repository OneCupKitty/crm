package com.zhangleifeng.crm.settings.web.interceptor;

import com.zhangleifeng.crm.commons.contants.Contants;
import com.zhangleifeng.crm.settings.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: crm-project
 * @description:
 * @author: Zhang_Leifeng
 * @create: 2022-07-31 16:46
 **/
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断用户是否登录
        User user = (User) request.getSession().getAttribute(Contants.SESSION_USER);
        if (user == null) {
            //没有登录
            //重定向到登录页
            response.sendRedirect(request.getContextPath());//  /crm手动跳转,需要加项目名
            return false;
        }
        return true;
    }
}
