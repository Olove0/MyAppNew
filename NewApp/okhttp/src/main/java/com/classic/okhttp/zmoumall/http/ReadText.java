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

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ReadText {
	public String text() default "";
}
