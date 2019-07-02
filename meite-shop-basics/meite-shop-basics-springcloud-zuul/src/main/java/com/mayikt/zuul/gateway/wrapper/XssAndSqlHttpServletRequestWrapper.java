package com.mayikt.zuul.gateway.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 防止xss攻击
 */
public class XssAndSqlHttpServletRequestWrapper extends HttpServletRequestWrapper {

	public XssAndSqlHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);

	}

	@Override
	public String getParameter(String name) {
		String value = super.getParameter(name);
		if (!StringUtils.isEmpty(value)) {
			value = StringEscapeUtils.escapeJava(value);
		}
		return value;
	}
}