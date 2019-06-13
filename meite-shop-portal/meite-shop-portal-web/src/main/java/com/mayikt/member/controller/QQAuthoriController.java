package com.mayikt.member.controller;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.api.member.service.MemberLoginService;
import com.mayikt.api.member.service.QQAuthoriService;
import com.mayikt.common.base.BaseResponse;
import com.mayikt.common.constants.Constants;
import com.mayikt.member.input.dto.UserLoginInpDTO;
import com.mayikt.member.vo.LoginVo;
import com.mayikt.web.base.BaseWebController;
import com.mayikt.web.bean.MeiteBeanUtils;
import com.mayikt.web.constants.WebConstants;
import com.mayikt.web.utils.CookieUtils;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * 
 * 
 * @description: QQ授权
 * @author: 97后互联网架构师-余胜军
 * @contact: QQ644064779、微信yushengjun644 www.mayikt.com
 * @date: 2019年1月3日 下午3:03:17
 * @version V1.0
 * @Copyright 该项目“基于SpringCloud2.x构建微服务电商项目”由每特教育|蚂蚁课堂版权所有，未经过允许的情况下，
 *            私自分享视频和源码属于违法行为。
 */
@Controller
@Slf4j
public class QQAuthoriController extends BaseWebController {
	@Autowired
	private QQAuthoriService qqAuthoriFeign;

	private static final String MB_QQ_QQLOGIN = "member/qqlogin";
	@Autowired
	private MemberLoginService memberLoginServiceFeign;
	/**
	 * 重定向到首页
	 */
	private static final String REDIRECT_INDEX = "redirect:/";

	/**
	 * 生成授权链接
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/qqAuth")
	public String qqAuth(HttpServletRequest request) {
		try {
			String authorizeURL = new Oauth().getAuthorizeURL(request);
			log.info("authorizeURL:{}", authorizeURL);
			return "redirect:" + authorizeURL;
		} catch (Exception e) {
			return ERROR_500_FTL;
		}
	}

	/**
	 * 
	 * @param code
	 * @return
	 */
	@RequestMapping("/qqLoginBack")
	public String qqLoginBack(String code, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {
		try {
			// 使用授权码获取accessToken
			AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
			if (accessTokenObj == null) {
				return ERROR_500_FTL;
			}
			String accessToken = accessTokenObj.getAccessToken();
			if (StringUtils.isEmpty(accessToken)) {
				return ERROR_500_FTL;
			}
			// 使用accessToken获取用户openid
			OpenID openIDObj = new OpenID(accessToken);
			String openId = openIDObj.getUserOpenID();
			if (StringUtils.isEmpty(openId)) {
				return ERROR_500_FTL;
			}
			// 使用openid 查询数据库是否已经关联账号信息
			BaseResponse<JSONObject> findByOpenId = qqAuthoriFeign.findByOpenId(openId);
			if (!isSuccess(findByOpenId)) {
				return ERROR_500_FTL;
			}
			//// 如果调用接口返回203 ,跳转到关联账号页面
			if (findByOpenId.getCode().equals(Constants.HTTP_RES_CODE_NOTUSER_203)) {
				// 页面需要展示 QQ头像
				UserInfo qzoneUserInfo = new UserInfo(accessToken, openId);
				UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
				if (userInfoBean == null) {
					return ERROR_500_FTL;
				}
				// 用户的QQ头像
				String avatarURL100 = userInfoBean.getAvatar().getAvatarURL100();
				request.setAttribute("avatarURL100", avatarURL100);
				// 需要将openid存入在session中
				httpSession.setAttribute(WebConstants.LOGIN_QQ_OPENID, openId);
				return MB_QQ_QQLOGIN;
			}
			// 如果能够查询到用户信息的话,直接自动登陆
			// 自动实现登陆
			JSONObject data = findByOpenId.getData();
			String token = data.getString("token");
			CookieUtils.setCookie(request, response, WebConstants.LOGIN_TOKEN_COOKIENAME, token);
			return REDIRECT_INDEX;

		} catch (Exception e) {
			return ERROR_500_FTL;
		}

	}

	@RequestMapping("/qqJointLogin")
	public String qqJointLogin(@ModelAttribute("loginVo") LoginVo loginVo, Model model, HttpServletRequest request,
							   HttpServletResponse response, HttpSession httpSession) {

		// 1.获取用户openid
		String qqOpenId = (String) httpSession.getAttribute(WebConstants.LOGIN_QQ_OPENID);
		if (StringUtils.isEmpty(qqOpenId)) {
			return ERROR_500_FTL;
		}

		// 2.将vo转换dto调用会员登陆接口
		UserLoginInpDTO userLoginInpDTO = MeiteBeanUtils.voToDto(loginVo, UserLoginInpDTO.class);
		userLoginInpDTO.setQqOpenId(qqOpenId);
		userLoginInpDTO.setLoginType(Constants.MEMBER_LOGIN_TYPE_PC);
		String info = webBrowserInfo(request);
		userLoginInpDTO.setDeviceInfor(info);
		BaseResponse<JSONObject> login = memberLoginServiceFeign.login(userLoginInpDTO);
		if (!isSuccess(login)) {
			setErrorMsg(model, login.getMsg());
			return MB_QQ_QQLOGIN;
		}
		// 3.登陆成功之后如何处理 保持会话信息 将token存入到cookie 里面 首页读取cookietoken 查询用户信息返回到页面展示
		JSONObject data = login.getData();
		String token = data.getString("token");
		CookieUtils.setCookie(request, response, WebConstants.LOGIN_TOKEN_COOKIENAME, token);
		return REDIRECT_INDEX;
	}

}
