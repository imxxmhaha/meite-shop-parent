package com.mayikt.common.advice;

import com.mayikt.common.annotation.IgnoreResponseAdvice;
import com.mayikt.common.base.BaseResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author xxm
 * @create 2019-05-26 22:04
 */
//@RestControllerAdvice
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 响应是否拦截
     * 1.根据方法参数MethodParameter  判断
     * 2.根据类converterType  判断
     *
     * @param methodParameter
     * @param converterType
     * @return
     */
    @Override
    @SuppressWarnings("all")
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) {
        // 如果类被IgnoreResponseAdvice注解声明,那么就不拦截
        if (methodParameter.getDeclaringClass().isAnnotationPresent(
                IgnoreResponseAdvice.class
        )) {
            return false;
        }
        // 如果方法被IgnoreResponseAdvice注解声明,那么就不拦截
        if (methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        return true;
    }

    @Override
    @SuppressWarnings("all")
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        System.out.println(body);
        BaseResponse<Object> baseResponse = new BaseResponse<>("001000","");
        if(null == body){
            return baseResponse;
        } else if (body instanceof BaseResponse){
            baseResponse = (BaseResponse<Object>) body;
        } else{
            baseResponse.setData(body);
        }

        return baseResponse;
    }
}
