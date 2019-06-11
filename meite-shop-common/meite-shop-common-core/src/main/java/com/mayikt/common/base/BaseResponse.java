package com.mayikt.common.base;

import lombok.Data;

import java.io.Serializable;

/**
 * 
 * 
 * @description: 微服务接口统一返回码
 * @author: 97后互联网架构师-余胜军
 * @contact: QQ644064779、微信yushengjun644 www.mayikt.com
 * @date: 2019年1月3日 下午3:03:17
 * @version V1.0
 * @Copyright 该项目“基于SpringCloud2.x构建微服务电商项目”由每特教育|蚂蚁课堂版权所有，未经过允许的情况下，
 *            私自分享视频和源码属于违法行为。
 */
@Data
public class BaseResponse<T> implements Serializable {
	private static final long serialVersionUID = -8469420879227883186L;

	/**
	 * 返回码
	 */
	private String code = "001000";
	/**
	 * 消息
	 */
	private String msg;
	/**
	 * 返回
	 */
	private T data;


	public BaseResponse() {

	}

	public BaseResponse(ResultCode resultCode) {
		super();
		this.code = resultCode.code()+"";
		this.msg = resultCode.message();
	}

	public BaseResponse( String msg, T data) {
		super();
		this.msg = msg;
		this.data = data;
	}

	public BaseResponse(String code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}


	public static <T> BaseResponse ok(T data) {
		BaseResponse<T> commonResponse = new BaseResponse<>();
		commonResponse.setMsg("success");
		commonResponse.setData(data);
		return commonResponse;
	}

	public static <T> BaseResponse ok() {
		BaseResponse<T> commonResponse = new BaseResponse<>();
		commonResponse.setMsg("success");
		return commonResponse;
	}

	public static <T> BaseResponse error(String msg) {
		BaseResponse<T> commonResponse = new BaseResponse<>();
		commonResponse.setMsg(msg);
		commonResponse.setCode("9999");
		return commonResponse;
	}

}
