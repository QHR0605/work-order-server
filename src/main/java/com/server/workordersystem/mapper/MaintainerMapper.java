package com.server.workordersystem.mapper;

import com.server.workordersystem.dto.WorkOrderMessage;
import com.server.workordersystem.entity.WorkOrder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintainerMapper {

    /**
     * @param message 工单信息
     * @return 插入行数
     * @throws Exception 数据库操作异常
     */
    Integer insertNewWorkOrder(WorkOrderMessage message) throws Exception;


    /**
     * @param orderId 工单号
     * @param completed 完成状态
     * @return 修改行数
     * @throws Exception 数据库操作异常
     */
    Integer updateOrderCompletedState(Integer orderId , Boolean completed) throws Exception;

    List<WorkOrder> selectOrders(Integer uid) throws Exception;
}
