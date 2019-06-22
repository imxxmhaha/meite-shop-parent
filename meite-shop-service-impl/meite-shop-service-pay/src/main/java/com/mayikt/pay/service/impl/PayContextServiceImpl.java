package com.mayikt.pay.service.impl;

import com.mayikt.api.pay.service.PayContextService;
import com.mayikt.api.pay.service.PayMentTransacInfoService;
import com.mayikt.common.base.BaseApiService;
import com.mayikt.common.base.BaseResponse;
import com.mayikt.pay.dao.ChannelDao;
import com.mayikt.pay.entity.ChannelEntity;
import com.mayikt.pay.factory.StrategyFactory;
import com.mayikt.pay.out.dto.PayMentTransacDTO;
import com.mayikt.pay.strategy.PayStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;


@RestController
public class PayContextServiceImpl extends BaseApiService<JSONObject> implements PayContextService {
	@Autowired
	private ChannelDao channelDao;
	@Autowired
	private PayMentTransacInfoService payMentTransacInfoServiceFeign;


	@Override
	public BaseResponse<JSONObject> toPayHtml(String channelId, String payToken) {

		// 1.使用渠道id获取渠道信息 classAddres
		ChannelEntity pymentChannel = channelDao.selectBychannelId(channelId);
		if (pymentChannel == null) {
			return setResultError("没有查询到该渠道信息");
		}
		// 2.使用payToken获取支付参数
		BaseResponse<PayMentTransacDTO> tokenByPayMentTransac = payMentTransacInfoServiceFeign
				.tokenByPayMentTransac(payToken);
		if (!isSuccess(tokenByPayMentTransac)) {
			return setResultError(tokenByPayMentTransac.getMsg());
		}
		PayMentTransacDTO payMentTransacDTO = tokenByPayMentTransac.getData();
		// 3.执行具体的支付渠道的算法获取html表单数据 策略设计模式 使用java反射机制 执行具体方法
		String classAddres = pymentChannel.getClassAddres();
		PayStrategy payStrategy = StrategyFactory.getPayStrategy(classAddres);
		String payHtml = payStrategy.toPayHtml(pymentChannel, payMentTransacDTO);
		// 4.直接返回html
		JSONObject data = new JSONObject();
		data.put("payHtml", payHtml);
		return setResultSuccess(data);
	}

}
