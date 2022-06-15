package com.example.ass_management;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ass_management.entity.Activity;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import static com.example.ass_management.temp.TempData.ass_name;
import static com.example.ass_management.temp.TempData.ip;
import static com.example.ass_management.temp.TempData.staticActivity;

/**
 * @author Barrie
 * @create 2022-05-13 23:03
 */
public class ApprovalActActivity extends AppCompatActivity {
    private EditText act_name;
    private EditText ass_name;
    private EditText range;
    private EditText act_place;
    private EditText start_time;
    private EditText end_time;
    private EditText act_content;
    private Button btn_submit;
    private Button btn_cancel;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval_act);
        //初始控件
        init();
        //点击事件
        click();
    }

    private void click() {
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Activity activity1 = new Activity();
                activity1.setAct_id(staticActivity.getAct_id());
                activity1.setAct_result("未通过");
                System.out.println(activity1.toString());
                new Thread(){
                    @Override
                    public void run() {
                        //继续注册
                        okhttpSub(activity1);
                    }
                }.start();
                Intent intent = new Intent(ApprovalActActivity.this,SubActApprovalActivity.class);
                SubActApprovalActivity.mActivity.finish();
                startActivity(intent);
                finish();
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Activity activity1 = new Activity();
                activity1.setAct_id(staticActivity.getAct_id());
                activity1.setAct_result("已通过");
                System.out.println(activity1.toString());
                new Thread(){
                        @Override
                        public void run() {
                            //继续注册
                            okhttpSub(activity1);
                        }
                }.start();
                Intent intent = new Intent(ApprovalActActivity.this,SubActApprovalActivity.class);
                SubActApprovalActivity.mActivity.finish();
                startActivity(intent);
                finish();
            }

        });
    }

    private void okhttpSub(Activity activity1) {
        try {
            String json = new Gson().toJson(activity1);
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(ip + "/approvalAct")
                    .post(RequestBody.create(MediaType.parse("application/json;charset=utf-8"), json))
                    .build();
            client.newCall(request).execute();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(ApprovalActActivity.this,"发布成功,即将退出页面",Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            //其他注册失败异常，比如网络未连接或其他问题
//            System.out.println(e);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(ApprovalActActivity.this,"网络异常", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void init() {
        act_name = findViewById(R.id.name);
        act_name.setText(staticActivity.getAct_name());
        act_name.setEnabled(false);
        ass_name =findViewById(R.id.ass_name);
        ass_name.setText(staticActivity.getAss_id());
        range = findViewById(R.id.range);
        range.setText(staticActivity.getAct_range());
        act_place = findViewById(R.id.place);
        act_place.setEnabled(false);
        act_place.setText(staticActivity.getPlace());
        start_time = findViewById(R.id.start);
        start_time.setText(staticActivity.getStart_time());
        end_time = findViewById(R.id.end);
        end_time.setText(staticActivity.getEnd_time());
        String strDate = staticActivity.getStart_time();
        strDate = strDate.replaceAll("T", " ");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateTime;
        try {
            dateTime = formatter.parse(strDate);
            System.out.println(formatter.format(dateTime));
            start_time.setText(formatter.format(dateTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String strDate1 = staticActivity.getEnd_time();
        strDate1 = strDate1.replaceAll("T", " ");
        Date dateTime1;
        try {
            dateTime1 = formatter.parse(strDate1);
            System.out.println(formatter.format(dateTime1));
            end_time.setText(formatter.format(dateTime1));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        act_content = findViewById(R.id.content);
        act_content.setText(staticActivity.getAct_content());
        btn_cancel = findViewById(R.id.btn_cancel);
        btn_cancel.setText("拒绝");
        btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setText("通过");
    }



    private void showLoginMsg(String msg) {
        final String title = msg;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                progressDialog.dismiss();//前面点击登录按钮时出现进度条，这里登录失败进度条消失
                //在登录界面生成对话框提示
                new AlertDialog.Builder(ApprovalActActivity.this)
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
