package com.example.be.exception;

import com.example.be.common.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //处理异常
    @ExceptionHandler(Exception.class) //指定能够处理的异常类型
    public R ex(Exception e) {
        e.printStackTrace();//打印堆栈中的异常信息

        //捕获到异常之后，响应一个标准的Result
        return R.error("对不起,操作失败,请联系管理员");
    }
}
