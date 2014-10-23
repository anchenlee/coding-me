package cn.youhui.utils;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {

	public static String getPlatform(HttpServletRequest request){
		String platform = "";
		String agent = request.getHeader("user-agent");      
		if(agent.indexOf("Android")>-1){                     
			platform = "android";
		}else if(agent.indexOf("iPhone")>-1){
			platform = "iphone";
		}else if(agent.indexOf("iPad")>-1){
			platform = "ipad";
		}else if (agent.indexOf("MSIE 6")>-1) {
			platform = "ie6";
		} else if (agent.indexOf("MSIE 7")>-1) {
			platform = "ie7";
		} else if (agent.indexOf("MSIE 8")>-1) {
			platform = "ie8";
		} else if (agent.indexOf("Firefox")>-1) {
			platform = "firefox";
		} else if (agent.indexOf("Chrome")>-1) {
			platform = "chrome";
		} else if (agent.indexOf("Safari")>-1) {
			platform = "safari";
		}
		return platform;
	}
	public static boolean isFromWeixin(HttpServletRequest request){
		String agent = request.getHeader("user-agent");   
		if(agent != null && agent.indexOf("MicroMessenger") > -1){
			return true;
		}
		return false;
	}
	
	public static String getIp(HttpServletRequest request){
		return request.getHeader("X-Real-IP") == null ? request.getRemoteAddr():request.getHeader("X-Real-IP");
	}
}
