package com.netting.cache.dao;

import com.google.gson.Gson;
import com.netting.bean.Sale;
import com.netting.redis.executor.JedisHashManager;
import com.netting.redis.executor.JedisSortedSetManager;

/**
 * 推荐管理---REDIS缓存更新操作类
 * @author YAOJIANBO
 *
 */
public class Admin_Recom_Cache_DAO 
{
	private static final String RECOMMEND = "sale.recommend";
	
	private static final String RECOMMEND_SAVE = RECOMMEND + ".save";
	
	private static final String SALE_TAG = "sale.tag";
	
	/**
	 * 更新推荐的缓存排序数据，key=sale.recommend  sale.recommend.save(尚未开始)
	 * @param recom
	 */
	public static void updateRecomRank(Sale recom)
	{
		JedisSortedSetManager.delete(RECOMMEND, recom.getId());
		JedisSortedSetManager.delete(RECOMMEND_SAVE, recom.getId());
		
		if(Long.parseLong(recom.getStartTime()) <= System.currentTimeMillis())
		{
			if(Long.parseLong(recom.getEndTime()) > System.currentTimeMillis())
			{
				JedisSortedSetManager.add(RECOMMEND , recom.getRank(), recom.getId());
			}
		}
		else
		{
			JedisSortedSetManager.add(RECOMMEND_SAVE , recom.getRank(), recom.getId());
		}
	}
	
	/**
	 * 删除推荐的缓存排序数据，key=sale.recommend  sale.recommend.save(尚未开始)
	 * @param recom
	 */
	public static void delRecomRank(String recom_id)
	{
		JedisSortedSetManager.delete(RECOMMEND, recom_id);
		JedisSortedSetManager.delete(RECOMMEND_SAVE, recom_id);
	}
	
	/**
	 * 更新推荐的缓存数据，key=sale.tag
	 * @param recom
	 */
	public static void setRecom(Sale recom)
	{
		JedisHashManager.set(SALE_TAG, recom.getId(), new Gson().toJson(recom));
	}
	
	/**
	 * 删除推荐的缓存数据，key=sale.tag
	 * @param recom
	 */
	public static void delRecom(String recom_id)
	{
		JedisHashManager.delete(SALE_TAG, recom_id);
	}
}
