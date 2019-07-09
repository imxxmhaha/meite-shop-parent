package com.mayikt.zuul.gateway.filter;

import com.mayikt.zuul.gateway.handler.GatewayHandler;
import com.mayikt.zuul.gateway.handler.ResponsibilityClient;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 使用网关拦截客户端所有的请求
 */
@Component
@Slf4j
public class GatewayFilter extends ZuulFilter {

	@Autowired
	private ResponsibilityClient responsibilityClient;

	/**
	 * 请求之前拦截处理业务逻辑 建议将限制黑名单存放到redis或者携程的阿波罗
	 */
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		// 1.获取请求对象
		// 1.获取请求对象
		HttpServletRequest request = ctx.getRequest();
		HttpServletResponse response = ctx.getResponse();
		GatewayHandler handler = responsibilityClient.getHandler();
		handler.service(ctx, request, response);
		return null;
	}
	// public/api/api-pay/cratePayToken?payAmount=300222&orderId=2019010203501502&userId=644064

	/**
	 * 过滤参数
	 */
	private Map<String, List<String>> filterParameters(HttpServletRequest request, RequestContext ctx) {
		Map<String, List<String>> requestQueryParams = ctx.getRequestQueryParams();
		if (requestQueryParams == null) {
			requestQueryParams = new HashMap<>();
		}
		Enumeration em = request.getParameterNames();
		while (em.hasMoreElements()) {
			String name = (String) em.nextElement();
			String value = request.getParameter(name);
			ArrayList<String> arrayList = new ArrayList<>();
			// 将参数转化为html参数 防止xss攻击 < 转为&lt;
			arrayList.add(StringEscapeUtils.escapeHtml(value));
			requestQueryParams.put(name, arrayList);
		}
		return requestQueryParams;
	}

	@Override
	public boolean shouldFilter() {

		return true;
	}

	@Override
	public int filterOrder() {

		return 0;
	}

	/**
	 * 在请求之前实现拦截
	 */
	public String filterType() {

		return "pre";
	}

	/**
	 * 获取Ip地址
	 * 
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	// ip地址存在一个问题
	private void resultInsufficientAuthority(RequestContext ctx, String errorMsg) {
		baseResultErrorBase(ctx, 401, errorMsg);
	}

	private void resultError(RequestContext ctx, String errorMsg) {
		baseResultErrorBase(ctx, 500, errorMsg);
	}

	private void baseResultErrorBase(RequestContext ctx, int code, String errorMsg) {
		ctx.setResponseStatusCode(500);
		// 网关响应为false 不会转发服务
		ctx.setSendZuulResponse(false);
		ctx.setResponseBody(errorMsg);
	}

	// MD5 单向加密 不可逆 加盐
	// 客户端调用接口 add?userName=yushengjun&zhangsan=644 MD5
	// userName=yushengjun&zhangsan=644 ==签名=msfgfjsjsxjss
	// userName=yushengjun&zhangsan=644 名=msfgfjsjsxjss
	// msfgfjsjsxjss=msfgfjsjsxjss

	// 签名的目的是 为了防止数据被篡改 数据还是明文数据
	// 加密 RSA
}
