package cn.youhuiWeb.ramdata;

import org.apache.log4j.Logger;

import cn.youhui.common.X;
import cn.youhui.common.X.CachePrefix;
import cn.youhui.manager.JedisKeyManager;

import cn.youhuiWeb.manager.HotRecommendManager;

public class HotRecommendCacher {

	private static String cacheKey = CachePrefix.YOUHUI_WEB_Hot_Recommend_view;
	
	private static String cacheLock = X.CachePrefix.CACHE_STATUS + X.DOT + cacheKey;
	
	private static final Logger log = Logger.getLogger(HotRecommendCacher.class);
	
	static HotRecommendCacher cacher = new HotRecommendCacher();
	
	public static HotRecommendCacher getInstance(){
		return cacher;
	}
	
	public static String getHotRecommendView(){
		String content = new JedisKeyManager(cacheKey).get();
		return content;
	}
	
	public boolean init() {
		String cacheStatus = new JedisKeyManager(cacheLock).get();
		if (!X.CachePrefix.LOCK_MARK.equals(cacheStatus)) {
			try{
				log.info("hotRecommend init.........");
				new JedisKeyManager(cacheLock).set(X.CachePrefix.LOCK_MARK);
				String content = HotRecommendManager.getInstance().getHotRecommendView().getContent();
				
				new JedisKeyManager(cacheKey).del();
				new JedisKeyManager(cacheKey).set(content);
			}
			catch(Exception e)
			{
				log.error("hotRecommend init exception  " + e.getMessage());
			}
			finally
			{
				new JedisKeyManager(cacheLock).del();
			}
		}
		return true;
	}
	
	public void reload(){
		init();
	}


}
