package cn.youhui.ramdata;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import com.google.gson.Gson;

import cn.youhui.bean.UserJFBAccount;
import cn.youhui.common.RedisMySqlExecutor;
import cn.youhui.common.RedisMySqlRunner;
import cn.youhui.common.TPool;
import cn.youhui.common.X;
import cn.youhui.dao.JedisDBIns;
import cn.youhui.dao.MySQLDBIns;
import cn.youhui.manager.ActivityMingxiManager;
import cn.youhui.manager.JedisHashManager;
import cn.youhui.manager.JiFenBaoMXManager;

/**
 * 用户集分宝帐号信息
 * @author lijun
 * @since 2014-02-10
 */
public class UserJFBAccountCacher {
	
	private static String cacheKey = X.CachePrefix.USER_JFB_ACCOUNT;
	private static String preCacheKey = X.CachePrefix.USER_JFB_ACCOUNT+"_";
	private Hashtable<String,String> updateresult = new Hashtable<String,String>();

	private static String cacheLock = X.CachePrefix.CACHE_STATUS + X.DOT + cacheKey;
	
	private static UserJFBAccountCacher instance = null;
	
	private UserJFBAccountCacher() {
	}
	
	public static UserJFBAccountCacher getInstance() {
		return instance == null ? (instance = new UserJFBAccountCacher()) : instance;
	}
	
	
	
	public String result(){
		StringBuffer rst = new StringBuffer();
		for (int i = 1; i < 10; i++) {
			rst.append(updateresult.get(i+""));
			rst.append("\n");
		}
		rst.append(cacheKey+":"+updateresult.get(cacheKey));
		rst.append("\n");
		return rst.toString();
	}
	
	/**
	 * 添加用户集分宝
	 * @param uid
	 * @param jfbNum
	 */
	public void addGainNum(String uid, int jfbNum){
		String ujastr2 = new JedisHashManager((preCacheKey+uid.charAt(0))).get(uid);
		UserJFBAccount uja2 = new UserJFBAccount();
		Gson gson2 = new Gson();
		if(ujastr2 != null && !"".equals(ujastr2)){
			uja2 = gson2.fromJson(ujastr2, UserJFBAccount.class);
			if(uja2 != null){
				uja2.setGainNum(jfbNum + uja2.getGainNum());
			}
		}else{
			uja2.setGainNum(jfbNum);
		}
		if(uja2.getGainNum() != 0){
			new JedisHashManager((preCacheKey+uid.charAt(0))).add(uid, gson2.toJson(uja2));
		}
		
		
		
		String ujastr = new JedisHashManager(cacheKey).get(uid);
		UserJFBAccount uja = new UserJFBAccount();
		Gson gson = new Gson();
		if(ujastr != null && !"".equals(ujastr)){
			uja = gson.fromJson(ujastr, UserJFBAccount.class);
			if(uja != null){
				uja.setGainNum(jfbNum + uja.getGainNum());
			}
		}else{
			uja.setGainNum(jfbNum);
		}
		if(uja.getGainNum() != 0){
			new JedisHashManager(cacheKey).add(uid, gson.toJson(uja));
		}
	}
	
	
	/**
	 * 添加用户返利集分宝
	 * @param uid
	 * @param jfbNum
	 */
	public void addFLNum(String uid, int jfbNum){
		String ujastr2 = new JedisHashManager((preCacheKey+uid.charAt(0))).get(uid);
		UserJFBAccount uja2 = new UserJFBAccount();
		Gson gson2 = new Gson();
		if(ujastr2 != null && !"".equals(ujastr2)){
			uja2 = gson2.fromJson(ujastr2, UserJFBAccount.class);
			if(uja2 != null){
				uja2.setFlNum(jfbNum + uja2.getFlNum());
			}
		}else{
			uja2.setFlNum(jfbNum);
		}
		if(uja2.getFlNum() != 0){
			new JedisHashManager((preCacheKey+uid.charAt(0))).add(uid, gson2.toJson(uja2));
		}
		
		
		
		String ujastr = new JedisHashManager(cacheKey).get(uid);
		UserJFBAccount uja = new UserJFBAccount();
		Gson gson = new Gson();
		if(ujastr != null && !"".equals(ujastr)){
			uja = gson.fromJson(ujastr, UserJFBAccount.class);
			if(uja != null){
				uja.setFlNum(jfbNum + uja.getFlNum());
			}
		}else{
			uja.setFlNum(jfbNum);
		}
		if(uja.getFlNum() != 0){
			new JedisHashManager(cacheKey).add(uid, gson.toJson(uja));
		}
	}
	
	/**
	 * 添加提现集分宝
	 */
	public void addTxNum(String uid, int jfbNum){
		String ujastr2 = new JedisHashManager((preCacheKey+uid.charAt(0))).get(uid);
		UserJFBAccount uja2 = new UserJFBAccount();
		Gson gson2 = new Gson();
		if(ujastr2 != null && !"".equals(ujastr2)){
			uja2 = gson2.fromJson(ujastr2, UserJFBAccount.class);
			if(uja2 != null){
				uja2.setChkTxNum(jfbNum + uja2.getChkTxNum());
			}
			new JedisHashManager((preCacheKey+uid.charAt(0))).add(uid, gson2.toJson(uja2));
		}
		
		
		String ujastr = new JedisHashManager(cacheKey).get(uid);
		UserJFBAccount uja = new UserJFBAccount();
		Gson gson = new Gson();
		if(ujastr != null && !"".equals(ujastr)){
			uja = gson.fromJson(ujastr, UserJFBAccount.class);
			if(uja != null){
				uja.setChkTxNum(jfbNum + uja.getChkTxNum());
			}
			new JedisHashManager(cacheKey).add(uid, gson.toJson(uja));
		}
	}
	
	/**
	 * 成功提现
	 */
	public void addSucTxNum(String uid, int jfbNum){
		String ujastr2 = new JedisHashManager((preCacheKey+uid.charAt(0))).get(uid);
		UserJFBAccount uja2 = new UserJFBAccount();
		Gson gson2 = new Gson();
		if(ujastr2 != null && !"".equals(ujastr2)){
			uja2 = gson2.fromJson(ujastr2, UserJFBAccount.class);
			if(uja2 != null){
				int chkNum = uja2.getChkTxNum() - jfbNum;
				uja2.setChkTxNum(chkNum >= 0 ? chkNum : 0);
				uja2.setTxNum(uja2.getTxNum() + jfbNum);
			}
			new JedisHashManager((preCacheKey+uid.charAt(0))).add(uid, gson2.toJson(uja2));
		}
		
		String ujastr = new JedisHashManager(cacheKey).get(uid);
		UserJFBAccount uja = new UserJFBAccount();
		Gson gson = new Gson();
		if(ujastr != null && !"".equals(ujastr)){
			uja = gson.fromJson(ujastr, UserJFBAccount.class);
			if(uja != null){
				int chkNum = uja.getChkTxNum() - jfbNum;
				uja.setChkTxNum(chkNum >= 0 ? chkNum : 0);
				uja.setTxNum(uja.getTxNum() + jfbNum);
			}
			new JedisHashManager(cacheKey).add(uid, gson.toJson(uja));
		}
	}
	
	/**
	 * 提现失败
	 */
	public void addFailTxNum(String uid, int jfbNum){
		
		String ujastr2 = new JedisHashManager((preCacheKey+uid.charAt(0))).get(uid);
		UserJFBAccount uja2 = new UserJFBAccount();
		Gson gson2 = new Gson();
		if(ujastr2 != null && !"".equals(ujastr2)){
			uja2 = gson2.fromJson(ujastr2, UserJFBAccount.class);
			if(uja2 != null){
				int chkNum = uja2.getChkTxNum() - jfbNum;
				uja2.setChkTxNum(chkNum >= 0 ? chkNum : 0);
			}
			new JedisHashManager(preCacheKey+uid.charAt(0)).add(uid, gson2.toJson(uja2));
		}
		
		String ujastr = new JedisHashManager(cacheKey).get(uid);
		UserJFBAccount uja = new UserJFBAccount();
		Gson gson = new Gson();
		if(ujastr != null && !"".equals(ujastr)){
			uja = gson.fromJson(ujastr, UserJFBAccount.class);
			if(uja != null){
				int chkNum = uja.getChkTxNum() - jfbNum;
				uja.setChkTxNum(chkNum >= 0 ? chkNum : 0);
			}
			new JedisHashManager(cacheKey).add(uid, gson.toJson(uja));
		}
	}
	
	public UserJFBAccount get(String uid){
		String ujastr = new JedisHashManager((preCacheKey+uid.charAt(0))).get(uid);
		UserJFBAccount uja = new UserJFBAccount();
		if(ujastr != null && !"".equals(ujastr)){
			uja = new Gson().fromJson(ujastr, UserJFBAccount.class);
		}
		return uja;
	}
	
	
	
	
	public boolean reload() {
		RedisMySqlRunner<Boolean> r = new RedisMySqlRunner<Boolean>() {
			@Override
			public Boolean run(Connection conn, Jedis jedis) throws JedisConnectionException, SQLException {
				jedis.del(cacheLock);
				String cacheStatus = jedis.get(cacheLock);
				if(!X.CachePrefix.LOCK_MARK.equals(cacheStatus)){
					try {
						jedis.set(cacheLock, X.CachePrefix.LOCK_MARK);
						Gson gson = new Gson();
						for(int i=1; i<10; i++){
							List<String> uids = JiFenBaoMXManager.getInstance().getUids(i+"", conn);
							for(String uid : uids){
								UserJFBAccount uja = ActivityMingxiManager.getInstance().getUserJfbAccount(uid, conn);
								
								if(uja != null&&!(uja.getChkTxNum()==0&&uja.getGainNum()==0&&uja.getTxNum()==0&&uja.getFlNum()==0)){
									jedis.hset(cacheKey, uid, gson.toJson(uja));
									String result = updateresult.get(cacheKey);
									if(result ==null||"".equals(result)){
										result = ""+System.currentTimeMillis() +"|"+i+"|"+uids.size();
									}else {
										String []results = result.split("|");
										result = results[0]+"|"+i+"|"+uids.size();
									}
									updateresult.put(cacheKey, result);
								}
								
								Thread.sleep(50);
							}
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
	
	public boolean reload(int type) {
		Thread t1 = new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("1 is start!");
				updatejfbinfo(1);
			}
		};
		Thread t2 = new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("2 is start!");
				updatejfbinfo(2);
			}
		};
		Thread t3 = new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("3 is start!");
				updatejfbinfo(3);
			}
		};
		Thread t4 = new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("4 is start!");
				updatejfbinfo(4);
			}
		};
		
		Thread t5 = new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("5 is start!");
				updatejfbinfo(5);
			}
		};
		
		Thread t6 = new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("6 is start!");
				updatejfbinfo(6);
				
			}
		};
		
		
		Thread t7 = new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("7 is start!");
				updatejfbinfo(7);
				
			}
		};
		
		Thread t8 = new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("8 is start!");
				updatejfbinfo(8);
			}
		};
		
		Thread t9 = new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("9 is start!");
				updatejfbinfo(9);
			}
		};
		
		
		switch (type) {
		case 0:
			TPool.getInstance().doit(t1);
			TPool.getInstance().doit(t2);
			TPool.getInstance().doit(t3);
			TPool.getInstance().doit(t4);
			TPool.getInstance().doit(t5);
			TPool.getInstance().doit(t6);
			TPool.getInstance().doit(t7);
			TPool.getInstance().doit(t8);
			TPool.getInstance().doit(t9);
			break;
		case 1:
			TPool.getInstance().doit(t1);
			break;
		case 2:
			TPool.getInstance().doit(t2);
			break;
		case 3:
			TPool.getInstance().doit(t3);
			break;
		case 4:
			TPool.getInstance().doit(t4);
			break;
		case 5:
			TPool.getInstance().doit(t5);
			break;
		case 6:
			TPool.getInstance().doit(t6);
			break;
		case 7:
			TPool.getInstance().doit(t7);
			break;
		case 8:
			TPool.getInstance().doit(t8);
			break;
		case 9:
			TPool.getInstance().doit(t9);
			break;
		default:
			return false;
		}
		return true;
	}
	
	public Boolean updatejfb(Connection conn, Jedis jedis, int uidpre)
			throws JedisConnectionException, SQLException {
		try {
			Gson gson = new Gson();
			List<String> uids = JiFenBaoMXManager.getInstance().getUids(uidpre + "", conn);
			updateresult.put(uidpre+"", ""+System.currentTimeMillis() +"|0|"+uids.size());
			for (int i = 0; i <uids.size();i++) {
				String uid = uids.get(i);
				UserJFBAccount uja = ActivityMingxiManager.getInstance().getUserJfbAccount(uid, conn);
				if (uja != null&&!(uja.getChkTxNum()==0&&uja.getGainNum()==0&&uja.getTxNum()==0)) {
					jedis.hset(preCacheKey+uidpre, uid, gson.toJson(uja));
					
					String result = updateresult.get(uidpre+"");
					if(result ==null||"".equals(result)){
						result = System.currentTimeMillis() +"|"+i+"|"+uids.size();
					}else {
						String []results = result.split("|");
						result = results[0]+"|"+i+"|"+uids.size();
					}
					updateresult.put(uidpre+"", result);
				}
				
				Thread.sleep(50);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return false;
	}
	
	public boolean updatejfbinfo(int uidpre){
		Boolean rst = null ;
		Jedis j = null ;
		Connection  conn = null ;
		try{
			conn = MySQLDBIns.getInstance().getReadConnection() ;
			j = JedisDBIns.getInstance().getJedis() ;
			
			rst = updatejfb(conn, j, uidpre);
			
			JedisDBIns.getInstance().release(j) ;
			
		} catch(JedisConnectionException e){
			
			JedisDBIns.getInstance().releaseBrokenJedis(j) ;
			
			e.printStackTrace() ;
		} catch(Exception e){
			
			JedisDBIns.getInstance().release(j) ;
			e.printStackTrace() ;
			
		} finally {
			
			MySQLDBIns.getInstance().release(conn) ;
			
		}
		
		return rst ;
	}
	
	public static void main(String[] args) {
		
//		System.out.println(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
		
		System.out.println(UserJFBAccountCacher.getInstance().reload());
		
	}
}
