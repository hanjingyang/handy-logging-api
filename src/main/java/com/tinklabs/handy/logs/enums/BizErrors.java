package com.tinklabs.handy.logs.enums;

import com.tinklabs.handy.base.exception.IError;

/**
 * @description: 当前应用内定义的错误信息数据
 * @company: tinklabs
 * @author: pengtao
 * @date: 2019 2019年4月1日 下午2:46:08
 */
public enum BizErrors implements IError{
	
	//upload log file
	UPLOADED_FILE_ERROR("001001",""),
	AWS_SERVER_ERROR("001002","Amazon server status invalid."),
	UPLOADED_FILE_TYPE_ERROR("001003","upload file type not allowed."),
	LOT_TYPE_NOT_FOUND("1001","the log type is not found!"),
	PARAMETER_ILLEGAL("2000","parameter illegal"),
	;
	

	private String code;
	
	private String msg;
	
	BizErrors(String code,String msg) {
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
