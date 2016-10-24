package com.activity;

import android.content.Context;
import android.widget.ListView;

import com.R;
import com.adapter.DetailAdapter;
import com.bean.UserBean;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;

import java.util.ArrayList;
import java.util.List;

import Base.BaseActivity;
import butterknife.BindView;

public class MineDetailActivity extends BaseActivity {

    @BindView(R.id.lv_detail)
    ListView mLvDetail;//详情list
    @BindView(R.id.refresh)
    MaterialRefreshLayout mRefresh;

    private List<UserBean> mUserBeans = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_mine_detail;
    }

    @Override
    protected void initView() {
        setTitle(getString(R.string.str_mine_detail_title));
        setBack();
    }

    @Override
    protected void setListener() {
        mRefresh.setLoadMore(true);
        mRefresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                try {
                    Thread.sleep(200);
                    mRefresh.finishRefresh();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
            }
        });

    }

    @Override
    protected void processLogic() {
        initData();
        DetailAdapter mDetailAdapter = new DetailAdapter(getActivityContext(), R.layout.detail_item, mUserBeans);
        mLvDetail.setAdapter(mDetailAdapter);

    }

    @Override
    protected Context getActivityContext() {
        return this;
    }

    /**
     * 初始化数据
     */

    private void initData() {

        for (int i = 0; i < 19; i++) {
            UserBean userBean = new UserBean();
            if (i == 9) {
                userBean.name = null;
                userBean.age = null;
            } else {
                userBean.name = "name" + i;
                userBean.age = i + "";
            }
            mUserBeans.add(userBean);
        }
    }


}
