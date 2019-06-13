package com.mayikt.member.service.impl;

import com.mayikt.api.member.service.MemberRegisterService;
import com.mayikt.api.weixin.service.VerificaCodeService;
import com.mayikt.common.base.BaseApiService;
import com.mayikt.common.base.BaseResponse;
import com.mayikt.common.constants.Constants;
import com.mayikt.common.core.utils.MD5Util;
import com.mayikt.common.core.utils.MiteBeanUtils;
import com.mayikt.member.dao.UserDao;
import com.mayikt.member.entity.UserEntity;
import com.mayikt.member.input.dto.UserInpDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;



@RestController
@Slf4j
public class MemberRegisterServiceImpl extends BaseApiService<JSONObject> implements MemberRegisterService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private VerificaCodeService verificaCodeServiceFeign;

	@Transactional
	public BaseResponse<JSONObject> register(@RequestBody UserInpDTO userInpDTO, String registCode) {
		// 1.参数验证
		//String userName = userInpDTO.getUserName();
		//if (StringUtils.isEmpty(userName)) {
		//	return setResultError("用户名称不能为空!");
		//}
		String mobile = userInpDTO.getMobile();
		if (StringUtils.isEmpty(mobile)) {
			return setResultError("手机号码不能为空!");
		}
		String password = userInpDTO.getPassword();
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
		userInpDTO.setPassword(newPassword);
		UserEntity userEntity = MiteBeanUtils.E2T(userInpDTO, UserEntity.class);
		// 4.调用数据库插入数据
		try {
			Integer row = userDao.insert(userEntity);
			return row > 0 ? setResultSuccess("注册成功") : setResultError("注册失败!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("插入数据库异常");
			return setResultError("注册失败!");
		}
	}

}
