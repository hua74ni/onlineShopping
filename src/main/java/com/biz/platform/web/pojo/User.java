package com.biz.platform.web.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by huangdonghua on 2017/12/13.
 */
@Table(name = "biz_platform_user")
public class User {

    //用户编码
    @Id
    @GeneratedValue(generator="UUID")
    @Column(name = "user_id")
    private String userId;

    //用户账号
    @Column(name = "user_code")
    private String userCode;

    //用户密码
    @Column(name = "user_password")
    private String userPassword;

    //用户密码
    @Column(name = "user_name")
    private String userName;

    //用户类型
    @Column(name = "user_type")
    private String userType;

    //用户号码
    @Column(name = "user_phone")
    private String userPhone;

    //用户邮箱
    @Column(name = "user_email")
    private String userEmail;

    //用户地址
    @Column(name = "user_addr")
    private String userAddr;

    //创建时间
    @Column(name = "create_time")
    private Date createTime;

    //修改时间
    @Column(name = "last_modify_time")
    private long lastModifyTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserAddr() {
        return userAddr;
    }

    public void setUserAddr(String userAddr) {
        this.userAddr = userAddr;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(long lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userCode='" + userCode + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userName='" + userName + '\'' +
                ", userType='" + userType + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userMail='" + userEmail + '\'' +
                ", userAddr='" + userAddr + '\'' +
                ", createTime='" + createTime + '\'' +
                ", lastModifyTime=" + lastModifyTime +
                '}';
    }
}
