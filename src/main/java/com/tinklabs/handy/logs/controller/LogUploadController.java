package com.tinklabs.handy.logs.controller;

import java.text.MessageFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.tinklabs.handy.logs.configuration.S3Properties;
import com.tinklabs.handy.logs.enums.Errors;
import com.tinklabs.handy.logs.service.S3Service;
import com.tinklabs.handy.logs.vo.ResultVO;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

@RestController
@RequestMapping("/api/uploader")
public class LogUploadController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	// 远程文件夹命名模式 devicelog/20190313/test.zip
	private final String FOLDER_PATTERN = "devicelog/{0}/{1}";
	@Autowired
	private S3Properties s3Properties;

	/**
	 * s3文件服务
	 */
	@Autowired
	private S3Service s3Service;

	@RequestMapping("/upload")
	public ResultVO uploadLog(@RequestParam("file") MultipartFile multipartFile) {
		// 构建文件描述信息
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType(multipartFile.getContentType());
		metadata.setContentLength(multipartFile.getSize());

		// 读取上传文件名
		String fileName = multipartFile.getOriginalFilename();
		logger.debug("upload file name is: " + fileName);
		// 校验文件类型是否允许上传
		if (!checkFileType(fileName)) {
			return ResultVO.fail(Errors.UPLOADED_FILE_TYPE_ERROR.getCode(), Errors.UPLOADED_FILE_TYPE_ERROR.getMsg());
		}

		try {
			// 构建远程文件名并执行上传
			String url = s3Service.uploadToS3(multipartFile.getInputStream(), metadata, getRemotePath(fileName));
			// 如果上传服务返回空，即为上传失败
			if (url == null) {
				return ResultVO.fail(Errors.AWS_SERVER_ERROR.getCode(), Errors.AWS_SERVER_ERROR.getMsg());
			}
			// 否则为上传成功，将文件下载URL返回给前台
			logger.debug("upload file to s3 success.");
			return ResultVO.success(url);
		} catch (Exception e) {
			logger.error("get input stream from MultipartFile error.", e);
			return ResultVO.fail(Errors.UPLOADED_FILE_ERROR.getCode(), e.getMessage());
		}
	}

	private String getRemotePath(String fileName) {
		// 组装远程上传文件夹及名称
		return MessageFormat.format(FOLDER_PATTERN, DateUtil.format(new Date(), "yyyyMMdd"), fileName);
	}

	private boolean checkFileType(String fileName) {
		String allowedFileExt = s3Properties.getAllowext();
		// 如果没有配置允许的后缀，则全部允许
		if (StrUtil.isEmpty(allowedFileExt)) {
			return true;
		}
		// 如果上传文件没有后缀名，则不允许
		if (fileName.indexOf(".") < 0) {
			return false;
		}
		// 截取文件后缀名
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		// 拆分允许的文件后缀配置
		String[] exts = allowedFileExt.split(",");
		// 如果在许可列表里，通过
		for (String ext : exts) {
			if (ext.equals(fileExt)) {
				return true;
			}
		}
		// 否则不允许上传
		return false;
	}
}
