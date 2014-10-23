package com.netting.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ChaojiHuiUtils {

	/**
	 * 获取超级惠列表
	 * @param list
	 * @return
	 */
	public static ArrayList<HashMap<String, String>> getChaojihuiList(ArrayList<HashMap<String, String>> list){
		ArrayList<HashMap<String, String>> chaojiHuiList = new ArrayList<HashMap<String,String>>();
		for(HashMap<String, String> map : list){

			Iterator<String> iter = map.keySet().iterator();
			HashMap<String, String> cjhMap = new HashMap<String, String>();
			while (iter.hasNext()) {

				String key = iter.next();

				String value = map.get(key);
				
				if(value.equals("超级惠") || (value.length()>4 && value.substring(0,3).equals("超级惠"))){
					cjhMap.put(key, value);
				}
			}
			chaojiHuiList.add(cjhMap);
		}
		return chaojiHuiList;
	}
	
	
	public static ArrayList<HashMap<String, String>> getYuList(ArrayList<HashMap<String, String>> list){
		ArrayList<HashMap<String, String>> chaojiHuiList = new ArrayList<HashMap<String,String>>();
		for(HashMap<String, String> map : list){

			Iterator<String> iter = map.keySet().iterator();
			HashMap<String, String> cjhMap = new HashMap<String, String>();
			while (iter.hasNext()) {

				String key = iter.next();

				String value = map.get(key);
				
				if(value.equals("收藏夹广告") || value.equals("女鞋") || value.equals("化妆品") || value.equals("男士")|| value.equals("女装") || value.equals("美历")){
					cjhMap.put(key, value);
				}
			}
			chaojiHuiList.add(cjhMap);
		}
		return chaojiHuiList;
	}
	
}
