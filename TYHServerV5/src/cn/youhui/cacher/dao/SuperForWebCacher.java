package cn.youhui.cacher.dao;

import com.google.gson.Gson;

import cn.youhui.bean.SuperForWebBean;
import cn.youhui.common.ParamConfig;
import cn.youhui.manager.JedisHashManager;

public class SuperForWebCacher {

	
	private static final String cacheKey = ParamConfig.SUPER_WEB;
	
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
	
}
