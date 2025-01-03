package com.ltw.common.web.dto;

import lombok.Data;

@Data
public class Result<T> {

    public static final int SUCCESS = 200;
    public static final int FAIL = 500;

    private int code;

    private String msg;

    private T data;

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result success(){
        return new Result(SUCCESS, "success", null);
    }

    public static Result success(String msg){
        return new Result(SUCCESS, msg, null);
    }

    public static Result fail(){
        return new Result(FAIL, "fail", null);
    }

    public static Result fail(String msg){
        return new Result(FAIL, msg, null);
    }

}
