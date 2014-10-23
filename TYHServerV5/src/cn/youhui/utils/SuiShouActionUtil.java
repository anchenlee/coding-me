package cn.youhui.utils;


import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.youhui.bean.Action;
import cn.youhui.common.SysConf;
import cn.youhui.common.ipadSysConf;
import cn.youhui.dao.MySQLDBIns;

/**
 * 新action跳转 转换
 * @author lijun
 * @since 2014-02-20
 */
public class SuiShouActionUtil {
	
	public interface OldActionType{
		String ITEM = "tagStyleItem";        //商品详情
		String TagStyleGrid = "tagStyleGrid";   //标签网格样式
	}
	
	public static String ipadUrlHttp = "suishouhd://app.youhui.cn/";
	public static String urlHttp = "suishou://app.youhui.cn/";
	
	public static String getSuiShouActionUrl(String platform, String actionType,String... actionValueAr){
		if("ipad".equals(platform) || "apad".equals(platform)){
			return getSuiShouIpadActionUrl(actionType, actionValueAr);
		}else{
			return getSuiShouActionUrlforPhone(actionType, actionValueAr);
		}
	}
	
	public static String getSuiShouActionUrlforPhone(String actionType,String... actionValueAr){
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
		if(actionType != null && (actionType.equals(OldActionType.ITEM) || "goodDetail".equals(actionType))){
			url = urlHttp +  "TagStyleItemPage?item_id="+actionValue+"";
		}
		else if(actionType != null && actionType.equals("searchShopId") || "shopDetail".equals(actionType)){
			url  = urlHttp +  "DianPuXiangQingPage?shop_id="+actionValue+"";
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
//	public static void main(String[] args) {
//			String str=getSuiShouActionUrlforPhone("systemWeb", "http://detail.tmall.com/item.htm?spm=a1z10.3.w4011-3150979377.123.s1Q450&id=25700080631&rn=d21ce6643553a90f120ff778c095e7c0");
//			try {
//				System.out.println(URLEncoder.encode("http://detail.tmall.com/item.htm?spm=a1z10.3.w4011-3150979377.123.s1Q450&id=25700080631&rn=d21ce6643553a90f120ff778c095e7c0", "UTF-8"));
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	}
	public static String getSuiShouIpadActionUrl(String actionType,String... actionValueAr) {
		String url = "";
		//系统页面
		if (actionType != null && actionType.equals("tagStyleAppPage")) {
			url = ipadUrlHttp + ipadSysConf.actionValueToUrlMap.get(actionValueAr[0]);
		}
		// 系统浏览器 actionValueAr：url
		else	if (actionType != null && actionType.equals("systemWeb")) {  
			if (actionValueAr != null && !"".equals(actionValueAr)) {
				String val = actionValueAr[0];
				String valarr [] = val.split(",");
				if (valarr.length != 0) {
					url = valarr[0];
				}
			}
			url = ipadUrlHttp + ipadSysConf.actionTypeToUrlMap.get(actionType)
					+ "?url=" + url ;
		} 
		// web页面 actionValueAr：url
		else	if (actionType != null && actionType.equals("tagStyleWeb")) {
			String urlStr = "";
			if (actionValueAr != null && !"".equals(actionValueAr)) {
				String val = actionValueAr[0];
				String valarr [] = val.split(",");
				if (valarr.length != 0) {
					urlStr = valarr[0];
				}
			url = ipadUrlHttp + ipadSysConf.actionTypeToUrlMap.get(actionType)
					+ "?url=" + urlStr;
			}
		}
		//网络，特惠，瀑布流 actionValueAr：tagId，tagTitle
		else if ("tagStyleGrid".equals(actionType) || "tagStyleWaterflow".equals(actionType) || "tagStyleSale".equals(actionType)) {
			String tagId = "";
			String tagTitle = "";
			if (actionValueAr != null && !"".equals(actionValueAr)) {
				String val = actionValueAr[0];
				String valarr [] = val.split(",");
				if (valarr.length == 1) {
					tagId = valarr[0];
					url = ipadUrlHttp+ipadSysConf.actionTypeToUrlMap.get(actionType)+  "?tag_id="+tagId;
				}else {
					tagId = valarr[0];
					tagTitle = ecodeParam(valarr[1]);
					url = ipadUrlHttp+ipadSysConf.actionTypeToUrlMap.get(actionType)+  "?tag_id="+tagId + "&tag_title=" + tagTitle;
				}
			}
			
		}
		//商品关键字,店铺关键字搜索  actionValueAr：keyword
		else if ("searchKeyword".equals(actionType) || "searchShopId".equals(actionType)){
			String keyword = "";
			String title = "";
			String type = "";
			if ("searchKeyword".equals(actionType)) {
				type = "searchItem";
			}else{
				type = "searchShop";
			}
				
			if (actionValueAr != null && !"".equals(actionValueAr)) {
				if (actionValueAr.length > 1) {
					title = ecodeParam(actionValueAr[1]);
					keyword = ecodeParam(actionValueAr[0]);
				}
			url = ipadUrlHttp+"SSJieGuoPageHD?search_type="+type+"&keyword="+keyword + "&title=" + title;
			}
		}
		//店铺详情  actionValueAr：shopId
		else if ("shopDetail".equals(actionType) || "searchShopId".equals(actionType)) {
			String shopId = "";
			if (actionValueAr != null && !"".equals(actionValueAr)) {
				String val = actionValueAr[0];
				String valarr [] = val.split(",");
				if (valarr.length != 0) {
					shopId = valarr[0];
				}
			url = ipadUrlHttp+"DPXQPageHD?shop_id="+shopId;
			}
		}
		//商品详情  actionValueAr：itemId
		else if ("goodDetail".equals(actionType) || "tagStyleItem".equals(actionType)) {
			String itemId = "";
			if (actionValueAr != null && !"".equals(actionValueAr)) {
				String val = actionValueAr[0];
				String valarr [] = val.split(",");
				if (valarr.length != 0) {
					itemId = valarr[0];
				}
			url = ipadUrlHttp+"TagStyleItemPageHD?item_id="+itemId;
			}
		}
		//优惠券详情  actionValueAr：couponId
		else if ("couponDetail".equals(actionType)) {
			String couponId = "";
			if (actionValueAr != null && !"".equals(actionValueAr)) {
				String val = actionValueAr[0];
				String valarr [] = val.split(",");
				if (valarr.length != 0) {
					couponId = valarr[0];
				}
			url = ipadUrlHttp+"YHQXQPageHD?coupon_id="+couponId;
			}
		}
		//商品分享  actionValueAr：clickUrl，imgUrl，content，title，itemId
		else if ("goodShare".equals(actionType)) {
			String clickUrl = "";
			String imgUrl = "";
			String content = "";
			String title = "";
			String itemId = "";
			if (actionValueAr != null && !"".equals(actionValueAr)) {
				String val = actionValueAr[0];
				String valarr [] = val.split(",");
				if (valarr.length > 3) {
					clickUrl = valarr[0];
					imgUrl = valarr[1];
					content = ecodeParam(valarr[2]);
					title = ecodeParam(valarr[3]);
					itemId = valarr[4];
				}
			url = ipadUrlHttp+"YouHuiSharePageHD?share_type=product_type&click_url=" + clickUrl + "&img_url=" + imgUrl + "&content=" + content + "&title=" + title + "&item_id=" + itemId ;
			}
		}
		//活动分享  actionValueAr：shareEncryptionCode
		else if ("goodShare".equals(actionType)) {
			String shareEncryptionCode = "";
			if (actionValueAr != null && !"".equals(actionValueAr)) {
				String val = actionValueAr[0];
				String valarr [] = val.split(",");
				if (valarr.length != 0) {
					shareEncryptionCode = ecodeParam(valarr[0]);
				}
			url = ipadUrlHttp+"YouHuiSharePageHD?share_type=activity_type&share_encryption_code=" + shareEncryptionCode;
			}
		}
		//品牌下优惠券列表  actionValueAr：brandId
		else if ("goodShare".equals(actionType)) {
			String brandId = "";
			if (actionValueAr != null && !"".equals(actionValueAr)) {
				String val = actionValueAr[0];
				String valarr [] = val.split(",");
				if (valarr.length != 0) {
					brandId = valarr[0];
				}
			url = ipadUrlHttp+"PPYHQPageHD?brand_id=" + brandId;
			}
		}
		//我的优惠详情月视图  actionValueAr：acJoinId
		else if ("goodShare".equals(actionType)) {
			String acJoinId = "";
			if (actionValueAr != null && !"".equals(actionValueAr)) {
				String val = actionValueAr[0];
				String valarr [] = val.split(",");
				if (valarr.length != 0) {
					acJoinId = valarr[0];
				}
			url = ipadUrlHttp+"WDYHMonthPageHD?ac_join_id=" + acJoinId;
			}
		}
		return url;
}
 
	public static String ecodeParam(String param) {
		String str = "";
		try {
			str = URLEncoder.encode(param, "UTF-8");
			str = str.replaceAll("\\+", "%20");
		} catch (UnsupportedEncodingException e) {
			return str;
		}
		return str;
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
	
	
	public static String getSuiShouActionUrl(String platform,Action action){
		String url = "";
		if(action != null){
			url = getSuiShouActionUrl(platform,action.getActionType(), action.getActionValue());
		}
		return url;
	}
	
	public static String getTagNameByTagId(String tagId){
		String tagName = "";
		String sql = "SELECT keyword FROM youhui_datamining.m_discount_keywords where id=?;";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, tagId);
			rs = ps.executeQuery();
			if(rs.next()){
				tagName = rs.getString("keyword");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return tagName;
	}
	
	public static void main(String[] args) {
		http://s.click.taobao.com/t?e=m%3D2%26s%3DQBaIkAM%2Bs6kcQipKwQzePOeEDrYVVa64pRe%2F8jaAHci5VBFTL4hn2WfXO8%2FQez1dPkWZNjOK2CM34KhLtLMDNLEa1KCqhbejyvFEf6YNQLMOWF0LKWMqDAM8uAFvESP2DFWhufKxid53r%2F4iRfcFqyGFCzYOOqAQ
		System.out.println(getSuishouWebUrl("iphone", "http://s.click.taobao.com/t?e=m%3D2%26s%3DQBaIkAM%2Bs6kcQipKwQzePOeEDrYVVa64pRe%2F8jaAHci5VBFTL4hn2WfXO8%2FQez1dPkWZNjOK2CM34KhLtLMDNLEa1KCqhbejyvFEf6YNQLMOWF0LKWMqDAM8uAFvESP2DFWhufKxid53r%2F4iRfcFqyGFCzYOOqAQ"));
	}
	
	public static String getSuishouWebUrl(String platform,String url){
		if(url == null){
			return "";
		}
		try {
			url = URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if ("apad".equals(platform) || "ipad".equals(platform)) {
	    	return ipadUrlHttp + "WebStylePageHD" +  "?url="+url+"";
		}else{
			return urlHttp + "WebStylePage" +  "?url="+url+"";
		}
	}
	
	
	public static String getSpecialWebUrl(String platform,String url){
		if(url == null){
			return "";
		}
		try {
			url = URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	    if ("apad".equals(platform) || "ipad".equals(platform)) {
	    	return ipadUrlHttp + "WebStylePageSpecialHD" +  "?url="+url+"";
		}else{
			return urlHttp + "WebStylePageSpecial" +  "?url="+url+"";
		}
	}
	
}
