package com.tinklabs.handy.logs.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TestRedisListener implements IRedisMessageListener{

	private static final Logger log = LoggerFactory.getLogger(TestRedisListener.class);
	
	public void handleMessage(String message) {
		log.info("receiveMessage: "+message);
	}

	@Override
	public String getTopic() {
		return "testQueue1";
	}
	
}
