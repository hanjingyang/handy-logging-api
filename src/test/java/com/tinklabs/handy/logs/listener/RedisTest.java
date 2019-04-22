package com.tinklabs.handy.logs.listener;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.tinklabs.handy.logs.App;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class RedisTest {

	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	@Test
	public void testString() {
		String key = "testKey1";
		String value = "testValue1";
		stringRedisTemplate.opsForValue().set(key, value);
		assertEquals(value, stringRedisTemplate.opsForValue().get(key));
	}
	
	@Test
	public void testQueue() {
		for(int i=0; i<10 ; i++) {
			stringRedisTemplate.convertAndSend("testQueue1", "message-"+i);
		}
	}
	
}
