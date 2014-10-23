package cn.youhui.cacher.dao;

import com.google.gson.Gson;

import cn.youhui.bean.AntiAttack;
import cn.youhui.common.ParamConfig;
import cn.youhui.manager.JedisKeyManager;

public class AntiAttackCacher {

	private static AntiAttackCacher instance=null;
	public static AntiAttackCacher getInstance(){
		if(instance==null){
			instance=new AntiAttackCacher();
		}
		return instance;
	}
	
	public void addAntiAttack(String ip,AntiAttack ak){
		JedisKeyManager jm=new JedisKeyManager(ip);
		
		jm.set(new Gson().toJson(ak, AntiAttack.class));
		jm.expire(5);
	}
	
	public String getAntiAttack(String ip){
		JedisKeyManager jm=new JedisKeyManager(ip);
		return jm.get();
	}
	
}
