package com.mayikt.zuul.gateway.mapper;

import com.mayikt.zuul.gateway.mapper.entity.MeiteBlackList;
import org.apache.ibatis.annotations.Select;


public interface BlacklistMapper {

	@Select(" select ID AS ID ,ip_addres AS ipAddres,restriction_type  as restrictionType, availability  as availability from meite_black_list where  ip_addres =#{ipAddres} and  restriction_type='1' ")
	MeiteBlackList findBlacklist(String ipAddres);

}
