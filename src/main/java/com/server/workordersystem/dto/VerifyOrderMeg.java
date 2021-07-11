package com.server.workordersystem.dto;

import io.swagger.models.auth.In;

public class VerifyOrderMeg {

    private Integer OrderId;
    //审核通过state为true，不通过为false;
    private Boolean state;

    public Integer getOrderId() {
        return OrderId;
    }

    public void setOrderId(Integer orderId) {
        OrderId = orderId;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }


}
