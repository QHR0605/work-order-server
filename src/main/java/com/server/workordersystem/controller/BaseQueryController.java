package com.server.workordersystem.controller;

import com.server.workordersystem.entity.Group;
import com.server.workordersystem.entity.WorkOrderType;
import com.server.workordersystem.service.BaseQueryService;
import com.server.workordersystem.util.json.JsonResult;
import com.server.workordersystem.util.json.JsonResultFactory;
import com.server.workordersystem.util.json.JsonResultStateCode;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api
@CrossOrigin
@RestController
@RequestMapping("/base")
public class BaseQueryController {

    @Autowired
    private BaseQueryService baseQueryService;


    @GetMapping("/get-order-types")
    public JsonResult handleGetOrderTypes() {

        List<WorkOrderType> res;
        res = baseQueryService.getWorkOrderTypes();
        if (res == null) {
            return JsonResultFactory.buildFailureResult();
        } else if (res.size() == 0) {
            return JsonResultFactory.buildJsonResult(
                    JsonResultStateCode.NOT_FOUND,
                    JsonResultStateCode.NOT_FOUND_DESC,
                    null
            );
        } else {
            return JsonResultFactory.buildJsonResult(
                    JsonResultStateCode.SUCCESS,
                    JsonResultStateCode.SUCCESS_DESC,
                    res
            );
        }
    }

    @GetMapping("/get-groups")
    public JsonResult handleGetGroups() {

        List<Group> res;
        res = baseQueryService.getGroups();
        if (res == null) {
            return JsonResultFactory.buildFailureResult();
        } else if (res.size() == 0) {
            return JsonResultFactory.buildJsonResult(
                    JsonResultStateCode.NOT_FOUND,
                    JsonResultStateCode.NOT_FOUND_DESC,
                    null
            );
        } else {
            return JsonResultFactory.buildJsonResult(
                    JsonResultStateCode.SUCCESS,
                    JsonResultStateCode.SUCCESS_DESC,
                    res
            );
        }
    }
}
