package com.example.ass_management.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ass_management.BuildAssActivity;
import com.example.ass_management.BuildAssApprovalActivity;
import com.example.ass_management.JoinAssActivity;
import com.example.ass_management.JoinAssApprovalActivity;
import com.example.ass_management.ProgressActivity;
import com.example.ass_management.ProgressSecondActivity;
import com.example.ass_management.R;
import com.example.ass_management.SubActActivity;
import com.example.ass_management.SubActApprovalActivity;
import com.example.ass_management.base.BaseFragment;
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

import static com.example.ass_management.temp.TempData.staticAdmin;
import static com.example.ass_management.temp.TempData.ass_name;
import static com.example.ass_management.temp.TempData.ip;
import static com.example.ass_management.temp.TempData.student;

/**
 * @author Barrie
 * @create 2022-03-16 10:05
 * 服务框架的fragment
 */
public class ServiceFragment extends BaseFragment {
    private static final String TAG = ServiceFragment.class.getSimpleName();
    private TextView textView;
    private Button join_ass;
    private Button build_ass;
    private Button progress_manage;
    private Button progress_management;
    private Button approval2;
    private Button sub_activity;
    private Button join_ass_approve;
    private Button ass_approval;
    private Button act_approval;
    private List<Association> associations = new ArrayList<>();
    @Override
    protected View initView() {
        Log.e(TAG,"服务框架Fragment页面被初始化了。。。。");
        View view = View.inflate(mContext, R.layout.service_fragment,null);
        //点击进入入团申请
        okhttpLeadAss();
        join_ass = view.findViewById(R.id.join);
        join_ass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (staticAdmin == null){
                    startActivity(new Intent(getActivity(), JoinAssActivity.class));
                }
                if (staticAdmin != null){
                    Toast.makeText(mContext,"你没有该权限",Toast.LENGTH_SHORT).show();
                }
            }
        });
        //点击进入建团申请
        build_ass = view.findViewById(R.id.build);
        build_ass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), BuildAssActivity.class));
            }
        });
        //点击进入进度管理
        progress_manage = view.findViewById(R.id.progress);
        progress_manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ProgressActivity.class));
            }
        });
        //点击进入进度管理
        progress_management = view.findViewById(R.id.progress1);
        progress_management.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ProgressSecondActivity.class));
            }
        });
        //发布活动
        sub_activity = view.findViewById(R.id.sub_act);
        sub_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ass_name[0] == null){
                    Toast.makeText(mContext,"你不是社团社长",Toast.LENGTH_SHORT).show();
                }
                if (ass_name[0] != null){
                    startActivity(new Intent(getActivity(), SubActActivity.class));
                }
//                startActivity(new Intent(getActivity(), SubActActivity.class));
            }
        });
        join_ass_approve = view.findViewById(R.id.approve);
        join_ass_approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ass_name[0] == null){
                    Toast.makeText(mContext,"你不是社团社长",Toast.LENGTH_SHORT).show();
                }
                if (ass_name[0] != null){
                    startActivity(new Intent(getActivity(), JoinAssApprovalActivity.class));
                }
            }
        });
        //点击审批按钮进入审批管理
        act_approval = view.findViewById(R.id.act_approval);
        act_approval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (staticAdmin == null){
                    Toast.makeText(mContext,"你没有该权限",Toast.LENGTH_SHORT).show();
                }
                if (staticAdmin != null){
                    startActivity(new Intent(getActivity(), SubActApprovalActivity.class));
                }
            }
        });
        ass_approval = view.findViewById(R.id.ass_approval);
        ass_approval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (staticAdmin == null){
                    Toast.makeText(mContext,"你没有该权限",Toast.LENGTH_SHORT).show();
                }
                if (staticAdmin != null){
                    startActivity(new Intent(getActivity(), BuildAssApprovalActivity.class));
                }
            }
        });
        return view;
    }

    private void okhttpLeadAss() {
        try {
            FormBody.Builder parems = new FormBody.Builder();
            parems.add("stu_id", student.getStu_id());
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(ip + "/lead_ass")
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
                    System.out.println(responseData);
                    Type type = new TypeToken<List<Association>>() {}.getType();
//                    System.out.println(responseData+"-----");
                    associations = new Gson().fromJson(responseData, type);
                    for (int i = 0 ;i<associations.size();i++){
                        ass_name[i] = associations.get(i).getAss_name();
                        System.out.println(ass_name[i]);
                    }
//                    System.out.println("--------------------------------------");
//                    ocaBillsMutableLiveData.postValue(activities);  //向主线程发送信息
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("失败！");
        }
    }

    @Override
    protected void initData() {
        super.initData();
        Log.e(TAG,"服务框架Fragment页面被初始化了");
    }

}
