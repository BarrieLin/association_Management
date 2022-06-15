package com.example.ass_management.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ass_management.R;
import com.example.ass_management.entity.Activity;
import com.example.ass_management.entity.BuildAss;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Barrie
 * @create 2022-05-13 22:34
 */
public class SubAssApprovalAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<Activity> mActivities;
    public SubAssApprovalAdapter(Context context, List<Activity> mActivities){
        this.mContext = context;
        this.mActivities = mActivities;
    }
    @Override
    public int getCount() {
        return mActivities.size();
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
        Activity activity = mActivities.get(i);
        View view;
        view = View.inflate(mContext, R.layout.activity_list, null);
        TextView act_name;
        TextView act_content;
        TextView state;
        TextView ass_name;
        TextView place;
        act_name = (TextView) view.findViewById(R.id.act_name);
        act_content = (TextView) view.findViewById(R.id.act_content);
        state = (TextView) view.findViewById(R.id.ass);
        ass_name = (TextView) view.findViewById(R.id.starttime);
        place = (TextView) view.findViewById(R.id.endtime);
        act_name.setText(activity.getAct_name());
        act_content.setText(activity.getAct_content());
        ass_name.setText("社团："+activity.getAss_id());
        place.setText("地点："+activity.getPlace());
        state.setText(activity.getAct_result());
        return view;
    }
}
