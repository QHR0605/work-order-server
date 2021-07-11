package com.server.workordersystem.controller;

import com.server.workordersystem.dto.UserInfoMsg;
import com.server.workordersystem.dto.UserMessage;
import com.server.workordersystem.entity.WorkOrder;
import com.server.workordersystem.service.MaintainerService;
import com.server.workordersystem.service.MonitorService;
import com.server.workordersystem.util.http.CookieUtils;
import com.server.workordersystem.util.json.JsonResult;
import com.server.workordersystem.util.json.JsonResultFactory;
import com.server.workordersystem.util.json.JsonResultStateCode;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api
@CrossOrigin
@RestController
@RequestMapping("/monitor")
public class MonitorController {

    @Autowired
    private MonitorService monitorService;

    @GetMapping("/get-teammate")
    public JsonResult handleGetTeamMate(HttpServletRequest request) {

        Integer uid = CookieUtils.parseInt(request.getCookies(), "uid");
        List<UserInfoMsg> userList = monitorService.getMembers(uid);
        if (userList != null) {
            if (userList.size() > 0) {
                return JsonResultFactory.buildJsonResult(
                        JsonResultStateCode.SUCCESS,
                        JsonResultStateCode.SUCCESS_DESC,
                        userList
                );
            } else {
                return JsonResultFactory.buildJsonResult(
                        JsonResultStateCode.FAILED,
                        JsonResultStateCode.FAILED_DESC,
                        null
                );
            }
        } else {
            return JsonResultFactory.buildJsonResult(
                    JsonResultStateCode.NOT_FOUND,
                    "非组长",
                    null
                );
            }
    }

    @GetMapping("/get-unallocated-order")
    public JsonResult handleGetUnallocatedOrder(HttpServletRequest request) {

        Integer uid = CookieUtils.parseInt(request.getCookies(), "uid");
        List<WorkOrder> workOrderList = monitorService.getGroupOrder(uid);
        if (workOrderList != null) {
            if (workOrderList.size() > 0) {
                return JsonResultFactory.buildJsonResult(
                        JsonResultStateCode.SUCCESS,
                        JsonResultStateCode.SUCCESS_DESC,
                        workOrderList
                );
            } else {
                return JsonResultFactory.buildJsonResult(
                        JsonResultStateCode.FAILED,
                        JsonResultStateCode.FAILED_DESC,
                        null
                );
            }
        } else {
            return JsonResultFactory.buildJsonResult(
                    JsonResultStateCode.FAILED,
                   "非组长",
                    null
            );
        }
    }


}
