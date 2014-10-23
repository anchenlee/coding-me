package cn.youhui.ramdata;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import cn.youhui.bean.Activity;
import cn.youhui.common.RedisMySqlExecutor;
import cn.youhui.common.RedisMySqlRunner;
import cn.youhui.common.X;
import cn.youhui.dao.JedisDBIns;
import cn.youhui.manager.JedisHashManager;

public class ActivityCacher {

	private static final Logger log = Logger.getLogger(ActivityCacher.class);
	
	private static String cacheKey = X.CachePrefix.YOUHUI_ACTIVITY;
	private static String cacheLock = X.CachePrefix.CACHE_STATUS + X.DOT + cacheKey;
	
	private static ActivityCacher instance = null;
	
	private ActivityCacher(){
	}
	
	public static ActivityCacher getInstance(){
		if(instance == null) instance = new ActivityCacher();
		return instance;
	}
	
	public void init() {
		RedisMySqlRunner<Boolean> r = new RedisMySqlRunner<Boolean>() {
			@Override
			public Boolean run(Connection conn, Jedis jedis)
					throws JedisConnectionException, SQLException {
				String cacheStatus = jedis.get(cacheLock);
				if(!X.CachePrefix.LOCK_MARK.equals(cacheStatus)){
					try {
						log.info("Activity init.....");
						Statement s = conn.createStatement();
						String sql = "SELECT * FROM youhui_cn_fanli.tyh_activity;";
						ResultSet rs = s.executeQuery(sql);
						jedis.del(cacheKey);
						while (rs.next()) {
							Activity activity = new Activity();
							activity.setId(rs.getString("id"));
							activity.setDescription(rs.getString("description"));
							activity.setName(rs.getString("name"));
							activity.setFrequency(rs.getInt("frequency"));
							activity.setJfbNum(rs.getInt("jfb_num"));
							activity.setKey(rs.getString("key"));
							activity.setRule(rs.getString("rule"));
							activity.setStartTime(rs.getLong("start_time"));
							activity.setEndTime(rs.getLong("end_time"));
							activity.setTimestamp(rs.getLong("timestamp"));
							activity.setImg(rs.getString("img"));
							new JedisHashManager(cacheKey).add(activity.getId(), new Gson().toJson(activity));
						}
					} catch (Exception e) {
						log.info("Activity init exception : " + e.getMessage());
					}finally{
						jedis.del(cacheLock);
					}
				}
				return true;
			}
		};
		RedisMySqlExecutor<Boolean> e = new RedisMySqlExecutor<Boolean>();
		e.exe(r);
	}
	
	public void reload() {
		init();
	}
	
	public Activity getActivity(String id){
		
		Activity bean = null;
		Gson gson = new Gson();
		String json = new JedisHashManager(cacheKey).get(id);
		if (json != null) {
			bean = gson.fromJson(json, Activity.class);
		}			
		return bean;
	}
	
}
