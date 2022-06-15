package com.example.ass_management;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.ass_management.entity.Student;

import static com.example.ass_management.temp.TempData.student;

public class LauncherActivity extends Activity {
    private String userid;
    private String userpassword;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        //读取登录保存的用户，判断是否进行自动登录
//        SharedPreferences userInfo = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);
//        userid = userInfo.getString("userid", null);//读取age
//        userpassword = userInfo.getString("userpassword", null);//读取age
//        username = userInfo.getString("username", null);//读取username
//        Student student1 = new Student();
//        student1.setStu_id(userid);
//        student1.setStu_password(userpassword);
//        student1.setStu_name(username);
//        if (userid.equals("") && userpassword.equals("")){
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    //在主线程中执行
//                    startLoginActivity();
//                }
//            }, 2000);
//        }
//        else {
//            student = student1;
//            System.out.println(".............................");
//            System.out.println(student.toString());
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    //在主线程中执行
//                    startMainActivity();
//                }
//            }, 2000);
//        }
        startLoginActivity ();
    }
        /**
         * 启动主页面
         */
    private void startLoginActivity () {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        //关闭当前页面
        finish();

    }
    private void startMainActivity () {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        //关闭当前页面
        finish();

    }
}