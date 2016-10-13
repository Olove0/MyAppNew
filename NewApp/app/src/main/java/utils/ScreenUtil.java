/*
 * ========================================================
 * Copyright(c) 2014 杭州龙骞科技-版权所有
 * ========================================================
 * 本软件由杭州龙骞科技所有, 未经书面许可, 任何单位和个人不得以
 * 任何形式复制代码的部分或全部, 并以任何形式传播。
 * 公司网址
 * http://www.hzdracom.com/
 * ========================================================
 */
package utils;

import android.content.Context;
import android.util.DisplayMetrics;


/**
 * @Project： Lqyd_Android
 * @Title：ScreenUtil
 * @Description：手机屏幕数据获取类
 * @Author：wayne
 * @Date：2014年8月11日下午12:22:55
 */
public class ScreenUtil {
    public static int screenWidth;
    public static int screenHeight;
    public static float density;
    public static float scaleDensity;
    public static float xdpi;
    public static float ydpi;
    public static int densityDpi;
    public static int screenMin;   // 宽高中，最小的值

    private static double RATIO = 0.85;
    public static int dialogWidth;

    public static void GetInfo(Context context) {
        if (null == context) {
            return;
        }
        DisplayMetrics dm = new DisplayMetrics();
        dm = context.getApplicationContext().getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        screenMin = (screenWidth > screenHeight) ? screenHeight : screenWidth;
        density = dm.density;
        scaleDensity = dm.scaledDensity;
        xdpi = dm.xdpi;
        ydpi = dm.ydpi;
        densityDpi = dm.densityDpi;
    }

    /**
     * dip转px
     *
     * @param dipValue
     * @return
     */
    public static int dip2px(float dipValue) {
        final float scale = ScreenUtil.density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * px转dip
     */
    public static float px2dip(float pxValue) {
        final float scale = ScreenUtil.density;
        return (pxValue / scale + 0.5f);
    }

    public static boolean isBigScreen() {
        return screenWidth > 720 ? true : false;
    }


    public static int getDialogWidth() {
        dialogWidth = (int) (screenMin * RATIO);
        return dialogWidth;
    }
}
