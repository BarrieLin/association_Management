package com.example.ass_management.fragment;

import android.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.ass_management.R;
import com.example.ass_management.adapter.FirstFragmentAdapter;
import com.example.ass_management.base.BaseFragment;
import com.example.ass_management.entity.Activity;
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
 * 首页框架的fragment
 */
public class FirstFragment extends BaseFragment {
    private ListView mListView;
//    private String[] datas;
    private FirstFragmentAdapter adapter;
    private static final String TAG = FirstFragment.class.getSimpleName();
    private MutableLiveData<List<Activity>> ocaBillsMutableLiveData;
    private List<Activity> activities = new ArrayList<>();

    @Override
    protected View initView() {
        ocaBillsMutableLiveData = new MutableLiveData<>();
        Log.e(TAG,"首页框架Fragment页面被初始化了。。。。");
        View view = View.inflate(mContext, R.layout.first_fragment,null);
        mListView = view.findViewById(R.id.listview);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Activity activity = activities.get(i);
                AlertDialog dialog;
                dialog = new AlertDialog.Builder(mContext).setTitle("活动内容")
                        .setMessage(activity.getAct_content())
                        .setPositiveButton("确认",null)
                        .create();
                dialog.show();
            }
        });
        return view;
    }
    @Override
    protected void initData() {
        Log.e(TAG,"首页框架Fragment页面被初始化了");
        mListView.setAdapter(adapter);
        ocaBillsMutableLiveData.observe(this, new Observer<List<Activity>>() {
            @Override
            public void onChanged(List<Activity> activities) {
                adapter = new FirstFragmentAdapter(mContext,activities);
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
                    .url(ip + "/select_out_act")
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
                    Type type = new TypeToken<List<Activity>>() {}.getType();
                    System.out.println(responseData+"-----");
                    activities = new Gson().fromJson(responseData, type);
                    System.out.println("--------------------------------------");
                    ocaBillsMutableLiveData.postValue(activities);  //向主线程发送信息
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("失败！");
        }
    }
}
