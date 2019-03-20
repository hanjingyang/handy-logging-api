package com.tinklabs.handy.logs.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.tinklabs.handy.logs.base.BaseController;
import com.tinklabs.handy.logs.bean.Results;
import com.tinklabs.handy.logs.cache.LogQueue;
import com.tinklabs.handy.logs.constants.LogType;
import com.tinklabs.handy.logs.exception.BusinessException;

@RestController
@RequestMapping(value = "/logs/api")
public class RecordController extends BaseController {

    @RequestMapping(value = "/record")
    public Results record(@RequestBody JSONObject log) {
        LogType logType = null;
        if (log == null || StringUtils.isEmpty(log.getString("logType")) || (logType = LogType.search(log.getIntValue("logType"))) == null) {
            return outputFailure("1000", "the log type not found!");
        }
        boolean status = false;
        try {
            status = LogQueue.newInstance(logType).push(log);
        } catch (BusinessException e) {
            return outputFailure(String.valueOf(e.getCode()), e.getMessage());
        }
        return outputSuccess(status);
    }

}
