package com.mayikt.spike.service.mapper.entity;

import java.util.Date;

import lombok.Data;

/**
 * 商品库存
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
@Data
public class SeckillEntity {

	/**
	 * 库存id
	 * 
	 */
	private Long seckillId;
	/**
	 * 商品名称
	 */
	private String name;

	/**
	 * 库存数量
	 */
	private Long inventory;

	/**
	 * 开启时间
	 */
	private Date startTime;

	/**
	 * 结束时间
	 */
	private Date endTime;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 秒杀抢购
	 */
	private Long version;
}
