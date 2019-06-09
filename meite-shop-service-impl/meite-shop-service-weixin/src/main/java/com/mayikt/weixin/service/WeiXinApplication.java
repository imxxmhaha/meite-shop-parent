package com.mayikt.weixin.service;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xxm
 * @create 2019-05-29 19:19
 */
@EnableSwagger2Doc
@SpringBootApplication
@EnableEurekaClient
@EnableApolloConfig
@EnableFeignClients
@ComponentScan(basePackages={"com.mayikt.api","com.mayikt.weixin","com.mayikt.common"})//扫描接口
public class WeiXinApplication {
    public static void main(String[] args) {
        SpringApplication.run(WeiXinApplication.class,args);
    }
}
