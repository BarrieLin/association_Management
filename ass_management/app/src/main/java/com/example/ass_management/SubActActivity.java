package com.example.ass_management;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
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

/**
 * @author Barrie
 * @create 2022-05-09 23:00
 */
public class SubActActivity extends AppCompatActivity {
    private EditText act_name;
    private RadioButton range_school;
    private RadioButton range_ass;
    private EditText act_place;
    private EditText start_time;
    private EditText end_time;
    private EditText act_content;
    private Button btn_submit;
    private Button btn_cancel;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subact);
        //初始控件
        init();
        //点击事件
        click();
        showDateOnClick(start_time);
        showDateOnClick(end_time);
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
                String activity_name = act_name.getText().toString().trim();
                String range;
                if (range_school.isChecked()){
                    range = range_school.getText().toString().trim();
                }
                else{
                    range = range_ass.getText().toString().trim();
                }
                String activity_place = act_place.getText().toString().trim();
                String begin_time = start_time.getText().toString().trim();
                String endtime = end_time.getText().toString().trim();
                String actcontent = act_content.getText().toString().trim();
//                Date etime = null;
//                Date stime = null;
//                long etime = 0;
//                long stime = 0;
//                try {
//                    stime = ConverToLong(begin_time);
//                    etime = ConverToLong(endtime);
//                    Log.d("barrie", "onClick: "+stime);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                if (activity_name.equals("") || range.equals("") || activity_place.equals("") || begin_time.equals("")
                        || endtime.equals("") || actcontent.equals("")){
                    showLoginMsg("*必填项不能为空");
                }else {
                    final Activity activity1 = new Activity();
                    activity1.setAct_name(activity_name);
                    activity1.setAct_range(range);
                    activity1.setPlace(activity_place);
                    activity1.setAct_name(activity_name);
                    activity1.setStart_time(begin_time);
                    activity1.setEnd_time(endtime);
                    activity1.setAct_content(actcontent);
                    activity1.setAss_id(ass_name[0]);
                    System.out.println(activity1.toString());
                    new Thread(){
                        @Override
                        public void run() {
                            //继续注册
                            okhttpSub(activity1);
                        }
                    }.start();
                }

            }
        });
    }

    private void okhttpSub(Activity activity1) {
        try {
            String json = new Gson().toJson(activity1);
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(ip + "/sub_act")
                    .post(RequestBody.create(MediaType.parse("application/json;charset=utf-8"), json))
                    .build();
            client.newCall(request).execute();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(SubActActivity.this,"发布成功,即将退出页面",Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }catch (Exception e){
            //其他注册失败异常，比如网络未连接或其他问题
//            System.out.println(e);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(SubActActivity.this,"网络异常", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public static Date ConverToDate(String strDate) throws Exception
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.parse(strDate);
    }

    public static long ConverToLong(String strDate) throws Exception
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.parse(strDate).getTime();
    }
    private void init() {
        act_name = findViewById(R.id.name);
        range_school = findViewById(R.id.range1);
        range_ass = findViewById(R.id.range2);
        act_place = findViewById(R.id.place);
        start_time = findViewById(R.id.start);
        end_time = findViewById(R.id.end);
        act_content = findViewById(R.id.content);
        btn_cancel = findViewById(R.id.btn_cancel);
        btn_submit = findViewById(R.id.btn_submit);
    }
    //点击打开日历，改变日期
    protected void showDateOnClick(final EditText editText){
        //点击事件传递，接触，点击打开
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    showDatePickDlg(editText);
                    return true;
                }

                return false;
            }
        });
        //改变日期
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    showDatePickDlg(editText);
                }
            }
        });
    }
    //选择日期,改变文本
    protected void showDatePickDlg(final EditText editText) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(SubActActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                editText.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
    private void showLoginMsg(String msg) {
        final String title = msg;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                progressDialog.dismiss();//前面点击登录按钮时出现进度条，这里登录失败进度条消失
                //在登录界面生成对话框提示
                new AlertDialog.Builder(SubActActivity.this)
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
