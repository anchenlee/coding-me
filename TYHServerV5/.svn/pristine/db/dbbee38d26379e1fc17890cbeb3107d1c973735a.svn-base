package cn.youhui.utils;

import java.util.Map;

public class UrlUtil {

	
	public static String addParams(String url, String params){
		if(url.indexOf("?") < 0){
			return url + "?" +params;
		}else{
			return url + "&" +params; 
		}
	}
	
	public static String addParams(String url, Map<String, String[]> params){
	if(params != null){
		if(url.indexOf("?") < 0){
			url = url + "?";
		}
		for(Map.Entry<String, String[]> m : params.entrySet()){
			url += "&"+m.getKey() + "=" + m.getValue()[0];
		}
	}
	return url;
	}
}
