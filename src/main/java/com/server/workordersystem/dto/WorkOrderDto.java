package com.server.workordersystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;

public class WorkOrderDto {
    private Integer orderId;
    private String orderName;
    private Integer creator;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private String content;
    private Integer handleGroup;
    private Integer type;
    private Integer cid;
    private Integer row;

    public WorkOrderDto(WorkOrderMessage message) {
        this.orderId = message.getOrderId();
        this.orderName = message.getOrderName();
        this.creator = message.getCreator();
        this.createTime = message.getCreateTime();
        this.content = message.getContent();
        this.handleGroup = message.getHandleGroup();
        this.type = message.getType();
    }

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

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    @Override
    public String toString() {
        return "WorkOrderDto{" +
                "orderId=" + orderId +
                ", orderName='" + orderName + '\'' +
                ", creator=" + creator +
                ", createTime=" + createTime +
                ", content='" + content + '\'' +
                ", handleGroup=" + handleGroup +
                ", type=" + type +
                ", cid=" + cid +
                ", row=" + row +
                '}';
    }
}
