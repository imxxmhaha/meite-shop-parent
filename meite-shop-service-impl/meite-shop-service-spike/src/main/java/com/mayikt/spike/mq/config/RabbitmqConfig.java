package com.mayikt.spike.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 
 * RabbitmqConfig 配置
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
public class RabbitmqConfig {

	// 添加修改库存队列
	public static final String MODIFY_INVENTORY_QUEUE = "modify_inventory_queue";
	// 交换机名称
	private static final String MODIFY_EXCHANGE_NAME = "modify_exchange_name";

	// 1.添加交换机队列
	@Bean
	public Queue directModifyInventoryQueue() {
		return new Queue(MODIFY_INVENTORY_QUEUE);
	}

	// 2.定义交换机
	@Bean
	DirectExchange directModifyExchange() {
		return new DirectExchange(MODIFY_EXCHANGE_NAME);
	}

	// 3.修改库存队列绑定交换机
	@Bean
	Binding bindingExchangeintegralDicQueue() {
		return BindingBuilder.bind(directModifyInventoryQueue()).to(directModifyExchange()).with("modifyRoutingKey");
	}

}
