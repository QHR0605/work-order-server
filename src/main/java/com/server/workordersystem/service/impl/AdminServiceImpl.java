package com.server.workordersystem.service.impl;

import com.server.workordersystem.config.SpringContextConfig;

import com.server.workordersystem.dto.*;
import com.server.workordersystem.entity.Group;
import com.server.workordersystem.entity.SolutionLog;
import com.server.workordersystem.entity.User;
import com.server.workordersystem.entity.WorkOrder;
import com.server.workordersystem.mapper.AdminMapper;
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
        User infoUser = null;
        Group infoGroup = null;

        try {
            if (message.getGroup() < 9 && message.getGroup() > 0) {
                infoUser = adminMapper.selectUser(message.getUid());
                if (infoUser.getAccountType().equals(1)) {
                    if (message.getAccountType().equals(1)) {
                        infoGroup = adminMapper.selectMentor(message);
                        if (infoGroup.getMentor() == null) {
                            row = adminMapper.updateMentor(message.getGroup(), message.getUid());
                        } else if (infoGroup.getMentor().equals(message.getUid())) {
                            return row = -1;
                        } else {
                            return row = 0;
                        }
                    }
                } else {
                    row = adminMapper.updateMentor(message.getGroup(), message.getUid());
                }
                row = adminMapper.updateUserAuthMentor(message);
            } else {
                return row = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    /*
    重置密码
     */
    @Override
    public Integer resetPw(ResetPasswordMeg resetPasswordMeg) {
        Integer row = null;
        User user = null;
        try {
            user = adminMapper.selectUser(resetPasswordMeg.getUid());
            if (user != null) {
                row = adminMapper.updateResetPassword(resetPasswordMeg);
            } else {
                return row = 0;
            }
            System.out.println(row);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    /*
    创建用户
     */
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
        try {
            row = adminMapper.updateUserType(usernames, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    /*
    获取所有用户信息
     */
    @Override
    public List<UserInfoMsg> getAllUsers() {
        List<UserInfoMsg> users = null;
        try {
            users = adminMapper.selectAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
            return users;
        }
        return users;
    }

    /*
    获取所有工单
     */
    @Override
    public List<WorkOrder> getAllWorkOrder() {
        List<WorkOrder> workOrders = null;
        try {
            workOrders = adminMapper.selectAllWorkOrder();
        } catch (Exception e) {
            e.printStackTrace();
            return workOrders;
        }
        return workOrders;
    }

    /*
    获取未分配工单
     */
    @Override
    public List<WorkOrder> getUnallocatedOrder() {
        List<WorkOrder> workOrders = null;
        try {
            workOrders = adminMapper.selectUnallocatedOrder();
        } catch (Exception e) {
            e.printStackTrace();
            return workOrders;
        }
        return workOrders;
    }

    /*
    获取未审核工单
     */
    @Override
    public List<WorkOrder> getNotVerifyOrder() {
        List<WorkOrder> workOrders = null;
        try {
            workOrders = adminMapper.selectNotVerifyOrder();
        } catch (Exception e) {
            e.printStackTrace();
            return workOrders;
        }
        return workOrders;
    }


    /*
    审核工单
     */
    @Override
    public Integer updateVerifyOrder(VerifyOrderMeg verifyOrderMeg, Integer uid) {
        Integer row = null;
        Integer state = null;
        WorkOrder workOrder = null;
        try {
            //判断工单是否存在、待审核
            workOrder = adminMapper.selectWorkOrder(verifyOrderMeg.getOrderId());
            if (workOrder != null) {
                if (workOrder.getState().equals(0)) {
                    if (verifyOrderMeg.getVerifiedResult()) {
                        //审核通过，工单状态为2
                        state = 2;
                        row = adminMapper.updateVerifyOrder(verifyOrderMeg, state, workOrder.getCid(), uid);
                    } else {
                        //审核不通过，工单状态为3
                        state = 3;
                        row = adminMapper.updateVerifyOrder(verifyOrderMeg, state, workOrder.getCid(), uid);
                    }
                } else {
                    return row = -1;
                }
            } else {
                return row = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    /*
    分配工单给组
     */
    @Override
    public Integer allocateOrder(AllocateOrderMeg allocateOrderMeg) {

        Integer row = null;
        WorkOrder workOrder = null;
        try {
            //查找工单，并判断状态为已审核(待分配)才能分配
            workOrder = adminMapper.selectWorkOrder(allocateOrderMeg.getOrderId());
            if (workOrder != null) {
                if (workOrder.getState().equals(2) && workOrder.getHandleGroup() == 4) {
                    row = adminMapper.updateAllocateOrder(allocateOrderMeg);
                } else {
                    return row = -1;
                }
            } else {
                return row = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    /*
    关闭工单
     */
    @Override
    public Integer closeOrder(OrderCloseMeg orderCloseMeg) {
        Integer row = null;
        WorkOrder workOrder = null;
        try {
            //查找工单
            workOrder = adminMapper.selectWorkOrder(orderCloseMeg.getOrderId());
            if (workOrder != null && workOrder.getState() != 1) {
                if (orderCloseMeg.getCompletionTime().after(workOrder.getCreateTime())) {
                    row = adminMapper.updateCloseOrder(orderCloseMeg);
                } else {
                    row = -1;
                }
            } else {
                return row = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    /*
    查询分组成员
     */
    @Override
    public List<UserInfoMsg> getGroupMember(GroupMemberMeg groupMemberMeg) {
        List<UserInfoMsg> users = null;
        try {
            users = adminMapper.selectGroupMember(groupMemberMeg.getGroup());
        } catch (Exception e) {
            e.printStackTrace();
            return users;
        }
        return users;
    }

    @Override
    public OrderCircleMeg getOrderCircle(OrderIdMeg orderIdMeg) {
        User user = null;
        User user1 = null;
        SolutionLog solutionLog = null;
        WorkOrder workOrder = null;
        OrderCircleMeg orderCircleMeg = new OrderCircleMeg();
        try {
            workOrder = adminMapper.selectWorkOrder(orderIdMeg.getOrderId());
            if (workOrder != null) {
                user = adminMapper.selectUser(workOrder.getCreator());
                solutionLog = adminMapper.selectSolutionLog(workOrder.getSid());
                if (solutionLog != null) {
                    user1 = adminMapper.selectUser(solutionLog.getUid());
                    orderCircleMeg.setHandlerId(user1.getUid());
                    orderCircleMeg.setHandlerName(user1.getName());
                    orderCircleMeg.setDistributeTime(solutionLog.getDistributeTime());
                    orderCircleMeg.setHandleTime(solutionLog.getHandleTime());
                }
                orderCircleMeg.setCreatorId(user.getUid());
                orderCircleMeg.setCreatorName(user.getName());
                orderCircleMeg.setCreateTime(workOrder.getCreateTime());
                orderCircleMeg.setCompletionTime(workOrder.getCompletionTime());
                orderCircleMeg.setState(workOrder.getState());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return orderCircleMeg;
        }
        return orderCircleMeg;
    }


}
