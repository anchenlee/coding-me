package com.netting.cache.dao;

import java.util.HashMap;
import java.util.Map;

import com.netting.redis.executor.JedisHashManager;

/**
 * IOS用户终端令牌---REDIS缓存更新操作类
 * @author YAOJIANBO
 *
 */
public class Admin_IphoneDevToken_Cache_DAO 
{
	private static final String IphoneDevTokenCacherkey = "iphone.devtoken";
	
	// private static final String AllIphoneDevTokenCacherkey = "iphone.alldevtoken";
	
	/**
	 * 获取所有的IOS设备的令牌，key=iphone.devtoken
	 */
	public static Map<String, String> getAllDevTokenWithUID()
	{
		Map<String, String> tokenMap = new HashMap<String, String>();
		tokenMap = JedisHashManager.getAll(IphoneDevTokenCacherkey);
		return tokenMap;
	}
	
	public static String getDevToken(String uid)
	{
		String devToken = JedisHashManager.get(IphoneDevTokenCacherkey, uid);
		return devToken;
	}
}
