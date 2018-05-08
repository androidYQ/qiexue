package com.xgj.qiaxue.activity.login;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xgj.qiaxue.R;
import com.xgj.qiaxue.base.BaseActivity;
import com.xgj.qiaxue.base.ConstantsPool;
import com.xgj.qiaxue.bean.eventinfo.EB_LoginSuccess;
import com.xgj.qiaxue.http.BaseRes;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 设置密码/忘记密码
 * Created by qi.yang on 2018/5/3 0003.
 */

public class SetPasswordActivity extends EditCommitActivity {



    boolean isForget = false;


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

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_set_password;
    }

    @Override
    protected void initViews() {
        super.initViews();
        EventBus.getDefault().register(this);
        if(getIntent().getExtras() != null){
            isForget = getIntent().getExtras().getBoolean(ConstantsPool.IS_FORGET,false);
        }

        mTitle.setText(isForget ? "重置密码" : "设置密码");
        bt_commit.setText(isForget ? "确定" : "下一步");

        setTextLength(6);
    }

    @Override
    protected void initData() throws IllegalAccessException {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EB_LoginSuccess eb_loginSuccess){
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Override
    protected void setOnClickEvents() {

    }

    @Override
    protected void submit() {
        super.submit();
        if(!isForget) {
            readyGo(SetNicknameActivity.class);
        }else {
            finish();
        }
    }
}
