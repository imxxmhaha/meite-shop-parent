package com.mayikt.auth.service.api.impl;

import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.mayikt.api.auth.service.AuthorizationService;
import com.mayikt.common.base.BaseApiService;
import com.mayikt.common.base.BaseResponse;
import com.mayikt.common.core.utils.GenerateToken;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.auth.mapper.AppInfoMapper;
import com.mayikt.auth.mapper.entity.MeiteAppInfo;
import com.mayikt.auth.utils.Guid;


@RestController
public class AuthorizationServiceImpl extends BaseApiService<JSONObject> implements AuthorizationService {
	@Autowired
	private AppInfoMapper appInfoMapper;
	@Autowired
	private GenerateToken generateToken;

	@Override
	public BaseResponse<JSONObject> applyAppInfo(String appName) {
		// 1.验证参数
		if (StringUtils.isEmpty(appName)) {
			return setResultError("机构名称不能为空!");
		}
		// 2.生成appid和appScrec
		Guid guid = new Guid();
		String appId = guid.getAppId();
		String appScrect = guid.getAppScrect();
		// 3.添加数据库中
		MeiteAppInfo meiteAppInfo = new MeiteAppInfo(appName, appId, appScrect);
		meiteAppInfo.setId(IdWorker.getId());
		int insertAppInfo = appInfoMapper.insertAppInfo(meiteAppInfo);
		if (!toDaoResult(insertAppInfo)) {
			return setResultError("申请失败!");
		}
		// 4.返回给客户端
		JSONObject data = new JSONObject();
		data.put("appId", appId);
		data.put("appScrect", appScrect);
		return setResultSuccess(data);
	}

	@Override
	public BaseResponse<JSONObject> getAccessToken(String appId, String appSecret) {
		// 使用appid+appSecret获取AccessToken
		// 1.参数验证
		if (StringUtils.isEmpty(appId)) {
			return setResultError("appId不能为空!");
		}
		if (StringUtils.isEmpty(appSecret)) {
			return setResultError("appSecret不能为空!");
		}
		// 2.使用appId+appSecret查询数据库
		MeiteAppInfo meiteAppInfo = appInfoMapper.selectByAppInfo(appId, appSecret);
		if (meiteAppInfo == null) {
			return setResultError("appId或者是appSecret错误");
		}
		// 3.获取应用机构信息 生成accessToken
		String dbAppId = meiteAppInfo.getAppId();
		String accessToken = generateToken.createToken("auth", dbAppId);
		JSONObject data = new JSONObject();
		data.put("accessToken", accessToken);
		return setResultSuccess(data);
	}

	public BaseResponse<JSONObject> getAppInfo(String accessToken) {
		// 1.验证参数
		if (StringUtils.isEmpty(accessToken)) {
			return setResultError("AccessToken cannot be empty ");
		}
		// 2.从redis中获取accessToken
		String appId = generateToken.getToken(accessToken);
		if (StringUtils.isEmpty(appId)) {
			return setResultError("accessToken  invalid");
		}
		// 3.使用appid查询数据库
		MeiteAppInfo meiteAppInfo = appInfoMapper.findByAppInfo(appId);
		if (meiteAppInfo == null) {
			return setResultError("AccessToken  invalid");
		}
		// 4.返回应用机构信息
		JSONObject data = new JSONObject();
		data.put("appInfo", meiteAppInfo);
		return setResultSuccess(data);
	}

	// 注意：每次生成新的accessToken的时候 ,之前accessToken能够使用吗?
}
