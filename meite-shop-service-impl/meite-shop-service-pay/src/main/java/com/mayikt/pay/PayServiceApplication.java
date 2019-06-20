package com.mayikt.pay;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
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
//@EnableApolloConfig
@MapperScan(basePackages = {"com.mayikt.pay.dao"})
@EnableFeignClients(basePackages = "com.mayikt.api")  //开启FeignClient支持
@ComponentScan(basePackages={"com.mayikt.api","com.mayikt.pay","com.mayikt.common"})//扫描接口
public class PayServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayServiceApplication.class,args);
    }
}
