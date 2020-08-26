package com.phantom5702.sms.config;


import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public Object exceptionHandler(HttpServletRequest request, Exception e) {
        //绑定异常是需要明确提示给用户的
        if (e instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException exception = (MethodArgumentTypeMismatchException) e;
            MethodParameter parameter = exception.getParameter();
            String msg =parameter.getParameterName();//获取自错误信息
            return msg+" 参数格式错误";//将具体
        }
        return e.getMessage();
    }
}
