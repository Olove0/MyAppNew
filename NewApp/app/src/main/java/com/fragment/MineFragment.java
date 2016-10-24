package com.fragment;

import android.widget.ImageView;

import com.R;
import com.activity.MineDetailActivity;

import Base.BaseFragment;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的fragment
 */
public class MineFragment extends BaseFragment {

    @BindView(R.id.iv_detail)
    ImageView mIvDetail;

    @Override
    protected int getLayoutResId() {
        return R.layout.mine_tab;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.iv_detail)
    public void onClick() {
        startActivity(MineDetailActivity.class);
    }
}
