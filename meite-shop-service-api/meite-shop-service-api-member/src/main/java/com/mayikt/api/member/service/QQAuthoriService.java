package com.mayikt.api.member.service;

import com.mayikt.common.base.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * 
 * 
 * @description: 用户授权接口
 * @author: 97后互联网架构师-余胜军
 * @contact: QQ644064779、微信yushengjun644 www.mayikt.com
 * @date: 2019年1月3日 下午3:03:17
 * @version V1.0
 * @Copyright 该项目“基于SpringCloud2.x构建微服务电商项目”由每特教育|蚂蚁课堂版权所有，未经过允许的情况下，
 *            私自分享视频和源码属于违法行为。
 */
@FeignClient(value = "APP-MAYIKT-MEMBER")
public interface QQAuthoriService {
	/**
	 * 根据 openid查询是否已经绑定,如果已经绑定，则直接实现自动登陆
	 * 
	 * @param qqOpenId
	 * @return
	 */
	@RequestMapping("/findByOpenId")
	BaseResponse<JSONObject> findByOpenId(@RequestParam("qqOpenId") String qqOpenId);

}
