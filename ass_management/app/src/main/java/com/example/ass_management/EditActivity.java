package com.example.ass_management;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

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
 * @create 2022-04-08 10:20
 */
public class EditActivity extends AppCompatActivity {
    private EditText id;
    private EditText name;
    private RadioButton male;
    private RadioButton female;
    private EditText major;
    private EditText phone;
    private Button btn_submit;
    private Button btn_cancel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        //初始化界面
        okhttpinit();
        //初始控件
        init();
        //点击事件
        click();
    }

    private void okhttpinit() {
    }
    private void okhttpedit(Student student) {
        try {
            String json = new Gson().toJson(student);
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(ip + "/stu_edit")
                    .post(RequestBody.create(MediaType.parse("application/json;charset=utf-8"), json))
                    .build();
            client.newCall(request).execute();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(EditActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            //其他注册失败异常，比如网络未连接或其他问题
//            System.out.println(e);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(EditActivity.this,"网络异常", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    private void click() {
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stu_id = id.getText().toString().trim();
                String stu_name = name.getText().toString().trim();
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
                if (stu_id.equals("") || stu_name.equals("") || sex.equals("") || major_class.equals("")){
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
                        tempStudent.setSex(sex);
                        tempStudent.setMajor_class(major_class);
                        tempStudent.setStu_phone(stu_phone);
                        student = tempStudent;
                        new Thread(){
                            @Override
                            public void run() {
                                //继续注册
                                okhttpedit(tempStudent);
                            }
                        }.start();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(EditActivity.this,MainActivity.class);
                                startActivity(intent);
                                //关闭当前页面
                                finish();
                            }
                        }, 2000);

                    }

                }
            }
        });
    }

    private void init() {
        id = findViewById(R.id.stu_id);
        name = findViewById(R.id.stu_name);
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
                new AlertDialog.Builder(EditActivity.this)
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

