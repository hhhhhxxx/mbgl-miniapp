package com.hhhhhx.mbgl.entity.enums;

public enum NoticeOption {

    CONFIRM(11,"确认"),
    REFUSED(22,"拒绝"),
    INIT(0,"初始化");

    Integer code;
    String name;

    NoticeOption(Integer code, String name) {
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
