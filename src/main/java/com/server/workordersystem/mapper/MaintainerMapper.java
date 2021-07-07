package com.server.workordersystem.mapper;

import com.server.workordersystem.dto.*;
import com.server.workordersystem.dto.WorkOrder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintainerMapper {

    /**
     * @param workOrderDto 工单信息
     * @return 插入行数
     * @throws Exception 数据库操作异常
     */
    Integer insertNewWorkOrder(WorkOrderDto workOrderDto) throws Exception;

    Integer insertNewOrderFile(List<OrderAttachFile> files) throws Exception;

    Integer insertNewCommitFile(List<CommitAttachFile> files) throws Exception;

    Integer insertNewSolutionFile(List<SolutionAttachFile> files) throws Exception;

    Integer insertNewSolution(SolutionMessage message) throws Exception;

    /**
     * @param workOrderDto 提交记录
     * @return 插入行数
     * @throws Exception 数据库操作异常
     */
    Integer insertNewCommitLog(WorkOrderDto workOrderDto) throws Exception;

    Integer insertNewDraft(WorkOrderMessage message) throws Exception;

    /**
     * @param orderId   工单号
     * @param completed 完成状态
     * @return 修改行数
     * @throws Exception 数据库操作异常
     */
    Integer updateOrderCompletedState(Integer orderId, Boolean completed) throws Exception;

    Integer updateOrderState(Integer orderId, Integer state) throws Exception;

    List<WorkOrder> selectOrders(Integer uid) throws Exception;

    List<WorkOrder> selectDrafts(Integer uid) throws Exception;

    List<WorkOrder> selectHandledOrders(Integer uid) throws Exception;

    List<WorkOrder> selectNotHandledOrders(Integer uid) throws Exception;

    Integer deleteOrderFile(Integer orderId);

    List<String> getOrderFiles(Integer orderId);

    List<String> getSolutionFiles(Integer sid);

    List<String> getCommitFiles(Integer cid);
}
