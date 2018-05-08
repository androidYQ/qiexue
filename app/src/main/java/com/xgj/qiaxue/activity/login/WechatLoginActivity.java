package com.xgj.qiaxue.activity.login;

import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xgj.qiaxue.MainActivity;
import com.xgj.qiaxue.R;
import com.xgj.qiaxue.base.BaseActivity;
import com.xgj.qiaxue.bean.eventinfo.EB_LoginSuccess;
import com.xgj.qiaxue.http.BaseRes;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

/**
 * Created by qi.yang on 2018/5/3 0003.
 */

public class WechatLoginActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.img_head)
    ImageView img_head;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.et_content)
    EditText et_phone;
    @BindView(R.id.et_code)
    EditText et_code;
    @BindView(R.id.img_cancel)
    ImageView img_cancelPhone;
    @BindView(R.id.img_cancel_code)
    ImageView img_cancelCode;
    @BindView(R.id.tv_getCode)
    TextView tv_getCode;
    @BindView(R.id.bt_commit)
    Button bt_commit;
    boolean isCountDown = false;//正在倒计时

    private CountDownTimer timer = new CountDownTimer(60 * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            isCountDown = true;

            tv_getCode.setText((millisUntilFinished / 1000) + "s后重新发送");
        }

        @Override
        public void onFinish() {
            tv_getCode.setEnabled(true);
            isCountDown = false;
            tv_getCode.setText("重新获取验证码");
            et_phone.setEnabled(true);
        }
    };


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
        mTitle.setText("绑定手机号");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wechat_login;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() throws IllegalAccessException {

    }

    @Override
    protected void setOnClickEvents() {
        img_cancelPhone.setOnClickListener(this);
        img_cancelCode.setOnClickListener(this);
        tv_getCode.setOnClickListener(this);
        bt_commit.setOnClickListener(this);
        et_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String phone = et_phone.getText().toString();
                String code = et_code.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    img_cancelPhone.setVisibility(View.INVISIBLE);
                    if (!isCountDown) {//非倒计时状态才能更改颜色
                        tv_getCode.setTextColor(getResources().getColor(R.color.gray_light));
                    }
                    tv_getCode.setEnabled(false);
                } else if (phone.length() == 11) {
                    img_cancelPhone.setVisibility(View.VISIBLE);
                    if (!isCountDown) {
                        tv_getCode.setTextColor(getResources().getColor(R.color.main_theme_color));
                        tv_getCode.setEnabled(true);
                    }

                } else {
                    if (!isCountDown) {
                        tv_getCode.setTextColor(getResources().getColor(R.color.gray_light));
                    }
                    tv_getCode.setEnabled(false);
                    img_cancelPhone.setVisibility(View.VISIBLE);
                }

                if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(code)) {
                    setClickAble(true);
                } else {
                    setClickAble(false);
                }

            }
        });
        et_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String phone = et_phone.getText().toString();
                String code = et_code.getText().toString();


                if (!TextUtils.isEmpty(code)) {
                    img_cancelCode.setVisibility(View.VISIBLE);
                    if (!TextUtils.isEmpty(phone))
                        setClickAble(true);
                } else {
                    img_cancelCode.setVisibility(View.INVISIBLE);
                    setClickAble(false);
                }

            }
        });


        tv_getCode.setEnabled(false);
        img_cancelPhone.setVisibility(View.INVISIBLE);
        img_cancelCode.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_getCode:
                timer.start();
                tv_getCode.setEnabled(false);
                et_phone.setEnabled(false);
                img_cancelPhone.setVisibility(View.GONE);
                break;
            case R.id.img_cancel:
                et_phone.setText("");
                break;
            case R.id.img_cancel_code:
                et_code.setText("");
                break;
            case R.id.bt_commit:
                readyGo(MainActivity.class);
                EventBus.getDefault().post(new EB_LoginSuccess());
                finish();
                break;

        }
    }

    private void setClickAble(boolean clickAble) {
        bt_commit.setEnabled(clickAble);
        bt_commit.setAlpha(clickAble ? 1.0f : 0.4f);
    }
}
