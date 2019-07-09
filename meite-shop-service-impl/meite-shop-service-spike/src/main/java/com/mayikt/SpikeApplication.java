package com.mayikt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "com.mayikt.spike.service.mapper")
public class SpikeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpikeApplication.class, args);
	}

}
