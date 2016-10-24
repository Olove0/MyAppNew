package com.fragment;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.R;
import com.adapter.MyFragmentPagerAdapter;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Base.BaseFragment;
import butterknife.BindView;

/**
 * 首页fragment
 */
public class HomeFragment extends BaseFragment {
    private static final String[] CHANNELS = new String[]{"Fragment1", "Fragment2", "Fragment3"};
    private List<String> mDataList = Arrays.asList(CHANNELS);

    private List<Fragment> fragmentList;

    @BindView(R.id.vp_home)
    ViewPager mVpHome;

    @Override
    protected int getLayoutResId() {
        return R.layout.home_tab;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        //TODO 适配器
        InitViewPager();
        //初始化指示器
        initMagicIndicator();

    }
    private void initMagicIndicator() {
        MagicIndicator magicIndicator = (MagicIndicator) mRootView.findViewById(R.id.magic_indicator3);
        magicIndicator.setBackgroundResource(R.drawable.round_indicator_bg);
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ClipPagerTitleView clipPagerTitleView = new ClipPagerTitleView(context);
                clipPagerTitleView.setText(mDataList.get(index));
                clipPagerTitleView.setTextColor(Color.parseColor("#e94220"));
                clipPagerTitleView.setClipColor(Color.WHITE);
                clipPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mVpHome.setCurrentItem(index);
                    }
                });
                return clipPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                float navigatorHeight = context.getResources().getDimension(R.dimen.common_navigator_height);
                float borderWidth = UIUtil.dip2px(context, 1);
                float lineHeight = navigatorHeight - 2 * borderWidth;
                indicator.setLineHeight(lineHeight);
                indicator.setRoundRadius(lineHeight / 2);
                indicator.setYOffset(borderWidth);
                indicator.setColors(Color.parseColor("#bc2a2a"));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, mVpHome);
    }


    /*
     * 初始化ViewPager
     */
    public void InitViewPager(){
        fragmentList = new ArrayList<Fragment>();
        Fragment secondFragment = TestFragment1.newInstance("this is second fragment");
        Fragment thirdFragment = TestFragment2.newInstance("this is third fragment");
        Fragment fourthFragment = TestFragment3.newInstance("this is fourth fragment");
        fragmentList.add(secondFragment);
        fragmentList.add(thirdFragment);
        fragmentList.add(fourthFragment);

        //给ViewPager设置适配器
        mVpHome.setAdapter(new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager(), fragmentList));
        mVpHome.setCurrentItem(0);//设置当前显示标签页为第一页
    }
}
