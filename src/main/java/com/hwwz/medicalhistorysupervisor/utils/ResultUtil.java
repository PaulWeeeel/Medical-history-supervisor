package com.hwwz.medicalhistorysupervisor.utils;

import com.hwwz.medicalhistorysupervisor.domain.Result;
<<<<<<< HEAD
import com.hwwz.medicalhistorysupervisor.enums.ResultEnum;
=======
>>>>>>> 990e1f0dff9dafcbbe9b46f756d96fb37af66f2f

/**
 * @author: Aliweea
 * @date: 2017/12/1/001 19:22
 */
public class ResultUtil {

	public static Result success() {
		return success(null);
	}

	public static Result success(Object object) {
<<<<<<< HEAD
		Result result = new Result(ResultEnum.SUCCESS);
=======
		Result result = new Result();
		result.setCode(0);
		result.setMsg("success");
>>>>>>> 990e1f0dff9dafcbbe9b46f756d96fb37af66f2f
		result.setData(object);
		return result;
	}

	public static Result error(Integer code, String msg) {
		Result result = new Result();
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}
<<<<<<< HEAD

	public static Result error(ResultEnum resultEnum) {
		Result result = new Result(resultEnum);
		return result;
	}
=======
>>>>>>> 990e1f0dff9dafcbbe9b46f756d96fb37af66f2f
}
