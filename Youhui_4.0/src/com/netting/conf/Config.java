/**
 * 
 */
package com.netting.conf;

/**
 * @author Eric Sun
 *
 */
public interface Config {
	
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
	
	
	
	int GLB_REDIS_PORT		 = 6379 ;
	


	/**
	 * Relase Config
	 * 正式服务器配置
	 */
	boolean relase = false;               //true为正式
	
	
	String GLB_REDIS_HOST	 = relase ? "172.16.71.31" : "10.0.0.21";
	String MONGO_SERVER		 = relase ? "172.16.71.32" : "10.0.0.21";
	String GLB_MYSQL_HOST	 = relase ? "172.16.71.41" : "10.0.0.21";
	String GLB_MYSQL_HOST_R	 = relase ? "172.16.71.141" : "10.0.0.21";
	String JFBAD_HOST		 = relase ? "http://v2.api.njnetting.cn/" : "http://10.0.0.21:8080/tyhapi/";
	
	String GLB_MYSQL_USER 	 = relase ? "mysqlsiud" : "root";
	String GLB_MYSQL_PWD 	 = relase ? "mysql!@#456" : "123456";
	
	String GLB_MYSQL_USER_R	 = relase ? "mysqls" : "root";
	String GLB_MYSQL_PWD_R	 = relase ? "mysql!@#456" : "123456";
	
	
	String GLB_MYSQL_DBNAME	 = "youhui_v3";
	
	
	
	String GLB_MYSQL_URL 	 = "jdbc:mysql://"+GLB_MYSQL_HOST+":3306/youhui_cn_fanli?useUnicode=true&characterEncoding=utf-8";
	String GLB_MYSQL_URL_R 	 = "jdbc:mysql://"+GLB_MYSQL_HOST_R+":3306/youhui_cn_fanli?useUnicode=true&characterEncoding=utf-8";
	
    public static String JFBAdSkipUrl = JFBAD_HOST + "jfbskip?data=";
	public static String JFBAdMidSkip = JFBAD_HOST + "jfbmidskip?data=";             //中间跳转
	
	public static String RecomModelUrl = "http://172.16.71.61:8866/log";

}
