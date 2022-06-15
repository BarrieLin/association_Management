package com.example.ass_management.temp;

import android.widget.ListView;

import com.example.ass_management.entity.Activity;
import com.example.ass_management.entity.Admin;
import com.example.ass_management.entity.Association;
import com.example.ass_management.entity.BuildAss;
import com.example.ass_management.entity.JoinAss;
import com.example.ass_management.entity.Student;

import java.util.List;

/**
 * @author Barrie
 * @create 2022-03-18 15:31
 */
public class TempData {
//    okhttp访问请求ip地址
//    本机ip
    public final static String ip = "http://10.200.33.183:8080";
    //服务器ip

    //记录登录时的学生账号
    public static Student student = null;
    //记录登录时的管理员账号
    public static Admin staticAdmin = null;
    public static String[] ass_name = new String[1];
    public static JoinAss staticJoinAss = null;
    public static BuildAss staticBuildAss = null;
    public static Activity staticActivity =null;
}