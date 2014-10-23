package cn.suishou.some.util;

import javax.servlet.http.HttpServletRequest;

public class ParamUtil {
	 
	public static String getParameter(HttpServletRequest request, String param) {
		
		String ret = request.getParameter(param);
	    if (ret == null) ret = "";
	    return ret.trim();
	}
}
