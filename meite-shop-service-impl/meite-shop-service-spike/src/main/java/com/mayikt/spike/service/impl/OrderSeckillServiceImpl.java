package com.mayikt.spike.service.impl;

import com.mayikt.api.spike.service.OrderSeckillService;
import com.mayikt.common.base.BaseApiService;
import com.mayikt.common.base.BaseResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import com.mayikt.spike.service.mapper.OrderMapper;
import com.mayikt.spike.service.mapper.entity.OrderEntity;

@RestController
public class OrderSeckillServiceImpl extends BaseApiService<JSONObject> implements OrderSeckillService {
	@Autowired
	private OrderMapper orderMapper;

	@Override
	public BaseResponse<JSONObject> getOrder(String phone, Long seckillId) {
		if (StringUtils.isEmpty(phone)) {
			return setResultError("手机号码不能为空!");
		}
		if (seckillId == null) {
			return setResultError("商品库存id不能为空!");
		}
		OrderEntity orderEntity = orderMapper.findByOrder(phone, seckillId);
		if (orderEntity == null) {
			return setResultError("正在排队中.....");
		}
		return setResultSuccess("恭喜你秒杀成功!");
	}

}
