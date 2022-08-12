package com.zhangleifeng.crm.workbench.service;

import com.zhangleifeng.crm.workbench.domain.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerService {

    List<Customer> selectCustomerByConditionForPage(Map<String,Object> map);

    int selectCountCustomerByConditionForPage(Map<String,Object> map);

    List<String> selectCustomerByName(String name);
}
