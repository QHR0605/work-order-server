package com.server.workordersystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class VerifyOrderMeg {

    private Integer orderId;
    private String verification;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date verifiedTime;
    //审核通过state为true，不通过为false;
    private Boolean verifiedResult;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }

    public Date getVerifiedTime() {
        return verifiedTime;
    }

    public void setVerifiedTime(Date verifiedTime) {
        this.verifiedTime = verifiedTime;
    }

    public Boolean getVerifiedResult() {
        return verifiedResult;
    }

    public void setVerifiedResult(Boolean verifiedResult) {
        this.verifiedResult = verifiedResult;
    }


}
