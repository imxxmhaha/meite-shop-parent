package com.mayikt.member.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
	@TableId
	private Long userId;
	/**
	 * 手机号码
	 */
	@TableField("mobile")
	private String mobile;
	/**
	 * 邮箱
	 */
	@TableField("email")
	private String email;
	/**
	 * 密码
	 */
	@TableField("password")
	private String password;
	/**
	 * 用户名称
	 */
	@TableField("user_name")
	private String userName;
	/**
	 * 性别 0 男 1女
	 */
	@TableField("sex")
	private char sex;
	/**
	 * 年龄
	 */
	@TableField("age")
	private Long age;
	/**
	 * 注册时间
	 */
	@TableField("create_time")
	private Date createTime;
	/**
	 * 修改时间
	 *
	 */
	@TableField("update_time")
	private Date updateTime;
	/**
	 * 账号是否可以用 1 正常 0冻结
	 */
	@TableField("is_avalible")
	private Integer isAvalible = 1;
	/**
	 * 用户头像
	 */
	@TableField("pic_img")
	private String picImg;
	/**
	 * 用户关联 QQ 开放ID
	 */
	@TableField("qq_openid")
	private Date qqOpenid;
	/**
	 * 用户关联 微信 开放ID
	 */
	@TableField("wx_openid")
	private Date wxOpenid;
}
