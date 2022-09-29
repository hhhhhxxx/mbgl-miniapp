package com.hhhhhx.mbgl.entity.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hhhhhx.mbgl.base.SystemCode;
import com.hhhhhx.mbgl.exception.ErrorType;
import com.hhhhhx.mbgl.exception.SystemErrorType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @version 1.0.0
 * @description: 返回数据
 * @date 2022/1/24
 */
@Data
@Accessors(chain = true)
public class RestResponse<T> {
    // 响应码
    private Integer code;
    private String message;
    private T response;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;

    // 构造函数
    public RestResponse() {
        this.time = new Date();
    }

    public RestResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.time = new Date();
    }

    public RestResponse(Integer code, String message, T response) {
        this.code = code;
        this.message = message;
        this.response = response;
        this.time = new Date();
    }


    public static RestResponse ok() {
        SystemCode systemCode = SystemCode.OK;
        return new RestResponse<>(systemCode.getCode(), systemCode.getMessage());
    }

    public static <F> RestResponse<F> ok(F response) {
        SystemCode systemCode = SystemCode.OK;
        return new RestResponse<F>(systemCode.getCode(), systemCode.getMessage(), response);
    }



    // 失败的构造
    public RestResponse(ErrorType errorType, T data) {
        this.code = errorType.getCode();
        this.message = errorType.getMessage();
        this.response = data;
        this.time = new Date();
    }

    public static RestResponse fail(Integer code, String msg) {
        return new RestResponse(code, msg);
    }

    /**
     * 系统异常类没有返回数据
     *
     * @return Result
     */
    public static RestResponse fail() {
        return new RestResponse(SystemErrorType.SYSTEM_ERROR,null);
    }



    // 下面三个是一起的
    public static RestResponse fail(ErrorType errorType, Object data) {
        return new RestResponse(errorType, data);
    }
    /**
     * 系统异常类并返回结果数据
     *
     * @param errorType
     * @return Result
     */
    public static RestResponse fail(ErrorType errorType) {
        return RestResponse.fail(errorType, null);
    }
    /**
     * 系统异常类并返回结果数据
     *
     * @param data
     * @return Result
     */
    public static RestResponse fail(Object data) {
        return RestResponse.fail(SystemErrorType.SYSTEM_ERROR, data);
    }

    // ------------------------------------------------------------------

    @JsonIgnore
    public boolean isOk() {
        return SystemCode.OK.getCode().equals(this.code);
    }
}
