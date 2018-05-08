package com.xgj.qiaxue.activity.login;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xgj.qiaxue.MainActivity;
import com.xgj.qiaxue.R;
import com.xgj.qiaxue.base.BaseActivity;
import com.xgj.qiaxue.bean.eventinfo.EB_LoginSuccess;
import com.xgj.qiaxue.http.BaseRes;

import org.greenrobot.eventbus.EventBus;

/**
 * 设置昵称
 * Created by qi.yang on 2018/5/3 0003.
 */

public class SetNicknameActivity extends EditCommitActivity {
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
        mTitle.setText("给自己取个名吧");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_set_nickname;
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

    @Override
    protected void submit() {
        super.submit();
        readyGo(MainActivity.class);
        EventBus.getDefault().post(new EB_LoginSuccess());
        finish();
    }
}
