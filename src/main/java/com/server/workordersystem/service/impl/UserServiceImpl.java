package com.server.workordersystem.service.impl;

import com.server.workordersystem.config.SpringContextConfig;
import com.server.workordersystem.dto.UserMessage;
import com.server.workordersystem.mapper.UserMapper;
import com.server.workordersystem.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author 全鸿润
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper = SpringContextConfig.getBean(UserMapper.class);

    @Override
    public UserMessage getUserInfo(Integer uid) {

        UserMessage message = null;
        try {
            message = userMapper.getUserInfo(uid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    @Override
    public Integer updateUserInfo(UserMessage message) {

        Integer row = null;
        try {
            row = userMapper.updateUserMsg(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }
}
