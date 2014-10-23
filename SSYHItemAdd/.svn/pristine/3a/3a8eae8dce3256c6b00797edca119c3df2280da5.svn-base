package cn.youhui.cache.dao;

import cn.youhui.platform.config.param;
import cn.youhui.platform.jedis.JedisHashManager;

public class SuperDiscountCleanRedis {

	public static void cleanAll(){
		new JedisHashManager(param.superDiscount_TAG).clean();
		new JedisHashManager(param.relationcache_TAG).clean();
		new JedisHashManager(param.secondKill_TAG).clean();
	}
	
}
