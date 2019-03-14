package com.tinklabs.handy.logs.configuration;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.tinklabs.handy.logs.service.S3Service;

@Configuration
@EnableConfigurationProperties(S3Properties.class)
public class S3Configuration {
	
	@Autowired
	private S3Properties s3Properties;
	
	@ConfigurationProperties
	public S3Properties s3Properties() {
		return new S3Properties();
	}
	
	/**
	 * @description: 初始化S3Service
	 * @company: tinklabs
	 * @author: pengtao
	 * @date: 2019 2019年3月13日 下午7:36:35
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@Bean
	public S3Service s3Service() {
		AmazonS3 s3 = new AmazonS3Client(new BasicAWSCredentials(s3Properties.getAccesskey(), s3Properties.getSecretkey()));
		s3.setRegion(Region.getRegion(Regions.AP_SOUTHEAST_1)); // 此处根据自己的 s3 地区位置改变
        
        S3Service service = new S3Service(s3,s3Properties.getBucketname());
        return service;
	}
	
	/**
	 * @description: 配置允许上传的文件最大值
	 * @company: tinklabs
	 * @author: pengtao
	 * @date: 2019 2019年3月13日 下午7:36:24
	 * @return
	 */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory(); //允许上传的文件最大值 
        //设置文件大小 50 M
        DataSize size = DataSize.of(s3Properties.getMaxfilesize(), DataUnit.MEGABYTES);
        factory.setMaxFileSize(size); //KB,MB /// 设置总上传数据总大小 
        factory.setMaxRequestSize(size);
        
        return factory.createMultipartConfig();
    }
    
	
}
