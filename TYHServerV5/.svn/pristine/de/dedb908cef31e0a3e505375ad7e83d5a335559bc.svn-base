package cn.youhui.utils;

import org.json.JSONObject;

public class ItemPriceUtil {
	
	public static double getPromoPrice(String item_id){
		double promoPrice = -1;
		String content = null;
		try {
			content = NetManager.getInstance().send("http://tbitem.duapp.com/price","itemids=" + item_id);
			JSONObject jso = new JSONObject(content);
			if (jso != null && jso.has(item_id)) {
				 promoPrice = jso.getDouble(item_id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return promoPrice;
	}
	
	public static void main(String[] args) {
		System.out.println(ItemPriceUtil.getPromoPrice("15602477627"));
	}
}
