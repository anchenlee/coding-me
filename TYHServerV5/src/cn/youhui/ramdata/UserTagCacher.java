package cn.youhui.ramdata;

/**
 * @author leejun
 * @since 2012-11
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import cn.youhui.bean.KeyCategoryBean.KeywordBean;
import cn.youhui.common.RedisExecutor;
import cn.youhui.common.RedisMySqlExecutor;
import cn.youhui.common.RedisMySqlRunner;
import cn.youhui.common.RedisRunner;
import cn.youhui.common.SepaConfig;
import cn.youhui.common.X;

public class UserTagCacher {
	private static final Logger log = Logger.getLogger(UserTagCacher.class);
	
	private static String cacheKey = X.CachePrefix.TAG_USER_TAGS;
	private static String cacheLock = X.CachePrefix.CACHE_STATUS + X.DOT + cacheKey;
	private static String defalutuid = X.CachePrefix.DEFALUT_UID;
	private static String tagstatus = X.CachePrefix.TAG_STATUS;
	private static final int defalutcount = 8;
	private static UserTagCacher instance = null;
	private static Pattern p = Pattern.compile("^[0-9]+$");
	private UserTagCacher(){
	}
	
	public static UserTagCacher getInstance(){
		if(instance == null) instance = new UserTagCacher();
		return instance;
	}
	
	public void init() {
		RedisMySqlRunner<Boolean> r = new RedisMySqlRunner<Boolean>() {
			@Override
			public Boolean run(Connection conn, Jedis jedis) throws JedisConnectionException, SQLException {
				String cacheStatus = jedis.get(cacheLock);
				if(!X.CachePrefix.LOCK_MARK.equals(cacheStatus)){
					try {
						jedis.set(cacheLock, X.CachePrefix.LOCK_MARK);
						log.info("usertagcacher init............");
					    Statement s = conn.createStatement();
						String sql = "SELECT * FROM `youhui_datamining`.`tyh_user_tag` order by uid,rank asc;";
						
						ResultSet rs = s.executeQuery(sql);
						List<String> list = new ArrayList<String>();

						while (rs.next()) 
						{
							if(!list.contains(rs.getString("uid")))
							{
								list.add(rs.getString("uid"));
								jedis.hdel(cacheKey, rs.getString("uid"));
								jedis.hset(cacheKey, rs.getString("uid"),rs.getString("tagid"));
							}
							else
							{
								String tag = jedis.hget(cacheKey, rs.getString("uid"));
//								if( rs.getString("uid").equals("54496402")) System.out.println(tag);
								if(tag != null && !tag.contains(rs.getString("tagid")))
							        jedis.hset(cacheKey, rs.getString("uid"),tag + SepaConfig.TAG_SEPA +rs.getString("tagid"));
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
						log.error("usertagcacher init exception:" + e.getMessage());
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
	
//	public void reloadDefalutTagIds(){
//		RedisMySqlRunner<Boolean> r = new RedisMySqlRunner<Boolean>() {
//			@Override
//			public Boolean run(Connection conn, Jedis jedis) throws JedisConnectionException, SQLException {
//					try {
//						Statement s = conn.createStatement();
//						String sql = "SELECT * FROM `youhui_datamining`.`tyh_user_tag` where uid = '"+defalutuid+"' order by rank asc;";
//						ResultSet rs = s.executeQuery(sql);
//						String defalutTagIds = "";
//						while(rs.next()){
//							defalutTagIds += rs.getString("tagid") + SepaConfig.TAG_SEPA;
//							}
//						jedis.hset(cacheKey, defalutuid, defalutTagIds);
//					} catch (Exception e) {
//						e.printStackTrace();
//						log.info("defalutusertag init exception:" + e.getMessage());
//					}
//				return true;
//			}
//		};
//		RedisMySqlExecutor<Boolean> e = new RedisMySqlExecutor<Boolean>();
//		e.exe(r);
//	}
	
	public String getTagIds(final String uid){
		RedisRunner<String> r = new RedisRunner<String>() {
			@Override
			public String run(Jedis jedis) throws JedisConnectionException {
				String tagIds = null;
				
				if(uid != null && !"".equals(uid)){
					tagIds = jedis.hget(cacheKey, uid);
					String dtagId = "831";     //订单认领
					String rtagId = "832";     //买家必读
					String shareId = "908";
					if(tagIds != null && !"".equals(tagIds)){       //强制添加某个标签
						tagIds = tagIds.replaceAll(shareId, "") + SepaConfig.TAG_SEPA + shareId ;
						
						tagIds = tagIds.replaceAll(dtagId, "") + SepaConfig.TAG_SEPA + dtagId ;
						tagIds = tagIds.replaceAll(rtagId, "") + SepaConfig.TAG_SEPA + rtagId ;
						
					}
				}
				if(uid == null || "".equals(uid) || tagIds == null || "".equals(tagIds) || SepaConfig.TAG_SEPA.equals(tagIds)){
					String defalTagIds= jedis.hget(cacheKey, defalutuid);
					if(defalTagIds == null) {
						reload();
						defalTagIds= jedis.hget(cacheKey, defalutuid);
					}
					return defalTagIds;
				}
				return tagIds;
			}
		};
		
		RedisExecutor<String> re = new RedisExecutor<String>() ;
		return re.exe(r) ;
	}
	
	private String getFirst(String tagids){
		if(tagids == null || "".equals(tagids)){
			return null;
		}else{
			String ret = "";
			String[] tagidss = tagids.split(SepaConfig.TAG_SEPA);
			if(tagidss.length <= defalutcount){
				return tagids;
			}else{
				int i = 1;
				for(String tagid:tagidss){
					if(i>defalutcount)
						break;
					if(tagid != null && !"".equals(tagid)){
					    ret += tagid + SepaConfig.TAG_SEPA;
					    i++;
					}
				}
				return ret;
			}
		}
	}
	
	public List<KeywordBean> getTags(final String uid){
		RedisRunner<List<KeywordBean>> r = new RedisRunner<List<KeywordBean>>(){
			@Override
			public List<KeywordBean> run(Jedis jedis) throws JedisConnectionException {
				TagCacher cacher = TagCacher.getInstance();
				List<KeywordBean> list = new ArrayList<KeywordBean>();
				String tag = getTagIds(uid);
				tag = clear(tag);
				String[] tags = null;
				if(tag != null){
					tags = tag.split(SepaConfig.TAG_SEPA);
					String defalTagIds= jedis.hget(cacheKey, defalutuid);
					for (String id : tags) {
						if(defalTagIds.indexOf(id) > -1){
							KeywordBean keywordBean = cacher.getTag(id);
							if (keywordBean != null){
								list.add(keywordBean);
							} 
						}
					}
				}
				if((list == null || list.size() == 0 ) && !defalutuid.equals(uid)){
					list = getAllTag();
				}
				return list;
			}
		};
		return new RedisExecutor<List<KeywordBean>>().exe(r);
	}
	
	/**
	 * 去除tagids中相同的tagid
	 * @param tagids
	 * @return
	 */
	private String clear(String tagids){
		String ret = "";
		String[] tags = tagids.split(SepaConfig.TAG_SEPA);
		for(String tag : tags){
			if(ret.indexOf(tag) < 0){
				ret += tag + SepaConfig.TAG_SEPA;
			}
		}
		return ret;
	}
	
	public List<KeywordBean> getAllTag(){
		return getTags(defalutuid);
	}
	
	public boolean checkUid(String uid){
		
		Matcher m = p.matcher(uid);
		return m.find();
	}
	
	public boolean setTagIds(final String uid, final String tagIds){
		
		RedisMySqlRunner<Boolean> r = new RedisMySqlRunner<Boolean>() {
			@Override
			public Boolean run(Connection conn, Jedis jedis) throws JedisConnectionException, SQLException {
				if(checkUid(uid)){
					String sqlde = "delete from `youhui_datamining`.`tyh_user_tag` where `uid` =?;";
					String sqlin = "insert into `youhui_datamining`.`tyh_user_tag`(uid, tagid, rank) values(?,?,?);";
					conn.setAutoCommit(false);
					jedis.hset(cacheKey, uid, tagIds);
						try {
							PreparedStatement s = conn.prepareStatement(sqlde);
							s.setString(1, uid);
							s.executeUpdate();
							if(tagIds != null && !"".equals(tagIds)){
					        String[] tags = tagIds.split(SepaConfig.TAG_SEPA);
					        int rank = 1;
					        s = conn.prepareStatement(sqlin);
							for(String tagid : tags){
								s.setString(1, uid);
								s.setString(2, tagid);
								s.setString(3, rank+"");
								s.addBatch();
								rank ++;
							}
							}
							s.executeBatch();
							conn.setAutoCommit(true);
							return true;
						} catch (Exception e) {
							e.printStackTrace();
						}
					return false;
				}else {
					System.err.println("error UID = "+uid);
					return false;
				}
				
				
			}
		};
		RedisMySqlExecutor<Boolean> e = new RedisMySqlExecutor<Boolean>();
		return e.exe(r);
	}
	
public boolean addTagId(final String uid, final String tagId){
		
		RedisMySqlRunner<Boolean> r = new RedisMySqlRunner<Boolean>() {
			@Override
			public Boolean run(Connection conn, Jedis jedis) throws JedisConnectionException, SQLException {
				if(checkUid(uid)){
					String sqlse = "select max(`rank`) as mrank from `youhui_datamining`.`tyh_user_tag` where `uid` =?;";
					String sqlin = "insert into `youhui_datamining`.`tyh_user_tag`(uid, tagid, rank) values(?,?,?);";
						try {
							PreparedStatement s = conn.prepareStatement(sqlse);
							s.setString(1, uid);
							int rank = 1;
							ResultSet rs = s.executeQuery();
							if(rs.next()){
								rank = rs.getInt("mrank")+1;
							}
							s = conn.prepareStatement(sqlin);
							s.setString(1, uid);
							s.setString(2, tagId);
							s.setInt(3, rank);
							int i = s.executeUpdate();
							if(i > 0){
								String tagids = jedis.hget(cacheKey, uid);
								jedis.hset(cacheKey, uid, tagids + SepaConfig.TAG_SEPA + tagId);
							}
							return true;
						} catch (Exception e) {
//							e.printStackTrace();
							return true;                //已存在此uid - tagid  返回true
						}
				}else {
					return false;
				}
				
				
			}
		};
		RedisMySqlExecutor<Boolean> e = new RedisMySqlExecutor<Boolean>();
		return e.exe(r);
	}
	
	public String getTagStatus(){
		RedisRunner<String> r = new RedisRunner<String>() {
			@Override
			public String run(Jedis jedis) throws JedisConnectionException{
			     return jedis.get(tagstatus);
			}
		};
		RedisExecutor<String> e = new RedisExecutor<String>();
		return e.exe(r);
	}

	public static void main(String[] args) {
//		UserTagCacher.getInstance().reload();
	}
}
