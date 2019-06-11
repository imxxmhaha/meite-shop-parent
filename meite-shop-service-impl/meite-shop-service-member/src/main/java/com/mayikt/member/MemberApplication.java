package com.mayikt.member;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.mayikt.common.core.utils.SpringContextUtil;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author xxm
 * @create 2019-05-29 19:19
 */
@EnableSwagger2Doc
@SpringBootApplication
@EnableEurekaClient
@EnableApolloConfig
@MapperScan(basePackages = {"com.mayikt.member.dao"})
@EnableFeignClients(basePackages = "com.mayikt.api")  //开启FeignClient支持
@ComponentScan(basePackages={"com.mayikt.api","com.mayikt.member","com.mayikt.common"})//扫描接口
//@EnableAspectJAutoProxy
public class MemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(MemberApplication.class,args);
    }
}
