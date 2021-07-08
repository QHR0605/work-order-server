package com.server.workordersystem.service;

import com.server.workordersystem.entity.Group;
import com.server.workordersystem.entity.WorkOrderType;

import java.util.List;

public interface BaseQueryService {

    List<WorkOrderType> getWorkOrderTypes();

    List<Group> getGroups();
}
