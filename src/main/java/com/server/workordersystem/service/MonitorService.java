package com.server.workordersystem.service;

import com.server.workordersystem.dto.OrderAllocateMeg;
import com.server.workordersystem.dto.UserInfoMsg;
import com.server.workordersystem.entity.WorkOrder;

import java.util.List;

public interface MonitorService {

    /**
     * 获取组员
     *
     * @return 所有组员信息
     */
    List<UserInfoMsg> getMembers(Integer uid);

    /**
     * 获取被分配到组的工单
     *
     * @return 被分配到组的工单
     */
    List<WorkOrder> getGroupOrder(Integer uid);

    List<WorkOrder> getAllocatedGroupOrder(Integer uid);

    /**
     * 分配工单
     *
     * @return 操作成功
     */
    Integer insertAllocateOrder(OrderAllocateMeg orderAllocateMeg, Integer uid);
}
