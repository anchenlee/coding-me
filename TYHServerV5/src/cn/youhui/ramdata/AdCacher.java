package cn.youhui.ramdata;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import cn.youhui.bean.Ad;
import cn.youhui.common.X;
import cn.youhui.common.X.CachePrefix;
import cn.youhui.manager.AdNewManager;
import cn.youhui.manager.JedisHashManager;
import cn.youhui.manager.JedisKeyManager;
import cn.youhui.manager.JedisSortedSetManager;

public class AdCacher {

	private static String cacheKey = CachePrefix.AD_AD;
	
	private static String cacheLock = X.CachePrefix.CACHE_STATUS + X.DOT + cacheKey;
	
	private static final Logger log = Logger.getLogger(AdCacher.class);
	
	/**
	 * 新的ad key
	 */
	private static String adCacheKey = "tyh_ad.tyh_aditem";
	
	private static String adKey2Ad = "tyh_ad.tyh_keyword2ad";
	
	
	
	private static List<String> adPlatform = new ArrayList<String>();
	
	static{
		adPlatform.add("android");
		adPlatform.add("ipad2.0");
		adPlatform.add("iphone");
		adPlatform.add("all");
	}
	
	static AdCacher cacher = new AdCacher();
	
	public static AdCacher getInstance(){
		return cacher;
	}
	
	public static List<Ad> getAdList(String platform){
		Set<String> set = new JedisSortedSetManager(cacheKey+platform).getAll();
		
		List<Ad> list = new ArrayList<Ad>();
		Iterator<String> it = set.iterator();
		Gson g = new Gson();
		while (it.hasNext()) {  
		  String str = it.next();
		  Ad ad = g.fromJson(str, Ad.class);
		  long now = System.currentTimeMillis();
		  long startTime = Long.parseLong(ad.getStart_time());
		  long endTime = Long.parseLong(ad.getEnd_time());
		  if(startTime < now && endTime >now){
			  list.add(ad);
		  }

		}  
		return list;
	}
	
	/**
	 * 获取platform下所有Ad
	 * @param platform
	 */
	public static List<Ad> getAdIds(String platform){
		List<Ad> list = new ArrayList<Ad>();
		Set<String> set = new JedisSortedSetManager(adKey2Ad+platform).getAll();
		Iterator<String> it = set.iterator();  
		while (it.hasNext()) {			
			Ad ad = new Ad();
			String id = it.next();   
			ad = getAd(id);
			list.add(ad);
		} 		
		return list;
	}
	
	/**
	 * 根据id获取Ad的详细信息
	 * @param id
	 * @return
	 */
	public static Ad getAd(String id){
		String content = new JedisHashManager(adCacheKey).get(id);
		Gson g = new Gson();
		Ad ad = g.fromJson(content, Ad.class);
		
		return ad;
	}
	
	
	
	public boolean init() {
		new JedisKeyManager(cacheLock).del();
		String cacheStatus = new JedisKeyManager(cacheLock).get();
		if (!X.CachePrefix.LOCK_MARK.equals(cacheStatus)) {
			try{
				log.info("ad init.........");
				new JedisKeyManager(cacheLock).set(X.CachePrefix.LOCK_MARK);
				for(String platform : adPlatform){					
					List<Ad> list = AdNewManager.getInstance().getAds(platform);
					if(list != null)
					{
						new JedisKeyManager(adKey2Ad+platform).del();
//						new JedisKeyManager(cacheKey+platform).del();
						for(Ad ad : list){
							Gson g = new Gson();
							String content = g.toJson(ad);
							new JedisSortedSetManager(adKey2Ad+platform).add(ad.getRank(), ad.getId());
							new JedisHashManager(adCacheKey).add(ad.getId(), content);
//							new JedisSortedSetManager(cacheKey+platform).add(ad.getRank(), content);	
						}
						
					}
				}
			}
			catch(Exception e)
			{
				log.error("ad init exception  " + e.getMessage());
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
	
	public static void main(String[] args) {
		AdCacher.getInstance().reload();
//		for(String platform : adPlatform){					
//			new JedisKeyManager(cacheKey+platform).del();
//		}
//		System.out.println(getAdList("all").size());
	}
}
