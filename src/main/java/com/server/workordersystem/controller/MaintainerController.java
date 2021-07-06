package com.server.workordersystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.server.workordersystem.dto.WorkOrderMessage;
import com.server.workordersystem.entity.WorkOrder;
import com.server.workordersystem.service.MaintainerService;
import com.server.workordersystem.util.http.CookieUtils;
import com.server.workordersystem.util.json.JsonResult;
import com.server.workordersystem.util.json.JsonResultFactory;
import com.server.workordersystem.util.json.JsonResultStateCode;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
@Api
@RequestMapping("/maintainer")
public class MaintainerController {

    @Autowired
    private MaintainerService maintainerService;

    @PostMapping("/commit-order")
    public JsonResult handleCommitOrder(@RequestBody WorkOrderMessage message) {
        Integer row;
        row = maintainerService.insertNewWorkerOrder(message);
        if (row != null && row == 1) {
            return JsonResultFactory.buildSuccessResult();
        } else {
            return JsonResultFactory.buildFailureResult();
        }
    }

    @PostMapping("/recommit-order")
    public JsonResult handleRecommitOrder(@RequestBody WorkOrderMessage message) {
        Integer row;
        row = maintainerService.insertNewCommitLog(message);
        if (row != null && row == 1) {
            return JsonResultFactory.buildSuccessResult();
        } else {
            return JsonResultFactory.buildFailureResult();
        }
    }

    @PostMapping("/complete-order")
    public JsonResult handleCompleteOrder(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        Boolean completed = Boolean.valueOf(jsonObject.getString("completed"));
        Integer orderId = jsonObject.getInteger("orderId");
        Integer row = maintainerService.completeOrder(orderId, completed);

        if (row != null && row == 1) {
            return JsonResultFactory.buildSuccessResult();
        } else {
            return JsonResultFactory.buildFailureResult();
        }
    }

    @GetMapping("/get-orders")
    public JsonResult handleGetOrders(HttpServletRequest request) {

        Integer uid = CookieUtils.parseInt(request.getCookies(), "uid");
        List<WorkOrder> res;
        res = maintainerService.getOrders(uid);
        if (res == null) {
            return JsonResultFactory.buildFailureResult();
        } else if (res.size() > 0) {
            return JsonResultFactory.buildJsonResult(
                    JsonResultStateCode.SUCCESS,
                    JsonResultStateCode.SUCCESS_DESC,
                    res
            );
        } else {
            return JsonResultFactory.buildJsonResult(
                    JsonResultStateCode.NOT_FOUND,
                    JsonResultStateCode.NOT_FOUND_DESC, null);
        }
    }
}
