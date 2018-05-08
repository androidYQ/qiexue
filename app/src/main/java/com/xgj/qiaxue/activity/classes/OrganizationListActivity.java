package com.xgj.qiaxue.activity.classes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xgj.qiaxue.R;
import com.xgj.qiaxue.adapter.OrganizationListAdapter;
import com.xgj.qiaxue.base.BaseActivity;
import com.xgj.qiaxue.base.ConstantsPool;
import com.xgj.qiaxue.bean.ClassPostInfo;
import com.xgj.qiaxue.bean.OrganizationInfo;
import com.xgj.qiaxue.http.BaseRes;
import com.xgj.qiaxue.utils.DensityUtil;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by qi.yang on 2018/5/4 0004.
 */

public class OrganizationListActivity extends BaseActivity implements OrganizationListAdapter.ClickListener {
    @BindView(R.id.rv_organization)
    RecyclerView rv_organization;

    OrganizationListAdapter adapter;
    ArrayList<OrganizationInfo> organizationInfos = new ArrayList<>();

    int selectedIndex  = -1;
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
        mTitle.setText("关联机构");
        mTvRight.setText("创建");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_organization_list;
    }

    int test = 0;
    @Override
    protected void onResume() {
        super.onResume();
        if(++test % 2 == 0){
            adapter.setNewData(null);
            adapter.setEmptyView(noDataView);
            mTvRight.setVisibility(View.VISIBLE);
        }else{
            adapter.setNewData(organizationInfos);
            mTvRight.setVisibility(View.GONE);
        }

    }

    View noDataView;
    Button bt_commit;
    @Override
    protected void initViews() {
        adapter = new OrganizationListAdapter(R.layout.listitem_post,organizationInfos,this);
        rv_organization.setLayoutManager(new LinearLayoutManager(this));
        rv_organization.setAdapter(adapter);
        noDataView = getLayoutInflater().inflate(R.layout.layout_no_data_organization, (ViewGroup) rv_organization.getParent(), false);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) noDataView.getLayoutParams();
        layoutParams.height = DensityUtil.getWindowHeight(this);
        noDataView.setLayoutParams(layoutParams);

        bt_commit = (Button) noDataView.findViewById(R.id.bt_commit);
        bt_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readyGo(CreateOrganizationActivity.class);
            }
        });
        mTvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readyGo(CreateOrganizationActivity.class);
            }
        });
    }



    @Override
    protected void initData() throws IllegalAccessException {
        organizationInfos.add(new OrganizationInfo("1","洽学教育"));
        organizationInfos.add(new OrganizationInfo("2","新东方教育"));
        organizationInfos.add(new OrganizationInfo("3","成都教育"));
        organizationInfos.add(new OrganizationInfo("4","思琪教育"));
        if(getIntent().getExtras() != null){
            OrganizationInfo organizationInfo = (OrganizationInfo) getIntent().getExtras().getSerializable(ConstantsPool.CHOOSE_ORGANIZATION_RESULT_DATA);
            if(organizationInfo == null){
                return;
            }

            for(int i = 0; i < organizationInfos.size() ; i ++){
                if(organizationInfo.getId().equals(organizationInfos.get(i).getId())){
                    selectedIndex = i;
                    organizationInfos.set(i,organizationInfo);
                }
            }

            adapter.notifyDataSetChanged();
        }

    }

    @Override
    protected void setOnClickEvents() {

    }

    @Override
    public void onclick(Object object) {
        int position = (int) object;
        if(position == selectedIndex){
            return;
        }
        if(selectedIndex != -1 ){
            organizationInfos.get(selectedIndex).setSelected(false);
        }
        organizationInfos.get(position).setSelected(true);
        selectedIndex = position;
        adapter.notifyDataSetChanged();
        returnData(organizationInfos.get(selectedIndex));
        finish();

    }
    /**
     * 返回机构
     * @param organizationInfo
     */
    private void returnData(OrganizationInfo organizationInfo){
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ConstantsPool.CHOOSE_ORGANIZATION_RESULT_DATA,organizationInfo);
        intent.putExtras(bundle);
        setResult(ConstantsPool.CHOOSE_ORGANIZATION_RESULT_CODE,intent);
        finish();
    }


}
