package com.server.workordersystem.service;

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
}
