package com.atguigu.servicebase.exceptionhandler;


import com.atguigu.commomutils.ResponseBean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseBean error(Exception e){
        e.printStackTrace();
        return ResponseBean.error().message("执行全局异常处理......");
    }
}
