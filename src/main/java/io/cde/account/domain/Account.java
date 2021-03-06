package io.cde.account.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 用户实体.
 *
 * @author lcl
 */
@Document(collection = "account")
public class Account extends BaseEntity {

    /**
     * 用户名.
     */

    private String name;

    /**
     * 用户密码.
     */
    private String password;

    /**
     * 由于修改密码的保留字段，不存入数据库.
     */
    @Transient
    @JsonIgnore
    private String password1;

    /**
     * 由于修改密码的保留字段，不存入数据库.
     */
    @Transient
    @JsonIgnore
    private String password2;

    /**
     * 用户头像地址.
     */
    private String avatar;

    /**
     * 所在公司.
     */
    private String company;

    /**
     * 公司地址.
     */
    private String address;

    /**
     * 行业.
     */
    private String business;

    /**
     * 职位.
     */
    private String position;

    /**
     * 个人简介.
     */
    private String personal;

    /**
     * 注册时间.
     */
    private Date timestamp;

    /**
     * 用户真实姓名.
     */
    private String realName;

    /**
     * 校验码，保留字段，不存到数据库中.
     */
    @Transient
    @JsonIgnore
    private String verifyCode;

    /**
     * 用户默认邮箱.
     */
    private String email;

    /**
     * 用户默认电话号码.
     */
    private String mobile;

    /**
     * 默认邮箱是否公开，1：公开.
     */
    private boolean isPublicEmail;

    /**
     * 默认电话号码是否公开，1：公开.
     */
    private boolean isPublicMobile;

    /**
     * 用户邮箱信息.
     */
    @JsonIgnore
    private List<Email> emails = new ArrayList<>();

    /**
     * 用户电话信息.
     */
    @JsonIgnore
    private List<Mobile> mobiles = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(final String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(final String password2) {
        this.password2 = password2;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(final String avatar) {
        this.avatar = avatar;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(final String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(final String business) {
        this.business = business;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(final String position) {
        this.position = position;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(final String personal) {
        this.personal = personal;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(final String realName) {
        this.realName = realName;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(final String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(final String mobile) {
        this.mobile = mobile;
    }

    public boolean isPublicEmail() {
        return isPublicEmail;
    }

    public void setPublicEmail(final boolean isPublicEmail) {
        this.isPublicEmail = isPublicEmail;
    }

    public boolean isPublicMobile() {
        return isPublicMobile;
    }

    public void setPublicMobile(final boolean isPublicMobile) {
        this.isPublicMobile = isPublicMobile;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(final List<Email> emails) {
        this.emails = emails;
    }

    public List<Mobile> getMobiles() {
        return mobiles;
    }

    public void setMobiles(final List<Mobile> mobiles) {
        this.mobiles = mobiles;
    }
}