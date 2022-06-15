package com.example.ass_management;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ass_management.entity.EnterStatus;
import com.example.ass_management.entity.Student;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.ass_management.temp.TempData.ip;
import static com.example.ass_management.temp.TempData.student;

/**
 * @author Barrie
 * @create 2022-03-24 16:35
 */
public class RegisterActivity extends AppCompatActivity {
    private EditText id;
    private EditText name;
    private EditText password;
    private RadioButton male;
    private RadioButton female;
    private EditText major;
    private EditText phone;
    private Button btn_submit;
    private Button btn_cancel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //初始控件
        init();
        //点击事件
        click();
    }

    //点击事件
    private void click() {
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stu_id = id.getText().toString().trim();
                String stu_name = name.getText().toString().trim();
                String stu_password = password.getText().toString().trim();
                String sex;
                if (male.isChecked()){
                    sex = male.getText().toString().trim();
                }
                else{
                    sex = female.getText().toString().trim();
                }
                String major_class = major.getText().toString().trim();
                String stu_phone = phone.getText().toString().trim();
                //判断空值
                if (stu_id.equals("") || stu_name.equals("") || stu_password.equals("") ||
                sex.equals("") || major_class.equals("")){
                    showLoginMsg("*必填项不能为空");
                }
                else {
                    if (stu_phone.length()>0 && stu_phone.length() != 11){
                        showLoginMsg("请输入正确的联系方式");
                    }
                    else {
                        final Student tempStudent = new Student();
                        tempStudent.setStu_id(stu_id);
                        tempStudent.setStu_name(stu_name);
                        tempStudent.setStu_password(stu_password);
                        tempStudent.setSex(sex);
                        tempStudent.setMajor_class(major_class);
                        tempStudent.setStu_phone(stu_phone);
                        student = tempStudent;
                        new Thread(){
                            @Override
                            public void run() {
                                //继续注册
                                okhttpRegister(tempStudent);
                            }
                        }.start();
                    }

                }
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    //注册账号
    private void okhttpRegister(Student student) {
        try {
            String json = new Gson().toJson(student);
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(ip + "/stu_register")
                    .post(RequestBody.create(MediaType.parse("application/json;charset=utf-8"),json))
                    .build();
            Response response = client.newCall(request).execute();
            String backJson = response.body().string();
            EnterStatus regStatus = new Gson().fromJson(backJson, EnterStatus.class);
            if (regStatus.getCode() == EnterStatus.named){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(RegisterActivity.this,"用户名已存在",Toast.LENGTH_SHORT).show();
                    }
                });
            }
            //如果状态码为1,则注册的用户名已存在
            else if (regStatus.getCode() == EnterStatus.unnamed){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(RegisterActivity.this,"用户名已存在",Toast.LENGTH_SHORT).show();
                    }
                });
            }
            //如果状态码为2,则注册的用户名账号密码不匹配，这个状态码在注册这里不存在啥意义，空函数就行
            else if (regStatus.getCode() == EnterStatus.wrongPass){}
            //如果状态码为3,则注册成功
            else if (regStatus.getCode() == EnterStatus.pass){
                //okhttp需要在线程内运行，如果Toast需要在okhttp内运行需要使用runOnUiThread方法
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                    }
                });
                //注册成功跳转到主界面
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
            //如果状态码为4,则注册过程密码为空
            else if (regStatus.getCode() == EnterStatus.invalid){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(RegisterActivity.this,"密码为空",Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }catch (Exception e){
            //其他注册失败异常，比如网络未连接或其他问题
//            System.out.println(e);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(RegisterActivity.this,"网络异常", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    //初始控件
    private void init() {
        id = findViewById(R.id.stu_id);
        name = findViewById(R.id.stu_name);
        password = findViewById(R.id.stu_password);
        male =findViewById(R.id.male);
        female =findViewById(R.id.female);
        major = findViewById(R.id.major_class);
        phone = findViewById(R.id.stu_phone);
        btn_submit = findViewById(R.id.btn_submit);
        btn_cancel = findViewById(R.id.btn_cancel);
    }
    private void showLoginMsg(String msg) {
        final String title = msg;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                progressDialog.dismiss();//前面点击登录按钮时出现进度条，这里登录失败进度条消失
                //在登录界面生成对话框提示
                new AlertDialog.Builder(RegisterActivity.this)
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
}
