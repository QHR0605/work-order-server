package com.server.workordersystem.service.impl;

import com.server.workordersystem.dto.WorkOrderDto;
import com.server.workordersystem.dto.WorkOrderMessage;
import com.server.workordersystem.entity.WorkOrder;
import com.server.workordersystem.mapper.MaintainerMapper;
import com.server.workordersystem.service.MaintainerService;
import com.server.workordersystem.util.idGenerator.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintainerServiceImpl implements MaintainerService {

    @Autowired
    private MaintainerMapper maintainerMapper;

    @Override
    public Integer insertNewWorkerOrder(WorkOrderMessage message) {

        Integer row = null;
        try {
            message.setOrderId(IdGenerator.getId());
            WorkOrderDto workOrderDto = new WorkOrderDto(message);
            workOrderDto.setCid(IdGenerator.getId());
            maintainerMapper.insertNewWorkOrder(workOrderDto);
            row = workOrderDto.getRow();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer completeOrder(Integer orderId, Boolean completed) {

        Integer row = null;
        try {
            row = maintainerMapper.updateOrderCompletedState(orderId, completed);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public List<WorkOrder> getOrders(Integer uid) {

        List<WorkOrder> orders = null;
        try {
            orders = maintainerMapper.selectOrders(uid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public Integer insertNewCommitLog(WorkOrderMessage message) {
        Integer row = null;
        try {
            WorkOrderDto workOrderDto = new WorkOrderDto(message);
            workOrderDto.setCid(IdGenerator.getId());
            maintainerMapper.insertNewCommitLog(workOrderDto);
            row = workOrderDto.getRow();
            System.out.println(row);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }
}
