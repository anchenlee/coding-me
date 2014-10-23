package cn.youhui.cache.dao;

import java.util.List;

import com.google.gson.Gson;
import cn.youhui.bean.SuperItemTags;
import cn.youhui.itemDAO.SuperItemTagsDAO;
import cn.youhui.platform.config.param;
import cn.youhui.platform.jedis.JedisHashManager;

public class superItemTagsCacher {

	private static final String cacheKey = param.superItemTags_TAG;
	private static superItemTagsCacher instance = null;
	public superItemTagsCacher(){}
	public static superItemTagsCacher getInstance() {
		if (instance == null){
			instance = new superItemTagsCacher();
		}
		return instance;
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
	
	public void update(SuperItemTags sit){
		String id=sit.getId()+"";
		delSuperDiscount(id);
		addSuperDiscountById(id, sit);
	}
	
	public SuperItemTags getSuperItemTagsById(final String id) {
		SuperItemTags sd = null;
		String superItemTagsStr = new JedisHashManager(cacheKey).get(id);
		if (superItemTagsStr==null || superItemTagsStr.equals("")) {
			return null;
		}
		sd = new Gson().fromJson(superItemTagsStr, SuperItemTags.class);
		System.out.println(sd);
		return sd;
	}
	public void reload(){
		List<SuperItemTags> list=SuperItemTagsDAO.getInstance().getAllTags();
		for(SuperItemTags sit:list){
			getInstance().addSuperDiscountById(sit.getId()+"", sit);
		}
	}
	public static void main(String[] args) {
		getInstance().reload();
	}
}
