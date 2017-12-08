package com.hwwz.medicalhistorysupervisor.domain;

import com.hwwz.medicalhistorysupervisor.enums.ResultEnum;

/**
 * @author: Aliweea
 * @date: 2017/12/1/001 19:17
 */
public class Result {

	/** error code */
	private Integer code;

	/** message */
	private String msg;

	/** concrete content */
	private Object data;

	public Result() { }

	public Result(ResultEnum resultEnum) {
		code = resultEnum.getCode();
		msg = resultEnum.getMessage();
	}

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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
