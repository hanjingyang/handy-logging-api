package com.tinklabs.handy.logs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tinklabs.handy.logs.bean.DialerCallLog;
import com.tinklabs.handy.logs.mapper.LogMapper;
import com.tinklabs.handy.logs.service.LogService;

@Service
public class DialerCallLogServiceImpl extends LogService<DialerCallLog> {

    @Autowired
    LogMapper logMapper;

    @Override
    public boolean valid(DialerCallLog log) {
        if (log.getDeviceUserId() == null || StringUtils.isEmpty(log.getNumber()) || StringUtils.isEmpty(log.getCallType()) || StringUtils.isEmpty(log.getPhoneType()) || log.getDuration() == null) {
            return false;
        }
        return true;
    }

    @Override
    public int saveLog(List<DialerCallLog> logs) {
        return logMapper.addDialerCallLog(logs);
    }

}
