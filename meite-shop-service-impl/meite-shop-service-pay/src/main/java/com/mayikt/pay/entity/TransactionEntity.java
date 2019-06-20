package com.mayikt.pay.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 * 支付交易 
 * </p>
 *
 * @author Xxm123
 * @since 2019-06-21
 */
@TableName("payment_transaction")
@Data
@ToString
public class TransactionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;
    /**
     * 支付金额
     */
    @TableField("PAY_AMOUNT")
    private Long payAmount;
    /**
     * 支付状态 0待支付1已经支付2支付超时3支付失败
     */
    @TableField("PAYMENT_STATUS")
    private Integer paymentStatus;
    /**
     * 用户ID
     */
    @TableField("USER_ID")
    private Long userId;
    /**
     * 订单号码
     */
    @TableField("ORDER_ID")
    private String orderId;
    /**
     * 乐观锁
     */
    @TableField("REVISION")
    private Integer revision;
    /**
     * 创建人
     */
    @TableField("CREATED_BY")
    private String createdBy;
    /**
     * 创建时间
     */
    @TableField("CREATED_TIME")
    private Date createdTime;
    /**
     * 更新人
     */
    @TableField("UPDATED_BY")
    private String updatedBy;
    /**
     * 更新时间
     */
    @TableField("UPDATED_TIME")
    private Date updatedTime;
    @TableField("PARTYPAY_ID")
    private String partypayId;
    @TableField("PAYMENT_ID")
    private String paymentId;



}
