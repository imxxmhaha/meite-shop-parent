package com.mayikt.pay.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mayikt.pay.entity.ChannelEntity;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 支付渠道  Mapper 接口
 * </p>
 *
 * @author Xxm123
 * @since 2019-06-21
 */
public interface ChannelDao extends BaseMapper<ChannelEntity> {


    @Select("SELECT channel_Name  AS channelName , channel_Id AS channelId, merchant_Id AS merchantId,sync_Url AS syncUrl, asyn_Url AS asynUrl,public_Key AS publicKey, private_Key AS privateKey,channel_State AS channelState ,class_ADDRES as classAddres  FROM payment_channel WHERE CHANNEL_STATE='0'  AND channel_Id=#{channelId} ;")
    ChannelEntity selectBychannelId(String channelId);
}
