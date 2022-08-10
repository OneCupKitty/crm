package com.zhangleifeng.crm.workbench.service.impl;

import com.zhangleifeng.crm.commons.contants.Contants;
import com.zhangleifeng.crm.commons.utils.DateUtils;
import com.zhangleifeng.crm.commons.utils.UUIDUtils;
import com.zhangleifeng.crm.settings.domain.User;
import com.zhangleifeng.crm.workbench.domain.Clue;
import com.zhangleifeng.crm.workbench.domain.Customer;
import com.zhangleifeng.crm.workbench.mapper.ClueMapper;
import com.zhangleifeng.crm.workbench.mapper.CustomerMapper;
import com.zhangleifeng.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: crm-project
 * @description:
 * @author: Zhang_Leifeng
 * @create: 2022-08-08 09:54
 **/
@Service
public class ClueServiceImpl implements ClueService {

    @Autowired
    ClueMapper clueMapper;

    @Autowired
    CustomerMapper customerMapper;

    @Override
    public int insertClue(Clue clue) {
        return clueMapper.insertClue(clue);
    }

    @Override
    public List<Clue> selectAllClue() {
        return clueMapper.selectAllClue();
    }

    @Override
    public Clue selectClueForDetailByClueId(String clueId) {
        return clueMapper.selectClueForDetailByClueId(clueId);
    }

    @Override
    public List<Clue> selectClueByConditionForPage(Map<String,Object> map) {
        return clueMapper.selectClueByConditionForPage(map);
    }

    @Override
    public int selectCountClueByCondition(Map<String, Object> map) {
        return clueMapper.selectCountClueByCondition(map);
    }

    @Override
    public void saveClueConvert(Map<String, Object> map) {
        String clueId=(String) map.get("clueId");
        User user=(User) map.get(Contants.SESSION_USER);
        //根据id查询线索的信息
        Clue clue=clueMapper.selectClueByClueId(clueId);
        //把该线索中有关公司的信息转换到客户表中
        Customer c=new Customer();
        c.setAddress(clue.getAddress());
        c.setContactSummary(clue.getContactSummary());
        c.setCreateBy(user.getId());
        c.setCreateTime(DateUtils.formatDateTime(new Date()));
        c.setDescription(clue.getDescription());
        c.setId(UUIDUtils.getUUID());
        c.setName(clue.getCompany());
        c.setNextContactTime(clue.getNextContactTime());
        c.setOwner(user.getId());
        c.setPhone(clue.getPhone());
        c.setWebsite(clue.getWebsite());
        customerMapper.insertCustomer(c);
    }
}
