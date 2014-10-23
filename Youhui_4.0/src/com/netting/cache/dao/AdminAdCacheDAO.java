package com.netting.cache.dao;

import com.google.gson.Gson;
import com.netting.bean.AD_Bean;
import com.netting.redis.executor.JedisSortedSetManager;


public class AdminAdCacheDAO {

	private static String cacheKey ="tyh_ad.tyh_ad";
	
	public static void addAd(AD_Bean ad){
		Gson g = new Gson();
		String content = g.toJson(ad);
		JedisSortedSetManager.add(cacheKey+ad.getPlatform(), ad.getRank(), content);
	}
	
	public static void delAd(AD_Bean ad){
		JedisSortedSetManager.deleteByScore(cacheKey+ad.getPlatform(), ad.getRank()+"", (ad.getRank())+"");
	}
	
	public static void main(String[] args) {
//		for(int i =0 ; i <5 ; i++){		
//			JedisSortedSetManager.add("test.test", i+12, i+"hher");
//		}
		JedisSortedSetManager.deleteByScore(cacheKey+"all", "-300", "12");
		System.out.println(JedisSortedSetManager.getAll(cacheKey+"all"));
	}
}
