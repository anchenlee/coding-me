package cn.youhuiWeb.ramdata;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import cn.youhui.common.X;
import cn.youhui.common.X.CachePrefix;
import cn.youhui.manager.JedisKeyManager;
import cn.youhui.manager.JedisSortedSetManager;
import cn.youhuiWeb.bean.AD;
import cn.youhuiWeb.manager.AdManager;

public class ADCacher {

	private static String cacheKey = CachePrefix.YOUHUI_WEB_AD;
	
	private static String cacheLock = X.CachePrefix.CACHE_STATUS + X.DOT + cacheKey;
	
	private static final Logger log = Logger.getLogger(ADCacher.class);
	
	static ADCacher cacher = new ADCacher();
	
	public static ADCacher getInstance(){
		return cacher;
	}
	
	public static List<AD> getAdList(){
		List<AD> list = new ArrayList<AD>();
		try{
			Set<String> set = new JedisSortedSetManager(cacheKey).getAll();
			Iterator<String> it = set.iterator();
			Gson gson = new Gson();
			while (it.hasNext()) {  
			  String str = it.next();
			  AD ad = gson.fromJson(str, AD.class);
			  long now = System.currentTimeMillis();
			  long startTime = Long.parseLong(ad.getStart_time());
			  long endTime = Long.parseLong(ad.getEnd_time());
			  if(startTime < now && endTime >now){
				  list.add(ad);
			  }
			}  
		}catch(Exception e){
			log.error("ADCacher.getAdList error!",e);
			return list;
		}
		return list;
	}
	
	public boolean init() {
		String cacheStatus = new JedisKeyManager(cacheLock).get();
		if (!X.CachePrefix.LOCK_MARK.equals(cacheStatus)) {
			try{
				log.info("ad init.........");
				new JedisKeyManager(cacheLock).set(X.CachePrefix.LOCK_MARK);
				
				List<AD> list = AdManager.getInstance().getAds();
				new JedisKeyManager(cacheKey).del();
				if(list != null)
				{
					for(AD ad : list){
						Gson g = new Gson();
						String content = g.toJson(ad);
						new JedisSortedSetManager(cacheKey).add(ad.getRank(), content);	
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
		ADCacher.getInstance().reload();
		System.out.println("ad size:"+getAdList().size());
	}
}
