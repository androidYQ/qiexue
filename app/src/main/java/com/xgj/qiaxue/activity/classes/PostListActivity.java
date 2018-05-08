package com.xgj.qiaxue.activity.classes;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xgj.qiaxue.R;
import com.xgj.qiaxue.adapter.ClassPostAdapter;
import com.xgj.qiaxue.base.BaseActivity;
import com.xgj.qiaxue.base.ConstantsPool;
import com.xgj.qiaxue.base.XGJActManager;
import com.xgj.qiaxue.bean.ClassPostInfo;
import com.xgj.qiaxue.dialog.MakeSureDialog;
import com.xgj.qiaxue.http.BaseRes;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 职务列表
 * Created by qi.yang on 2018/5/4 0004.
 */

public class PostListActivity extends BaseActivity implements ClassPostAdapter.ClickListener {
    @BindView(R.id.rv_post)
    RecyclerView rv_post;
    @BindView(R.id.rl_other)
    RelativeLayout rl_other;
    @BindView(R.id.et_post)
    EditText et_post;
    @BindView(R.id.tv_sure)
    TextView tv_sure;


    ArrayList<ClassPostInfo> classPostInfos = new ArrayList<>();
    ClassPostAdapter adapter;
    int selectedIndex = -1;



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
        mTitle.setText("班级职务");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_post_list;
    }

    @Override
    protected void initViews() {
        classPostInfos.add(new ClassPostInfo(ConstantsPool.CHARGE_TEACHER,"班主任"));
        classPostInfos.add(new ClassPostInfo(ConstantsPool.MASTER_TEACHER,"教务老师"));
        classPostInfos.add(new ClassPostInfo(ConstantsPool.HELPER_TEACHER,"助教"));
        classPostInfos.add(new ClassPostInfo(ConstantsPool.OTHER_TEACHER,"其他"));
        adapter = new ClassPostAdapter(R.layout.listitem_post,classPostInfos,this);
        rv_post.setLayoutManager(new LinearLayoutManager(this));
        rv_post.setAdapter(adapter);
        tv_sure.setEnabled(false);
        tv_sure.setAlpha(0.4f);
        rl_other.setVisibility(View.GONE);
        if(getIntent().getExtras() != null){
            ClassPostInfo postInfo = (ClassPostInfo) getIntent().getExtras().getSerializable(ConstantsPool.CHOOSE_POST_RESULT_DATA);
            if(postInfo == null){
                return;
            }

            for(int i = 0; i < classPostInfos.size() ; i ++){
                if(postInfo.getType() == classPostInfos.get(i).getType()){
                    selectedIndex = i;
                    classPostInfos.set(i,postInfo);

                    if(i == classPostInfos.size() -1){
                        rl_other.setVisibility(View.VISIBLE);
                        et_post.setText(postInfo.getValue());
                        tv_sure.setEnabled(true);
                        tv_sure.setAlpha(1.0f);
                    }
                }
            }

            adapter.notifyDataSetChanged();
        }


    }

    @Override
    protected void initData() throws IllegalAccessException {

    }

    @Override
    protected void setOnClickEvents() {
        et_post.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(TextUtils.isEmpty(et_post.getText().toString())){
                    tv_sure.setAlpha(0.4f);
                    tv_sure.setEnabled(false);
                }else{
                    tv_sure.setAlpha(1.0f);
                    tv_sure.setEnabled(true);
                }
            }
        });

        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classPostInfos.get(selectedIndex).setValue(et_post.getText().toString());
                returnData(classPostInfos.get(selectedIndex));
            }
        });


    }

    /**
     * 返回职务
     * @param postInfo
     */
    private void returnData(ClassPostInfo postInfo){
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ConstantsPool.CHOOSE_POST_RESULT_DATA,postInfo);
        intent.putExtras(bundle);
        setResult(ConstantsPool.CHOOSE_POST_RESULT_CODE,intent);
        finish();
    }

    @Override
    public void onclick(Object object) {
        int position = (int) object;
        if(position == selectedIndex){
            return;
        }
        if(selectedIndex != -1 ){
            classPostInfos.get(selectedIndex).setSelected(false);
        }
        classPostInfos.get(position).setSelected(true);
        selectedIndex = position;
        adapter.notifyDataSetChanged();
        if(position == classPostInfos.size() - 1){
            rl_other.setVisibility(View.VISIBLE);
        }else{
            rl_other.setVisibility(View.GONE);
            returnData(classPostInfos.get(position));
        }
    }

    @Override
    protected void clickBack() {
        if(selectedIndex == classPostInfos.size() -1 ){
            final MakeSureDialog dialog = new MakeSureDialog(this,"要放弃编辑的内容吗？","放弃");
            dialog.setListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    dialog.dismiss();
                }
            });
        }else{
            onBackPressed();
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                clickBack();
                return true;
        }
        return false;
    }


    Dialog giveUpDialog;

    private void showGiveUpDialog() {
        if (giveUpDialog != null) {
            giveUpDialog.dismiss();
        }
        giveUpDialog = new Dialog(this, R.style.dialog);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_choose_post_tip, null);
        giveUpDialog.setContentView(view);
        TextView tv_giveUp = (TextView) view.findViewById(R.id.tv_giveUp);
        TextView tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);


        tv_giveUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                giveUpDialog.dismiss();
            }
        });

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                giveUpDialog.dismiss();
            }
        });

        Window win = giveUpDialog.getWindow();
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.FILL_PARENT;
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        win.setAttributes(lp);
        giveUpDialog.show();

    }

}
