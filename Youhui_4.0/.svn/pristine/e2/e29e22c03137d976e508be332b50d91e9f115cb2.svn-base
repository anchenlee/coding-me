package com.netting.cache.dao;

import com.netting.redis.executor.JedisSortedSetManager;

public class AdminSearchKeywordCacheDAO {

	private static final String cacheKey = "keyword.keyword2item";
	
	
	public static void addItem2Keyword(String keyword_id, String itemid, double rank)
	{
		JedisSortedSetManager.add(cacheKey + keyword_id, rank, itemid);
	}
}
