package com.xgj.qiaxue.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.xgj.qiaxue.R;
import com.xgj.qiaxue.base.recyclerviewbase.BaseQuickAdapter;
import com.xgj.qiaxue.base.recyclerviewbase.BaseViewHolder;
import com.xgj.qiaxue.bean.ClassPostInfo;
import com.xgj.qiaxue.bean.ClassesInfo;
import com.xgj.qiaxue.utils.DensityUtil;

import java.util.List;

/**
 * Created by qi.yang on 2018/3/8 0008.
 */

public class ClassManagerAdapter extends BaseQuickAdapter<ClassesInfo,BaseViewHolder> {

    ClickListener listener;

    public ClassManagerAdapter(int layoutResId, List<ClassesInfo> data, ClickListener listener) {
        super(layoutResId, data);
        this.listener = listener;
    }

    @Override
    protected void convert(BaseViewHolder helper, final ClassesInfo item) {
        RelativeLayout rl_student = helper.getView(R.id.rl_student);
        for(int i = 0; i < 6; i++){
            View view = LayoutInflater.from(mContext).inflate(R.layout.layout_student_head_img,null);
            ImageView img_head = (ImageView) view.findViewById(R.id.img_head);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) img_head.getLayoutParams();
            layoutParams.leftMargin = i * DensityUtil.dip2px(mContext,20);
            img_head.setLayoutParams(layoutParams);
            rl_student.addView(view);
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int positions) {
        super.onBindViewHolder(holder, positions);
        if(holder.getView(R.id.ll_item) == null){
            return;
        }
        holder.getView(R.id.ll_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onclick(positions);
            }
        });
    }

    public interface ClickListener{
        void onclick(Object object);
    }
}
