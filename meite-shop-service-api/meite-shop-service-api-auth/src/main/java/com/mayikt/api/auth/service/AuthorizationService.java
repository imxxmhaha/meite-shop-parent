package com.mayikt.api.auth.service;

import com.mayikt.common.base.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;

/**
 * 用户授权接口
 * 
 * 
 * @description:
 * @author: 97后互联网架构师-余胜军
 * @contact: QQ644064779、微信yushengjun644 www.mayikt.com
 * @date: 2019年1月3日 下午3:03:17
 * @version V1.0
 * @Copyright 该项目“基于SpringCloud2.x构建微服务电商项目”由每特教育|蚂蚁课堂版权所有，未经过允许的情况下，
 *            私自分享视频和源码属于违法行为。
 */
@FeignClient(value = "APP-MAYIKT-AUTH")
public interface AuthorizationService {
	/**
	 * 机构申请 获取appid 和appsecret
	 * 
	 * @return
	 */
	@GetMapping("/applyAppInfo")
	public BaseResponse<JSONObject> applyAppInfo(@RequestParam("appName") String appName);

	/*
	 * 使用appid 和appsecret密钥获取AccessToken
	 */
	@GetMapping("/getAccessToken")
	public BaseResponse<JSONObject> getAccessToken(@RequestParam("appId") String appId,
			@RequestParam("appSecret") String appSecret);

	/*
	 * 验证Token是否失效
	 */
	@GetMapping("/getAppInfo")
	public BaseResponse<JSONObject> getAppInfo(@RequestParam("accessToken") String accessToken);

}
