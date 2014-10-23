package cn.youhui.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import cn.youhui.bean.Action;
import cn.youhui.common.ipadSysConf;

/**
 * IpadAction跳转 转换
 * 
 * @author
 * @since
 */
public class SuiShouIpadActionUtil {

	public static String ipadUrlHttp = "suishouhd://app.youhui.cn/";

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
		//商品id,店铺id搜索  actionValueAr：keyword
		else if ("searchItem".equals(actionType) || "searchShop".equals(actionType)) {
			String keyword = "";
			if (actionValueAr != null && !"".equals(actionValueAr)) {
				String val = actionValueAr[0];
				String valarr [] = val.split(",");
				if (valarr.length != 0) {
					keyword = ecodeParam(valarr[1]);
				}
			url = ipadUrlHttp+"SSJieGuoPageHD?search_type="+actionType+"&keyword="+keyword;
			}
		}
		//店铺详情  actionValueAr：shopId
		else if ("shopDetail".equals(actionType)) {
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
		else if ("goodDetail".equals(actionType)) {
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
	
	public static String getSuiShouIpadActionUrl(Action action){
		String url = "";
		if(action != null){
			url = getSuiShouIpadActionUrl(action.getActionType(), action.getActionValue());
		}
		return url;
	}
	
	public static void main(String[] args) {
		String ss = getSuiShouIpadActionUrl("goodShare","www.baidu.com","http://12.jpg","123","test","2313");
		System.out.println(ss);
	}
}
