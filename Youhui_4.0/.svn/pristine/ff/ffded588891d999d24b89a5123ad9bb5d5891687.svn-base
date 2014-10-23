package com.netting.cache.dao;

import java.util.Map;

import com.google.gson.Gson;
import com.netting.bean.PushMessage;
import com.netting.redis.executor.JedisHashManager;

/**
 * Android推送消息---REDIS缓存更新操作类
 * @author YAOJIANBO
 *
 */
public class Admin_Message_Android_Cache_DAO 
{
	private static String AndroidPushMessageAllPrefix = "tyh.pushmsg.all";
	
	private static String AndroidPushMessagePrefix = "tyh.pushmsg";
	
	private static String AndroidPushMessageValuePrefix = "tyh.pushmsg.value.";
	
	/**
	 * 将消息发送至<全部发送位置>，key=tyh.pushmsg.all
	 */
	public static void addMsgToAll(PushMessage msg)
	{
		Gson gson = new Gson();
		JedisHashManager.set(AndroidPushMessageAllPrefix, msg.getpId(), gson.toJson(msg));
	}
	
	/**
	 * 将消息发送至<条件发送位置>，key=tyh.pushmsg和tyh.pushmsg.value.
	 * @param msg
	 * @param msgValue
	 */
	public static void addMsgWithValue(PushMessage msg, Map<String, String> msgValue)
	{
		Gson gson = new Gson();
		JedisHashManager.set(AndroidPushMessagePrefix, msg.getpId(), gson.toJson(msg));
		JedisHashManager.setAll(AndroidPushMessageValuePrefix + msg.getpId(), msgValue);
	}
}
