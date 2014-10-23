package cn.suishou.some.util;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class ParamSignUtil {
	
	/**
	 * 验证sign正确性
	 * @param params
	 * @param key
	 * @param sign
	 * @return
	 */
	public static boolean check(Map<String, String> params, String key, String sign){
		boolean flag = false;
		if(sign != null && sign.equalsIgnoreCase(getSign(params, key))){
			flag = true;
		}
		return flag;
	}

	/**
	 * 获取 sign 后的字符串
	 * @param params
	 * @param key
	 * @return
	 */
	public static String getSign(Map<String, String> params, String key){
		System.out.println(params.toString());
		return MD5.digest(key + paramsToStr(params) + key);
	}
	
	private static String paramsToStr(Map<String, String> paramMap){
		StringBuffer sb = new StringBuffer();
		TreeMap<String, String> newmap = new TreeMap<String, String>();
		newmap.putAll(paramMap);
		Iterator<String> keys = newmap.keySet().iterator();
		while(keys.hasNext()){
			String key = keys.next();
			if(!"sign".equals(key)){
				String param = paramMap.get(key);
				sb.append(key).append("=").append(param).append("&");
			}
		}
		return sb.substring(0, sb.length()-1);
	}
}
