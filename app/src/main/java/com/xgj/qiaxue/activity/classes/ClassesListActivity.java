package com.xgj.qiaxue.activity.classes;

import android.app.Dialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xgj.qiaxue.R;
import com.xgj.qiaxue.adapter.ClassManagerAdapter;
import com.xgj.qiaxue.base.BaseActivity;
import com.xgj.qiaxue.bean.ClassesInfo;
import com.xgj.qiaxue.http.BaseRes;
import com.xgj.qiaxue.utils.DensityUtil;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 班级管理
 * Created by qi.yang on 2018/5/7 0004.
 */

public class ClassesListActivity extends BaseActivity implements ClassManagerAdapter.ClickListener {
    @BindView(R.id.rv_classes)
    RecyclerView rv_classes;

    ClassManagerAdapter adapter;
    ArrayList<ClassesInfo> classesInfos = new ArrayList<>();

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
        mTitle.setText("班级管理");
        mTvRight.setText("创建");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_classes_list;
    }

    int test = 0;

    @Override
    protected void onResume() {
        super.onResume();
        if (++test % 2 == 0) {
            adapter.setNewData(null);
            adapter.setEmptyView(noDataView);
            mTvRight.setVisibility(View.VISIBLE);
        } else {
            adapter.setNewData(classesInfos);
            mTvRight.setVisibility(View.GONE);
        }

    }

    View noDataView;
    Button bt_commit;

    @Override
    protected void initViews() {
        adapter = new ClassManagerAdapter(R.layout.listitem_classes, classesInfos, this);
        rv_classes.setLayoutManager(new LinearLayoutManager(this));
        rv_classes.setAdapter(adapter);
        noDataView = getLayoutInflater().inflate(R.layout.layout_no_data_classes, (ViewGroup) rv_classes.getParent(), false);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) noDataView.getLayoutParams();
        layoutParams.height = DensityUtil.getWindowHeight(this);
        noDataView.setLayoutParams(layoutParams);

        bt_commit = (Button) noDataView.findViewById(R.id.bt_commit);
        bt_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readyGo(CreateClassActivity.class);
            }
        });
        mTvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readyGo(CreateClassActivity.class);


            }
        });
    }


    @Override
    protected void initData() throws IllegalAccessException {
        classesInfos.add(new ClassesInfo());
        classesInfos.add(new ClassesInfo());

        adapter.notifyDataSetChanged();
        showCreateSuccessDialog();
    }

    @Override
    protected void setOnClickEvents() {

    }

    @Override
    public void onclick(Object object) {
        readyGo(ClassSettingActivity.class);

    }



    Dialog createSuccessDialog;

    private void showCreateSuccessDialog(){
        createSuccessDialog = new Dialog(this,R.style.dialog);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_create_class_success,null);
        createSuccessDialog.setContentView(view);
        Button bt_invite = (Button) view.findViewById(R.id.bt_invite);
        ImageView img_cancel = (ImageView) view.findViewById(R.id.img_cancel);
        Window win = createSuccessDialog.getWindow();
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.FILL_PARENT;
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        win.setAttributes(lp);
        bt_invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showShareDialog();
                createSuccessDialog.dismiss();
            }
        });

        img_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSuccessDialog.dismiss();
            }
        });

        createSuccessDialog.show();

    }
    Dialog shareDialog;

    private void showShareDialog(){
        shareDialog = new Dialog(this,R.style.dialog);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_invite_student_by_wx,null);
        shareDialog.setContentView(view);

        TextView tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
        ImageView img_wx = (ImageView) view.findViewById(R.id.img_wx);


        Window win = shareDialog.getWindow();
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.FILL_PARENT;
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        win.setAttributes(lp);
        img_wx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                shareDialog.dismiss();
            }
        });

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareDialog.dismiss();
            }
        });

        shareDialog.show();

    }



}
