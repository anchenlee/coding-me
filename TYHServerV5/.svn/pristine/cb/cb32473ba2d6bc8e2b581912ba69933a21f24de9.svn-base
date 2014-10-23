package cn.youhui.ramdata;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.util.SafeEncoder;
import cn.youhui.bean.ItemBean;
import cn.youhui.bean.UMPItemBean;
import cn.youhui.common.RedisExecutor;
import cn.youhui.common.RedisRunner;
import cn.youhui.common.X;
import cn.youhui.dao.JedisDBIns;

import com.google.gson.Gson;


/**
 *  存储了所有的商品缓存，来自于所有用户收藏的商品
 * 
 * 
 * 
 * all items(from binded users) will be stored here , for detecting promotions
 * 
 *  a daemon process OR a cron job will do the background works 
 * 
 * 	Redis Data structure : sorted set
 * sorted_set_key : KP {
 * 		score		member
 * 		time 		ItemBeanJsonString
 * 		137...		[{.....}]
 * }
 * 
 * ***/
public class FavAllItemsCacher {
	static String KP = X.CachePrefix.FAV_ALL_ITEMS ;
	static FavAllItemsCacher cacher = new FavAllItemsCacher();
	Gson g = new Gson();
	public static FavAllItemsCacher getInstance(){
		return cacher;
	}
	
	/**
	 * 获取收藏的所有商品
	 * */
	public ArrayList<ItemBean> getItems() {
		
		RedisRunner<ArrayList<ItemBean>> runner = new RedisRunner<ArrayList<ItemBean>>(){

			@Override
			public ArrayList<ItemBean> run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				ArrayList<ItemBean> itemList = new ArrayList<ItemBean>();
				
				Set<String> set = jedis.zrange(
						KP, 0, -1);
				Iterator<String> ite = set.iterator();
				while (ite.hasNext()) {
					String jsonStr = ite.next();
					ItemBean item = g.fromJson(jsonStr, ItemBean.class);
					itemList.add(item);
				}
				return itemList ;
			}} ;
		
		RedisExecutor<ArrayList<ItemBean>> re = new RedisExecutor<ArrayList<ItemBean>>() ;
		
		return re.exe(runner) ;
	}
	
	/**
	 * 获取收藏的某一商品
	 * */
	public ItemBean getItem(final String itemid) {
		RedisRunner<ItemBean> runner = new RedisRunner<ItemBean>(){

			@Override
			public ItemBean run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				ItemBean item = new ItemBean();
				
				Set<String> set = jedis.zrangeByScore(
						KP, Double.parseDouble(itemid), Double.parseDouble(itemid));
				Iterator<String> ite = set.iterator();
				if (ite.hasNext()) {
					String jsonStr = ite.next();
					item = g.fromJson(jsonStr, ItemBean.class);
				}
				return item ;
			}} ;
		
		RedisExecutor<ItemBean> re = new RedisExecutor<ItemBean>() ;
		
		return re.exe(runner) ;
		
	}
	
	
	/**
	 * 添加商品
	 **/
	
	public boolean addItem(final ItemBean item){
		
		RedisRunner<Boolean> runner = new RedisRunner<Boolean>(){

			@Override
			public Boolean run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				String jsonStr = g.toJson(item);
				long i = jedis.zadd(SafeEncoder.encode(KP),Double.parseDouble(item.getNum_iid()) , SafeEncoder.encode(jsonStr));
				
				return i>0?true:false ;
			}} ;
		
		RedisExecutor<Boolean> re = new RedisExecutor<Boolean>() ;
		
		return re.exe(runner) ;
	}
	
	/**
	 * 删除商品
	 **/
	public boolean delete(final ItemBean item){
		
		RedisRunner<Boolean> runner = new RedisRunner<Boolean>(){

			@Override
			public Boolean run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				double d = Double.parseDouble(item.getNum_iid());
				long i = jedis.zremrangeByScore(KP, d,d );
				return i>0?true:false ;
			}} ;
		
		RedisExecutor<Boolean> re = new RedisExecutor<Boolean>() ;
		
		return re.exe(runner) ;
	}
	
	/**
	 * 判断num_iid是否存在
	 * */
	public boolean isExist(final String num_iid){
		
		RedisRunner<Boolean> runner = new RedisRunner<Boolean>(){

			@Override
			public Boolean run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				double d = Double.parseDouble(num_iid);
				Set<String> set = jedis.zrangeByScore(KP, d,d );
				if(set!=null&&set.size()>0){
					return true;
				}else{
					return false;
				}
			}} ;
		
		RedisExecutor<Boolean> re = new RedisExecutor<Boolean>() ;
		
		return re.exe(runner) ;
	}
	
	
}
