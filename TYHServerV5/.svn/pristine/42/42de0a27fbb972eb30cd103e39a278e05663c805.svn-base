package cn.youhui.ramdata;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Set;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.exceptions.JedisConnectionException;
import cn.youhui.bean.KeyCategoryBean;
import cn.youhui.bean.KeyCategoryBean.KeywordBean;
import cn.youhui.common.RedisExecutor;
import cn.youhui.common.RedisMySqlExecutor;
import cn.youhui.common.RedisMySqlRunner;
import cn.youhui.common.RedisRunner;
import cn.youhui.common.X;

//停用
public class Tag4UserCacher {
	private static final Logger log = Logger.getLogger(Tag4UserCacher.class);
	int PAGE_SIZE = 12;
	private static String cacheKey;
	private static Tag4UserCacher instance;

	private Tag4UserCacher() {
		// TODO Auto-generated constructor stub
		cacheKey = X.CachePrefix.TAG_TAGS_4_USER;
	}

	public static Tag4UserCacher getInstance() {
		return instance == null ? (instance = new Tag4UserCacher()) : instance;
	}

	/**
	 * 初始化tag标签到redis，根据数据库，初始化三种tag列表： 1. 男人专用 2. 女人专用 3. 通用 对应于三种getTagList方法
	 * 
	 * */
	public void init() {
		RedisMySqlRunner<Boolean> r = new RedisMySqlRunner<Boolean>() {

			@Override
			public Boolean run(Connection conn, Jedis jedis)
					throws JedisConnectionException, SQLException {
				// TODO Auto-generated method stub
				String cacheStatus = jedis.get(X.CachePrefix.CACHE_STATUS
						+ X.DOT + X.CachePrefix.TAG_TAGS_4_SEX);
				if (cacheStatus != null && cacheStatus.equals("OK")) {
					if (log.isInfoEnabled()) {
						log.info(X.CachePrefix.TAG_TAGS_4_SEX
								+ " was cache done.");
					}
					return false;
				} else {  
					try {
						Statement s = conn.createStatement();
						String sql = "SELECT * FROM `youhui_datamining`.`m_discount_keywords`  "
								+ "INNER JOIN (SELECT `id` AS pid,keyword AS pcat FROM `youhui_datamining`.`m_discount_keywords` WHERE parent_id = 0) as T2 on T2.pid = parent_id "
								+ "WHERE `client_show` = '1' ORDER BY `parent_id` ASC,`rank` ASC";
						ResultSet rs = s.executeQuery(sql);
						while (rs.next()) {
							jedis.zadd(X.CachePrefix.TAG_TAGS_4_SEX + X.DOT + rs.getString("sex"), rs.getDouble("parent_id"), rs.getString("id"));
						}
						jedis.set(X.CachePrefix.CACHE_STATUS + X.DOT + X.CachePrefix.TAG_TAGS_4_SEX, "OK");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return true;
			}
		};
		RedisMySqlExecutor<Boolean> e = new RedisMySqlExecutor<Boolean>();
		e.exe(r);
	}

	public void reload() {
		RedisRunner<Boolean> r = new RedisRunner<Boolean>() {

			@Override
			public Boolean run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				jedis.del(X.CachePrefix.CACHE_STATUS + X.DOT
						+ X.CachePrefix.TAG_TAGS_4_SEX);
				jedis.del(new String[] {
						X.CachePrefix.TAG_TAGS_4_SEX + X.DOT + "0",
						X.CachePrefix.TAG_TAGS_4_SEX + X.DOT + "1",
						X.CachePrefix.TAG_TAGS_4_SEX + X.DOT + "2" });
				log.info("tag4sex reload");
				init();
				return true;
			}
		};
		RedisExecutor<Boolean> e = new RedisExecutor<Boolean>();
		e.exe(r);
	}

	/**
	 * 根据性别获取关键词列表
	 * 
	 * @param sex
	 *            == 1 女; sex == 2 男 ;sex == 0 通用
	 * @return
	 */
	public ArrayList<KeyCategoryBean> getTagList4SexWithScore(final String sex) {
		RedisRunner<ArrayList<KeyCategoryBean>> r = new RedisRunner<ArrayList<KeyCategoryBean>>() {

			@Override
			public ArrayList<KeyCategoryBean> run(Jedis jedis)
					throws JedisConnectionException {
				// TODO Auto-generated method stub
				Set<Tuple> sets = jedis.zrangeByScoreWithScores( X.CachePrefix.TAG_TAGS_4_SEX + X.DOT + sex, Double.MIN_VALUE, Double.MAX_VALUE);
				TagCacher cacher = TagCacher.getInstance();
				String lastScore = "";
				ArrayList<KeyCategoryBean> list = new ArrayList<KeyCategoryBean>();
				KeyCategoryBean cat = null;
				for (Tuple tuple : sets) {
					String score = (int) (tuple.getScore()) + "";
					if (!score.equals(lastScore)) {
						lastScore = score;
						cat = new KeyCategoryBean();
						KeywordBean catBean = cacher.getTag(score);
						if (catBean != null) {
							cat.setId(score);
							cat.setName(catBean.getKeyword());
							cat.setSex(catBean.getSex());
							list.add(cat);
						}
					}
					KeywordBean keywordBean = cacher.getTag(tuple.getElement());
					if (cat != null && keywordBean != null) {
						cat.addKeyword(keywordBean);
					}
				}
				return list;
			}
		};
		return new RedisExecutor<ArrayList<KeyCategoryBean>>().exe(r);
	}

	/**
	 * 根据性别获取关键词列表
	 * 
	 * @param sex
	 *            == 1 女; sex == 2 男 ;sex == 0 通用
	 * @return
	 */
	public ArrayList<KeywordBean> getTagList4Sex(final String sex) {
		RedisRunner<ArrayList<KeywordBean>> r = new RedisRunner<ArrayList<KeywordBean>>() {

			@Override
			public ArrayList<KeywordBean> run(Jedis jedis)
					throws JedisConnectionException {
				// TODO Auto-generated method stub
				Set<String> sets = jedis.zrangeByScore(
						X.CachePrefix.TAG_TAGS_4_SEX + X.DOT + sex,
						Double.MIN_VALUE, Double.MAX_VALUE);
				TagCacher cacher = TagCacher.getInstance();
				ArrayList<KeywordBean> list = new ArrayList<KeywordBean>();
				for (String id : sets) {
					KeywordBean keywordBean = cacher.getTag(id);
					if (keywordBean != null) {
						list.add(keywordBean);
					} else {
						jedis.zrem(X.CachePrefix.TAG_TAGS_4_SEX + X.DOT + sex, id);
					}
				}
				return list;
			}
		};
		return new RedisExecutor<ArrayList<KeywordBean>>().exe(r);
	}

	public void addTag4Sex(final String tagId) {
		RedisRunner<Boolean> r = new RedisRunner<Boolean>() {

			@Override
			public Boolean run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				TagCacher cacher = TagCacher.getInstance();
				KeywordBean key = cacher.getTag(tagId);
				if (key != null) {
					jedis.zadd(
							X.CachePrefix.TAG_TAGS_4_SEX + X.DOT + key.getSex(),
							Double.parseDouble(key.getCategoryId()),
							key.getId());
				}
				return true;
			}
		};
		new RedisExecutor<Boolean>().exe(r);
	}

	public void removeTag4Sex(final String tagId, final String sex) {
		RedisRunner<Boolean> r = new RedisRunner<Boolean>() {
			@Override
			public Boolean run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				jedis.zrem(X.CachePrefix.TAG_TAGS_4_SEX + X.DOT + sex, tagId);
				return null;
			}
		};
		new RedisExecutor<Boolean>().exe(r);
	}

	public int getTagList4UserTotal(final String uid) {
		RedisRunner<Integer> r = new RedisRunner<Integer>() {

			@Override
			public Integer run(Jedis jedis) throws JedisConnectionException {
				long count = jedis.zcount(X.CachePrefix.TAG_TAGS_4_USER + X.DOT
						+ uid, Double.MIN_VALUE, Double.MAX_VALUE);
				if (count == 0)
					return 0;
				int totalPage = (int) (count % PAGE_SIZE == 0 ? count
						/ PAGE_SIZE : count / PAGE_SIZE + 1);
				return totalPage;
			}
		};
		return new RedisExecutor<Integer>().exe(r);
	}

	public ArrayList<KeywordBean> getTagList4User(final String uid,
			final int page, final int totalPage) {
		RedisRunner<ArrayList<KeywordBean>> r = new RedisRunner<ArrayList<KeywordBean>>() {

			@Override
			public ArrayList<KeywordBean> run(Jedis jedis)
					throws JedisConnectionException {
				// TODO Auto-generated method stub
				ArrayList<KeywordBean> list = new ArrayList<KeywordBean>();
				TagCacher cacher = TagCacher.getInstance();
				int _page = page < 1 ? 1 : page;
				_page = _page > totalPage ? totalPage : _page;
				int start = PAGE_SIZE * (_page - 1);
				int end = start + PAGE_SIZE - 1;
				Set<String> sets = jedis.zrange(X.CachePrefix.TAG_TAGS_4_USER
						+ X.DOT + uid, start, end);
				for (String id : sets) {
					KeywordBean keywordBean = cacher.getTag(id);
					if (keywordBean != null) {
						// 如果关键词存在
						if (keywordBean.getClientShow() == 1 && !"tagStyleWeb".equals(keywordBean.getAction().getActionType()) && !"tagStyleAppPage".equals(keywordBean.getAction().getActionType()))
							list.add(keywordBean);
					} else {
						// 如果关键词不存在，删除用户对应关系
						jedis.zrem(X.CachePrefix.TAG_TAGS_4_USER + X.DOT + uid,
								id);
					}
				}
				return list;
			}
		};
		RedisExecutor<ArrayList<KeywordBean>> e = new RedisExecutor<ArrayList<KeywordBean>>();
		return e.exe(r);
	}

	public ArrayList<KeywordBean> getTagList4User(final String uid) {
		RedisRunner<ArrayList<KeywordBean>> r = new RedisRunner<ArrayList<KeywordBean>>() {

			@Override
			public ArrayList<KeywordBean> run(Jedis jedis)
					throws JedisConnectionException {
				// TODO Auto-generated method stub
				ArrayList<KeywordBean> list = new ArrayList<KeywordBean>();
				TagCacher cacher = TagCacher.getInstance();
				Set<String> sets = jedis.zrangeByScore(
						X.CachePrefix.TAG_TAGS_4_USER + X.DOT + uid,
						Double.MIN_VALUE, Double.MAX_VALUE);
				for (String id : sets) {
					KeywordBean keywordBean = cacher.getTag(id);
					if (keywordBean != null) {
						// 如果关键词存在
						if (keywordBean.getClientShow() == 1)
							list.add(keywordBean);
					} else {
						// 如果关键词不存在，删除用户对应关系
						jedis.zrem(X.CachePrefix.TAG_TAGS_4_USER + X.DOT + uid,
								id);
					}
				}
				return list;
			}
		};
		RedisExecutor<ArrayList<KeywordBean>> e = new RedisExecutor<ArrayList<KeywordBean>>();
		return e.exe(r);
	}

	/**
	 * 用户第一次请求发生之后属于该用户的tag列表
	 * 
	 * @return
	 * 
	 * */
	public ArrayList<KeyCategoryBean> getTagList4UserWithScore(final String uid) {
		RedisRunner<ArrayList<KeyCategoryBean>> r = new RedisRunner<ArrayList<KeyCategoryBean>>() {

			@Override
			public ArrayList<KeyCategoryBean> run(Jedis jedis)
					throws JedisConnectionException {
				// TODO Auto-generated method stub
				ArrayList<KeyCategoryBean> list = new ArrayList<KeyCategoryBean>();
				TagCacher cacher = TagCacher.getInstance();
				Set<Tuple> sets = jedis.zrangeByScoreWithScores(
						X.CachePrefix.TAG_TAGS_4_USER + X.DOT + uid,
						Double.MIN_VALUE, Double.MAX_VALUE);
				String lastScore = "";
				KeyCategoryBean cat = null;
				for (Tuple tuple : sets) {
					String score = (int) (tuple.getScore()) + "";
					if (!score.equals(lastScore)) {
						lastScore = score;
						cat = new KeyCategoryBean();
						KeywordBean catBean = cacher.getTag(score);
						if (catBean != null) {
							cat.setId(score);
							cat.setName(catBean.getKeyword());
							cat.setSex(catBean.getSex());
							list.add(cat);
						}
					}
					KeywordBean keywordBean = cacher.getTag(tuple.getElement());
					if (cat != null) {
						if (keywordBean != null) {
							// 如果关键词存在
							cat.addKeyword(keywordBean);
						} else {
							// 如果关键词不存在，删除用户对应关系
							jedis.zrem(X.CachePrefix.TAG_TAGS_4_USER + X.DOT
									+ uid, tuple.getElement());
						}
					}
				}
				return list;
			}
		};
		return new RedisExecutor<ArrayList<KeyCategoryBean>>().exe(r);
	}

	public String getTag4UserVer(final String uid) {
		RedisRunner<String> r = new RedisRunner<String>() {

			@Override
			public String run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				String ver = jedis.hget(X.CachePrefix.TAG_USER_4_VER, uid);
				if (ver == null) {
					ver = "0";
					jedis.hset(X.CachePrefix.TAG_USER_4_VER, uid, ver);
				}
				return ver;
			}
		};
		return new RedisExecutor<String>().exe(r);
	}

	public void setTag4UserVer(final String uid) {
		// TODO Auto-generated method stub
		RedisRunner<Boolean> r = new RedisRunner<Boolean>() {

			@Override
			public Boolean run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				String ver = jedis.get(X.CachePrefix.TAG_VERSION);
				if (ver == null) {
					ver = "0";
				}
				jedis.hset(X.CachePrefix.TAG_USER_4_VER, uid, ver);
				return true;
			}
		};
		new RedisExecutor<Boolean>().exe(r);
	}

	public void setTagList4User(final String uid,
			final ArrayList<KeywordBean> list) {
		RedisRunner<Boolean> r = new RedisRunner<Boolean>() {

			@Override
			public Boolean run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				String ver = jedis.get(X.CachePrefix.TAG_VERSION);
				if (ver == null)
					ver = System.currentTimeMillis() + "";
				jedis.del(X.CachePrefix.TAG_TAGS_4_USER + X.DOT + uid);
				for (KeywordBean key : list) {
					jedis.zadd(X.CachePrefix.TAG_TAGS_4_USER + X.DOT + uid,
							Double.parseDouble(key.getCategoryId()),
							key.getId());
				}
				jedis.hset(X.CachePrefix.TAG_USER_4_VER, uid, ver);
				return true;
			}
		};
		new RedisExecutor<Boolean>().exe(r);
	}

	public void setTagList4UserWithScore(final String uid,
			final ArrayList<KeyCategoryBean> list) {
		RedisRunner<Boolean> r = new RedisRunner<Boolean>() {

			@Override
			public Boolean run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				String ver = jedis.get(X.CachePrefix.TAG_VERSION);
				if (ver == null)
					ver = "0";
				jedis.del(X.CachePrefix.TAG_TAGS_4_USER + X.DOT + uid);
				for (KeyCategoryBean cat : list) {
					for (KeywordBean key : cat.getList()) {
						jedis.zadd(X.CachePrefix.TAG_TAGS_4_USER + X.DOT + uid,
								Double.parseDouble(cat.getId()), key.getId());
					}
				}
				jedis.hset(X.CachePrefix.TAG_USER_4_VER, uid, ver);
				return true;
			}
		};
		new RedisExecutor<Boolean>().exe(r);
	}

	public void addTag4User(final String uid, final String tagId) {
		RedisRunner<Boolean> r = new RedisRunner<Boolean>() {

			@Override
			public Boolean run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				TagCacher cacher = TagCacher.getInstance();
				KeywordBean keyword = cacher.getTag(tagId);
				if (keyword != null)
					jedis.zadd(X.CachePrefix.TAG_TAGS_4_USER + X.DOT + uid,
							Double.parseDouble(keyword.getCategoryId()),
							keyword.getId());
				return true;
			}
		};
		new RedisExecutor<Boolean>().exe(r);
	}

	public void removeTag4User(final String uid, final String tagId) {
		RedisRunner<Boolean> r = new RedisRunner<Boolean>() {
			@Override
			public Boolean run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				jedis.zrem(X.CachePrefix.TAG_TAGS_4_USER + X.DOT + uid, tagId);
				return true;
			}
		};
		new RedisExecutor<Boolean>().exe(r);
	}

}
