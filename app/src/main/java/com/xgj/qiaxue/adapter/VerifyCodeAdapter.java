package com.xgj.qiaxue.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xgj.qiaxue.R;

/**
 * Created by qi.yang on 2018/5/3 0003.
 */

public class VerifyCodeAdapter extends BaseAdapter {
    private String VerifyCode = "";
    Context context;

    public VerifyCodeAdapter(Context context) {
        this.context = context;
    }

    public void flush(String verifyCode){
        if(verifyCode == null){
            this.VerifyCode = "";
        }
        this.VerifyCode = verifyCode;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return 6;
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
        convertView = LayoutInflater.from(context).inflate(R.layout.griditem_verify_code,null);

        TextView tv_code = (TextView) convertView.findViewById(R.id.tv_code);
        View line = convertView.findViewById(R.id.line);

        if(VerifyCode.length() > position){
            tv_code.setText(VerifyCode.charAt(position)+"");
        }

        int lineColor = VerifyCode.length() >= position ? context.getResources().getColor(R.color.color_333333) :context.getResources().getColor(R.color.divide_line_color);
        line.setBackgroundColor(lineColor);


        return convertView;
    }
}
