package cn.youhui.cache.dao;

import com.google.gson.Gson;

import cn.youhui.bean.SuperCouponBean;
import cn.youhui.platform.config.param;
import cn.youhui.platform.jedis.JedisHashManager;

public class SuperCouponCacher {
	private static final String cacheKey = param.SUPER_COUPON;
	private static SuperCouponCacher instance=null;
	public static SuperCouponCacher getInstance(){
		if(instance==null){
			instance=new SuperCouponCacher();
		}
		return instance;
	}
	private SuperCouponCacher(){}
	
	public void del(String itemid){
		new JedisHashManager(cacheKey).delete(itemid);
	}
	
	public void insertInfo(String id,SuperCouponBean scb ){
		Gson gson = new Gson();
		new JedisHashManager(cacheKey).add(id, gson.toJson(scb ,SuperCouponBean.class));
	}
	
	public SuperCouponBean getInfo(String id){
		SuperCouponBean scb=null;
		String superDiscountStr = new JedisHashManager(cacheKey).get(id);
		if (superDiscountStr==null || superDiscountStr.equals("")) {
			return null;
		}
		scb=new Gson().fromJson(superDiscountStr, SuperCouponBean.class);
		return scb;
	}
}
