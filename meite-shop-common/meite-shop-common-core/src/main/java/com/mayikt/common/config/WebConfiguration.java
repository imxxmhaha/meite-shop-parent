package com.mayikt.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author xxm
 * @create 2019-05-26 23:20
 */
@Configuration
@Slf4j
public class WebConfiguration implements WebMvcConfigurer {

    // 将dasFilterInterceptor 拦截器 注册到spring 容器中
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(dasFilterInterceptor).addPathPatterns("/**");
        // .excludePathPatterns("/api/getToken", "/success");
        //registry.addInterceptor(browseAdaptInterceptor).addPathPatterns("/**");
    }

    // 统一页码处理配置
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                // ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
                ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/common/404.html");
                log.error("====WebMvcConfig.WebServerFactoryCustomizer===404错误跳转页面:/common/404.html");
                factory.addErrorPages(error404Page);
            }
        };
    }
}
