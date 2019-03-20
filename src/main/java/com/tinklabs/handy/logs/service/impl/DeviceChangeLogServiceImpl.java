package com.tinklabs.handy.logs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tinklabs.handy.logs.bean.DeviceChangeLog;
import com.tinklabs.handy.logs.mapper.LogMapper;
import com.tinklabs.handy.logs.service.LogService;
import com.tinklabs.handy.logs.utils.LoggerUtils;

@Service
public class DeviceChangeLogServiceImpl extends LogService<DeviceChangeLog> {

    @Autowired
    LogMapper logMapper;

    @Override
    public boolean valid(DeviceChangeLog log) {
        if (log.getDeviceId() == 0 || StringUtils.isEmpty(log.getCreated()) || LoggerUtils.notTimeString(log.getCreated()) || StringUtils.isEmpty(log.getModified())
                || LoggerUtils.notTimeString(log.getModified())) {
            return false;
        }

        return true;
    }

    @Override
    public int saveLog(List<DeviceChangeLog> logs) {
        return logMapper.addDeviceChangeLog(logs);
    }

}
