package com.example.ass_management;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ass_management.entity.BuildAss;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import static com.example.ass_management.temp.TempData.ip;
import static com.example.ass_management.temp.TempData.student;

/**
 * @author Barrie
 * @create 2022-05-04 22:03
 */
public class BuildAssActivity extends AppCompatActivity {
    private EditText association_name;
    private EditText student_id;
    private EditText introduce;
    private EditText sort;
    private Button btn_submit;
    private Button btn_cancel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_ass);
        //初始控件
        init();
        //点击事件
        click();
    }
    private void okhttpbuild(BuildAss buildAss){
        try {
            String json = new Gson().toJson(buildAss);
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(ip + "/build_ass")
                    .post(RequestBody.create(MediaType.parse("application/json;charset=utf-8"), json))
                    .build();
            client.newCall(request).execute();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(BuildAssActivity.this,"申请成功",Toast.LENGTH_SHORT).show();
                }
            });
            finish();
        }catch (Exception e){
            //其他注册失败异常，比如网络未连接或其他问题
//            System.out.println(e);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(BuildAssActivity.this,"网络异常", Toast.LENGTH_SHORT).show();
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
                String ass_name = association_name.getText().toString().trim();
                String stu_id = student.getStu_id();
                String ass_introduce = introduce.getText().toString().trim();
                String ass_sort = sort.getText().toString().trim();
                if (ass_name.equals("") || stu_id.equals("") ||ass_introduce.equals("") || ass_sort.equals("")){
                    showLoginMsg("数据不能为空");
                }else {
                    final BuildAss buildAss = new BuildAss();
                    buildAss.setBuild_ass_name(ass_name);
                    buildAss.setBuilder(stu_id);
                    buildAss.setIntroduce(ass_introduce);
                    buildAss.setSort(ass_sort);
                    new Thread(){
                        @Override
                        public void run() {
                            //继续注册
                            okhttpbuild(buildAss);
                        }
                    }.start();
                }
            }
        });
    }

    private void init() {
        association_name = findViewById(R.id.ass_name);
        student_id = findViewById(R.id.builder);
        introduce = findViewById(R.id.introduce);
        sort = findViewById(R.id.sort);
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
                new AlertDialog.Builder(BuildAssActivity.this)
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
