package cn.youhui.dao;

import org.apache.log4j.Logger;

import cn.youhui.common.Config;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 本类实现了使用Jedis客户端API对Redis数据库的底层连接池的封装
 * 
 * @author Eric Sun
 * @version 1.2.2
 * @since 2011-03-10
 * @update 2011-09-04
 * */
public class JedisDBIns {

	private static JedisDBIns ins = new JedisDBIns();
	private static Logger log = Logger.getLogger(JedisDBIns.class) ;

	protected static JedisPool pool = null;
	protected static JedisPoolConfig config = null;

	protected JedisDBIns() {
		init();
	}

	public static JedisDBIns getInstance() {
		return ins == null ? (ins = new JedisDBIns()) : ins;
	}

	public Jedis getJedis() {

		try {
			if (pool == null) {
				init();
			}
			Jedis j = pool.getResource();
			return j;
		} catch (Exception e) {
//			e.printStackTrace();
			log.error(e, e) ;
			return null;
		}
	}

	/**
	 * 本方法会判断传入的Jedis对象是否为空，或者是否连接。外界仅需调用
	 * 
	 * @param jedis
	 *            需要释放的Jedis对象
	 * */
	public void release(Jedis jedis) {
		if (pool != null && jedis != null) {
			pool.returnResource(jedis);
		}
		return;
	}

	public void releaseBrokenJedis(Jedis jedis) {
		if (pool!= null && jedis != null) {
			pool.returnBrokenResource(jedis);
		}
	}

	public void destory(){
		if(pool != null){
			pool.destroy();
		}
		pool = null ;
		return;
	}

	private void init() {
		System.out.println("redisconnection start");
		
		config = new JedisPoolConfig();
		config.setMaxActive(512);
		config.setMaxIdle(512);
		config.setMaxWait(1000);
		config.setTestOnBorrow(true);
		config.setTestWhileIdle(true);
		config.setTestOnReturn(true);
		config.minEvictableIdleTimeMillis = 60000;
		config.timeBetweenEvictionRunsMillis = 30000;
		config.numTestsPerEvictionRun = -1;

		pool = new JedisPool(config, Config.GLB_REDIS_HOST, Config.GLB_REDIS_PORT, 5000);
		System.out.println("redisconnection end");
		
		//pool = new JedisPool(config, "10.10.30.7", 6379, 5000);
		//	pool = new JedisPool(config, "192.168.90.27", 6379, 5000);
		//pool = new JedisPool(config, "192.168.1.15", 6379, 5000);
	}

	/*public static void main(String[] args) {
		Jedis j = JedisDBIns.getInstance().getJedis();

		j.set("692656526563", "test");

	}*/
}
