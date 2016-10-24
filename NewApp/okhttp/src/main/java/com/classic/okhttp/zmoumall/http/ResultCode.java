/*
 *   ========================================================
 *   Copyright(c) 2012 杭州龙骞科技-版权所有
 *   ========================================================
 *   本软件由杭州龙骞科技所有, 未经书面许可, 任何单位和个人不得以
 *   任何形式复制代码的部分或全部, 并以任何形式传播。
 *   公司网址
 *
 *   			http://www.hzdracom.com/
 *
 *   ========================================================
 */

package com.classic.okhttp.zmoumall.http;

/**
 * @Title: ResultCode.java
 * @Package com.surfingread.httpsdk.constant
 * @Description: TODO(添加描述)
 * @author 刘章
 * @date 2014年1月22日 下午1:54:04
 */
public class ResultCode
{
	@ReadText (text = "成功")
	public static final int OK     = 1000;
	
	@ReadText (text = "必填参数存在空值")
	public static final int E_1001 = 1001;
	
	@ReadText (text = "参数长度、格式不合法")
	public static final int E_1002 = 1002;
	
	@ReadText (text = "非法请求（校验不合法）")
	public static final int E_1003 = 1003;
	
	@ReadText (text = "根据用户帐号获取企业信息失败")
	public static final int E_1004 = 1004;
	
	@ReadText (text = "非法的文件类型")
	public static final int E_1006 = 1006;
	
	@ReadText (text = "文件大小超过最大限定值")
	public static final int E_1007 = 1007;
	
	@ReadText (text = "短信递交失败")
	public static final int E_1008 = 1008;
	
	@ReadText (text = "短信请求过于频繁")
	public static final int E_1009 = 1009;
	
	@ReadText (text = "用户不存在")
	public static final int E_1100 = 1100;
	
	@ReadText (text = "用户状态不正确")
	public static final int E_1101 = 1101;
	
	//	@ReadText (text = "用户密码不正确")
	@ReadText (text = "帐号或密码错误")
	public static final int E_1102 = 1102;
	
	@ReadText (text = "用户修改信息失败")
	public static final int E_1103 = 1103;
	
	@ReadText (text = "用户头像上传读取客户端文件失败")
	public static final int E_1104 = 1104;
	
	@ReadText (text = "用户头像上传到文件服务器失败")
	public static final int E_1105 = 1105;
	
	@ReadText (text = "帐号已存在")
	public static final int E_1106 = 1106;
	
	@ReadText (text = "请输入正确的书券信息")
	public static final int E_2100 = 2100;
	
	@ReadText (text = "图集不存在或已下架")
	public static final int E_3003 = 3003;
	
	@ReadText (text = "主平台或者有声平台接口交互http状态码返回非200成功码")
	public static final int E_4001 = 4001;
	
	@ReadText (text = "主平台或者有声平台接口交互返回错误返回码")
	public static final int E_4002 = 4002;
	
	@ReadText (text = "虽然您热爱阅读，但是阅读数量已到上限了")
	public static final int E_6201 = 6201;
	
	@ReadText (text = "账号被锁定")
	public static final int E_9103 = 9103;
	
	@ReadText (text = "帐号未登录")
	public static final int E_9992 = 9992;
	
	@ReadText (text = "找回密码非法请求，未按流程请求接口")
	public static final int E_9993 = 9993;
	
	@ReadText (text = "验证码已失效，请重新获取")
	public static final int E_9994 = 9994;
	
	@ReadText (text = "验证码错误")
	public static final int E_9995 = 9995;
	
	@ReadText (text = "密码错误已达上限3次，请一个小时后再试")
	public static final int E_9996 = 9996;
	
	@ReadText (text = "密码错误已达上限3次，请一个小时后再试")
	public static final int E_9997 = 9997;
	
	@ReadText (text = "时间戳过期（15分钟）")
	public static final int E_9998 = 9998;
	
	@ReadText (text = "系统异常或故障")
	public static final int E_9999 = 9999;
	
	/**
	 * 根据返回码取返回文本
	 * 
	 * @param resultCode
	 * @return
	 */
	public static String getResultText(int resultCode) {
		ReadText rst = null;
		try
		{
			rst = ResultCode.class.getField("E_" + resultCode).getAnnotation(ReadText.class);
		}
		catch (Exception e)
		{
			return null;
		}
		return rst == null ? null : rst.text();
	}
	
}
