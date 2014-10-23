package cn.youhui.cacher.dao;

import cn.youhui.bean.SuperDays;
import cn.youhui.common.ParamConfig;
import cn.youhui.dao.SuperDaysDAO;
import cn.youhui.manager.JedisHashManager;

import com.google.gson.Gson;

public class SuperDaysCacher {

	private static final String cacheKey = ParamConfig.superDays_TAG;
	private static SuperDaysCacher instance = null;
	public SuperDaysCacher(){}
	public static SuperDaysCacher getInstance() {
		if (instance == null){
			instance = new SuperDaysCacher();
		}
		return instance;
	}
	
	public SuperDays getSuperDays(String id){
		SuperDays sd=null;
		Gson gson = new Gson();
		String superdays=new JedisHashManager(cacheKey).get(id);
		if(superdays==null||"".equals(superdays)){
			return null;
		}
		sd=gson.fromJson(superdays, SuperDays.class);
		return sd;
	}
	public void addSuperDays(String id , SuperDays sd){
		Gson gson = new Gson();
		new JedisHashManager(cacheKey).add(id, gson.toJson(sd ,SuperDays.class));
	}
	
	public void delSuperDayById(String id){
		new JedisHashManager(cacheKey).delete(id);
	}
	public void reload(){
		SuperDays sds=SuperDaysDAO.getInstance().getInfo("iphone",1);
		addSuperDays("1", sds);
		
		SuperDays sds2=SuperDaysDAO.getInstance().getInfo("iphone",2);
		addSuperDays("2", sds2);
	}
}
