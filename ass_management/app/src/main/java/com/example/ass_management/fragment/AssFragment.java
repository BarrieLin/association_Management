package com.example.ass_management.fragment;

import android.app.AlertDialog;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.ass_management.R;
import com.example.ass_management.adapter.AsssFragmentAdapter;
import com.example.ass_management.adapter.FirstFragmentAdapter;
import com.example.ass_management.base.BaseFragment;
import com.example.ass_management.entity.Activity;
import com.example.ass_management.entity.Association;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.ass_management.temp.TempData.ip;

/**
 * @author Barrie
 * @create 2022-03-16 10:05
 * 社团框架的fragment
 */
public class AssFragment extends BaseFragment {
    private static final String TAG = AssFragment.class.getSimpleName();
    private ListView mListView;
    private AsssFragmentAdapter adapter;
    private MutableLiveData<List<Association>> ocaBillsMutableLiveData;
    private List<Association> associations = new ArrayList<>();

    @Override
    protected View initView() {
        ocaBillsMutableLiveData = new MutableLiveData<>();
        Log.e(TAG,"首页框架Fragment页面被初始化了。。。。");
        View view = View.inflate(mContext, R.layout.first_fragment,null);
        mListView = view.findViewById(R.id.listview);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Association association = associations.get(i);
                AlertDialog dialog;
                dialog = new AlertDialog.Builder(mContext).setTitle("社团内容")
                        .setMessage(association.getIntroduction())
                        .setPositiveButton("确认",null)
                        .create();
                dialog.show();
            }
        });
//        initData();
        return view;
    }

    @Override
    protected void initData() {
        Log.e(TAG,"首页框架Fragment页面被初始化了");
//        //准备数据
//        datas = new String[]{"活动一", "活动二","心理话剧表演","计科院篮球比赛","知识竞赛","....."};
//        //设置适配器
//        adapter = new FirstFragmentAdapter(mContext,datas);
        mListView.setAdapter(adapter);
        ocaBillsMutableLiveData.observe(this, new Observer<List<Association>>() {
            @Override
            public void onChanged(List<Association> associations) {
                adapter = new AsssFragmentAdapter(mContext,associations);
                mListView.setAdapter(adapter);
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                okhttpGetActivity();
            }
        }).start();
    }

    private void okhttpGetActivity() {
        try {
            FormBody.Builder parems = new FormBody.Builder();
            parems.add("accomplish", "0");
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(ip + "/select_all_ass")
                    .post(parems.build())
                    .build();
            client.newCall(request).enqueue(new Callback() {      //发送请求
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d("FlowFragment", "onFailure: "+e.getMessage());
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseData = response.body().string();
                    Type type = new TypeToken<List<Association>>() {}.getType();
//                    System.out.println(responseData+"-----");
                    associations = new Gson().fromJson(responseData, type);
//                    System.out.println("--------------------------------------");
                    ocaBillsMutableLiveData.postValue(associations);  //向主线程发送信息
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("失败！");
        }
    }
}