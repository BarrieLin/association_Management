package com.example.association_management;

import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.association_management.base.BaseFragment;
import com.example.association_management.fragment.AssFragment;
import com.example.association_management.fragment.FirstFragment;
import com.example.association_management.fragment.MyselfFragment;
import com.example.association_management.fragment.ServiceFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private RadioGroup mRg_main;
    private List<BaseFragment> mBaseFragment;
    /**
     * 选中的Fragment对应的位置
     */
    private int position;
    /**
     * 上次切换的Fragment
     */
    private Fragment mContent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化View
        initView();
        //初始化Fragment
        initFragment();
        //设置RadioGroup的监听
        setListener();
    }

    private void setListener() {
        mRg_main.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        //设置默认选中
        mRg_main.check(R.id.rb_first);
    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i){
                case R.id.rb_first://首页
                    position = 0;
                    break;
                case R.id.rb_service://服务
                    position = 1;
                    break;
                case R.id.rb_ass://社团
                    position = 2;
                    break;
                case R.id.rb_myself://我的
                    position = 3;
                    break;
                default:
                    position = 0;
                    break;
            }
            //根据对应的位置得到Fragment
            BaseFragment to = getFragment();
            //替换
            switchFragment(mContent,to);


        }
    }

    /**
     *
     * @param from 刚显示的Fragment,马上就要被隐藏了
     * @param to 马上要切换到的Fragment，一会要显示
     */
    private void switchFragment(Fragment from,Fragment to) {
        if(from != to){
            mContent = to;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //才切换
            //判断有没有被添加
            if(!to.isAdded()){
                //to没有被添加
                //from隐藏
                if(from != null){
                    ft.hide(from);
                }
                //添加to
                if(to != null){
                    ft.add(R.id.fl_content,to).commit();
                }
            }else{
                //to已经被添加
                // from隐藏
                if(from != null){
                    ft.hide(from);
                }
                //显示to
                if(to != null){
                    ft.show(to).commit();
                }
            }
        }

    }
//    private void switchFragment(BaseFragment fragment) {
//        //1.得到FragmentManger
//        FragmentManager fm = getSupportFragmentManager();
//        //2.开启事务
//        FragmentTransaction transaction = fm.beginTransaction();
//        //3.替换
//        transaction.replace(R.id.fl_content,fragment);
//        //4.提交事务
//        transaction.commit();
//    }

    /**
     * 根据对应的位置得到Fragment
     * @return
     */
    private BaseFragment getFragment() {
        BaseFragment fragment = mBaseFragment.get(position);
        return fragment;
    }

    private void initFragment() {
        mBaseFragment = new ArrayList<>();
        mBaseFragment.add(new FirstFragment());//首页框架Fragment
        mBaseFragment.add(new ServiceFragment());//服务框架Fragment
        mBaseFragment.add(new AssFragment());//社团框架Fragment
        mBaseFragment.add(new MyselfFragment());//我的框架Fragment
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        mRg_main = findViewById(R.id.rg_main);

    }
}
