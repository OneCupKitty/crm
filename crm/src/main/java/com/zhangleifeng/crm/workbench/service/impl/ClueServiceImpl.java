package com.zhangleifeng.crm.workbench.service.impl;

import com.zhangleifeng.crm.commons.contants.Contants;
import com.zhangleifeng.crm.commons.utils.DateUtils;
import com.zhangleifeng.crm.commons.utils.UUIDUtils;
import com.zhangleifeng.crm.settings.domain.User;
import com.zhangleifeng.crm.workbench.domain.*;
import com.zhangleifeng.crm.workbench.mapper.*;
import com.zhangleifeng.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    ContactsMapper contactsMapper;

    @Autowired
    ClueRemarkMapper clueRemarkMapper;

    @Autowired
    CustomerRemarkMapper customerRemarkMapper;

    @Autowired
    ContactsRemarkMapper contactsRemarkMapper;

    @Autowired
    private ClueActivityRelationMapper clueActivityRelationMapper;

    @Autowired
    private ContactsActivityRelationMapper contactsActivityRelationMapper;

    @Autowired
    TranMapper tranMapper;

    @Autowired
    TranRemarkMapper tranRemarkMapper;

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
        String isCreateTran=(String) map.get("isCreateTran");
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
        //把该线索中有关个人的信息转换到联系人表中
        Contacts co=new Contacts();
        co.setAddress(clue.getAddress());
        co.setAppellation(clue.getAppellation());
        co.setContactSummary(clue.getContactSummary());
        co.setCreateBy(user.getId());
        co.setCreateTime(DateUtils.formatDateTime(new Date()));
        co.setCustomerId(c.getId());
        co.setDescription(clue.getDescription());
        co.setEmail(clue.getEmail());
        co.setFullname(clue.getFullname());
        co.setId(UUIDUtils.getUUID());
        co.setJob(clue.getJob());
        co.setMphone(clue.getMphone());
        co.setNextContactTime(clue.getNextContactTime());
        co.setOwner(user.getId());
        co.setSource(clue.getSource());
        contactsMapper.insertContact(co);
        //根据clueId查询该线索下所有的备注
        List<ClueRemark> crList=clueRemarkMapper.selectClueRemarkByClueId(clueId);

        //如果该线索下有备注，把该线索下所有的备注转换到客户备注表中一份,把该线索下所有的备注转换到联系人备注表中一份
        if(crList!=null&&crList.size()>0){
            //遍历crList，封装客户备注
            CustomerRemark cur=null;
            ContactsRemark cor=null;
            List<CustomerRemark> curList=new ArrayList<>();
            List<ContactsRemark> corList=new ArrayList<>();
            for(ClueRemark cr:crList){
                cur=new CustomerRemark();
                cur.setCreateBy(cr.getCreateBy());
                cur.setCreateTime(cr.getCreateTime());
                cur.setCustomerId(c.getId());
                cur.setEditBy(cr.getEditBy());
                cur.setEditFlag(cr.getEditFlag());
                cur.setEditTime(cr.getEditTime());
                cur.setId(UUIDUtils.getUUID());
                cur.setNoteContent(cr.getNoteContent());
                curList.add(cur);

                cor=new ContactsRemark();
                cor.setContactsId(co.getId());
                cor.setCreateBy(cr.getCreateBy());
                cor.setCreateTime(cr.getCreateTime());
                cor.setEditBy(cr.getEditBy());
                cor.setEditFlag(cr.getEditFlag());
                cor.setEditTime(cr.getEditTime());
                cor.setId(UUIDUtils.getUUID());
                cor.setNoteContent(cr.getNoteContent());
                corList.add(cor);
            }
            customerRemarkMapper.insertCustomerRemarkByList(curList);
            contactsRemarkMapper.insertContactsRemarkByList(corList);
        }
        //根据clueId查询该线索和市场活动的关联关系
        List<ClueActivityRelation> carList=clueActivityRelationMapper.selectClueActivityRelationByClueId(clueId);

        //把该线索和市场活动的关联关系转换到联系人和市场活动的关联关系表中
        if(carList!=null&&carList.size()>0){
            ContactsActivityRelation coar=null;
            List<ContactsActivityRelation> coarList=new ArrayList<>();
            for(ClueActivityRelation car:carList){
                coar=new ContactsActivityRelation();
                coar.setActivityId(car.getActivityId());
                coar.setContactsId(co.getId());
                coar.setId(UUIDUtils.getUUID());
                coarList.add(coar);
            }
            contactsActivityRelationMapper.insertContactsActivityRelationByList(coarList);
        }

        //如果需要创建交易，则往交易表中添加一条记录,还需要把该线索下的备注转换到交易备注表中一份
        if("true".equals(isCreateTran)){
            Tran tran=new Tran();
            tran.setActivityId((String) map.get("activityId"));
            tran.setContactsId(co.getId());
            tran.setCreateBy(user.getId());
            tran.setCreateTime(DateUtils.formatDateTime(new Date()));
            tran.setCustomerId(c.getId());
            tran.setExpectedDate((String) map.get("expectedDate"));
            tran.setId(UUIDUtils.getUUID());
            tran.setMoney((String) map.get("money"));
            tran.setName((String) map.get("name"));
            tran.setOwner(user.getId());
            tran.setStage((String) map.get("stage"));
            tranMapper.insertTran(tran);

            if(crList!=null&&crList.size()>0){
                TranRemark tr=null;
                List<TranRemark> trList=new ArrayList<>();
                for(ClueRemark cr:crList){
                    tr=new TranRemark();
                    tr.setCreateBy(cr.getCreateBy());
                    tr.setCreateTime(cr.getCreateTime());
                    tr.setEditBy(cr.getEditBy());
                    tr.setEditFlag(cr.getEditFlag());
                    tr.setEditTime(cr.getEditTime());
                    tr.setId(UUIDUtils.getUUID());
                    tr.setNoteContent(cr.getNoteContent());
                    tr.setTranId(tran.getId());
                    trList.add(tr);
                }

                tranRemarkMapper.insertTranRemarkByList(trList);
            }
        }
        //删除该线索下所有的备注
        clueRemarkMapper.deleteClueRemarkByClueId(clueId);

        //删除该线索和市场活动的关联关系
        clueActivityRelationMapper.deleteClueActivityRelationByClueId(clueId);
        
        //删除线索
        clueMapper.deleteClueById(clueId);

    }
}
