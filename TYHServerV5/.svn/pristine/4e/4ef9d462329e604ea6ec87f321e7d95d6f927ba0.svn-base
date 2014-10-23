package cn.youhui.ramdata;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import cn.youhui.bean.KeyCategoryBean;
import cn.youhui.bean.KeyCategoryBean.KeywordBean;
import cn.youhui.common.RedisExecutor;
import cn.youhui.common.RedisMySqlExecutor;
import cn.youhui.common.RedisMySqlRunner;
import cn.youhui.common.RedisRunner;
import cn.youhui.common.SepaConfig;
import cn.youhui.common.X;

public class AllTagCacher {
	
	private static final Logger log = Logger.getLogger(AllTagCacher.class);
	
	private static String cacheKey = X.CachePrefix.TAG_ALL_TAG;
	private static String cacheLock = X.CachePrefix.CACHE_STATUS + X.DOT + cacheKey;
	private static AllTagCacher instance = null;
	
	private AllTagCacher(){
	}
	
	public static AllTagCacher getInstance(){
		if(instance == null) instance = new AllTagCacher();
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
						jedis.set(cacheLock, X.CachePrefix.LOCK_MARK);
						log.info("alltag init.....");
						Statement s = conn.createStatement();
						String sql = "SELECT * FROM `youhui_datamining`.`m_discount_keywords`  "
								+ "INNER JOIN (SELECT `id` AS pid,keyword AS pcat FROM `youhui_datamining`.`m_discount_keywords` WHERE `parent_id` = 0 and `client_show` = '1') as T2 on T2.pid = parent_id "
								+ "WHERE `client_show` = '1' ORDER BY `parent_id` ASC,`rank` ASC";
						ResultSet rs = s.executeQuery(sql);
						jedis.del(cacheKey);
						while (rs.next()) {
							String tag = jedis.hget(cacheKey, rs.getString("parent_id"));
							if(tag != null && !tag.contains(rs.getString("id")))
							{
								jedis.hset(cacheKey, rs.getString("parent_id"),tag + SepaConfig.TAG_SEPA +rs.getString("id"));
							}
							if(tag == null) 
							{
								jedis.hset(cacheKey, rs.getString("parent_id"),rs.getString("id"));
							}
						}
					} catch (Exception e) {
						log.info("alltag init exception : " + e.getMessage());
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
	
	
	public List<KeyCategoryBean> getTagList() 
	{
		RedisRunner<List<KeyCategoryBean>> r = new RedisRunner<List<KeyCategoryBean>>() {
			@Override
			public List<KeyCategoryBean> run(Jedis jedis) throws JedisConnectionException {
				List<KeyCategoryBean> list = new ArrayList<KeyCategoryBean>();
				Set<String> parentids = jedis.hkeys(cacheKey);
				for(String parentid :parentids)
				{
					KeyCategoryBean cat = new KeyCategoryBean();
					KeywordBean catBean = TagCacher.getInstance().getTag(parentid);
					if (catBean != null) 
					{
						cat.setId(parentid);
						cat.setName(catBean.getKeyword());
						cat.setSex(catBean.getSex());
						list.add(cat);
					}
					String[] tagids = jedis.hget(cacheKey, parentid).split(SepaConfig.TAG_SEPA);
					for(String tagid : tagids)
					{
						KeywordBean keywordBean = TagCacher.getInstance().getTag(tagid);
						if (cat != null && keywordBean != null) 
						{
							cat.addKeyword(keywordBean);
						}
					}
				}
				return list;
			}
		};
		return new RedisExecutor<List<KeyCategoryBean>>().exe(r);
	}
	public static void main(String[] args) {
		AllTagCacher.getInstance().reload();
	}
}
