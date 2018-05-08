package com.xgj.qiaxue.fragment;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xgj.qiaxue.R;
import com.xgj.qiaxue.activity.classes.ClassesListActivity;
import com.xgj.qiaxue.base.XGJBaseFragment;

/**
 * Created by qi.yang on 2018/5/3 0003.
 */

public class WorkFragment extends XGJBaseFragment implements View.OnClickListener{
    RelativeLayout rl_classes;


    @Override
    protected void initHead(LinearLayout mIconBack, TextView mTitle, ImageView mTvLeft, TextView mTvRight, ImageView mIvRight) {

    }

    @Override
    protected int getContentViewID() {
        return R.layout.fragment_menu;
    }

    @Override
    protected void initView(View view) {
        rl_classes  = (RelativeLayout) view.findViewById(R.id.rl_classes);
        rl_classes.setOnClickListener(this);
    }

    @Override
    protected Context initContext() {
        return null;
    }

    @Override
    protected boolean isInvalidateView() {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_classes:
                readyGo(ClassesListActivity.class);
                break;

        }
    }
}
