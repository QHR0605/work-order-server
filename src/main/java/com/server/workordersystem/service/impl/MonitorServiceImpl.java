package com.server.workordersystem.service.impl;

import com.server.workordersystem.config.SpringContextConfig;
import com.server.workordersystem.dto.UserInfoMsg;
import com.server.workordersystem.mapper.AdminMapper;
import com.server.workordersystem.mapper.MonitorMapper;
import com.server.workordersystem.service.MonitorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonitorServiceImpl implements MonitorService {

    private final MonitorMapper monitorMapper = SpringContextConfig.getBean(MonitorMapper.class);

    @Override
    public List<UserInfoMsg> getMembers(Integer uid) {
        List<UserInfoMsg> users = null;
        try {
            users = monitorMapper.selectMembers(uid);
        } catch (Exception e) {
            e.printStackTrace();
            return users;
        }
        return users;
    }
}
