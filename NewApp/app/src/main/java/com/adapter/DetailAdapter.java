package com.adapter;

import android.content.Context;
import android.view.View;

import com.R;
import com.bean.UserBean;

import java.util.List;

import Base.baseAdapter.CommonAdapter;
import Base.baseAdapter.ViewHolder;
import utils.ToastUtil;

/**
 *测试通用适配器
 */
public class DetailAdapter extends CommonAdapter<UserBean> implements View.OnClickListener {

    public DetailAdapter(Context context, int layout, List<UserBean> dataList) {
        super(context, layout, dataList);
    }

    @Override
    protected void convert(ViewHolder holder, UserBean item) {
      holder.setText(R.id.tv_age,item.age).setText(R.id.tv_name,item.name);//赋值
        //接口设置
       holder.setTag(item,R.id.tv_age).setOnClickListener(this);
       holder.setTag(null,R.id.tv_name).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        UserBean userBean= (UserBean) v.getTag();
        switch (v.getId()){
            case R.id.tv_name:
                ToastUtil.showToast(context,"name--->点击");
                break;

            case R.id.tv_age:
                ToastUtil.showToast(context,"age"+userBean.age);
                break;
        }

    }
}
