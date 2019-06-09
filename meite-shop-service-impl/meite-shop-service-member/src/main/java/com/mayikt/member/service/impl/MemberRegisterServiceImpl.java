package com.mayikt.member.service.impl;

import com.mayikt.api.member.service.MemberRegisterService;
import com.mayikt.api.weixin.service.VerificaCodeService;
import com.mayikt.common.base.BaseApiService;
import com.mayikt.common.base.BaseResponse;
import com.mayikt.common.constants.Constants;
import com.mayikt.common.core.utils.MD5Util;
import com.mayikt.member.dao.UserDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import com.mayikt.member.entity.UserEntity;


@RestController
public class MemberRegisterServiceImpl extends BaseApiService<JSONObject> implements MemberRegisterService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private VerificaCodeService verificaCodeServiceFeign;

	@Transactional
	public BaseResponse<JSONObject> register(@RequestBody UserEntity userEntity, String registCode) {
		// 1.参数验证
		String userName = userEntity.getUserName();
		if (StringUtils.isEmpty(userName)) {
			return setResultError("用户名称不能为空!");
		}
		String mobile = userEntity.getMobile();
		if (StringUtils.isEmpty(mobile)) {
			return setResultError("手机号码不能为空!");
		}
		String password = userEntity.getPassword();
		if (StringUtils.isEmpty(password)) {
			return setResultError("密码不能为空!");
		}
		// 2.验证码注册码是否正确 暂时省略 会员调用微信接口实现注册码验证
		BaseResponse<JSONObject> verificaWeixinCode = verificaCodeServiceFeign.verificaWeixinCode(mobile, registCode);
		if (!verificaWeixinCode.getCode().equals(Constants.HTTP_RES_CODE_200)) {
			return setResultError(verificaWeixinCode.getMsg());
		}
		// 3.对用户的密码进行加密 // MD5 可以解密 暴力破解
		String newPassword = MD5Util.MD5(password);
		userEntity.setPassword(newPassword);
		// 4.调用数据库插入数据
		Integer row = userDao.insert(userEntity);
		return row > 0 ? setResultSuccess("注册成功") : setResultError("注册失败!");
	}

}
