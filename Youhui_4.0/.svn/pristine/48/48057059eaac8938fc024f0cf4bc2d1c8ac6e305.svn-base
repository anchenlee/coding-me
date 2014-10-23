package com.netting.util;

import javax.servlet.http.HttpServletRequest;

public class ParameterUtil {

	public static String getTrueString(HttpServletRequest request,String parameter,String wrongString) {
		String parameterRe = request.getParameter(parameter);
		if(parameterRe==null||"".equals(parameterRe)) 
		{
			return wrongString;
		}
		return parameterRe;
	}
}
