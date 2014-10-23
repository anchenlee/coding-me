/**
 * 
 */
package cn.youhui.common;

import cn.youhui.log4ssy.utils.Enums.Debug;

/**
 * @author Eric Sun
 *
 */
public interface Config {
	public static boolean isCutCallBack = true;  //是否开启扣除推广回调量 ，true为开启
	
	public static boolean paramDeBug = false;          //是否对参数进行严格控制
	
	public static String SKIP_URL = "http://b17.cn/item?";   //"http://a.suishouyouhui.cn/skip/item?";  
	
	// 如果需要返利给用户使用的淘宝OPEN平台API
	public static String Fanli_url = "http://gw.api.taobao.com/router/rest";
	
	/** 所有关于返利的使用 */
	public static String Fanli_appkey = "12415289";     //"12347692"; 
	/** 所有关于返利的使用 */
	public static String Fanli_secret = "8a381fcabc1b74003c689550a95a0f0a";  //"c62deb79ce636f9cbac7c074269ab1d6"; //
	
	public static String Taobao_url = "http://gw.api.taobao.com/router/rest";
	
	
	String AppKey = "12415289";
	String AppSecret = "8a381fcabc1b74003c689550a95a0f0a" ;
	
	public String jfb_activity_JoinKey = "mryutfbo";
	
	interface TaobaoApp {
		String getKey() ;
		String getSecret() ;
	}
	
	public static final String AES_PWD = "suishou774999810";
	
	public static final String SIGN_KEY = "77499981";
	
	
	int GLB_REDIS_PORT = 6379 ;
	


	/**
	 * Relase Configy
	 * 正式服务器配置
	 */
	boolean relase = true;               //true为正式
	
	Debug debug =relase?Debug.FALSE:Debug.TRUE;

	String ACTIVITY_URL=relase?"http://172.16.71.63:8081/activity/ActivityServlet":"http://localhost:8080/activity/ActivityServlet";
	
	String GLB_REDIS_HOST	 = relase ? "172.16.71.31" : "10.0.0.21";
	String MONGO_SERVER		 = relase ? "172.16.71.150" : "10.0.0.21";
	String GLB_MYSQL_HOST	 = relase ? "172.16.71.41" : "10.0.0.21";
	String GLB_MYSQL_HOST_R	 = relase ? "172.16.71.141" : "10.0.0.21";
	String GLB_MYSQL_HOST_2	 = relase ? "172.16.71.42" : "10.0.0.21";
	String JFBAD_HOST		 = relase ? "http://v2.api.njnetting.cn/" : "http://10.0.0.21:8080/tyhapi/";
	
	String GLB_MYSQL_USER 	 = relase ? "mysqlsiud" : "root";
	String GLB_MYSQL_PWD 	 = relase ? "mysql!@#456" : "123456";
	String GLB_MYSQL_USER_R	 = relase ? "mysqls" : "root";
	String GLB_MYSQL_PWD_R	 = relase ? "mysql!@#456" : "123456";
	String GLB_MYSQL_USER_2	 = relase ? "root" : "root";
	String GLB_MYSQL_PWD_2	 = relase ? "mysql!@#456" : "123456";
	
	
//	String LOG_URL		 = relase ? "http://172.16.71.65:8081/logapinew/addlog?" : "http://10.0.0.21:8080/logapinew/addlog?";
	
	String GLB_MYSQL_DBNAME	 = "youhui_v3";
	
	
	String GLB_MYSQL_URL 	 = "jdbc:mysql://"+GLB_MYSQL_HOST+":3306/youhui_cn_fanli?useUnicode=true&characterEncoding=utf-8";
	String GLB_MYSQL_URL_R 	 = "jdbc:mysql://"+GLB_MYSQL_HOST_R+":3306/youhui_cn_fanli?useUnicode=true&characterEncoding=utf-8";
	String GLB_MYSQL_URL_2 	 = "jdbc:mysql://"+GLB_MYSQL_HOST_2+":3306/youhui_visit?useUnicode=true&characterEncoding=utf-8";
	
	String MYSQL_URL  =  "jdbc:mysql://"+GLB_MYSQL_HOST+":3306/youhui_v3?useUnicode=true&characterEncoding=utf-8";
	
    public static String JFBAdSkipUrl = JFBAD_HOST + "jfbskip?data=";
	public static String JFBAdMidSkip = JFBAD_HOST + "jfbmidskip?data=";             //中间跳转
	
	public static String RecomModelUrl = "http://172.16.71.61:8866/log";
	
	public static String PURCHASE_URL = relase ? "http://172.16.71.59:8080/purchase/purapi/PurchaseCoupon?" : "http://172.18.2.96:8080/SSYHPurchaseApi/purapi/PurchaseCoupon?";
	public static String AddFavBrand_URL = relase ? "http://172.16.71.59:8080/couponapi/api/addfavbrand?" : "http://10.0.0.21:8080/couponapi/api/addfavbrand?";
	public static String AddFavCoupon_URL = relase ? "http://172.16.71.59:8080/couponapi/api/addfavcoupon?" : "http://10.0.0.21:8080/couponapi/api/addfavcoupon?";
	
	public static String TURNPLATE_URL = relase ? "http://v2.api.njnetting.cn/turnplate/?" : "http://10.0.0.21:8080/tyhapi/turnplate/?";
	public static String TURNPLATE2_HF_URL = relase ? "http://v2.api.njnetting.cn/turnplate2/copy_of_success.html" : "http://10.0.0.21:8080/tyhapi/turnplate2/copy_of_success.html";
	
	public static String API_3_0_FOR_WEB = relase ? "http://172.16.71.58:8080/Epai" :"http://10.0.0.21:8080/Epai";
	
	public static String API_V5_FOR_WEB = relase ? "http://172.16.71.57:8080/tyhapi" :"http://10.0.0.21:8080/tyhapi";
	
}
