package com.hz.wsnIndoorBack.DTO;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Result<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	public int code; // 结果码 结果码为0表示无错误，其它则有错误

	public String message; // 结果详细信息

	public Boolean success;// 是否成功

	public Map<String, Object> map; // 结果对象消息封装

	public T obj; // 结果对象消息封装

	public static int Success = 0;

	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}

	/**
	 * 构造方法
	 * 
	 * @param code
	 * @param message
	 * @param map
	 */
	public Result() {
		this.code = Success;
		this.success = true;
		this.message = "操作成功";
	}

	public Result(T obj) {
		this.code = Success;
		this.success = true;
		this.message = "操作成功";
		this.obj = obj;
	}

	public Result(int code, boolean success, String message) {
		this.code = code;
		this.success = success;
		this.message = message;
	}

	public Result(int code, boolean success, String message, T object) {
		this.code = code;
		this.success = success;
		this.message = message;
		this.obj = object;
	}

	/**
	 * 存在success 的构造方法
	 * 
	 * @param code
	 * @param message
	 * @param map
	 * @param success
	 */
	public Result(int code, Boolean success, String message,
			Map<String, Object> map) {
		super();
		this.code = code;
		this.message = message;
		this.success = success;
		this.map = map;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getMap() {
		if (map == null) {
			map = new HashMap<String, Object>();
		}
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}
}
