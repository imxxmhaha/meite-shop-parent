package com.mayikt.common.exception;


import com.mayikt.common.base.ResultCode;

/**
 * @author xxm
 * @create 2019-03-04 23:05
 */
public class ExceptionCast {
    //使用此静态方法抛出自定义异常
    public static void cast(ResultCode resultCode) {
        throw new BizException(resultCode);
    }
}