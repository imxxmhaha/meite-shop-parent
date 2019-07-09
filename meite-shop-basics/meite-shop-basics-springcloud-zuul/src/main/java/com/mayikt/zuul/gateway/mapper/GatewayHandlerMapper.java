package com.mayikt.zuul.gateway.mapper;

import com.mayikt.zuul.gateway.mapper.entity.GatewayHandlerEntity;
import org.apache.ibatis.annotations.Select;


/**
 * 网关handler数据库访问
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
public interface GatewayHandlerMapper {

	/**
	 * 获取第一个GatewayHandler
	 * 
	 * @return
	 */
	@Select("SELECT  handler_name AS handlerName,handler_id AS handlerid ,prev_handler_id AS prevhandlerid ,next_handler_id AS nexthandlerid  ,is_open AS ISOPEN FROM gateway_handler WHERE is_open ='1' and prev_handler_id is null")
	public GatewayHandlerEntity getFirstGatewayHandler();

	@Select("SELECT  handler_name AS handlerName,handler_id AS handlerid ,prev_handler_id AS prevhandlerid ,next_handler_id AS nexthandlerid  ,is_open AS ISOPEN FROM gateway_handler WHERE is_open ='1' and handler_id=#{handlerId}")
	public GatewayHandlerEntity getByHandler(String handlerId);

}
