package com.example.ass_management.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ass_management.R;

import com.example.ass_management.entity.Association;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Barrie
 * @create 2022-03-16 15:07
 */
public class AsssFragmentAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<Association> mAssociations;
    public AsssFragmentAdapter(Context context, List<Association> mAssociations){
        this.mContext = context;
        this.mAssociations = mAssociations;
    }
    @Override
    public int getCount() {
        return mAssociations.size();
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
        Association association = mAssociations.get(i);
        View view;
        view = View.inflate(mContext, R.layout.association_list, null);
        TextView ass_name;
        TextView ass_type;
        TextView ass_leader;
        TextView ass_place;
        TextView buildAss_time;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ass_name = (TextView) view.findViewById(R.id.ass_name);
        ass_type = (TextView) view.findViewById(R.id.ass_type);
        ass_leader = (TextView) view.findViewById(R.id.ass_leader);
        ass_place = (TextView) view.findViewById(R.id.ass_place);
        buildAss_time = (TextView) view.findViewById(R.id.buildAss_time);
//        System.out.println(association.getAss_name()+association.getType()+association.getLeader()+association.getPlace()+formatter.format(association.getBuild_time()));
        ass_name.setText(association.getAss_name());
        ass_type.setText("类型："+association.getType());
        ass_leader.setText("社长："+association.getLeader());
        ass_place.setText("社团地点："+association.getPlace());
        System.out.println("-------------------------------------");
        buildAss_time.setText("创建时间："+formatter.format(association.getBuild_time()));
//        ass_name.setText(activity.getass_name());
//        ass_type.setText(activity.getPlace());
//        String strDate = activity.getass_place();
//        strDate = strDate.replaceAll("T", " ");
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date dateTime;
//        try {
//            dateTime = formatter.parse(strDate);
//            System.out.println(formatter.format(dateTime));
//            ass_place.setText("开始："+formatter.format(dateTime));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        String strDate1 = activity.getEnd_time();
//        strDate1 = strDate1.replaceAll("T", " ");
//        Date dateTime1;
//        try {
//            dateTime1 = formatter.parse(strDate1);
//            System.out.println(formatter.format(dateTime1));
//            end_time.setText("开始："+formatter.format(dateTime1));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        ass_name.setText("社团："+activity.getAss_id());
        return view;
    }
}
