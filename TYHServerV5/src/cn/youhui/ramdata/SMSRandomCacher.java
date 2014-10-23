package cn.youhui.ramdata;

import cn.youhui.common.X;
import cn.youhui.manager.JedisKeyManager;


public class SMSRandomCacher {
	private static String cacheKey = X.CachePrefix.SMS_RANDOM_NUM;
	
	private static SMSRandomCacher instance;
	
	public static SMSRandomCacher getInstance() {
		return instance == null ? (instance = new SMSRandomCacher()) : instance;
	}
	
	public boolean saveSMSRandomNum(String phoneNum, String randomNum){
		String flag = new JedisKeyManager(cacheKey+phoneNum).set(randomNum);
		new JedisKeyManager(cacheKey+phoneNum).expire(15 * 60);       //15分钟  （短信发送太慢）
		if(flag != null) return true;
		else return false;
	}
	
	public String getRandomNum(String phoneNum){
		return new JedisKeyManager(cacheKey+phoneNum).get();
	}
}
