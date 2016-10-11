package com.example.master.newapp.activity;

import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.master.newapp.FragmentController;
import com.example.master.newapp.R;
import com.example.master.newapp.constant.SpKey;

import java.util.Timer;
import java.util.TimerTask;

import Base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @BindView(R.id.fl_context)
    FrameLayout mFlContext;//fragment依赖的布局
    @BindView(R.id.rb_home)
    RadioButton mRbHome;//首页按钮
    @BindView(R.id.rb_home1)
    RadioButton mRbHome1;//推荐按钮
    @BindView(R.id.rb_search)
    RadioButton mRbSearch;//搜索按钮
    @BindView(R.id.rb_my)
    RadioButton mRbMy;//我的按钮
    private FragmentController fragmentController;//fragment控制器
    private static Boolean isExit = false;//双击退出标识


    @Override
    protected void setListener() {

    }

    @Override
    protected void initView() {
        fragmentController = FragmentController.getInstance(this, R.id.fl_context);
        fragmentController.showFragment(0);//首次加载首页
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
        if (doIsFirst(SpKey.MAIN_ISFIRST)) {
            Log.e("ltx", "mainActivity---->第一次");
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    /**
     * 四个table栏切换
     * @param view table
     */
    @OnClick({R.id.rb_home, R.id.rb_home1, R.id.rb_search, R.id.rb_my})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_home:
                fragmentController.showFragment(0);
                break;
            case R.id.rb_home1:
                fragmentController.showFragment(1);
                break;
            case R.id.rb_search:
                fragmentController.showFragment(2);
                break;
            case R.id.rb_my:
                fragmentController.showFragment(3);
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        FragmentController.onDestroy();
    }
    /**

     * 菜单、返回键响应

     */

    @Override

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK)

        {

            exitBy2Click();      //退出应用的操作

        }

        return false;

    }

    /**

     * 双击退出函数

     */

    private void exitBy2Click() {

        if (!isExit) {

            isExit = true; // 准备退出

            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();

            Timer tExit = new Timer();

            tExit.schedule(new TimerTask() {

                @Override

                public void run() {

                    isExit = false; // 取消退出

                }

            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
        }

    }

}
