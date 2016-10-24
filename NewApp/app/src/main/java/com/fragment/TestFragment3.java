package com.fragment;

import android.os.Bundle;

import com.R;

import Base.BaseFragment;

/**
 * homeFragment 嵌套的Frament1
 */
public class TestFragment3 extends BaseFragment {
    public static TestFragment3 newInstance(String s) {
        TestFragment3 newFragment = new TestFragment3();
        Bundle bundle = new Bundle();
        bundle.putString("hello", s);
        newFragment.setArguments(bundle);
        //bundle还可以在每个标签里传送数据
        return newFragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.layout_test_ft3;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
}
