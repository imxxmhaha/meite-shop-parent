package com.mayikt.member.service.impl;

import com.mayikt.api.member.service.MemberService;
import com.mayikt.api.weixin.service.WeiXinService;
import com.mayikt.common.base.BaseApiService;
import com.mayikt.common.base.BaseResponse;
import com.mayikt.common.constants.Constants;
import com.mayikt.member.dao.UserDao;
import com.mayikt.member.entity.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xxm
 * @create 2019-05-29 19:09
 */
@RestController
public class MemberServiceImpl extends BaseApiService<UserEntity> implements MemberService {

    // 调用Feign客户端接口
    @Autowired
    private WeiXinService weiXinService;

    /**
     *  1.接口中已经定义了GetMapping  这里可以不用定义,继承接口中的GetMapping
     *  2.如果这里定义的与接口中定义的不一致 使用当前定义的映射路径
     *  3.接口中定义的映射路径 主要是为了给Feign客户端调用的
     * @return
     */
    public BaseResponse memberToWeiXin() {
        BaseResponse baseResponse = weiXinService.getApp();
        return baseResponse;
    }

    // 问题:为什么命名为service  而不是controller  没有表现层
    // 问题:实现中需要些springmvc  url映射注解吗? 不需要的


    @Autowired
    private UserDao userDao;

    @Override
    public BaseResponse<UserEntity> existMobile(String mobile) {
        // 1.验证参数
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("手机号码不能为空!");
        }
        UserEntity userEntity = userDao.existMobile(mobile);
        if (userEntity == null) {
            return setResultError(Constants.HTTP_RES_CODE_EXISTMOBILE_202, "用户不存在");
        }
        // 注意需要将敏感数据进行脱敏
        userEntity.setPassword(null);
        return setResultSuccess(userEntity);
    }
}
