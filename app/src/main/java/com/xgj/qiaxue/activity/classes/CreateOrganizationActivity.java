package com.xgj.qiaxue.activity.classes;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xgj.qiaxue.R;
import com.xgj.qiaxue.activity.login.EditCommitActivity;
import com.xgj.qiaxue.base.BaseActivity;
import com.xgj.qiaxue.http.BaseRes;

/**
 * 创建机构
 * Created by qi.yang on 2018/5/4 0004.
 */

public class CreateOrganizationActivity extends EditCommitActivity {


    @Override
    public void successResponse(BaseRes baseRes) {

    }

    @Override
    public void handleExceptionResponse(Class<? extends BaseRes> baseRes, String errMssg) {

    }

    @Override
    public void handleExceptionResponse(Class<? extends BaseRes> baseRes, String errMssg, int code) {

    }

    @Override
    protected void initHead(LinearLayout mIconBack, TextView mTitle, TextView mTvRight, ImageView mIvRight) {
        mTitle.setText("创建机构");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_create_organization;
    }

    @Override
    protected void initViews() {
        super.initViews();
        setTextLength(1);
    }

    @Override
    protected void initData() throws IllegalAccessException {

    }

    @Override
    protected void setOnClickEvents() {

    }
}
