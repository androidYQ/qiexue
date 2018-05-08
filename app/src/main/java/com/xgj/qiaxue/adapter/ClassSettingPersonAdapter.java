package com.xgj.qiaxue.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xgj.qiaxue.R;

/**
 * Created by qi.yang on 2018/5/7 0007.
 */

public class ClassSettingPersonAdapter extends BaseAdapter {
    Context context;
    boolean isTeacher = false;

    public ClassSettingPersonAdapter(Context context, boolean isTeacher) {
        this.context = context;
        this.isTeacher = isTeacher;
    }



    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.griditem_teacher_or_student,null);
        ImageView img_head = (ImageView) convertView.findViewById(R.id.img_head);
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);

        if(!isTeacher){
            tv_name.setVisibility(View.GONE);
            if(getCount() == 1){
                tv_name.setVisibility(View.VISIBLE);
                tv_name.setText("添加");
            }
            if(position == getCount() - 1){
                img_head.setImageResource(R.drawable.ic_addhead80);
            }

        }


        return convertView;
    }
}
