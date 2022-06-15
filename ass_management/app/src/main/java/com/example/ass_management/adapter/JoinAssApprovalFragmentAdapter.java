package com.example.ass_management.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ass_management.R;
import com.example.ass_management.entity.JoinAss;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Barrie
 * @create 2022-05-13 15:41
 */
public class JoinAssApprovalFragmentAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<JoinAss> mjoinAsses;
    public JoinAssApprovalFragmentAdapter(Context context, List<JoinAss> mjoinAsses){
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
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        JoinAss joinAss = mjoinAsses.get(i);
        View view;
        view = View.inflate(mContext, R.layout.activity_list, null);
        TextView join_people;
        TextView join_introduce;
        TextView state;
        TextView join_time;
        TextView end_time;
        join_people = (TextView) view.findViewById(R.id.act_name);
        join_introduce = (TextView) view.findViewById(R.id.act_content);
        state = (TextView) view.findViewById(R.id.ass);
        join_time = (TextView) view.findViewById(R.id.starttime);
        join_people.setText(joinAss.getJoin_people());
        join_introduce.setText(joinAss.getJoin_introduction());
        state.setText(joinAss.getState());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        join_time.setText("申请时间："+formatter.format(joinAss.getJoin_time()));
        return view;
    }
}
