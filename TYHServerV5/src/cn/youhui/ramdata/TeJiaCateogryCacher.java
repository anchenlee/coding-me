package cn.youhui.ramdata;

import cn.youhui.dao.JedisDBIns;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class TeJiaCateogryCacher {
	protected static final String CATEGORY_PREFIX = "youhui.cn.tjcateory";

	static TeJiaCateogryCacher instance = new TeJiaCateogryCacher();
	
	public static final int pageSize = 10 ;

	public static TeJiaCateogryCacher getInstance() {
		return instance;
	}
	
	
	
	public String getCategoryXml(String channel){
		Jedis jedis = null ;
		String context = "";
		try{
			jedis = JedisDBIns.getInstance().getJedis() ;
			String key = "default";
			if(channel!=null&&!channel.equals("")){
				key = channel;
			}
			
			context = jedis.hget(CATEGORY_PREFIX, key);
			if(context==null){
				context ="";
			}
			
			JedisDBIns.getInstance().release(jedis) ;
		} catch (JedisConnectionException e) {
			JedisDBIns.getInstance().releaseBrokenJedis(jedis) ;
		} catch (Exception e){
			JedisDBIns.getInstance().release(jedis) ;
		}
		
		return context;
	}
	
	public boolean pushCategoryXml(String channel,String xml){
		
		Jedis jedis = null ;
		boolean flag = true;
		try{
			
			jedis = JedisDBIns.getInstance().getJedis() ;
			String key = "default";
			if(channel!=null&&!channel.equals("")){
				key = channel;
			}
			jedis.hset(CATEGORY_PREFIX, key, xml);
			
			
			JedisDBIns.getInstance().release(jedis) ;
		} catch (JedisConnectionException e) {
			JedisDBIns.getInstance().releaseBrokenJedis(jedis) ;
			flag = false;
		} catch (Exception e){
			JedisDBIns.getInstance().release(jedis) ;
			flag = false;
		}
		
		return flag;
		
	}
	
	public boolean clearCategoryXml(){
		Jedis jedis = null ;
		boolean flag = false;
		try{
			jedis = JedisDBIns.getInstance().getJedis() ;
			
			long i = jedis.del(CATEGORY_PREFIX);
			if(i>0){
				flag = true;
			}

			JedisDBIns.getInstance().release(jedis) ;
		} catch (JedisConnectionException e) {
			JedisDBIns.getInstance().releaseBrokenJedis(jedis) ;
		} catch (Exception e){
			JedisDBIns.getInstance().release(jedis) ;
		}
		
		return flag;
	}
	
}
