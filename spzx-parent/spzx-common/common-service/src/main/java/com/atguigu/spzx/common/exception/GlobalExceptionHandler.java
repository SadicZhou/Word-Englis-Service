package com.atguigu.spzx.common.exception;

import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    //全局异常处理
    @ExceptionHandler(Exception.class)
    @ResponseBody //处理返回的数据格式为json
    public Result error(Exception ex){
        System.out.println("错误======>"+ex);
        return Result.build(null, ResultCodeEnum.SYSTEM_ERROR);
    }

    //自定义异常处理
    @ExceptionHandler(GuiguException.class)//此处传入自定义异常类的class，表示针对这种异常做处理
    @ResponseBody
    public  Result error(GuiguException e){
        return Result.build(null, e.getResultCodeEnum());
    }
}
