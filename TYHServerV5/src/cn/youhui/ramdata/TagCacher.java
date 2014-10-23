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
import cn.youhui.common.SepaConfig;
import cn.youhui.common.X;
import cn.youhui.dao.JedisDBIns;
import cn.youhui.dao.MySQLDBIns;
import cn.youhui.utils.Util;

import com.google.gson.Gson;

public class TagCacher {
	private static String cacheKey = X.CachePrefix.TAG_ALL_TAGS;
	private static String cacheLock = X.CachePrefix.CACHE_STATUS + X.DOT + cacheKey;
	private static final Logger log = Logger.getLogger(TagCacher.class);
	private static TagCacher instance;
	
	private TagCacher() {
	}
	
	public static TagCacher getInstance() {
		return instance == null ? (instance = new TagCacher()) : instance;
	}
	
	public String getTagVer() {
		RedisRunner<String> r = new RedisRunner<String>() {
			
			@Override
			public String run(Jedis jedis) throws JedisConnectionException {
				String ver = jedis.get(X.CachePrefix.TAG_VERSION);
				if (ver == null) {
					ver = System.currentTimeMillis() + "";
					jedis.set(X.CachePrefix.TAG_VERSION, ver);
				}
				return ver;
			}
		};
		RedisExecutor<String> e = new RedisExecutor<String>();
		return e.exe(r);
	}
	
	public boolean addTag(final KeywordBean keyword) {
		RedisRunner<Boolean> r = new RedisRunner<Boolean>() {
			
			@Override
			public Boolean run(Jedis jedis) throws JedisConnectionException {
				jedis.hset(cacheKey, keyword.getId(),
						new Gson().toJson(keyword));
				return true;
			}
		};
		RedisExecutor<Boolean> e = new RedisExecutor<Boolean>();
		return e.exe(r);
	}
	
//	public int getTag(final String id) {
//		RedisRunner<KeywordBean> r = new RedisRunner<KeywordBean>() {
//			
//			@Override
//			public KeywordBean run(Jedis jedis) throws JedisConnectionException {
//				String source = jedis.hlen(id);
//				KeywordBean keyword = null;
//				if (source != null)
//				{
//					keyword = new Gson().fromJson(source, KeywordBean.class);
//				}
//				return keyword;
//			}
//		};
//		RedisExecutor<KeywordBean> e = new RedisExecutor<KeywordBean>();
//		return e.exe(r);
//	}
	
	public KeywordBean getTag(final String id) {
		RedisRunner<KeywordBean> r = new RedisRunner<KeywordBean>() {
			
			@Override
			public KeywordBean run(Jedis jedis) throws JedisConnectionException {
				String source = jedis.hget(cacheKey, id);
				KeywordBean keyword = null;
				if (source != null)
				{
					keyword = new Gson().fromJson(source, KeywordBean.class);
//					String icon = jedis.get(cacheKey + keyword.getKeyword() + ".icon");
//					if (icon != null)
//						keyword.setIcon(icon);
//					else if(keyword.getIcon().equals("")){
//						keyword.setIcon(tagRandomIcon(keyword.getKeyword()));
//					}
				}
				return keyword;
			}
		};
		RedisExecutor<KeywordBean> e = new RedisExecutor<KeywordBean>();
		return e.exe(r);
	}
	
	public boolean isexistTag(final String id) {
		RedisRunner<Boolean> r = new RedisRunner<Boolean>() {
			public Boolean run(Jedis jedis) throws JedisConnectionException {
				String source = jedis.hget(cacheKey, id);
				if(source != null && !"".equals(source))
					return true;
				else return false;
			}
		};
		RedisExecutor<Boolean> e = new RedisExecutor<Boolean>();
		return e.exe(r);
	}
	
	/**
	 * 检查上传的tagids是否合法
	 * @param tagIds
	 * @return
	 */
	public boolean isRightTagIds(final String tagIds) {
		if(SepaConfig.TAG_SEPA.equals(tagIds)){
			return false;
		}
		return true;
	}
	
	public String tagRandomIcon(final String tag){
		String icon = "";
		Connection conn = MySQLDBIns.getInstance().getConnection();
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		try {
			Statement s = conn.createStatement();
			String sql = "SELECT `pic_url`,`item_id` FROM `youhui_datamining`.`m_discount_products` WHERE `keyword` = '"
					+ tag + "' ORDER BY RAND() LIMIT 1;";
			ResultSet rs = s.executeQuery(sql);
			if (rs.next()) {
				icon = Util.getSmallImg(rs.getString("pic_url"));
				jedis.set(cacheKey + tag + ".icon", icon);
				jedis.expire(cacheKey + tag + ".icon", 24 * 60 * 60);
			}
			JedisDBIns.getInstance().release(jedis);
		} catch (JedisConnectionException e) {		
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		} catch (Exception e) {
			log.error(e, e);
			JedisDBIns.getInstance().release(jedis);
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return icon;
	}
	
	public void delTag(final String tagId) {
		RedisRunner<Boolean> r = new RedisRunner<Boolean>() {
			
			@Override
			public Boolean run(Jedis jedis) throws JedisConnectionException {
				//jedis.hdel(cacheKey, tagId);
				return true;
			}
		};
		RedisExecutor<Boolean> e = new RedisExecutor<Boolean>();
		e.exe(r);
	}
	
	public boolean init() {
		RedisMySqlRunner<Boolean> r = new RedisMySqlRunner<Boolean>() {
			@Override
			public Boolean run(Connection conn, Jedis jedis) throws JedisConnectionException, SQLException {
				String cacheStatus = jedis.get(cacheLock);
				if(!X.CachePrefix.LOCK_MARK.equals(cacheStatus)){
					try {
						jedis.set(cacheLock, X.CachePrefix.LOCK_MARK);
						log.info("tagcacher init............");
						Statement s = conn.createStatement();
						String sql = "SELECT * FROM `youhui_datamining`.`m_discount_keywords` where `client_show` = 1 or `client_show` = 2;";
						ResultSet rs = s.executeQuery(sql);
						KeyCategoryBean cat = new KeyCategoryBean();
						Gson gson = new Gson();
						jedis.del(cacheKey);
						while (rs.next()) {
							KeywordBean bean = cat.new KeywordBean();
							bean.setId(rs.getString("id"));
							bean.setSex(rs.getInt("sex"));
							bean.setCategoryId(rs.getString("parent_id"));
							bean.setBgColor(rs.getString("bgcolor"));
							bean.setKeyword(rs.getString("keyword"));   
							bean.setDescription(rs.getString("description"));
							bean.setCategoryId(rs.getString("parent_id"));
							bean.setRank(rs.getString("rank"));
							bean.setIcon(rs.getString("icon"));
							bean.setClientShow(rs.getInt("client_show"));
							bean.setSearchTimes(rs.getString("search_times"));
							bean.setHeat(rs.getString("heat"));
							bean.setShow(rs.getString("show"));
							bean.setAction(new Action(rs.getString("action_type"), rs.getString("action_value")));
							bean.setChaye_icon(rs.getString("chaye_icon"));
							bean.setChayeIconSize(rs.getString("chaye_icon_size"));
							bean.setChayeAction(new Action(rs.getString("chaye_action_type"), rs.getString("chaye_action_value")));
							bean.setHasSonTagAll(rs.getString("has_son_tag_all"));
							bean.setDefaultSonTagId(rs.getString("default_son_tag_id"));
							bean.setStartTimestamp(rs.getLong("sale_time"));
							jedis.hset(cacheKey, rs.getString("id"), gson.toJson(bean));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						jedis.del(cacheLock);
					}
				}
				return true;
			}
		};
		RedisMySqlExecutor<Boolean> e = new RedisMySqlExecutor<Boolean>();
		return e.exe(r);
	}
	
	public void reload() {
		init();
	}
	
	public static void main(String[] args) {
		TagCacher.getInstance().reload();
		System.out.println(TagCacher.getInstance().getTag("713"));
	}
}
