package com.xgj.qiaxue.activity.classes;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xgj.qiaxue.R;
import com.xgj.qiaxue.base.BaseActivity;
import com.xgj.qiaxue.http.BaseRes;

import butterknife.BindView;

/**
 * 搜索老师
 * Created by qi.yang on 2018/5/8 0008.
 */

public class SearchTeacherActivity extends BaseActivity {
    @BindView(R.id.img_search)
    ImageView img_search;
    @BindView(R.id.img_cancel)
    ImageView img_cancel;
    @BindView(R.id.img_head)
    ImageView img_head;
    @BindView(R.id.et_content)
    EditText et_content;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_noData)
    TextView tv_noData;
    @BindView(R.id.bt_invite)
    Button bt_invite;
    @BindView(R.id.rl_result)
    RelativeLayout rl_result;






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
        mTitle.setText("邀请老师");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_teacher;
    }

    boolean isTestData = false;

    @Override
    protected void initViews() {
        et_content.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    isTestData = !isTestData;
                    tv_noData.setVisibility(!isTestData ? View.VISIBLE :View.GONE);
                    rl_result.setVisibility(isTestData ? View.VISIBLE :View.GONE);

                }
                return false;
            }
        });
        img_cancel.setVisibility(View.GONE);
        tv_noData.setVisibility(View.GONE);
        rl_result.setVisibility(View.GONE);
        et_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(TextUtils.isEmpty(et_content.getText().toString())){
                    img_search.setVisibility(View.VISIBLE);
                    img_cancel.setVisibility(View.GONE);
                }else{
                    img_search.setVisibility(View.GONE);
                    img_cancel.setVisibility(View.VISIBLE);
                }
            }
        });


    }




    @Override
    protected void initData() throws IllegalAccessException {

    }

    @Override
    protected void setOnClickEvents() {

    }
}
