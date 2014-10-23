package com.netting.cache.dao;

import com.netting.bean.AdDown;
import com.netting.redis.executor.JedisSortedSetManager;

public class AdminAdUpCacheDAO {

	private static String cachekey = "adup.adup";
	
	public static void addAdUp(String content,long rank){
		JedisSortedSetManager.add(cachekey, rank, content);
	}
	
	public static void delAdUp(String rank){
		JedisSortedSetManager.deleteByScore(cachekey, rank, rank);
	}
	
	public static void main(String[] args) {
//		addAdUp("{22}", 123);
//		addAdUp("{33}", 12);
//		AdDown ad = new AdDown();
//		ad.setRank(12);
//		delAdUp(ad);
//		System.out.println(JedisSortedSetManager.deleteByScore(cachekey, "-100", "100"));
		System.out.println(JedisSortedSetManager.getAllBydesc(cachekey).size());
		
	}
}
