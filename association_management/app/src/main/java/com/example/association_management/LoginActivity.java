package com.example.association_management;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.association_management.entity.EnterStatus;
import com.example.association_management.entity.Student;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.association_management.temp.TempData.ip;
import static com.example.association_management.temp.TempData.student;

/**
 * @author Barrie
 * @create 2022-03-18 15:00
 */
public class LoginActivity extends AppCompatActivity {
    private EditText login_id;
    private EditText login_password;
    private Button login_button;
    private Button reg_button;
    private String userId;
    private String password;
    private static final String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        click();
    }
    //初始化控件
    private void init() {
        login_id = findViewById(R.id.login_id);
        login_password = findViewById(R.id.login_password);
        login_button = findViewById(R.id.login_button);
        reg_button = findViewById(R.id.reg_button);
    }
    //点击跳转
    private void click() {
        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                 *      输入账号密码登录功能代码
                 *
                 * */
                userId = login_id.getText().toString().trim();
                password = login_password.getText().toString().trim();
                //账号或密码为空时
                if (userId.equals("") || userId == null || password.equals("") || password == null){
                    showLoginMsg("账号密码不能为空");
                }
                else {
                    final Student tempStudent = new Student();
                    //数据打包成对象
                    tempStudent.setStu_id(userId);
                    tempStudent.setStu_password(password);
                    student = tempStudent;
                    new Thread(){
                        @Override
                        public void run() {
                            okhttpLogin(tempStudent);
                        }
                    }.start();

                }
//                //跳转测试
//                else {
//                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
            }
        });
    }
    //post登录
    private void okhttpLogin(Student student) {
        try {
            //获取对象变为json
            String json = new Gson().toJson(student);
            OkHttpClient client = new OkHttpClient();
//            System.out.println(json);
            Request request = new Request.Builder()
                    .url(ip + "/stu_login")
                    .post(RequestBody.create(MediaType.parse("application/json;charset=utf-8"),json))
                    .build();
            //获取后端返回值
            Response response = client.newCall(request).execute();
            String backJson = response.body().string();
            EnterStatus regStatus = new Gson().fromJson(backJson,EnterStatus.class);
            if (regStatus.getCode() == EnterStatus.named){ }
            //判定状态码为1，登录用户存在，返空
            else if (regStatus.getCode() == EnterStatus.unnamed){}
            //判定状态码为2，账号或密码错误
            else if (regStatus.getCode() == EnterStatus.wrongPass){
                showLoginMsg("账号或密码错误");
            }
            //判定状态码为3，登录成功
            else if (regStatus.getCode() == EnterStatus.pass){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    }
                });
                //保存用户信息
                saveUserInfo(userId,password);
                getUserInfo();
                //登录成功跳转
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
            //判定状态码为4，登录密码为空，返空
            else if (regStatus.getCode() == EnterStatus.invalid){}
        }catch (Exception e){
            //其他问题，抛出异常
            showLoginMsg("网络异常");
        }

    }

    private void showLoginMsg(String msg) {
        final String title = msg;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                progressDialog.dismiss();//前面点击登录按钮时出现进度条，这里登录失败进度条消失
                //在登录界面生成对话框提示
                new AlertDialog.Builder(LoginActivity.this)
                        .setTitle("错误")
                        .setTitle(title)
                        .setCancelable(true)
                        .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();//点击确定，对话框消失
                            }
                        }).create().show();
            }
        });
    }
    /**
     * 保存用户信息
     */
    private void saveUserInfo(String id,String password){
        SharedPreferences userInfo = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);
        SharedPreferences.Editor editor = userInfo.edit();//获取Editor
        //得到Editor后，写入需要保存的数据
        editor.putString("username", id);
        editor.putString("userpassword", password);
        editor.commit();//提交修改
        Log.i(TAG, "保存用户信息成功");
    }
    /**
     * 读取用户信息
     */
    private void getUserInfo(){
        SharedPreferences userInfo = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);
        String username = userInfo.getString("username", null);//读取username
        String userpassword = userInfo.getString("userpassword", null);//读取age
        System.out.println("username:" + username + "， userpassword:" + userpassword);
    }

    /**
     * 清空数据
     */
    private void clearUserInfo(){
        SharedPreferences userInfo = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);
        SharedPreferences.Editor editor = userInfo.edit();//获取Editor
        editor.clear();
        editor.commit();
        Log.i(TAG, "清空数据");
    }
}
