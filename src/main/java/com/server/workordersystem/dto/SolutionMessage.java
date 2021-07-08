package com.server.workordersystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;
import java.util.List;

public class SolutionMessage {

    private Integer sid;
    private Integer orderId;
    private Integer cid;
    private Integer uid;
    private String advice;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date handleTime;
    private Boolean handleResult;
    private List<String> images;
    private Integer row;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public Boolean getHandleResult() {
        return handleResult;
    }

    public void setHandleResult(Boolean handleResult) {
        this.handleResult = handleResult;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    @Override
    public String toString() {
        return "SolutionMessage{" +
                "sid=" + sid +
                ", orderId=" + orderId +
                ", cid=" + cid +
                ", uid=" + uid +
                ", advice='" + advice + '\'' +
                ", handleTime=" + handleTime +
                ", handleResult=" + handleResult +
                ", images=" + images +
                ", row=" + row +
                '}';
    }
}
