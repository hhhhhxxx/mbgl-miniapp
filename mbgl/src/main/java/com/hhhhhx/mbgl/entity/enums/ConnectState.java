package com.hhhhhx.mbgl.entity.enums;

public enum ConnectState {
    APPLY(2,"申请关系"),
    SUCEESS(1,"已成功建立");

    Integer code;
    String name;

    ConnectState(Integer code, String name) {
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
