package com.server.workordersystem.entity;

import java.util.Date;

public class SolutionLog {

    private Integer sid;
    private Integer uid;
    private String advice;
    private Date handleTime;
    private Boolean handleResult;
    private Integer cid;
    private Date distributeTime;

    public SolutionLog(Integer sid, Integer uid, String advice, Date handleTime, Boolean handleResult, Integer cid, Date distributeTime) {
        this.sid = sid;
        this.uid = uid;
        this.advice = advice;
        this.handleTime = handleTime;
        this.handleResult = handleResult;
        this.cid = cid;
        this.distributeTime = distributeTime;
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

    public SolutionLog handleTime(Date handleTime) {
        this.setHandleTime(handleTime);
        return this;
    }

    public SolutionLog cid(Integer cid) {
        this.setCid(cid);
        return this;
    }

    public SolutionLog distributeTime(Date distributeTime){
        this.setDistributeTime(distributeTime);
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

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Date getDistributeTime() {
        return distributeTime;
    }

    public void setDistributeTime(Date distributeTime) {
        this.distributeTime = distributeTime;
    }

}
