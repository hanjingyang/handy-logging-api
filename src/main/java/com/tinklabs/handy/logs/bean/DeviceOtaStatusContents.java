package com.tinklabs.handy.logs.bean;

public class DeviceOtaStatusContents extends Log {

    private Integer deviceOtaStatusId;

    private String  content;

    public Integer getDeviceOtaStatusId() {
        return deviceOtaStatusId;
    }

    public void setDeviceOtaStatusId(Integer deviceOtaStatusId) {
        this.deviceOtaStatusId = deviceOtaStatusId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
