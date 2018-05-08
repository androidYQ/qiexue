package com.xgj.qiaxue.activity.login;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xgj.qiaxue.R;
import com.xgj.qiaxue.base.BaseActivity;
import com.xgj.qiaxue.http.BaseRes;

import butterknife.BindView;

/**
 * 包含编辑，提交的公用基类Activity
 * Created by qi.yang on 2018/5/3 0003.
 */

public class EditCommitActivity extends BaseActivity {
    protected EditText et_content;
    protected ImageView img_cancelWords;
    protected Button bt_commit;

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
        return 0;
    }

    /**
     * 监听到此字符串长度时  才将按钮置为可用
     * @param length
     */
    protected void setTextLength(final int length){
        et_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(et_content.getText().toString())) {
                    setClickAble(false);
                    img_cancelWords.setVisibility(View.INVISIBLE);
                } else if (et_content.getText().toString().length() >= length) {
                    setClickAble(true);
                    img_cancelWords.setVisibility(View.VISIBLE);
                }else{
                    setClickAble(false);
                    img_cancelWords.setVisibility(View.VISIBLE);
                }
            }
        });

    }



    @Override
    protected void initViews() {
        et_content = (EditText) findViewById(R.id.et_content);
        img_cancelWords = (ImageView) findViewById(R.id.img_cancel);
        bt_commit = (Button) findViewById(R.id.bt_commit);
        img_cancelWords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_content.setText("");
                setClickAble(false);
            }
        });
        et_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(et_content.getText().toString())) {
                    setClickAble(false);
                    img_cancelWords.setVisibility(View.INVISIBLE);
                } else if (et_content.getText().toString().length() == 11) {
                    setClickAble(true);
                    img_cancelWords.setVisibility(View.VISIBLE);
                }else{
                    setClickAble(false);
                    img_cancelWords.setVisibility(View.VISIBLE);
                }
            }
        });

        bt_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        setClickAble(false);
        img_cancelWords.setVisibility(View.INVISIBLE);
    }

    private void setClickAble(boolean clickAble) {
        bt_commit.setEnabled(clickAble);
        bt_commit.setAlpha(clickAble ? 1.0f : 0.4f);
    }

    /**
     * 提交
     */
    protected void submit() {

    }

    @Override
    protected void initData() throws IllegalAccessException {

    }

    @Override
    protected void setOnClickEvents() {

    }
}
