package com.server.workordersystem.controller;
import com.server.workordersystem.config.SpringContextConfig;
import com.server.workordersystem.dto.UserPhoneMsg;
import com.server.workordersystem.entity.Contact;
import com.server.workordersystem.entity.ContactMsg;
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
import java.util.List;

/**
 * @author 全鸿润
 */
@Api
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService = SpringContextConfig.getBean(UserServiceImpl.class);

    @GetMapping("/get-contact")
    @IsLogin
    public JsonResult handleGetContact(HttpServletRequest request) {
        String username = CookieUtils.findCookie(request.getCookies(), "username").getValue();
        List<ContactMsg> contactList = userService.getContactList(username);
        if (contactList != null) {
            if (contactList.size() > 0) {
                return JsonResultFactory.buildJsonResult(
                        JsonResultStateCode.SUCCESS,
                        JsonResultStateCode.SUCCESS_DESC,
                        contactList
                );
            } else {
                return JsonResultFactory.buildJsonResult(
                        JsonResultStateCode.NOT_FOUND,
                        JsonResultStateCode.NOT_FOUND_DESC,
                        null
                );
            }
        }
        return JsonResultFactory.buildFailureResult();
    }

    @PostMapping("/add-contact")
    @IsLogin
    public JsonResult handleAddContact(@RequestBody Contact contact) {

        Integer row;
        row = userService.addContact(contact);
        if (row != null && row == 1) {
            return JsonResultFactory.buildSuccessResult();
        } else {
            return JsonResultFactory.buildFailureResult();
        }
    }

    @PostMapping("/del-contact")
    @IsLogin
    public JsonResult handleDelContact(@RequestBody Contact contact) {
        Integer row = userService.deleteContact(contact);
        if (row != null && row == 1) {
            return JsonResultFactory.buildSuccessResult();
        } else {
            return JsonResultFactory.buildFailureResult();
        }
    }

    @PostMapping("/update-phone")
    @IsLogin
    public JsonResult handleUpdatePhone(@RequestBody UserPhoneMsg msg) {

        Integer row = userService.updatePhone(msg);
        if (row != null && row == 1) {
            return JsonResultFactory.buildSuccessResult();
        } else {
            return JsonResultFactory.buildFailureResult();
        }
    }
}
