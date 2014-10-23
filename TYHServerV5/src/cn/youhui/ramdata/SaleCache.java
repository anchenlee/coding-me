
package cn.youhui.ramdata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.youhui.manager.JedisHashManager;
import cn.youhui.manager.JedisKeyManager;
import cn.youhui.manager.SaleManager;
import cn.youhui.bean.Sale;
import cn.youhui.common.X;

import com.google.gson.Gson;

public class SaleCache {
	private static final Logger log = Logger.getLogger(SaleCache.class);
	private static final String cacheKey = X.CachePrefix.SALE_TAG;
	private static String cacheLock = X.CachePrefix.CACHE_STATUS + X.DOT + cacheKey;
	
	private static SaleCache instance;
	
	private SaleCache() {
	}
	
	public static SaleCache getInstance(){
		if (instance == null){
			instance = new SaleCache();
		}
		return instance;
	}
	
	public Sale getSale(final String id) {
		String saleStr = new JedisHashManager(cacheKey).get(id);
		Sale sale = null;
		sale = new Gson().fromJson(saleStr, Sale.class);
		return sale;
	}
	
	
	public boolean init() {
		String cacheStatus = new JedisKeyManager(cacheLock).get();
		if (!X.CachePrefix.LOCK_MARK.equals(cacheStatus)) {
			try{
				log.info("sale init.........");
				new JedisKeyManager(cacheLock).set(X.CachePrefix.LOCK_MARK);
				List<Sale> list = SaleManager.getInstance().getSaleItems();
				Map<String, String> smap = new HashMap<String, String>();
				Gson gson = new Gson();
				for(Sale sale : list){
					smap.put(sale.getId(), gson.toJson(sale, Sale.class));
				}
				new JedisKeyManager(cacheKey).del();
				new JedisHashManager(cacheKey).setAll(smap);
			}catch(Exception e){
				log.error("sale init exception  " + e.getMessage());
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
		SaleCache.getInstance().reload();
	}
}