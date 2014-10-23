package cn.youhui.platform.config;

import cn.youhui.itemadd.ProjectInfo;

public class Config {
	

	private static String SQL_URL = "jdbc:mysql://172.16.71.41:3306/youhui_datamining?useUnicode=true&characterEncoding=utf-8";
	private static String SQL_USER  = "root";
	private static String SQL_PWD = "!@#456QWEasd";
	private static String GLB_REDIS_HOST = "172.16.71.31";
	private static int GLB_REDIS_PORT = 6379 ;
	
	private static String SQL_TEST_URL = "jdbc:mysql://10.0.0.21:3306/youhui_datamining?useUnicode=true&characterEncoding=utf-8";
	private static String SQL_TEST_USER  = "root" ;
	private static String SQL_TEST_PWD = "123456" ;
	private static String GLB_REDIS_HOST_TEST = "10.0.0.21";
	private static int GLB_REDIS_PORT_TEST = 6379 ;
	
	public static String getSQLUrl(){
		return ProjectInfo.DebugMode ? SQL_TEST_URL : SQL_URL;
	}
	
	public static String getSQLUser(){
		return ProjectInfo.DebugMode ? SQL_TEST_USER : SQL_USER;
	}
	
	public static String getSQLPWD(){
		return ProjectInfo.DebugMode ? SQL_TEST_PWD : SQL_PWD;
	}
	
	public static String getRedisHost(){
		return ProjectInfo.DebugMode ? GLB_REDIS_HOST_TEST : GLB_REDIS_HOST;
	}
	
	public static int getRedisPort(){
		return ProjectInfo.DebugMode ? GLB_REDIS_PORT_TEST : GLB_REDIS_PORT;
	}
	
}
