package cn.youhui.ramdata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import cn.youhui.bean.UMPItemBean;
import cn.youhui.common.RedisExecutor;
import cn.youhui.common.RedisRunner;
import cn.youhui.common.X;

/**
 *  所有UMP商品
 * 
 * **/
public class FavUMPCacher {
	/* (non-Javadoc)
	 * @see java.lang.Object#finalize()
	 */
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		KP = null ;
		ins = null ;
		super.finalize();
	}

	static String KP = X.CachePrefix.FAV_UMP_ITEMS ;
	static String RKEY_itemId2umpid = KP + X.DOT + "itemId2umpid" ;
	static String RKEY_umpid2itemId = KP + X.DOT + "umpid2itemId" ; 
	
	static FavUMPCacher ins = new FavUMPCacher() ;
	
	protected FavUMPCacher(){}
	
	public static FavUMPCacher getInstance(){
		return ins == null ? (ins = new FavUMPCacher()) : ins ;
	}
	
	public UMPItemBean getUMPItem(final String umpId){
		RedisRunner<UMPItemBean> runner = new RedisRunner<UMPItemBean>(){

			@Override
			public UMPItemBean run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				String key = KP+X.DOT+umpId ;
				Map<String,String> map = jedis.hgetAll(key) ;
				return UMPItemBean.fromMap(map) ;
			}} ;
		
		RedisExecutor<UMPItemBean> re = new RedisExecutor<UMPItemBean>() ;
		
		return re.exe(runner) ;
	}
	
	public boolean addUMPItem(final UMPItemBean umpItemBean){
		RedisRunner<Boolean> runner = new RedisRunner<Boolean>(){

			@Override
			public Boolean run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				String key = KP+X.DOT+umpItemBean.getPromotionId() ;
				jedis.hmset(key, umpItemBean.trans2Map()) ;
				jedis.hset(RKEY_itemId2umpid, Long.toString(umpItemBean.getItemId()), umpItemBean.getPromotionId()) ;
				jedis.hset(RKEY_umpid2itemId, umpItemBean.getPromotionId(), Long.toString(umpItemBean.getItemId())) ;
				jedis.expireAt(key, umpItemBean.getEndTime().getTime()) ;
				return true ;
			}} ;
		
		RedisExecutor<Boolean> re = new RedisExecutor<Boolean>() ;
		
		return re.exe(runner) ;
	}
	
	/***
	 *  @param itemIds 商品ID
	 *  @return  UMPItemBean
	 * 
	 * */
	public UMPItemBean getUMPInfo(final long itemId){
		RedisRunner<UMPItemBean> runner = new RedisRunner<UMPItemBean>(){

			@Override
			public UMPItemBean run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				String umpid = jedis.hget(RKEY_itemId2umpid, Long.toString(itemId)) ;
				UMPItemBean b = null ;
				if(umpid != null){
					String key = KP+X.DOT+umpid ;
					Map<String,String> map = jedis.hgetAll(key) ;
					b = UMPItemBean.fromMap(map) ;
				}
				return b ;
			}} ;
		
		return new RedisExecutor<UMPItemBean>().exe(runner) ;
	}
	
	/***
	 *  @param itemIds 系列商品ID
	 *  @return Long.toString(itemId) --->UMPItemBean
	 * 
	 * */
	public Map<String,UMPItemBean> getUMPInfo(final List<Long> itemIds){
		RedisRunner<Map<String,UMPItemBean>> runner = new RedisRunner<Map<String,UMPItemBean>>(){

			@Override
			public Map<String,UMPItemBean> run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				Map<String,UMPItemBean> rstmap = new HashMap<String,UMPItemBean>();
				for(long itemId : itemIds){
					String umpid = jedis.hget(RKEY_itemId2umpid, Long.toString(itemId)) ;
					UMPItemBean b = null ;
					if(umpid != null){
						String key = KP+X.DOT+umpid ;
						Map<String,String> map = jedis.hgetAll(key) ;
						if(map != null){
							b = UMPItemBean.fromMap(map) ;
							rstmap.put(Long.toString(itemId), b) ;
						}
					}
				}
				return rstmap  ;
			}} ;
		
		return new RedisExecutor<Map<String,UMPItemBean>>().exe(runner) ;
	}
	
	public long getItemId(final String umpId){
		RedisRunner<String> runner = new RedisRunner<String>(){

			@Override
			public String run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				return jedis.hget(RKEY_umpid2itemId, umpId) ;
			}} ;
		
		String itemIdStr = new RedisExecutor<String>().exe(runner) ;
		
		return Long.parseLong(itemIdStr) ;
	}
	
	public long getUmpId(final String itemId){
		RedisRunner<String> runner = new RedisRunner<String>(){

			@Override
			public String run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				return jedis.hget(RKEY_itemId2umpid, itemId) ;
			}} ;
		
		String itemIdStr = new RedisExecutor<String>().exe(runner) ;
		
		return Long.parseLong(itemIdStr) ;
	}
	
	protected void deleteRel(final long itemId){
		RedisRunner<Boolean> runner = new RedisRunner<Boolean>(){

			@Override
			public Boolean run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				String umpid = jedis.hget(RKEY_itemId2umpid, Long.toString(itemId)) ;
				jedis.hdel(RKEY_itemId2umpid, Long.toString(itemId)) ;
				if(umpid != null){
					jedis.hdel(RKEY_umpid2itemId, umpid) ;
				}				
				return true ;
			}} ;
		
		new RedisExecutor<Boolean>().exe(runner) ;
	}
	
	protected void deleteRel(final String umpId){
		RedisRunner<Boolean> runner = new RedisRunner<Boolean>(){

			@Override
			public Boolean run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				String itemId = jedis.hget(RKEY_umpid2itemId, umpId) ;
				jedis.hdel(RKEY_umpid2itemId, umpId) ;
				if(itemId != null){
					jedis.hdel(RKEY_itemId2umpid, itemId) ;
				}				
				return true ;
			}} ;
		
		new RedisExecutor<Boolean>().exe(runner) ;
	}
}
