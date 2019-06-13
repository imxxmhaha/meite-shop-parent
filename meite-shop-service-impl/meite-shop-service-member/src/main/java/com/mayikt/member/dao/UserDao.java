package com.mayikt.member.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mayikt.member.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 用户会员表 Mapper 接口
 * </p>
 *
 * @author Xxm123
 * @since 2019-06-10
 */
public interface UserDao extends BaseMapper<UserEntity> {
    @Select("select * from meite_user where mobile=#{mobile}")
    UserEntity existMobile(@Param("mobile") String mobile);

    @Select("SELECT USER_ID AS USERID ,MOBILE AS MOBILE,EMAIL AS EMAIL,PASSWORD AS PASSWORD, USER_NAME AS USER_NAME ,SEX AS SEX ,AGE AS AGE ,CREATE_TIME AS CREATETIME,IS_AVALIBLE AS ISAVALIBLE,PIC_IMG AS PICIMG,QQ_OPENID AS QQOPENID,WX_OPENID AS WXOPENID "
            + "  FROM meite_user  WHERE MOBILE=#{mobile} and password=#{password}")
    UserEntity login(@Param("mobile") String mobile, @Param("password") String password);


    @Select("SELECT USER_ID AS USERID ,MOBILE AS MOBILE,EMAIL AS EMAIL,PASSWORD AS PASSWORD, USER_NAME AS USER_NAME ,SEX AS SEX ,AGE AS AGE ,CREATE_TIME AS CREATETIME,IS_AVALIBLE AS ISAVALIBLE,PIC_IMG AS PICIMG,QQ_OPENID AS QQOPENID,WX_OPENID AS WXOPENID"
            + " FROM meite_user WHERE qq_openid=#{qqOpenId}")
    UserEntity findByOpenId(@Param("qqOpenId") String qqOpenId);

    @Update("update meite_user set QQ_OPENID =#{qqOpenId} WHERE USER_ID=#{userId}")
    int updateUserOpenId(@Param("qqOpenId") String qqOpenId, @Param("userId") Long userId);
}
