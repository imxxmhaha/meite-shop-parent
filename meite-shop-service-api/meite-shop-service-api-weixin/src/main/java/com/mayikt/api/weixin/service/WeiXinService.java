package com.mayikt.api.weixin.service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xxm
 * @create 2019-05-29 18:44
 */
@Api(tags = "微信服务接口")
@FeignClient(value = "APP-MAYIKT-WEIXIN") //指定需要远程调用的服务名
public interface WeiXinService {


    /**
     * 获取app应用信息
     *
     * @return
     */
    @ApiOperation(value = "微信应用服务接口")
    @GetMapping("/getApp")
    public com.mayikt.common.base.BaseResponse getApp();
}
