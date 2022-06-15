package com.example.association_management.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.association_management.base.BaseFragment;

/**
 * @author Barrie
 * @create 2022-03-16 10:05
 * 社团框架的fragment
 */
public class AssFragment extends BaseFragment {
    private static final String TAG = AssFragment.class.getSimpleName();
    private TextView textView;
    @Override
    protected View initView() {
        Log.e(TAG,"社团框架Fragment页面被初始化了。。。。");
        textView = new TextView(mContext);
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    protected void initData() {
        super.initData();
        Log.e(TAG,"社团框架Fragment页面被初始化了");
        textView.setText("社团框架页面");
    }
}
