package com.zhangleifeng.crm.workbench.service.impl;

import com.zhangleifeng.crm.workbench.domain.Contacts;
import com.zhangleifeng.crm.workbench.mapper.ContactsMapper;
import com.zhangleifeng.crm.workbench.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: crm-project
 * @description:
 * @author: Zhang_Leifeng
 * @create: 2022-08-12 10:03
 **/
@Service
public class ContactsServiceImpl implements ContactsService {

    @Autowired
    ContactsMapper contactsMapper;

    @Override
    public List<Contacts> selectContactsForDetailByName(String name) {
        return contactsMapper.selectContactsForDetailByName(name);
    }
}
