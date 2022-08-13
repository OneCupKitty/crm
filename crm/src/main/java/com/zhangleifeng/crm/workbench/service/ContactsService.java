package com.zhangleifeng.crm.workbench.service;

import com.zhangleifeng.crm.workbench.domain.Contacts;

import java.util.List;
import java.util.Map;

public interface ContactsService {
    List<Contacts> selectContactsForDetailByName(String name);

    List<Contacts> selectContactsByConditionForPage(Map<String,Object> map);

    int selectCountContactsByConditionForPage(Map<String,Object> map);
}
