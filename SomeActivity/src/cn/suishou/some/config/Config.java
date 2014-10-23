package cn.suishou.some.config;

import cn.suishou.some.ProjectInformation;


public class Config {
	
	public final static String MD5Key = "369258147";
	
	/**
	 * mysql config
	 * */
	private static String mysql_test_url = "jdbc:mysql://10.0.0.21:3306/youhui_talk?useUnicode=true&characterEncoding=utf-8";
	private static String mysql_test_user = "root";
	private static String mysql_test_pass = "123456";

	
//	private static String mysql_url = "jdbc:mysql://10.0.254.100:3306/youhui_cn_fanli?useUnicode=true&characterEncoding=utf-8";
	private static String mysql_url = "jdbc:mysql://172.16.71.41:3306/youhui_cn_fanli?useUnicode=true&characterEncoding=utf-8";
	private static String mysql_user = "root";
	private static String mysql_pass = "!@#456QWEasd";
	
	
	
	public static String getMysqlUrl(){
		return ProjectInformation.DebugMode?mysql_test_url:mysql_url;
	}
	public static String getMysqlUser(){
		return ProjectInformation.DebugMode?mysql_test_user:mysql_user;
	}
	public static String getMysqlPass(){
		return ProjectInformation.DebugMode?mysql_test_pass:mysql_pass;
	}
	
	
	private static String PURCHASE_URL_TEST = "http://10.0.0.21:8080/purchaseapi/PurchaseCoupon?";
	private static String AddFavCoupon_URL_TEST = "http://10.0.0.21:8080/couponapi/api/addfavbrand?";
	
	private static String PURCHASE_URL = "http://172.16.71.59:8080/purchase/purapi/PurchaseCoupon?";
	private static String AddFavCoupon_URL = "http://172.16.71.59:8080/couponapi/api/addfavbrand?";
	
	public static String getPurchaseURL(){
		return ProjectInformation.DebugMode?PURCHASE_URL_TEST:PURCHASE_URL;
	}
	
	public static String getAddFavBrandUrl(){
		return ProjectInformation.DebugMode?AddFavCoupon_URL_TEST:AddFavCoupon_URL;
	}
	
	/**
	 * 
	 * redis config
	 */
	
	private static String GLB_REDIS_TEST_HOST = "10.0.0.21";
	private static int GLB_REDIS_TEST_PORT = 6379 ;
	
	
	private static String GLB_REDIS_HOST = "172.16.71.31";
	private static int GLB_REDIS_PORT = 6379 ;
	
	public static String getRedisHost(){
		return ProjectInformation.DebugMode?GLB_REDIS_TEST_HOST:GLB_REDIS_HOST;
	}
	
	public static int getRedisPort(){
		return ProjectInformation.DebugMode?GLB_REDIS_TEST_PORT:GLB_REDIS_PORT;
	}
	
	public static String DAY="1";
	public static String TONGJI="2";
	public static String SIGN="签到";
	public static String FENHONG="分红";
	public static String TRADE="购物";
	public static String level_1="http:111";
	public static String level_2="http:222";
	public static String level_3="http:333";
	public static String level_4="http:444";
	public static String level_5="http:555";
	public static String level_6="http:666";
	
}
