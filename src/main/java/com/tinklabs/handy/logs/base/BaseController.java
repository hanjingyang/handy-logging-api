package com.tinklabs.handy.logs.base;

import com.tinklabs.handy.logs.bean.Results;

public class BaseController {

    public Results outputSuccess(Object data) {
        Results results = new Results();
        results.setSuccess(true);
        results.setData(data);
        return results;
    }

    public Results outputFailure(String code, String message, Object data) {
        Results results = new Results();
        results.setSuccess(false);
        results.setCode(code);
        results.setMessage(message);
        results.setData(data);
        return results;
    }

    public Results outputFailure(String code, String message) {
        return outputFailure(code, message, null);
    }
}
