package com.tinklabs.handy.logs.aspects;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.tinklabs.handy.logs.bean.Results;

import cn.hutool.http.HttpUtil;

/**
 * 
 * @description: aop 统一日志切面
 * @copyright: Copyright (c) 2019
 * @company: tinklabs
 * @author: 曹友安
 * @version: 1.0
 * @date: 2019 Mar 26, 2019 2:42:00 PM
 */
@Aspect
@Order(1)
@Component
public class ControllerLogAspect {

    private final Logger logger    = LoggerFactory.getLogger(getClass());

    ThreadLocal<Long>    startTime = new ThreadLocal<>();

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping) || @annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void controllerLog() {
    }

    @Before("controllerLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) return;
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        String requestURL = request.getRequestURL().toString();
        String requestMethod = request.getMethod();
        String ipAddress = HttpUtil.getClientIP(request, "");
        String requestService = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        String requestParams = JSON.toJSONString(request.getParameterMap());
        logger.info("请求地址 : " + requestURL);
        logger.info("请求方法 : " + requestMethod);
        logger.info("IP : " + ipAddress);
        logger.info("请求服务 : " + requestService);
        logger.info("接口固定入参 : " + Arrays.toString(joinPoint.getArgs()));
        logger.info("接口固定入参 : " + requestParams);
    }

    @AfterReturning(returning = "ret", pointcut = "controllerLog()")
    public Results doAfterReturning(Results ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("出参 : " + JSON.toJSONString(ret));
        logger.info("响应时间 : " + (System.currentTimeMillis() - startTime.get()));
        return ret;
    }
}
