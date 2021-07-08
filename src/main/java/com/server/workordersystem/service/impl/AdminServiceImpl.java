package com.server.workordersystem.service.impl;

import com.server.workordersystem.config.SpringContextConfig;
import com.server.workordersystem.dto.*;
import com.server.workordersystem.entity.User;
import com.server.workordersystem.mapper.AdminMapper;
import com.server.workordersystem.mapper.LoginMapper;
import com.server.workordersystem.mapper.UserMapper;
import com.server.workordersystem.service.AdminService;
import com.server.workordersystem.util.http.CookieUtils;
import com.server.workordersystem.util.http.HttpUtil;
import com.server.workordersystem.util.idGenerator.IdGenerator;
import com.server.workordersystem.util.interceptor.IpUtil;
import com.server.workordersystem.util.json.JsonResultStateCode;
import com.server.workordersystem.util.token.TokenGenerator;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author 全鸿润
 */
@Service
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper = SpringContextConfig.getBean(AdminMapper.class);
    private final UserMapper userMapper = SpringContextConfig.getBean(UserMapper.class);
    /*
    管理员登录
     */

    @Override
    public String handleLoginAdmin(String username, String password) {
        try {
            User user;
            System.out.println("username: " + username);
            System.out.println("password: " + password);
            user = adminMapper.findUserByUserNameAdmin(username);
            if (user == null) {
                return JsonResultStateCode.NOT_Admin;
            } else if (user.getLogout()) {
                return JsonResultStateCode.USER_IS_LOG_OUT_DESC;
            } else {
                if (!user.getPassword().equals(password)) {
                    return JsonResultStateCode.PASSWORD_WRONG_DESC;
                } else {
                    //设置cookie:token和用户ID
                    HttpServletResponse response = HttpUtil.getResponse();
                    System.out.println(user);
                    String token = TokenGenerator.generateToken(user.getUid(), password, user.getAccountType());
                    System.out.println("登录成功,生成token: " + token);
                    Cookie tokenCookie = CookieUtils.buildCookie("token", token);
                    Cookie usernameCookie = CookieUtils.buildCookie("uid", user.getUid().toString());
                    response.addCookie(tokenCookie);
                    response.addCookie(usernameCookie);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResultStateCode.UNKNOWN_ERROR_DESC;
        }
        //更新最近登录时间和IP
        LatestLoginMsg msg = new LatestLoginMsg();
        msg.setUsername(username);
        msg.setLatestLoginTime(new Timestamp(System.currentTimeMillis()));
        msg.setLatestLoginIp(IpUtil.getIp(HttpUtil.getRequest()));
        try {
            userMapper.updateUserLatestLoginMsg(msg);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResultStateCode.SUCCESS_DESC;
        }
        return JsonResultStateCode.SUCCESS_DESC;
    }


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

    /*
    修改用户权限和分组
     */
    @Override
    public Integer auths(List<UserTypeGroup> userTypeGroups) {
        Integer row = null;

        try {
            row = adminMapper.updateUserAuthorizations(userTypeGroups);

            System.out.println(row);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    /*
    修改用户权限和分组
     */
    @Override
    public Integer auth(ModifyUserPowerMessage message) {
        Integer row = null;

        try {
            row = adminMapper.updateUserAuthorization(message);

            System.out.println(row);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer createUser(NewUserMessage message) {

        User user = new User()
                .uid(IdGenerator.getId())
                .username(message.getUsername())
                .password(message.getPassword())
                .accountType(message.getAccountType());
        System.out.println(user.getUid());
        Integer row = null;
        try {
            row = adminMapper.insertNewUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    /*
    删除用户
     */
    @Override
    public Integer deleteUsersByUid(List<Integer> uids) {
        Integer row = null;
        try {
            row = adminMapper.deleteUsers(uids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer updateUsersLogState(List<Integer> uids, Boolean logState) {

        Integer row = null;

        try {
            row = adminMapper.updateUserLogState(uids, logState);

            System.out.println(row);
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
