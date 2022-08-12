package com.zhangleifeng.crm.workbench.mapper;

import com.zhangleifeng.crm.workbench.domain.Contacts;

import java.util.List;

public interface ContactsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts
     *
     * @mbggenerated Wed Aug 10 17:11:10 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts
     *
     * @mbggenerated Wed Aug 10 17:11:10 CST 2022
     */
    int insert(Contacts record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts
     *
     * @mbggenerated Wed Aug 10 17:11:10 CST 2022
     */
    int insertSelective(Contacts record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts
     *
     * @mbggenerated Wed Aug 10 17:11:10 CST 2022
     */
    Contacts selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts
     *
     * @mbggenerated Wed Aug 10 17:11:10 CST 2022
     */
    int updateByPrimaryKeySelective(Contacts record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts
     *
     * @mbggenerated Wed Aug 10 17:11:10 CST 2022
     */
    int updateByPrimaryKey(Contacts record);

    /**
     * 保存创建的联系人
     * @param contacts
     * @return
     */
    int insertContact(Contacts contacts);

    /**
     * 根据联系人名称,模糊查询联系人
     * @param name
     * @return
     */
    List<Contacts> selectContactsForDetailByName(String name);
}