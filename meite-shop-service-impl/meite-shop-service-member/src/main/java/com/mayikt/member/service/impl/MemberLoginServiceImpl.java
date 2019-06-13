package com.mayikt.member.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.api.member.service.MemberLoginService;
import com.mayikt.common.aop.RedisTransAdvice;
import com.mayikt.common.base.BaseApiService;
import com.mayikt.common.base.BaseResponse;
import com.mayikt.common.constants.Constants;
import com.mayikt.common.core.transaction.RedisDataSoureceTransaction;
import com.mayikt.common.core.utils.*;
import com.mayikt.member.dao.UserDao;
import com.mayikt.member.dao.UserTokenDao;
import com.mayikt.member.entity.UserEntity;
import com.mayikt.member.entity.UserToken;
import com.mayikt.member.handle.MemberLoginHandle;
import com.mayikt.member.input.dto.UserLoginInpDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xxm
 * @create 2019-06-11 19:58
 */
@RestController
@Slf4j
public class MemberLoginServiceImpl extends BaseApiService<JSONObject> implements MemberLoginService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private MemberLoginHandle memberLoginHandle;

    @Autowired
    private RedisUtil redisUtil;
    /**
     * 手动事务工具类
     */
    @Autowired
    private RedisDataSoureceTransaction manualTransaction;

    @Override
    public BaseResponse<JSONObject> login(@RequestBody UserLoginInpDTO userLoginInpDTO) {
        // 1.验证参数
        String mobile = userLoginInpDTO.getMobile();
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("手机号码不能为空!");
        }
        String password = userLoginInpDTO.getPassword();
        if (StringUtils.isEmpty(password)) {
            return setResultError("密码不能为空!");
        }
        String loginType = userLoginInpDTO.getLoginType();
        if (StringUtils.isEmpty(loginType)) {
            return setResultError("登陆类型不能为空!");
        }
        if (!(loginType.equals(Constants.MEMBER_LOGIN_TYPE_ANDROID) || loginType.equals(Constants.MEMBER_LOGIN_TYPE_IOS)
                || loginType.equals(Constants.MEMBER_LOGIN_TYPE_PC))) {
            return setResultError("登陆类型出现错误!");
        }

        // 设备信息
        String deviceInfor = userLoginInpDTO.getDeviceInfor();
        if (StringUtils.isEmpty(deviceInfor)) {
            return setResultError("设备信息不能为空!");
        }
        String newPassWord = MD5Util.MD5(password);
        // 2.用户名称与密码登陆
        UserEntity userDo = userDao.login(mobile, newPassWord);
        if (userDo == null) {
            return setResultError("用户名称与密码错误!");
        }

        //TransactionStatus transactionStatus = null;
        // 注入当前Bean使得调用内部方法也被SpringAOP拦截
        // 内部方法调用,这里无法触发内部方法(doLogin)的切面,
        // 而在当前方法使用注解是可以触发切面的,因为spring 使用CGLIB来实例化目标类的代理对象的,所以这个时候是可以触发切面的
        // 调用内部方法时候,this的指向其实是目标方法自己,目标方法自己调用自己的方法 是无法触发切面的
        // 解决办法一:重新从spring ioc 容器里面拿出当前类的代理对象,用代理对象调用内部方法,从而触发切面
        // 解决办法二:重新声明一个Component组件类,将内部方法移到该组件内,然后@Autowire 注入这个类,调用其方法,从而触发切面
        //MemberLoginServiceImpl memberService = (MemberLoginServiceImpl) SpringContextUtil.getBean(this.getClass());
        //JSONObject tokenData = memberService.doLogin(userLoginInpDTO, loginType, deviceInfor, userDo);
        JSONObject tokenData = null;
        try {
             tokenData = memberLoginHandle.doLogin(userLoginInpDTO, loginType, deviceInfor, userDo);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("====登录异常====");
            return setResultError("系统错误!");
        }
        return setResultSuccess(tokenData);
    }


    @Override
    public BaseResponse<JSONObject> delToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return setResultError("token不能为空!");
        }
        Boolean flag = true;
        try {
             flag = memberLoginHandle.doDelKey(token);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }

        return flag ? setResultSuccess("删除成功") : setResultError("删除失败!");
    }
}
