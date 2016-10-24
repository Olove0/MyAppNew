package com;

import android.app.Activity;
import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.classic.okhttp.base.OkHttpUtils;
import com.classic.okhttp.zmoumall.http.ActionHelp;
import com.constant.Constants;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import charles.nocompany.greendao.DaoMaster;
import charles.nocompany.greendao.DaoSession;
import utils.DeviceUtil;

/*
 *自定义Application
 * 用于初始化各种数据以及服务
 *  */

public class MyApplication extends Application {
    //记录当前栈里所有activity
    private List<Activity> activities = new ArrayList<Activity>();
    //记录需要一次性关闭的页面
    private List<Activity> activitys = new ArrayList<Activity>();
    //标记是否是第一次进入app
    public boolean isFirst;
    private final static String MY_TAG="my_tag";
    public DaoSession daoSession;

    public SQLiteDatabase db;

    public DaoMaster.DevOpenHelper helper;

    public DaoMaster daoMaster;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
       Logger.init(MY_TAG) ;                // default PRETTYLOGGER or use just init()
        ActionHelp.init(String.valueOf(DeviceUtil.getInstance(this)), "", true);
        OkHttpUtils.getInstance();
    }
    private void setupDatabase() {

//通过DaoMaster的内部类DevOpenHelper，你可以得到一个便利的SQLiteOpenHelper对象。
// 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的SQL语句，因为greenDAO已经帮你做了。
// 注意：默认的DaoMaster.DevOpenHelper会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
// 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。

        helper=new DaoMaster.DevOpenHelper(this, Constants.DB_NAME,null);

        db=helper.getWritableDatabase();

//注意：该数据库连接属于DaoMaster，所以多个Session指的是相同的数据库连接。

        daoMaster=new DaoMaster(db);

        daoSession=daoMaster.newSession();

    }

    public DaoSession getDaoSession() {

        return daoSession;

    }

    public SQLiteDatabase getDb() {

        return db;

    }

    /**
     * 应用实例
     **/
    private static MyApplication instance;

    /**
     * 获得实例
     *
     * @return
     */
    public static MyApplication getInstance() {
        return instance;
    }

    /**
     * 新建了一个activity
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**
     * 结束指定的Activity
     *
     * @param activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            this.activities.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /*
    *给临时Activitys
    * 添加activity
    * */
    public void addTemActivity(Activity activity) {
        activitys.add(activity);
    }

    public void finishTemActivity(Activity activity) {
        if (activity != null) {
            this.activitys.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /*
    * 退出指定的Activity
    * */
    public void exitActivitys() {
        for (Activity activity : activitys) {
            if (activity != null) {
                activity.finish();
            }
        }
    }

    /**
     * 应用退出，结束所有的activity
     */
    public void exit() {
        for (Activity activity : activities) {
            if (activity != null) {
                activity.finish();
            }
        }
        System.exit(0);
    }

}
