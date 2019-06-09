package com.mayikt.api.weixin.service;

import com.mayikt.common.base.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description:微信服务注册码验证接口
 */
@Api(tags = "微信注册码验证码接口")
@FeignClient(value = "APP-MAYIKT-WEIXIN") //指定需要远程调用的服务名
public interface VerificaCodeService {

	/**
	 * 功能说明:根据手机号码验证码token是否正确
	 * 
	 * @return
	 */
	@ApiOperation(value = "根据手机号码验证码token是否正确")
	@GetMapping("/verificaWeixinCode")
	@ApiImplicitParams({
			// @ApiImplicitParam(paramType="header",name="name",dataType="String",required=true,value="用户的姓名",defaultValue="zhaojigang"),
			@ApiImplicitParam(paramType = "query", name = "phone", dataType = "String", required = true, value = "用户手机号码"),
			@ApiImplicitParam(paramType = "query", name = "weixinCode", dataType = "String", required = true, value = "微信注册码") })
	public BaseResponse<JSONObject> verificaWeixinCode(@RequestParam("phone") String phone,@RequestParam("weixinCode") String weixinCode);
}
