package com.tinklabs.handy.logs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinklabs.handy.logs.constants.LogType;
import com.tinklabs.handy.logs.service.impl.DeviceChangeLogServiceImpl;
import com.tinklabs.handy.logs.service.impl.DeviceOtaStatusContentLogServiceImpl;
import com.tinklabs.handy.logs.service.impl.DeviceOtaStatusLogServiceImpl;
import com.tinklabs.handy.logs.service.impl.DialerCallLogServiceImpl;
import com.tinklabs.handy.logs.service.impl.EmailQueueLogServiceImpl;
import com.tinklabs.handy.logs.service.impl.ImportLogServiceImpl;
import com.tinklabs.handy.logs.service.impl.InvalidBarcodeLogServiceImpl;

@Service
public class LogServiceFactory {

    @Autowired
    DeviceChangeLogServiceImpl           changeLogServiceImpl;

    @Autowired
    EmailQueueLogServiceImpl             emailQueueLogServiceImpl;

    @Autowired
    InvalidBarcodeLogServiceImpl         barcodeLogServiceImpl;

    @Autowired
    DeviceOtaStatusLogServiceImpl        deviceOtaStatusLogServiceImpl;

    @Autowired
    DeviceOtaStatusContentLogServiceImpl contentLogServiceImpl;

    @Autowired
    DialerCallLogServiceImpl             callLogServiceImpl;

    @Autowired
    ImportLogServiceImpl                 importLogServiceImpl;

    public LogService getLogService(LogType logType) {
        switch (logType.getType()) {
            case 1:
                return changeLogServiceImpl;
            case 2:
                return emailQueueLogServiceImpl;
            case 3:
                return barcodeLogServiceImpl;
            case 4:
                return deviceOtaStatusLogServiceImpl;
            case 5:
                return contentLogServiceImpl;
            case 6:
                return callLogServiceImpl;
            case 7:
                return importLogServiceImpl;
            default:
                break;
        }
        return null;
    }

}
