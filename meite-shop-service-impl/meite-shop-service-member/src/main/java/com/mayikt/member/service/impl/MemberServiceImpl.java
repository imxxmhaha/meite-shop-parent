package com.mayikt.member.service.impl;

import com.mayikt.api.member.service.MemberService;
import com.mayikt.api.weixin.service.WeiXinService;
import com.mayikt.entity.weixin.AppEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xxm
 * @create 2019-05-29 19:09
 */
@RestController
public class MemberServiceImpl implements MemberService {

    // 调用Feign客户端接口
    @Autowired
    private WeiXinService weiXinService;

    /**
     *  1.接口中已经定义了GetMapping  这里可以不用定义,继承接口中的GetMapping
     *  2.如果这里定义的与接口中定义的不一致 使用当前定义的映射路径
     *  3.接口中定义的映射路径 主要是为了给Feign客户端调用的
     * @return
     */
    public AppEntity memberToWeiXin() {
        AppEntity app = weiXinService.getApp();
        return app;
    }

    // 问题:为什么命名为service  而不是controller  没有表现层
    // 问题:实现中需要些springmvc  url映射注解吗? 不需要的
}
