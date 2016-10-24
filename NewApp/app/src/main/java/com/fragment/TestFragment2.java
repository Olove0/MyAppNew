package com.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.R;
import com.adapter.HorizontalAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Base.BaseFragment;
import butterknife.BindView;

/**
 * homeFragment 嵌套的Frament2
 */
public class TestFragment2 extends BaseFragment {
    @BindView(R.id.rv_recycle)
    RecyclerView mRvRecycle;

    private HorizontalAdapter mHorizontalAdapter;//横向适配器
    private List<Integer> mDatas=new ArrayList<>();

    public static TestFragment2 newInstance(String s) {
        TestFragment2 newFragment = new TestFragment2();
        Bundle bundle = new Bundle();
        bundle.putString("hello", s);
        newFragment.setArguments(bundle);
        //bundle还可以在每个标签里传送数据
        return newFragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.layout_test_ft2;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        initDatas();
        //得到控件
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvRecycle.setLayoutManager(linearLayoutManager);
        //设置适配器
        mHorizontalAdapter = new HorizontalAdapter(getContext(), mDatas);
        mRvRecycle.setAdapter(mHorizontalAdapter);

    }
    private void initDatas()
    {
        mDatas = new ArrayList<Integer>(Arrays.asList(R.mipmap.load_no_data,
                R.mipmap.load_error, R.mipmap.nocover, R.mipmap.load_no_data, R.mipmap.load_error,
                R.mipmap.nocover, R.mipmap.load_no_data, R.mipmap.load_error, R.mipmap.nocover));
    }
}
