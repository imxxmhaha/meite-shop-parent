package com.mayikt.auth.mapper.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import org.springframework.data.annotation.Id;

@TableName(value = "meite_app_info")
@Data
public class MeiteAppInfo implements Serializable, Cloneable {
	/** 主键ID */
	@TableId(value = "ID", type = IdType.AUTO)
	private Long id;
	/** 应用名称 */
	private String appName;
	/** 应用id */
	private String appId;
	/** 应用密钥 */
	private String appSecret;
	/** 是否可用 */
	private String availability;

	public MeiteAppInfo(String appName, String appId, String appSecret) {
		super();
		this.appName = appName;
		this.appId = appId;
		this.appSecret = appSecret;
	}

	public MeiteAppInfo() {

	}

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