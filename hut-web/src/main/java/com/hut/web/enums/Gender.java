package com.hut.web.enums;

/**
 * Created by Jared on 2017/1/19.
 */
public enum Gender {

    MALE("男",1),
    FEMALE("女",0),
    SECRET("保密",-1);

    private String sex;
    private int value;

    Gender(String sex, int value) {
        this.sex = sex;
        this.value = value;
    }

    public String getSex() {
        return sex;
    }

    public int getValue() {
        return value;
    }
}
