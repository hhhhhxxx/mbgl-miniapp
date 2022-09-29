package com.hhhhhx.mbgl.entity.enums;

public enum NoticeType {

    // 有操作
    APPLY_CONNECT(1001,"申请关注通知"),

    // 普通通知 无操作
    INFO(222,"普通通知");


    Integer code;
    String name;

    NoticeType(Integer code, String name) {
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
