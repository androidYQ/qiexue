package com.xgj.qiaxue.activity.login;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xgj.qiaxue.MainActivity;
import com.xgj.qiaxue.R;
import com.xgj.qiaxue.base.BaseActivity;
import com.xgj.qiaxue.base.ConstantsPool;
import com.xgj.qiaxue.bean.eventinfo.EB_LoginSuccess;
import com.xgj.qiaxue.http.BaseRes;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

/**
 * Created by qi.yang on 2018/5/3 0003.
 */

public class LoginByPassActivity extends EditCommitActivity {
    @BindView(R.id.tv_forgetPass)
    TextView tv_forgetPass;


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
        mTitle.setText("登录");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login_by_password;
    }

    @Override
    protected void initViews() {
        super.initViews();
        tv_forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(mContext,VerifyCodeActivity.class).putExtra(ConstantsPool.IS_FORGET,true));
            }
        });
        setTextLength(6);

    }

    @Override
    protected void initData() throws IllegalAccessException {

    }

    @Override
    protected void submit() {
        super.submit();
        readyGo(MainActivity.class);
        EventBus.getDefault().post(new EB_LoginSuccess());
        finish();

    }

    @Override
    protected void setOnClickEvents() {

    }
}
