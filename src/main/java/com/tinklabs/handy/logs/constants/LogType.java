package com.tinklabs.handy.logs.constants;

/**
 * 
 * @description: 日志类型
 * @copyright: Copyright (c) 2019
 * @company: tinklabs
 * @author: 曹友安
 * @version: 1.0
 * @date: 2019 Mar 15, 2019 3:28:19 PM
 */
public enum LogType {
    DEVICE_CHANGE(1), EMAIL_QUEUE(2), INVALID_BARCODES(3), DEVICE_OTA_STATUS(4), DEVICE_OTA_STATUS_CONTENT(5), DEALER_CALL(6), IMPORT(7);

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private LogType(int type) {
        this.type = type;
    }

    public static LogType search(int type) {
        for (LogType logType : LogType.values()) {
            if (logType.type == type) {
                return logType;
            }
        }
        return null;
    }
}
