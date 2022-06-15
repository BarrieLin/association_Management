package com.example.association_management.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.association_management.R;
import com.example.association_management.adapter.FirstFragmentAdapter;
import com.example.association_management.base.BaseFragment;

/**
 * @author Barrie
 * @create 2022-03-16 10:05
 * 首页框架的fragment
 */
public class FirstFragment extends BaseFragment {
    private ListView mListView;
    private String[] datas;
    private FirstFragmentAdapter adapter;
    private static final String TAG = FirstFragment.class.getSimpleName();

    @Override
    protected View initView() {
        Log.e(TAG,"首页框架Fragment页面被初始化了。。。。");
        View view = View.inflate(mContext, R.layout.fragment_first,null);
        mListView = view.findViewById(R.id.listview);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String data = datas[i];
                Toast.makeText(mContext, "data=="+data,Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        Log.e(TAG,"首页框架Fragment页面被初始化了");
        //准备数据
        datas = new String[]{"OKHttp", "xUtils3","Retrofit2","Fresco","Glide","greenDao","RxJava","volley","Gson","FastJson","picasso","evenBus","jcvideoplayer","pulltorefresh","Expandablelistview","UniversalVideoView","....."};
        //设置适配器
        adapter = new FirstFragmentAdapter(mContext,datas);
        mListView.setAdapter(adapter);
    }
}
