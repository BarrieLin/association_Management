package com.example.ass_management.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.ass_management.EditAsActivity;
import com.example.ass_management.EditJsActivity;
import com.example.ass_management.R;
import com.example.ass_management.adapter.BuildAssFragmentAdapter;
import com.example.ass_management.adapter.JoinAssFragmentAdapter;
import com.example.ass_management.base.BaseFragment;
import com.example.ass_management.entity.BuildAss;
import com.example.ass_management.entity.JoinAss;
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
import static com.example.ass_management.temp.TempData.staticBuildAss;
import static com.example.ass_management.temp.TempData.student;

/**
 * @author Barrie
 * @create 2022-05-12 22:59
 */
public class ProgressSecondFragment extends BaseFragment {
    private static final String TAG = ProgressSecondFragment.class.getSimpleName();
    private ListView mListView;
    private BuildAssFragmentAdapter adapter;
    private MutableLiveData<List<BuildAss>> ocaBillsMutableLiveData;
    private List<BuildAss> buildAsses = new ArrayList<>();

    @Override
    protected View initView() {
        ocaBillsMutableLiveData = new MutableLiveData<>();
        Log.e(TAG,"加入社团申请框架Fragment页面被初始化了。。。。");
        View view = View.inflate(mContext, R.layout.first_fragment,null);
        mListView = view.findViewById(R.id.listview);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                BuildAss buildAss = buildAsses.get(i);
                staticBuildAss = new BuildAss();
                staticBuildAss = buildAss;
                Log.e(TAG,"进入修改");
                startActivity(new Intent(getActivity(), EditAsActivity.class));}
        });
//        initData();
        return view;
    }

    @Override
    protected void initData() {
        Log.e(TAG,"加入社团申请框架Fragment页面被初始化了");
//        //准备数据
//        datas = new String[]{"活动一", "活动二","心理话剧表演","计科院篮球比赛","知识竞赛","....."};
//        //设置适配器
//        adapter = new FirstFragmentAdapter(mContext,datas);
        mListView.setAdapter(adapter);
        ocaBillsMutableLiveData.observe(this, new Observer<List<BuildAss>>() {
            @Override
            public void onChanged(List<BuildAss> buildAsses) {
                adapter = new BuildAssFragmentAdapter(mContext,buildAsses);
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
            parems.add("stu_id", student.getStu_id());
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(ip + "/selectIdBuildAss")
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
                    Type type = new TypeToken<List<BuildAss>>() {}.getType();
                    System.out.println(responseData+"-----");
                    buildAsses = new Gson().fromJson(responseData, type);
//                    System.out.println("--------------------------------------");
                    ocaBillsMutableLiveData.postValue(buildAsses);  //向主线程发送信息
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("失败！");
        }
    }
}
