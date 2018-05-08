package com.xgj.qiaxue.adapter;

import android.view.View;

import com.xgj.qiaxue.R;
import com.xgj.qiaxue.base.recyclerviewbase.BaseQuickAdapter;
import com.xgj.qiaxue.base.recyclerviewbase.BaseViewHolder;
import com.xgj.qiaxue.bean.ClassPostInfo;
import com.xgj.qiaxue.bean.OrganizationInfo;

import java.util.List;

/**
 * Created by qi.yang on 2018/3/8 0008.
 */

public class OrganizationListAdapter extends BaseQuickAdapter<OrganizationInfo,BaseViewHolder> {

    ClickListener listener;

    public OrganizationListAdapter(int layoutResId, List<OrganizationInfo> data, ClickListener listener) {
        super(layoutResId, data);
        this.listener = listener;
    }

    @Override
    protected void convert(BaseViewHolder helper, final OrganizationInfo item) {
        helper.setText(R.id.tv_name,item.getName());
        helper.setVisible(R.id.img_selected,item.isSelected());

    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int positions) {
        super.onBindViewHolder(holder, positions);
        if(holder.getView(R.id.rl_item) != null) {
            holder.getView(R.id.rl_item).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onclick(positions);
                }
            });
        }
    }

    public interface ClickListener{
        void onclick(Object object);
    }
}
