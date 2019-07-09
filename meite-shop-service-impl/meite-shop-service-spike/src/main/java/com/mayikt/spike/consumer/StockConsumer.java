package com.mayikt.spike.consumer;

import java.io.IOException;
import java.util.Map;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.spike.service.mapper.OrderMapper;
import com.mayikt.spike.service.mapper.SeckillMapper;
import com.mayikt.spike.service.mapper.entity.OrderEntity;
import com.mayikt.spike.service.mapper.entity.SeckillEntity;
import com.rabbitmq.client.Channel;

import lombok.extern.slf4j.Slf4j;

/**
 * 库存消费者
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
public class StockConsumer {
	@Autowired
	private SeckillMapper seckillMapper;
	@Autowired
	private OrderMapper orderMapper;

	@RabbitListener(queues = "modify_inventory_queue")
	@Transactional
	public void process(Message message, @Headers Map<String, Object> headers, Channel channel) throws IOException {
		String messageId = message.getMessageProperties().getMessageId();
		String msg = new String(message.getBody(), "UTF-8");
		log.info(">>>messageId:{},msg:{}", messageId, msg);
		JSONObject jsonObject = JSONObject.parseObject(msg);
		// 1.获取秒杀id
		Long seckillId = jsonObject.getLong("seckillId");
		SeckillEntity seckillEntity = seckillMapper.findBySeckillId(seckillId);
		if (seckillEntity == null) {
			log.warn("seckillId:{},商品信息不存在!", seckillId);
			return;
		}
		Long version = seckillEntity.getVersion();
		int inventoryDeduction = seckillMapper.inventoryDeduction(seckillId, version);
		if (!toDaoResult(inventoryDeduction)) {
			log.info(">>>seckillId:{}修改库存失败>>>>inventoryDeduction返回为{} 秒杀失败！", seckillId, inventoryDeduction);
			return;
		}
		// 2.添加秒杀订单
		OrderEntity orderEntity = new OrderEntity();
		String phone = jsonObject.getString("phone");
		orderEntity.setUserPhone(phone);
		orderEntity.setSeckillId(seckillId);
		orderEntity.setState(1l);
		int insertOrder = orderMapper.insertOrder(orderEntity);
		if (!toDaoResult(insertOrder)) {
			return;
		}
		log.info(">>>修改库存成功seckillId:{}>>>>inventoryDeduction返回为{} 秒杀成功", seckillId, inventoryDeduction);
	}

	// 调用数据库层判断
	public Boolean toDaoResult(int result) {
		return result > 0 ? true : false;
	}

}
