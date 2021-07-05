package com.server.workordersystem.controller;

import com.server.workordersystem.config.SpringContextConfig;
import com.server.workordersystem.dto.UserMessage;
import com.server.workordersystem.service.UserService;
import com.server.workordersystem.service.impl.UserServiceImpl;
import com.server.workordersystem.util.annotation.IsLogin;
import com.server.workordersystem.util.http.CookieUtils;
import com.server.workordersystem.util.json.JsonResult;
import com.server.workordersystem.util.json.JsonResultFactory;
import com.server.workordersystem.util.json.JsonResultStateCode;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 全鸿润
 */
@Api
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService = SpringContextConfig.getBean(UserServiceImpl.class);

    @GetMapping("/get-user-info")
    @IsLogin
    public JsonResult handleGetUserInfo(HttpServletRequest request) {
        Integer uid = CookieUtils.parseInt(request.getCookies(), "uid");
        UserMessage message = userService.getUserInfo(uid);

        if (message == null) {
            return JsonResultFactory.buildFailureResult();
        } else {
            return JsonResultFactory.buildJsonResult(
                    JsonResultStateCode.SUCCESS,
                    JsonResultStateCode.SUCCESS_DESC,
                    message
            );
        }

    }

    @PostMapping("update-user-info")
    @IsLogin
    public JsonResult handleUpdateUserInfo(@RequestBody UserMessage message, HttpServletRequest request) {
        Integer uid = CookieUtils.parseInt(request.getCookies(), "uid");

        Integer row = userService.updateUserInfo(message);

        if (row != null && row == 1) {
            return JsonResultFactory.buildSuccessResult();
        } else {
            return JsonResultFactory.buildFailureResult();
        }
    }
}
