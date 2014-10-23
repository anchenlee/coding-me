package cn.youhui.ramdata;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.util.SafeEncoder;
import cn.youhui.bean.ItemBean;
import cn.youhui.common.RedisExecutor;
import cn.youhui.common.RedisRunner;
import cn.youhui.common.X;
import cn.youhui.dao.JedisDBIns;

/**
 *  某一个item被多少用户收藏 
 * 
 * favorited users for each item , express who has collected the certain item.
 * 
 * 
 * Redis Data structure : sorted set sorted_set_key :
 * X.CachePrefix.FAV_ITEMS_4_USERS.$ItemId { score member time uid 137... 13123
 * }
 * 
 * ***/
public class FavItems4UsersCacher {
	static String KP = X.CachePrefix.FAV_ITEMS_4_USERS;
	static FavItems4UsersCacher cacher = new FavItems4UsersCacher();

	public static FavItems4UsersCacher getInstance() {
		return cacher;
	}

	/**
	 * 获取某商品Id对应的用户uid
	 * */
	public ArrayList<String> getUsers(final String itemid) {
		
		RedisRunner<ArrayList<String>> runner = new RedisRunner<ArrayList<String>>(){

			@Override
			public ArrayList<String> run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				ArrayList<String> userList = new ArrayList<String>();
				
				Set<byte[]> set = jedis.zrange(
						SafeEncoder.encode(generateRedisKey(itemid)), 0, -1);
				Iterator<byte[]> ite = set.iterator();
				while (ite.hasNext()) {
					byte[] key = ite.next();
					userList.add(SafeEncoder.encode(key));
				}
				return userList ;
			}} ;
		
		RedisExecutor<ArrayList<String>> re = new RedisExecutor<ArrayList<String>>() ;
		
		return re.exe(runner) ;
	}

	/**
	 * 添加对应的用户uid到商品下
	 * */
	public boolean addUid2Item(final String uid, final String itemid) {

		RedisRunner<Boolean> runner = new RedisRunner<Boolean>(){

			@Override
			public Boolean run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				long i = jedis.zadd(SafeEncoder.encode(generateRedisKey(itemid)), System.currentTimeMillis(),
						SafeEncoder.encode(uid));
				return i>0?true:false ;
			}} ;
		
		RedisExecutor<Boolean> re = new RedisExecutor<Boolean>() ;
		
		return re.exe(runner) ;
	}

	/**
	 * 删除对应商品itemid下的某个Uid
	 */
	public boolean deleteUid4Item(final String uid, final String itemid) {
		
		RedisRunner<Boolean> runner = new RedisRunner<Boolean>(){

			@Override
			public Boolean run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				long i = jedis.zrem(SafeEncoder.encode(generateRedisKey(itemid)),
						SafeEncoder.encode(itemid));
				return i>0?true:false ;
			}} ;
		
		RedisExecutor<Boolean> re = new RedisExecutor<Boolean>() ;
		
		return re.exe(runner) ;
	}
	
	/**
	 * 删除对应商品itemid下的所有商品
	 * **/
	public boolean deleteUid4Item(final String itemid) {
		
		RedisRunner<Boolean> runner = new RedisRunner<Boolean>(){

			@Override
			public Boolean run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				long i = jedis.del(SafeEncoder.encode(generateRedisKey(itemid)));
				return i>0?true:false ;
			}} ;
		
		RedisExecutor<Boolean> re = new RedisExecutor<Boolean>() ;
		
		return re.exe(runner) ;
	}
	
	protected static String generateRedisKey(String itemid){
		return new StringBuilder(KP).append(X.DOT).append(itemid).toString() ;
	}
	
}
