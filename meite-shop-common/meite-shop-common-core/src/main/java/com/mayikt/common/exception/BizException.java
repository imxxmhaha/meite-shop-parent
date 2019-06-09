package com.mayikt.common.exception;

import com.mayikt.common.base.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xxm
 * @create 2019-05-26 22:47
 */
@Data
@NoArgsConstructor
public class BizException extends RuntimeException {
    private static final long serialVersionUID = 4061732202885069884L;


    private ResultCode resultCode;

    public BizException(ResultCode resultCode) {
        //异常信息为错误代码+异常信息
        super("错误代码：" + resultCode.code() + "错误信息：" + resultCode.message());
        this.resultCode = resultCode;
    }
    public ResultCode getResultCode() {
        return this.resultCode;
    }
}
