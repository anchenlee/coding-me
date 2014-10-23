package cn.youhui.ramdata;

import cn.youhui.manager.JedisHashManager;

public class RankLockCacher {
	private static RankLockCacher cacher = new RankLockCacher();
	private static final String pre_key = "LockCacher_Rank";
	private static final String key = "update_status";
	private static final String locked = "locked";
	private static final String unlock = "unlock";
	JedisHashManager jhm = new JedisHashManager(pre_key);
	public static RankLockCacher getInstance(){
		return cacher;
	}
	
	private RankLockCacher(){
		this.destoryRefrush();
	}
	/**
	 * 检测排名列表是否在刷新
	 * */
	public boolean isRefrush() {
		if(jhm.get(key).equals(locked))
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	/**
	 *  标志排名列表正在刷新
	 * */
	public boolean runRefrush() {
		long i = jhm.add(key, locked);
		boolean flag = false;
		flag = i>0?true:false;
		return flag;
	}
	/**
	 * 删除刷新状态
	 * */
	public boolean destoryRefrush() {
		long i = jhm.add(key, unlock);
		boolean flag = false;
		flag = i>0?true:false;
		return flag;
	}
	
}
