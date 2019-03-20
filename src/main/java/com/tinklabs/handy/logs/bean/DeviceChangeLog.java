package com.tinklabs.handy.logs.bean;

/**
 * 
 * @description: 设备
 * @copyright: Copyright (c) 2019
 * @company: tinklabs
 * @author: 曹友安
 * @version: 1.0
 * @date: 2019 Mar 15, 2019 6:11:00 PM
 */
public class DeviceChangeLog extends Log {

    private Integer userId;

    private String  barcode;

    private Integer deviceId;

    private Integer hotelId;

    private String  hotelRoomNumber;

    private String  status;

    private String  userName;

    private String  debug;

    private String  debugPackage;

    private String  serviceProvider;

    private String  simId;

    private String  countryCode;

    private String  phoneNumber;

    private String  version;

    private String  deviceSimId;

    private String  caller;

    private String  created;

    private String  modified;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelRoomNumber() {
        return hotelRoomNumber;
    }

    public void setHotelRoomNumber(String hotelRoomNumber) {
        this.hotelRoomNumber = hotelRoomNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDebug() {
        return debug;
    }

    public void setDebug(String debug) {
        this.debug = debug;
    }

    public String getDebugPackage() {
        return debugPackage;
    }

    public void setDebugPackage(String debugPackage) {
        this.debugPackage = debugPackage;
    }

    public String getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(String serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public String getSimId() {
        return simId;
    }

    public void setSimId(String simId) {
        this.simId = simId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDeviceSimId() {
        return deviceSimId;
    }

    public void setDeviceSimId(String deviceSimId) {
        this.deviceSimId = deviceSimId;
    }

    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

}
