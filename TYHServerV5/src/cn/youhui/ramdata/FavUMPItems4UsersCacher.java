package cn.youhui.ramdata;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.util.SafeEncoder;
import cn.youhui.bean.UMPItemBean;
import cn.youhui.common.RedisExecutor;
import cn.youhui.common.RedisRunner;
import cn.youhui.common.X;
import cn.youhui.dao.JedisDBIns;


/**
 *  一个人的所有UMP商品列表
 *  
 *  uid =>{
 *  	umpid ,
 *  	umpid,
 *  	.....
 *  }
 * 
 * the representation of UMP products , API will invoke this directly to detected promotion for user
 * 
 * 	Redis Data structure : sorted set
 * sorted_set_key : X.CachePrefix.FAV_UMP_ITEMS.$uid {
 * 		score		member
 * 		time 		itemId
 * 		137...		[{.....}]
 * }
 * 
 * ***/
public class FavUMPItems4UsersCacher {
	static String KP = X.CachePrefix.FAV_UMP_ITEMS_4_USERS ;
	static FavUMPItems4UsersCacher cache = new FavUMPItems4UsersCacher();
	
	public static FavUMPItems4UsersCacher getInstance(){
		return cache;
	}
	
	/**
	 * 获取某个用户的所有商品id
	 * */
	public ArrayList<String> getUMPItemIDs(long uid){
		Jedis j = null;
		ArrayList<String> list = new ArrayList<String>();
		try{
			j = JedisDBIns.getInstance().getJedis();
			Set<byte[]> set = j.zrange(SafeEncoder.encode(X.CachePrefix.FAV_UMP_ITEMS_4_USERS+"."+uid), 0, -1);
			Iterator<byte[]> ite = set.iterator();
			while(ite.hasNext()){
				byte[] key = ite.next();
				list.add(new String(key));
			}
			
			JedisDBIns.getInstance().release(j);
		}catch(JedisConnectionException e){
			JedisDBIns.getInstance().releaseBrokenJedis(j);
			e.printStackTrace();
			
		}catch(Exception e){
			JedisDBIns.getInstance().release(j);
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 获取某个用户的的某个商品促销的umpId
	 * */
	public String getUMPItemID(long uid,long itemId){
		Jedis j = null;
		String umpId = "";
		try{
			j = JedisDBIns.getInstance().getJedis();
			Set<Tuple> set = j.zrangeWithScores(SafeEncoder.encode(X.CachePrefix.FAV_UMP_ITEMS_4_USERS+"."+uid),
					(int)itemId, (int)itemId);
			Iterator<Tuple> ite = set.iterator();
			if(ite.hasNext()){
				Tuple tuple = ite.next();
				umpId = tuple.getScore()+"";
			}
			
			JedisDBIns.getInstance().release(j);
		}catch(JedisConnectionException e){
			JedisDBIns.getInstance().releaseBrokenJedis(j);
			e.printStackTrace();
			
		}catch(Exception e){
			JedisDBIns.getInstance().release(j);
			e.printStackTrace();
		}
		return umpId;
	}
	
	public void addUMPItemId4AllUser(final long itemId , final String umpId){
		FavItems4UsersCacher fav4userCacher = FavItems4UsersCacher.getInstance() ;
		final ArrayList<String> uidList = fav4userCacher.getUsers(Long.toString(itemId)) ;
		RedisRunner<Long[]> rr = new RedisRunner<Long[]>(){

			@Override
			public Long[] run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				for(String uid : uidList){
					jedis.zadd(generateRedisKey(uid),itemId, umpId) ;
				}
				return null;
			}
			
		} ;
		
		RedisExecutor<Long[]> re = new RedisExecutor<Long[]>() ;
		re.exe(rr) ;
	}
	
	/**
	 * 添加
	 */
	public void addUMPItemID(long uid,String umpItemId,long itemid){
		Jedis j = null;
		try{
			j = JedisDBIns.getInstance().getJedis();
			j.zadd(X.CachePrefix.FAV_UMP_ITEMS_4_USERS+"."+uid, itemid, umpItemId);
			
			JedisDBIns.getInstance().release(j);
		}catch(JedisConnectionException e){
			JedisDBIns.getInstance().releaseBrokenJedis(j);
			e.printStackTrace();
			
		}catch(Exception e){
			JedisDBIns.getInstance().release(j);
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 删除某个用户下的商品
	 * */
	public boolean deleteUMPItemID(long uid,String umpItemId){
		Jedis j = null;
		boolean flag = false;
		try{
			j = JedisDBIns.getInstance().getJedis();
			
			long i = j.zrem(SafeEncoder.encode(X.CachePrefix.FAV_UMP_ITEMS_4_USERS+"."+uid),
					SafeEncoder.encode(umpItemId));
			flag = i>0?true:false;
			JedisDBIns.getInstance().release(j);
		}catch(JedisConnectionException e){
			JedisDBIns.getInstance().releaseBrokenJedis(j);
			e.printStackTrace();
			
		}catch(Exception e){
			JedisDBIns.getInstance().release(j);
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 删除某个用户下的所有商品
	 * */
	public boolean deleteAllUMPItemID(long uid){
		Jedis j = null;
		boolean flag = false;
		try{
			j = JedisDBIns.getInstance().getJedis();
			
			long i = j.del(SafeEncoder.encode(X.CachePrefix.FAV_UMP_ITEMS_4_USERS+"."+uid));
			flag = i>0?true:false;
			JedisDBIns.getInstance().release(j);
		}catch(JedisConnectionException e){
			JedisDBIns.getInstance().releaseBrokenJedis(j);
			e.printStackTrace();
			
		}catch(Exception e){
			JedisDBIns.getInstance().release(j);
			e.printStackTrace();
		}
		return flag;
	}
	 
	protected static String generateRedisKey(String uid){
		return new StringBuilder(KP).append(X.DOT).append(uid).toString() ;
	}
}
