package cn.youhui.ramdata;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import cn.youhui.bean.FrameTitle;
import cn.youhui.common.X;
import cn.youhui.manager.FrameTitleManager;
import cn.youhui.manager.JedisHashManager;
import cn.youhui.manager.JedisKeyManager;
import cn.youhui.manager.JedisSortedSetManager;

public class FrameTitleCache {

	/**
	 * 鏂扮殑棣栭〉 key
	 */
	private static String rankCachekey = "homepage.homepage2id";
	
	private static String homePageKey = "homepage.homepage";
	
	private static String cacheLock = X.CachePrefix.CACHE_STATUS + X.DOT + rankCachekey;
	
	private static final Logger log = Logger.getLogger(AdCacher.class);
	
	public static List<FrameTitle> getFrameTitleList(String platform){
		List<FrameTitle> list = new ArrayList<FrameTitle>();
		Set<String> set = new JedisSortedSetManager(rankCachekey).getAllDesc();
		
		if (set == null ) {
			return list;
		}
		Iterator<String> it = set.iterator();  
		while (it.hasNext()) {
			String id = it.next();
			FrameTitle bean = getFrameTitle(id);
			if (platform.equals(bean.getVersion()) || "custom".equals(bean.getVersion())) {
				list.add(bean);
			}
		}
		return list;
	}
	
	public static FrameTitle getFrameTitle(String id){
		FrameTitle bean = null;
		
		String content = new JedisHashManager(homePageKey).get(id);
		Gson g = new Gson();
		bean = g.fromJson(content, FrameTitle.class);
		
		return bean;
	}
	
	
	public static void init(String platform){
		String cacheStatus = new JedisKeyManager(cacheLock).get();
		if (!X.CachePrefix.LOCK_MARK.equals(cacheStatus)) {
			try{
				log.info("FrameTitle init.........");
				new JedisKeyManager(cacheLock).set(X.CachePrefix.LOCK_MARK);					
				List<FrameTitle> list = FrameTitleManager.getFrameTitleList(platform);
				if(list != null)
				{
					new JedisKeyManager(rankCachekey).del();
					new JedisKeyManager(homePageKey).del();
					for(FrameTitle bean : list){
						Gson g = new Gson();
						String content = g.toJson(bean);
						System.out.println(bean.getId() + " " +bean.getTitle());
						new JedisSortedSetManager(rankCachekey).add(bean.getRank(), bean.getId());
						new JedisHashManager(homePageKey).add(bean.getId(), content);
					}						
				}			
								
			}
			catch(Exception e)
			{
				log.error("FrameTitle init exception  " + e.getMessage());
			}
			finally
			{
				new JedisKeyManager(cacheLock).del();
			}
		}
	}
	
	public static void reload(){
		init("");
	}
	
	public static void main(String[] args) {
//		System.out.println(getFrameTitleList());
//		init();
		Set<String> set = new JedisSortedSetManager("adup.adup").getAllDesc();
		Iterator<String> list = set.iterator();
		while (list.hasNext()) {
			Gson gs = new Gson();
			FrameTitle ft = gs.fromJson(list.next(), FrameTitle.class);
			System.out.println(ft);
			String str = gs.toJson(ft, FrameTitle.class);
			System.out.println(str);
			
		}
	}
	
}
