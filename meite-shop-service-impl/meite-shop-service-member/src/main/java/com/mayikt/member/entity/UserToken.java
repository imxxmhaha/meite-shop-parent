package com.mayikt.member.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Xxm123
 * @since 2019-06-11
 */
@TableName("meite_user_token")
@Data
public class UserToken implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;
    private String token;
    @TableField("login_type")
    private String loginType;
    @TableField("device_infor")
    private String deviceInfor;
    @TableField("is_availability")
    private Integer isAvailability = 1;
    @TableField("user_id")
    private Long userId;

    @TableField("create_time")
    private Date createTime = new Date();
    @TableField("update_time")
    private Date updateTime;


    @Override
    public String toString() {
        return "UserToken{" +
        ", id=" + id +
        ", token=" + token +
        ", loginType=" + loginType +
        ", deviceInfor=" + deviceInfor +
        ", isAvailability=" + isAvailability +
        ", userId=" + userId +
        "}";
    }
}
