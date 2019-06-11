package com.tinklabs.handy.logs.controller;

import com.tinklabs.handy.logs.constants.TopicType;
import com.tinklabs.handy.logs.utils.Base64Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tinklabs.handy.base.exception.BaseErrors;
import com.tinklabs.handy.base.vo.ResultVO;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;

@RestController
@RequestMapping("/api/trace_log")
public class LogTraceController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Value("${log.trace.kafka.topic}")
	private String traceLogTopic;
	
	@Value("${log.trace.kafka.url}")
	private String restUrl;

	@Value("${log.trace.kafka.analysis.url}")
	private String analysisRestUrl;
	
	@Value("${log.trace.local}")
	private boolean localSave;

	/**
	 * @description: 通过客户端发送消息
	 * @company: tinklabs
	 * @author: pengtao
	 * @date: 2019 2019年3月25日 下午5:57:55
	 * @param
	 * @param data
	 * @return
	 */
	@RequestMapping( method = RequestMethod.POST)
	public ResultVO sendTraceLog( @RequestBody Map<String, Object> data) {
		logger.debug("in sendTraceLog.");
		if (data == null || data.isEmpty()) {
			// 如果提交参数为空，返回错误
			logger.debug("request body parameter empty.");
			return ResultVO.fail(BaseErrors.PARAMS_EMPTY);
		}
		// 转换消息为json文本
		String dataStr = JSONUtil.toJsonStr(data);
		logger.info("recived data: " + dataStr);
		// 发送消息文本
		try {
			kafkaTemplate.send(traceLogTopic, dataStr);
			return ResultVO.success(null);
		} catch (Exception e) {
			logger.error("sendTraceLog with kafka client error. ",e);
			return ResultVO.fail(BaseErrors.SYSTEM_EXCEPTION);
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
	@RequestMapping(value = "rest", method = RequestMethod.POST)
	public ResultVO sendRestTraceLog( @RequestBody Map<String, Object> data,
			@RequestParam("topic") String topic) {
		logger.debug("sendRestTraceLog topic=" + topic);
		if (data == null || data.isEmpty()) {
			// 如果提交参数为空，返回错误
			logger.debug("request body parameter empty.");
			return ResultVO.fail(BaseErrors.PARAMS_EMPTY);
		}
		// 转换消息为json文本
		String dataStr = JSONUtil.toJsonStr(data);
		logger.info("recived data: " + dataStr);
		//转换成服务器接收的格式 {"records":[{"value":"5bCK5pWs55qE5a6i5oi35oKo5aW977yMaGkga2Fma2EsIGknbSB4bmNoYWxs"}]}
		dataStr = Base64Utils.encode(dataStr);
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
			String url = restUrl;
			if (!StringUtils.isEmpty(topic) && TopicType.ANALYSIS_HANDY.equals(topic.trim())) {
				url = analysisRestUrl;
			}
			logger.info("rest url: " + url);
			if (StringUtils.isEmpty(url)) {
				logger.debug("rest url empty.");
				return ResultVO.fail(BaseErrors.BUSINESS_EXCEPTION);
			}

			result = HttpUtil.createPost(url).header("Content-Type", "application/vnd.kafka.v2+json")
					.timeout(2000).body(JSONUtil.toJsonStr(records)).execute();
			return ResultVO.success(result.body());
		} catch (Exception e) {
			logger.error("sendTraceLog with kafka rest-api error. ",e);
			return ResultVO.fail(BaseErrors.SYSTEM_EXCEPTION,e.getMessage());
		}

	}

}
