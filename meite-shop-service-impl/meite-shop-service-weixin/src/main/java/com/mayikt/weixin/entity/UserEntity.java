package com.mayikt.weixin.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 用户信息实体类
 */
@Data
@TableName("meite_user")
public class UserEntity {

	/**
	 * userid
	 */
	private Long userId;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 用户名称
	 */
	private String userName;
	/**
	 * 性别 0 男 1女
	 */
	private char sex;
	/**
	 * 年龄
	 */
	private Long age;
	/**
	 * 注册时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 *
	 */
	private Date updateTime;
	/**
	 * 账号是否可以用 1 正常 0冻结
	 */
	private char isAvalible;
	/**
	 * 用户头像
	 */
	private String picImg;
	/**
	 * 用户关联 QQ 开放ID
	 */
	private Date qqOpenid;
	/**
	 * 用户关联 微信 开放ID
	 */
	private Date wxOpenid;
}
