package cn.suishou.some.manager;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import cn.suishou.some.config.RedisExecutor;
import cn.suishou.some.config.RedisRunner;



import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

/**
 * @category 对redis的sortedset数据操作
 * @author leejun
 * @since 2013-1-21
 *
 */
public class JedisSortedSetManager{	
	
	private String key = new String();
	
	public JedisSortedSetManager(String key){
		this.key = key;
	}
	
	public long add(final double score, final String member) {		
		RedisRunner<Long> runner = new RedisRunner<Long>(){
			public Long run(Jedis jedis) {
				long i = jedis.zadd(key, score, member);
				return i;
			}} ;
		RedisExecutor<Long> re = new RedisExecutor<Long>() ;		
		return re.exe(runner) ;
	}
	
	public long size() {		
		RedisRunner<Long> runner = new RedisRunner<Long>(){
			public Long run(Jedis jedis) {
				long i = jedis.zcount(key, -Double.MAX_VALUE, Double.MAX_VALUE);
				return i;
			}} ;
		RedisExecutor<Long> re = new RedisExecutor<Long>() ;		
		return re.exe(runner);
	}
	
	public long size1() {		
		RedisRunner<Long> runner = new RedisRunner<Long>(){
			public Long run(Jedis jedis) {
				long i = jedis.zcount(key, Double.parseDouble("-1000000"), Double.MAX_VALUE);
				return i;
			}} ;
		RedisExecutor<Long> re = new RedisExecutor<Long>() ;		
		return re.exe(runner) ;
	}
	
	public long delete(final String member) {		
		RedisRunner<Long> runner = new RedisRunner<Long>(){
			public Long run(Jedis jedis) {
				long i = jedis.zrem(key, member);
				return i;
			}} ;
		RedisExecutor<Long> re = new RedisExecutor<Long>() ;		
		return re.exe(runner) ;
	}
	
	/**
	 * 删除 从strat个 到end个 
	 * @param start
	 * @param end
	 * @return
	 */
	public long deleteByRank(final int start, final int end) {		
		RedisRunner<Long> runner = new RedisRunner<Long>(){
			public Long run(Jedis jedis) {
				long i = jedis.zremrangeByRank(key, start, end);
				return i;
			}} ;
		RedisExecutor<Long> re = new RedisExecutor<Long>() ;		
		return re.exe(runner) ;
	}
	
	public boolean isExist(final String member) {		
		RedisRunner<Boolean> runner = new RedisRunner<Boolean>(){
			public Boolean run(Jedis jedis) {
			    Double d = jedis.zscore(key, member);
			    if(d != null) return true;
				return false;
			}} ;
		RedisExecutor<Boolean> re = new RedisExecutor<Boolean>() ;		
		return re.exe(runner) ;
	}
	
	/**
	 * 根据sorce从大到小
	 */
	public Set<String> getRange(final int start, final int end) {		
		RedisRunner<Set<String>> runner = new RedisRunner<Set<String>>(){
			public Set<String> run(Jedis jedis) {
				Set<String> i = jedis.zrevrange(key, start, end);
				return i;
			}} ;
		RedisExecutor<Set<String>> re = new RedisExecutor<Set<String>>() ;		
		return re.exe(runner) ;
	}
	
	/**
	 * 根据sorce从小到大
	 */
	public Set<String> zrange(final int start, final int end) {		
		RedisRunner<Set<String>> runner = new RedisRunner<Set<String>>(){
			public Set<String> run(Jedis jedis) {
				Set<String> i = jedis.zrange(key, start, end);
				return i;
			}} ;
		RedisExecutor<Set<String>> re = new RedisExecutor<Set<String>>() ;		
		return re.exe(runner) ;
	}
	
	/**
	 * 根据sorce从小到大
	 */
	public Set<String> getAll() {		
		RedisRunner<Set<String>> runner = new RedisRunner<Set<String>>(){
			public Set<String> run(Jedis jedis) {
				Set<String> i = jedis.zrange(key, Integer.MIN_VALUE, Integer.MAX_VALUE);
				return i;
			}} ;
		RedisExecutor<Set<String>> re = new RedisExecutor<Set<String>>() ;		
		return re.exe(runner) ;
	}
	
	public Map<String, Double> getRangeWithScores(final int start, final int end) {		
		RedisRunner<Map<String, Double>> runner = new RedisRunner<Map<String, Double>>(){
			public Map<String, Double> run(Jedis jedis) {
				Set<Tuple> set = jedis.zrevrangeWithScores(key, start, end);
				Map<String, Double> map = new LinkedHashMap<String, Double>();
				if(set != null && set.size() > 0)
				for(Tuple t : set){
					map.put(t.getElement(), t.getScore());
				}
				return map;
			}} ;
		RedisExecutor<Map<String, Double>> re = new RedisExecutor<Map<String, Double>>();		
		return re.exe(runner) ;
	}
	
	/**
	 * 从大到小
	 * @param start
	 * @param end
	 * @return
	 */
	public Map<String, Double> getRangeByScores(final double start, final double end){		
		RedisRunner<Map<String, Double>> runner = new RedisRunner<Map<String, Double>>(){
			public Map<String, Double> run(Jedis jedis) {
				Set<Tuple> set = jedis.zrevrangeByScoreWithScores(key, start, end);
				Map<String, Double> map = new LinkedHashMap<String, Double>();
				if(set != null && set.size() > 0)
				for(Tuple t : set){
					map.put(t.getElement(), t.getScore());
				}
				return map;
			}} ;
		RedisExecutor<Map<String, Double>> re = new RedisExecutor<Map<String, Double>>();		
		return re.exe(runner) ;
	}

	public long deleteKey() {		
		RedisRunner<Long> runner = new RedisRunner<Long>(){
			public Long run(Jedis jedis) {
				long i = jedis.del(key);
				return i;
			}} ;
		RedisExecutor<Long> re = new RedisExecutor<Long>() ;		
		return re.exe(runner) ;
	}
	
}