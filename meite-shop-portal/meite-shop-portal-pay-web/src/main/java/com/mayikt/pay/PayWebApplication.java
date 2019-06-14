package com.mayikt.pay;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
@EnableEurekaClient
@EnableApolloConfig
@EnableFeignClients(basePackages="com.mayikt.api")  //开启FeignClient支持
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800)
@ComponentScan(basePackages={"com.mayikt","com.xxl.sso"})//扫描接口
public class PayWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayWebApplication.class, args);
	}

}
