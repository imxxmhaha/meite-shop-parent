package com.mayikt.member.dao;

import com.mayikt.member.entity.UserToken;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Xxm123
 * @since 2019-06-11
 */
public interface UserTokenDao extends BaseMapper<UserToken> {


    @Select("SELECT id as id ,token as token ,login_type as LoginType, device_infor as deviceInfor ,is_availability as isAvailability,user_id as userId"
            + "" + ""
            + " , create_time as createTime,update_time as updateTime   FROM meite_user_token WHERE user_id=#{userId} AND login_type=#{loginType} and is_availability ='1'")
    UserToken selectByUserIdAndLoginType(@Param("userId") Long userId, @Param("loginType") String loginType);

    @Update("    update meite_user_token set is_availability ='0',update_time=now()   where token=#{token} ")
    int updateTokenAvailability(@Param("token")  String token);

}
