package com.hwwz.medicalhistorysupervisor.utils;

import com.hwwz.medicalhistorysupervisor.domain.Result;
import com.hwwz.medicalhistorysupervisor.enums.ResultEnum;

/**
 * @author: Aliweea
 * @date: 2017/12/1/001 19:22
 */
public class ResultUtil {

	public static Result success() {
		return success(null);
	}

	public static Result success(Object object) {

		Result result = new Result(ResultEnum.SUCCESS);
		result.setData(object);
		return result;
	}

	public static Result error(Integer code, String msg) {
		Result result = new Result();
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}

	public static Result error(ResultEnum resultEnum) {
		Result result = new Result(resultEnum);
		return result;
	}
}
