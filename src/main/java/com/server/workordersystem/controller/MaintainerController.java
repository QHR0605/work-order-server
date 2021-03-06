package com.server.workordersystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.server.workordersystem.dto.SolutionMessage;
import com.server.workordersystem.dto.UpdatedOrderMessage;
import com.server.workordersystem.dto.WorkOrderMessage;
import com.server.workordersystem.dto.WorkOrderWithFiles;
import com.server.workordersystem.service.MaintainerService;
import com.server.workordersystem.util.http.CookieUtils;
import com.server.workordersystem.util.idGenerator.IdGenerator;
import com.server.workordersystem.util.image.AliyunOssUtil;
import com.server.workordersystem.util.json.JsonResult;
import com.server.workordersystem.util.json.JsonResultFactory;
import com.server.workordersystem.util.json.JsonResultStateCode;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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

    @PostMapping("/create-draft")
    public JsonResult handleCreateDraft(@RequestBody WorkOrderMessage message) {
        Integer row;
        row = maintainerService.insertNewDraft(message);
        if (row != null && row == 1) {
            return JsonResultFactory.buildSuccessResult();
        } else {
            return JsonResultFactory.buildFailureResult();
        }
    }

    @PostMapping("/commit-draft")
    public JsonResult handleCommit(@RequestBody JSONObject object, HttpServletRequest request) {
        Integer orderId = object.getInteger("orderId");
        Integer uid = CookieUtils.parseInt(request.getCookies(), "uid");
        List<WorkOrderWithFiles> drafts = maintainerService.getDrafts(uid);
        int state = 0;
        for (WorkOrderWithFiles w : drafts
        ) {
            if (orderId.equals(w.getWorkOrder().getOrderId())) {
                if ("?????????".equals(w.getWorkOrder().getType())) {
                    state = 0;
                } else {
                    state = 2;
                }
                break;
            }
        }
        Integer row = maintainerService.updateOrderState(orderId, state);
        if (row != null && row == 1) {
            return JsonResultFactory.buildSuccessResult();
        } else {
            return JsonResultFactory.buildFailureResult();
        }
    }

    @PostMapping("/commit-solution")
    public JsonResult handleCommitSolution(@RequestBody SolutionMessage message) {
        Integer row = maintainerService.insertNewSolution(message);
        if (row != null && row == 1) {
            return JsonResultFactory.buildSuccessResult();
        } else {
            return JsonResultFactory.buildFailureResult();
        }
    }

    @PostMapping("/upload-image")
    public JsonResult handleUploadFile(MultipartFile file) {
        JsonResult jsonResult;
        try {
            byte[] bytes = file.getBytes();
            String key = IdGenerator.getId() + ".png";
            String url = AliyunOssUtil.uploadFile(bytes, key);
            jsonResult = JsonResultFactory.buildJsonResult(
                    JsonResultStateCode.SUCCESS,
                    JsonResultStateCode.SUCCESS_DESC,
                    url
            );
        } catch (IOException e) {
            e.printStackTrace();
            jsonResult = JsonResultFactory.buildFailureResult();
        }
        return jsonResult;
    }

    @GetMapping("/get-solutions")
    public JsonResult handleGetSolutions(HttpServletRequest request) {
        Integer uid = CookieUtils.parseInt(request.getCookies(), "uid");
        List<WorkOrderWithFiles> res;
        res = maintainerService.getHandledOrders(uid);
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


    @PostMapping("/complete-order")
    public JsonResult handleCompleteOrder(@RequestBody JSONObject jsonObject) {
        Integer orderId = jsonObject.getInteger("orderId");
        Integer state = jsonObject.getInteger("state");
        Integer row = maintainerService.updateOrderState(orderId, state);

        if (row != null && row == 1) {
            return JsonResultFactory.buildSuccessResult();
        } else {
            return JsonResultFactory.buildFailureResult();
        }
    }

    @GetMapping("/get-orders")
    public JsonResult handleGetOrders(HttpServletRequest request) {

        Integer uid = CookieUtils.parseInt(request.getCookies(), "uid");
        List<WorkOrderWithFiles> res;
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

    @GetMapping("/get-drafts")
    public JsonResult handleGetDraft(HttpServletRequest request) {

        Integer uid = CookieUtils.parseInt(request.getCookies(), "uid");
        List<WorkOrderWithFiles> res;
        res = maintainerService.getDrafts(uid);
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

    @PostMapping("update-order")
    public JsonResult handleUpdateOrder(@RequestBody UpdatedOrderMessage message) {

        Integer row = maintainerService.updateOrder(message);
        if (row != null && row == 1) {
            return JsonResultFactory.buildSuccessResult();
        } else {
            return JsonResultFactory.buildFailureResult();
        }
    }


}
