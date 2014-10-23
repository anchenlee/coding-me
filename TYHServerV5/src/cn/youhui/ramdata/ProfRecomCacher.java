package cn.youhui.ramdata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import cn.youhui.bean.ProfRecom;
import cn.youhui.bean.SuiShouAction;
import cn.youhui.common.X;
import cn.youhui.manager.JedisKeyManager;
import cn.youhui.manager.JedisSortedSetManager;
import cn.youhui.manager.ProfRecomDAO;
import cn.youhui.utils.SuiShouActionUtil;

/**
 * 达人推荐
 * @author YAOJIANBO
 *
 */
public class ProfRecomCacher {
	
	private static final Logger log = Logger.getLogger(ProfRecomCacher.class);
	
	private static final String bigCacheKey = X.CachePrefix.PROF_RECOM_BIG;        //用户访问长队列
	
	private static final String cacheKey = X.CachePrefix.PROF_RECOM_SET;
	
	private static String cacheLock = X.CachePrefix.CACHE_STATUS + X.DOT + cacheKey;
	
	private static int pageSize = 20;
	
	private static ProfRecomCacher instance = null;
	
	private ProfRecomCacher() {
	}

	public static ProfRecomCacher getInstance() {
		return instance == null ? (instance = new ProfRecomCacher()) : instance;
	}

	/**
	 * 第page页的列表
	 * @param start
	 * @param page
	 * @return
	 */
	public List<ProfRecom> getList(long start, int page,String platform){
		List<ProfRecom> list = new ArrayList<ProfRecom>();
		Map<String, Double> smap = new JedisSortedSetManager(bigCacheKey).getRangeByScores(start, 0);
		if(smap != null && smap.size() > 0){
			Set<String> ss = smap.keySet();
			if(ss != null && ss.size() > 0){
				Gson gson = new Gson();
				int i = 0;
				for(String ps : ss){
					i++;
					if(i > (page-1)*pageSize && i <= page*pageSize){
						ProfRecom pr = gson.fromJson(ps, ProfRecom.class);
						if ("ipad".equals(platform) || "apad".equals(platform)) {
							System.out.println("rank : " + pr.getRank());
							System.out.println("actionType : " +  pr.getActionType());
							System.out.println("actionValue : " + pr.getActionValue());
							pr.setAction(new SuiShouAction(SuiShouActionUtil.getSuiShouActionUrl(platform, "tagStyleItem", pr.getItemId())));
							System.out.println("url : " + pr.getAction().getUrl());
						}
						list.add(pr);
					}
				}
			}
		}
		return list;
	}
	
	/**
	 * 页数
	 * @param start
	 * @return
	 */
	public int getTotalPage(long start){
		int num = 0;
		Map<String, Double> smap = new JedisSortedSetManager(bigCacheKey).getRangeByScores(start, 0);
		if(smap != null){
			int size = smap.size();
			num = size/pageSize;
			if(size > num*pageSize){
				num ++;
			}
		}
		return num;
	}
	
	/**
	 * 从start开始 到现在的更新条数
	 * @param start
	 * @return
	 */
	public int getUpNum(long start){
		int num = 0;
		Map<String, Double> smap = new JedisSortedSetManager(bigCacheKey).getRangeByScores(System.currentTimeMillis(), start);
		if(smap != null){
			num = smap.size();
		}
		return num;
	}
	
	/**
	 * 从start开始 到end更新的条数
	 * @param start
	 * @param end
	 * @return
	 */
	public int getUpNum(long start, long end){
		int num = 0;
		Map<String, Double> smap = new JedisSortedSetManager(bigCacheKey).getRangeByScores(end, start);
		if(smap != null){
			num = smap.size();
		}
		return num;
	}
	
	public boolean init() {
		String cacheStatus = new JedisKeyManager(cacheLock).get();
		if (!X.CachePrefix.LOCK_MARK.equals(cacheStatus)) {
			try{
				log.info("ProfRecom Cache init.........");
				new JedisKeyManager(cacheLock).set(X.CachePrefix.LOCK_MARK);
				
				List<ProfRecom> list = ProfRecomDAO.getInstance().getListNew("android");
				
				if (list != null && list.size() > 0){
					new JedisKeyManager(cacheKey).delonly();
					Gson gson = new Gson();
					String json = "";
					for(ProfRecom bean : list){
						json = gson.toJson(bean);
						new JedisSortedSetManager(cacheKey).add(bean.getRank(), json);	
					}
				}
				
				log.info("ProfRecom Cache finish init");
			}catch(Exception e){
				log.error("ProfRecom Cache init exception  " + e.getMessage());
			}finally{
				new JedisKeyManager(cacheLock).del();
			}
		}
		return true;
	}
	
	public void reload(){
		init();
	}
	
	public static void main(String[] args) {
//		System.out.println(ProfRecomCacher.getInstance().getUpNum(1393486565768l));
//		ProfRecomCacher.getInstance().reload();
//		List<ProfRecom> list = (ProfRecomCacher.getInstance().getList(System.currentTimeMillis(), 1));
//		for(ProfRecom bean : list){
//			System.out.println(bean.getItemId());
//		}
//		System.out.println(ProfRecomCacher.getInstance().getList(System.currentTimeMillis(), 0).size());
		//1393486565768
//		System.out.println(System.currentTimeMillis());
		
		System.out.println(ProfRecomCacher.getInstance().getTotalPage(0));
		
	}
}
