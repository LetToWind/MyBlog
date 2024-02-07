package com.ltw.common.web.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 突发奇想，如果能够给前端返回的报错信息带有当前所在位置会不会好些？
 * 其实不用，只要在错误的地方打印日志就好了
 */
@AllArgsConstructor
@Getter
public enum ExceptionEnum {
    INTERNAL_SERVER_ERROR(500,"internal-server-error");

    private final Integer code;
    private final String msg;

}
