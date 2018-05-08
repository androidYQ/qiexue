package com.xgj.qiaxue.activity.classes;

import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.xgj.qiaxue.R;
import com.xgj.qiaxue.base.BaseActivity;
import com.xgj.qiaxue.http.BaseRes;

import java.io.File;

import butterknife.BindView;

/**
 * Created by qi.yang on 2018/5/7 0007.
 */

public class EditClassInfoActivity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.img_head)
    ImageView img_head;
    @BindView(R.id.tv_edit)
    TextView tv_edit;
    @BindView(R.id.et_content)
    EditText et_content;
    @BindView(R.id.img_cancel)
    ImageView img_cancel;







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
        mTitle.setText("编辑班级信息");
        mTvRight.setVisibility(View.VISIBLE);
        mTvRight.setText("保存");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_class_info;
    }

    @Override
    protected void initViews() {
        isShouldTakeAPhoto = true;
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
                    img_cancel.setVisibility(View.GONE);
                }else{
                    img_cancel.setVisibility(View.VISIBLE);
                }
            }
        });

        img_cancel.setVisibility(View.GONE);
    }

    @Override
    protected void initData() throws IllegalAccessException {

    }

    @Override
    protected void setOnClickEvents() {
        mTvRight.setOnClickListener(this);
        img_head.setOnClickListener(this);
        img_cancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.xgj_head_right_text:

                break;
            case R.id.img_head:
                showGetPhotoDialog();
                break;
            case R.id.img_cancel:
                et_content.setText("");
                break;

        }
    }

    @Override
    protected void getPhoto(File file) {
        Glide.with(this)
                .load(file)
                .asBitmap()
                .centerCrop()
                .into(new BitmapImageViewTarget(img_head) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        view.setImageDrawable(circularBitmapDrawable);
                    }
                });

    }

}
