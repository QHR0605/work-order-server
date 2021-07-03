package com.server.workordersystem.service;


import com.server.workordersystem.dto.NewUserMessage;
import com.server.workordersystem.dto.UserMessage;
import com.server.workordersystem.entity.User;

/**
 * @author 全鸿润
 */
public interface AuthService {
    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @return 验证状态
     */
    String handleLogin(String username, String password);

    /**
     * 查询用户
     *
     * @param username 用户名
     * @return 用户实例
     */
    User findUserByUsername(String username);

    /**
     * 注册用户
     *
     * @param userMessage 用户填写的信息
     * @return 新增用户数
     */
    Integer registerUser(NewUserMessage userMessage);

    /**
     * 修改密码
     *
     * @param username    用户名
     * @param newPassword 新密码
     * @return 修改的密码数
     */
    Integer updatePassword(String username, String newPassword);


}
