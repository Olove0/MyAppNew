package Base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import utils.SharedPreferencesUtil;

/**
 * ltx
 */
public abstract class BaseFragment extends Fragment {

    public View mRootView;
    private SharedPreferencesUtil spUtil;
    private Unbinder mUnbinder;//butterknife
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        mRootView = initView(inflater,container);
        mRootView=LayoutInflater.from(getActivity()).inflate(getLayoutResId(),container,false);
       mUnbinder= ButterKnife.bind(this, mRootView);//绑定到butterknife
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onFirst();
        initListener();
        initData();
    }
    /*********************子类实现*****************************/
    protected abstract int getLayoutResId();
    protected abstract void initListener();
    protected abstract void initData();
    /**
     * 第一次操作 应用新手引导或者引导页等
     */

    public void onFirst() {
    }

    /**
     *  判断每个类是否第一次加载
     * @param SP_NAME key
     * @return true 第一次 否则不是
     */
    public boolean doIsFirst(String SP_NAME, Context mContext){
        boolean isFirst=false;
        spUtil = new SharedPreferencesUtil(mContext,SP_NAME);
        final String simpleName = mContext.getClass().getSimpleName();
        if(spUtil.getBooleanValue(simpleName, true)){
            isFirst=true;
            spUtil.putBooleanValue(simpleName, false);
        }
        return isFirst;
    }
    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }
    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getContext(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
    /**
     * 右边的按钮
     * 默认隐藏
     * @param resId 资源Id
     */
    public void setRightBtn(int resId){
        ImageView ivRight= (ImageView) mRootView.findViewById(R.id.iv_img_right);
        ivRight.setVisibility(View.VISIBLE);
        ivRight.setImageResource(resId);

    }
    /**
     * 设置标题
     * @param title 标题
     */
    public void setTitle(String title){
        TextView mTitleView= (TextView) mRootView.findViewById(R.id.tv_title);
        mTitleView.setText(title);
    }

    /**
     * 返回键
     */

    public void setBack(){
        ImageView mBack= (ImageView)mRootView.findViewById(R.id.iv_back);
        mBack.setVisibility(View.GONE);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
        unRegist();
    }

    /**
     * 注销的方法
     */

    private void unRegist() {
    }

    /**
     * 显示数据加载对话框
     */
    public void showLoadingDialog() {
        showProgressDialog("正在加载，请稍等...");
    }
    /**
     * 显示自定义信息进度条
     *
     * @param message 要显示的信息内容
     */
    public void showProgressDialog(String message) {
        if (progressDialog == null) {
            createProgressDialog();
        }
        progressDialog.setMessage(message);
        if (!getActivity().isFinishing() && !progressDialog.isShowing()) {
            progressDialog.show();
        }
    }
    

    /**
     * 创建进度条
     */
    protected void createProgressDialog() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("正在加载，请稍等...");
        progressDialog.setCancelable(true);

        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
            }
        });
    }

    /**
     * 取消菊花
     */

    public void dismissProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
