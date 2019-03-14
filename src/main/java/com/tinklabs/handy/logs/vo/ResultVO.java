package com.tinklabs.handy.logs.vo;

import java.io.Serializable;

import com.tinklabs.handy.logs.enums.Errors;

/**
 * @description: 返回对象VO
 * @company: tinklabs
 * @author: pengtao
 * @date: 2019 2019年3月13日 下午7:38:50
 */
public class ResultVO implements Serializable{

	/**
	* @fields
	*/
	private static final long serialVersionUID = 3315745598718701972L;

	/**
	 * 返回编码
	 */
	private String code;
	/**
	 * 返回消息
	 */
	private String msg;
	/**
	 * 返回数据对象
	 */
	private Object data;
	
	public static final ResultVO SUCCESS = new ResultVO(Errors.SUCCESS.getCode(),Errors.SUCCESS.getMsg(),null);
	
	public ResultVO() {}
	
	
	public ResultVO(String code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	/**
	 * @description: 构建成功返回对象
	 * @company: tinklabs
	 * @author: pengtao
	 * @date: 2019 2019年3月13日 下午7:43:48
	 * @param data
	 * @return
	 */
	public static ResultVO success(Object data) {
		return new ResultVO(Errors.SUCCESS.getCode(),Errors.SUCCESS.getMsg(),data);
	}
	
	/**
	 * @description: 构建成功返回对象
	 * @company: tinklabs
	 * @author: pengtao
	 * @date: 2019 2019年3月13日 下午7:43:48
	 * @param data
	 * @return
	 */
	public static ResultVO fail(String code,String msg) {
		return new ResultVO(code,msg,null);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
}
