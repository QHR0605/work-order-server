package com.server.workordersystem.mapper;
import com.server.workordersystem.entity.Filter;
import com.server.workordersystem.entity.Log;
import com.server.workordersystem.entity.ServerMessage;
import com.server.workordersystem.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 全鸿润
 */
@Repository
public interface AdminMapper {
    /**
     * 修改权限
     *
     * @param usernames 用户集合
     * @param type      用户类型
     * @return 授权的个数
     * @throws Exception 数据库操作异常
     */
    Integer updateUserAuthorization(List<String> usernames, Integer type) throws Exception;

    /**
     * 创建新账户
     *
     * @param user 新用户
     * @return 插入个数
     * @throws Exception 数据库操作异常
     */
    Integer insertNewUser(User user) throws Exception;

    /**
     * 批量删除用户
     *
     * @param usernames 要删除的用户名集合
     * @return 删除的个数
     * @throws Exception 数据库操作异常
     */
    Integer deleteUsers(List<String> usernames) throws Exception;

    /**
     * 获取用户列表
     *
     * @return 用户列表
     * @throws Exception 数据库操作异常
     */
    List<User> selectAllUsers() throws Exception;

    /**
     * 修改用户类型
     *
     * @param usernames 要修改的用户集合
     * @param type      用类型(0,1,2)
     * @return 修改的用户个数
     * @throws Exception 数据库操作异常
     */
    Integer updateUserType(List<String> usernames, Integer type) throws Exception;

    /**
     * 批量注销用户
     *
     * @param usernames 要注销的用户集合
     * @param logState  注销状态
     * @return 注销的个数
     * @throws Exception 数据库操作异常
     */
    Integer updateUserLogState(List<String> usernames, Boolean logState) throws Exception;

    /**
     * 过滤账号
     *
     * @param usernames 账号名单
     * @param forbidden 是否过滤
     * @return 设置用户数
     * @throws Exception 数据库操作异常
     */
    Integer updateUserForbidden(List<String> usernames, Boolean forbidden) throws Exception;

    /**
     * 获取服务器信息
     *
     * @return 服务器信息
     */
    List<ServerMessage> selectServerMessage() throws Exception;

    /**
     * 获取IP地址黑名单
     *
     * @return IP地址黑名单
     * @throws Exception 数据库操作异常
     */
    List<Filter> selectFilter() throws Exception;

    /**
     * 添加IP黑名单
     *
     * @param filter 新添加的IP地址黑名单列表
     * @return 添加的行数
     * @throws Exception 数据库操作异常
     */
    Integer insertNewIpAddress(List<Filter> filter) throws Exception;

    /**
     * 取消黑名单的IP地址
     *
     * @param ipList 黑名单的id列表
     * @return 删除行数
     * @throws Exception 数据库操作异常
     */
    Integer deleteIpAddress(List<String> ipList) throws Exception;

    /**
     * 修改邮箱大小
     *
     * @param usernames 要修改的邮箱
     * @param size      邮箱大小
     * @return 修改行数
     * @throws Exception 数据库操作异常
     */
    Integer updateMailBoxSize(List<String> usernames, Integer size) throws Exception;

    /**
     * 添加日志
     *
     * @param log 日志
     * @return 添加的行数
     * @throws Exception 数据库操作异常
     */
    Integer addLog(Log log) throws Exception;

    /**
     * 获取所有日志
     *
     * @return 所有日志信息
     * @throws Exception 数据库操作异常
     */
    List<Log> selectLogs() throws Exception;

    /**
     * 删除日志
     *
     * @param logIdList 日志id列表
     * @return 删除行数
     * @throws Exception 数据库操作异常
     */
    Integer deleteLog(List<Integer> logIdList) throws Exception;

}
