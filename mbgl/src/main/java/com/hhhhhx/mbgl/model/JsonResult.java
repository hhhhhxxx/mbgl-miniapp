package com.hhhhhx.mbgl.model;

import lombok.Data;

@Data
public class JsonResult<T> {

    private T data;
    private String code;
    private String msg;

    /**
     * 若没有数据返回，默认状态码为 1，提示信息为“操作成功！”
     */
    public JsonResult() {
        this.code = "1";
        this.msg = "操作成功！";
    }

    public JsonResult(T data, String code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public JsonResult(T data) {
        this.data = data;
        this.code = "1";
        this.msg = "操作成功！";
    }
}
