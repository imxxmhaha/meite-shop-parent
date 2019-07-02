package com.mayikt.zuul.gateway.build;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netflix.zuul.context.RequestContext;

/**
 * 网关权限Build
 * 
 * 
 * @description:
 * @author: 97后互联网架构师-余胜军
 * @contact: QQ644064779、微信yushengjun644 www.mayikt.com
 * @date: 2019年1月3日 下午3:03:17
 * @version V1.0
 * @Copyright 该项目“基于SpringCloud2.x构建微服务电商项目”由每特教育|蚂蚁课堂版权所有，未经过允许的情况下，
 *            私自分享视频和源码属于违法行为。
 */
public interface GatewayBuild {
	/**
	 * 黑名单拦截
	 */
	Boolean blackBlock(RequestContext ctx, String ipAddres, HttpServletResponse response);

	/**
	 * 参数验证
	 */
	Boolean toVerifyMap(RequestContext ctx, String ipAddres, HttpServletRequest request);

	/**
	 * api权限控制
	 * 
	 * @return
	 */
	Boolean apiAuthority(RequestContext ctx, HttpServletRequest request);
}
