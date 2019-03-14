package com.tinklabs.handy.logs.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description: S3配置属性
 * @company: tinklabs
 * @author: pengtao
 * @date: 2019 2019年3月13日 下午5:06:56
 */
@ConfigurationProperties(prefix = "aws.s3")
public class S3Properties {
	
	/**
	 * s3 accessey
	 */
	private String accesskey;
	/**
	 * s3 secretkey
	 */
	private String secretkey;
	/**
	 * s3 log 用到的 bucketname
	 */
	private String bucketname;
	/**
	 * s3 log 最大上传文件大小 maxfilesize
	 */
	private long maxfilesize;
	/**
	 * s3 log 上传允许的文件后缀名
	 */
	private String allowext;
	
	
	public String getAccesskey() {
		return accesskey;
	}
	public void setAccesskey(String accesskey) {
		this.accesskey = accesskey;
	}
	public String getSecretkey() {
		return secretkey;
	}
	public void setSecretkey(String secretkey) {
		this.secretkey = secretkey;
	}
	public String getBucketname() {
		return bucketname;
	}
	public void setBucketname(String bucketname) {
		this.bucketname = bucketname;
	}
	public long getMaxfilesize() {
		return maxfilesize;
	}
	public void setMaxfilesize(long maxfilesize) {
		this.maxfilesize = maxfilesize;
	}
	public String getAllowext() {
		return allowext;
	}
	public void setAllowext(String allowext) {
		this.allowext = allowext;
	}
	

}
