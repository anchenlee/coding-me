package cn.youhuiWeb.util;


import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class StringUtil {
	/**
	 * 将全部请求参数按字母顺序拼接成字符串
	 * @param paramMap
	 * @return
	 * @throws UnsupportedEncodingException 
	 */	
	public static String paramsToStr(Map<String, String[]> paramMap) throws UnsupportedEncodingException{
		StringBuffer sb = new StringBuffer();
		TreeMap<String, String[]> newmap = new TreeMap<String, String[]>();
		newmap.putAll(paramMap);
		Iterator<String> keys = newmap.keySet().iterator();
		while(keys.hasNext()){
			String key = keys.next();
			if(!"sign".equals(key)){
				String param = paramMap.get(key)[0];
				sb.append(key).append("=").append(param).append("&");
			}
		}
		return sb.substring(0, sb.length()-1);
	}
	

}
