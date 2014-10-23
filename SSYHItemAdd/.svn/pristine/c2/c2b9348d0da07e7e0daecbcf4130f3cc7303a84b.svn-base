package cn.youhui.cache.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.youhui.admin.dao.AdminRecoItemDAO;
import cn.youhui.bean.ProfRecom;
import cn.youhui.platform.jedis.JedisHashManager;
import cn.youhui.platform.jedis.JedisKeyManager;
import cn.youhui.platform.jedis.JedisSortedSetManager;

import com.google.gson.Gson;

public class RecoItemCacheDAO {

	private static String cacheKey = "youhui.prof.recom.items";
	
	private static final String bigCacheKey = "youhui.prof.recom.itemsbig";
	
	private static int baocunNum = 300;//首页保留的个数
	
	public static void addHomePage(ProfRecom bean){
		Gson gson = new Gson();
		String json =  gson.toJson(bean);
		new JedisSortedSetManager(cacheKey).add(bean.getRank(), json);
	}
	
	public static void delRecoItem(String member){
		new JedisSortedSetManager(cacheKey).delete(member);
	}
	
	public static void delRecoItemByRank(int start,int end){
		new JedisSortedSetManager(cacheKey).deleteByRank(start, end);
	}
	
	public static int getSize(){
		return new JedisSortedSetManager(cacheKey).getRange(0, -1).size();
	}
	
	public static Set<String> getAllReco(){
		Set<String> set = new JedisSortedSetManager(cacheKey).getAll(); 
		
		return set;
	}
	
	public static Set<String> getAllBigReco(){
		Set<String> set = new JedisSortedSetManager(bigCacheKey).getAllBigToSmall();
		
		return set;
	}
	
	public static Map<String, Double> getMap(long start,long end){
		Map<String, Double> map = new JedisSortedSetManager(cacheKey).zangeByScores(start, end);
		return map;
	}
	
	public static Map<String, Double> getBigRecoMap(long start,long end){
		Map<String, Double> map = new JedisSortedSetManager(bigCacheKey).zangeByScores(start, end);
		return map;
	}
	
	public static void reload(){
		List<ProfRecom> list = AdminRecoItemDAO.getListNew();
		if (list != null && list.size() > 0){
			new JedisKeyManager(cacheKey).delonly();
			Gson gson = new Gson();
			String json = "";
			for(ProfRecom bean : list){
				json = gson.toJson(bean);
				new JedisSortedSetManager(cacheKey).add(bean.getRank(), json);	
			}
		}
	}
	
	
}
