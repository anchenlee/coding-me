package cn.youhui.ramdata;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import cn.youhui.bean.Action;
import cn.youhui.bean.KeyCategoryBean;
import cn.youhui.bean.KeyCategoryBean.KeywordBean;
import cn.youhui.common.RedisExecutor;
import cn.youhui.common.RedisMySqlExecutor;
import cn.youhui.common.RedisMySqlRunner;
import cn.youhui.common.RedisRunner;
import cn.youhui.common.X;

import com.google.gson.Gson;

public class TagInIpadCacher{
	private static String cacheKey =  X.CachePrefix.TAG_ALLINIPAD_TAGS;
	private static String cacheLock = X.CachePrefix.CACHE_STATUS + X.DOT + cacheKey;
	private static final Logger log = Logger.getLogger(TagInIpadCacher.class);
	private static TagInIpadCacher instance;
	
	private TagInIpadCacher() {
	}

	public static TagInIpadCacher getInstance() {
		return instance == null ? (instance = new TagInIpadCacher()) : instance;
	}

	public KeywordBean getTag(final String id) {
		RedisRunner<KeywordBean> r = new RedisRunner<KeywordBean>() {

			@Override
			public KeywordBean run(Jedis jedis) throws JedisConnectionException {
				String source = jedis.hget(cacheKey, id);
				KeywordBean keyword = null;
				if (source != null) {
					keyword = new Gson().fromJson(source, KeywordBean.class);
				}
				return keyword;
			}
		};
		RedisExecutor<KeywordBean> e = new RedisExecutor<KeywordBean>();
		return e.exe(r);
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
						log.info("taginipadcacher init............");
						Statement s = conn.createStatement();
						String sql = "SELECT * FROM `youhui_datamining`.`m_discount_keywords` WHERE `istag` = '1';";
						ResultSet rs = s.executeQuery(sql);
						KeyCategoryBean cat = new KeyCategoryBean();
						Gson gson = new Gson();
						jedis.del(cacheKey);
						while (rs.next()){
							KeywordBean bean = cat.new KeywordBean();
							bean.setId(rs.getString("id"));
							bean.setSex(rs.getInt("sex"));
							bean.setCategoryId(rs.getString("parent_id"));
							bean.setKeyword(rs.getString("keyword"));   
							bean.setDescription(rs.getString("description"));
							bean.setCategoryId(rs.getString("parent_id"));
							bean.setRank(rs.getString("rank"));
							bean.setBgColor(rs.getString("bgcolor"));
							bean.setIcon(rs.getString("tag_icon"));
							bean.setClientShow(rs.getInt("client_show"));
							bean.setSearchTimes(rs.getString("search_times"));
							bean.setHeat(rs.getString("heat"));
							bean.setAction(new Action(rs.getString("action_type"), rs.getString("action_value")));
							
							bean.setChaye_icon(rs.getString("chaye_icon"));
							bean.setChayeIconSize(rs.getString("chaye_icon_size"));
							bean.setChayeAction(new Action(rs.getString("chaye_action_type"), rs.getString("chaye_action_value")));
							
							jedis.hset(cacheKey, rs.getString("id"), gson.toJson(bean));
						}
					} catch (Exception e) {
						log.error("taginipadcacher init exception  "+ e.getMessage());
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
}
