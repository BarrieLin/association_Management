package com.example.ass_management.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ass_management.R;
import com.example.ass_management.entity.Association;
import com.example.ass_management.entity.BuildAss;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Barrie
 * @create 2022-05-13 19:12
 */
public class BuildAssApprovalAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<BuildAss> mbuildAsses;
    public BuildAssApprovalAdapter(Context context, List<BuildAss> mbuildAsses){
        this.mContext = context;
        this.mbuildAsses = mbuildAsses;
    }
    @Override
    public int getCount() {
        return mbuildAsses.size();
    }
    @Override
    public Object getItem(int i) {
        return null;
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        BuildAss buildAss = mbuildAsses.get(i);
        View view;
        view = View.inflate(mContext, R.layout.activity_list, null);
        TextView build_ass_name;
        TextView introduce;
        TextView state;
        TextView sort;
        TextView build_time;
        build_ass_name = (TextView) view.findViewById(R.id.act_name);
        introduce = (TextView) view.findViewById(R.id.act_content);
        state = (TextView) view.findViewById(R.id.ass);
        sort = (TextView) view.findViewById(R.id.starttime);
        build_time = (TextView) view.findViewById(R.id.endtime);
        build_ass_name.setText(buildAss.getBuild_ass_name());
        introduce.setText(buildAss.getIntroduce());
        state.setText(buildAss.getState());
        sort.setText(buildAss.getSort());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        build_time.setText("申请时间："+formatter.format(buildAss.getBuild_time()));
        return view;
    }
}
