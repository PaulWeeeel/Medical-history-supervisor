package com.hwwz.medicalhistorysupervisor.domain;

/**
 * @author: Aliweea
 * @date: 2017/12/1/001 19:17
 */
public class Result <T>{

	/** error code */
	private Integer code;

	/** message */
	private String msg;

	/** concrete content */
	private T data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
