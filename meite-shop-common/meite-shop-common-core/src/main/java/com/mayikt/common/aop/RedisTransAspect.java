package com.mayikt.common.aop;


import com.alibaba.fastjson.JSONObject;
import com.mayikt.common.base.BaseApiService;
import com.mayikt.common.core.transaction.RedisDataSoureceTransaction;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * 
 * 
 *  输出日志的切面.
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2018年7月27日    	     徐晓鸣    新建
 * </pre>
 */
@Aspect
@Component
@Slf4j
public class RedisTransAspect {
    /**
     * 手动事务工具类
     */
    @Autowired
    private RedisDataSoureceTransaction manualTransaction;

    // Controller层切点
    @Pointcut("within(com.mayikt..*)")
    public void pointcut() {
    }
    


    @Before("pointcut() && @annotation(beforeInvoked)")
    public void addBefore(JoinPoint joinPoint, RedisTransAdvice beforeInvoked) {
        HttpServletRequest request = getHttpServletRequest();
        TransactionStatus transactionStatus = manualTransaction.begin();
        // 开启事务
        request.setAttribute("transactionStatus",transactionStatus);
    }



    //@After("pointcut() && @annotation(afterInvoked)")
    //public void addAfter(JoinPoint joinPoint, RedisTransAdvice afterInvoked) throws Exception {
    //    HttpServletRequest request = getHttpServletRequest();
    //    TransactionStatus transactionStatus = (TransactionStatus) request.getAttribute("transactionStatus");
    //    // 提交手动事务
    //    manualTransaction.commit(transactionStatus);
    //}

    @AfterReturning(returning = "rvt", pointcut = "pointcut() && @annotation(afterReturning)")
    public void addAfterReturningLogger(JoinPoint joinPoint, RedisTransAdvice afterReturning, Object rvt) throws Exception {
        HttpServletRequest request = getHttpServletRequest();
        TransactionStatus transactionStatus = (TransactionStatus) request.getAttribute("transactionStatus");
        // 提交手动事务
        manualTransaction.commit(transactionStatus);
    }

    @AfterThrowing(pointcut = "pointcut() && @annotation(throwingEx)", throwing = "ex")
    public void afterThrowingMethod(JoinPoint joinPoint, Exception ex,RedisTransAdvice throwingEx) {
        HttpServletRequest request = getHttpServletRequest();
        TransactionStatus transactionStatus = (TransactionStatus) request.getAttribute("transactionStatus");
        // 回滚事务
        try {
            manualTransaction.rollback(transactionStatus);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private HttpServletRequest getHttpServletRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes.getRequest();
    }
}