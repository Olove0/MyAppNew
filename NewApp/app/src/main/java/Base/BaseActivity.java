package Base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.MyApplication;
import com.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import utils.SharedPreferencesUtil;

/**
 * ltx
 */

public abstract class BaseActivity extends AppCompatActivity{
	protected Context mContext;
	private SharedPreferencesUtil spUtil;
	private Unbinder unbinder;//饭注销

	public ImageView mIvRight;
	protected ProgressDialog progressDialog; // 数据加载进度条

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 锁定竖屏
		mContext = getActivityContext();
		setContentView(getLayoutResId());
		unbinder=ButterKnife.bind(this);
		onFirst();
		initView();
		setListener();
		processLogic();//网络请求
		MyApplication.getInstance().addActivity(this);
	}

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
	public boolean doIsFirst(String SP_NAME){
		boolean isFirst=false;
		spUtil = new SharedPreferencesUtil(this,SP_NAME);
		final String simpleName = mContext.getClass().getSimpleName();
		if(spUtil.getBooleanValue(simpleName, true)){
			isFirst=true;
			spUtil.putBooleanValue(simpleName, false);
		}
		return isFirst;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
//		StatService.onPause(mContext);
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
//		StatService.onResume(mContext);
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		MyApplication.getInstance().finishActivity(this);
		unbinder.unbind();
		unRegist();
	}
	/*********************子类实现*****************************/
	/**
	 * 加载页面layout
	 */
	protected abstract int getLayoutResId();
	/**
	 * 初始化控件
	 */
	protected abstract void initView();
	/**
	 * 设置各种事件的监听器
	 */
	protected abstract void setListener();

	/**
	 * 业务逻辑处理，主要与后端交互
	 */
	protected abstract void processLogic();


	/**
	 * Activity.this
	 */
	protected abstract Context getActivityContext();
	/**
	 * 需要注销的事件
	 */
	protected void unRegist(){

	}

	/**
	 * 弹出Toast
	 * 
	 * @param text 显示的文字
	 */
	public void showToast(String text) {
		Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}
	/**
	 * 获取屏幕宽度(px)
	 * 
	 * @return 屏幕宽度
	 */
	public int getMobileWidth() {
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int width = dm.widthPixels;
		return width;
	}

	/**
	 * 获取屏幕高度(px)
	 * 
	 * @return 屏幕高度
	 */
	public int getMobileHeight() {
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int height = dm.heightPixels;
		return height;
	}

	/**
	 * 获取状态栏高度
	 * @return 状态栏高度
	 */
	public int getStatusBarHeight() {
		int result = 0;
		int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}
	/*
	   返回版本api是否不小于19
	 */
	public boolean SdkApi(){
		int currentapiVersion = android.os.Build.VERSION.SDK_INT;
		if (currentapiVersion >= android.os.Build.VERSION_CODES.KITKAT){
			return true;
		} else{
			return false;
		}
	}

	/**
	 * 获取网络状态
	 * @return true 有网络
     */
	public boolean checkNetworkState() {
		boolean flag = false;
		//得到网络连接信息
		ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		//去进行判断网络是否连接
		if (manager.getActiveNetworkInfo() != null) {
			flag = manager.getActiveNetworkInfo().isAvailable();
		}
		return flag;
	}

	/**
	 * 获取版本号
	 * @return 版本号
     */
	public String getVersionName()
	{
		try {
			// 获取packagemanager的实例
			PackageManager packageManager = getPackageManager();
			// getPackageName()是你当前类的包名，0代表是获取版本信息
			PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(),0);
			String version = packInfo.versionName;
			return version;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return "1.0";
	}

	/**
	 * 设置标题
	 * @param title 标题
     */
	public void setTitle(String title){
		TextView mTitleView= (TextView) findViewById(R.id.tv_title);
		mTitleView.setText(title);
	}

	/**
	 * 返回键
	 */

	public void setBack(){
		ImageView mBack= (ImageView) findViewById(R.id.iv_back);
		mBack.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	/**
	 * 右边的按钮
	 * 默认隐藏
	 * @param resId 资源Id
     */
	public void setRightBtn(int resId){
		mIvRight = (ImageView) findViewById(R.id.iv_img_right);
		mIvRight.setVisibility(View.VISIBLE);
		mIvRight.setImageResource(resId);
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
		if (!this.isFinishing() && !progressDialog.isShowing()) {
			progressDialog.show();
		}
	}

	/**
	 * 显示数据加载对话框
	 */
	public void showLoadingDialog() {
		showProgressDialog("正在加载，请稍等...");
	}

	/**
	 * 创建进度条
	 */
	protected void createProgressDialog() {
		progressDialog = new ProgressDialog(this);
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
