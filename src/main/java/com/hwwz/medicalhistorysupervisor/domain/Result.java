package com.hwwz.medicalhistorysupervisor.domain;

<<<<<<< HEAD
import com.hwwz.medicalhistorysupervisor.enums.ResultEnum;

=======
>>>>>>> 990e1f0dff9dafcbbe9b46f756d96fb37af66f2f
/**
 * @author: Aliweea
 * @date: 2017/12/1/001 19:17
 */
<<<<<<< HEAD
public class Result {
=======
public class Result <T>{
>>>>>>> 990e1f0dff9dafcbbe9b46f756d96fb37af66f2f

	/** error code */
	private Integer code;

	/** message */
	private String msg;

	/** concrete content */
<<<<<<< HEAD
	private Object data;

	public Result() { }

	public Result(ResultEnum resultEnum) {
		code = resultEnum.getCode();
		msg = resultEnum.getMessage();
	}
=======
	private T data;
>>>>>>> 990e1f0dff9dafcbbe9b46f756d96fb37af66f2f

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

<<<<<<< HEAD
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
=======
	public T getData() {
		return data;
	}

	public void setData(T data) {
>>>>>>> 990e1f0dff9dafcbbe9b46f756d96fb37af66f2f
		this.data = data;
	}
}
