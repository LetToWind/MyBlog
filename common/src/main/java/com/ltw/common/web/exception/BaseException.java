package com.ltw.common.web.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseException extends RuntimeException{
    private Integer code;
    private String message;

    /**
     * 如果有可以公共使用的不经常变的异常可以这样使用，
     * 不过感觉更多还是需要可变的msg的。
     * 或许应该改成只有状态码的枚举值？但似乎没有必要
     * @param exceptionEnum
     */
    public BaseException(ExceptionEnum exceptionEnum){
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMsg();
    }

    public BaseException(String  msg){
        this.code = HttpStatus.BAD_REQUEST.value();
        this.message = msg;
    }

}

