package com.hhhhhx.mbgl.entity.enums;

import lombok.Data;


public enum MessageType {
    APPLY_CONNECT(10001,"申请与医生建立联系"),
    CANCEL_CONNECT(10002,"取消与医生的联系");

    Integer code;
    String name;

    MessageType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
