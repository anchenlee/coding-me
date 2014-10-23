package cn.youhui.utils;

import java.util.HashMap;
import java.util.Map;

import cn.youhui.bean.TeJiaGoodsBean;

public class UrlConvert {

	public static Map<String, String> actionMap = new HashMap<String, String>();
	static{
		actionMap.put("tagStyleItem","GoodsDetailBridgeActivity");
	}
	
	public static String urlConvert(String type,TeJiaGoodsBean item){
		String url = "";
		String activity = actionMap.get(type);
		if(activity != null && !activity.equals("")){
			if("GoodsDetailBridgeActivity".equals(activity)){				
				url = "suishou://app.youhui.cn/"+activity+"?"+itemBeanToString(item);
			}
		}
		
		return url;
	}
	
	public static String itemBeanToString(TeJiaGoodsBean item){
		String parameters = "";
		if(item != null){
			parameters += "clickurl="+item.getClickURL();
			parameters += "&isInMyFav=";
			parameters += "&title="+item.getTitle();
			parameters += "&FromValue=";
			parameters += "&FromChannel=";
			parameters += "&jfb_rate="+item.getRate();
			parameters += "&price_original="+item.getPrice_high();
			parameters += "&pid="+item.getItem_id();
			parameters += "&iconurl="+item.getPic_url();
			parameters += "&price_discount="+item.getDiscount();
		}
		return parameters;
	}
}
