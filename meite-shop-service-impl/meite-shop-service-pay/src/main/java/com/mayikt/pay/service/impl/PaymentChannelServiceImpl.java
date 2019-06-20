package com.mayikt.pay.service.impl;

import java.util.List;

import com.mayikt.api.pay.service.PaymentChannelService;
import com.mayikt.common.base.BaseApiService;
import com.mayikt.common.core.utils.mapper.MapperUtils;
import com.mayikt.pay.dao.ChannelDao;
import com.mayikt.pay.entity.ChannelEntity;
import com.mayikt.pay.out.dto.PaymentChannelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PaymentChannelServiceImpl extends BaseApiService<List<PaymentChannelDTO>>
		implements PaymentChannelService {
	@Autowired
	private ChannelDao paymentChannelMapper;

	@Override
	public List<PaymentChannelDTO> selectAll() {
		List<ChannelEntity> paymentChannelList = paymentChannelMapper.selectList(null);
		return MapperUtils.mapAsList(paymentChannelList, PaymentChannelDTO.class);
	}

}
