package com.server.workordersystem.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.server.workordersystem.config.SpringContextConfig;
import com.server.workordersystem.dto.*;
import com.server.workordersystem.entity.User;
import com.server.workordersystem.entity.WorkOrder;
import com.server.workordersystem.service.AdminService;
import com.server.workordersystem.service.impl.AdminServiceImpl;
import com.server.workordersystem.util.annotation.IsAdmin;
import com.server.workordersystem.util.http.CookieUtils;
import com.server.workordersystem.util.json.JsonResult;
import com.server.workordersystem.util.json.JsonResultFactory;
import com.server.workordersystem.util.json.JsonResultStateCode;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 全鸿润
 */
@Api
@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService = SpringContextConfig.getBean(AdminServiceImpl.class);

    /*
    管理员登录请求
     */
    @PostMapping("/login-admin")
    public JsonResult handleLoginAdmin(@RequestBody LoginMessage loginMessage) {

        String username = loginMessage.getUsername();
        String password = loginMessage.getPassword();
        String msg = adminService.handleLoginAdmin(username, password);
        JsonResult res = null;
        if (JsonResultStateCode.NOT_Admin.equals(msg)) {
            res = JsonResultFactory.buildJsonResult(JsonResultStateCode.USERNAME_WRONG, msg, null);
        } else if (JsonResultStateCode.PASSWORD_WRONG_DESC.equals(msg)) {
            res = JsonResultFactory.buildJsonResult(JsonResultStateCode.PASSWORD_WRONG, msg, null);
        } else if (JsonResultStateCode.SUCCESS_DESC.equals(msg)) {
            res = JsonResultFactory.buildSuccessResult();
        } else if (JsonResultStateCode.USER_IS_LOG_OUT_DESC.equals(msg)) {
            res = JsonResultFactory.buildJsonResult(JsonResultStateCode.USER_IS_LOG_OUT, msg, null);
        } else {
            res = JsonResultFactory.buildJsonResult(JsonResultStateCode.UNKNOWN_ERROR, msg, null);
        }
        return res;
    }


    @PostMapping("/auths")
    @IsAdmin
    public JsonResult handleAuthorize(@RequestBody TypeGroupListMeg typeGroupListMegs) {

        if ((typeGroupListMegs != null)) {

            Integer rows = adminService.auths(typeGroupListMegs.getUserTypeGroupMegs());
            if (rows != null) {
                if ((rows.equals(1))) {
                    return JsonResultFactory.buildSuccessResult();
                } else {
                    return JsonResultFactory.
                            buildJsonResult(
                                    JsonResultStateCode.OPERATION_IS_NOT_COMPLETED,
                                    JsonResultStateCode.OPERATION_IS_NOT_COMPLETED_DESC, null);
                }
            } else {
                return JsonResultFactory.buildFailureResult();
            }
        } else {
            return JsonResultFactory.buildFailureResult();
        }

    }

    /*
    修改用户权限和分组
     */
    @PostMapping("/auth")
    @IsAdmin
    public JsonResult handleAuthorize(@RequestBody ModifyUserPowerMessage message) {

        Integer rows = adminService.auth(message);
        if (rows == 1) {
            return JsonResultFactory.buildSuccessResult();
        } else if (rows == -1) {
            return JsonResultFactory.
                    buildJsonResult(
                            JsonResultStateCode.FAILED,
                            "该用户已是该组组长", null);
        } else if (rows == 0) {
            return JsonResultFactory.
                    buildJsonResult(
                            JsonResultStateCode.FAILED,
                            "该组已有组长", null);
        } else {
            return JsonResultFactory.buildFailureResult();
        }
    }

    /*
    重置密码
    */
    @PostMapping("/reset-password")
    @IsAdmin
    public JsonResult handleResetPassword(@RequestBody ResetPasswordMeg resetPasswordMeg) {

        Integer rows;
        rows = adminService.resetPw(resetPasswordMeg);
        if (rows != null) {
            if (rows == 1) {
                return JsonResultFactory.buildSuccessResult();
            } else if (rows == 0) {
                return JsonResultFactory.buildJsonResult(
                        JsonResultStateCode.FAILED,
                        "用户不存在", null);
            } else {
                return JsonResultFactory.buildFailureResult();
            }
        } else {
            return JsonResultFactory.buildFailureResult();
        }
    }

    /*
    删除用户
     */
    @PostMapping("/delete-users")
    @IsAdmin
    public JsonResult handleDeleteUser(@RequestBody UserListMsg message) {

        Integer rows = adminService.deleteUsersByUid(message.getUids());
        if (rows != null) {
            if (rows.equals(message.getUids().size())) {
                return JsonResultFactory.buildSuccessResult();
            } else {
                return JsonResultFactory.buildJsonResult(JsonResultStateCode.OPERATION_IS_NOT_COMPLETED, JsonResultStateCode.OPERATION_IS_NOT_COMPLETED_DESC, null);
            }
        } else {
            return JsonResultFactory.buildFailureResult();
        }
    }

    @PostMapping("/logout-users")
    @IsAdmin
    public JsonResult handleLogout(@RequestBody UserListMsg message) {
        Integer rows = adminService.updateUsersLogState(message.getUids(), true);
        if (rows != null) {
            if (rows.equals(message.getUids().size())) {
                return JsonResultFactory.buildSuccessResult();
            } else {
                return JsonResultFactory.buildJsonResult(JsonResultStateCode.OPERATION_IS_NOT_COMPLETED, JsonResultStateCode.OPERATION_IS_NOT_COMPLETED_DESC, null);
            }
        } else {
            return JsonResultFactory.buildFailureResult();
        }
    }

    @PostMapping("/login-users")
    @IsAdmin
    public JsonResult handleLogin(@RequestBody UserListMsg message) {
        Integer rows = adminService.updateUsersLogState(message.getUids(), false);
        if (rows != null) {
            if (rows.equals(message.getUids().size())) {
                return JsonResultFactory.buildSuccessResult();
            } else {
                return JsonResultFactory.buildJsonResult(JsonResultStateCode.OPERATION_IS_NOT_COMPLETED, JsonResultStateCode.OPERATION_IS_NOT_COMPLETED_DESC, null);
            }
        } else {
            return JsonResultFactory.buildFailureResult();
        }
    }

    /*
    获取所有用户信息
     */
    @GetMapping("/get-users")
    @IsAdmin
    public JsonResult handleGetUsers() {
        List<UserInfoMsg> userList = adminService.getAllUsers();
        if (userList != null) {
            if (userList.size() > 0) {
                return JsonResultFactory.buildJsonResult(
                        JsonResultStateCode.SUCCESS,
                        JsonResultStateCode.SUCCESS_DESC,
                        userList
                );
            } else {
                return JsonResultFactory.buildJsonResult(
                        JsonResultStateCode.NOT_FOUND,
                        JsonResultStateCode.NOT_FOUND_DESC,
                        null
                );
            }
        } else {
            return JsonResultFactory.buildFailureResult();
        }
    }

    /*
    创建用户
     */
    @PostMapping("/create-user")
    @IsAdmin
    public JsonResult handleCreate(@RequestBody NewUserMessage userMessage) {

        Integer rows;
        rows = adminService.createUser(userMessage);
        if (rows != null) {
            if (rows == 1) {
                return JsonResultFactory.buildSuccessResult();
            } else {
                return JsonResultFactory.buildFailureResult();
            }
        } else {
            return JsonResultFactory.buildFailureResult();
        }
    }

    /*
    获取工单
     */
    @GetMapping("/get-work-order")
    @IsAdmin
    public JsonResult handleGetWorkOrder() {
        List<WorkOrder> workOrders = adminService.getAllWorkOrder();
        if (workOrders != null) {
            if (workOrders.size() > 0) {
                return JsonResultFactory.buildJsonResult(
                        JsonResultStateCode.SUCCESS,
                        JsonResultStateCode.SUCCESS_DESC,
                        workOrders
                );
            } else {
                return JsonResultFactory.buildJsonResult(
                        JsonResultStateCode.NOT_FOUND,
                        JsonResultStateCode.NOT_FOUND_DESC,
                        null
                );
            }
        } else {
            return JsonResultFactory.buildFailureResult();
        }
    }

    /*
    分配工单给组
     */
    @PostMapping("/allocate-work-order")
    @IsAdmin
    public JsonResult handleAllocate(@RequestBody AllocateOrderMeg allocateOrderMeg) {

        Integer rows;
        rows = adminService.allocateOrder(allocateOrderMeg);
        if (rows != null) {
            if (rows == 1) {
                return JsonResultFactory.buildSuccessResult();
            } else if (rows == -1) {
                return JsonResultFactory.buildJsonResult(
                        JsonResultStateCode.FAILED,
                        "该工单已分配或待审核", null);
            } else if (rows == 0) {
                return JsonResultFactory.buildJsonResult(
                        JsonResultStateCode.FAILED,
                        "工单号不存在", null);
            } else {
                return JsonResultFactory.buildFailureResult();
            }
        } else {
            return JsonResultFactory.buildFailureResult();
        }
    }

    /*
    审核工单
     */
    @PostMapping("/verify-work-order")
    @IsAdmin
    public JsonResult handleVerifyOrder(@RequestBody VerifyOrderMeg verifyOrderMeg, HttpServletRequest request) {

        Integer rows;
        Integer uid = CookieUtils.parseInt(request.getCookies(), "uid");
        rows = adminService.updateVerifyOrder(verifyOrderMeg, uid);
        if (rows != null) {
            if (rows == 1) {
                return JsonResultFactory.buildSuccessResult();
            } else if (rows == -1) {
                return JsonResultFactory.buildJsonResult(
                        JsonResultStateCode.FAILED,
                        "该工单已审核", null);
            } else if (rows == 0) {
                return JsonResultFactory.buildJsonResult(
                        JsonResultStateCode.FAILED,
                        "工单号不存在", null);
            } else {
                return JsonResultFactory.buildFailureResult();
            }
        } else {
            return JsonResultFactory.buildFailureResult();
        }
    }

    /*
    关闭工单
     */
    @PostMapping("/close-work-order")
    @IsAdmin
    public JsonResult handleCloseOrder(@RequestBody OrderCloseMeg orderCloseMeg) {

        Integer rows;
        rows = adminService.closeOrder(orderCloseMeg);
        if (rows != null) {
            if (rows == 1) {
                return JsonResultFactory.buildSuccessResult();
            } else if (rows == -1) {
                return JsonResultFactory.buildJsonResult(
                        JsonResultStateCode.FAILED,
                        "完成时间早于创建时间", null);
            } else if (rows == 0) {
                return JsonResultFactory.buildJsonResult(
                        JsonResultStateCode.FAILED,
                        "工单号不存在", null);
            } else {
                return JsonResultFactory.buildFailureResult();
            }
        } else {
            return JsonResultFactory.buildFailureResult();
        }
    }

    /*
    查询分组成员
     */
    @PostMapping("/query-teammate")
    @IsAdmin
    public JsonResult handleQueryMate(@RequestBody GroupMemberMeg groupMemberMeg) {

        List<UserInfoMsg> userList = adminService.getGroupMember(groupMemberMeg);
        if (userList != null) {
            if (userList.size() > 0) {
                return JsonResultFactory.buildJsonResult(
                        JsonResultStateCode.SUCCESS,
                        JsonResultStateCode.SUCCESS_DESC,
                        userList
                );
            } else {
                return JsonResultFactory.buildJsonResult(
                        JsonResultStateCode.NOT_FOUND,
                        JsonResultStateCode.NOT_FOUND_DESC,
                        null
                );
            }
        } else {
            return JsonResultFactory.buildJsonResult(
                    JsonResultStateCode.NOT_FOUND,
                    JsonResultStateCode.NOT_FOUND_DESC,
                    null);
        }
    }

}
