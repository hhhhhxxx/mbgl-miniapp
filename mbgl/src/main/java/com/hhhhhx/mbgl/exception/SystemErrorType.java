package com.hhhhhx.mbgl.exception;

import lombok.Getter;

@Getter
public enum SystemErrorType implements ErrorType {
    SYSTEM_ERROR(110000, "失败"),
    SYSTEM_BUSY(110001, "系统繁忙,请稍候再试"),
    GATEWAY_NOT_FOUND_SERVICE(11002, "服务未找到");

    /**
     * 错误类型码
     */
    private Integer code;
    /**
     * 错误类型描述信息
     */
    private String message;

    SystemErrorType(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
