package cn.youhui.ramdata;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.util.SafeEncoder;

import cn.youhui.bean.ItemBean;
import cn.youhui.common.RedisExecutor;
import cn.youhui.common.RedisRunner;
import cn.youhui.common.X;

/**
 * favorite items list for each user
 * 
 * Redis Data structure : sorted set
 * sorted_set_key:X.CachePrefix.FAV_USER_2_ITEMS.$uid { score member time itemId
 * 137... [{.....}] }
 * 
 * ***/
public class FavUser2ItemsCacher {

	private int PAGE_SIZE = 20;
	private String cacheKey = "";

	public FavUser2ItemsCacher(long uid) {
		this.cacheKey = new StringBuilder(X.CachePrefix.FAV_USER_2_ITEMS)
				.append(X.DOT).append(uid).toString();
	}
	
	public boolean isExist(){
		RedisRunner<Boolean> runner = new RedisRunner<Boolean>() {

			@Override
			public Boolean run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				return jedis.exists(cacheKey);
			}
		};

		RedisExecutor<Boolean> re = new RedisExecutor<Boolean>();

		return re.exe(runner);
	}
	
	public boolean hasItemId(final long itemId) {
		RedisRunner<Boolean> runner = new RedisRunner<Boolean>() {

			@Override
			public Boolean run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				Double score = jedis.zscore(cacheKey, Long.toString(itemId));
				return score == null || score.doubleValue() <= 0 ? false : true;
			}
		};

		RedisExecutor<Boolean> re = new RedisExecutor<Boolean>();

		return re.exe(runner);
	}

	/**
	 * add one or more item id for this user
	 * **/
	public boolean addItemId(final long... itemId) {

		RedisRunner<Boolean> runner = new RedisRunner<Boolean>() {

			@Override
			public Boolean run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				for (int i = 0; i < itemId.length; i++) {
					jedis.zadd(cacheKey, System.currentTimeMillis() + i,
							Long.toString(itemId[i]));
				}
				return true;
			}
		};

		RedisExecutor<Boolean> re = new RedisExecutor<Boolean>();

		return re.exe(runner);
	}

	/**
	 * get item
	 */
	public ArrayList<String> getItems() {

		RedisRunner<ArrayList<String>> runner = new RedisRunner<ArrayList<String>>() {

			@Override
			public ArrayList<String> run(Jedis jedis)
					throws JedisConnectionException {
				// TODO Auto-generated method stub
				ArrayList<String> list = new ArrayList<String>();
				Set<String> set = jedis.zrangeByScore(cacheKey, Double.MIN_VALUE,
						Double.MAX_VALUE);
				if (set != null) {
					Iterator<String> ite = set.iterator();
					while (ite.hasNext()) {
						String key = ite.next();
						list.add(key);
					}
				}
				return list;
			}
		};

		RedisExecutor<ArrayList<String>> re = new RedisExecutor<ArrayList<String>>();

		return re.exe(runner);
	}

	public ArrayList<String> getItems(final int page) {

		RedisRunner<ArrayList<String>> runner = new RedisRunner<ArrayList<String>>() {

			@Override
			public ArrayList<String> run(Jedis jedis)
					throws JedisConnectionException {
				// TODO Auto-generated method stub
				int start = PAGE_SIZE * (page - 1);
				int end = start + PAGE_SIZE - 1;
				ArrayList<String> list = new ArrayList<String>();
				Set<String> set = jedis.zrange(cacheKey, start, end);
				for (String key : set)
					list.add(key);
				return list;
			}
		};

		RedisExecutor<ArrayList<String>> re = new RedisExecutor<ArrayList<String>>();

		return re.exe(runner);
	}
	
	

	/**
	 * delete item
	 * */
	public boolean deleteItems() {

		RedisRunner<Boolean> runner = new RedisRunner<Boolean>() {

			@Override
			public Boolean run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				boolean flag = false;
				long i = jedis.del(SafeEncoder.encode(cacheKey));
				flag = i > 0 ? true : false;
				return flag;
			}
		};

		RedisExecutor<Boolean> re = new RedisExecutor<Boolean>();

		return re.exe(runner);
	}

	/**
	 * 获取收藏商品总数
	 * **/
	public int getTotalCount() {
		RedisRunner<Integer> r = new RedisRunner<Integer>() {

			@Override
			public Integer run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				long count = jedis.zcount(cacheKey, Double.MIN_VALUE,
						Double.MAX_VALUE);
				return (int) count;
			}
		};
		RedisExecutor<Integer> e = new RedisExecutor<Integer>();
		return e.exe(r);
	}

	/**
	 * 获取接口 列表每页 多少个商品
	 * **/
	public int getPageSize() {
		return this.PAGE_SIZE;
	}

	/**
	 * 获取分页数量
	 * */
	public int getPageCount() {
		int totalCount = getTotalCount();
		int pageCount = (totalCount % PAGE_SIZE == 0 ? totalCount / PAGE_SIZE
				: totalCount / PAGE_SIZE + 1);
//		pageCount = pageCount > 0 ? pageCount : 1;
		return pageCount;
	}

	/**
	 * 分页 返回收藏列表 ID
	 * **/
	public List<Long> getFavItemIDList(long uid, int pageIndex,
			String accessToken) {
		return null;
	}

	/**
	 * 分页 返回收藏列表 ITEM
	 * **/
	public List<ItemBean> getFavItemBeanList(long uid, int pageIndex,
			String accessToken) {
		return null;
	}

}
