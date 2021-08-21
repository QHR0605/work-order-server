package com.server.workordersystem.dto;

import java.util.Date;

public class OrderCircleMeg {

    //创建人
    private Integer creatorId;
    //创建人姓名
    private String creatorName;
    //创建工单时间
    private Date createTime;
    //工单完成时间
    private Date completionTime;
    //工单被分配时间
    private Date distributeTime;
    //处理人ID
    private Integer handlerId;
    //处理人姓名
    private String handlerName;
    //处理结果时间
    private Date handleTime;
    //工单状态
    private Integer state;

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Date completionTime) {
        this.completionTime = completionTime;
    }

    public Date getDistributeTime() {
        return distributeTime;
    }

    public void setDistributeTime(Date distributeTime) {
        this.distributeTime = distributeTime;
    }

    public Integer getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(Integer handler) {
        this.handlerId = handlerId;
    }

    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

}
