package utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;

import java.io.File;

/**
 * 跳转到对应的系统界面
 * @author 续写经典
 * @date 2015/11/3
 */
public final class IntentUtil
{
	private IntentUtil(){}

	/** 进入拨号界面 */
	public static void dial(Activity activity,String phoneNumber){
		if(activity != null){
			Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:" + phoneNumber));
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			activity.startActivity(intent);
		}
	}
	/**
	 *  直接拨号
	 *  需要权限：android.permission.CALL_PHONE
	 */
	public static void call(Activity activity,String phoneNumber){
		if(activity != null){
			Intent intentPhone = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
			activity.startActivity(intentPhone);
		}
	}
	/** 用浏览器打开url */
	public static void browser(Activity activity,String url){
		if(activity != null){
			Intent intent   = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
			activity.startActivity(intent);
		}
	}
	/** 用系统浏览器打开url */
	public static void browserBySystem(Activity activity,String url){
		if(activity != null){
			Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
			intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
			activity.startActivity(intent);
		}
	}
	
	/** 安装应用 */
	public static void installApp(Activity activity, String apkPath) {
		if (activity != null && !TextUtils.isEmpty(apkPath))
		{
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.setDataAndType(Uri.fromFile(new File(apkPath)),
					"application/vnd.android.package-archive");
			activity.startActivity(intent);
		}
	}
	/** 发送短信 */
	public static void sendSms(Activity activity, String smsBody) {
		if (activity != null)
		{
			Uri smsToUri = Uri.parse("smsto:");
			Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
			intent.putExtra("sms_body", smsBody);
			activity.startActivity(intent);
		}
	}
	/** 发送短信 */
	public static void sendSms(Activity activity,String phone, String smsBody) {
		if (activity != null)
		{
			Uri smsToUri = Uri.parse("smsto:"+phone);
			Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
			intent.putExtra("sms_body", smsBody);
			activity.startActivity(intent);
		}
	}
	/** 跳转到设置界面 */
	public static void setting(Activity activity) {
		if (activity != null)
		{
			Intent intent = new Intent(Settings.ACTION_SETTINGS);
			activity.startActivity(intent);
		}
	}
	/** 跳转到网络设置界面 */
	public static void networkSettings(Activity activity) {
		if (activity != null)
		{
			Intent intent = null;
			if(Build.VERSION.SDK_INT > 10){
				intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
			}else{
				intent = new Intent();
				intent.setClassName("com.android.settings", "com.android.settings.WirelessSettings");
			}
			activity.startActivity(intent);
		}
	}

	/**
	 * 跳转到系统程序详细信息界面
	 *
	 * @param context
	 * @param packageName
	 */
	public static void startInstalledAppDetails(Context context, String packageName) {
		Intent intent = new Intent();
		int sdkVersion = Build.VERSION.SDK_INT;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
			intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
			intent.setData(Uri.fromParts("package", packageName, null));
		} else {
			intent.setAction(Intent.ACTION_VIEW);
			intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
			intent.putExtra((sdkVersion == Build.VERSION_CODES.FROYO ? "pkg"
					: "com.android.settings.ApplicationPkgName"), packageName);
		}
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}
}
