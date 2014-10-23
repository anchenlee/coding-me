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
import cn.youhuiWeb.bean.HotSearch;
import cn.youhuiWeb.manager.HotSearchManager;

public class HotSearchCacher {

	private static String cacheKey = CachePrefix.YOUHUI_WEB_Hot_Search;
	
	private static String cacheLock = X.CachePrefix.CACHE_STATUS + X.DOT + cacheKey;
	
	private static final Logger log = Logger.getLogger(HotSearchCacher.class);
	
	static HotSearchCacher cacher = new HotSearchCacher();
	
	public static HotSearchCacher getInstance(){
		return cacher;
	}
	
	public static List<HotSearch> getHotSearchList(String catagoryId){
		List<HotSearch> list = new ArrayList<HotSearch>();
		try{
			Set<String> set = new JedisSortedSetManager(cacheKey + catagoryId).getAll();			
			Iterator<String> it = set.iterator();
			Gson gson = new Gson();
			while (it.hasNext()) {  
			  String str = it.next();
			  HotSearch hotSearch = gson.fromJson(str, HotSearch.class);
			  list.add(hotSearch);
			}  
		}catch(Exception e){
			log.error("HotSearchCacher.getHotSearchList error!",e);
			return list;
		}
		return list;
	}
	
	public boolean init(String catagoryId) {
		String cacheStatus = new JedisKeyManager(cacheLock + catagoryId).get();		
		if (!X.CachePrefix.LOCK_MARK.equals(cacheStatus)) {
			try{
				log.info("hotSearch init.........");
				new JedisKeyManager(cacheLock + catagoryId).set(X.CachePrefix.LOCK_MARK);
				
				List<HotSearch> list = HotSearchManager.getInstance().getHotSearchList(catagoryId);
				new JedisKeyManager(cacheKey + catagoryId).del();
				if(list != null)
				{
					for(HotSearch hotSearch : list){
						Gson g = new Gson();
						String content = g.toJson(hotSearch);
						new JedisSortedSetManager(cacheKey + catagoryId).add(hotSearch.getRank(), content);	
					}					
				}
			}
			catch(Exception e)
			{
				log.error("hotSearch init exception  " + e.getMessage());
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
