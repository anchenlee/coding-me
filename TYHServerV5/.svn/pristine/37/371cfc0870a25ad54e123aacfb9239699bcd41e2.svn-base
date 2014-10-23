package cn.youhui.cacher.dao;

import cn.youhui.manager.JedisHashManager;

public class Switch4JfbCacher {

	private static String key="tagstatus";
	private static String field="value";
	
	private static Switch4JfbCacher instance=null;
	public static Switch4JfbCacher getInstance(){
		if(instance==null){
			instance=new Switch4JfbCacher();
		}
		return instance;
	}
	private Switch4JfbCacher(){}
	public  String getSwitch(){
		String sw=new JedisHashManager(key).get(field);
		if(sw==null||"".equals(sw)){
			sw="1";
		}
		return sw;
	}
	
}
