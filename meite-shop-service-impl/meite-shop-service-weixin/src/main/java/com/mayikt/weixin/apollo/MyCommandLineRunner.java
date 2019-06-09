package com.mayikt.weixin.apollo;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @description: 项目启动开启分布式配置中心阿波罗监听
 * @date: 2019年1月3日 下午3:03:17
 * @version V1.0
 */
@Component
@Slf4j
public class MyCommandLineRunner implements CommandLineRunner {
	@ApolloConfig
	private Config config;

	@Override
	public void run(String... args) throws Exception {
		log.info("################分布式配置中心启动成功#################");
		config.addChangeListener(new ConfigChangeListener() {

			@Override
			public void onChange(ConfigChangeEvent changeEvent) {
				log.info("####分布式配置中心监听#####" + changeEvent.changedKeys().toString());
			}
		});
	}

}