package com.server.workordersystem.mapper;

import com.server.workordersystem.dto.LatestLoginMsg;
import com.server.workordersystem.dto.UserMessage;
import org.springframework.stereotype.Repository;

/**
 * @author 全鸿润
 */
@Repository
public interface UserMapper {

    /**
     * @param uid 用户ID
     * @return 用户信息
     * @throws Exception 数据库操作异常
     */
    UserMessage getUserInfo(Integer uid) throws Exception;

    /**
     * @param userMessage 修改的用户信息
     * @return 修改行数
     * @throws Exception 数据库操作异常
     */
    Integer updateUserMsg(UserMessage userMessage) throws Exception;

    /**
     * @param msg 最近登录信息
     * @return 修改行数
     * @throws Exception 数据库操作异常
     */
    Integer updateUserLatestLoginMsg(LatestLoginMsg msg) throws Exception;

}
