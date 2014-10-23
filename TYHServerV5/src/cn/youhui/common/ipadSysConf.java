package cn.youhui.common;

import java.util.HashMap;
import java.util.Map;

public class ipadSysConf {

	public static Map<String, String> actionValueToUrlMap = new HashMap<String, String>();
	static
	{
		actionValueToUrlMap.put("loginPage", "LoginPageHD");
		actionValueToUrlMap.put("woDeTaoBaoPage", "WoDeTaoBaoPageHD");
		actionValueToUrlMap.put("juYouHuiPage", "JuYouHuiPageHD");
		actionValueToUrlMap.put("pinPaiHuiPage", "PinPaiHuiPageHD");
		actionValueToUrlMap.put("gengDuoPage", "GengDuoPageHD");
		actionValueToUrlMap.put("ssFenLeiPage", "SSFenLeiPageHD");
		actionValueToUrlMap.put("mainPage", "MainPageHD");
		actionValueToUrlMap.put("mianZePage", "MianZePageHD");
		actionValueToUrlMap.put("yJFKPage", "YJFKPageHD");
		actionValueToUrlMap.put("gYWMPage", "GYWMPageHD");
		actionValueToUrlMap.put("xXZXPage", "XXZXPageHD");
		actionValueToUrlMap.put("bangZhuPage", "BangZhuPageHD");
		actionValueToUrlMap.put("faXianPinPaiPage", "FaXianPinPaiPageHD");
		actionValueToUrlMap.put("liWuPage", "LiWuPageHD");
		actionValueToUrlMap.put("zHSZPage", "ZHSZPageHD");
		actionValueToUrlMap.put("zJPage", "ZJPageHD");
		actionValueToUrlMap.put("jfbHistoryPage", "JFBHistoryPageHD");
		actionValueToUrlMap.put("miBaoSetPage", "MiBaoSetPageHD");
		actionValueToUrlMap.put("miBaoReSetPage", "MiBaoReSetPageHD");
		actionValueToUrlMap.put("mobileForgetPage", "MobileForgetPageHD");
		actionValueToUrlMap.put("mobileRegisterPage", "MobileRegisterPageHD");
		actionValueToUrlMap.put("wSCDYouHui", "WSCDYouHuiHD");
	}
	
	public static Map<String, String> actionTypeToUrlMap = new HashMap<String, String>(); 
	static{
		actionTypeToUrlMap.put("tagStyleWeb", "WebStylePageHD");
		actionTypeToUrlMap.put("tagStyleGrid", "TagStyleGridPageHD");
		actionTypeToUrlMap.put("tagStyleWaterflow", "TagStyleWaterflowPageHD");
		actionTypeToUrlMap.put("tagStyleSale", "TagStyleSalePageHD");
		actionTypeToUrlMap.put("systemWeb", "XiTongWebPageHD");
	}
	

}
