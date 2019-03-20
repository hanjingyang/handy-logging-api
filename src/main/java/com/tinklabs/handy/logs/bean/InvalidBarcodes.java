package com.tinklabs.handy.logs.bean;

public class InvalidBarcodes extends Log {

    private String  barcode;

    private String  url;

    private String  domain;

    private String  mockAction;

    private String  cookieVersion;

    private String  cookiePackageName;

    private String  cookieAllversion;

    private String  cookieSim;

    private String  cookieModel;

    private String  cookieData;

    private Integer status;

    private String  created;

    private String  modified;

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getMockAction() {
        return mockAction;
    }

    public void setMockAction(String mockAction) {
        this.mockAction = mockAction;
    }

    public String getCookieVersion() {
        return cookieVersion;
    }

    public void setCookieVersion(String cookieVersion) {
        this.cookieVersion = cookieVersion;
    }

    public String getCookiePackageName() {
        return cookiePackageName;
    }

    public void setCookiePackageName(String cookiePackageName) {
        this.cookiePackageName = cookiePackageName;
    }

    public String getCookieAllversion() {
        return cookieAllversion;
    }

    public void setCookieAllversion(String cookieAllversion) {
        this.cookieAllversion = cookieAllversion;
    }

    public String getCookieSim() {
        return cookieSim;
    }

    public void setCookieSim(String cookieSim) {
        this.cookieSim = cookieSim;
    }

    public String getCookieModel() {
        return cookieModel;
    }

    public void setCookieModel(String cookieModel) {
        this.cookieModel = cookieModel;
    }

    public String getCookieData() {
        return cookieData;
    }

    public void setCookieData(String cookieData) {
        this.cookieData = cookieData;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
