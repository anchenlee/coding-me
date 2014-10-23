package cn.youhui.ramdata;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import cn.youhui.bean.ExchItem;
import cn.youhui.common.RedisExecutor;
import cn.youhui.common.RedisMySqlExecutor;
import cn.youhui.common.RedisMySqlRunner;
import cn.youhui.common.RedisRunner;
import cn.youhui.common.X;
import cn.youhui.manager.ExchItemManager;

public class ExchItemCacher {

    private static final Logger log = Logger.getLogger(ExchItemCacher.class);
	private static String cacheKey = X.CachePrefix.EXCHITEM_ALL;
	private static String cacheLock = X.CachePrefix.CACHE_STATUS + X.DOT + cacheKey;
	private static ExchItemCacher instance = null;
	private static final int PageSize = 3;
 	
	private ExchItemCacher(){
	}
	
	public static ExchItemCacher getInstance(){
		if(instance == null) instance = new ExchItemCacher();
		return instance;
	}
	
	public void init() {
		RedisMySqlRunner<Boolean> r = new RedisMySqlRunner<Boolean>() {
			@Override
			public Boolean run(Connection conn, Jedis jedis)
					throws JedisConnectionException, SQLException {
				String cacheStatus = jedis.get(cacheLock);
				if(!X.CachePrefix.LOCK_MARK.equals(cacheStatus)){
					try {
						jedis.set(cacheLock, X.CachePrefix.LOCK_MARK);
						log.info("exchitem init.....");
						List<ExchItem> list = ExchItemManager.getInstance().getAll();
						Gson gson = new Gson();
						jedis.del(cacheKey);
						for(ExchItem item : list){
							jedis.hset(cacheKey, item.getItemId(), gson.toJson(item));
						}
					} catch (Exception e) {
						log.info("exchitem init exception : " + e.getMessage());
					}finally{
						jedis.del(cacheLock);
					}
				}
				return true;
			}
		};
		RedisMySqlExecutor<Boolean> e = new RedisMySqlExecutor<Boolean>();
		e.exe(r);
	
	}
	
	public void reload() {
		init();
	}
	
	public ExchItem getExchItem(final String id){
		RedisRunner<ExchItem> r = new RedisRunner<ExchItem>() {

			@Override
			public ExchItem run(Jedis jedis) throws JedisConnectionException {
				Gson gson = new Gson();
				if(id != null){
				String itemStr = jedis.hget(cacheKey, id);
				if(itemStr != null){
					ExchItem item = gson.fromJson(itemStr, ExchItem.class);
					long now = new Date().getTime();
					if(item.getListTime() < now && now <item.getDelistTime()){
						if(item.getPromStartTime() > now || now > item.getPromEndTime())
							item.setPromPrice(item.getPrice());
						return item;
					}
				}
				else return null;
				}
				return null;
			}
		};
		return new RedisExecutor<ExchItem>().exe(r);
	}
	
	public List<ExchItem> getItemList(final int page) {
		RedisRunner<List<ExchItem>> r = new RedisRunner<List<ExchItem>>() {

			@Override
			public List<ExchItem> run(Jedis jedis) throws JedisConnectionException {
				List<ExchItem> list = new ArrayList<ExchItem>();
				Map<String, String> map = jedis.hgetAll(cacheKey);
				Gson gson = new Gson();
				for(Map.Entry<String, String> m : map.entrySet()){
					ExchItem item = gson.fromJson(m.getValue(), ExchItem.class);
					long now = new Date().getTime();
					if(item.getListTime() < now && now <item.getDelistTime()){
						if(item.getPromStartTime() > now || now > item.getPromEndTime())
							item.setPromPrice(item.getPrice());
				      list.add(item);
					}
				}
				int last = page*PageSize;
				int total = list.size();
				if(last > total) last = total;
				list = list.subList((page-1)*PageSize,last);
				return list;
			}
		};
		return new RedisExecutor<List<ExchItem>>().exe(r);
	}
	
	public int getTotal() {
		RedisRunner<Integer> r = new RedisRunner<Integer>() {
			@Override
			public Integer run(Jedis jedis) throws JedisConnectionException {
				Map<String, String> map = jedis.hgetAll(cacheKey);
				Gson gson = new Gson();
				int total = 0;
				for(Map.Entry<String, String> m : map.entrySet()){
					ExchItem item = gson.fromJson(m.getValue(), ExchItem.class);
					long now = new Date().getTime();
					if(item.getListTime() < now && now <item.getDelistTime()){
				      total++;
					}
				}
				if(total%PageSize == 0)
				   return total/PageSize;
				else return total/PageSize + 1;
			}
		};
		return new RedisExecutor<Integer>().exe(r);
	}
	
	public static void main(String[] a){
		ExchItemCacher.getInstance().reload();
	}
}
