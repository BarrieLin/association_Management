package com.example.ass_management.entity;

import java.util.Date;

/**
 * @author Barrie
 * @create 2022-05-04 18:40
 */
public class JoinAss {
    private String join_id;
    private Date join_time;
    private String join_introduction;
    private String ass_name;
    private String join_people;
    private String state;
    public String getJoin_id() {
        return join_id;
    }

    public void setJoin_id(String join_id) {
        this.join_id = join_id;
    }

    public Date getJoin_time() {
        return join_time;
    }

    public void setJoin_time(Date join_time) {
        this.join_time = join_time;
    }

    public String getJoin_introduction() {
        return join_introduction;
    }

    public void setJoin_introduction(String join_introduction) {
        this.join_introduction = join_introduction;
    }

    public String getAss_name() {
        return ass_name;
    }

    public void setAss_name(String ass_name) {
        this.ass_name = ass_name;
    }

    public String getJoin_people() {
        return join_people;
    }

    public void setJoin_people(String join_people) {
        this.join_people = join_people;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @Override
    public String toString() {
        return "JoinAss{" +
                "join_id='" + join_id + '\'' +
                ", join_time='" + join_time + '\'' +
                ", join_introduction='" + join_introduction + '\'' +
                ", ass_name='" + ass_name + '\'' +
                ", join_people='" + join_people + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
