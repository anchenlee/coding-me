package cn.youhui.ramdata;

import cn.youhui.manager.JedisHashManager;

public class RefreshTimeCacher {

	private static String cachekey = "refreshtime.refreshcontrol";
	
	public static long getTime(String type){
		String timeStr = new JedisHashManager(cachekey).get(type);
		long time = 0;
		try {
			time = Long.parseLong(timeStr);			
		} catch (Exception e) {
			return 0;
		}
		return time;
	}
	
	public static void setTime(String type){
		new JedisHashManager(cachekey).add(type, System.currentTimeMillis()+"");
	}
	
	public static void main(String[] args) {
		for(int i = 0 ; i < 10 ; i ++){
			setTime("tttj");
		}
		System.out.println(getTime("ttt"));
	}
}
