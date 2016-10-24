package utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast工具类
 *
 * @author ltx
 * @date 2015/11/3
 */
public final class ToastUtil {
    private static Toast toast;
    private ToastUtil() {
    }

    public static void showToast(Context context, String text) {
        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(), text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);
        }
        toast.show();
    }

    public static void showToast(Context context, int resId) {
        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(), context.getResources().getText(resId), Toast.LENGTH_SHORT);
        } else {
            toast.setText(context.getResources().getText(resId));
        }
        toast.show();
    }

    public static void showLongToast(Context context, String text) {
        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(), text, Toast.LENGTH_LONG);
        } else {
            toast.setText(text);
        }
        toast.show();
    }

    public static void showLongToast(Context context, int resId) {
        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(), context.getResources().getText(resId), Toast.LENGTH_LONG);
        } else {
            toast.setText(context.getResources().getText(resId));
        }
        toast.show();
    }
}
