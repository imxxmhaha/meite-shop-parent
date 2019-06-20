package com.mayikt.pay.config.mybatis;

import com.baomidou.mybatisplus.toolkit.IdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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