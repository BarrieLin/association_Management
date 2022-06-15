package com.example.association_management.entity;

/**
 * @author barrie
 * @create 2022-03-19 10:39
 */
public class Student {
    private String stu_id;
    private String stu_password;
    private String stu_name;
    private String sex;
    private String major_class;
    private String stu_phone;

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public String getStu_password() {
        return stu_password;
    }

    public void setStu_password(String stu_password) {
        this.stu_password = stu_password;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMajor_class() {
        return major_class;
    }

    public void setMajor_class(String major_class) {
        this.major_class = major_class;
    }
    public String getStu_phone() {
        return stu_phone;
    }

    public void setStu_phone(String stu_phone) {
        this.stu_phone = stu_phone;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stu_id='" + stu_id + '\'' +
                ", stu_password='" + stu_password + '\'' +
                ", stu_name='" + stu_name + '\'' +
                ", sex='" + sex + '\'' +
                ", major_class='" + major_class + '\'' +
                ", stu_phone='" + stu_phone + '\'' +
                '}';
    }
}