package com.server.workordersystem.entity;

import java.sql.Date;

public class WorkOrder {

    private Integer orderId;
    private String orderName;
    private String creator;
    private String verifier;
    private String handler;
    private String handleGroup;
    private Date createTime;
    private String content;
    private Date completionTime;
    private Integer state;
    private Boolean deleted;
    private Integer type;
    private String verification;
    private Date verifiedTime;
    private Integer handle;
    private Date handleTime;
    private String handlerContent;
    private Integer cid;
    private Integer sid;

    public WorkOrder orderId(Integer orderId){
        this.setOrderId(orderId);
        return this;
    }

    public WorkOrder orderName(String orderName){
        this.setOrderName(orderName);
        return this;
    }

    public WorkOrder creator(String creator){
        this.setCreator(creator);
        return this;
    }

    public WorkOrder createTime(Date createTime){
        this.setCreateTime(createTime);
        return this;
    }

    public WorkOrder content(String content){
        this.setContent(content);
        return this;
    }

    public WorkOrder completionTime(Date completionTime){
        this.setCompletionTime(completionTime);
        return this;
    }

    public WorkOrder state(Integer createTime){
        this.setState(state);
        return this;
    }

    public WorkOrder deleted(Boolean deleted){
        this.setDeleted(deleted);
        return this;
    }

    public WorkOrder handleGroup(String handleGroup){
        this.setHandleGroup(handleGroup);
        return this;
    }

    public WorkOrder type(Integer type){
        this.setType(type);
        return this;
    }

    public WorkOrder verifier(String verifier){
        this.setVerifier(verifier);
        return this;
    }

    public WorkOrder verification(String verification){
        this.setVerification(verification);
        return this;
    }

    public WorkOrder verifiedTime(Date verifiedTime){
        this.setVerifiedTime(verifiedTime);
        return this;
    }

    public WorkOrder handle(Integer handle){
        this.setHandle(handle);
        return this;
    }

    public WorkOrder handleTime(Date handleTime){
        this.setHandleTime(handleTime);
        return this;
    }

    public WorkOrder handlerContent(String handlerContent){
        this.setHandlerContent(handlerContent);
        return this;
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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getVerifier() {
        return verifier;
    }

    public void setVerifier(String verifier) {
        this.verifier = verifier;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getHandleGroup() {
        return handleGroup;
    }

    public void setHandleGroup(String handleGroup) {
        this.handleGroup = handleGroup;
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

    public Date getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Date completionTime) {
        this.completionTime = completionTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Integer getHandle() {
        return handle;
    }

    public void setHandle(Integer handle) {
        this.handle = handle;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public String getHandlerContent() {
        return handlerContent;
    }

    public void setHandlerContent(String handlerContent) {
        this.handlerContent = handlerContent;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }
}
