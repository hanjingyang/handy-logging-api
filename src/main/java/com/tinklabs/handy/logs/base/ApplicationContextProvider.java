package com.tinklabs.handy.logs.base;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 
 * @description: spring 上下文管理
 * @copyright: Copyright (c) 2019
 * @company: tinklabs
 * @author: 曹友安
 * @version: 1.0
 * @date: 2019 Mar 15, 2019 4:19:10 PM
 */
@Component
public class ApplicationContextProvider implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextContainer.setApplicationContext(applicationContext);
    }

}
