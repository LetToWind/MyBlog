package com.ltw.common.web;

import com.ltw.common.web.dto.Result;
import com.ltw.common.web.exception.BaseException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalResolver {


    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public Result commonExceptionHandler(BaseException e){
        return new Result<>(e.getCode(),e.getMessage());
    }

}
