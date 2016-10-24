package com.classic.okhttp.zmoumall.http;

import com.classic.okhttp.base.OkHttpUtils;
import com.classic.okhttp.base.callback.StringCallback;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * 通用对象解析
 * <pre>使用Gson进行解析</pre>
 *
 */
public abstract class ObjectCallback<T> extends StringCallback {

    private static final int SUCCESS_CODE = 0;
    private static final int LOGIN_OTHER_CODE = 201;
    private static final String KEY_CODE = "ErrorCode";
    private static final String KEY_OBJECT = "Data";
    private static final String KEY_ERROR = "Message";

    @Override
    public void onSuccess(String json) {
        try {
            if (null == json || "".equals(json) || json.length() == 0 || json.equals("null")) {
                onError(OkHttpUtils.DEFAULT_ERROR_CODE, "");
                Logger.e("Error","数据为空");
                return;
            }
            final int code = new JSONObject(json).getInt(KEY_CODE);
            if (code == SUCCESS_CODE) {
                final String content = new JSONObject(json).getString(KEY_OBJECT);
                final String message = new JSONObject(json).getString(KEY_ERROR);
                T obj = new Gson().fromJson(content, getType());
                onSuccess(obj,message);
                Logger.i("OnSuccess",content);
            } else if (code == LOGIN_OTHER_CODE) {
                final String content = new JSONObject(json).getString(KEY_ERROR);
               /* CarApplication.otherLogin(content);*/
                Logger.i("OnError",content);
            } else {
                final String content = new JSONObject(json).getString(KEY_ERROR);
                onError(code, content);
                Logger.i("OnError",content);
            }
        } catch (Exception e) {
            onError(OkHttpUtils.DEFAULT_ERROR_CODE, "");
            e.printStackTrace();
        }
    }


    public abstract void onSuccess(T response,String message);
    public abstract void onError(int code, String errorMessage);

    /**
     * 设置要转换的对象类型  <br />
     * <pre>
     * 示例：
     * Type type = new TypeToken&lt;T&gt;(){}.getType()
     * </pre>
     */
    public abstract Type getType();
}
