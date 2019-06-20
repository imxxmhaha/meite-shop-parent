package com.mayikt.pay.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 支付渠道 
 * </p>
 *
 * @author Xxm123
 * @since 2019-06-21
 */
@TableName("payment_channel")
public class ChannelEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;
    /**
     * 渠道名称
     */
    @TableField("CHANNEL_NAME")
    private String channelName;
    /**
     * 渠道ID
     */
    @TableField("CHANNEL_ID")
    private String channelId;
    /**
     * 商户id
     */
    @TableField("MERCHANT_ID")
    private String merchantId;
    /**
     * 同步回调URL
     */
    @TableField("SYNC_URL")
    private String syncUrl;
    /**
     * 异步回调URL
     */
    @TableField("ASYN_URL")
    private String asynUrl;
    /**
     * 公钥
     */
    @TableField("PUBLIC_KEY")
    private String publicKey;
    /**
     * 私钥
     */
    @TableField("PRIVATE_KEY")
    private String privateKey;
    /**
     * 渠道状态 0开启1关闭
     */
    @TableField("CHANNEL_STATE")
    private Integer channelState;
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
    @TableField("CLASS_ADDRES")
    private String classAddres;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getSyncUrl() {
        return syncUrl;
    }

    public void setSyncUrl(String syncUrl) {
        this.syncUrl = syncUrl;
    }

    public String getAsynUrl() {
        return asynUrl;
    }

    public void setAsynUrl(String asynUrl) {
        this.asynUrl = asynUrl;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public Integer getChannelState() {
        return channelState;
    }

    public void setChannelState(Integer channelState) {
        this.channelState = channelState;
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

    public String getClassAddres() {
        return classAddres;
    }

    public void setClassAddres(String classAddres) {
        this.classAddres = classAddres;
    }

    @Override
    public String toString() {
        return "Channel{" +
        ", id=" + id +
        ", channelName=" + channelName +
        ", channelId=" + channelId +
        ", merchantId=" + merchantId +
        ", syncUrl=" + syncUrl +
        ", asynUrl=" + asynUrl +
        ", publicKey=" + publicKey +
        ", privateKey=" + privateKey +
        ", channelState=" + channelState +
        ", revision=" + revision +
        ", createdBy=" + createdBy +
        ", createdTime=" + createdTime +
        ", updatedBy=" + updatedBy +
        ", updatedTime=" + updatedTime +
        ", classAddres=" + classAddres +
        "}";
    }
}
