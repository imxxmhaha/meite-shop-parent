package com.mayikt.pay.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 支付交易日志表 
 * </p>
 *
 * @author Xxm123
 * @since 2019-06-21
 */
@TableName("payment_transaction_log")
public class TransactionLogEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;
    /**
     * 同步回调日志
     */
    @TableField("SYNCH_LOG")
    private String synchLog;
    /**
     * 异步回调日志
     */
    @TableField("ASYNC_LOG")
    private String asyncLog;
    /**
     * 支付渠道ID
     */
    @TableField("CHANNEL_ID")
    private Integer channelId;
    /**
     * 支付交易ID
     */
    @TableField("TRANSACTION_ID")
    private Integer transactionId;
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
    private String untitled;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSynchLog() {
        return synchLog;
    }

    public void setSynchLog(String synchLog) {
        this.synchLog = synchLog;
    }

    public String getAsyncLog() {
        return asyncLog;
    }

    public void setAsyncLog(String asyncLog) {
        this.asyncLog = asyncLog;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getRevision() {
        return revision;
    }

    public void setRevision(Integer revision) {
        this.revision = revision;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getUntitled() {
        return untitled;
    }

    public void setUntitled(String untitled) {
        this.untitled = untitled;
    }

    @Override
    public String toString() {
        return "TransactionLog{" +
        ", id=" + id +
        ", synchLog=" + synchLog +
        ", asyncLog=" + asyncLog +
        ", channelId=" + channelId +
        ", transactionId=" + transactionId +
        ", revision=" + revision +
        ", createdBy=" + createdBy +
        ", createdTime=" + createdTime +
        ", updatedBy=" + updatedBy +
        ", updatedTime=" + updatedTime +
        ", untitled=" + untitled +
        "}";
    }
}
