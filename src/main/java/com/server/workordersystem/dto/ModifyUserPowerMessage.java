package com.server.workordersystem.dto;

import com.sun.istack.internal.NotNull;
import io.swagger.models.auth.In;

public class ModifyUserPowerMessage {

    @NotNull
    private Integer uid;
    @NotNull
    private Integer group;
    @NotNull
    private Integer accountType;

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
