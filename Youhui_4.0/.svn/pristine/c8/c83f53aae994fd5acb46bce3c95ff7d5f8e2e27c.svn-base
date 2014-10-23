package com.netting.conf;

import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 系统配置
 * @author YAOJIANBO
 * @version  1.0
 * @since 2012-12-26
 */
public class SysConf
{
	public static String project_abs_path = "";
	/**
	 * 初始化日志
	 */
	private static Log logger = LogFactory.getLog( SysConf.class );
	
	/**
	 * -----------数据库连接参数
	 */
	public static String driverClass = "com.mysql.jdbc.Driver";
	public static String url = "jdbc:mysql://10.0.0.21:3306/youhui_v3?useUnicode=true&characterEncoding=utf-8";
	public static String username = "root";
	public static String password = "123456";
	
	/**
	 * 淘宝接口的连接参数
	 */
	public static String taobao_url = "http://gw.api.taobao.com/router/rest";
	public static String taobao_appkey =  "12415289";
	public static String taobao_secret = "8a381fcabc1b74003c689550a95a0f0a";
	public static String taobao_id = "yaoyuewudi";
	
	/**
	 * REDIS服务器连接参数：IP,PORT
	 */
	public static String RedisServer_IP = "10.0.0.21";
	public static int RedisServer_PORT = 6379;
	
	/**
	 * 系统初始化
	 * @throws SocketException
	 */
	public static void init( String sysConfFilePath )
	{
		PropertiesUtil.load(sysConfFilePath);
		
		logger.info( "服务器系统配置参数初始化开始......" );

		String isLocalHostTest = PropertiesUtil.readValue("system.test.flag");
		if (isLocalHostTest.equals("1"))
		{
			return;
		}
		
		/**
		 * *********************************数据库连接参数
		 */
		driverClass = PropertiesUtil.readValue("db.driverClass");
		url = PropertiesUtil.readValue("db.url");
		username = PropertiesUtil.readValue("db.username");
		password = PropertiesUtil.readValue("db.password");
		
		/**
		 * *********************************REDIS服务器连接参数：IP,PORT
		 */
		RedisServer_IP = PropertiesUtil.readValue( "RedisServer.IP" );
		RedisServer_PORT = Integer.parseInt( PropertiesUtil.readValue( "RedisServer.PORT" ) );
		
		
		PropertiesUtil.close();

		logger.info( "服务器系统初始化完成......" );
	}
	
	public static String hostUrl = "http://b17.cn/";
	
	
	public static Map<String, String> actionValueToUrlMap = new HashMap<String, String>();
	static
	{
		actionValueToUrlMap.put("wodetaobao", "WoDeTaoBaoPage");
		actionValueToUrlMap.put("ss", "SouSuoPage");
		actionValueToUrlMap.put("jhs", "JuHuaSuanPage");
		actionValueToUrlMap.put("tttj", "TianTianTeJiaPage");
		actionValueToUrlMap.put("yjfk", "YiJianFanKuiPage");
		actionValueToUrlMap.put("smbj", "SaoMaBiJiaPage");
		actionValueToUrlMap.put("prd", "JiangJiaTiXingPage");
		actionValueToUrlMap.put("xiaoxi", "XiaoXiZhongXinPage");
		actionValueToUrlMap.put("tehui", "TeHuiPage");
		actionValueToUrlMap.put("gift", "LiWuPage");
		actionValueToUrlMap.put("locallife", "BenDiShengHuoPage");
		actionValueToUrlMap.put("gywm", "GuanYuWoMenPage");
		actionValueToUrlMap.put("tixianguanli", "TiQuJFBPage");

	}
	
	public static Map<String, String> actionTypeToUrlMap = new HashMap<String, String>(); 
	static{
		actionTypeToUrlMap.put("tagStyleWeb", "WebStylePage");
		actionTypeToUrlMap.put("tagStyleSingle", "TagStyleSinglePage");
		actionTypeToUrlMap.put("tagStyleList", "TagStyleListPage");
		actionTypeToUrlMap.put("tagStyleGrid", "TagStyleGridPage");
		actionTypeToUrlMap.put("tagStyleMix", "TagStyleMixPage");
		actionTypeToUrlMap.put("tagStyleCategory", "TagStyleCategoryPage");
		actionTypeToUrlMap.put("tagStyleWaterflow", "TagStyleWaterflowThreePage");
		actionTypeToUrlMap.put("tagStyleSale", "TagStyleSalePage");
		actionTypeToUrlMap.put("tagStyleItem", "TagStyleItemPage");
		actionTypeToUrlMap.put("searchShopId", "DianPuXiangQingPage");
		actionTypeToUrlMap.put("tagStyleSale", "TagStyleSalePage");
		actionTypeToUrlMap.put("tagStyleWaterFlowDouble", "TagStyleWaterflowDoublePage");
		actionTypeToUrlMap.put("systemWeb", "XiTongWebPage");
		actionTypeToUrlMap.put("pinpaiYouhui", "PinPaiYouHuiPage");
	}
	
}
