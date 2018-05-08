package com.xgj.qiaxue.activity.login;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xgj.qiaxue.R;
import com.xgj.qiaxue.adapter.VerifyCodeAdapter;
import com.xgj.qiaxue.base.BaseActivity;
import com.xgj.qiaxue.base.ConstantsPool;
import com.xgj.qiaxue.bean.eventinfo.EB_LoginSuccess;
import com.xgj.qiaxue.http.BaseRes;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * 验证码页面  注册/忘记密码
 * Created by qi.yang on 2018/5/3 0003.
 */

public class VerifyCodeActivity extends BaseActivity {
    @BindView(R.id.et_verify)
    EditText et_verify;
    @BindView(R.id.gridView)
    GridView gridView;
    @BindView(R.id.tv_time)
    TextView tv_time;


    VerifyCodeAdapter adapter;

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


        if(getIntent().getExtras() != null){
            isForget = getIntent().getExtras().getBoolean(ConstantsPool.IS_FORGET,false);
        }

        mTitle.setText(isForget ?"忘记密码":"注册");
    }


    private CountDownTimer timer = new CountDownTimer(60*1000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            tv_time.setText((millisUntilFinished/1000)+"秒后可重新发送验证码");
        }

        @Override
        public void onFinish() {
            tv_time.setText("重新发送验证码");
            tv_time.setEnabled(true);
            tv_time.setTextColor(getResources().getColor(R.color.main_theme_color));
        }
    };


    @Override
    protected int getLayoutId() {
        return R.layout.activity_get_verify_code;
    }

    @Override
    protected void initViews() {
        EventBus.getDefault().register(this);

        adapter = new VerifyCodeAdapter(this);
        gridView.setAdapter(adapter);
        et_verify.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String verifyCode = et_verify.getText().toString();
                adapter.flush(verifyCode);
                if(!TextUtils.isEmpty(verifyCode) && verifyCode.length() == 6){
                    commit(verifyCode);
                }
            }
        });
        timer.start();
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
    protected void initData() throws IllegalAccessException {

    }

    @Override
    protected void setOnClickEvents() {
        tv_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_time.setTextColor(getResources().getColor(R.color.gray_x_light));
                tv_time.setEnabled(false);
                timer.start();
            }
        });

        tv_time.setEnabled(false);
    }

    private void commit(String verifyCode){
       startActivity(new Intent(this,SetPasswordActivity.class).putExtra(ConstantsPool.IS_FORGET,isForget));
       finish();
    }
}
