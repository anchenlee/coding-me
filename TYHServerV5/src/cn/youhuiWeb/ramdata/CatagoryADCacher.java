package cn.youhuiWeb.ramdata;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import cn.youhui.common.X;
import cn.youhui.common.X.CachePrefix;
import cn.youhui.manager.JedisKeyManager;
import cn.youhui.manager.JedisSortedSetManager;
import cn.youhuiWeb.bean.CatagoryAD;
import cn.youhuiWeb.manager.CatagoryAdManager;

public class CatagoryADCacher {

	private static String cacheKey = CachePrefix.YOUHUI_WEB_Catagory_AD;
	
	private static String cacheLock = X.CachePrefix.CACHE_STATUS + X.DOT + cacheKey;
	
	private static final Logger log = Logger.getLogger(CatagoryADCacher.class);
	
	static CatagoryADCacher cacher = new CatagoryADCacher();
	
	public static CatagoryADCacher getInstance(){
		return cacher;
	}
	
	public static List<CatagoryAD> getAdList(String catagoryId){
		List<CatagoryAD> list = new ArrayList<CatagoryAD>();
		try{
			Set<String> set = new JedisSortedSetManager(cacheKey + catagoryId).getAll();
			Iterator<String> it = set.iterator();
			Gson gson = new Gson();
			while (it.hasNext()) {  
			  String str = it.next();
			  CatagoryAD ad = gson.fromJson(str, CatagoryAD.class);
			  long now = System.currentTimeMillis();
			  long startTime = Long.parseLong(ad.getStart_time());
			  long endTime = Long.parseLong(ad.getEnd_time());
			  if(startTime < now && endTime >now){
				  list.add(ad);
			  }
			}
		}catch(Exception e){
			log.error("CatagoryADCacher.getAdList error!",e);
			return list;
		}  
		return list;
	}
	
	public boolean init(String catagoryId) {
		String cacheStatus = new JedisKeyManager(cacheLock + catagoryId).get();
		
		if (!X.CachePrefix.LOCK_MARK.equals(cacheStatus)) {
			try{
				log.info("ad init.........");
				new JedisKeyManager(cacheLock + catagoryId).set(X.CachePrefix.LOCK_MARK);
				
				List<CatagoryAD> list = CatagoryAdManager.getInstance().getAds(catagoryId);
				new JedisKeyManager(cacheKey + catagoryId).del();
				if(list != null)
				{
					for(CatagoryAD ad : list){
						Gson g = new Gson();
						String content = g.toJson(ad);
						new JedisSortedSetManager(cacheKey + catagoryId).add(ad.getRank(), content);	
					}					
				}
			}
			catch(Exception e)
			{
				log.error("catagoryAd init exception  " + e.getMessage());
			}
			finally
			{
				new JedisKeyManager(cacheLock + catagoryId).del();
			}
		}
		return true;
	}
	
	public void reload(String catagoryId){
		init(catagoryId);
	}
	
	public void reloadAll(){
		Map<String, String> catagoryKeyMap  = X.CachePrefix.CatagoryKeyMap;
		Iterator<String> it = catagoryKeyMap.values().iterator();
		while(it.hasNext()){
			init(it.next().toString());
		}
	}


}
