package com.tinklabs.handy.logs.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.tinklabs.handy.logs.base.SpringContextContainer;
import com.tinklabs.handy.logs.bean.Log;
import com.tinklabs.handy.logs.constants.LogType;
import com.tinklabs.handy.logs.exception.BusinessException;
import com.tinklabs.handy.logs.service.LogService;
import com.tinklabs.handy.logs.service.LogServiceFactory;

/**
 * @description: 日志内存队列(组合打包)
 * @copyright: Copyright (c) 2019
 * @company: tinklabs
 * @author: 曹友安
 * @version: 1.0
 * @date: 2019 Mar 15, 2019 3:47:45 PM
 */
public class LogQueue {

    private Logger                 logger        = LoggerFactory.getLogger(LogQueue.class);

    private LogService<Log>        logService;

    /**
     * 批量打包
     */
    private final static int       BATCH_COUNT   = 1;

    /**
     * 当前打包计时
     */
    private static int             CURRENT_COUNT = 0;

    /**
     * 打包用缓存
     */
    private final static List<Log> LOG_CACHE     = new ArrayList<>();

    private LogQueue(LogType logType) throws BusinessException {
        LogServiceFactory factory = SpringContextContainer.getApplicationContext().getBean(LogServiceFactory.class);
        /* 初始化执行类 */
        this.logService = factory.getLogService(logType);
        if (logService == null) {
            throw new BusinessException(1001, "the log type is not found!");
        }

        logger.info("instance queue:{}", logType);

        /* 启动消费 */
        consumer.execute(new Runnable() {

            @Override
            public void run() {
                exec();
            }
        });

        this.logType = logType;
    }

    /**
     * 存储日志队列集
     */
    private final static Map<LogType, LogQueue> LOGQUEUE = new HashMap<>();

    private LogType                             logType;

    public static LogQueue newInstance(LogType logType) {
        LogQueue logQueue = LOGQUEUE.get(logType);
        if (logQueue == null) {
            synchronized (logType) {
                logQueue = LOGQUEUE.get(logType);
                if (logQueue != null) {
                    return logQueue;
                }
                logQueue = new LogQueue(logType);
                LOGQUEUE.putIfAbsent(logType, logQueue);
            }
        }
        return logQueue;
    }

    /**
     * 存储日志队列
     */
    private BlockingQueue<Log> logs = new LinkedBlockingQueue<>();

    /**
     * @description: 添加日志到队列
     * @copyright: Copyright (c) 2019
     * @company: tinklabs
     * @author: 曹友安
     * @version: 1.0
     * @date: 2019 Mar 18, 2019 2:59:24 PM
     * @param log
     * @return
     * @throws BusinessException
     */
    public boolean push(JSONObject log) throws BusinessException {
        if (!logService.valid(log.toJavaObject(logService.getClazz()))) {
            throw new BusinessException(1001, "the log params is no valid.");
        }
        return logs.add((Log) log.toJavaObject(logService.getClazz()));
    }

    /**
     * 异步线程
     */
    private Executor consumer = new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

    /**
     * 异步入库线程
     */
    private Executor executor = new ThreadPoolExecutor(8, 16, 20, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

    @SuppressWarnings(value = "all")
    private void exec() {
        while (true) {
            try {
                Log log = logs.take();
                LOG_CACHE.add(log);
                /* 异步批量入库 */
                if (++CURRENT_COUNT >= BATCH_COUNT) {
                    List tmp = logService.getTmpCacheArr(CURRENT_COUNT);
                    tmp.addAll(LOG_CACHE);
                    LOG_CACHE.clear();
                    CURRENT_COUNT = 0;
                    executor.execute(new Runnable() {

                        @Override
                        public void run() {
                            int count = logService.saveLog(tmp);
                            logger.info("add batch log finish; log type:{}, count:{}", logType, count);
                        }
                    });
                }
            } catch (InterruptedException e) {
                logger.error("", e);
            }

        }
    }
}
