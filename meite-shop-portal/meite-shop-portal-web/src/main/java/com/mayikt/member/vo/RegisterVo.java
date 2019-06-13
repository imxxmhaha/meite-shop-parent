package com.mayikt.member.vo;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 
 * 
 * 
 * @description: 前端注册信息
 * @author: 97后互联网架构师-余胜军
 * @contact: QQ644064779、微信yushengjun644 www.mayikt.com
 * @date: 2019年1月3日 下午3:03:17
 * @version V1.0
 * @Copyright 该项目“基于SpringCloud2.x构建微服务电商项目”由每特教育|蚂蚁课堂版权所有，未经过允许的情况下，
 *            私自分享视频和源码属于违法行为。
 */
@Data
public class RegisterVo {
	/**
	 * 手机号码
	 */
	@NotBlank(message = "手机号码不能为空")
	@Size(min = 11, max = 11, message = "手机号码长度不正确")
	@Pattern(regexp = "^(((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(16[6])|(17[0135678])|(18[0-9])|(19[89]))\\d{8})$", message = "手机号格式错误")
	private String mobile;
	/**
	 * 密码
	 */
	@NotNull(message = "密码不能为空!")
	private String password;

	/**
	 * 注册码
	 */
	@NotNull(message = "注册码不能为空!")
	private String registCode;
	/**
	 * 图形验证码
	 */
	@NotBlank(message = "图形验证码不能为空!")
	private String graphicCode;

}
