package com.example.master.newapp.activity;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.example.master.newapp.R;
import com.example.master.newapp.constant.SpKey;

import Base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;
import utils.LoggerUtils;

public class MainActivity extends BaseActivity {


    @BindView(R.id.text)
    TextView mText;


    @Override
    protected void setListener() {

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
        if(doIsFirst(SpKey.MAIN_ISFIRST)){
            Log.e("ltx","mainActivity---->第一次");
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }


    @OnClick(R.id.text)
    public void onClick() {
        LoggerUtils.loggerErro("test---->logger");
    }
}
