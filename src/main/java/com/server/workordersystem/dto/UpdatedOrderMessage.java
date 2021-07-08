package com.server.workordersystem.dto;

public class UpdatedOrderMessage {

    private Integer orderId;
    private String orderName;
    private Integer handleGroup;
    private Integer type;
    private String content;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Integer getHandleGroup() {
        return handleGroup;
    }

    public void setHandleGroup(Integer handleGroup) {
        this.handleGroup = handleGroup;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
