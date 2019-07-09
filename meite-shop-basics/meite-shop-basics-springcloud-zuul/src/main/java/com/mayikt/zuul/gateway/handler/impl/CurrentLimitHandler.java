package com.mayikt.zuul.gateway.handler.impl;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mayikt.common.core.utils.GenerateToken;
import com.mayikt.zuul.gateway.handler.GatewayHandler;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.context.RequestContext;

import lombok.extern.slf4j.Slf4j;

/**
 * 服务限流
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
@Slf4j
public class CurrentLimitHandler extends BaseHandler implements GatewayHandler {
	private RateLimiter rateLimiter = RateLimiter.create(1);

	@Autowired
	private GenerateToken generateToken;

	@Override
	public void service(RequestContext ctx, HttpServletRequest req, HttpServletResponse response) {
		log.info(">>>>>第一关,CurrentLimitHandler限流<<<<<<");
		// 1.用户限流频率设置 每秒中限制1个请求
		boolean tryAcquire = rateLimiter.tryAcquire(0, TimeUnit.SECONDS);
		if (!tryAcquire) {
			resultError(500, ctx, "现在抢购的人数过多，请稍等一下下哦！");
			return;
		}
		// 2.使用redis限制用户访问频率
		String seckillId = req.getParameter("seckillId");
		String seckillToken = generateToken.getListKeyToken(seckillId + "");
		if (StringUtils.isEmpty(seckillToken)) {
			log.info(">>>seckillId:{}, 亲，该秒杀已经售空，请下次再来!", seckillId);
			resultError(500, ctx, "亲，该秒杀已经售空，请下次再来!");
			return;
		}
		// 3.执行修改库存操作
		nextGatewayHandler.service(ctx, req, response);
	}

}
