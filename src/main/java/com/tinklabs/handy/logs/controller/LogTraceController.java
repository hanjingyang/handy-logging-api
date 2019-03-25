package com.tinklabs.handy.logs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tinklabs.handy.logs.enums.Errors;
import com.tinklabs.handy.logs.vo.ResultVO;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;

@RestController
@RequestMapping("/api/trace_log")
public class LogTraceController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	private String restUrl = "http://ec2-13-250-5-192.ap-southeast-1.compute.amazonaws.com:8082/topics/backend2-hangpan-test";

	/**
	 * @description: 通过客户端发送消息
	 * @company: tinklabs
	 * @author: pengtao
	 * @date: 2019 2019年3月25日 下午5:57:55
	 * @param topic
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/{topic}", method = RequestMethod.POST)
	public ResultVO sendTraceLog(@PathVariable("topic") String topic, @RequestBody Map<String, Object> data) {
		logger.debug("in sendTraceLog.");
		if (data == null || data.isEmpty() || StrUtil.isEmpty(topic)) {
			// 如果提交参数为空，返回错误
			logger.debug("request body parameter empty.");
			return ResultVO.fail(Errors.PARAMS_EMPTY);
		}
		// 转换消息为json文本
		String dataStr = JSONUtil.toJsonStr(data);
		logger.info("recived data: " + dataStr);
		// 发送消息文本
		ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send(topic, dataStr);
		try {
			//读取结果
			String resultStr = result.get().toString();
			return ResultVO.success(resultStr);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultVO.fail("500", e.getMessage());
		}
	}

	/**
	 * @description: 通过rest api 发送消息
	 * @company: tinklabs
	 * @author: pengtao
	 * @date: 2019 2019年3月25日 下午5:58:32
	 * @param topic
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "rest/{topic}", method = RequestMethod.POST)
	public ResultVO sendRestTraceLog(@PathVariable("topic") String topic, @RequestBody Map<String, Object> data) {
		logger.debug("in sendTraceLog.");
		if (data == null || data.isEmpty() || StrUtil.isEmpty(topic)) {
			// 如果提交参数为空，返回错误
			logger.debug("request body parameter empty.");
			return ResultVO.fail(Errors.PARAMS_EMPTY);
		}
		// 转换消息为json文本
		String dataStr = JSONUtil.toJsonStr(data);
		logger.info("recived data: " + dataStr);
		//转换成服务器接收的格式 {"records":[{"value":"5bCK5pWs55qE5a6i5oi35oKo5aW977yMaGkga2Fma2EsIGknbSB4bmNoYWxs"}]} 
		//屏蔽了批量发送，一次只发送一条
		Map<String, List<Map<String, String>>> records = new HashMap<>();
		List<Map<String, String>> valueList = new ArrayList<>();
		records.put("records", valueList);

		Map<String, String> value = new HashMap<>();
		value.put("value", dataStr);

		valueList.add(value);

		// 发送消息文本
		HttpResponse result;
		try {
			result = HttpUtil.createPost(restUrl).header("Content-Type", "application/vnd.kafka.v2+json")
					.body(JSONUtil.toJsonStr(records)).execute();
			return ResultVO.success(result.body());
		} catch (Exception e) {
			e.printStackTrace();
			return ResultVO.fail("500", e.getMessage());
		}

	}

}
