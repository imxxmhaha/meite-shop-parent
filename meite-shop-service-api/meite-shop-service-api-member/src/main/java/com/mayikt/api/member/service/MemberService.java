package com.mayikt.api.member.service;

import com.mayikt.entity.weixin.AppEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 会员服务接口
 * @author xxm
 * @create 2019-05-29 18:44
 */
@Api(tags = "会员服务接口")
public interface MemberService {

    /**
     * 会员服务接口调用微信接口
     * @return
     */
    @ApiOperation(value = "会员服务调用微信服务")
    @GetMapping("/memberToWeiXin")
    public AppEntity memberToWeiXin();
}
