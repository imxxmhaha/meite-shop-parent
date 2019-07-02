package com.mayikt.auth.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.mayikt.auth.mapper.entity.MeiteAppInfo;

public interface AppInfoMapper {

	@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("INSERT INTO `meite_app_info` VALUES (#{id},#{appName}, #{appId}, #{appSecret}, '0', null, null, null, null, null)")
	public int insertAppInfo(MeiteAppInfo meiteAppInfo);

	@Select("SELECT ID AS ID ,app_id as appId, app_name AS appName ,app_secret as appSecret  FROM meite_app_info where app_id=#{appId} and app_secret=#{appSecret}; ")
	public MeiteAppInfo selectByAppInfo(@Param("appId") String appId, @Param("appSecret") String appSecret);

	@Select("SELECT ID AS ID ,app_id as appId, app_name AS appName ,app_secret as appSecret  FROM meite_app_info where app_id=#{appId}  ")
	public MeiteAppInfo findByAppInfo(@Param("appId") String appId);
}
