package cn.youhui.cacher.dao;

import com.google.gson.Gson;

import cn.youhui.bean.SuperCouponBean;
import cn.youhui.common.ParamConfig;
import cn.youhui.manager.JedisHashManager;

public class SuperCouponCacher {
	private static final String cacheKey = ParamConfig.SUPER_COUPON;
	private static SuperCouponCacher instance=null;
	public static SuperCouponCacher getInstance(){
		if(instance==null){
			instance=new SuperCouponCacher();
		}
		return instance;
	}
	private SuperCouponCacher(){}
	
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
