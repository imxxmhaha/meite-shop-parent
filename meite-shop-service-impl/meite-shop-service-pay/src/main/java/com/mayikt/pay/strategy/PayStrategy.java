package com.mayikt.pay.strategy;

import com.mayikt.pay.entity.ChannelEntity;
import com.mayikt.pay.out.dto.PayMentTransacDTO;

/**
 * @description: 支付接口共同实现行为算法
 */
public interface PayStrategy {

	/**
	 * 
	 * @param pymentChannel
	 *            渠道参数
	 * @param payMentTransacDTO
	 *            支付参数
	 * @return
	 */
	public String toPayHtml(ChannelEntity pymentChannel, PayMentTransacDTO payMentTransacDTO);

}
