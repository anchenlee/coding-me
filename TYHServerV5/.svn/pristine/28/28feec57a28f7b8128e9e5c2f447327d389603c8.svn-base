package cn.youhui.common;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class YouHuiLogger {
	private static Logger logger = Logger.getLogger("rootLogger");
	private static String rootPath = "/home/8080_tomcat_server/webapps/tyhapi/";
	public YouHuiLogger() {}
	
	/**
	 * add log to file
	 * @param uid
	 * @param source
	 * @param sid
	 * @param ptype
	 * @param pid
	 * @param message
	 * @return
	 */
	public static boolean logToFile( String id,String uid, String sid, String code, String result){
		PropertyConfigurator.configure(rootPath+"WEB-INF/log4j2.properties");
		logger.info("id::"+id+",uid::"+uid+",sid::"+sid+",code::"+code+",result::"+result);
		return true;
	}
}