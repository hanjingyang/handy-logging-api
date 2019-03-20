package com.tinklabs.handy.logs.exception;

/**
 * 
 * @description: 业务异常
 * @copyright: Copyright (c) 2019
 * @company: tinklabs
 * @author: 曹友安
 * @version: 1.0
 * @date: 2019 Mar 18, 2019 11:25:58 AM
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 3687494332355644801L;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BusinessException(int code, String message, Throwable e) {
        super(message, e);
        this.code = code;
        this.message = message;
    }

    public BusinessException(Throwable e) {
        super(e);
    }

    private int    code;

    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
