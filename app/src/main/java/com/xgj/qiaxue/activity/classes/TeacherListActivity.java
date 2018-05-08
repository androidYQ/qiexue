package com.xgj.qiaxue.activity.classes;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xgj.qiaxue.R;
import com.xgj.qiaxue.adapter.TeacherAdapter;
import com.xgj.qiaxue.base.BaseActivity;
import com.xgj.qiaxue.bean.TeacherInfo;
import com.xgj.qiaxue.http.BaseRes;
import com.xgj.qiaxue.utils.DensityUtil;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 教师列表
 * Created by qi.yang on 2018/5/7 0007.
 */

public class TeacherListActivity extends BaseActivity implements TeacherAdapter.ClickListener {
    @BindView(R.id.rv_teachers)
    RecyclerView rv_teachers;

    TeacherAdapter adapter;
    ArrayList<TeacherInfo> teacherInfos = new ArrayList<>();

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
        mTitle.setText("教师");
        mIvRight.setVisibility(View.VISIBLE);
        mIvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOperationPop(mIvRight);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_teacher_list;
    }

    @Override
    protected void initViews() {
        teacherInfos.add(new TeacherInfo());
        teacherInfos.add(new TeacherInfo());
        teacherInfos.add(new TeacherInfo());

        rv_teachers.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TeacherAdapter(R.layout.listitem_teacher,teacherInfos,this,false);
        rv_teachers.setAdapter(adapter);
    }

    @Override
    protected void initData() throws IllegalAccessException {

    }

    @Override
    protected void setOnClickEvents() {

    }


    PopupWindow operationPop;

    // 昵称修改 弹出框popuwindow
    private void showOperationPop(View view) {
        if (operationPop != null) {
            operationPop.dismiss();
        }

        View layout = LayoutInflater.from(this).inflate(R.layout.popu_teacher_operation, null);
        TextView tv_invite = (TextView) layout.findViewById(R.id.tv_invite);
        TextView tv_delete = (TextView) layout.findViewById(R.id.tv_delete);

        tv_invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readyGo(SearchTeacherActivity.class);
                operationPop.dismiss();
            }
        });

        tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readyGo(DeleteTeacherActivity.class);
                operationPop.dismiss();
            }
        });



        operationPop = new PopupWindow(layout, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // 可聚焦
        operationPop.setFocusable(true);
        // 外部可点击
        operationPop.setOutsideTouchable(true);
        operationPop.update();
        // 点击返回键隐藏（需要给PopuWindow设置背景）
//        operationPop.setBackgroundDrawable(new ColorDrawable(0xffffffff));
        operationPop.showAsDropDown(view,(DensityUtil.dip2px(this,20)),0);  //显示位置设置在view这个控件下面
//        operationPop.showAtLocation(view, Gravity.RIGHT,-(DensityUtil.dip2px(this,20)),0);
    }


    @Override
    public void onclickEdit(Object object) {
        readyGo(PostListActivity.class);
    }

    @Override
    public void onclickDelete(Object object) {

    }
}
