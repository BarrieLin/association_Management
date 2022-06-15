package com.example.ass_management.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ass_management.R;
import com.example.ass_management.entity.Association;
import com.example.ass_management.entity.JoinAss;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Barrie
 * @create 2022-03-16 15:07
 */
public class JoinAssFragmentAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<JoinAss> mjoinAsses;
    public JoinAssFragmentAdapter(Context context, List<JoinAss> mjoinAsses){
        this.mContext = context;
        this.mjoinAsses = mjoinAsses;
    }
    @Override
    public int getCount() {
        return mjoinAsses.size();
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
        JoinAss joinAss = mjoinAsses.get(i);
        View view;
        view = View.inflate(mContext, R.layout.activity_list, null);
        TextView ass_name;
        TextView join_introduce;
        TextView state;
        TextView join_time;
        TextView end_time;
        ass_name = (TextView) view.findViewById(R.id.act_name);
        join_introduce = (TextView) view.findViewById(R.id.act_content);
        state = (TextView) view.findViewById(R.id.ass);
        join_time = (TextView) view.findViewById(R.id.starttime);
        ass_name.setText(joinAss.getAss_name());
        join_introduce.setText(joinAss.getJoin_introduction());
        state.setText(joinAss.getState());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        join_time.setText("申请时间："+formatter.format(joinAss.getJoin_time()));
        return view;
    }
}
