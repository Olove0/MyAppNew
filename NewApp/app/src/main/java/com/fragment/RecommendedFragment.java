package com.fragment;

import android.widget.TextView;

import com.R;
import com.classic.okhttp.zmoumall.http.ActionHelp;
import com.classic.okhttp.zmoumall.http.ObjectCallback;
import com.classic.okhttp.zmoumall.http.bean.ConfigBean;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import Base.BaseFragment;
import butterknife.BindView;
import butterknife.OnClick;
import cache.ACache;
import http.subscribes.SubscriberOnNextListener;

/**
 * 推荐fragment
 */
public class RecommendedFragment extends BaseFragment {

    @BindView(R.id.tv_test_http)
    TextView mTvTestHttp;
    private SubscriberOnNextListener mSubscriberOnNextListener;

    private ACache mACache;

    @Override
    protected int getLayoutResId() {
        return R.layout.stack_tab;
    }

    @Override
    protected void initListener() {
       /* mSubscriberOnNextListener=new SubscriberOnNextListener<List<Subject>>() {
            @Override
            public void onNext(List<Subject> subjects) {
                mTvTestHttp.setText(subjects.toString());
            }
        };*/

    }

    @Override
    protected void initData() {
        mACache=ACache.get(getContext());
        mACache.put("test","是否缓存成功");
    }

    @OnClick(R.id.tv_test_http)
    public void onClick() {
       /* HttpMethods.getInstance().getTopMovie(new ProgressSubscriber(mSubscriberOnNextListener,getContext()),0,10);*/
        ActionHelp.upgradeVersion(getActivity(), "1", "1111", "2.0.0", new ObjectCallback<ConfigBean>() {


            @Override
            public void onHttpErrorCall(int statusCode) {

            }

            @Override
            public void onSuccess(ConfigBean response, String message) {

            }

            @Override
            public void onError(int code, String errorMessage) {
                mTvTestHttp.setText(errorMessage);
            }

            @Override
            public Type getType() {
                return new TypeToken<ConfigBean>() {
                }.getType();
            }
        });
        String t=mACache.getAsString("test");
        mTvTestHttp.setText(t);


    }

}
