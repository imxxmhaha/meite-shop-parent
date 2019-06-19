package com.mayikt.product.es.entity;

import org.elasticsearch.action.fieldstats.FieldStats.Date;
import org.elasticsearch.cluster.metadata.MappingMetaData.Timestamp;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;

/**
 * ES 索引和类型
 * 
 * @description:
 * @author: 97后互联网架构师-余胜军
 * @contact: QQ644064779、微信yushengjun644 www.mayikt.com
 * @date: 2019年1月3日 下午3:03:17
 * @version V1.0
 * @Copyright 该项目“基于SpringCloud2.x构建微服务电商项目”由每特教育|蚂蚁课堂版权所有，未经过允许的情况下，
 *            私自分享视频和源码属于违法行为。
 */
@Document(indexName = "goods", type = "goods")
@Data
public class ProductEntity {
	/** 主键ID */
	private Integer id;
	/** 类型ID */
	private Integer categoryId;
	/** 名称 */
	private String name;
	/** 小标题 */
	private String subtitle;
	/** 主图像 */
	private String mainImage;
	/** 小标题图像 */
	private String subImages;
	/** 描述 */
	private String detail;
	/** 商品规格 */
	private String attributeList;
	/** 价格 */
	private Double price;
	/** 库存 */
	private Integer stock;
	/** 状态 */
	private Integer status;

	/** 创建人 */
	private String createdBy;
	/** 创建时间 */
	private Date createdTime;

	/** 更新时间 */
	private Timestamp updatedTime;
}
