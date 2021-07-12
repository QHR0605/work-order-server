package com.server.workordersystem.mapper;

import com.server.workordersystem.dto.OrderAllocateMeg;
import com.server.workordersystem.dto.UserInfoMsg;
import com.server.workordersystem.entity.WorkOrder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonitorMapper {

    /**
     * 获取用户列表
     *
     * @return 用户列表
     * @throws Exception 数据库操作异常
     */
    List<UserInfoMsg> selectMembers(Integer group) throws Exception;

    /**
     * 获取用户列表
     *
     * @return 用户列表
     * @throws Exception 数据库操作异常
     */
    List<WorkOrder> selectGroupOrder(Integer group) throws Exception;

    /**
     * 分配工单
     *
     * @return 操作成功
     * @throws Exception 数据库操作异常
     */
    Integer insertAllocateOrder(OrderAllocateMeg orderAllocateMeg,Integer sid, Integer cid) throws Exception;

    Integer updateAllocateOrder(OrderAllocateMeg orderAllocateMeg,Integer sid) throws Exception;
}
