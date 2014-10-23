package cn.youhui.ramdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.youhui.bean.PushMessage;
import cn.youhui.dao.JedisDBIns;

import com.google.gson.Gson;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * @category Android端推送的消息存储管理
 * @author leejun
 * @since 2012.11.12
 */
public class AndroidPushMessageCacher {
	private static AndroidPushMessageCacher instance = null;
	private static String AndroidPushMessageAllPrefix = "tyh.pushmsg.all";
	private static String AndroidPushMessagePrefix = "tyh.pushmsg";
	private static String AndroidPushMessageValuePrefix = "tyh.pushmsg.value.";
	
	private AndroidPushMessageCacher(){
		
	}
	
	public static AndroidPushMessageCacher getInstance(){
		if(instance == null) instance = new AndroidPushMessageCacher();
		return instance;
	}
	
	public void addMsgAll(PushMessage msg) {
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		try {
			Gson gson = new Gson();
			jedis.hset(AndroidPushMessageAllPrefix , msg.getpId(), gson.toJson(msg));
			JedisDBIns.getInstance().release(jedis);
		} catch (JedisConnectionException e) {
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		} catch (Exception e) {
			JedisDBIns.getInstance().release(jedis);
		}
	}
		
	public List<PushMessage> getMsgAll(){
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		List<PushMessage> retlist = new ArrayList<PushMessage>();
		try {
			List<String> list = jedis.hvals(AndroidPushMessageAllPrefix);
			Gson gson = new Gson();
			for(String key : list){
		       retlist.add(gson.fromJson(key, PushMessage.class));
			}
			JedisDBIns.getInstance().release(jedis);
		} catch (JedisConnectionException e) {
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		} catch (Exception e) {
			JedisDBIns.getInstance().release(jedis);
		}
		return retlist;
	}
	
	public void addMsg(PushMessage msg, Map<String, String> msgValue) {
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		try {
			Gson gson = new Gson();
			jedis.hset(AndroidPushMessagePrefix, msg.getpId(), gson.toJson(msg));
			jedis.hmset(AndroidPushMessageValuePrefix + msg.getpId(), msgValue);
			JedisDBIns.getInstance().release(jedis);
		} catch (JedisConnectionException e) {
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		} catch (Exception e) {
			JedisDBIns.getInstance().release(jedis);
		}
	}
	
	
	public Map<String, String> getMsgValue(String uid) {
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		Map<String, String> retmap = new HashMap<String, String>();
		try {
			Set<String> list = jedis.hkeys(AndroidPushMessagePrefix);
			for(String msgPid : list){
				long pid = Long.parseLong(msgPid);
				if(pid + 24*60*60*1000 < System.currentTimeMillis()){
					continue;
				}
			   String value = jedis.hget(AndroidPushMessageValuePrefix + msgPid, uid);
			   if(value != null)
		          retmap.put(msgPid, value);
			}
			JedisDBIns.getInstance().release(jedis);
		} catch (JedisConnectionException e) {
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		} catch (Exception e) {
			JedisDBIns.getInstance().release(jedis);
		}
		return retmap;
	}
	
	public PushMessage getMsg(String msgPid){
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		PushMessage msg = new PushMessage();
		try {
			Gson gson = new Gson();
			msg = gson.fromJson(jedis.hget(AndroidPushMessagePrefix, msgPid), PushMessage.class);
			JedisDBIns.getInstance().release(jedis);
		} catch (JedisConnectionException e) {
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		} catch (Exception e) {
			JedisDBIns.getInstance().release(jedis);
		}
		return msg;
	}
	
	public static void main(String[] args) {
//		System.out.println(AndroidPushMessageCacher.getInstance().getMsgValue("108612506"));
	}
}



