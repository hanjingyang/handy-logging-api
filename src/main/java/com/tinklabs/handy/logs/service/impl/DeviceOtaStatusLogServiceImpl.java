package com.tinklabs.handy.logs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tinklabs.handy.logs.bean.DeviceOtaStatus;
import com.tinklabs.handy.logs.mapper.LogMapper;
import com.tinklabs.handy.logs.service.LogService;

@Service
public class DeviceOtaStatusLogServiceImpl extends LogService<DeviceOtaStatus> {

    @Autowired
    LogMapper logMapper;

    @Override
    public boolean valid(DeviceOtaStatus log) {
        if (log.getDeviceId() == null || StringUtils.isEmpty(log.getStatus()) || StringUtils.isEmpty(log.getVersion())) {
            return false;
        }
        return true;
    }

    @Override
    public int saveLog(List<DeviceOtaStatus> logs) {
        return logMapper.addDeviceOtaStatusLog(logs);
    }

}
