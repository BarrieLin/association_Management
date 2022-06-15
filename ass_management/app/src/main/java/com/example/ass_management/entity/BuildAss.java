package com.example.ass_management.entity;

import java.util.Date;

/**
 * @author barrie
 * @create 2022-05-04 21:45
 */
public class BuildAss {
    private String build_id;
    private String build_ass_name;
    private Date build_time;
    private String sort;
    private String introduce;
    private String builder;
    private String state;

    public String getBuild_id() {
        return build_id;
    }

    public void setBuild_id(String build_id) {
        this.build_id = build_id;
    }

    public String getBuild_ass_name() {
        return build_ass_name;
    }

    public void setBuild_ass_name(String build_ass_name) {
        this.build_ass_name = build_ass_name;
    }

    public Date getBuild_time() {
        return build_time;
    }

    public void setBuild_time(Date build_time) {
        this.build_time = build_time;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getBuilder() {
        return builder;
    }

    public void setBuilder(String builder) {
        this.builder = builder;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @Override
    public String toString() {
        return "BuildAss{" +
                "build_id='" + build_id + '\'' +
                ", build_ass_name='" + build_ass_name + '\'' +
                ", build_time='" + build_time + '\'' +
                ", sort='" + sort + '\'' +
                ", introduce='" + introduce + '\'' +
                ", builder='" + builder + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
