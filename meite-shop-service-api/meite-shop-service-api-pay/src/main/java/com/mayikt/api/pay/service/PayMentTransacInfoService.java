package com.mayikt.api.pay.service;

import com.mayikt.common.base.BaseResponse;
import com.mayikt.pay.out.dto.PayMentTransacDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 
 * 
 * 
 * @description:PayMentTransacInfo 服务接口
 * @author: 97后互联网架构师-余胜军
 * @contact: QQ644064779、微信yushengjun644 www.mayikt.com
 * @date: 2019年1月3日 下午3:03:17
 * @version V1.0
 * @Copyright 该项目“基于SpringCloud2.x构建微服务电商项目”由每特教育|蚂蚁课堂版权所有，未经过允许的情况下，
 *            私自分享视频和源码属于违法行为。
 */
@FeignClient(value = "APP-MAYIKT-PAY")
public interface PayMentTransacInfoService {
	@GetMapping("/tokenByPayMentTransac")
	public BaseResponse<PayMentTransacDTO> tokenByPayMentTransac(@RequestParam("token") String token);
}
