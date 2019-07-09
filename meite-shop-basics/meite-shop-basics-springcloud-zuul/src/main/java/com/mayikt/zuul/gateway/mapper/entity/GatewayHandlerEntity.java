package com.mayikt.zuul.gateway.mapper.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;

import lombok.Data;

/**
 * 
 * GatewayHandler
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
public class GatewayHandlerEntity implements Serializable, Cloneable {
	/** 主键ID */
	@Id
	private Long id;
	/** handler名称 */
	private String handlerName;
	/** handler主键id */
	private String handlerId;
	/** 下一个handler */
	private String nextHandlerId;
	/** 是否打开 */
	private Integer isOpen;
	/** 乐观锁 */
	private Integer revision;
	/** 创建人 */
	private String createdBy;
	/** 创建时间 */
	private Date createdTime;
	/** 更新人 */
	private String updatedBy;
	/** 更新时间 */
	private Date updatedTime;

}
