package com.mayikt.member.input.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 * 
 * 
 * @description: 用户登陆请求参数
 * @author: 97后互联网架构师-余胜军
 * @contact: QQ644064779、微信yushengjun644 www.mayikt.com
 * @date: 2019年1月3日 下午3:03:17
 * @version V1.0
 * @Copyright 该项目“基于SpringCloud2.x构建微服务电商项目”由每特教育|蚂蚁课堂版权所有，未经过允许的情况下，
 *            私自分享视频和源码属于违法行为。
 */
@Data
@ApiModel(value = "用户登陆参数")
@Accessors(chain = true)
public class UserLoginInpDTO {
	/**
	 * 手机号码
	 */
	@ApiModelProperty(value = "手机号码")
	private String mobile;
	/**
	 * 密码
	 */
	@ApiModelProperty(value = "密码")
	private String password;

	/**
	 * 登陆类型 PC、Android 、IOS
	 */
	@ApiModelProperty(value = "登陆类型")
	private String loginType;
	/**
	 * 设备信息
	 */
	@ApiModelProperty(value = "设备信息")
	private String deviceInfor;
	// 为什么一个接口单独定义一个 dto请求参数类 swagger 接口文档对称

	/**
	 * 腾讯开放平台ID
	 */
	@ApiModelProperty(value = "腾讯开放平台ID")
	private String qqOpenId;
}
