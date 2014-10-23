package cn.youhui.manager;

import cn.youhui.common.Config;
import cn.youhui.utils.NetManager;

/**
 * 两百人推荐 结果统计
 * @author lijun
 * @since 2014-4-19
 */
public class RecomModelStat {

	
	public static String VISIT_APP = "1";       //app启动
	
	public static String VISIT_TAG = "2";       //浏览标签
	
	public static String TRADE = "3";           //购买订单
	
	public static String VISIT_ITEM = "4";      //浏览商品
	
	
	public static void addStat(String uid, String type){
		addStat(uid, type, "");
	}
	
	public static void addStat(String uid, String type, String tag){
		addStat(uid, type, tag, "");
	}

	public static void addStat(String uid, String type, String tag, String message){
		String params = "uid=" + uid + "&action=" + type + "&message=" + message + "&tag=" + tag;
		try {
			NetManager.getInstance().getContent(Config.RecomModelUrl +"?"+ params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
