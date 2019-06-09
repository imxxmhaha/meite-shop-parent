package com.mayikt.common.advice;

import com.mayikt.common.base.BaseResponse;
import com.mayikt.common.base.ExceptionCodeEum;
import com.mayikt.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler
	@ResponseBody
	public Object handleException(Exception e,HttpServletRequest req){
		BaseResponse returnMsg = new BaseResponse();
		// 业务异常
		if(e instanceof BizException){
			returnMsg.setCode(((BizException) e).getCode());
			returnMsg.setMsg(((BizException) e).getMsg());
		}else{// 系统异常
			returnMsg.setCode(ExceptionCodeEum.SYSTEM_EXCEPTION.getCode());
			returnMsg.setMsg(ExceptionCodeEum.SYSTEM_EXCEPTION.getMsg());
		}

		// 判断响应类型
		String contentTypeHeader = req.getHeader("Content-Type");
		String acceptHeader = req.getHeader("Accept");
		String xRequestedWith = req.getHeader("X-Requested-With");
		if ((contentTypeHeader != null && contentTypeHeader.contains("application/json"))
				|| (acceptHeader != null && acceptHeader.contains("application/json"))
				|| "XMLHttpRequest".equalsIgnoreCase(xRequestedWith)) {
			log.error("====GlobalExceptionHandler.handleException===ajax异常====msg:"+e.getMessage());
			log.error("====GlobalExceptionHandler.handleException===ajax异常====url:"+getCompleteUrl(req));
			log.error("",e);
			return returnMsg;
		} else {
			log.error("====GlobalExceptionHandler.handleException=======msg:"+ e.getMessage());
			log.error("====GlobalExceptionHandler.handleException=======url:"+getCompleteUrl(req));
			log.error("",e);
			// 对于系统异常直接抛出404错误页面
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("common/404");
			return modelAndView;
		}
	}

	private String getCompleteUrl(HttpServletRequest request) {
		String returnUrl = request.getRequestURL().toString();
		// 请求参数
		String queryString = request.getQueryString();
		returnUrl = StringUtils.isEmpty(queryString)?returnUrl:(returnUrl+"?"+queryString);
		return returnUrl;
	}
	

}