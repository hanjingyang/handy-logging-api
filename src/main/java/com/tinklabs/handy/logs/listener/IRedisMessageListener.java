package com.tinklabs.handy.logs.listener;

/**
 * @description: 统一监听redis作为消息队列的监听器接口
 * @company: tinklabs
 * @author: pengtao
 * @date: 2019 2019年3月22日 下午4:05:14
 */
public interface IRedisMessageListener {

	/**
	 * @description: 指定监听的队列名称
	 * @company: tinklabs
	 * @author: pengtao
	 * @date: 2019 2019年3月22日 下午4:06:55
	 * @return
	 */
	String getTopic();
	
	/**
	 * @description: 消息处理方法，此方法要拦截所有异常
	 * @company: tinklabs
	 * @author: pengtao
	 * @date: 2019 2019年3月22日 下午4:07:15
	 * @param message
	 */
	void handleMessage(String message);
}
