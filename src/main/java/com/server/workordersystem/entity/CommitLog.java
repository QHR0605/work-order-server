package com.server.workordersystem.entity;

import io.swagger.models.auth.In;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author 全鸿润
 */
public class CommitLog {

    private Integer cid;
    private Integer orderId;
    private Date commitTime;
    private Integer verifuyer;
    private String verification;
    private Date verifiedTime;
    private Boolean verifiedResult;
    private String content;
    private String image;

    public CommitLog() {

    }

    public CommitLog cid(Integer cid){
        this.setCid(cid);
        return this;
    }

    public CommitLog orderId(Integer orderId){
        this.setOrderId(orderId);
        return this;
    }

    public CommitLog commitTime(Date commitTime){
        this.setCommitTime(commitTime);
        return this;
    }

    public CommitLog verifuyer(Integer verifuyer){
        this.setVerifuyer(verifuyer);
        return this;
    }

    public CommitLog verification(String verification){
        this.setVerification(verification);
        return this;
    }

    public CommitLog verifiedTime(Date verifiedTime){
        this.setVerifiedTime(verifiedTime);
        return this;
    }

    public CommitLog verifiedResult(Boolean verifiedResult){
        this.setVerifiedResult(verifiedResult);
        return this;
    }

    public CommitLog content(String content){
        this.setContent(content);
        return this;
    }

    public CommitLog image(String image){
        this.setImage(image);
        return this;
    }


    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(Date commitTime) {
        this.commitTime = commitTime;
    }

    public Integer getVerifuyer() {
        return verifuyer;
    }

    public void setVerifuyer(Integer verifuyer) {
        this.verifuyer = verifuyer;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
