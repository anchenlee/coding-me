package cn.youhui.cache.dao;


import java.util.List;

import com.google.gson.Gson;

import cn.youhui.bean.SecondKill;
import cn.youhui.itemDAO.SecondKillDAO;
import cn.youhui.platform.config.param;
import cn.youhui.platform.jedis.JedisHashManager;

public class secondKillCacher {

//	private static final Logger log = Logger.getLogger(secondKillCacher.class);
	private static final String cacheKey = param.secondKill_TAG;
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
		System.out.println(id+":"+gson.toJson(sk ,SecondKill.class));
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
			_sk.setKillPrice(sk.getKillPrice());
			_sk.setKillNum(sk.getKillNum());
			_sk.setKillStartTimestamp(sk.getKillStartTimestamp());
			_sk.setKillEndTimestamp(sk.getKillEndTimestamp());
			this.addSecondKill(id, _sk);
			flag = true;
		}
		return flag;
	}
	
	public SecondKill getSecondKillById(final String id) {
		SecondKill sk = null;
		String secondKillStr = new JedisHashManager(cacheKey).get(id);
		if (secondKillStr==null || secondKillStr.equals("")) {
			return null;
		}
		System.out.println(secondKillStr);
		sk = new Gson().fromJson(secondKillStr, SecondKill.class);
		System.out.println(sk.getType());
		return sk;
	}
	
	public void reload(){
		List<SecondKill> list=SecondKillDAO.getInstance().getAll();
		for(SecondKill sk:list){
			getInstance().addSecondKill(sk.getType()+sk.getTypeId(), sk);
		}
	}
	
	public static void main(String[] args) {
		getInstance().reload();
	}
	
}
