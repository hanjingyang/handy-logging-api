package com.tinklabs.handy.logs.enums;

public enum Errors {
	SUCCESS("0","success"),
	PARAMS_EMPTY("100001","parameter(s) can not be empty."),
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
