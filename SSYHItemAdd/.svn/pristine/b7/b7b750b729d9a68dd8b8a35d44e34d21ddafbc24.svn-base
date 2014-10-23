package cn.youhui.cache.dao;

import java.util.List;

import com.google.gson.Gson;

import cn.youhui.bean.SuperForWebBean;
import cn.youhui.itemDAO.SuperForWebDAO;
import cn.youhui.platform.config.param;
import cn.youhui.platform.jedis.JedisHashManager;

public class SuperForWebCacher {

	
	private static final String cacheKey = param.SUPER_WEB;
	
	public static void addSuperForWeb(String id , SuperForWebBean swb)
	{
		Gson gson = new Gson();
		new JedisHashManager(cacheKey).add(id, gson.toJson(swb ,SuperForWebBean.class));
	}
	
	public static boolean updateSuperForWeb(String id,SuperForWebBean swb)
	{
		boolean flag = false;
		SuperForWebBean  _sd = getSuperForWebById(id);
		if (_sd != null) {
			addSuperForWeb(id, swb);
			flag = true;
		}
		return flag;
	}
	
	public static SuperForWebBean getSuperForWebById(final String id) {
		SuperForWebBean swb = null;
		String superForWebStr = new JedisHashManager(cacheKey).get(id);
		if (superForWebStr==null || superForWebStr.equals("")) {
			return null;
		}
		swb = new Gson().fromJson(superForWebStr, SuperForWebBean.class);
		return swb;
	}
	public static String getSuperForWebById2(final String id) {
		SuperForWebBean swb = null;
		String superForWebStr = new JedisHashManager(cacheKey).get(id);
		if (superForWebStr==null || superForWebStr.equals("")) {
			return null;
		}
		return superForWebStr;
	}
	
	public static void reload(){
		List<SuperForWebBean> list=SuperForWebDAO.getInstance().getAll();
		for(SuperForWebBean swb:list){
			addSuperForWeb(swb.getSuperId()+"", swb);
		}
	}
	public static void main(String[] args) {
		reload();
	}
}
