package cn.youhui.common;

import java.util.HashMap;
import java.util.Map;

public class SysConf {

	public static Map<String, String> actionValueToUrlMap = new HashMap<String, String>();
	static
	{
		actionValueToUrlMap.put("wodetaobao", "WoDeTaoBaoPage");
		actionValueToUrlMap.put("ss", "SouSuoPage");
		actionValueToUrlMap.put("jhs", "JuHuaSuanPage");
		actionValueToUrlMap.put("tttj", "TianTianTeJiaPage");
		actionValueToUrlMap.put("yjfk", "YiJianFanKuiPage");
		actionValueToUrlMap.put("smbj", "SaoMaBiJiaPage");
		actionValueToUrlMap.put("prd", "JiangJiaTiXingPage");
		actionValueToUrlMap.put("xiaoxi", "XiaoXiZhongXinPage");
		actionValueToUrlMap.put("tehui", "TeHuiPage");
		actionValueToUrlMap.put("gift", "LiWuPage");
		actionValueToUrlMap.put("locallife", "BenDiShengHuoPage");
		actionValueToUrlMap.put("gywm", "GuanYuWoMenPage");
		actionValueToUrlMap.put("tixianguanli", "TiQuJFBPage");
		actionValueToUrlMap.put("SCPage", "SCPage");
	}
	
	public static Map<String, String> actionTypeToUrlMap = new HashMap<String, String>(); 
	static{
		actionTypeToUrlMap.put("tagStyleWeb", "WebStylePage");
		actionTypeToUrlMap.put("tagStyleSingle", "TagStyleSinglePage");
		actionTypeToUrlMap.put("tagStyleList", "TagStyleListPage");
		actionTypeToUrlMap.put("tagStyleGrid", "TagStyleGridPage");
		actionTypeToUrlMap.put("tagStyleMix", "TagStyleMixPage");
		actionTypeToUrlMap.put("tagStyleCategory", "TagStyleCategoryPage");
		actionTypeToUrlMap.put("tagStyleWaterflow", "TagStyleWaterflowThreePage");
		actionTypeToUrlMap.put("tagStyleSale", "TagStyleSalePage");
		actionTypeToUrlMap.put("tagStyleItem", "TagStyleItemPage");
		actionTypeToUrlMap.put("searchShopId", "DianPuXiangQingPage");
		actionTypeToUrlMap.put("tagStyleSale", "TagStyleSalePage");
		actionTypeToUrlMap.put("tagStyleWaterFlowDouble", "TagStyleWaterflowDoublePage");
		actionTypeToUrlMap.put("systemWeb", "XiTongWebPage");
		actionTypeToUrlMap.put("pinpaiYouhui", "PinPaiYouHuiPage");
	}
	

}
