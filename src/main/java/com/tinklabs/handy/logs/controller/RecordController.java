package com.tinklabs.handy.logs.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.tinklabs.handy.base.exception.BusinessException;
import com.tinklabs.handy.base.vo.ResultVO;
import com.tinklabs.handy.logs.cache.LogQueue;
import com.tinklabs.handy.logs.constants.LogType;
import com.tinklabs.handy.logs.enums.BizErrors;

@RestController
@RequestMapping(value = "/api/logs/")

public class RecordController  {


    @RequestMapping(value = "/record")
    public ResultVO record(@RequestBody JSONObject log) {
        LogType logType = null;
        if (log == null || StringUtils.isEmpty(log.getString("logType")) || (logType = LogType.search(log.getIntValue("logType"))) == null) {
            return ResultVO.fail(BizErrors.LOT_TYPE_NOT_FOUND);
        }
        boolean status = false;
        try {
            status = LogQueue.newInstance(logType).push(log);
        } catch (BusinessException e) {
            return ResultVO.fail(e.getError(), e.getMessage());
        }
        return ResultVO.success(status);
    }
}
