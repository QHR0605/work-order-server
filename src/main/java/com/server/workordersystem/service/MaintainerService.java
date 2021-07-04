package com.server.workordersystem.service;

import com.server.workordersystem.dto.WorkOrderMessage;
import com.server.workordersystem.entity.WorkOrder;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MaintainerService {

    Integer insertNewWorkerOrder(WorkOrderMessage message);

    Integer completeOrder(Integer uid , Boolean completed);

    List<WorkOrder> getOrders(Integer uid);
}
