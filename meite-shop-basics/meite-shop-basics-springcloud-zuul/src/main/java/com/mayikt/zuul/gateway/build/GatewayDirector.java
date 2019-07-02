package com.mayikt.zuul.gateway.build;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.netflix.zuul.context.RequestContext;

/**
 * 链接Build
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
@Component
public class GatewayDirector {
	@Resource(name = "verificationBuild")
	private GatewayBuild gatewayBuild;

	// 责任链 装饰 外观 如何在实际项目中灵活运营设计模式
	/**
	 * 连接建造者
	 * 
	 * @param ctx
	 * @param ipAddres
	 * @param response
	 * @param request
	 */
	public void direcot(RequestContext ctx, String ipAddres, HttpServletResponse response, HttpServletRequest request) {
		// 1.黑名单
		Boolean blackBlock = gatewayBuild.blackBlock(ctx, ipAddres, response);
		if (!blackBlock) {
			return;
		}
//		// // 2.参数验证
//		Boolean verifyMap = gatewayBuild.toVerifyMap(ctx, ipAddres, request);
//		if (!verifyMap) {
//			return;
//		}
		// 3.验证accessToken
		Boolean apiAuthority = gatewayBuild.apiAuthority(ctx, request);
		if (!apiAuthority) {
			return;
		}

	}
	// 如何重构思路；装饰 、责任量 将每不操作存放到抽象工厂中，遍历工厂即可。 遍历遍历所有的实现
}
