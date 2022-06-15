package com.example.ass_management;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;


import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.example.ass_management.base.BaseFragment;
import com.example.ass_management.fragment.ProgressFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Barrie
 * @create 2022-05-12 14:04
 */
public class ProgressActivity extends AppCompatActivity {
    public static Activity mActivity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        mActivity = this;
        //初始标题栏
        Toolbar toolbar = findViewById(R.id.tb_register_back);
        setSupportActionBar(toolbar);

        //显示返回按钮
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        initView();
        initFragment();
    }

    private void initFragment() {
        ProgressFragment fragment = new ProgressFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.progress_content,fragment);
        ft.commit();
    }

    private void initView() {
    }


    /**
     * 监听标题栏按钮点击事件.
     *
     * @param item 按钮
     * @return 结果
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //返回按钮点击事件
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
