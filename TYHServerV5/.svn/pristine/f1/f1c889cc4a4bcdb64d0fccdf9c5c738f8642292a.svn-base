package cn.youhui.manager;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.json.JSONException;
import org.json.JSONObject;

import sun.misc.BASE64Decoder;

import cn.youhui.bean.TeJiaGoodsBean;
import cn.youhui.common.Config;
import cn.youhui.utils.Base64;
import cn.youhui.utils.NetManager;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.TbkItem;
import com.taobao.api.domain.User;
import com.taobao.api.request.TaobaokeItemsGetRequest;
import com.taobao.api.request.TaobaokeMobileItemsGetRequest;
import com.taobao.api.request.TaobaokeMobileShopsGetRequest;
import com.taobao.api.request.TaobaokeRebateAuthorizeGetRequest;
import com.taobao.api.request.TbkItemsDetailGetRequest;
import com.taobao.api.request.UserBuyerGetRequest;
import com.taobao.api.response.TaobaokeItemsGetResponse;
import com.taobao.api.response.TaobaokeMobileItemsGetResponse;
import com.taobao.api.response.TaobaokeMobileShopsGetResponse;
import com.taobao.api.response.TaobaokeRebateAuthorizeGetResponse;
import com.taobao.api.response.TbkItemsDetailGetResponse;
import com.taobao.api.response.UserBuyerGetResponse;

/**
 * @category 
 * @author leejun
 * @since 2012-9-27
 */
public class TaobaoManager {
	private static TaobaoClient client = new DefaultTaobaoClient(Config.Fanli_url,Config.Fanli_appkey, Config.Fanli_secret);
	
	public static TeJiaGoodsBean getItem(String itemId){
		if(itemId == null || "".equals(itemId)){
			return null;
		}
		TeJiaGoodsBean bean = null;
		TaobaoClient client=new DefaultTaobaoClient(Config.Fanli_url, Config.Fanli_appkey, Config.Fanli_secret);
		TbkItemsDetailGetRequest req=new TbkItemsDetailGetRequest();
		req.setFields("num_iid,seller_id,nick,title,price,volume,pic_url,item_url,shop_url");
		req.setNumIids(itemId);
		TbkItem item = null;
		try{
			TbkItemsDetailGetResponse response = client.execute(req);
			if(response.getTbkItems() != null && response.getTbkItems().size() > 0){
				item = response.getTbkItems().get(0);
			}
		} catch (ApiException e){
			e.printStackTrace();
			return bean;
		}
		if(item != null){
			bean = new TeJiaGoodsBean();
			
			bean.setItem_id(itemId);
			bean.setPic_url(item.getPicUrl());
			bean.setTitle(item.getTitle());
			bean.setPrice_low(item.getPrice());
			bean.setPrice_high(item.getPrice());
			bean.setClickURL("http://a.m.taobao.com/i"+item.getNumIid()+".htm");
			bean.setCommission("0");
			bean.setCommission_rate("0");
		}
		return bean;
	}
	
	public static TeJiaGoodsBean getItem2(String itemId){
		if(itemId == null || "".equals(itemId)){
			return null;
		}
		TeJiaGoodsBean bean = null;
		TaobaoClient client=new DefaultTaobaoClient(Config.Fanli_url, Config.Fanli_appkey, Config.Fanli_secret);
		TbkItemsDetailGetRequest req=new TbkItemsDetailGetRequest();
		req.setFields("num_iid,seller_id,nick,title,price,volume,pic_url,item_url,shop_url");
		req.setNumIids(itemId);
		TbkItem item = null;
		try{
			TbkItemsDetailGetResponse response = client.execute(req);
			if(response.getTbkItems() != null && response.getTbkItems().size() > 0){
				item = response.getTbkItems().get(0);
			}
		} catch (ApiException e){
			e.printStackTrace();
			return bean;
		}
		if(item != null){
			bean = new TeJiaGoodsBean();
			
			bean.setItem_id(itemId);
			bean.setPic_url(item.getPicUrl());
			bean.setTitle(item.getTitle());
			bean.setPrice_low(item.getPrice());
			bean.setPrice_high(item.getPrice());
			bean.setClickURL("http://a.m.taobao.com/i"+item.getNumIid()+".htm");
			bean.setCommission("0");
			bean.setCommission_rate("0");
		}else{
			try {
				String content = NetManager.getInstance().getContent("http://fetch.b17.cn/item?itemid="+itemId);
				if(content!=null&&!"".equals(content)){
					JsonParser jp=new JsonParser();
					JsonObject jo=jp.parse(content).getAsJsonObject();
					bean = new TeJiaGoodsBean();
					bean.setItem_id(itemId);
					if(jo.has("pic_url")){
						bean.setPic_url(jo.get("pic_url").getAsString());
					}else{
						bean.setPic_url("");
					}
					if(jo.has("title")){
						bean.setTitle(jo.get("title").getAsString());
					}else{
						bean.setTitle("");
					}
					if(jo.has("orgin_price")){
						bean.setPrice_high(jo.get("orgin_price").getAsString());
					}else{
						bean.setPrice_high("");
					}
					if(jo.has("price")){
						bean.setPrice_low(jo.get("price").getAsString());
					}else{
						bean.setPrice_low(bean.getPrice_high());
					}
					bean.setClickURL("http://a.m.taobao.com/i"+itemId+".htm");
					bean.setCommission("0");
					bean.setCommission_rate("0");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		return bean;
	}
	
	/**
	 * 鍗栧鏄惁鍚屾剰杩斿埄
	 * @return
	 */
	private static boolean isAgreeFanli(String itemId){
		if(itemId == null || "".equals(itemId)){
			return false;
		}
		TaobaoClient client=new DefaultTaobaoClient(Config.Fanli_url, Config.Fanli_appkey, Config.Fanli_secret);
		TaobaokeRebateAuthorizeGetRequest req=new TaobaokeRebateAuthorizeGetRequest();
		try {
			req.setNumIid(Long.parseLong(itemId));
			TaobaokeRebateAuthorizeGetResponse response = client.execute(req);
			return response.getRebate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 鍟嗗搧鏄惁鏀寔杩斿埄
	 * @param itemId
	 * @return
	 */
	public static boolean isSupportFanli(String itemId){
		boolean flag = false;
		if(getItem(itemId) != null){
			flag = isAgreeFanli(itemId);
		}
		return flag;
	}
	
	public static String searchItem(String keyword, String startPrice,
			String endPrice, String startCredit, String endCredit,
			String pageNo, String pageSize, String sort) {
		TaobaokeItemsGetRequest req = new TaobaokeItemsGetRequest();
		String fields = "num_iid,seller_id,title,pic_url,price,click_url,seller_credit_score,volume ";
		req.setFields(fields);
		if (keyword != null && !"".equals(keyword))
			req.setKeyword(keyword);
		if (startPrice != null && !"".equals(startPrice))
			req.setStartPrice(startPrice);
		if (endPrice != null && !"".equals(endPrice))
			req.setEndPrice(endPrice);
		if (startCredit != null && !"".equals(startCredit))
			req.setStartCredit(startCredit);
		if (endCredit != null && !"".equals(endCredit))
			req.setEndCredit(endCredit);
		if (pageNo != null && !"".equals(pageNo))
			req.setPageNo(Long.valueOf(pageNo));
		else
			req.setPageNo((long) 1);
		if (pageSize != null && !"".equals(pageSize))
			req.setPageSize(Long.valueOf(pageSize));
		else
			req.setPageSize(30l);
		if (sort != null && !"".equals(sort))
			req.setSort(sort);
		
		req.setIsMobile(true);
		TaobaokeItemsGetResponse response = null;
		try {
			response = client.execute(req);

			return response.getBody();
		} catch (ApiException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static String searchItemBf(String keyword, String mallItem,String startPrice,
			String endPrice, String startCredit, String endCredit,
			String pageNo, String pageSize, String sort) {
		TaobaokeMobileItemsGetRequest req = new TaobaokeMobileItemsGetRequest();
		String fields = "num_iid,seller_id,title,pic_url,price,click_url,seller_credit_score,volume,commission,commission_rate";
		req.setFields(fields);
		
		if (keyword != null && !"".equals(keyword)){
			req.setKeyword(keyword);
		}
		if(mallItem != null && !"".equals(mallItem)){
			req.setMallItem(mallItem);
		}
		if (startPrice != null && !"".equals(startPrice))
			req.setStartPrice(startPrice);
		if (endPrice != null && !"".equals(endPrice))
			req.setEndPrice(endPrice);
		if (startCredit != null && !"".equals(startCredit))
			req.setStartCredit(startCredit);
		if (endCredit != null && !"".equals(endCredit))
			req.setEndCredit(endCredit);
		if (pageNo != null && !"".equals(pageNo))
			req.setPageNo(Long.valueOf(pageNo));
		else
			req.setPageNo((long) 1);
		if (pageSize != null && !"".equals(pageSize))
			req.setPageSize(Long.valueOf(pageSize));
		else
			req.setPageSize(30l);
		if (sort != null && !"".equals(sort))
			req.setSort(sort);
		
		TaobaokeMobileItemsGetResponse response = null;
		try {
			response = client.execute(req);
			
			return response.getBody();
		} catch (ApiException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String searchShopBf(String keyword,boolean onlyMall, String startCredit, String endCredit,
			String pageNo, String pageSize) {
		TaobaokeMobileShopsGetRequest req = new TaobaokeMobileShopsGetRequest();
		String fields = "user_id,click_url,shop_title, commission_rate, seller_credit, shop_type, auction_count, total_auction";
		req.setFields(fields);
		if (keyword != null && !"".equals(keyword)){
			req.setKeyword(keyword);
		}
		req.setOnlyMall(onlyMall);
		if (startCredit != null && !"".equals(startCredit))
			req.setStartCredit(startCredit);
		if (endCredit != null && !"".equals(endCredit))
			req.setEndCredit(endCredit);
		if (pageNo != null && !"".equals(pageNo))
			req.setPageNo(Long.valueOf(pageNo));
		else
			req.setPageNo((long) 1);
		if (pageSize != null && !"".equals(pageSize))
			req.setPageSize(Long.valueOf(pageSize));
		else
			req.setPageSize(30l);
		
		TaobaokeMobileShopsGetResponse response = null;
		try {
			response = client.execute(req);
			return response.getBody();
		} catch (ApiException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String searchItem(String keyword, String mallItem,String startPrice,
			String endPrice, String startCredit, String endCredit,
			String pageNo, String pageSize, String sort, String format) {
		
		    if(!"json".equals(format) && !"xml".equals(format)){
		    	format = "xml";
		    }
			String content = "";
			String url = Config.Taobao_url;
			url = url + "?";
			
			Map<String, String> treeMap = new TreeMap<String, String>();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = df.format(new Date());
			treeMap.put("timestamp", date);
			treeMap.put("fields","num_iid,seller_id,nick,title,price,volume,pic_url,item_url,shop_ur");
			treeMap.put("method","taobao.tbk.items.get");
			if (keyword != null && !"".equals(keyword)){
				treeMap.put("keyword", keyword);
			}
			if(mallItem != null && !"".equals(mallItem)){
				treeMap.put("mall_item", mallItem);
			}
			if (startPrice != null && !"".equals(startPrice)){
				treeMap.put("start_price", startPrice);
			}
			if (endPrice != null && !"".equals(endPrice)){
				treeMap.put("end_price", endPrice);
			}
			if (startCredit != null && !"".equals(startCredit)){
				treeMap.put("start_credit", startCredit);
			}
			if (endCredit != null && !"".equals(endCredit)){
				treeMap.put("end_credit", endCredit);
			}
			if (pageNo != null && !"".equals(pageNo)){
				treeMap.put("page_no", pageNo);
			}else{
				treeMap.put("page_no", "1");
			}
			if (pageSize != null && !"".equals(pageSize)){
				treeMap.put("page_size", pageSize);
			}else{
				treeMap.put("page_size", "30");
			}
			if (sort != null && !"".equals(sort)){
				treeMap.put("sort", sort);
			}
			treeMap.put("format", format);
			treeMap.put("v", "2.0");
			treeMap.put("sign_method", "hmac");
			treeMap.put("app_key", Config.Fanli_appkey);
			try{
				String signStr = signWithHmac(treeMap, Config.Fanli_secret);//绛惧悕	
				treeMap.put("sign", signStr);
				Iterator<String> iter = treeMap.keySet().iterator();
				while (iter.hasNext()){
					String name = (String) iter.next();
					url = url+name+"="+URLEncoder.encode(treeMap.get(name), "UTF-8")+"&";
				}
				
				content = NetManager.getInstance().getContent(url);

			}catch (Exception e){
				e.printStackTrace();
			}
			return content;
		}
	
	public static String searchShop(String keyword,boolean onlyMall, String startCredit, String endCredit,
			String pageNo, String pageSize, String format){
		    
		    if(!"json".equals(format) && !"xml".equals(format)){
		    	format = "xml";
		    }
			String content = "";
			String url = Config.Taobao_url;
			url = url + "?";
			
			Map<String, String> treeMap = new TreeMap<String, String>();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = df.format(new Date());
			treeMap.put("timestamp", date);
			treeMap.put("fields","user_id,seller_nick,shop_title,pic_url,shop_url");
			treeMap.put("method","taobao.tbk.shops.get");
			if (keyword != null && !"".equals(keyword)){
				treeMap.put("keyword", keyword);
			}
			treeMap.put("only_mall", onlyMall +"");
			if (startCredit != null && !"".equals(startCredit)){
				treeMap.put("start_credit", startCredit);
			}
			if (endCredit != null && !"".equals(endCredit)){
				treeMap.put("end_credit", endCredit); 
			}
			if (pageNo != null && !"".equals(pageNo)){
				treeMap.put("page_no", pageNo);
			}else{
				treeMap.put("page_no", "1");
			}
			if (pageSize != null && !"".equals(pageSize)){
				treeMap.put("page_size", pageSize);
			}else{
				treeMap.put("page_size", "30");
			}
			treeMap.put("format", format);
			treeMap.put("v", "2.0");
			treeMap.put("sign_method", "hmac");
			treeMap.put("app_key", Config.Fanli_appkey);
			try{
				String signStr = signWithHmac(treeMap, Config.Fanli_secret);//绛惧悕	
				treeMap.put("sign", signStr);
				Iterator<String> iter = treeMap.keySet().iterator();
				while (iter.hasNext()){
					String name = (String) iter.next();
					url = url+name+"="+URLEncoder.encode(treeMap.get(name), "UTF-8")+"&";
				}
				
				content = NetManager.getInstance().getContent(url);
			}catch (Exception e){
				e.printStackTrace();
			}
			return content;
		}
	
	public static String searchShopf(String keyword,boolean onlyMall, String startCredit, String endCredit,
			String pageNo, String pageSize, String outerCode, String format){
		    if(!"json".equals(format) && !"xml".equals(format)){
		    	format = "xml";
		    }
		 
			String content = "";
			String url = Config.Taobao_url;
			url = url + "?";
			
			Map<String, String> treeMap = new TreeMap<String, String>();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = df.format(new Date());
			treeMap.put("timestamp", date);
			treeMap.put("fields","user_id,click_url,shop_title, commission_rate, seller_credit, shop_type, auction_count, total_auction");
			treeMap.put("method","taobao.taobaoke.mobile.shops.get");
			if (keyword != null && !"".equals(keyword)){
				treeMap.put("keyword", keyword);
			}
			if (outerCode != null && !"".equals(outerCode)){
				treeMap.put("outer_code", outerCode);
			}
			treeMap.put("only_mall", onlyMall +"");
			if (startCredit != null && !"".equals(startCredit)){
				treeMap.put("start_credit", startCredit);
			}
			if (endCredit != null && !"".equals(endCredit)){
				treeMap.put("end_credit", endCredit); 
			}
			if (pageNo != null && !"".equals(pageNo)){
				treeMap.put("page_no", pageNo);
			}else{
				treeMap.put("page_no", "1");
			}
			if (pageSize != null && !"".equals(pageSize)){
				treeMap.put("page_size", pageSize);
			}else{
				treeMap.put("page_size", "30");
			}
			treeMap.put("format", format);
			treeMap.put("v", "2.0");
			treeMap.put("sign_method", "hmac");
			treeMap.put("app_key", Config.Fanli_appkey);
			try{
				String signStr = signWithHmac(treeMap, Config.Fanli_secret);//绛惧悕	
				treeMap.put("sign", signStr);
				Iterator<String> iter = treeMap.keySet().iterator();
				while (iter.hasNext()){
					String name = (String) iter.next();
					url = url+name+"="+URLEncoder.encode(treeMap.get(name), "UTF-8")+"&";
				}
				
				content = NetManager.getInstance().getContent(url);
			}catch (Exception e){
				e.printStackTrace();
			}
			return content;
		}
	
	public static String searchItemf(String keyword, String mallItem,String startPrice,
			String endPrice, String startCredit, String endCredit,
			String pageNo, String pageSize, String sort, String outerCode, String format) {
		
		    if(!"json".equals(format) && !"xml".equals(format)){
		    	format = "xml";
		    }
		 
			String content = "";
			String url = Config.Taobao_url;
			url = url + "?";
			
			Map<String, String> treeMap = new TreeMap<String, String>();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = df.format(new Date());
			treeMap.put("timestamp", date);
			treeMap.put("fields","num_iid,seller_id,title,pic_url,price,click_url,seller_credit_score,volume,commission,commission_rate");
			treeMap.put("method","taobao.taobaoke.mobile.items.get");
			if (keyword != null && !"".equals(keyword)){
				treeMap.put("keyword", keyword);
			}
			if (outerCode != null && !"".equals(outerCode)){
				treeMap.put("outer_code", outerCode);
			}
			if(mallItem != null && !"".equals(mallItem)){
				treeMap.put("mall_item", mallItem);
			}
			if (startPrice != null && !"".equals(startPrice)){
				treeMap.put("start_price", startPrice);
			}
			if (endPrice != null && !"".equals(endPrice)){
				treeMap.put("end_price", endPrice);
			}
			if (startCredit != null && !"".equals(startCredit)){
				treeMap.put("start_credit", startCredit);
			}
			if (endCredit != null && !"".equals(endCredit)){
				treeMap.put("end_credit", endCredit);
			}
			if (pageNo != null && !"".equals(pageNo)){
				treeMap.put("page_no", pageNo);
			}else{
				treeMap.put("page_no", "1");
			}
			if (pageSize != null && !"".equals(pageSize)){
				treeMap.put("page_size", pageSize);
			}else{
				treeMap.put("page_size", "30");
			}
			if (sort != null && !"".equals(sort)){
				treeMap.put("sort", sort);
			}
			treeMap.put("format", format);
			treeMap.put("v", "2.0");
			treeMap.put("sign_method", "hmac");
			treeMap.put("app_key", Config.Fanli_appkey);
			try{
				String signStr = signWithHmac(treeMap, Config.Fanli_secret);//绛惧悕	
				treeMap.put("sign", signStr);
				Iterator<String> iter = treeMap.keySet().iterator();
				while (iter.hasNext()){
					String name = (String) iter.next();
					url = url+name+"="+URLEncoder.encode(treeMap.get(name), "UTF-8")+"&";
				}
				
				content = NetManager.getInstance().getContent(url);
			}catch (Exception e){
				e.printStackTrace();
			}
			return content;
		}
	
	
	public static String signWithHmac(Map<String, String> params,
			String secret) throws IOException {
		StringBuffer orgin = getBeforeSign(params, new StringBuffer());
		byte[] bytes = encryptHMAC(orgin.toString(), secret);
		return byte2hex(bytes);

	}	
	
	private static byte[] encryptHMAC(String data, String secret)
			throws IOException {
		byte[] bytes = null;
		try {
			SecretKey secretKey = new SecretKeySpec(secret.getBytes("UTF-8"),
					"HmacMD5");
			Mac mac = Mac.getInstance(secretKey.getAlgorithm());
			mac.init(secretKey);
			bytes = mac.doFinal(data.getBytes("UTF-8"));
		} catch (GeneralSecurityException gse) {
			
		}
		return bytes;
	}

	private static StringBuffer getBeforeSign(Map<String, String> params,
			StringBuffer orgin) {
		if (params == null)
			return null;
		Map<String, String> treeMap = new TreeMap<String, String>();
		treeMap.putAll(params);
		Iterator<String> iter = treeMap.keySet().iterator();
		while (iter.hasNext()) {
			String name = (String) iter.next();
			orgin.append(name).append(params.get(name));
		}
		return orgin;
	}
	
	private static String byte2hex(byte[] b) {
		StringBuffer hs = new StringBuffer();
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs.append("0").append(stmp);
			else
				hs.append(stmp);
		}
		return hs.toString().toUpperCase();
	}
	
	public static String getCatId(String item_id) {
		String catId = null;
		try {
			String content = NetManager.getInstance().getContent("http://fetch.b17.cn/catid?itemid=" + item_id);
			JSONObject jso = new JSONObject(content);
			if (jso.has("catid")){
				catId = jso.getString("catid");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return catId;
	}
	
	/**
	 * 通过登陆获得token获取taobaonick
	 * @param token
	 * @return
	 */
	public static String getNickByToken(String token){
		String nick = null;
		try{
			UserBuyerGetRequest req=new UserBuyerGetRequest();
			req.setFields("nick");
			UserBuyerGetResponse response = client.execute(req , token);
			if(response != null){
			   User user = response.getUser();
			   if(user != null){
				  nick =  user.getNick();
			   }
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return nick;
	}
	
	/**
	 * 取出taobaonick
	 * @param code
	 * @return
	 */
	public static String decodeTBNick(String code) {
		String jcode = code;
		try {
			String code1 = new String(Base64.decode(code), "UTF-8");
			if (code1.indexOf("taobao#") == -1
					|| code1.indexOf("#taobao") == -1) {
				return "-1";
			}
			code1 = code1.replace("taobao#", "").replace("#taobao", "");
			String code2 = new String(Base64.decode(code1), "UTF-8");
			jcode = code2;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jcode;
	}
	
	
	public static int getUserLevel(String taobaoUid) {
		int level = -1;
		try {
		    String content = NetManager.getInstance().getContent("http://tbitem.duapp.com/level?uid=" + taobaoUid);
			JSONObject jso = new JSONObject(content);
			if (jso.has("level")){
				level = jso.getInt("level");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return level;
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
//		System.out.println(searchItem("T", "", "", "", "", "", "", "", "", "xml"));
//		System.out.println(searchShopfXML("f", true, "", "", "", ""));
//		System.out.println(searchShopBf("f", true, "", "", "", ""));
//		System.out.println(getCatId("35517881625"));
	
		System.out.println(isSupportFanli("41072535619"));
		
//		System.out.println(Base64.encode(new String("taobao#" + Base64.encode("李军2324".getBytes()) + "#taobao").getBytes()));
		
//		System.out.println(decodeTBNick("dGFvYmFvIzVwMk81WWFiTWpNeU5BPT0jdGFvYmFv"));
	}
}
