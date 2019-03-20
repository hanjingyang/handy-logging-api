package com.tinklabs.handy.logs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tinklabs.handy.logs.bean.DeviceOtaStatusContents;
import com.tinklabs.handy.logs.mapper.LogMapper;
import com.tinklabs.handy.logs.service.LogService;

@Service
public class DeviceOtaStatusContentLogServiceImpl extends LogService<DeviceOtaStatusContents> {

    @Autowired
    LogMapper logMapper;

    @Override
    public boolean valid(DeviceOtaStatusContents log) {
        if (log.getDeviceOtaStatusId() == null || StringUtils.isEmpty(log.getContent())) {
            return false;
        }
        return true;
    }

    @Override
    public int saveLog(List<DeviceOtaStatusContents> logs) {
        return logMapper.addDeviceOtaStatusContentLog(logs);
    }

}
