package com.mayikt.member.service.impl;

import com.mayikt.api.member.service.MemberService;
import com.mayikt.common.base.BaseApiService;
import com.mayikt.common.base.BaseResponse;
import com.mayikt.common.constants.Constants;
import com.mayikt.common.core.utils.GenerateToken;
import com.mayikt.common.core.utils.MD5Util;
import com.mayikt.common.core.utils.MiteBeanUtils;
import com.mayikt.common.core.utils.TypeCastHelper;
import com.mayikt.member.dao.UserDao;
import com.mayikt.member.dao.UserTokenDao;
import com.mayikt.member.entity.UserEntity;
import com.mayikt.member.input.dto.UserLoginInpDTO;
import com.mayikt.member.output.dto.UserOutDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xxm
 * @create 2019-05-29 19:09
 */
@RestController
public class MemberServiceImpl extends BaseApiService<UserOutDTO> implements MemberService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserTokenDao userTokenDao;

    @Autowired
    private GenerateToken generateToken;


    /**
     *  1.接口中已经定义了GetMapping  这里可以不用定义,继承接口中的GetMapping
     *  2.如果这里定义的与接口中定义的不一致 使用当前定义的映射路径
     *  3.接口中定义的映射路径 主要是为了给Feign客户端调用的
     * @return
     */
    //public BaseResponse memberToWeiXin() {
    //    BaseResponse baseResponse = weiXinService.getApp();
    //    return baseResponse;
    //}

    // 问题:为什么命名为service  而不是controller  没有表现层
    // 问题:实现中需要些springmvc  url映射注解吗? 不需要的



    @Override
    public BaseResponse<UserOutDTO> existMobile(String mobile) {
        // 1.验证参数
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("手机号码不能为空!");
        }
        UserEntity userEntity = userDao.existMobile(mobile);
        if (userEntity == null) {
            return setResultError(Constants.HTTP_RES_CODE_EXISTMOBILE_202, "用户不存在");
        }
        UserOutDTO userOutDTO = MiteBeanUtils.E2T(userEntity, UserOutDTO.class);
        // 注意需要将敏感数据进行脱敏
        //userEntity.setPassword(null);
        return setResultSuccess(userOutDTO);
    }


    @Override
    public BaseResponse<UserOutDTO> getInfo(String token) {
        // 1.参数验证
        if (StringUtils.isEmpty(token)) {
            return setResultError("token不能为空!");
        }
        // 2.使用token向redis中查询userId
        String redisValue = generateToken.getToken(token);
        if (StringUtils.isEmpty(redisValue)) {
            return setResultError("token已经失效或者不正确");
        }
        Long userId = TypeCastHelper.toLong(redisValue);
        // 3.根据userId查询用户信息
        UserEntity userDo = userDao.selectById(userId);
        if (userDo == null) {
            return setResultError("用户信息不存在!");
        }
        // 4.将Do转换为Dto
        UserOutDTO doToDto = MiteBeanUtils.E2T(userDo, UserOutDTO.class);
        return setResultSuccess(doToDto);
    }


    @Override
    public BaseResponse<UserOutDTO> ssoLogin(@RequestBody UserLoginInpDTO userLoginInpDTO) {
        // 1.验证参数
        String mobile = userLoginInpDTO.getMobile();
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("手机号码不能为空!");
        }
        String password = userLoginInpDTO.getPassword();
        if (StringUtils.isEmpty(password)) {
            return setResultError("密码不能为空!");
        }
        // 判断登陆类型
        String loginType = userLoginInpDTO.getLoginType();
        if (StringUtils.isEmpty(loginType)) {
            return setResultError("登陆类型不能为空!");
        }
        // 目的是限制范围
        if (!(loginType.equals(Constants.MEMBER_LOGIN_TYPE_ANDROID) || loginType.equals(Constants.MEMBER_LOGIN_TYPE_IOS)
                || loginType.equals(Constants.MEMBER_LOGIN_TYPE_PC))) {
            return setResultError("登陆类型出现错误!");
        }

        // 设备信息
        String deviceInfor = userLoginInpDTO.getDeviceInfor();
        if (StringUtils.isEmpty(deviceInfor)) {
            return setResultError("设备信息不能为空!");
        }
        // 2.对登陆密码实现加密
        String newPassWord = MD5Util.MD5(password);
        // 3.使用手机号码+密码查询数据库 ，判断用户是否存在
        UserEntity userDo = userDao.login(mobile, newPassWord);
        if (userDo == null) {
            return setResultError("用户名称或者密码错误!");
        }
        return setResultSuccess(MiteBeanUtils.E2T(userDo, UserOutDTO.class));
    }
}
