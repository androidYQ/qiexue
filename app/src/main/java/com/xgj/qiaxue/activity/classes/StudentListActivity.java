package com.xgj.qiaxue.activity.classes;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xgj.qiaxue.R;
import com.xgj.qiaxue.adapter.StudentAdapter;
import com.xgj.qiaxue.adapter.TeacherAdapter;
import com.xgj.qiaxue.base.BaseActivity;
import com.xgj.qiaxue.bean.StudentInfo;
import com.xgj.qiaxue.bean.TeacherInfo;
import com.xgj.qiaxue.dialog.MakeSureDialog;
import com.xgj.qiaxue.http.BaseRes;
import com.xgj.qiaxue.utils.DensityUtil;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 学生列表
 * Created by qi.yang on 2018/5/7 0007.
 */

public class StudentListActivity extends BaseActivity implements StudentAdapter.ClickListener {
    @BindView(R.id.rv_teachers)
    RecyclerView rv_students;

    StudentAdapter adapter;
    ArrayList<StudentInfo> studentInfos = new ArrayList<>();
    boolean isDelete = false;


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
    protected void initHead(LinearLayout mIconBack, TextView mTitle, TextView mTvRight, final ImageView mIvRight) {
        mTitle.setText("学员");
        mTvRight.setVisibility(View.VISIBLE);
        mTvRight.setText(isDelete ?"取消": "移除");
        mTvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                changeDeleteStatus();
            }
        });

    }

    private void changeDeleteStatus(){
        isDelete = !isDelete;
        mTvRight.setText(isDelete ?"取消": "移除");
        adapter.setIsDelete(isDelete);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_teacher_list;
    }

    @Override
    protected void initViews() {
        studentInfos.add(new StudentInfo());
        studentInfos.add(new StudentInfo());
        studentInfos.add(new StudentInfo());

        rv_students.setLayoutManager(new LinearLayoutManager(this));
        adapter = new StudentAdapter(R.layout.listitem_student,studentInfos,this,isDelete);
        rv_students.setAdapter(adapter);
    }

    @Override
    protected void initData() throws IllegalAccessException {

    }

    @Override
    protected void setOnClickEvents() {

    }






    @Override
    public void onclickEdit(Object object) {

    }

    @Override
    public void onclickDelete(Object object) {
        int position = (int) object;
        final MakeSureDialog makeSureDialog = new MakeSureDialog(this,"移除将不可修复，确定要移除"+studentInfos.get(position).getId()+"吗？","确定");
        makeSureDialog.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeSureDialog.dismiss();
            }
        });
    }
}
