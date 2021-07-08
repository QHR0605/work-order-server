package com.server.workordersystem.service;

import com.server.workordersystem.dto.SolutionMessage;
import com.server.workordersystem.dto.UpdatedOrderMessage;
import com.server.workordersystem.dto.WorkOrderMessage;
import com.server.workordersystem.dto.WorkOrderWithFiles;

import java.util.List;

public interface MaintainerService {

    Integer insertNewWorkerOrder(WorkOrderMessage message);

    List<WorkOrderWithFiles> getOrders(Integer uid);

    List<WorkOrderWithFiles> getDrafts(Integer uid);

    Integer insertNewCommitLog(WorkOrderMessage message);

    Integer insertNewDraft(WorkOrderMessage message);

    Integer updateOrderState(Integer orderId, Integer state);

    Integer insertNewSolution(SolutionMessage message);

    List<WorkOrderWithFiles> getHandledOrders(Integer uid);

    List<WorkOrderWithFiles> getNotHandledOrders(Integer uid);

    Integer updateOrder(UpdatedOrderMessage message);
}
