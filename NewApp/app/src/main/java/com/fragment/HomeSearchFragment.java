package com.fragment;

import android.widget.TextView;

import com.R;
import com.activity.StickyListActivity;

import Base.BaseFragment;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 搜索fragment
 */
public class HomeSearchFragment extends BaseFragment {

    @BindView(R.id.tv_search)
    TextView mTvSearch;

    @Override
    protected int getLayoutResId() {
        return R.layout.search_tab;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.tv_search)
    public void onClick() {
        startActivity(StickyListActivity.class);
    }
}
