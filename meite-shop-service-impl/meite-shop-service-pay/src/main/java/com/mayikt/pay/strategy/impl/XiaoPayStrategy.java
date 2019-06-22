package com.mayikt.pay.strategy.impl;

import com.mayikt.pay.entity.ChannelEntity;
import com.mayikt.pay.out.dto.PayMentTransacDTO;
import com.mayikt.pay.strategy.PayStrategy;

public class XiaoPayStrategy implements PayStrategy {

	@Override
	public String toPayHtml(ChannelEntity pymentChannel, PayMentTransacDTO payMentTransacDTO) {

		return "小米支付from表单提交";
	}
	//com.mayikt.pay.strategy.impl.XiaoPayStrategy

}
