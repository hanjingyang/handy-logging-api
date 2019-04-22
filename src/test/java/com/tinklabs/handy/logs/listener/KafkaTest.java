package com.tinklabs.handy.logs.listener;

import org.junit.Test;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;

public class KafkaTest {

	@Test
	public void testRest(){
		HttpResponse result = HttpUtil.createPost("http://ec2-13-250-5-192.ap-southeast-1.compute.amazonaws.com:8082/topics/backend2-hangpan-test").
				header("Content-Type", "application/vnd.kafka.v2+json").body("{\"records\":[{\"value\":\"{\\\"name\\\": \\\"hangpan-offset-4\\\"}\"}]}").execute();
		System.out.println(result.body());
	}
}
