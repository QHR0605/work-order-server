package com.server.workordersystem.mapper;

import com.server.workordersystem.dto.*;
import com.server.workordersystem.entity.Group;
import com.server.workordersystem.entity.SolutionLog;
import com.server.workordersystem.entity.User;
import com.server.workordersystem.entity.WorkOrder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 全鸿润
 */
@Repository
public interface AdminMapper {

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户数据
     * @throws Exception 数据库操作异常
     */
    User findUserByUserNameAdmin(String userName) throws Exception;

    /**
     * 批量修改权限和分组
     *
     * @param message 用户uid、分组、权限
     * @return 操作是否成功
     * @throws Exception 数据库操作异常
     */
    Integer updateUserAuthorizations(List<UserTypeGroup> message) throws Exception;

    /**
     * 查询组长
     *
     * @param message 用户uid、分组、权限
     * @return 操作是否成功
     * @throws Exception 数据库操作异常
     */
    Group selectMentor(ModifyUserPowerMessage message) throws Exception;

    /**
     * 查询用户信息
     *
     * @param uid 用户uid
     * @return 操作是否成功
     * @throws Exception 数据库操作异常
     */
    User selectUser(Integer uid) throws Exception;

    /**
     * 查询处理记录
     *
     * @param sid 处理记录id
     * @return 处理记录
     * @throws Exception 数据库操作异常
     */
    SolutionLog selectSolutionLog(Integer sid) throws Exception;

    /**
     * 修改组长
     *
     * @param group,mentor 用户uid、分组、权限
     * @return 操作是否成功
     * @throws Exception 数据库操作异常
     */
    Integer updateMentor(Integer group,Integer mentor) throws Exception;

    /**
     * 修改权限
     *
     * @param message 用户uid、分组、权限
     * @return 操作是否成功
     * @throws Exception 数据库操作异常
     */
    Integer updateUserAuthMentor(ModifyUserPowerMessage message) throws Exception;

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
     * @param uids 要删除的用户名集合
     * @return 删除的个数
     * @throws Exception 数据库操作异常
     */
    Integer deleteUsers(List<Integer> uids) throws Exception;

    /**
     * 获取用户列表
     *
     * @return 用户列表
     * @throws Exception 数据库操作异常
     */
    List<UserInfoMsg> selectAllUsers() throws Exception;

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
     * 重置密码
     *
     * @param resetPasswordMeg 用户uid、新密码
     * @return 操作是否成功
     * @throws Exception 数据库操作异常
     */
    Integer updateResetPassword(ResetPasswordMeg resetPasswordMeg) throws Exception;

    /**
     * 批量注销用户
     *
     * @param uids     要注销的用户集合
     * @param logState 注销状态
     * @return 注销的个数
     * @throws Exception 数据库操作异常
     */
    Integer updateUserLogState(List<Integer> uids, Boolean logState) throws Exception;

    /**
     * 获取所有工单
     *
     * @return 所有工单
     * @throws Exception 数据库操作异常
     */
    List<WorkOrder> selectAllWorkOrder() throws Exception;

    /**
     * 获取所有未审核工单
     *
     * @return 所有未审核工单
     * @throws Exception 数据库操作异常
     */
    List<WorkOrder> selectNotVerifyOrder() throws Exception;

    /**
     * 获取所有未分配工单
     *
     * @return 所有未分配工单
     * @throws Exception 数据库操作异常
     */
    List<WorkOrder> selectUnallocatedOrder() throws Exception;

    /**
     * 查找工单
     *
     * @param orderId 工单号
     * @return 工单信息
     * @throws Exception 数据库操作异常
     */
    WorkOrder selectWorkOrder(Integer orderId) throws Exception;

    /**
     * 工单审核结果
     *
     * @param verifyOrderMeg 工单号和审核结果
     * @return 修改的用户个数
     * @throws Exception 数据库操作异常
     */
    Integer updateVerifyOrder(VerifyOrderMeg verifyOrderMeg, Integer state, Integer cid, Integer uid) throws Exception;

    /**
     * 工单分配给组
     *
     * @param allocateOrderMeg 工单号和待分配的组
     * @return 操作成功
     * @throws Exception 数据库操作异常
     */
    Integer updateAllocateOrder(AllocateOrderMeg allocateOrderMeg) throws Exception;

    /**
     * 关闭工单
     *
     * @param orderCloseMeg 工单号和时间
     * @return 操作成功
     * @throws Exception 数据库操作异常
     */
    Integer updateCloseOrder(OrderCloseMeg orderCloseMeg) throws Exception;

    /**
     * 获取分组成员
     *
     * @return 成员用户列表
     * @throws Exception 数据库操作异常
     */
    List<UserInfoMsg> selectGroupMember(Integer group) throws Exception;

}
