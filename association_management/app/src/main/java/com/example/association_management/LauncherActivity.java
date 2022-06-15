package com.example.association_management;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class LauncherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //在主线程中执行
                startMainActivity();
            }
        }, 2000);
    }
        /**
         * 启动主页面
         */
    private void startMainActivity () {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        //关闭当前页面
        finish();

    }

}