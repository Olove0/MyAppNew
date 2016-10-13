package com.example.master.newapp.activity;

import android.content.Context;
import android.widget.ListView;

import com.example.master.newapp.R;
import com.example.master.newapp.adapter.DetailAdapter;
import com.example.master.newapp.bean.UserBean;

import java.util.ArrayList;
import java.util.List;

import Base.BaseActivity;
import butterknife.BindView;

public class MineDetailActivity extends BaseActivity {

    @BindView(R.id.lv_detail)
    ListView mLvDetail;//详情list

    private List<UserBean> mUserBeans=new ArrayList<>();

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

    }

    @Override
    protected void processLogic() {
        initData();
        DetailAdapter mDetailAdapter=new DetailAdapter(getActivityContext(),R.layout.detail_item,mUserBeans);
        mLvDetail.setAdapter(mDetailAdapter);

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

    @Override
    protected Context getActivityContext() {
        return this;
    }

}
