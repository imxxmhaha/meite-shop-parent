package com.mayikt.member.input.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * 
 * 
 * @description: 用户输入dto
 * @author: 97后互联网架构师-余胜军
 * @contact: QQ644064779、微信yushengjun644 www.mayikt.com
 * @date: 2019年1月3日 下午3:03:17
 * @version V1.0
 * @Copyright 该项目“基于SpringCloud2.x构建微服务电商项目”由每特教育|蚂蚁课堂版权所有，未经过允许的情况下，
 *            私自分享视频和源码属于违法行为。
 */
@Data
@ApiModel(value = "用户信息实体类")
public class UserInpDTO {

	/**
	 * userid
	 */
	@ApiModelProperty(value = "用户id")
	private Long userId;
	/**
	 * 手机号码
	 */
	@ApiModelProperty(value = "手机号码")
	private String mobile;
	/**
	 * 邮箱
	 */
	@ApiModelProperty(value = "邮箱")
	private String email;
	/**
	 * 密码
	 */
	@ApiModelProperty(value = "密码")
	private String password;
	/**
	 * 用户名称
	 */
	@ApiModelProperty(value = "用户名称")
	private String userName;
	/**
	 * 性别 0 男 1女
	 */
	@ApiModelProperty(value = "用户性别")
	private char sex;
	/**
	 * 年龄
	 */
	@ApiModelProperty(value = "用户年龄")
	private Integer age;

	/**
	 * 用户头像
	 */
	@ApiModelProperty(value = " 用户头像")
	private String picImg;
	/**
	 * 用户关联 QQ 开放ID
	 */
	@ApiModelProperty(value = "用户关联 QQ 开放ID")
	private String qqOpenId;
	/**
	 * 用户关联 微信 开放ID
	 */
	@ApiModelProperty(value = "用户关联 微信 开放ID")
	private String wxOpenId;

	// 目前存在的问题： 接口层传递实体类→数据库传递实体类 都是相同的。
	// 注册的时候不需要传递isAvalible updateTime createTime
	// 查询的用户相关信息的时候，是不需要将密码返回给客户端
	// 请求参数与返回参数如果公用同一个实体类会存在那些问题？ 可能会暴露数据库字段攻击 安全性
	// 修改用户密码

	// DTO 主要用于外部接口参数传递封装 接口与接口进行传递使用
	// DO主要操作用于数据库层传递
	// VO主要用于视图层展示
	// DTO转换DO (接口接受参数，转换为数据库实体类插入数据库中) DO转换DTO（向数据库查询数据转换DTO返回给客户端）

}
