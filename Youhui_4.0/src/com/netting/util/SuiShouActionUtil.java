package com.netting.util;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.netting.bean.Action;
import com.netting.conf.SysConf;


/**
 * 新action跳转 转换
 * @author lijun
 * @since 2014-02-20
 */
public class SuiShouActionUtil {
	
	public static String urlHttp = "suishou://app.youhui.cn/";
	
	public static String getSuiShouActionUrl(String actionType,String... actionValueAr){
		String url = "";
		String actionValue = "";
		if(actionValueAr != null && !"".equals(actionValueAr)){
			actionValue = actionValueAr[0];
			try {
				if(actionType != null && (actionType.equals("tagStyleWeb") )){
					
				}else{					
					actionValue = URLEncoder.encode(actionValue,"UTF-8");
					actionValue = actionValue.replaceAll("\\+", "%20");
				}
			} catch (UnsupportedEncodingException e) {
			}
		}
		String title = "";
		if(actionValueAr != null && actionValueAr.length >1){
			title = actionValueAr[1];
		}
		if(title != null && !"".equals(title)){
			try {
				title = URLEncoder.encode(title,"UTF-8");
				title = title.replaceAll("\\+", "%20");
			} catch (UnsupportedEncodingException e) {
				
			}
		}
//		String actionValue = actionValueAr[0];
		if(actionType != null && actionType.equals("tagStyleItem")){
			url = urlHttp+SysConf.actionTypeToUrlMap.get(actionType)+  "?item_id="+actionValue+"";
		}
		else if(actionType != null && actionType.equals("searchShopId")){
			url  = urlHttp+SysConf.actionTypeToUrlMap.get(actionType)+  "?shop_id="+actionValue+"";
		}
		else if(actionType != null && actionType.equals("tagStyleAppPage") ){				
			url = urlHttp+SysConf.actionValueToUrlMap.get(actionValue);
		}else if(actionType != null && (actionType.equals("tagStyleWeb") )){//网页			
			url = actionValue;
//			url = urlHttp+SysConf.actionTypeToUrlMap.get(actionType)+  "?url="+actionValue+"";
		}else if(actionType != null && (actionType.equals("tagStyleMix")|| actionType.equals("tagStyleSingle")  ||actionType.equals("tagStyleList") 
				|| actionType.equals("tagStyleGrid") || actionType.equals("tagStyleCategory") || actionType.equals("tagStyleSale") ||  actionType.equals("tagStyleWaterflow") 
				||  actionType.equals("tagStyleWaterFlowDouble") || actionType.equals("tagStyleSale") )){
			url = urlHttp+SysConf.actionTypeToUrlMap.get(actionType)+  "?tag_id="+actionValue+"&tag_title="+title;
		}else if(actionType != null && actionType.equals("YouHuiJuanShangJia")){
			url = urlHttp+"YouHuiJuanShangJia"+"?shop_id="+actionValue;
		}else if(actionType != null && actionType.equals("YouHuiJuanXiangQing")){
			url = urlHttp+"YouHuiJuanXiangQing"+"?id="+actionValue;
		}else if(actionType != null &&  actionType.equals("systemWeb")){//系统浏览器
			url = urlHttp+SysConf.actionTypeToUrlMap.get(actionType)+  "?url="+actionValue+"";
		}
		else if(actionType != null && actionType.equals("searchKeyword")){
			String searchType = "searchItem";
			try {
				searchType = URLEncoder.encode(searchType, "UTF-8");
				searchType = searchType.replaceAll("\\+", "%20");
			} catch (UnsupportedEncodingException e) {
				searchType = "searchItem";
			}
			url = urlHttp+"SouSuoJieGuoPage"+"?search_type="+searchType+"&keyword="+actionValue+"&title="+title;
		}else if(actionType != null && actionType.equals("pinpaiYouhui")){
			String brandId = "";
			String brandName = "";
			if(actionValueAr != null && !"".equals(actionValueAr)){
				String brand = actionValueAr[0];
				String brandAr[] = brand.split(",");
				if(brandAr.length>1){					
					brandId = brandAr[0];
					brandName = brandAr[1];
					try {
						brandId = URLEncoder.encode(brandId, "UTF-8");
						brandId = brandId.replaceAll("\\+", "%20");
						brandName = URLEncoder.encode(brandName, "UTF-8");
						brandName = brandName.replaceAll("\\+", "%20");
					} catch (UnsupportedEncodingException e) {
						
					}
				}
			}
			url = urlHttp+SysConf.actionTypeToUrlMap.get(actionType)+  "?brand_id="+brandId+"&brand_name="+brandName;
		}
		return url;
	}
	
	public static String ecodeUrl(String url){
		String ecoUrl = url;
		try {
			ecoUrl =  URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return url;
		}
		return ecoUrl;
	}
	
	public static String getTitle(String type,String id){
		String title = "";
		
		return title;
	}
	
	
	public static String getSuiShouActionUrl(Action action){
		String url = "";
		if(action != null){
			url = getSuiShouActionUrl(action.getActionType(), action.getActionValue());
		}
		return url;
	}
	
	
	
	public static void main(String[] args) {
		String[] a = new String[]{"jh","kj"};
		System.out.println(getSuiShouActionUrl("tagStyleMix", a));
		String s = "gfgf汉字 grgf";
		try {
			String eco = (URLEncoder.encode(s,"UTF-8"));
			System.out.println(eco);
			String e = (eco.replaceAll("\\+", "%20"));
			System.out.println(e);
			System.out.println(URLDecoder.decode(e,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(TagCacher.getInstance().getTag("569"));
//		System.out.println(getTagNameByTagId("402"));
	}
}
