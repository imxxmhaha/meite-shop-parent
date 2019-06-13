package com.mayikt.api.member.service;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.common.base.BaseResponse;
import com.mayikt.member.input.dto.UserLoginInpDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户登录接口服务
 */
@Api(tags = "用户登陆服务接口")
@FeignClient(value = "APP-MAYIKT-MEMBER")
public interface MemberLoginService {
	/**
	 * 用户登陆接口
	 * 
	 * @param userLoginInpDTO
	 * @return
	 */
	@PostMapping("/login")
	@ApiOperation(value = "会员用户登陆信息接口")
	BaseResponse<JSONObject> login(@RequestBody UserLoginInpDTO userLoginInpDTO);

	/**
	 * 删除登陆token
	 *
	 * @return
	 */
	@PostMapping("/delToken")
	@ApiOperation(value = "删除登陆token")
	BaseResponse<JSONObject> delToken(@RequestParam("token") String token);
}