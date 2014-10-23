package cn.suishou.some.util;

import javax.servlet.http.HttpServletRequest;

public class WebUtil {
	
	public static boolean checkMobile(HttpServletRequest request){
		boolean flag = false;
		String userAgent = "";
		String userAgents = request.getHeader("user-agent");

		if (userAgents != null) {
			userAgent = userAgents;
			userAgent = userAgent.toUpperCase();
		}
		
		if (userAgent.indexOf("NOKI") > -1 || // Nokia phones and emulators
				userAgent.indexOf("ERIC") > -1 || // Ericsson WAP phones and
													// emulators
				userAgent.indexOf("WAPI") > -1 || // Ericsson WapIDE 2.0
				userAgent.indexOf("MC21") > -1 || // Ericsson MC218
				userAgent.indexOf("AUR") > -1 || // Ericsson R320
				userAgent.indexOf("R380") > -1 || // Ericsson R380
				userAgent.indexOf("UP.B") > -1 || // UP.Browser
				userAgent.indexOf("WINW") > -1 || // WinWAP browser
				userAgent.indexOf("UPG1") > -1 || // UP.SDK 4.0
				userAgent.indexOf("UPSI") > -1 || // another kind of UP.Browser
				userAgent.indexOf("QWAP") > -1 || // unknown QWAPPER browser
				userAgent.indexOf("JIGS") > -1 || // unknown JigSaw browser
				userAgent.indexOf("JAVA") > -1 || // unknown Java based browser
				userAgent.indexOf("ALCA") > -1 || // unknown Alcatel-BE3 browser
													// (UP based)
				userAgent.indexOf("MITS") > -1 || // unknown Mitsubishi browser
				userAgent.indexOf("MOT-") > -1 || // unknown browser (UP based)
				userAgent.indexOf("MY S") > -1 || // unknown Ericsson devkit
													// browser
				userAgent.indexOf("WAPJ") > -1 || // Virtual WAPJAG
													// www.wapjag.de
				userAgent.indexOf("FETC") > -1 || // fetchpage.cgi Perl script
													// from www.wapcab.de
				userAgent.indexOf("ALAV") > -1 || // yet another unknown UP
													// based browser
				userAgent.indexOf("WAPA") > -1 || // another unknown browser
													// (Web based "Wapalyzer")
				userAgent.indexOf("OPER") > -1 || // Opera
				userAgent.indexOf("DOPOD") > -1 || // 多普达
				userAgent.indexOf("SYMBIAN") > -1 || // symbian系统
				userAgent.indexOf("WINW") > -1 || // 
				userAgent.indexOf("WAPI") > -1 || // 
				userAgent.indexOf("ANDROID")>-1||
				userAgent.indexOf("IPHONE") >-1||
				userAgent.indexOf("BLACKBERRY")>-1||
				userAgent.indexOf("BADA")>-1
		) {
			flag = true;
		}
		return flag;
	}
	
	
	public static  String getIpAddr(HttpServletRequest request){  
//        String ip = request.getHeader("X-Real-IP");   
		String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){   
        	ip = request.getHeader("Proxy-Client-IP");    
        }    
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){    
        	ip = request.getHeader("WL-Proxy-Client-IP");    
        }   
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){   
        	ip = request.getRemoteAddr();    
        }    
        return ip;    
	} 
	
	/**
	 * 是否为内网IP
	 * @param ip
	 * @return
	 */
	public static boolean isIntranet(String ip){
		boolean flag = false;
		if(ip == null || "".equals(ip)){
			flag = true;
		}else if(ip.indexOf("0:0") == 0 || ip.indexOf("10") ==0 || ip.indexOf("192.168") == 0){
			flag = true;
		}else{
			if(ip.indexOf("172") == 0){
				String b = ip.split("\\.")[1];
				int bi = Integer.parseInt(b);
				if(bi>=16 && bi<=31){
					flag = true;
				}
			}
		}
		return flag;
	}
	
	public static boolean isFromWeixin(HttpServletRequest request){
		String agent = request.getHeader("user-agent");   
		if(agent != null && agent.indexOf("MicroMessenger") > -1){
			return true;
		}
		return false;
	}
}
