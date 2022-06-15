package com.example.ass_management;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ass_management.entity.Student;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import static com.example.ass_management.temp.TempData.ip;
import static com.example.ass_management.temp.TempData.student;

/**
 * @author Barrie
 * @create 2022-04-28 16:27
 */
public class EditpwActivity extends AppCompatActivity {
    private EditText native_password;
    private EditText new_password1;
    private EditText new_password2;
    private Button btn_submit;
    private Button btn_cancel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editps);
        //初始控件
        init();
        //点击事件
        click();
    }

    private void okhttpeditpw(Student student) {
        try {
            String json = new Gson().toJson(student);
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(ip + "/stu_edit_pw")
                    .post(RequestBody.create(MediaType.parse("application/json;charset=utf-8"), json))
                    .build();
            client.newCall(request).execute();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(EditpwActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            //其他注册失败异常，比如网络未连接或其他问题
//            System.out.println(e);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(EditpwActivity.this,"网络异常", Toast.LENGTH_SHORT).show();
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
                String np = native_password.getText().toString().trim();
                String np1 = new_password1.getText().toString().trim();
                String np2 = new_password2.getText().toString().trim();
                System.out.println(np+"   "+np1+"   "+np2);
                if (np1.equals(np2)){
                    if (np.equals(student.getStu_password())){
                        final Student student1 = new Student();
                        student1.setStu_id(student.getStu_id());
                        student1.setStu_password(np1);
                        new Thread(){
                            @Override
                            public void run() {
                                //继续注册
                                okhttpeditpw(student1);
                            }
                        }.start();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(EditpwActivity.this,LoginActivity.class);
                                startActivity(intent);
                                //关闭当前页面
                                finish();
                            }
                        }, 2000);
                        SharedPreferences userInfo = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);
                        SharedPreferences.Editor editor = userInfo.edit();//获取Editor
                        editor.clear();
                        editor.commit();
                    }else showLoginMsg("输入原密码错误");
                }else showLoginMsg("新密码不一致");
            }
        });
    }

    private void init() {
        native_password = findViewById(R.id.native_password);
        new_password1 =findViewById(R.id.new_password1);
        new_password2 = findViewById(R.id.new_password2);
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
                new AlertDialog.Builder(EditpwActivity.this)
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
