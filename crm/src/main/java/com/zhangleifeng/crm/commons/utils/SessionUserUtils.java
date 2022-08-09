package com.zhangleifeng.crm.commons.utils;

import com.zhangleifeng.crm.commons.contants.Contants;
import com.zhangleifeng.crm.settings.domain.User;

import javax.servlet.http.HttpSession;

/**
 * @program: crm-project
 * @description:
 * @author: Zhang_Leifeng
 * @create: 2022-08-08 10:04
 **/
public class SessionUserUtils {
    public static String getSessionUserId(HttpSession session){
        User user = (User) session.getAttribute(Contants.SESSION_USER);
        String Id = user.getId();
        return Id;
    }
}
