package cn.youhui.cache.dao;

import com.google.gson.Gson;

import cn.youhui.bean.SuperDays;
import cn.youhui.itemDAO.SuperDaysDAO;
import cn.youhui.platform.config.param;
import cn.youhui.platform.jedis.JedisHashManager;

public class superDaysCacher {

	private static final String cacheKey = param.superDays_TAG;
	private static superDaysCacher instance = null;
	public superDaysCacher(){}
	public static superDaysCacher getInstance() {
		if (instance == null){
			instance = new superDaysCacher();
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
		SuperDays sds=SuperDaysDAO.getInstance().getInfo(1);
		addSuperDays("1", sds);
		
		SuperDays sds2=SuperDaysDAO.getInstance().getInfo(2);
		addSuperDays("2", sds2);
	}
	public static void main(String[] args) {
		getInstance().reload();
	}
}
