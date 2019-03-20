package com.tinklabs.handy.logs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tinklabs.handy.logs.bean.ImportLog;
import com.tinklabs.handy.logs.mapper.LogMapper;
import com.tinklabs.handy.logs.service.LogService;
import com.tinklabs.handy.logs.utils.LoggerUtils;

@Service
public class ImportLogServiceImpl extends LogService<ImportLog> {

    @Autowired
    LogMapper logMapper;

    @Override
    public boolean valid(ImportLog log) {
        if (StringUtils.isEmpty(log.getType()) || log.getUserId() == null || StringUtils.isEmpty(log.getS3FileKey()) || StringUtils.isEmpty(log.getData()) || StringUtils.isEmpty(log.getCreated())
                || StringUtils.isEmpty(log.getUpdated())) {
            return false;
        }
        if (LoggerUtils.notTimeString(log.getCreated()) || LoggerUtils.notTimeString(log.getUpdated())) {
            return false;
        }
        return true;
    }

    @Override
    public int saveLog(List<ImportLog> logs) {
        return logMapper.addImportLog(logs);
    }

}
