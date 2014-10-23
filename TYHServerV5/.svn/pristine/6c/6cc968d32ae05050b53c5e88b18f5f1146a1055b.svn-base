package cn.youhui.cacher.dao;

import org.apache.log4j.Logger;
import com.google.gson.Gson;

import cn.youhui.bean.SuperItemTags;
import cn.youhui.common.ParamConfig;
import cn.youhui.manager.JedisHashManager;

public class superItemTagsCacher {

	private static final Logger log = Logger.getLogger(superItemTagsCacher.class);
	private static final String cacheKey = ParamConfig.superItemTags_TAG;
	private static superItemTagsCacher instance = null;
	public superItemTagsCacher(){}
	public static superItemTagsCacher getInstance() {
		if (instance == null){
			instance = new superItemTagsCacher();
		}
		return instance;
	}
	
	public static void main(String[] args) {
		
	}
	
	public void addSuperDiscountById(String id , SuperItemTags sit)
	{
		Gson gson = new Gson();
		new JedisHashManager(cacheKey).add(id, gson.toJson(sit ,SuperItemTags.class));
	}
	
	public void delSuperDiscount(String id)
	{
		new JedisHashManager(cacheKey).delete(id);
	}
	
	
	public SuperItemTags getSuperItemTagsById(final String id) {
		SuperItemTags sd = null;
		String superItemTagsStr = new JedisHashManager(cacheKey).get(id);
		if (superItemTagsStr==null || superItemTagsStr.equals("")) {
			return null;
		}
		sd = new Gson().fromJson(superItemTagsStr, SuperItemTags.class);
		
		return sd;
	}
}
