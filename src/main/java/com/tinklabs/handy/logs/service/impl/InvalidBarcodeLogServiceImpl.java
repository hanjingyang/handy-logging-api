package com.tinklabs.handy.logs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tinklabs.handy.logs.bean.InvalidBarcodes;
import com.tinklabs.handy.logs.mapper.LogMapper;
import com.tinklabs.handy.logs.service.LogService;
import com.tinklabs.handy.logs.utils.LoggerUtils;

@Service
public class InvalidBarcodeLogServiceImpl extends LogService<InvalidBarcodes> {

    @Autowired
    LogMapper logMapper;

    @Override
    public boolean valid(InvalidBarcodes log) {
        if (StringUtils.isEmpty(log.getBarcode()) || StringUtils.isEmpty(log.getUrl()) || StringUtils.isEmpty(log.getDomain()) || StringUtils.isEmpty(log.getMockAction()) || log.getStatus() == null
                || StringUtils.isEmpty(log.getCreated()) || StringUtils.isEmpty(log.getModified())) {
            return false;
        }
        if (LoggerUtils.notTimeString(log.getCreated()) || LoggerUtils.notTimeString(log.getModified())) {
            return false;
        }
        return true;
    }

    @Override
    public int saveLog(List<InvalidBarcodes> logs) {
        return logMapper.addInvaldBarcodeLog(logs);
    }
}
