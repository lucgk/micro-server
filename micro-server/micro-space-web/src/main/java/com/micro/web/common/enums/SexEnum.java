package com.micro.web.common.enums;

public enum SexEnum {

    MALE(1,"男"),FEMAL(-1,"女");

    private Integer code;
    private String desc;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    SexEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
