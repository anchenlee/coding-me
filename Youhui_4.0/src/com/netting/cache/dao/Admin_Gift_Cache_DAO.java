package com.netting.cache.dao;

import com.netting.bean.Gift_Bean;
import com.netting.redis.executor.JedisHashManager;
import com.netting.redis.executor.JedisSortedSetManager;

/**
 * 礼物管理---REDIS缓存更新操作类
 * @author YAOJIANBO
 *
 */
public class Admin_Gift_Cache_DAO 
{
	private static final String GIFT_ID_BEAN = "gift.giftid2bean";
	
	private static final String GIFT_RANK = "gift.giftid2rank";
	
	/**
	 * 更新礼物的缓存排序数据，key=gift.giftid2rank
	 * @param recom
	 */
	public static void updateRecomRank(Gift_Bean gift)
	{
		JedisSortedSetManager.delete(GIFT_RANK, gift.getId());
		
		if(Long.parseLong(gift.getStart_time()) <= System.currentTimeMillis())
		{
			if(Long.parseLong(gift.getEnd_time()) > System.currentTimeMillis())
			{
				JedisSortedSetManager.add(GIFT_RANK , gift.getRank(), gift.getId());
			}
		}
	}
	
	/**
	 * 删除礼物的缓存排序数据，key=gift.giftid2rank
	 * @param recom
	 */
	public static void delGiftRank(String gift_id)
	{
		JedisSortedSetManager.delete(GIFT_RANK, gift_id);
	}
	
	/**
	 * 更新礼物的缓存数据，key=gift.giftid2bean
	 * @param recom
	 */
	public static void setGfit(Gift_Bean gift)
	{
		JedisHashManager.set(GIFT_ID_BEAN, gift.getId(), gift.converToRedisJson());
	}
	
	/**
	 * 删除礼物的缓存数据，key=gift.giftid2bean
	 * @param recom
	 */
	public static void delGift(String gift_id)
	{
		JedisHashManager.delete(GIFT_ID_BEAN, gift_id);
	}
}
