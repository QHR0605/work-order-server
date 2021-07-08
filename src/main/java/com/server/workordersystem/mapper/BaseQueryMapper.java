package com.server.workordersystem.mapper;

import com.server.workordersystem.entity.Group;
import com.server.workordersystem.entity.WorkOrderType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseQueryMapper {

    List<WorkOrderType> getWorkOrderType() throws Exception;

    List<Group> getGroup() throws Exception;
}
