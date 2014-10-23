package cn.suishou.some.manager;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.suishou.some.config.RedisExecutor;
import cn.suishou.some.config.RedisRunner;




import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * @category 对redis的hash数据进行操作
 * @author leejun
 * @since 2012-10-12
 *
 */
public class JedisHashManager {	
	
 private String key = new String();
	
	public JedisHashManager(String key){
		this.key = key;
	} 
	/**
	 * @return 添加成功返回true,更新返回false
	 */
	public long add(final String field, final String value) {
		
		RedisRunner<Long> runner = new RedisRunner<Long>(){
			public Long run(Jedis jedis) {
				long i = jedis.hset(key, field, value);	
				return i;
			}} ;
			
			RedisExecutor<Long> re = new RedisExecutor<Long>() ;		
			return re.exe(runner) ;
	}
	
	public String get(final String field) {
		
		RedisRunner<String> runner = new RedisRunner<String>(){
			public String run(Jedis jedis) throws JedisConnectionException {
				if(null == field) return null;
				 String value = jedis.hget(key, field); 
				 return value;
			}} ;
			
		RedisExecutor<String> re = new RedisExecutor<String>();		
		return re.exe(runner) ;
	}
	
	public Map<String, String> getAll(){
		
		RedisRunner<Map<String, String>> runner = new RedisRunner<Map<String, String>>(){
			public Map<String, String> run(Jedis jedis) throws JedisConnectionException {
				Map<String, String> ret = jedis.hgetAll(key);
				return ret;
			}} ;
			
			RedisExecutor<Map<String, String>> re = new RedisExecutor<Map<String, String>>();		
			return re.exe(runner) ;
	}
	
	public ArrayList<String> getListByFields(final ArrayList<String> fields)
	{
		RedisRunner<ArrayList<String>> runner = new RedisRunner<ArrayList<String>>()
		{
			public ArrayList<String> run(Jedis jedis) throws JedisConnectionException 
			{
				ArrayList<String> values = new ArrayList<String>();
				for (String field : fields)
				{
					if (null != field && !"".equals(field))
					{
						String value = jedis.hget(key, field); 
						values.add(value);
					}
				}
				return values;
		}} ;
			
		RedisExecutor<ArrayList<String>> re = new RedisExecutor<ArrayList<String>>();		
		return re.exe(runner) ;
	}
	
	public String setAll(final Map<String, String> hash) {
			
			RedisRunner<String> runner = new RedisRunner<String>(){
				public String run(Jedis jedis) throws JedisConnectionException {
					 String value = jedis.hmset(key, hash);
					 return value;
				}} ;
				
			RedisExecutor<String> re = new RedisExecutor<String>();		
			return re.exe(runner) ;
		}

	public List<String> getAllField(){
		
		RedisRunner<List<String>> runner = new RedisRunner<List<String>>(){
			public List<String> run(Jedis jedis) throws JedisConnectionException {
				List<String> ret = new ArrayList<String>();
				ret.addAll(jedis.hkeys(key)); 
				return ret;
			}} ;
			
			RedisExecutor<List<String>> re = new RedisExecutor<List<String>>();		
			return re.exe(runner) ;
	}
	
	public boolean isExist(final String field) {
		
		RedisRunner<Boolean> runner = new RedisRunner<Boolean>(){
			public Boolean run(Jedis jedis) throws JedisConnectionException {
				return  jedis.hexists(key, field);			
			}} ;
		
		RedisExecutor<Boolean> re = new RedisExecutor<Boolean>();		
		return re.exe(runner) ;
	}
	
	/**
	 * @return 删除成功返回true
	 */
	public long delete(final String field){
		
		RedisRunner<Long> runner = new RedisRunner<Long>(){
			public Long run(Jedis jedis) throws JedisConnectionException {
				long i = jedis.hdel(key, field);
				return i;
			}} ;
		
		RedisExecutor<Long> re = new RedisExecutor<Long>();		
		return re.exe(runner) ;
	}
	
	public long size(){
		
		RedisRunner<Long> runner = new RedisRunner<Long>(){
			public Long run(Jedis jedis) throws JedisConnectionException {
				long i = jedis.hlen(key);
				return i;
			}} ;
		
		RedisExecutor<Long> re = new RedisExecutor<Long>();		
		return re.exe(runner) ;
	}
	
	/**
	 * 清空
	 */
	public long clean() {
		
		RedisRunner<Long> runner = new RedisRunner<Long>(){
			public Long run(Jedis jedis) throws JedisConnectionException {
				Long value = jedis.del(key);
				return value;
			}} ;
			
			RedisExecutor<Long> re = new RedisExecutor<Long>();		
			return re.exe(runner) ;
	}
	

}