package cn.youhui.ramdata;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.util.SafeEncoder;
import cn.youhui.bean.ShopBean;
import cn.youhui.common.RedisExecutor;
import cn.youhui.common.RedisRunner;
import cn.youhui.common.X;

import com.google.gson.Gson;


/**
 * all items(from binded users) will be stored here , for detecting promotions
 * 
 *  a daemon process OR a cron job will do the background works 
 * 
 * 	Redis Data structure : sorted set
 * sorted_set_key : X.CachePrefix.FAV_ALL_SHOPS {
 * 		score		member
 * 		time 		ShopBeanJsonString
 * 		137...		[{.....}]
 * }
 * 
 * ***/
public class FavAllShopsCacher {
	static String KP = X.CachePrefix.FAV_ALL_SHOPS ;
	static FavAllShopsCacher cacher = new FavAllShopsCacher();
	Gson g = new Gson();
	public static FavAllShopsCacher getInstance(){
		return cacher;
	}
	
	/**
	 * 获取收藏的所有商品
	 * */
	public ArrayList<ShopBean> getItems() {
		
		RedisRunner<ArrayList<ShopBean>> runner = new RedisRunner<ArrayList<ShopBean>>(){

			@Override
			public ArrayList<ShopBean> run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				ArrayList<ShopBean> itemList = new ArrayList<ShopBean>();
				
				Set<String> set = jedis.zrange(
						KP, 0, -1);
				Iterator<String> ite = set.iterator();
				while (ite.hasNext()) {
					String jsonStr = ite.next();
					ShopBean item = g.fromJson(jsonStr, ShopBean.class);
					itemList.add(item);
				}
				return itemList ;
			}} ;
		
		RedisExecutor<ArrayList<ShopBean>> re = new RedisExecutor<ArrayList<ShopBean>>() ;
		
		return re.exe(runner) ;
	}
	
	/**
	 * 获取收藏的某一商店
	 * */
	public ShopBean getItem(final String itemid) {
		RedisRunner<ShopBean> runner = new RedisRunner<ShopBean>(){

			@Override
			public ShopBean run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				ShopBean item = new ShopBean();
				
				Set<String> set = jedis.zrangeByScore(
						KP, Double.parseDouble(itemid), Double.parseDouble(itemid));
				Iterator<String> ite = set.iterator();
				if (ite.hasNext()) {
					String jsonStr = ite.next();
					item = g.fromJson(jsonStr, ShopBean.class);
				}
				return item ;
			}} ;
		
		RedisExecutor<ShopBean> re = new RedisExecutor<ShopBean>() ;
		
		return re.exe(runner) ;
		
	}
	
	
	/**
	 * 添加商品
	 **/
	
	public boolean addItem(final ShopBean item){
		
		RedisRunner<Boolean> runner = new RedisRunner<Boolean>(){

			@Override
			public Boolean run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				String jsonStr = g.toJson(item);
				
				long i = jedis.zadd(KP,Double.parseDouble(item.getShop_id()) , jsonStr);
				
				return i>0?true:false ;
			}} ;
		
		RedisExecutor<Boolean> re = new RedisExecutor<Boolean>() ;
		
		return re.exe(runner) ;
	}
	
	/**
	 * 删除商品
	 **/
	public boolean delete(final ShopBean item){
		
		RedisRunner<Boolean> runner = new RedisRunner<Boolean>(){

			@Override
			public Boolean run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				double d = Double.parseDouble(item.getShop_id());
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
