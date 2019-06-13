package com.mayikt.member.service.impl;

import com.mayikt.api.member.service.QQAuthoriService;
import com.mayikt.common.base.BaseApiService;
import com.mayikt.common.base.BaseResponse;
import com.mayikt.common.constants.Constants;
import com.mayikt.common.core.utils.GenerateToken;
import com.mayikt.common.core.utils.RediskeyUtils;
import com.mayikt.member.dao.UserDao;
import com.mayikt.member.entity.UserEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;


@RestController
public class QQAuthoriServiceImpl extends BaseApiService<JSONObject> implements QQAuthoriService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private GenerateToken generateToken;

	@Override
	public BaseResponse<JSONObject> findByOpenId(String qqOpenId) {
		// 1.根据qqOpenId查询用户信息
		if (StringUtils.isEmpty(qqOpenId)) {
			return setResultError("qqOpenId不能为空!");
		}
		// 2.如果没有查询到 直接返回状态码203
		UserEntity userDo = userDao.findByOpenId(qqOpenId);
		if (userDo == null) {
			return setResultError(Constants.HTTP_RES_CODE_NOTUSER_203, "根据qqOpenId没有查询到用户信息");
		}
		// 3.如果能够查询到用户信息的话 返回对应用户信息token
		String keyPrefix = RediskeyUtils.getTokenPrefix(Constants.LOGIN_TYPE);
		Long userId = userDo.getUserId();
		String userToken = generateToken.createToken(keyPrefix, userId + "");
		// TODO 做唯一登录
		JSONObject data = new JSONObject();
		data.put("token", userToken);
		return setResultSuccess(data);
	}

}
