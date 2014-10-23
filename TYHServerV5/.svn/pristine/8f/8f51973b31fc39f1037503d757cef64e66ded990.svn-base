package cn.youhui.cacher.dao;

import org.apache.log4j.Logger;
import com.google.gson.Gson;

import cn.youhui.bean.SecondKill;
import cn.youhui.common.ParamConfig;
import cn.youhui.manager.JedisHashManager;
import cn.youhui.manager.JedisKeyManager;

public class secondKillCacher {

	private static final Logger log = Logger.getLogger(secondKillCacher.class);
	private static final String cacheKey = ParamConfig.secondKill_TAG;
	private static final String cacheKey2 = "secondkill.tag.";
	private static secondKillCacher instance = null;
	public secondKillCacher(){}
	public static secondKillCacher getInstance() {
		if (instance == null){
			instance = new secondKillCacher();
		}
		return instance;
	}
	
	
	public void addSecondKill(String id , SecondKill sk)
	{
		Gson gson = new Gson();
		new JedisHashManager(cacheKey).add(id, gson.toJson(sk ,SecondKill.class));
	}
	
	public void delSecondKillById(String id)
	{
		new JedisHashManager(cacheKey).delete(id);
	}
	
	public boolean updateSecondKillById(String id,SecondKill sk)
	{
		boolean flag = false;
		SecondKill  _sk = this.getSecondKillById(id);
		if (_sk != null) {
			this.addSecondKill(id, sk);
			flag = true;
		}
		return flag;
	}
	
	public SecondKill getSecondKillById(final String id) {
		SecondKill sk = null;
		String secondKillStr = new JedisHashManager(cacheKey).get(id);
		System.out.println(id);
		if (secondKillStr==null || secondKillStr.equals("")) {
			return null;
		}
		sk = new Gson().fromJson(secondKillStr, SecondKill.class);
		System.out.println("===============================================================================>"+sk.toString());
		return sk;
	}

	
	public int getSecondKillPrice(String couponid) {
		JedisKeyManager manager = new JedisKeyManager(cacheKey2+"price."+couponid);
		try {
			return Integer.parseInt(manager.get());
		} catch (Exception e) {
			return -1;
		}
		
	}
	
}
