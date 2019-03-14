package com.tinklabs.handy.logs.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

/**
 * @description: s3 上传与下载服务类
 * @company: tinklabs
 * @author: pengtao
 * @date: 2019 2019年3月13日 下午5:32:10
 */
public class S3Service {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * s3 客户端
	 */
	private AmazonS3 s3;
	/**
	 * 桶名称
	 */
	private String bucketName;

	public S3Service(AmazonS3 s3, String bucketName) {
		this.s3 = s3;
		this.bucketName = bucketName;
	}

	/**
	 * @description: 上传文件到s3
	 * @company: tinklabs
	 * @author: pengtao
	 * @date: 2019 2019年3月13日 下午6:12:04
	 * @param file           文件对象
	 * @param remoteFilePath 想要在s3服务器上创建的路径和文件名，如：folder1/test.xlsx
	 * @return 远程文件的下载链接
	 */
	public String uploadToS3(File file, String remoteFilePath) {
		try {
			s3.putObject(new PutObjectRequest(bucketName, remoteFilePath, file));
			// 构建下载链接
			GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(bucketName, remoteFilePath);
			URL url = s3.generatePresignedUrl(urlRequest);
			return url.toString();
		} catch (AmazonServiceException e) {
			logger.error("uploadToS3 error.", e);
		} catch (AmazonClientException e) {
			logger.error("uploadToS3 error.", e);
		}
		return null;
	}
	
	/**
	 * @description: 上传文件到s3
	 * @company: tinklabs
	 * @author: pengtao
	 * @date: 2019 2019年3月13日 下午7:00:26
	 * @param inputStream 文件流
	 * @param metadata 文件描述信息
	 * @param remoteFilePath 想要在s3服务器上创建的路径和文件名，如：folder1/test.xlsx
	 * @return 远程文件的下载链接
	 */
	public String uploadToS3(InputStream inputStream, ObjectMetadata metadata, String remoteFilePath) {
		try {
			s3.putObject(new PutObjectRequest(bucketName, remoteFilePath, inputStream, metadata));
			// 构建下载链接
			GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(bucketName, remoteFilePath);
			URL url = s3.generatePresignedUrl(urlRequest);
			return url.toString();
		} catch (AmazonServiceException e) {
			logger.error("uploadToS3 error.", e);
		} catch (AmazonClientException e) {
			logger.error("uploadToS3 error.", e);
		}
		return null;
	}

	/**
	 * @description: 从s3服务器上下载文件流
	 * @company: tinklabs
	 * @author: pengtao
	 * @date: 2019 2019年3月13日 下午6:13:02
	 * @param remoteFilePath 在s3服务器上的路径和文件名，如：folder1/test.xlsx
	 * @return 文件流二进制数组
	 */
	public byte[] downloadFromS3(String remoteFilePath) {
		try {
			// 从s3查询指定的文件
			S3Object o = s3.getObject(bucketName, remoteFilePath);
			// 把文件流复制到字节数组，并返回
			try (S3ObjectInputStream s3is = o.getObjectContent()) {
				return StreamUtils.copyToByteArray(s3is);
			} catch (IOException e) {
				logger.error("downloadFromS3 error.", e);
			}
		} catch (AmazonServiceException e) {
			logger.error("downloadFromS3 error.", e);
		}
		return null;
	}

}
