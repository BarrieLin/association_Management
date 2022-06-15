package com.example.ass_management.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.example.ass_management.EditActivity;
import com.example.ass_management.EditpwActivity;
import com.example.ass_management.LoginActivity;
import com.example.ass_management.MainActivity;
import com.example.ass_management.R;
import com.example.ass_management.base.BaseFragment;
import com.example.ass_management.temp.TempData;

import static com.example.ass_management.temp.TempData.ass_name;
import static com.example.ass_management.temp.TempData.staticAdmin;
import static com.example.ass_management.temp.TempData.student;
import static android.content.Context.MODE_PRIVATE;

/**
 * @author Barrie
 * @create 2022-03-16 10:05
 * 我的框架的fragment
 */
public class MyselfFragment extends BaseFragment {
    private static final String TAG = MyselfFragment.class.getSimpleName();
    private Button btn_quit;
    private TextView name;
    private TextView id;
    private Button edit;
    private Button edit_password;
    @Override
    protected View initView() {
        Log.e(TAG,"我的框架Fragment页面被初始化了。。。。");
        View view = View.inflate(mContext,R.layout.myself_fragment,null);
        name = view.findViewById(R.id.t_name);
        id = view.findViewById(R.id.t_number);
        if (staticAdmin == null){
            id.setText(student.getStu_id());
            name.setText(student.getStu_name());
        }
        else if (student == null){
            id.setText(staticAdmin.getAdmin_id());
            name.setText(staticAdmin.getAdmin_name());
        }
        edit = view.findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG,"修改个人信息");
                startActivity(new Intent(getActivity(), EditActivity.class));
            }
        });
        edit_password = view.findViewById(R.id.edit_password);
        edit_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG,"修改密码");
                startActivity(new Intent(getActivity(), EditpwActivity.class));
            }
        });
        btn_quit = view.findViewById(R.id.btn_quit);
        btn_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG,"我要注销");
                startActivity(new Intent(getActivity(), LoginActivity.class));
                student = null;
                staticAdmin = null;
                ass_name[0] = null;
            }
        });
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        Log.e(TAG,"我的框架Fragment页面被初始化了");
    }
}
