package com.example.ass_management.entity;

import java.util.Date;

/**
 * @author barrie
 * @create 2022-05-05 10:23
 */
public class Activity {
    private String act_id;
    private String act_range;
    private String act_name;
    private String act_content;
////    private Date start_time;
////    private Date end_time;
//    private long start_time;
//    private long end_time;
    private String start_time;
    private String end_time;
    private String place;
    private String ass_id;
//    private String act_state;
    private String act_result;

    public String getAct_id() {
        return act_id;
    }

    public void setAct_id(String act_id) {
        this.act_id = act_id;
    }

    public String getAct_range() {
        return act_range;
    }

    public void setAct_range(String act_range) {
        this.act_range = act_range;
    }

    public String getAct_name() {
        return act_name;
    }

    public void setAct_name(String act_name) {
        this.act_name = act_name;
    }

    public String getAct_content() {
        return act_content;
    }

    public void setAct_content(String act_content) {
        this.act_content = act_content;
    }

//    public Date getStart_time() {
//        return start_time;
//    }
//
//    public void setStart_time(Date start_time) {
//        this.start_time = start_time;
//    }
//
//    public Date getEnd_time() {
//        return end_time;
//    }
//
//    public void setEnd_time(Date end_time) {
//        this.end_time = end_time;
//    }


//    public long getStart_time() {
//        return start_time;
//    }
//
//    public void setStart_time(long start_time) {
//        this.start_time = start_time;
//    }
//
//    public long getEnd_time() {
//        return end_time;
//    }
//
//    public void setEnd_time(long end_time) {
//        this.end_time = end_time;
//    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getAss_id() {
        return ass_id;
    }

    public void setAss_id(String ass_id) {
        this.ass_id = ass_id;
    }

//    public String getAct_state() {
//        return act_state;
//    }
//
//    public void setAct_state(String act_state) {
//        this.act_state = act_state;
//    }

    public String getAct_result() {
        return act_result;
    }

    public void setAct_result(String act_result) {
        this.act_result = act_result;
    }
    @Override
    public String toString() {
        return "Activity{" +
                "act_id='" + act_id + '\'' +
                ", act_range='" + act_range + '\'' +
                ", act_name='" + act_name + '\'' +
                ", act_content='" + act_content + '\'' +
                ", start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", place='" + place + '\'' +
                ", ass_id='" + ass_id + '\'' +
//                ", act_state='" + act_state + '\'' +
                ", act_result='" + act_result + '\'' +
                '}';
    }
}
