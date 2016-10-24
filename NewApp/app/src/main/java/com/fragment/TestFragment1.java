package com.fragment;

import android.os.Bundle;

import com.R;

import Base.BaseFragment;

/**
 * homeFragment 嵌套的Frament1
 */
public class TestFragment1 extends BaseFragment {
    public static TestFragment1 newInstance(String s) {
        TestFragment1 newFragment = new TestFragment1();
        Bundle bundle = new Bundle();
        bundle.putString("hello", s);
        newFragment.setArguments(bundle);
        //bundle还可以在每个标签里传送数据
        return newFragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.layout_test_ft1;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
}
