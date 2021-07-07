package com.server.workordersystem.entity;

import java.util.Date;

public class SolutionLog {

    private Integer sid;
    private Integer uid;
    private String advice;
    private String image;
    private Date handleTime;
    private Integer cid;

    public SolutionLog() {

    }

    public SolutionLog sid(Integer sid) {
        this.setSid(sid);
        return this;
    }

    public SolutionLog uid(Integer uid) {
        this.setUid(uid);
        return this;
    }

    public SolutionLog advice(String advice) {
        this.setAdvice(advice);
        return this;
    }

    public SolutionLog image(String image) {
        this.setImage(image);
        return this;
    }

    public SolutionLog handleTime(Date handleTime) {
        this.setHandleTime(handleTime);
        return this;
    }

    public SolutionLog cid(Integer cid) {
        this.setCid(cid);
        return this;
    }


    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }
}
