package com.server.workordersystem.service;

import com.server.workordersystem.dto.UserInfoMsg;

import java.util.List;

public interface MonitorService {

    /**
     * 获取组员
     *
     * @return 所有组员信息
     */
    List<UserInfoMsg> getMembers(Integer group);
}
