package com.mayikt.spike.service.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.mayikt.spike.service.mapper.entity.OrderEntity;

public interface OrderMapper {

	@Insert("INSERT INTO `meite_order` VALUES (#{seckillId},#{userPhone},#{state}, now());")
	int insertOrder(OrderEntity orderEntity);

	@Select("SELECT seckill_id AS seckillid,user_phone as userPhone , state as state FROM meite_order WHERE USER_PHONE=#{phone}  and seckill_id=#{seckillId}  AND STATE='1';")
	OrderEntity findByOrder(@Param("phone") String phone, @Param("seckillId") Long seckillId);
}
