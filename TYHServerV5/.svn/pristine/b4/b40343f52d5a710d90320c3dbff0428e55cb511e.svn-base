package cn.youhui.ramdata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.youhui.bean.TeJiaGoodsBean;
import cn.youhui.bean.UidToItemId;
import cn.youhui.common.X;
import cn.youhui.manager.JedisKeyManager;
import cn.youhui.manager.JedisSortedSetManager;
import cn.youhui.manager.LikeItemManager;
import cn.youhui.utils.JfbRateUtil;

public class LikeItemCacher {
	private static final Logger log = Logger.getLogger(LikeItemCacher.class);
	private static String cacheKey = X.CachePrefix.USER_LIKE_ITEM;
	private static String cacheLock = X.CachePrefix.CACHE_STATUS + X.DOT + X.CachePrefix.USER_LIKE_ITEM;
	static LikeItemCacher instance = null;
	private final int pageSize = 15;
	
	public static LikeItemCacher getInstance() {
		return instance == null ? (instance = new LikeItemCacher()) : instance;
	}
	
	public boolean addLikeItem(String uid, String itemId){
		try{
			new JedisSortedSetManager(cacheKey + uid).add(System.currentTimeMillis(), itemId);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean delLikeItem(String uid, String itemId){
		long i = new JedisSortedSetManager(cacheKey + uid).delete(itemId);
		if(i > 0) return true;
		else return false;
	}
	
	public boolean isExist(String uid, String itemId){
		return new JedisSortedSetManager(cacheKey + uid).isExist(itemId); 
	}
	
	public List<TeJiaGoodsBean> getLikeItemsByUid(String uid, int page){
		List<TeJiaGoodsBean> list = new ArrayList<TeJiaGoodsBean>();
			if(page <= 0) page = 1;
			long size = new JedisSortedSetManager(cacheKey + uid).size();
			if(size == 0){
				List<UidToItemId> li = LikeItemManager.getInstance().getLikeUid2Item(uid);
				addUidToItemId(li);
				size = new JedisSortedSetManager(cacheKey + uid).size();
			}
			Map<String, Double> ret = null;
			if(size > 0){
				if(size > pageSize*page){
					ret = new JedisSortedSetManager(cacheKey + uid).getRangeWithScores(pageSize*(page-1), pageSize*page-1);
				}else if(size > pageSize*(page-1)){
					ret = new JedisSortedSetManager(cacheKey + uid).getRangeWithScores(pageSize*(page-1), (int)size-1);
				}else{
					ret = new JedisSortedSetManager(cacheKey + uid).getRangeWithScores(0, pageSize);
				}
			}
			if(ret != null && ret.size() > 0){
				for(Map.Entry<String, Double> m : ret.entrySet()){
					TeJiaGoodsBean item = TagItemCacher.getInstance().getProduct(m.getKey());
					if(item != null){
						item.setInterviewTime(m.getValue().longValue());
						list.add(item);
					}else{
						delLikeItem(uid, m.getKey());
						return getLikeItemsByUid(uid, page);
					}
				}
			}
		return list;
	}
	
	public int getLikeItemTotalByUid(String uid){
		long size = new JedisSortedSetManager(cacheKey + uid).size();
		if(size%pageSize == 0)
		   return (int)(size/pageSize);
		else return (int)(size/pageSize + 1);
	}
	
	public boolean init(){
	    boolean flag = false;
		String lock = new JedisKeyManager(cacheLock).get();
		if(!X.CachePrefix.LOCK_MARK.equals(lock)){
			try{
				log.info("likeitem init.........");
				new JedisKeyManager(cacheLock).set( X.CachePrefix.LOCK_MARK);
				List<UidToItemId> list = LikeItemManager.getInstance().getLikeUid2Item();
				new JedisKeyManager(cacheKey).del();
				addUidToItemId(list);
			}catch(Exception e){
				log.error("likeitem init exception  " + e.getMessage());
			}finally{
				new JedisKeyManager(cacheLock).del();								
			}
		}
		return flag;
	}
	
	private void addUidToItemId(List<UidToItemId> list){
		if(list != null && list.size() > 0){
	    	for(UidToItemId iti : list){
	    		new JedisSortedSetManager(cacheKey + iti.getUid()).add(iti.getTimestamp(), iti.getItemId());
	    	}
	    }
	}
	public void reload(){
		init();
	}
}
