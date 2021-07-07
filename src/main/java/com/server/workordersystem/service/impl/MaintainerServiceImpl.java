package com.server.workordersystem.service.impl;

import com.server.workordersystem.dto.CommitAttachFile;
import com.server.workordersystem.dto.OrderAttachFile;
import com.server.workordersystem.dto.WorkOrderDto;
import com.server.workordersystem.dto.WorkOrderMessage;
import com.server.workordersystem.entity.WorkOrder;
import com.server.workordersystem.mapper.MaintainerMapper;
import com.server.workordersystem.service.MaintainerService;
import com.server.workordersystem.util.idGenerator.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
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
            System.out.println("插入记录和工单: " + row);
            if (row == 0) {
                return row;
            }
            //添加工单附件
            List<OrderAttachFile> orderAttachFiles = new LinkedList<>();
            for (String url : message.getImage()
            ) {
                OrderAttachFile file = new OrderAttachFile();
                file.setFid(IdGenerator.getId());
                file.setUrl(url);
                file.setOrderId(workOrderDto.getOrderId());
                orderAttachFiles.add(file);
            }
            row = maintainerMapper.insertNewOrderFile(orderAttachFiles);
            System.out.println("插入工单附件表: " + row);
            if (row == null || row != orderAttachFiles.size()) {
                return null;
            }
            //添加提交记录附件
            List<CommitAttachFile> commitAttachFiles = new LinkedList<>();
            for (String url : message.getImage()
            ) {
                CommitAttachFile file = new CommitAttachFile();
                file.setFid(IdGenerator.getId());
                file.setUrl(url);
                file.setCid(workOrderDto.getCid());
                commitAttachFiles.add(file);
            }
            row = maintainerMapper.insertNewCommitFile(commitAttachFiles);
            System.out.println("插入提交记录附件: " + row);
            if (row != commitAttachFiles.size()) {
                return null;
            }
            row = 1;
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
    public List<WorkOrder> getDrafts(Integer uid) {
        List<WorkOrder> orders = null;
        try {
            orders = maintainerMapper.selectDrafts(uid);
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
            if (row == 0){
                return null;
            }
            row = maintainerMapper.deleteOrderFile(workOrderDto.getOrderId());
            if (row == null){
                return row;
            }
            //添加工单附件
            List<OrderAttachFile> orderAttachFiles = new LinkedList<>();
            for (String url : message.getImage()
            ) {
                OrderAttachFile file = new OrderAttachFile();
                file.setFid(IdGenerator.getId());
                file.setUrl(url);
                file.setOrderId(workOrderDto.getOrderId());
                orderAttachFiles.add(file);
            }
            row = maintainerMapper.insertNewOrderFile(orderAttachFiles);
            System.out.println("插入工单附件表: " + row);
            if (row == null || row != orderAttachFiles.size()) {
                return null;
            }
            //添加提交记录附件
            List<CommitAttachFile> commitAttachFiles = new LinkedList<>();
            for (String url : message.getImage()
            ) {
                CommitAttachFile file = new CommitAttachFile();
                file.setFid(IdGenerator.getId());
                file.setUrl(url);
                file.setCid(workOrderDto.getCid());
                commitAttachFiles.add(file);
            }
            row = maintainerMapper.insertNewCommitFile(commitAttachFiles);
            System.out.println("插入提交记录附件: " + row);
            if (row != commitAttachFiles.size()) {
                return null;
            }
            row = 1;
            System.out.println(row);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer insertNewDraft(WorkOrderMessage message) {

        Integer row = null;

        try {
            message.setOrderId(IdGenerator.getId());
            row = maintainerMapper.insertNewDraft(message);
            if (row == null || row != 1){
                return row;
            }//添加工单附件
            List<OrderAttachFile> orderAttachFiles = new LinkedList<>();
            for (String url : message.getImage()
            ) {
                OrderAttachFile file = new OrderAttachFile();
                file.setFid(IdGenerator.getId());
                file.setUrl(url);
                file.setOrderId(message.getOrderId());
                orderAttachFiles.add(file);
            }
            row = maintainerMapper.insertNewOrderFile(orderAttachFiles);
            System.out.println("插入工单附件表: " + row);
            if (row == null || row != orderAttachFiles.size()) {
                return null;
            }
            row = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer updateOrderState(Integer orderId, Integer state) {

        Integer row = null;
        try {
            row = maintainerMapper.updateOrderState(orderId,state);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }
}
