package com.mayikt.common.advice;

import com.google.common.collect.ImmutableMap;
import com.mayikt.common.base.*;
import com.mayikt.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	//使用EXCEPTIONS存放异常类型和错误代码的映射，ImmutableMap的特点的一旦创建不可改变，并且线程安全
	private static ImmutableMap<Class<? extends Throwable>,ResultCode> EXCEPTIONS;
	//使用builder来构建一个异常类型和错误代码的异常
	protected static ImmutableMap.Builder<Class<? extends Throwable>,ResultCode> builder =
			ImmutableMap.builder();

	static{
		//在这里加入一些基础的异常类型判断
		builder.put(HttpMessageNotReadableException.class,CommonCode.INVALID_PARAM);
	}
	//@ExceptionHandler
	//@ResponseBody
	//public Object handleException(Exception e,HttpServletRequest req){
	//	BaseResponse returnMsg = new BaseResponse();
	//	// 业务异常
	//	if(e instanceof BizException){
	//		returnMsg.setCode(((BizException) e).getCode());
	//		returnMsg.setMsg(((BizException) e).getMsg());
	//	}else{// 系统异常
	//		returnMsg.setCode(ExceptionCodeEum.SYSTEM_EXCEPTION.getCode());
	//		returnMsg.setMsg(ExceptionCodeEum.SYSTEM_EXCEPTION.getMsg());
	//	}
    //
	//	// 判断响应类型
	//	String contentTypeHeader = req.getHeader("Content-Type");
	//	String acceptHeader = req.getHeader("Accept");
	//	String xRequestedWith = req.getHeader("X-Requested-With");
	//	if ((contentTypeHeader != null && contentTypeHeader.contains("application/json"))
	//			|| (acceptHeader != null && acceptHeader.contains("application/json"))
	//			|| "XMLHttpRequest".equalsIgnoreCase(xRequestedWith)) {
	//		log.error("====GlobalExceptionHandler.handleException===ajax异常====msg:"+e.getMessage());
	//		log.error("====GlobalExceptionHandler.handleException===ajax异常====url:"+getCompleteUrl(req));
	//		log.error("",e);
	//		return returnMsg;
	//	} else {
	//		log.error("====GlobalExceptionHandler.handleException=======msg:"+ e.getMessage());
	//		log.error("====GlobalExceptionHandler.handleException=======url:"+getCompleteUrl(req));
	//		log.error("",e);
	//		// 对于系统异常直接抛出404错误页面
	//		ModelAndView modelAndView = new ModelAndView();
	//		modelAndView.setViewName("common/404");
	//		return modelAndView;
	//	}
	//}

	private String getCompleteUrl(HttpServletRequest request) {
		String returnUrl = request.getRequestURL().toString();
		// 请求参数
		String queryString = request.getQueryString();
		returnUrl = StringUtils.isEmpty(queryString)?returnUrl:(returnUrl+"?"+queryString);
		return returnUrl;
	}


	//捕获 BizException
	@ExceptionHandler(BizException.class)
	@ResponseBody
	public Object bizException(BizException e) {
		log.error("catch exception : {}\r\nexception: ", e.getMessage(), e);
		ResultCode resultCode = e.getResultCode();
		BaseResponse baseResponse = new BaseResponse(resultCode);
		return baseResponse;
	}


	//捕获 Exception异常
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseResult exception(Exception e) {
		log.error("catch exception : {}\r\nexception: ", e.getMessage(), e);
		if(EXCEPTIONS == null)
			EXCEPTIONS = builder.build();
		final ResultCode resultCode = EXCEPTIONS.get(e.getClass());
		final ResponseResult responseResult;
		if (resultCode != null) {
			responseResult = new ResponseResult(resultCode);
		} else {
			responseResult = new ResponseResult(CommonCode.SERVER_ERROR);
		}
		return responseResult;
	}


}