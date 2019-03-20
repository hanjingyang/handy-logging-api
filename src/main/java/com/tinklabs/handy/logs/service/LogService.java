package com.tinklabs.handy.logs.service;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public abstract class LogService<T> {

    @SuppressWarnings("all")
    public Class<T> getClazz() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public List<T> getTmpCacheArr(int init) {
        return new ArrayList<T>(init);
    }

    public abstract boolean valid(T log);

    public abstract int saveLog(List<T> logs);
}
