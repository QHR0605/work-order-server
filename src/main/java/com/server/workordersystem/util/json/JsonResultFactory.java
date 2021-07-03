package com.server.workordersystem.util.json;

/**
 * 生成某个状态码对应的Json对象
 *
 * @author 全鸿润
 */
public class JsonResultFactory {

    public static JsonResult buildJsonResult(Integer stateCode, String message, Object body) {
        return new JsonResult(stateCode, message, body);
    }

    public static JsonResult buildSuccessResult() {
        return buildJsonResult(JsonResultStateCode.SUCCESS, JsonResultStateCode.SUCCESS_DESC, null);
    }

    public static JsonResult buildFailureResult() {
        return JsonResultFactory.buildJsonResult(JsonResultStateCode.FAILED, JsonResultStateCode.FAILED_DESC, null);
    }
}
