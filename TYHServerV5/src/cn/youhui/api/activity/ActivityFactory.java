package cn.youhui.api.activity;

import java.util.HashMap;
import java.util.Map;

import cn.youhui.manager.ActivityManager;

/**
 * 创建活动执行者的工厂
 * @author lijun
 * @since 2013-12-14
 */
public class ActivityFactory {
	
	private static Map<String, ActivityExecutor> executors = new HashMap<String, ActivityExecutor>();
	static{
		executors.put("signin", new SignInActivityExecutor());
		executors.put("mryutfbo", new GiftActivityExecutor());
	}
	
	public static ActivityExecutor getExecutor(String activityKey) throws Exception{
		ActivityExecutor executor = get(activityKey);
		if(executor == null && ActivityManager.getInstance().isExsitKey(activityKey)){
			executor = new ActivityExecutor();
		}
		if(executor == null){
			throw new Exception("activity not exsit");
		}else{
			return executor;
		}
	}
	
	private static ActivityExecutor get(String key){
		if("signin".equals(key)){
			return new SignInActivityExecutor();
		}else if("mryutfbo".equals(key)){
			return new GiftActivityExecutor();
		}
		return null;
	}
}
