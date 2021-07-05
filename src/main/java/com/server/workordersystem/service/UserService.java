package com.server.workordersystem.service;

import com.server.workordersystem.dto.UserMessage;
import org.springframework.stereotype.Service;

/**
 * @author 全鸿润
 */
@Service
public interface UserService {

    UserMessage getUserInfo(Integer uid);

    Integer updateUserInfo(UserMessage message);
}
