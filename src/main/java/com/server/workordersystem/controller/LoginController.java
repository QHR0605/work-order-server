package com.server.workordersystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.server.workordersystem.config.SpringContextConfig;
import com.server.workordersystem.dto.LoginMessage;
import com.server.workordersystem.dto.LoginUserType;
import com.server.workordersystem.dto.LoginUserTypeMeg;
import com.server.workordersystem.entity.User;
import com.server.workordersystem.service.LoginService;
import com.server.workordersystem.util.json.JsonResult;
import com.server.workordersystem.util.json.JsonResultFactory;
import com.server.workordersystem.util.json.JsonResultStateCode;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 全鸿润
 */
@Api
@RestController
@CrossOrigin
public class LoginController {

    private final LoginService loginService = SpringContextConfig.getBean("LoginServiceImpl");

    @PostMapping("/login")
    public JsonResult handleLogin(@RequestBody LoginMessage loginMessage) {

        String username = loginMessage.getUsername();
        String password = loginMessage.getPassword();
        String msg = loginService.handleLogin(username, password);
        JsonResult res = null;
        if (JsonResultStateCode.USERNAME_WRONG_DESC.equals(msg)) {
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

    @PostMapping("/update-password")
    public JsonResult handleUpdatePassword(@RequestBody JSONObject jsonObject) {
        String userName = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        String newPassword = jsonObject.getString("newPassword");

        User user = loginService.findUserByUsername(userName);
        if (user == null || !user.getPassword().equals(password)) {
            return JsonResultFactory.buildFailureResult();
        }
        Integer rows = loginService.updatePassword(userName, newPassword);
        if (rows != null && rows == 1) {
            return JsonResultFactory.buildSuccessResult();
        } else {
            return JsonResultFactory.buildFailureResult();
        }
    }

    @PostMapping("/login-type")
    public JsonResult handleLoginType(@RequestBody LoginUserTypeMeg loginUserTypeMeg) {

        LoginUserType loginUserType = loginService.handleLoginType(loginUserTypeMeg.getUsername());
        JsonResult res = null;
        if (loginUserType != null) {
            res = JsonResultFactory.buildJsonResult(JsonResultStateCode.SUCCESS, JsonResultStateCode.SUCCESS_DESC, loginUserType);
        } else {
            res = JsonResultFactory.buildJsonResult(JsonResultStateCode.NOT_FOUND, JsonResultStateCode.NOT_FOUND_DESC, null);
        }
        return res;
    }
}
