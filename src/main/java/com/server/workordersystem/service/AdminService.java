package com.server.workordersystem.service;

import com.server.workordersystem.dto.ModifyUserPowerMessage;
import com.server.workordersystem.dto.NewUserMessage;
import com.server.workordersystem.entity.User;

import java.util.List;

/**
 * @author 全鸿润
 */
public interface AdminService {

    /**
     * 管理员登录验证
     *
     * @param username password 用户名，密码
     * @return 操作是否成功
     */
    public String handleLoginAdmin(String username, String password);

    /**
     * 修改用户权限和分组
     *
     * @param message 用户uid、分组、权限
     * @return 操作是否成功
     */
    Integer auth(ModifyUserPowerMessage message);

    /**
     * 创建新账户
     *
     * @param message 账户信息
     * @return 创建成功的个数
     */
    Integer createUser(NewUserMessage message);

    /**
     * 批量删除用户
     *
     * @param uid 要删除的用户名单
     * @return 删除个数
     */
    Integer deleteUsersByUid(List<Integer> uid);

    /**
     * 批量注销用户
     *
     * @param username 用户名单
     * @param logState 注销状态
     * @return 注销个数
     */
    Integer updateUsersLogState(List<String> username, Boolean logState);

    /**
     * 修改用户类型
     *
     * @param username 用户名单
     * @param type     用户类型
     * @return 修改个数
     */
    Integer updateUsersType(List<String> username, Integer type);

    /**
     * 获取所有用户
     *
     * @return 所有用户
     */
    List<User> getAllUsers();
}
