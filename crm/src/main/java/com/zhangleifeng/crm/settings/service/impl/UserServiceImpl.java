package com.zhangleifeng.crm.settings.service.impl;

import com.zhangleifeng.crm.settings.domain.User;
import com.zhangleifeng.crm.settings.mapper.UserMapper;
import com.zhangleifeng.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @program: crm-project
 * @description:
 * @author: Zhang_Leifeng
 * @create: 2022-07-30 11:21
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectUserByLoginActAndLoginPwd(Map<String, Object> map) {
        return userMapper.selectUserByLoginActAndLoginPwd(map);
    }

    @Override
    public List<User> selectUserList() {
        return userMapper.selectUserList();
    }
}
