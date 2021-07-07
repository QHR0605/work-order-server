package com.server.workordersystem.service;

import com.server.workordersystem.dto.WorkOrderMessage;
import com.server.workordersystem.dto.WorkOrderWithFiles;
import com.server.workordersystem.entity.WorkOrder;

import java.util.List;

public interface MaintainerService {

    Integer insertNewWorkerOrder(WorkOrderMessage message);

    Integer completeOrder(Integer uid, Boolean completed);

    List<WorkOrderWithFiles> getOrders(Integer uid);

    List<WorkOrderWithFiles> getDrafts(Integer uid);

    Integer insertNewCommitLog(WorkOrderMessage message);

    Integer insertNewDraft(WorkOrderMessage message);

    Integer updateOrderState(Integer orderId, Integer state);
}
