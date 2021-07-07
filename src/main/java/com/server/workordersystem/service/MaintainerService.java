package com.server.workordersystem.service;

import com.server.workordersystem.dto.WorkOrderMessage;
import com.server.workordersystem.entity.WorkOrder;

import java.util.List;

public interface MaintainerService {

    Integer insertNewWorkerOrder(WorkOrderMessage message);

    Integer completeOrder(Integer uid, Boolean completed);

    List<WorkOrder> getOrders(Integer uid);

    List<WorkOrder> getDrafts(Integer uid);

    Integer insertNewCommitLog(WorkOrderMessage message);

    Integer insertNewDraft(WorkOrderMessage message);
}
