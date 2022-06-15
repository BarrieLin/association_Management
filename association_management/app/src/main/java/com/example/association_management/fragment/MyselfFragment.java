package com.example.association_management.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.association_management.R;
import com.example.association_management.base.BaseFragment;

/**
 * @author Barrie
 * @create 2022-03-16 10:05
 * 我的框架的fragment
 */
public class MyselfFragment extends BaseFragment {
    private static final String TAG = MyselfFragment.class.getSimpleName();
    private TextView textView;
    @Override
    protected View initView() {
        Log.e(TAG,"我的框架Fragment页面被初始化了。。。。");
        return textView;
    }

    @Override
    protected void initData() {
        super.initData();
        Log.e(TAG,"我的框架Fragment页面被初始化了");
        textView.setText("我的框架页面");
    }
}
