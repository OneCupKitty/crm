package com.zhangleifeng.crm.workbench.service;

import com.zhangleifeng.crm.workbench.domain.Contacts;

import java.util.List;

public interface ContactsService {
    List<Contacts> selectContactsForDetailByName(String name);
}
