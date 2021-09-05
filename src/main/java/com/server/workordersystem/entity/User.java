package com.server.workordersystem.entity;

import java.sql.Timestamp;

/**
 * @author 全鸿润
 */
public class User {

    private Integer uid;
    private Integer group;
    private Integer accountType;
    private String username;
    private String password;
    private String name;
    private Boolean sex;
    private Integer age;
    private String phone;
    private String email;
    private Timestamp latestLoginTime;
    private String latestLoginIp;
    private Boolean logout;
    private String avatarUrl;

    public User() {

    }

    public User uid(Integer uid) {
        this.uid = uid;
        return this;
    }

    public User username(String username) {
        this.setUsername(username);
        return this;
    }

    public User sex(Boolean sex) {
        this.setSex(sex);
        return this;
    }

    public User password(String password) {
        this.setPassword(password);
        return this;
    }

    public User name(String name) {
        this.setName(name);
        return this;
    }

    public User phone(String phone) {
        this.setPhone(phone);
        return this;
    }

    public User accountType(Integer accountType) {
        this.setAccountType(accountType);
        return this;
    }

    public User group(Integer group) {
        this.setGroup(group);
        return this;
    }

    public User latestLoginTime(Timestamp latestLoginTime) {
        this.setLatestLoginTime(latestLoginTime);
        return this;
    }

    public User latestLoginIp(String latestLoginIp) {
        this.setLatestLoginIp(latestLoginIp);
        return this;
    }

    public User isLogout(Boolean logout) {
        this.setLogout(logout);
        return this;
    }

    public User build() {
        return this;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public Boolean getLogout() {
        return logout;
    }

    public void setLogout(Boolean logout) {
        this.logout = logout;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public Timestamp getLatestLoginTime() {
        return latestLoginTime;
    }

    public void setLatestLoginTime(Timestamp latestLoginTime) {
        this.latestLoginTime = latestLoginTime;
    }

    public String getLatestLoginIp() {
        return latestLoginIp;
    }

    public void setLatestLoginIp(String latestLoginIp) {
        this.latestLoginIp = latestLoginIp;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", accountType=" + accountType +
                ", latestLoginTime=" + latestLoginTime +
                ", latestLoginIp='" + latestLoginIp + '\'' +
                ", logout=" + logout +
                '}';
    }
}
