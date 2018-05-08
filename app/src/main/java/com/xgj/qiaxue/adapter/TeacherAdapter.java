package com.xgj.qiaxue.adapter;

import android.view.View;

import com.xgj.qiaxue.R;
import com.xgj.qiaxue.base.recyclerviewbase.BaseQuickAdapter;
import com.xgj.qiaxue.base.recyclerviewbase.BaseViewHolder;
import com.xgj.qiaxue.bean.ClassPostInfo;
import com.xgj.qiaxue.bean.TeacherInfo;

import java.util.List;

/**
 * Created by qi.yang on 2018/3/8 0008.
 */

public class TeacherAdapter extends BaseQuickAdapter<TeacherInfo,BaseViewHolder> {

    ClickListener listener;
    boolean isDelete = false;

    public TeacherAdapter(int layoutResId, List<TeacherInfo> data, ClickListener listener,boolean isDelete) {
        super(layoutResId, data);
        this.listener = listener;
        this.isDelete = isDelete;
    }

    @Override
    protected void convert(BaseViewHolder helper, final TeacherInfo item) {
        helper.setVisible(R.id.ll_edit,!isDelete).setVisible(R.id.img_delete,isDelete);

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
