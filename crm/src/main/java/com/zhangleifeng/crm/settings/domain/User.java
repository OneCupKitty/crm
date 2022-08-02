package com.zhangleifeng.crm.settings.domain;

public class User {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_user.id
     *
     * @mbggenerated Sat Jul 30 11:52:28 CST 2022
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_user.login_act
     *
     * @mbggenerated Sat Jul 30 11:52:28 CST 2022
     */
    private String loginAct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_user.name
     *
     * @mbggenerated Sat Jul 30 11:52:28 CST 2022
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_user.login_pwd
     *
     * @mbggenerated Sat Jul 30 11:52:28 CST 2022
     */
    private String loginPwd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_user.email
     *
     * @mbggenerated Sat Jul 30 11:52:28 CST 2022
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_user.expire_time
     *
     * @mbggenerated Sat Jul 30 11:52:28 CST 2022
     */
    private String expireTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_user.lock_state
     *
     * @mbggenerated Sat Jul 30 11:52:28 CST 2022
     */
    private String lockState;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_user.deptno
     *
     * @mbggenerated Sat Jul 30 11:52:28 CST 2022
     */
    private String deptno;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_user.allow_ips
     *
     * @mbggenerated Sat Jul 30 11:52:28 CST 2022
     */
    private String allowIps;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_user.createTime
     *
     * @mbggenerated Sat Jul 30 11:52:28 CST 2022
     */
    private String createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_user.create_by
     *
     * @mbggenerated Sat Jul 30 11:52:28 CST 2022
     */
    private String createBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_user.edit_time
     *
     * @mbggenerated Sat Jul 30 11:52:28 CST 2022
     */
    private String editTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_user.edit_by
     *
     * @mbggenerated Sat Jul 30 11:52:28 CST 2022
     */
    private String editBy;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_user.id
     *
     * @return the value of tbl_user.id
     *
     * @mbggenerated Sat Jul 30 11:52:28 CST 2022
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_user.id
     *
     * @param id the value for tbl_user.id
     *
     * @mbggenerated Sat Jul 30 11:52:28 CST 2022
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_user.login_act
     *
     * @return the value of tbl_user.login_act
     *
     * @mbggenerated Sat Jul 30 11:52:28 CST 2022
     */
    public String getLoginAct() {
        return loginAct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_user.login_act
     *
     * @param loginAct the value for tbl_user.login_act
     *
     * @mbggenerated Sat Jul 30 11:52:28 CST 2022
     */
    public void setLoginAct(String loginAct) {
        this.loginAct = loginAct == null ? null : loginAct.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_user.name
     *
     * @return the value of tbl_user.name
     *
     * @mbggenerated Sat Jul 30 11:52:28 CST 2022
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_user.name
     *
     * @param name the value for tbl_user.name
     *
     * @mbggenerated Sat Jul 30 11:52:28 CST 2022
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_user.login_pwd
     *
     * @return the value of tbl_user.login_pwd
     *
     * @mbggenerated Sat Jul 30 11:52:28 CST 2022
     */
    public String getLoginPwd() {
        return loginPwd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_user.login_pwd
     *
     * @param loginPwd the value for tbl_user.login_pwd
     *
     * @mbggenerated Sat Jul 30 11:52:28 CST 2022
     */
    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd == null ? null : loginPwd.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_user.email
     *
     * @return the value of tbl_user.email
     *
     * @mbggenerated Sat Jul 30 11:52:28 CST 2022
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_user.email
     *
     * @param email the value for tbl_user.email
     *
     * @mbggenerated Sat Jul 30 11:52:28 CST 2022
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_user.expire_time
     *
     * @return the value of tbl_user.expire_time
     *
     * @mbggenerated Sat Jul 30 11:52:28 CST 2022
     */
    public String getExpireTime() {
        return expireTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_user.expire_time
     *
     * @param expireTime the value for tbl_user.expire_time
     *
     * @mbggenerated Sat Jul 30 11:52:28 CST 2022
     */
    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime == null ? null : expireTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_user.lock_state
     *
     * @return the value of tbl_user.lock_state
     *
     * @mbggenerated Sat Jul 30 11:52:28 CST 2022
     */
    public String getLockState() {
        return lockState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_user.lock_state
     *
     * @param lockState the value for tbl_user.lock_state
     *
     * @mbggenerated Sat Jul 30 11:52:28 CST 2022
     */
    public void setLockState(String lockState) {
        this.lockState = lockState == null ? null : lockState.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_user.deptno
     *
     * @return the value of tbl_user.deptno
     *
     * @mbggenerated Sat Jul 30 11:52:28 CST 2022
     */
    public String getDeptno() {
        return deptno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_user.deptno
     *
     * @param deptno the value for tbl_user.deptno
     *
     * @mbggenerated Sat Jul 30 11:52:28 CST 2022
     */
    public void setDeptno(String deptno) {
        this.deptno = deptno == null ? null : deptno.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_user.allow_ips
     *
     * @return the value of tbl_user.allow_ips
     *
     * @mbggenerated Sat Jul 30 11:52:28 CST 2022
     */
    public String getAllowIps() {
        return allowIps;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_user.allow_ips
     *
     * @param allowIps the value for tbl_user.allow_ips
     *
     * @mbggenerated Sat Jul 30 11:52:28 CST 2022
     */
    public void setAllowIps(String allowIps) {
        this.allowIps = allowIps == null ? null : allowIps.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_user.createTime
     *
     * @return the value of tbl_user.createTime
     *
     * @mbggenerated Sat Jul 30 11:52:28 CST 2022
     */
    public String getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_user.createTime
     *
     * @param createtime the value for tbl_user.createTime
     *
     * @mbggenerated Sat Jul 30 11:52:28 CST 2022
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_user.create_by
     *
     * @return the value of tbl_user.create_by
     *
     * @mbggenerated Sat Jul 30 11:52:28 CST 2022
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_user.create_by
     *
     * @param createBy the value for tbl_user.create_by
     *
     * @mbggenerated Sat Jul 30 11:52:28 CST 2022
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_user.edit_time
     *
     * @return the value of tbl_user.edit_time
     *
     * @mbggenerated Sat Jul 30 11:52:28 CST 2022
     */
    public String getEditTime() {
        return editTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_user.edit_time
     *
     * @param editTime the value for tbl_user.edit_time
     *
     * @mbggenerated Sat Jul 30 11:52:28 CST 2022
     */
    public void setEditTime(String editTime) {
        this.editTime = editTime == null ? null : editTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_user.edit_by
     *
     * @return the value of tbl_user.edit_by
     *
     * @mbggenerated Sat Jul 30 11:52:28 CST 2022
     */
    public String getEditBy() {
        return editBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_user.edit_by
     *
     * @param editBy the value for tbl_user.edit_by
     *
     * @mbggenerated Sat Jul 30 11:52:28 CST 2022
     */
    public void setEditBy(String editBy) {
        this.editBy = editBy == null ? null : editBy.trim();
    }
}