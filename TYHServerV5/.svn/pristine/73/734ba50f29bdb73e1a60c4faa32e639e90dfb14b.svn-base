package cn.youhui.utils;

import java.util.HashMap;
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
		String mysign = getSign(params, key);
		if(sign != null && sign.equalsIgnoreCase(mysign)){
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
		String s = key + paramsToStr(params) + key;
		return MD5.digest(s);
	}
	
	public static String paramsToStr(Map<String, String> paramMap){
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
	
	public static String paramsToStrs(Map<String, String[]> paramMap){
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
	
	public static String retain(String str){
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("\n", "\\\\n");
		map.put("\r", "\\\\r");
		map.put("\t", "\\\\t");
		map.put("\f", "\\\\f");
        for(String s : map.keySet()){  
        	str=str.replaceAll(s, map.get(s));
        }
        return str;
	}
	
	public static void main(String[] args) {
		System.out.println(MD5.digest("77499981time=1405581367094&tyh_web_channel=own&tyh_web_imei=357070058541345&tyh_web_imsi=460016593265253&tyh_web_platform=android&tyh_web_taobaonick=bhll&tyh_web_uid=64844839&tyh_web_version=4.0.477499981"));
	
//		HashMap<String, String> tableData = new HashMap<String, String>();
//		tableData.put("tyh_web_uid", "64844839");
//		tableData.put("tyh_web_taobaonick", "冰河龙龙");
//		tableData.put("tyh_web_version", "4.0.4");
//		tableData.put("tyh_web_channel", "own");
//		tableData.put("tyh_web_platform", "android");
//		tableData.put("tyh_web_imei", "357070058541345");
//		tableData.put("tyh_web_imsi", "460016593265253");
//		tableData.put("time", "1405581367094");
		
		
		
//		System.out.println(getSign(tableData,"77499981"));
		
	}
}
