package Base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import utils.SharedPreferencesUtil;

/**
 * ltx
 */
public abstract class BaseFragment extends Fragment {

    private View mRootView;
    private SharedPreferencesUtil spUtil;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        mRootView = initView(inflater,container);
        mRootView=LayoutInflater.from(getActivity()).inflate(getLayoutResId(),container,false);
        ButterKnife.bind(this, mRootView);//绑定到butterknife
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onFirst();
        initListener();
        initData();
    }

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
}
