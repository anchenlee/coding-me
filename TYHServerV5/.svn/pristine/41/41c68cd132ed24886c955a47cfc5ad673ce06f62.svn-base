package cn.youhui.ramdata;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import cn.youhui.bean.UserBean;
import cn.youhui.common.RedisExecutor;
import cn.youhui.common.RedisMySqlExecutor;
import cn.youhui.common.RedisMySqlRunner;
import cn.youhui.common.RedisRunner;
import cn.youhui.common.X;

public class UserCacher {
	static UserCacher ins ;
	static String KP = X.CachePrefix.YH_USERS ;
	
	protected UserCacher(){}
	
	public static UserCacher getInstance(){
		return ins == null ? (ins = new UserCacher()) : ins ;
	}
	
	/**
	 * 从redis读取user
	 * */
	public UserBean getUser(long uid){
		return null ;
	}
	
	/**
	 *  返回某用户的taobao NICK
	 * */
	public String getTaoNick(final long uid){
		RedisRunner<String> rr = new RedisRunner<String>() {

			@Override
			public String run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				return jedis.hget(KP + X.DOT + uid, "taobaoNick") ;
			}
		};
		
		RedisExecutor<String> re = new RedisExecutor<String>() ;
		return re.exe(rr) ;
	}
	
	public boolean addUser(UserBean user){
		return false ;
	}
	
	/**
	 *  从数据库读出user 存入到redis，然后return ， uid不存在返回null
	 * 
	 * **/
	public UserBean loadUser2Redis(final long uid){
		RedisMySqlRunner<UserBean> mrr = new RedisMySqlRunner<UserBean>() {

			@Override
			public UserBean run(Connection conn, Jedis jedis)
					throws JedisConnectionException, SQLException {
				// TODO Auto-generated method stub
				String sql = "SELECT * FROM `youhui_v3`.`user` WHERE `uid` = '" + uid + "' ; " ; 
				Statement stmt = conn.createStatement() ;
				ResultSet rs =stmt.executeQuery(sql) ;
				UserBean user = null;
				if(rs.first()){
					user = fetchUser(rs) ;
					jedis.hmset(KP + X.DOT + uid, user.trans2Map()) ;
				}
				
				return user;
			}
		};
		
		RedisMySqlExecutor<UserBean> rme = new RedisMySqlExecutor<UserBean>() ;
		
		return rme.exe(mrr) ;
	}
	
	protected UserBean fetchUser(ResultSet rs) throws SQLException{
		UserBean user = new UserBean() ;
		user.setUid(rs.getString("uid")) ;
		user.setTaobaoNick(rs.getString("taobao_nick")) ;
		
		return user ;
	}
}
