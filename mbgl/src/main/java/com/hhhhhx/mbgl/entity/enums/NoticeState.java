package com.hhhhhx.mbgl.entity.enums;

public enum NoticeState {

    READ(1,"已读"),
    UNREAD(0,"未读");


    Integer code;
    String name;

    NoticeState(Integer code, String name) {
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
