package com.xgj.qiaxue.adapter;

import android.view.View;

import com.xgj.qiaxue.R;
import com.xgj.qiaxue.base.recyclerviewbase.BaseQuickAdapter;
import com.xgj.qiaxue.base.recyclerviewbase.BaseViewHolder;
import com.xgj.qiaxue.bean.StudentInfo;
import com.xgj.qiaxue.bean.TeacherInfo;

import java.util.List;

/**
 * Created by qi.yang on 2018/3/8 0008.
 */

public class StudentAdapter extends BaseQuickAdapter<StudentInfo,BaseViewHolder> {

    ClickListener listener;
    boolean isDelete = false;

    public StudentAdapter(int layoutResId, List<StudentInfo> data, ClickListener listener, boolean isDelete) {
        super(layoutResId, data);
        this.listener = listener;
        this.isDelete = isDelete;
    }

    public void setIsDelete( boolean isDelete){
        this.isDelete = isDelete;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder helper, final StudentInfo item) {
        helper.setVisible(R.id.img_delete,isDelete);

    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int positions) {
        super.onBindViewHolder(holder, positions);
        holder.getView(R.id.ll_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onclickEdit(positions);
            }
        });
        holder.getView(R.id.img_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onclickDelete(positions);
            }
        });
    }

    public interface ClickListener{
        void onclickEdit(Object object);
        void onclickDelete(Object object);
    }
}
