package cn.youhui.utils;

import cn.youhui.manager.TaobaoCatManager;
import cn.youhui.manager.TaobaoManager;
import cn.youhui.ramdata.JuHuaSuanCacher;
import cn.youhui.ramdata.TagItemCacher;

public class JfbRateUtil {
	
	public static double getRate(String itemId){
		double rate = 0;
		if(JuHuaSuanCacher.isExistItem(itemId)){       //聚划算商品不支持返利
			return rate;
		}
		rate = TagItemCacher.getInstance().getJfbRate(itemId);
		if(rate == 0){
			rate = getRateByCat(itemId);
		}
		return rate;
	}
	
	private static double getRateByCat(String itemId){
		double rate = 0;
		String catId = TaobaoManager.getCatId(itemId);
		if(catId == null || "".equals(catId)){
			rate = 2;
		}else{
			rate = TaobaoCatManager.getInstance().getRateByCatId(catId);
		}
		return rate;
	}
}
