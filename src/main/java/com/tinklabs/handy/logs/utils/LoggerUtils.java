package com.tinklabs.handy.logs.utils;

public class LoggerUtils {

    static final String regex = "((?!0000)[0-9]{4}-((0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-8])|(0[13-9]|1[0-2])-(29|30)|(0[13578]|1[02])-31)|([0-9]{2}(0[48]|[2468][048]|[13579][26])|(0[48]|[2468][048]|[13579][26])00)-02-29)\\s(0[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1-5][0-9]):(0[0-9]|[0-5][0-9])";

    /**
     * 
     * @description: 时间验证
     * @copyright: Copyright (c) 2019
     * @company: tinklabs
     * @author: 曹友安
     * @version: 1.0
     * @date: 2019 Mar 20, 2019 2:35:20 PM
     * @param time
     * @return
     */
    public static boolean notTimeString(String time) {
        if (time.matches(regex)) {
            return false;
        }
        return true;
    }
}
