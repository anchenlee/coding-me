/**
 * 
 */
package cn.youhui.stat.util;

/**
 * @author Isaac
 *
 */
public class Config {
	/**
	 * Relase Config
	 */

	public static String ORDER_DB = "tradefind.tradeorder";
	public static String PURCHASE_DB = "youhui_fanli.tyh_report_trade";
	public static String STORE_DB = "youhui_visit.tyh_fav_uid2itemid";

	public static String MONGO_SERVER = ProjectInfo.DebugMode ?"10.0.0.21":"172.16.71.150";
	
	private static String SQL_URL = "jdbc:mysql://172.16.71.41:3306/youhui_purchase?useUnicode=true&characterEncoding=utf-8";
	private static String SQL_USER  = "root";
	private static String SQL_PWD = "!@#456QWEasd";
	private static String SQL_TEST_URL = "jdbc:mysql://10.0.0.21:3306/youhui_fanli?useUnicode=true&characterEncoding=utf-8";
	private static String SQL_TEST_USER  = "root" ;
	private static String SQL_TEST_PWD = "123456" ;
	public static String getSQLUrl(){
		return ProjectInfo.DebugMode ? SQL_TEST_URL : SQL_URL;
	}
	
	public static String getSQLUser(){
		return ProjectInfo.DebugMode ? SQL_TEST_USER : SQL_USER;
	}
	
	public static String getSQLPWD(){
		return ProjectInfo.DebugMode ? SQL_TEST_PWD : SQL_PWD;
	}
}
