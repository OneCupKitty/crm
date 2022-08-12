package com.zhangleifeng.crm.workbench.service.impl;

import com.zhangleifeng.crm.workbench.domain.Customer;
import com.zhangleifeng.crm.workbench.mapper.CustomerMapper;
import com.zhangleifeng.crm.workbench.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @program: crm-project
 * @description:
 * @author: Zhang_Leifeng
 * @create: 2022-08-11 19:43
 **/
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerMapper customerMapper;

    @Override
    public List<Customer> selectCustomerByConditionForPage(Map<String, Object> map) {
        return customerMapper.selectCustomerByConditionForPage(map);
    }

    @Override
    public int selectCountCustomerByConditionForPage(Map<String, Object> map) {
        return customerMapper.selectCountCustomerByConditionForPage(map);
    }

    @Override
    public List<String> selectCustomerByName(String name) {
        return customerMapper.selectCustomerByName(name);
    }
}
