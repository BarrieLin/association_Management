package com.example.ass_management.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ass_management.R;
import com.example.ass_management.entity.Activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Barrie
 * @create 2022-03-16 15:07
 */
public class FirstFragmentAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<Activity> mActivities;
    public FirstFragmentAdapter(Context context, List<Activity> mActivities){
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
        TextView ass_name;
        TextView start_time;
        TextView end_time;
        act_name = (TextView) view.findViewById(R.id.act_name);
        act_content = (TextView) view.findViewById(R.id.act_content);
        ass_name = (TextView) view.findViewById(R.id.ass);
        start_time = (TextView) view.findViewById(R.id.starttime);
        end_time = (TextView) view.findViewById(R.id.endtime);
        act_name.setText(activity.getAct_name());
        act_content.setText(activity.getPlace());
        String strDate = activity.getStart_time();
        strDate = strDate.replaceAll("T", " ");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateTime;
        try {
            dateTime = formatter.parse(strDate);
            System.out.println(formatter.format(dateTime));
            start_time.setText("开始："+formatter.format(dateTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String strDate1 = activity.getEnd_time();
        strDate1 = strDate1.replaceAll("T", " ");
        Date dateTime1;
        try {
            dateTime1 = formatter.parse(strDate1);
            System.out.println(formatter.format(dateTime1));
            end_time.setText("开始："+formatter.format(dateTime1));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ass_name.setText("社团："+activity.getAss_id());
        return view;
    }
}
