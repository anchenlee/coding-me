package cn.youhui.common;

import java.util.HashMap;
import java.util.Map;

public interface X {
	
	String DOT = "." ;
	
	
	interface CachePrefix {
		
		String AD_AD ="tyh_ad.tyh_ad";
		
		String Shop="youhui.counponsShop";
		String LOCK_MARK = "tyh.lock";
		
		String SALE_TAG = "sale.tag";
		String SALE_DATE2TAG = "sale.date2tag.";
		String SALE_FRESH_SIGN = "sale.fresh_sign";
		String RECOMMEND = "sale.recommend";
		
		String PROF_RECOM_SET = "youhui.prof.recom.items";
		
		String PROF_RECOM_BIG = "youhui.prof.recom.itemsbig";
		
		String APPCONFIG_DATA = "client.appconfig";
		String ANNOUNCEMENT = "client.announcement";
		String SPECIALURL = "client.specialurl";
		String LOADINGAD = "client.loadingad";
		
		
		String APPCONFIG4IPHONE_DATA = "client.appconfig4iphone";
		
		String CLIENTMENU_XMLDATA = "clientmenu.xmldata";
		String CLIENTMENU_VERSION = "clientmenu.version";
		
		String YH_USERS = "cn.youhui.users" ;
		//-------------------------------------------------
		String FAV_ALL_ITEMS = "fav.all_items" ;
		String FAV_ALL_SHOPS = "fav.all_shops" ;
		String FAV_ITEMS_4_USERS = "fav.items4users" ;
		String FAV_USER_2_ITEMS = "fav.user2items" ;
		String FAV_USER_2_SHOPS = "fav.user2shops" ;
		String FAV_ITEMS_DETAIL = "fav.items_detail";
		String FAV_SHOPS_DETAIL = "fav.shops_detail";
		String FAV_UMP_ITEMS_4_USERS= "fav.umpitems4users" ;
		String FAV_UMP_ITEMS = "fav.allumpitems" ;
		String FAV_LOCK = "fav.favlock";
//---------------------------ornate line----------------
		String TAG_ALLINIPAD_TAGS = "tag.allinipad_tags";
		
		String TAG_ALL_TAGS = "tag.all_tags";
		String TAG_TAGS_4_SEX = "tag.tags4sex";
		String TAG_TAGS_4_USER = "tag.tags4user";
		String TAG_ITEMS_4_TAG = "tag.items4tag";
		String TAG_STATS = "tag.stats";
		String TAG_VERSION = "tag.version";
		String TAG_USER_4_VER = "tag.user4ver";
//-------------------------------------------		
		String TAG_USER_TAGS = "tag.usertags";
		String TAG_ALL_TAG = "tag.alltags";
		
		String TAG_TO_TAG = "tag.tag2tag";
		String TAG_TO_ITEM = "tag.tag2item";
		
		String TAG_ID_NAME = "tag.tagid2name";
		
		String TAG_USER_IPAD_TAGS = "tag.usertags.ipad";
		String TAG_TO_TAG_INIPAD = "tag.tag2taginipad";
		
		String BDYH_STORE_MAP = "bdyh.store.map";
		
		String TAG_STATUS = "tag.status";
		
		String IS_ITEMCLICK_CONVERT = "tag.itemclick.isconverted";
//------------------------------------------------------
		String CACHE_STATUS = "cache.status";
//------------------------------------------------------
		String ITEM_CACHE = "youhui.cn.tag.products.";
		
		String USER_VIEW_ITEM = "view.user2item";
		String USER_LIKE_ITEM = "like.user2item";
//------------------------------------------------------
		String EXCHITEM_ALL = "tyh.exchitem.all";
		String DEFALUT_UID = "00000000";
		String DEFALUT_IPAD_UID = "00000001";
		
		String STYLE_TAG_ITEMS_4_TAG = "styletag.items4tag";
		String STYLE_ITEM_CACHE = "youhui.cn.tag.styleproducts.";
		String M_MIX_ITEM_CACHE = "youhui.cn.tag.m_mix_pagetype.";
		String STYLE_CACHE_STATUS = "stylecache.status";
		
		String SMS_RANDOM_NUM = "sms.randomnum.";
		
		String YOUHUI_ACTIVITY = "youhui.activity";
		
		String USER_JFB_ACCOUNT = "youhui.userjfbaccount";
		
//------------------------------------------------------
		
		String CHECK_CODE = "youhui.checkcode";             //验证码
		String SID_2_CHECK_CODE = "youhui.sid2checkcode.";   
		
//---------------------------youhui web----------------		
		String YOUHUI_WEB_AD ="youhui_web.ad";		
		String YOUHUI_WEB_Catagory_AD ="youhui_web.catagory_ad_";		
		String YOUHUI_WEB_Hot_Search ="youhui_web.hot_search_";
		String YOUHUI_WEB_Hot_Recommend_view ="youhui_web.hot_recommend_view";
		
		public static Map<String, String> CatagoryKeyMap = new HashMap<String, String>()
		{
			private static final long serialVersionUID = 6999313051162125069L;
			{
				put("nvzhuang","11");
				put("nvxie","3");
				put("meizhuang","5");
				put("nanren","7");
		   }            
		};
	}
	
	interface JobGroup {
		String GP_FAV_DETECTION = "cn.youhui.v5.favdetection"  ;
		String GP_UMP_DETECTION = "cn.youhui.v5.umpdetection"  ;
	}
}
