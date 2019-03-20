package com.tinklabs.handy.logs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tinklabs.handy.logs.bean.EmailQueueLog;
import com.tinklabs.handy.logs.mapper.LogMapper;
import com.tinklabs.handy.logs.service.LogService;
import com.tinklabs.handy.logs.utils.LoggerUtils;

@Service
public class EmailQueueLogServiceImpl extends LogService<EmailQueueLog> {

    @Autowired
    LogMapper logMapper;

    @Override
    public boolean valid(EmailQueueLog log) {
        if (StringUtils.isEmpty(log.getFrom()) || StringUtils.isEmpty(log.getTo()) || StringUtils.isEmpty(log.getSubject()) || StringUtils.isEmpty(log.getConfig())
                || StringUtils.isEmpty(log.getData()) || StringUtils.isEmpty(log.getResponse()) || StringUtils.isEmpty(log.getError()) || log.getIsFailed() == null || log.getIsDelivered() == null
                || log.getIsOpened() == null || log.getTried() == null || StringUtils.isEmpty(log.getSent()) || StringUtils.isEmpty(log.getStatus()) || StringUtils.isEmpty(log.getCreated())
                || StringUtils.isEmpty(log.getUpdated())) {
            return false;
        }
        if (LoggerUtils.notTimeString(log.getCreated()) || LoggerUtils.notTimeString(log.getUpdated())) {
            return false;
        }
        return true;
    }

    @Override
    public int saveLog(List<EmailQueueLog> logs) {
        return logMapper.addEmailQueueLog(logs);
    }
}
