package com.example.master.newapp.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.master.newapp.R;
import com.example.master.newapp.adapter.WelcomAdapter;
import com.example.master.newapp.constant.SpKey;
import com.example.master.newapp.weight.ScaleCircleNavigator;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;

import Base.BaseActivity;
import butterknife.BindView;

/**
 * 启动页
 */

public class WelcomActivity extends BaseActivity {
    @BindView(R.id.vp_welcome)
    ViewPager mVpWelcome;//引导页vp
    @BindView(R.id.magic_indicator3)
    MagicIndicator mMagicIndicator3;//引导页指示器
    @BindView(R.id.iv_first)
    ImageView mIvFirst;//启动页

    private int[] mInts = {R.mipmap.load_error, R.mipmap.load_no_data, R.mipmap.nocover};
    private View[] views = new View[3];

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_welcom;
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void processLogic() {

    }

    @Override
    protected Context getActivityContext() {
        return this;
    }

    @Override
    public void onFirst() {
        if (doIsFirst(SpKey.WELCOME_ISFIRST)) {//第一次显示引导也否则显示启动页
            initVpView();
            WelcomAdapter welcomAdapter = new WelcomAdapter(views, getActivityContext());
            mVpWelcome.setAdapter(welcomAdapter);
            initMagicIndicator();

        } else {//启动页
            mIvFirst.setVisibility(View.VISIBLE);
            mIvFirst.setScaleType(ImageView.ScaleType.FIT_XY);
            new Thread(new Runnable() {//停止两秒跳首页
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        startActivity(new Intent(getActivityContext(), MainActivity.class));
                        finish();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    /**
     * 初始化viewPager视图
     */

    private void initVpView() {
        for (int i = 0; i < mInts.length - 1; i++) {
            ImageView imageView = new ImageView(mContext);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(lp);
            imageView.setImageResource(mInts[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            views[i] = imageView;
        }
        View viewLast = LayoutInflater.from(getActivityContext()).inflate(R.layout.view_start, null);
        views[2] = viewLast;
        TextView mStartView= (TextView) viewLast.findViewById(R.id.tv_start);
        mStartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivityContext(),MainActivity.class));
                finish();
            }
        });
    }

    /**
     * 初始化指示器
     */
    private void initMagicIndicator() {
        MagicIndicator magicIndicator = (MagicIndicator) findViewById(R.id.magic_indicator3);
        ScaleCircleNavigator scaleCircleNavigator = new ScaleCircleNavigator(this);
        scaleCircleNavigator.setCircleCount(mInts.length);
        scaleCircleNavigator.setNormalCircleColor(Color.LTGRAY);
        scaleCircleNavigator.setSelectedCircleColor(Color.DKGRAY);
        scaleCircleNavigator.setCircleClickListener(new ScaleCircleNavigator.OnCircleClickListener() {
            @Override
            public void onClick(int index) {
                mVpWelcome.setCurrentItem(index);
            }
        });
        magicIndicator.setNavigator(scaleCircleNavigator);
        ViewPagerHelper.bind(magicIndicator, mVpWelcome);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
