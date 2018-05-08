package com.xgj.qiaxue.activity.login;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xgj.qiaxue.MainActivity;
import com.xgj.qiaxue.R;
import com.xgj.qiaxue.activity.classes.CreateClassActivity;
import com.xgj.qiaxue.adapter.VerifyCodeAdapter;
import com.xgj.qiaxue.base.ConstantsPool;
import com.xgj.qiaxue.base.MyApplication;
import com.xgj.qiaxue.base.XGJActManager;
import com.xgj.qiaxue.bean.UserInfo;
import com.xgj.qiaxue.bean.eventinfo.EB_LoginSuccess;
import com.xgj.qiaxue.dao.UserDao;
import com.xgj.qiaxue.http.BaseGetReq;
import com.xgj.qiaxue.http.BaseRes;
import com.xgj.qiaxue.http.URLServerConnections;
import com.xgj.qiaxue.res.LoginRes;
import com.xgj.qiaxue.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.LinkedHashMap;

import butterknife.BindView;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by qi.yang on 2018/3/15 0015.
 */

public class LoginActivity extends EditCommitActivity implements View.OnClickListener {

    @BindView(R.id.tv_wechat)
    TextView tv_wechat;

    boolean isRepeatLogin = false;


    @Override
    public void successResponse(BaseRes baseRes) {
        if (baseRes instanceof LoginRes) {
            dismissPostProgressDialog();
            LoginRes loginRes = (LoginRes) baseRes;
            UserInfo userInfo = loginRes.data;
            if (userInfo == null) {
                ToastUtil.showToast(this, "登录失败,返回信息为空");
                return;
            }
            userInfo.setMobile(et_content.getText().toString().trim());
            UserDao.insertOrReplace(loginRes.data);
            JPushInterface.setAlias(this,1,MyApplication.getUserInfo().getUserId().replaceAll("-",""));
            MyApplication.setUserInfo(MyApplication.getUserInfo());//从数据库中获取用户 成为临时变量
            readyGo(MainActivity.class);
            finish();
        }
    }

    @Override
    public void handleExceptionResponse(Class<? extends BaseRes> baseRes, String errMssg) {
        dismissPostProgressDialog();
        ToastUtil.showToast(this, errMssg);
    }

    @Override
    public void handleExceptionResponse(Class<? extends BaseRes> baseRes, String errMssg, int code) {

    }

    @Override
    protected void initHead(LinearLayout mIconBack, TextView mTitle, TextView mTvRight, ImageView mIvRight) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_info_login;
    }

    @Override
    protected void initViews() {
        EventBus.getDefault().register(this);

        super.initViews();
        tv_wechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readyGo(WechatLoginActivity.class);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EB_LoginSuccess eb_loginSuccess){
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initData() throws IllegalAccessException {
        UserInfo userInfo = MyApplication.getUserInfo();

        if (getIntent().getExtras() != null) {
            isRepeatLogin = getIntent().getExtras().getBoolean(ConstantsPool.REPEAT_LOGIN,false);

        }
        if(isRepeatLogin) {
            if (userInfo != null) {
                et_content.setText(userInfo.getMobile());
                UserDao.delete(MyApplication.getUserInfo().getID());
                MyApplication.setUserInfo(null);
            }
        }else {
            if (userInfo != null) {
                readyGo(MainActivity.class);
//                JPushInterface.setAlias(this,1,"3e8556d2-790c-46db-ae13-868f19eef6f2".replaceAll("-",""));//测试通知用
                JPushInterface.setAlias(this,1,MyApplication.getUserInfo().getUserId().replaceAll("-",""));
                finish();
            }
        }
    }

    @Override
    protected void setOnClickEvents() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        isTestGo = !isTestGo;
    }

    boolean isTestGo = false;
    /**
     * 提交
     */
    @Override
    protected void submit() {
        readyGo(isTestGo ? VerifyCodeActivity.class :LoginByPassActivity.class);
//        readyGo(LoginByPassActivity.class);
//        readyGo(MainActivity.class);

        String phone = et_content.getText().toString().trim();

        if (phone.length() != 11) {
            ToastUtil.showToast(this, "输入手机号有误");
            return;
        }

        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showToast(this, getResources().getString(R.string.info_login_phone_hint));
            return;
        }


        LinkedHashMap<String, String> map = new LinkedHashMap<>();
//            map.put("companyname", URLEncoder.encode(companyName,"utf-8"));

       /* map.put("mobile", phone);
        BaseGetReq req = new BaseGetReq(mContext, map, URLServerConnections.LOGIN, LoginRes.class, this);
        getService().jsonObjectRequest(this, req);
        showPostProgressDialog();*/

    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                XGJActManager.exitApp(this);
                return true;
        }
        return false;
    }
}
