package com.server.workordersystem.dto;

public class UserTypeGroup {

    private Integer uid;
    private Integer group;
    private Integer accountType;

    public UserTypeGroup(){

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

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

}
