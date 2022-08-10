package com.zhangleifeng.crm.workbench.mapper;

import com.zhangleifeng.crm.workbench.domain.Customer;

public interface CustomerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_customer
     *
     * @mbggenerated Wed Aug 10 15:34:03 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_customer
     *
     * @mbggenerated Wed Aug 10 15:34:03 CST 2022
     */
    int insert(Customer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_customer
     *
     * @mbggenerated Wed Aug 10 15:34:03 CST 2022
     */
    int insertSelective(Customer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_customer
     *
     * @mbggenerated Wed Aug 10 15:34:03 CST 2022
     */
    Customer selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_customer
     *
     * @mbggenerated Wed Aug 10 15:34:03 CST 2022
     */
    int updateByPrimaryKeySelective(Customer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_customer
     *
     * @mbggenerated Wed Aug 10 15:34:03 CST 2022
     */
    int updateByPrimaryKey(Customer record);

    /**
     * 保存创建的客户
     * @param customer
     * @return
     */
    int insertCustomer(Customer customer);
}