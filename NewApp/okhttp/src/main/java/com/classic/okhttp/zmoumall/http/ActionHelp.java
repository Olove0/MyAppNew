package com.classic.okhttp.zmoumall.http;

import android.app.Activity;
import android.text.TextUtils;

import com.classic.okhttp.base.OkHttpUtils;
import com.classic.okhttp.base.callback.Callback;
import com.classic.okhttp.zmoumall.utils.MD5;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;

/**
 * 使用步骤： <br />
 * 1.在application初始化: <br />
 * ActionHelp.init(imei,psdnIp, true); <br />
 *
 */
public class ActionHelp {

    private static final String URL_R = "http://zmoumall.com/index.php/api/port/"; //商用
    private static final String URL_T = "http://zmoumall.com/index.php/api/port/"; //商测
    //        private static final String URL_T = "http://192.168.1.41:8015/gbb/app/patriarchDeal.do"; //商测
    public static final String CONTENTTYPE = "application/xml;charset=UTF-8";
    public static final String UPLOAD_CONTENTTYPE = "application/octet-stream";
    private static final String CHARTSET = "UTF-8";
    private static String ACCOUNTTYPE = "1";                                      //1手机账号  2 邮箱
    private static String CLIENTTYPE = "android_2.0.0";
    private static String CLIENT_IMEI;
    private static String USER_IP;
    private static String ACCOUNT;
    private static String URL;
    private static boolean ISRELEASE;

    /**
     * 接口调用前，需要调用此方法
     *
     * @param imei
     * @param psdnIp
     * @param isRelease 配置服务环境 true:商用，false:商测
     */
    public static void init(String imei, String psdnIp, boolean isRelease) {
        CLIENT_IMEI = TextUtils.isEmpty(imei) ? "" : imei;
        USER_IP = TextUtils.isEmpty(psdnIp) ? "" : psdnIp;
        ISRELEASE = isRelease;
        URL = isRelease ? URL_R : URL_T;
    }


    /**
     * 获取帐号类型 1：手机账号, 2: 邮箱
     */
    public static String getAccountType() {
        return ACCOUNTTYPE;
    }

    /**
     * 设置账号类型 1：手机账号,2: 邮箱
     */
    public static void setAccountType(String accountType) {
        ACCOUNTTYPE = accountType;
    }

    /**
     * 获取客户端类型
     */
    public static String getClientType() {
        return CLIENTTYPE;
    }

    /**
     * 设置客户端类型
     */
    public static void setClientType(String clientType) {
        CLIENTTYPE = clientType;
    }

    /**
     * 获取当前帐号
     */
    public static String getAccount() {
        return ACCOUNT;
    }

    /**
     * 设置账号
     */
    public static void setAccount(String account) {
        ACCOUNT = account;
    }

    /**
     * 获取接口url
     *
     * @return
     */
    public static String getUrl() {
        return URL;
    }

    /**
     * 获取登录key
     */
//    public static String getLoginKey() {
//        return LOGIN_KEY;
//    }

    /**
     * 设置登录key(登录成功时调用)
     *
     * @param key
     */
//    public static void setLoginKey(String key) {
//        if (!TextUtils.isEmpty(key)) LOGIN_KEY = key;
//    }

    /**
     * 设置默认的登录key(切换账号、注销时调用)
     */
//    public static void setDefaultLoginKey() {
//        LOGIN_KEY = "LQSW@)!$";
//    }

    /**
     * 登录
     *
     * @param activity
     * @param username
     * @param password
     * @param callback
     */
    public static void zmouUserLogin(Activity activity, String username, String password, Callback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("username", username);
        params.put("password", password);
        params.put("token", MD5.MD5Encode("checkLogin"));
        Gson gson=new Gson();
        String parm=gson.toJson(params);

        OkHttpUtils.post()
                .url(getUrl("checkLogin"))
                .params(params)
                .tag(activity)
                .build()
                .execute(callback);
    }



    public static void upgradeVersion(Activity activity, String platfrom, String channel, String version,Callback callback) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("platform", 1);
        params.put("channel", channel);
        params.put("version", version);
        Gson gson=new Gson();
        String json=gson.toJson(params);
        OkHttpUtils.postString()
                .url("http://s5.hulanet.cc:8080/cgt/PublicService/upgradeVersion")
                .mediaType(MediaType.parse("application/json;charset=utf-8"))//请求头
                .content(json)
                .tag(activity)
                .build()
                .execute(callback);
    }


    /**
     * 获取首页数据
     *
     * @param activity
     * @param callback
     */
    public static void zmouIndexData(Activity activity, int type, Callback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("i", type + "");
        params.put("token", MD5.MD5Encode("getHomepage"));
        OkHttpUtils.post()
                .url(getUrl("getHomepage"))
                .params(params)
                .tag(activity)
                .build()
                .execute(callback);
    }

    /**
     * 获取首页加载商品
     *
     * @param activity
     * @param page
     * @param cateid
     * @param callback
     */

    public static void zmouIndexGoods(Activity activity, int page, String cateid, Callback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("p", page + "");
        params.put("cateid", cateid);
        params.put("token", MD5.MD5Encode("getGoods"));
//        Log.e("url",MD5.MD5Encode("getGoods"));
        OkHttpUtils.post()
                .url(getUrl("getGoods"))
                .params(params)
                .tag(activity)
                .build()
                .execute(callback);
    }


    /**
     * 精品推荐列表数据
     *
     * @param activity
     * @param page
     * @param callback
     */
    public static void zmouGiftsList(Activity activity, int page, Callback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("p", page + "");
        params.put("position", 8 + "");
        params.put("token", MD5.MD5Encode("getGoods"));
        OkHttpUtils.post()
                .url(getUrl("getGoods"))
                .params(params)
                .tag(activity)
                .build()
                .execute(callback);
    }


    /**
     * 热销排行列表数据
     *
     * @param activity
     * @param page
     * @param callback
     */
    public static void zmouHotsList(Activity activity, int page, Callback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("p", page + "");
        params.put("position", 16 + "");
        params.put("token", MD5.MD5Encode("getGoods"));
        OkHttpUtils.post()
                .url(getUrl("getGoods"))
                .params(params)
                .tag(activity)
                .build()
                .execute(callback);
    }


    /**
     * 获取商家列表
     *
     * @param activity
     * @param page
     * @param callback
     */
    public static void zmouShopList(Activity activity, int page, Callback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("page1", page + "");
        params.put("page2", 6 + "");
        params.put("token", MD5.MD5Encode("shoplist"));
        OkHttpUtils.post()
                .url(getUrl("shoplist"))
                .params(params)
                .tag(activity)
                .build()
                .execute(callback);
    }


    /**
     * 获取商品详情数据
     *
     * @param activity
     * @param id
     * @param callback
     */
    public static void zmouGoodDetail(Activity activity, String id, Callback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id);
        params.put("token", MD5.MD5Encode("getGoodDetail"));
        OkHttpUtils.post()
                .url(getUrl("getGoodDetail"))
                .params(params)
                .tag(activity)
                .build()
                .execute(callback);
    }


    /**
     * 获取二级规格
     *
     * @param activity
     * @param id
     * @param callback
     */
    public static void zmouRules(Activity activity, String id, Callback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id);
        params.put("token", MD5.MD5Encode("getGoodRule"));
        OkHttpUtils.post()
                .url(getUrl("getGoodRule"))
                .params(params)
                .tag(activity)
                .build()
                .execute(callback);
    }


    /**
     * 获取地址
     *
     * @param activity
     * @param uid
     * @param type
     * @param callback
     */
    public static void zmouGetAddress(Activity activity, String uid, String type, Callback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("uid", uid);
        params.put("type", type);/** 1:默认地址  2:地址列表*/
        params.put("token", MD5.MD5Encode("getAddress"));

        OkHttpUtils.post()
                .url(getUrl("getAddress"))
                .params(params)
                .tag(activity)
                .build()
                .execute(callback);
    }

    /**
     * 提交订单
     *
     * @param activity
     * @param uid
     * @param addressid
     * @param goodsid
     * @param callback
     */

    public static void zmouCreateOrder(Activity activity, String uid, String addressid, String goodsid, Callback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("uid", uid);
        params.put("addressid", addressid);
        params.put("goodsid", goodsid);
        params.put("sort", "android");
        params.put("token", MD5.MD5Encode("createOrder"));

        OkHttpUtils.post()
                .url(getUrl("createOrder"))
                .params(params)
                .tag(activity)
                .build()
                .execute(callback);
    }


    /**
     * 获取订单列表
     *
     * @param activity
     * @param uid
     * @param status
     * @param callback
     */
    public static void zmouGetOrderList(Activity activity, String uid, String status, Callback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("uid", uid);
        params.put("status", status);
        params.put("token", MD5.MD5Encode("getOrderList"));

        OkHttpUtils.post()
                .url(getUrl("getOrderList"))
                .params(params)
                .tag(activity)
                .build()
                .execute(callback);

    }


    /**
     * 获取地区
     *
     * @param activity
     * @param callback
     */
    public static void zmouGetArea(Activity activity, Callback callback) {
        Map<String, String> params = new HashMap<String, String>();
//        params.put("pid", pid);
        params.put("token", MD5.MD5Encode("getArea"));

        OkHttpUtils.post()
                .url(getUrl("getArea"))
                .params(params)
                .tag(activity)
                .build()
                .execute(callback);
    }


    /**
     * 地址的增删改
     *
     * @param activity
     * @param type
     * @param uid
     * @param cellphone
     * @param province
     * @param city
     * @param area
     * @param address
     * @param realname
     * @param status
     * @param addressid
     * @param callback
     */

    public static void zmouAddressHandle(Activity activity, String type, String uid, String cellphone, String province, String city, String area, String address, String realname, int status, String addressid, Callback callback) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("type", type);
        params.put("uid", uid);
        params.put("cellphone", cellphone);
        params.put("province", province);
        params.put("city", city);
        params.put("area", area);
        params.put("address", address);
        params.put("realname", realname);
        params.put("status", status + "");
        params.put("addressid", addressid);
        params.put("token", MD5.MD5Encode("getAddressHandle"));

        OkHttpUtils.post()
                .url(getUrl("getAddressHandle"))
                .params(params)
                .tag(activity)
                .build()
                .execute(callback);

    }


    /**
     * 获取做单列表
     *
     * @param activity
     * @param status
     * @param uid
     * @param page
     * @param callback
     */
    public static void zmouMakelog(Activity activity, String status, String uid, int page, Callback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("status", status);
        params.put("uid", uid);
        params.put("page1", page + "");
        params.put("page2", 6 + "");
        params.put("token", MD5.MD5Encode("makelog"));

        OkHttpUtils.post()
                .url(getUrl("makelog"))
                .params(params)
                .tag(activity)
                .build()
                .execute(callback);

    }


    /**
     * 区域经理获取审核列表
     *
     * @param activity
     * @param uid
     * @param page
     * @param callback
     */
    public static void zmouQuyulog(Activity activity, String uid, int page, Callback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("uid", uid);
        params.put("page1", page + "");
        params.put("page2", 6 + "");
        params.put("token", MD5.MD5Encode("quyulog"));

        OkHttpUtils.post()
                .url(getUrl("quyulog"))
                .params(params)
                .tag(activity)
                .build()
                .execute(callback);

    }

    /**
     * 做单
     *
     * @param activity
     * @param param
     * @param callback
     */
    public static void zmouDomakeorder(Activity activity, String param, Callback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("params", param);
        params.put("token", MD5.MD5Encode("domakeorder"));

        OkHttpUtils.post()
                .url(getUrl("domakeorder"))
                .params(params)
                .tag(activity)
                .build()
                .execute(callback);

    }

    /**
     * 获取会员信息
     *
     * @param activity
     * @param uid
     * @param callback
     */
    public static void zmouGetMember(Activity activity, String uid, Callback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("uid", uid);
        params.put("token", MD5.MD5Encode("getMember"));

        OkHttpUtils.post()
                .url(getUrl("getMember"))
                .params(params)
                .tag(activity)
                .build()
                .execute(callback);
    }


    /**
     * 获取积分明细
     *
     * @param activity
     * @param uid
     * @param callback
     */
    public static void zmouGetScoreDetail(Activity activity, String uid, Callback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("uid", uid);
        params.put("token", MD5.MD5Encode("getJifenDetail"));

        OkHttpUtils.post()
                .url(getUrl("getJifenDetail"))
                .params(params)
                .tag(activity)
                .build()
                .execute(callback);
    }

    /**
     * 账户余额明细
     *
     * @param activity
     * @param uid
     * @param page
     * @param callback
     */
    public static void zmouGetAccountDetail(Activity activity, String uid, int page, Callback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("uid", uid);
        params.put("page1", page + "");
        params.put("token", MD5.MD5Encode("getAccountDetail"));

        OkHttpUtils.post()
                .url(getUrl("getAccountDetail"))
                .params(params)
                .tag(activity)
                .build()
                .execute(callback);
    }


    private static String getUrl(String action) {
        return new StringBuffer(URL).append(action).toString();
    }


    private static Map<String, String> getHeaders() {
        final HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("Connection", "Keep-Alive");
        headerMap.put("Chartset", CHARTSET);
        headerMap.put("Cache-Control", "no-cache");
        headerMap.put("Accept-Language", "zh-CN,en-US");
        headerMap.put("client-imei", CLIENT_IMEI);
        headerMap.put("user-ip", USER_IP);
        if (!TextUtils.isEmpty(ACCOUNT)) {
            headerMap.put("phone-number", ACCOUNT);
        }
        return headerMap;
    }

    private static Map<String, String> getHeaders(String account) {
        final HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("Connection", "Keep-Alive");
        headerMap.put("Chartset", CHARTSET);
        headerMap.put("Cache-Control", "no-cache");
        headerMap.put("Accept-Language", "zh-CN,en-US");
        headerMap.put("client-imei", CLIENT_IMEI);
        headerMap.put("user-ip", USER_IP);
        if (!TextUtils.isEmpty(account)) {
            headerMap.put("phone-number", account);
        }
        return headerMap;
    }
}
