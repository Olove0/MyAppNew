package com.example.master.newapp.fragment;

import android.widget.TextView;

import com.example.master.newapp.R;

import java.util.List;

import Base.BaseFragment;
import butterknife.BindView;
import butterknife.OnClick;
import http.HttpMethods;
import http.entity.Subject;
import http.subscribes.ProgressSubscriber;
import http.subscribes.SubscriberOnNextListener;

/**
 * 推荐fragment
 */
public class RecommendedFragment extends BaseFragment {

    @BindView(R.id.tv_test_http)
    TextView mTvTestHttp;
    private SubscriberOnNextListener mSubscriberOnNextListener;

    @Override
    protected int getLayoutResId() {
        return R.layout.stack_tab;
    }

    @Override
    protected void initListener() {
        mSubscriberOnNextListener=new SubscriberOnNextListener<List<Subject>>() {
            @Override
            public void onNext(List<Subject> subjects) {
                mTvTestHttp.setText(subjects.toString());
            }
        };
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.tv_test_http)
    public void onClick() {
        HttpMethods.getInstance().getTopMovie(new ProgressSubscriber(mSubscriberOnNextListener,getContext()),0,10);

    }
}
