package com.tinklabs.handy.logs.enums;

import com.tinklabs.handy.base.exception.IError;

/**
 * @description: 当前应用内定义的错误信息数据
 * @company: tinklabs
 * @author: pengtao
 * @date: 2019 2019年4月1日 下午2:46:08
 */
public enum Errors implements IError{
	
	//upload log file
	UPLOADED_FILE_ERROR("001001",""),
	AWS_SERVER_ERROR("001002","Amazon server status invalid."),
	UPLOADED_FILE_TYPE_ERROR("001003","upload file type not allowed.");
	
	

	private String code;
	
	private String msg;
	
	Errors(String code,String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
	
}
