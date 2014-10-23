package cn.youhui.manager;

import java.util.Set;

import redis.clients.jedis.Jedis;
import cn.youhui.common.RedisExecutor;
import cn.youhui.common.RedisRunner;

/**
 * 对redis 的set数据进行操作
 * @author lijun
 * @since 2014-4-2
 */
public class JedisSetManager {

private String key = new String();
	
	public JedisSetManager(String key){
		this.key = key;
	}
	
	public long add(final String member) {		
		RedisRunner<Long> runner = new RedisRunner<Long>(){
			public Long run(Jedis jedis) {
				long i = jedis.sadd(key, member);
				return i;
			}} ;
		RedisExecutor<Long> re = new RedisExecutor<Long>() ;		
		return re.exe(runner) ;
	}
	
	public long rem(final String member) {		
		RedisRunner<Long> runner = new RedisRunner<Long>(){
			public Long run(Jedis jedis) {
				long i = jedis.srem(key, member);
				return i;
			}} ;
		RedisExecutor<Long> re = new RedisExecutor<Long>() ;		
		return re.exe(runner) ;
	}
	
	public Set<String> getAll() {		
		RedisRunner<Set<String>> runner = new RedisRunner<Set<String>>(){
			public Set<String> run(Jedis jedis) {
				Set<String> i = jedis.smembers(key);
				return i;
			}} ;
		RedisExecutor<Set<String>> re = new RedisExecutor<Set<String>>() ;		
		return re.exe(runner) ;
	}
}
