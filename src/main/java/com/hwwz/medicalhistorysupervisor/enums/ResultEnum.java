package com.hwwz.medicalhistorysupervisor.enums;

/**
 * @author: Aliweea
 * @date: 2017/12/3/003 13:18
 */
public enum ResultEnum {
	UNKNOW_ERROR(-1,  "未知错误"),
	SUCCESS(0,  "成功"),
	VALID_ERROR(1, "验证失败"),
	FIND_ERROR(100,  "查找失败"),
	UPDATA_ERROR(101,  "更新失败"),
	DELETE_ERROR(102, "删除失败");

	private Integer code;

	private String message;

	ResultEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
