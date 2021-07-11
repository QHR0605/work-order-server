package com.server.workordersystem.service.impl;

import com.server.workordersystem.config.SpringContextConfig;
import com.server.workordersystem.dto.UserInfoMsg;
import com.server.workordersystem.entity.User;
import com.server.workordersystem.entity.WorkOrder;
import com.server.workordersystem.mapper.AdminMapper;
import com.server.workordersystem.mapper.MonitorMapper;
import com.server.workordersystem.service.MonitorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonitorServiceImpl implements MonitorService {

    private final MonitorMapper monitorMapper = SpringContextConfig.getBean(MonitorMapper.class);
    private final AdminMapper adminMapper = SpringContextConfig.getBean(AdminMapper.class);

    /*
    获取组成员
     */
    @Override
    public List<UserInfoMsg> getMembers(Integer uid) {
        User user = null;
        List<UserInfoMsg> users = null;
        try {
            user = adminMapper.selectUser(uid);
            if (user.getAccountType().equals(1)){
                users = monitorMapper.selectMembers(user.getGroup());
            } else {
                users = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return users;
        }
        return users;
    }

    /*
    获取被分配到组的工单
     */
    @Override
    public List<WorkOrder> getGroupOrder(Integer uid) {
        User user = null;
        List<WorkOrder> workOrderList = null;
        try {
            user = adminMapper.selectUser(uid);
            if (user.getAccountType().equals(1)){
                workOrderList = monitorMapper.selectGroupOrder(user.getGroup());
            } else {
                workOrderList = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return workOrderList;
        }
        return workOrderList;
    }


}
