package com.xgj.qiaxue.activity.classes;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xgj.qiaxue.R;
import com.xgj.qiaxue.adapter.ClassSettingPersonAdapter;
import com.xgj.qiaxue.base.BaseActivity;
import com.xgj.qiaxue.http.BaseRes;
import com.xgj.qiaxue.widget.MyGridView;

import butterknife.BindView;

/**
 * 班级设置
 * Created by qi.yang on 2018/5/7 0007.
 */

public class ClassSettingActivity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.gv_teacher)
    MyGridView gv_teacher;
    @BindView(R.id.gv_student)
    MyGridView gv_student;
    @BindView(R.id.rl_classInfo)
    RelativeLayout rl_classInfo;
    @BindView(R.id.rl_teacher)
    RelativeLayout rl_teacher;
    @BindView(R.id.rl_student)
    RelativeLayout rl_student;

    ClassSettingPersonAdapter teachAdapter;
    ClassSettingPersonAdapter studentAdapter;


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
        mTitle.setText("班级设置");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_classes_setting;
    }

    @Override
    protected void initViews() {
        teachAdapter = new ClassSettingPersonAdapter(this,true);
        studentAdapter = new ClassSettingPersonAdapter(this,false);
        gv_teacher.setAdapter(teachAdapter);
        gv_student.setAdapter(studentAdapter);
        gv_student.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 6){
                    showShareDialog();
                }
            }
        });

    }

    @Override
    protected void initData() throws IllegalAccessException {

    }

    @Override
    protected void setOnClickEvents() {
        rl_classInfo.setOnClickListener(this);
        rl_teacher.setOnClickListener(this);
        rl_student.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_classInfo:
                readyGo(EditClassInfoActivity.class);
                break;
            case R.id.rl_teacher:
                readyGo(TeacherListActivity.class);
                break;
            case R.id.rl_student:
                readyGo(StudentListActivity.class);
                break;
        }
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
