package com.server.workordersystem.service.impl;

import com.server.workordersystem.entity.Group;
import com.server.workordersystem.entity.WorkOrderType;
import com.server.workordersystem.mapper.BaseQueryMapper;
import com.server.workordersystem.service.BaseQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseQueryServiceImpl implements BaseQueryService {
    @Autowired
    private BaseQueryMapper baseQueryMapper;

    @Override
    public List<WorkOrderType> getWorkOrderTypes() {

        List<WorkOrderType> res = null;
        try {
            res = baseQueryMapper.getWorkOrderType();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public List<Group> getGroups() {
        List<Group> res = null;
        try {
            res = baseQueryMapper.getGroup();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
