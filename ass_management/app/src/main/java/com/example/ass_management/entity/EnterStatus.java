package com.example.ass_management.entity;

/**
 * @author barrie
 * @create 2022-03-22 16:15
 */
public class EnterStatus {
    private Integer code;
    private String curMessage;
    private String name;
    public static final Integer named = 0;
    public static final Integer unnamed = 1;
    public static final Integer wrongPass = 2;
    public static final Integer pass = 3;
    public static final Integer invalid = 4;
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getCurMessage() {
        return curMessage;
    }

    public void setCurMessage(String curMessage) {
        this.curMessage = curMessage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EnterStatus{" +
                "code=" + code +
                ", curMessage='" + curMessage + '\'' +
                '}';
    }
}
