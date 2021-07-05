package com.server.workordersystem.service.impl;

import com.server.workordersystem.config.SpringContextConfig;
import com.server.workordersystem.dto.NewUserMessage;
import com.server.workordersystem.entity.User;
import com.server.workordersystem.mapper.AdminMapper;
import com.server.workordersystem.service.AdminService;
import com.server.workordersystem.util.http.CookieUtils;
import com.server.workordersystem.util.http.HttpUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.List;

/**
 * @author 全鸿润
 */
@Service
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper = SpringContextConfig.getBean(AdminMapper.class);

    public String getUsername() {
        String username = null;
        try {
            Cookie cookie = CookieUtils.findCookie(HttpUtil.getRequest().getCookies(), "username");
            if (cookie != null) {
                username = cookie.getValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return username;
    }

    @Override
    public Integer auth(List<String> usernames, Integer authType) {
        Integer row = null;

        try {
            row = adminMapper.updateUserAuthorization(usernames, authType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer createUser(NewUserMessage message) {

        User user = new User()
                .username(message.getUsername())
                .password(message.getPassword())
                .accountType(message.getAccountType());
        Integer row = null;
        try {
            row = adminMapper.insertNewUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer deleteUsersByUsername(List<String> usernames) {
        Integer row = null;
        try {
            row = adminMapper.deleteUsers(usernames);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer updateUsersLogState(List<String> usernames, Boolean logState) {

        Integer row = null;

        try {
            row = adminMapper.updateUserLogState(usernames, logState);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer updateUsersType(List<String> usernames, Integer type) {
        Integer row = null;
        StringBuilder content = new StringBuilder();
        try {
            row = adminMapper.updateUserType(usernames, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try {
            users = adminMapper.selectAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
            return users;
        }
        return users;
    }

}
