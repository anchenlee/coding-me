package cn.youhui.ramdata;

import java.util.Map;

import org.apache.log4j.Logger;

import cn.youhui.common.X;
import cn.youhui.manager.JedisHashManager;
import cn.youhui.manager.JedisKeyManager;
import cn.youhui.manager.TagManager;

public class Tagid2TagnameCacher {
	private static final Logger log = Logger.getLogger(Tagid2TagnameCacher.class);
	private static String cacheKey = X.CachePrefix.TAG_ID_NAME;
	private static String cacheLock = X.CachePrefix.CACHE_STATUS + X.DOT + cacheKey;
	private static Tagid2TagnameCacher instance = null;
	
	private Tagid2TagnameCacher(){}
	
	public static Tagid2TagnameCacher getInstance() {
		return instance == null ? (instance = new Tagid2TagnameCacher()) : instance;
	}
	
	public String getIdbyName(String name){
		return new JedisHashManager(cacheKey).get(name);
	}
	
	public String getNamebyId(String id){
		return new JedisHashManager(cacheKey).get(id);
	}
	
	public void init(){
		String lock = new JedisKeyManager(cacheLock).get();
		if(!X.CachePrefix.LOCK_MARK.equals(lock)){
			try{
				log.info("tagid2tagname init.....");
				new JedisKeyManager(cacheLock).set(X.CachePrefix.LOCK_MARK);
				new JedisKeyManager(cacheKey).del();
				Map<String, String> map = TagManager.getInstance().getTagid2nameMap();
				new JedisHashManager(cacheKey).setAll(map);
			}catch(Exception e){
				log.error("tagid2tagname init exception " + e.getMessage());
			}finally{
				new JedisKeyManager(cacheLock).del();
			}
		}
	}
	
	public void reload(){
		init();
	}
}
