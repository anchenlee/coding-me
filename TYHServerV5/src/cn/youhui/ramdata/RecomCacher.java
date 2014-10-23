package cn.youhui.ramdata;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import cn.youhui.bean.Action;
import cn.youhui.bean.Sale;
import cn.youhui.common.X;
import cn.youhui.manager.JedisKeyManager;
import cn.youhui.manager.JedisSortedSetManager;
import cn.youhui.manager.SaleManager;
import cn.youhui.utils.ActionChangeUtil;


public class RecomCacher {
	
	private static final Logger log = Logger.getLogger(RecomCacher.class);
	private static final String cacheKey = X.CachePrefix.RECOMMEND;
	private static final String cacheKeySave = X.CachePrefix.RECOMMEND + ".save";
	private static String cacheLock = X.CachePrefix.CACHE_STATUS + X.DOT + cacheKey;
	private static final String RECOM_ID = "2";
	private static final int PAGE_SIZE = 10;
	private static boolean isneedRefresh = true;
	
	private static RecomCacher instance;
	
	private RecomCacher() {
	}
	
	public static RecomCacher getInstance(){
		if (instance == null){
			instance = new RecomCacher();
		}
		return instance;
	}
	
	public List<Sale> getRecomList(int page, String uid, String platform, String version_code)
	{
		if(isneedRefresh){
			refresh();
			isneedRefresh = false;
		}
		List<Sale> ret = new ArrayList<Sale>();
		Set<String> idset = new JedisSortedSetManager(cacheKey).zrange((page-1)*PAGE_SIZE, page*PAGE_SIZE - 1); 
		if(idset != null && idset.size() > 0)
		{
			for(String id : idset)
			{
				Sale bean = SaleCache.getInstance().getSale(id);
				
				if (bean != null)
				{
					if(bean.getEndTime() < System.currentTimeMillis())
					{
						isneedRefresh = true;
						continue;
					}
				}
				
				// 低版本检查，并转换Action
				String action_type = bean.getAction().getActionType();
				String action_value = bean.getAction().getActionValue();
				Action action = ActionChangeUtil.lowVersionItemAction(bean.getId(), uid, platform, "9", action_type, action_value, version_code);
				bean.setAction(action);
				
				if (bean.getId().equals("146"))
				{
					ret.add(0, bean);
				}
				else
				{
					ret.add(bean);	
				}
			}
			return ret;
		}
		else
		{
			refresh();
			return null;
		}
	}
	
	public int getPageNum()
	{
		int ret = 0;
		int count = (int)new JedisSortedSetManager(cacheKey).size();
		int ys = count % PAGE_SIZE;
		ret = (count - ys)/PAGE_SIZE;
		if(ys > 0)
		{
			ret ++;
		}
			
		return ret;
	}
	
	public void refresh()
	{
		Set<String> idsaves = new JedisSortedSetManager(cacheKeySave).getAll();
		if(idsaves != null)
		{
			SaleCache.getInstance().reload();
			for(String id : idsaves)
			{
				Sale sale = SaleCache.getInstance().getSale(id);
				if(sale.getStartTime() <= System.currentTimeMillis() && sale.getEndTime() > System.currentTimeMillis())
				{
					//已经开始的，加入到当前列表中，并从预备列表中删除
					new JedisSortedSetManager(cacheKey).add(sale.getRank(), sale.getId());
					new JedisSortedSetManager(cacheKeySave).delete(sale.getId());
				}
			}
		}
		Set<String> ids = new JedisSortedSetManager(cacheKey).getAll();
		if(ids != null)
		{
			for(String id : ids)
			{
				Sale sale = SaleCache.getInstance().getSale(id);
				if(sale.getEndTime() <= System.currentTimeMillis())
				{
					//已经过期的，还没开始的，从当前列表中删除
					new JedisSortedSetManager(cacheKey).delete(sale.getId());
				}
				if (sale.getStartTime() > System.currentTimeMillis())
				{
					//还没开始的，从当前列表中删除，并添加到预备列表中
					new JedisSortedSetManager(cacheKey).delete(sale.getId());
					new JedisSortedSetManager(cacheKeySave).add(sale.getRank(), sale.getId());
				}
			}
		}
	}
	
	public boolean init() {
		String cacheStatus = new JedisKeyManager(cacheLock).get();
		if (!X.CachePrefix.LOCK_MARK.equals(cacheStatus)) {
			try{
				log.info("recommend init.........");
				new JedisKeyManager(cacheLock).set(X.CachePrefix.LOCK_MARK);
				List<Sale> list = SaleManager.getInstance().getSaleItemIds(RECOM_ID);
				new JedisKeyManager(cacheKey).del();
				new JedisKeyManager(cacheKeySave).del();
				if(list != null)
				{
					for(Sale sale : list)
					{
						if(System.currentTimeMillis() < sale.getEndTime())
						{
							if(System.currentTimeMillis() >= sale.getStartTime())
							{
								new JedisSortedSetManager(cacheKey).add(sale.getRank(), sale.getId());	
							}
							else
							{
								new JedisSortedSetManager(cacheKeySave).add(sale.getRank(), sale.getId());
							}
						}
					}
				}
			}
			catch(Exception e)
			{
				log.error("recommend init exception  " + e.getMessage());
			}
			finally
			{
				new JedisKeyManager(cacheLock).del();
			}
		}
		return true;
	}
	
	public void reload(){
		init();
	}
	
}
