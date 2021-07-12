package com.server.workordersystem.service;

import com.server.workordersystem.dto.*;
import com.server.workordersystem.entity.User;
import com.server.workordersystem.entity.WorkOrder;

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
     * @param userTypeGroups 用户uid、分组、权限
     * @return 操作是否成功
     */
    Integer auths(List<UserTypeGroup> userTypeGroups);

    /**
     * 修改用户权限和分组
     *
     * @param message 用户uid、分组、权限
     * @return 操作是否成功
     */
    Integer auth(ModifyUserPowerMessage message);

    /**
     * 重置用户密码
     *
     * @param resetPasswordMeg 用户uid、新密码
     * @return 操作是否成功
     */
    Integer resetPw(ResetPasswordMeg resetPasswordMeg);

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
     * @param uids 要删除的用户名单
     * @return 删除个数
     */
    Integer deleteUsersByUid(List<Integer> uids);

    /**
     * 批量注销用户
     *
     * @param uids     用户名单
     * @param logState 注销状态
     * @return 注销个数
     */
    Integer updateUsersLogState(List<Integer> uids, Boolean logState);

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
    List<UserInfoMsg> getAllUsers();

    /**
     * 获取所有工单
     *
     * @return 所有工单
     */
    List<WorkOrder> getAllWorkOrder();

    /**
     * 获取所有未审核工单
     *
     * @return 所有未审核工单
     */
    List<WorkOrder> getNotVerifyOrder();

    /**
     * 获取所有未分配工单
     *
     * @return 所有工单
     */
    List<WorkOrder> getUnallocatedOrder();

    /**
     * 审核工单
     *
     * @param verifyOrderMeg 工单号和审核结果
     * @return 修改个数
     */
    Integer updateVerifyOrder(VerifyOrderMeg verifyOrderMeg, Integer uid);

    /**
     * 分配工单给组
     *
     * @param allocateOrderMeg 工单号和待分配的组
     * @return 操作成功
     */
    Integer allocateOrder(AllocateOrderMeg allocateOrderMeg);

    /**
     * 关闭工单
     *
     * @param orderCloseMeg 工单号和时间
     * @return 操作成功
     */
    Integer closeOrder(OrderCloseMeg orderCloseMeg);

    /**
     * 获取分组成员
     *
     * @return 所有用户
     */
    List<UserInfoMsg> getGroupMember(GroupMemberMeg groupMemberMeg);

}
