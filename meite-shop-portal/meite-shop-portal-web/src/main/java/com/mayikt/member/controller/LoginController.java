package com.mayikt.member.controller;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.api.member.service.MemberLoginService;
import com.mayikt.common.base.BaseResponse;
import com.mayikt.common.constants.Constants;
import com.mayikt.common.view.ViewUtils;
import com.mayikt.member.input.dto.UserLoginInpDTO;
import com.mayikt.member.vo.LoginVo;
import com.mayikt.web.base.BaseWebController;
import com.mayikt.web.bean.MeiteBeanUtils;
import com.mayikt.web.constants.WebConstants;
import com.mayikt.web.utils.CookieUtils;
import com.mayikt.web.utils.RandomValidateCodeUtil;
import lombok.extern.slf4j.Slf4j;
import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.UserAgent;
import nl.bitwalker.useragentutils.Version;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * 
 * 
 * @description:登陆请求
 * @author: 97后互联网架构师-余胜军
 * @contact: QQ644064779、微信yushengjun644 www.mayikt.com
 * @date: 2019年1月3日 下午3:03:17
 * @version V1.0
 * @Copyright 该项目“基于SpringCloud2.x构建微服务电商项目”由每特教育|蚂蚁课堂版权所有，未经过允许的情况下，
 *            私自分享视频和源码属于违法行为。
 */
@Controller
@Slf4j
public class LoginController extends BaseWebController {


	@Autowired
	private MemberLoginService memberLoginServiceFeign;
	/**
	 * 跳转页面
	 * 
	 * @return
	 */
	@GetMapping(value={"/login","/login.html"})
	public String getLogin() {
		return ViewUtils.PAGE_LOGIN_VIEW;
	}

	/**
	 * 接受请求参数
	 * 
	 * @return
	 */
	@PostMapping("/login")
	public String postLogin(@ModelAttribute("loginVo") @Validated LoginVo loginVo, BindingResult bindingResult, Model model, HttpServletRequest request,
							HttpServletResponse response, HttpSession httpSession) {
		// 1.接受表单参数(验证码) 创建对象接受vo do dto
		if (bindingResult.hasErrors()) {
			// 如果参数由错误的话  获取第一个错误
			String errorMsg = bindingResult.getFieldError().getDefaultMessage();
			setErrorMsg(model,errorMsg);
			log.error(errorMsg);
			return ViewUtils.PAGE_LOGIN_VIEW;
		}


		// 2.图形验证码判断
		String graphicCode = loginVo.getGraphicCode();
		if (!RandomValidateCodeUtil.checkVerify(graphicCode, httpSession)) {
			setErrorMsg(model, "图形验证码不正确!");
			return ViewUtils.PAGE_LOGIN_VIEW;
		}

		// 3.将vo转换为dto
		UserLoginInpDTO voToDto = MeiteBeanUtils.voToDto(loginVo, UserLoginInpDTO.class);
		voToDto.setLoginType(Constants.MEMBER_LOGIN_TYPE_PC);
		String info = webBrowserInfo(request);
		voToDto.setDeviceInfor(info);
		BaseResponse<JSONObject> login = memberLoginServiceFeign.login(voToDto);
		if (!isSuccess(login)) {
			setErrorMsg(model, login.getMsg());
			return ViewUtils.PAGE_LOGIN_VIEW;
		}
		// 4.将token存入到cookie中
		JSONObject data = login.getData();
		String token = data.getString("token");
		CookieUtils.setCookie(request, response, WebConstants.LOGIN_TOKEN_COOKIENAME, token,true);
		// 登陆成功后，跳转到首页
		// 登陆成功后，跳转到首页
		return ViewUtils.REDIRECT_INDEX;
	}

	/**
	 * 退出登录
	 * @param request
	 * @return
	 */
	@RequestMapping("/exit")
	public String exit(HttpServletRequest request) {
		// 1. 从cookie中获取token
		String token = CookieUtils.getCookieValue(request, WebConstants.LOGIN_TOKEN_COOKIENAME, true);
		if (!StringUtils.isEmpty(token)) {
			BaseResponse<JSONObject> delToken = memberLoginServiceFeign.delToken(token);
			if (isSuccess(delToken)) {
				return ViewUtils.REDIRECT_INDEX;
			}
		}

		return ERROR_500_FTL;
	}


}
