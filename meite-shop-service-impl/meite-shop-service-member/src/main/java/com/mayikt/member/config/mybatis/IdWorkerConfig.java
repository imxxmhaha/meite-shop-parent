package com.mayikt.member.config.mybatis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.toolkit.IdWorker;
/**
 * 将IdWorker  交给Spring管理
 */

@Configuration
public class IdWorkerConfig {


    @Bean
    public IdWorker idWorker() {
        IdWorker idWorker = new IdWorker();
        return idWorker;
    }

}