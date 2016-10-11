package com.example.master.newapp.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * 引导页指示器ViewPager Adapter
 */
public class WelcomAdapter extends PagerAdapter{
    private Context mContext;
    private View[] mViews;
    public WelcomAdapter(View[] mViews, Context mContext) {
        this.mViews=mViews;
        this.mContext=mContext;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View mView=mViews[position];
        container.addView(mView);
        return mView;
    }

    @Override
    public int getCount() {
        return mViews.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
