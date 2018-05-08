package com.xgj.qiaxue.activity.classes;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.xgj.qiaxue.R;
import com.xgj.qiaxue.base.BaseActivity;
import com.xgj.qiaxue.base.ConstantsPool;
import com.xgj.qiaxue.bean.ClassPostInfo;
import com.xgj.qiaxue.bean.OrganizationInfo;
import com.xgj.qiaxue.http.BaseRes;
import com.xgj.qiaxue.utils.ToastUtil;

import java.io.File;

import butterknife.BindView;

/**
 * Created by qi.yang on 2018/5/3 0003.
 */

public class CreateClassActivity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.img_head)
    ImageView img_head;
    @BindView(R.id.et_className)
    EditText et_className;
    @BindView(R.id.rl_post)
    RelativeLayout rl_post;
    @BindView(R.id.tv_post)
    TextView tv_post;
    @BindView(R.id.rl_relate)
    RelativeLayout rl_relate;
    @BindView(R.id.rl_discrete)
    RelativeLayout rl_discrete;
    @BindView(R.id.tv_organization)
    TextView tv_organization;
    @BindView(R.id.tv_relateOrganization)
    TextView tv_relateOrganization;
    @BindView(R.id.tv_discreteOrganization)
    TextView tv_discreteOrganization;
    @BindView(R.id.img_cancel)
    ImageView img_cancel;

    boolean isRelate = true;//是否关联机构
    ClassPostInfo postInfo;//职务
    OrganizationInfo organizationInfo;//机构



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
        mTitle.setText("创建班级");
        mTvRight.setVisibility(View.VISIBLE);
        mTvRight.setText("保存");
        mTvRight.setOnClickListener(this);
        mTvRight.setAlpha(0.4f);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_create_class;
    }

    @Override
    protected void initViews() {
        tv_relateOrganization.setSelected(isRelate);
        img_cancel.setVisibility(View.INVISIBLE);
        isShouldTakeAPhoto = true;//需要拍照
    }

    @Override
    protected void initData() throws IllegalAccessException {

    }

    @Override
    protected void setOnClickEvents() {
        img_head.setOnClickListener(this);
        tv_post.setOnClickListener(this);
        rl_relate.setOnClickListener(this);
        rl_discrete.setOnClickListener(this);
        tv_organization.setOnClickListener(this);
        img_cancel.setOnClickListener(this);
        et_className.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(TextUtils.isEmpty(et_className.getText().toString())){
                    img_cancel.setVisibility(View.INVISIBLE);
                }else{
                    img_cancel.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_head:
                showGetPhotoDialog();
                break;

            case R.id.tv_post:
                startActivityForResult(new Intent(this,PostListActivity.class).putExtra(ConstantsPool.CHOOSE_POST_RESULT_DATA,postInfo),ConstantsPool.CHOOSE_POST_RESULT_CODE);
                break;
            case R.id.rl_relate:
                isRelate = true;
                tv_relateOrganization.setSelected(isRelate);
                tv_discreteOrganization.setSelected(!isRelate);
                break;
            case R.id.rl_discrete:
                isRelate = false;
                tv_relateOrganization.setSelected(isRelate);
                tv_discreteOrganization.setSelected(!isRelate);

                break;

            case R.id.tv_organization:
                startActivityForResult(new Intent(this,OrganizationListActivity.class).putExtra(ConstantsPool.CHOOSE_ORGANIZATION_RESULT_DATA,organizationInfo),ConstantsPool.CHOOSE_ORGANIZATION_RESULT_CODE);
                break;
            case R.id.img_cancel:
                et_className.setText("");
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ConstantsPool.CHOOSE_POST_RESULT_CODE && data != null){
            postInfo = (ClassPostInfo) data.getExtras().getSerializable(ConstantsPool.CHOOSE_POST_RESULT_DATA);
            tv_post.setText(postInfo.getValue());

        }else  if(requestCode == ConstantsPool.CHOOSE_ORGANIZATION_RESULT_CODE && data != null){
            organizationInfo = (OrganizationInfo) data.getExtras().getSerializable(ConstantsPool.CHOOSE_ORGANIZATION_RESULT_DATA);
            tv_organization.setText(organizationInfo.getName());

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
