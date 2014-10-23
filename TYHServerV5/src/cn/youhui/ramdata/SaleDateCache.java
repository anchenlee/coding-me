
package cn.youhui.ramdata;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import cn.youhui.manager.JedisKeyManager;
import cn.youhui.manager.JedisSortedSetManager;
import cn.youhui.manager.SaleManager;
import cn.youhui.utils.DateUtil;
import cn.youhui.utils.StringUtils;
import cn.youhui.bean.Sale;
import cn.youhui.common.X;


public class SaleDateCache{
	
	private static final Logger log = Logger.getLogger(SaleDateCache.class);
	private static final String cacheKey = X.CachePrefix.SALE_DATE2TAG;
	private static final String cacheLock = X.CachePrefix.CACHE_STATUS + X.DOT + cacheKey;
	private static final String BEFORE = "before";
	private static final String AFTER = "after";
	private static final String SALE_ID = "1";      //特卖id
	private static final int PAGE_SIZE = 10;
	private static final String freshSign = X.CachePrefix.SALE_FRESH_SIGN;
	
	private static SaleDateCache instance;
	
	private SaleDateCache() {
	}
	
	public static SaleDateCache getInstance(){
		if (instance == null){
			instance = new SaleDateCache();
		}
		return instance;
	}
	
	public List<Sale> getAfterSale(int page) {
		return getAfterSaleList(SALE_ID, page);
	}
	
	public List<Sale> getBeforeSale(int page) {
		List<Sale> salelist = getBeforeSaleList(SALE_ID, page);
		sortSale(salelist);
		return salelist;
	}
	
	/**
	 * 把已结束的置底
	 */
	private void sortSale(List<Sale> salelist){
		List<Sale> outTimeSale = new ArrayList<Sale>();
		if(salelist != null){
			for(Sale sale : salelist){
				if(sale.getEndTime() < System.currentTimeMillis()){
					outTimeSale.add(sale);
				}
			}
		}
		if(outTimeSale != null && outTimeSale.size() > 0){
			salelist.removeAll(outTimeSale);
		    salelist.addAll(salelist.size(), outTimeSale);
		}
	}
	
	private List<Sale> getBeforeSaleList(String paid, int page) {
		List<Sale> ret = new ArrayList<Sale>();
		Set<String> idset = new JedisSortedSetManager(cacheKey + BEFORE + paid).getRange(page*PAGE_SIZE, (page+1)*PAGE_SIZE - 1); 
		if(idset != null && idset.size() > 0){
			for(String id : idset){
				Sale bean = SaleCache.getInstance().getSale(id);
				if(bean != null){
					ret.add(bean);
				}
			}
			return ret;
		}else{
			return null;
		}
	}
	
	private List<Sale> getAfterSaleList(String paid, int page){
		page = -page;
		List<Sale> ret = new ArrayList<Sale>();
		Set<String> idset = new JedisSortedSetManager(cacheKey + AFTER + paid).zrange((page-1)*PAGE_SIZE, page*PAGE_SIZE - 1); 
		if(idset != null && idset.size() > 0){
			for(String id : idset){
				Sale bean = SaleCache.getInstance().getSale(id);
				if(bean != null){
					ret.add(bean);
				}
			}
			return ret;
		}else{
			return null;
		}
	}
	
	public long getSaleBeforeSize(){
		return getBeforeSize(SALE_ID);
	}
	
	public long getSaleAfterSize(){
		return getAfterSize(SALE_ID);
	}
	
	private long getBeforeSize(String paid){
		int size = 0;
		long count = new JedisSortedSetManager(cacheKey +BEFORE+ paid).size();
		int ys = (int) (count%PAGE_SIZE);
		size = (int)(count - ys)/PAGE_SIZE;
		if(ys > 0)
			size ++;
		return size;
	}
	
	private long getAfterSize(String paid){
		int size = 0;
		long count = new JedisSortedSetManager(cacheKey +AFTER+ paid).size();
		int ys = (int) (count%PAGE_SIZE);
		size = (int)(count - ys)/PAGE_SIZE;
		if(ys > 0)
			size ++;
		return size;
	}
	
	/**
	 * 从after中获取今天之前的，放入before中
	 * @return
	 */
	private int refresh(String paid){
		int ret = 0;
		Map<String, Double> due = new JedisSortedSetManager(cacheKey + AFTER + paid).getRangeByScores(0, SaleManager.getInstance().getToday());   
		if(due != null && due.size() > 0){
			for(Map.Entry<String, Double> m : due.entrySet()){
				new JedisSortedSetManager(cacheKey + AFTER + paid).delete(m.getKey());
				ret += new JedisSortedSetManager(cacheKey + BEFORE + paid).add(m.getValue(), m.getKey());
			}			
		}
		return ret;
	}
	
	/**
	 * 是否需要更新
	 * @return
	 */
	private boolean isNeedFresh(String paid){
		boolean flag = false;
		String lastFreshTime = new JedisKeyManager(freshSign + paid).get();
		if(StringUtils.isNumeric(lastFreshTime)){
			long lastFreshTimeL = Long.parseLong(lastFreshTime);
			if(DateUtil.differ(new Date(), new Date(lastFreshTimeL)) > 0)
				flag = true;
		}else{
			flag = true;
		}
		return flag;
	}
	
	private void addFreshSign(String paid, long time){
		new JedisKeyManager(freshSign + paid).set(time + "");
	}
	
	public int refreshSale(){
		return refresh(SALE_ID);
	}
	
	public boolean isSaleNeedFresh(){
		return isNeedFresh(SALE_ID);
	}
	
	public void addSaleFreshSign(long time){
		addFreshSign(SALE_ID, time);
	}
	
	public int getNotStartNum(){
		Map<String, Double> map = new JedisSortedSetManager(cacheKey + BEFORE + SALE_ID).getRangeWithScores(Integer.MIN_VALUE, Integer.MAX_VALUE);

		int ret = 0;
		for(Map.Entry<String, Double> m : map.entrySet()){
			double time = m.getValue();
			if(time > System.currentTimeMillis()){
				ret ++;
			}else if(time < System.currentTimeMillis()){
				break;
			}
		}
		return ret;
	}
	
	public boolean init() {
		String cacheStatus = new JedisKeyManager(cacheLock).get();
		if (!X.CachePrefix.LOCK_MARK.equals(cacheStatus)) {
			try{
				log.info("saledate init.........");
				new JedisKeyManager(cacheLock).set(X.CachePrefix.LOCK_MARK);
				List<Sale> bflist = SaleManager.getInstance().getSaleItems(SALE_ID);
				new JedisKeyManager(cacheKey + "*").del();
				System.out.println(bflist.size());
				for(Sale sale : bflist){
					new JedisSortedSetManager(cacheKey + BEFORE + sale.getParentId()).add(sale.getStartTime(), sale.getId());
				}
//				List<Sale> aflist = SaleManager.getInstance().getAfterSaleItems(SALE_ID);
//				for(Sale sale : aflist){
//					new JedisSortedSetManager(cacheKey + AFTER + sale.getParentId()).add(sale.getStartTime(), sale.getId());
//				}
			}catch(Exception e){
				log.error("saledate init exception  " + e.getMessage());
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
		SaleDateCache.getInstance().reload();
	}
}