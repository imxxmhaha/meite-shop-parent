package com.mayikt.portal.controller;

import com.mayikt.api.member.service.MemberService;
import com.mayikt.common.base.BaseResponse;
import com.mayikt.common.view.ViewUtils;
import com.mayikt.member.output.dto.UserOutDTO;
import com.mayikt.web.base.BaseWebController;
import com.mayikt.web.constants.WebConstants;
import com.mayikt.web.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController extends BaseWebController {

	@Autowired
	private MemberService memberServiceFeign;
	/**
	 * 跳转到首页
	 * 
	 * @return
	 */
	@RequestMapping(value={"/","/index","/index.html"})
	//@RequestMapping(value={"/","/index"})
	public String index(HttpServletRequest request,Model model) {

		// 1.从cookie 中 获取 会员token
		String token = CookieUtils.getCookieValue(request, WebConstants.LOGIN_TOKEN_COOKIENAME, true);
		if (!StringUtils.isEmpty(token)) {
			// 2.调用会员服务接口,查询会员用户信息
			BaseResponse<UserOutDTO> userInfo = memberServiceFeign.getInfo(token);
			if (isSuccess(userInfo)) {
				UserOutDTO data = userInfo.getData();
				if (data != null) {
					String mobile = data.getMobile();
					// 对手机号码实现脱敏
					String desensMobile = mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
					model.addAttribute("desensMobile", desensMobile);
				}

			}

		}
		return ViewUtils.PAGE_INDEX_VIEW;
	}

	public static void main(String[] args) {
		String mobile = "13677233157";
		String desensMobile = mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
		System.out.println("desensMobile = " + desensMobile);
	}

}