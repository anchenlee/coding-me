package cn.youhui.cache.dao;

import cn.youhui.platform.jedis.JedisSortedSetManager;

public class SearchKeywordCacheDAO {

	private static final String cacheKey = "keyword.keyword2item";
	
	public static void addItem2Keyword(String keyword_id, String itemid, double rank){
		new JedisSortedSetManager(cacheKey+keyword_id).add(rank, itemid);
	}
	
	public static void delItem2Keyword(String keyword_id, String item_id)
	{
		new JedisSortedSetManager(cacheKey + keyword_id).delete(item_id);
	}
	
}
