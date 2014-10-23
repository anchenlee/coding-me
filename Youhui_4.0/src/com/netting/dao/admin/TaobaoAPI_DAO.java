package com.netting.dao.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.netting.action.admin.Admin_Tag_Item_Manager_Action;
import com.netting.conf.SysConf;
import com.netting.taobao.api.util.TaobaoRequestUtil;
import com.netting.util.NetManager;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.TaobaokeItem;
import com.taobao.api.domain.TbkItem;
import com.taobao.api.domain.TbkShop;
import com.taobao.api.request.TaobaokeMobileItemsConvertRequest;
import com.taobao.api.request.TaobaokeRebateAuthorizeGetRequest;
import com.taobao.api.request.TbkItemsDetailGetRequest;
import com.taobao.api.request.TbkItemsGetRequest;
import com.taobao.api.request.TbkMobileShopsConvertRequest;
import com.taobao.api.response.TaobaokeMobileItemsConvertResponse;
import com.taobao.api.response.TaobaokeRebateAuthorizeGetResponse;
import com.taobao.api.response.TbkItemsDetailGetResponse;
import com.taobao.api.response.TbkItemsGetResponse;
import com.taobao.api.response.TbkMobileShopsConvertResponse;


/**
 * TAOBAO数据接口的数据操作
 * @author YAOJIANBO
 *
 */
public class TaobaoAPI_DAO 
{
	
	private static long pageSiz = 15;
	public static long totalResults = 0;
	public static long totalCoupon = 0;
	
	/**
	 * 初始化日志
	 */
	private static Log logger = LogFactory.getLog(TaobaoAPI_DAO.class);
	
	/**
	 * 淘宝接口访问客户端连接实例
	 * private static TaobaoClient client = new DefaultTaobaoClient(SysConf.taobao_url, SysConf.taobao_appkey, SysConf.taobao_secret);
	 */
	
	/**
	 * 访问淘宝接口获取商品
	 * @param itemId
	 * @return
	 */
	public static TaobaokeItem getItem(String itemId) 
	{
		TbkItemsDetailGetRequest req = new TbkItemsDetailGetRequest();
		req.setFields("num_iid,title,price,pic_url");
		req.setNumIids(itemId);
		
		TaobaokeItem ite = null;
		try 
		{
			TaobaoClient client = new DefaultTaobaoClient(SysConf.taobao_url, SysConf.taobao_appkey, SysConf.taobao_secret);
			TbkItemsDetailGetResponse response = client.execute(req);
			List<TbkItem> items = response.getTbkItems();
			
			if(null != items && items.size() > 0)
			{
				TbkItem tbkItem = items.get(0);
				boolean flag = isFanliGoods(tbkItem.getNumIid(),tbkItem.getSellerId());
				if(!flag) 
				{
					// System.out.println("无返利商品：" + tbkItem.getNumIid());
					return null;
				}
				ite = new TaobaokeItem();
				
				ite.setNumIid(tbkItem.getNumIid());
				ite.setTitle(tbkItem.getTitle());
				ite.setPrice(tbkItem.getPrice());
				ite.setPicUrl(tbkItem.getPicUrl()); 
				ite.setClickUrl("http://a.m.taobao.com/i" + tbkItem.getNumIid() + ".htm");
			}
			else 
			{
				// System.out.println("下架或不存在商品：" + itemId);
				ite = null;
			}
		} 
		catch (ApiException e) 
		{
			logger.error("TaobaoAPI_DAO.getItem", e);
			return null;
		}
		return ite;
	}
	
	/**
	 * 访问淘宝接口获取商品(未使用SDK)
	 * @param itemId
	 * @return
	 */
	public static TaobaokeItem getItemWithOutSDK(String itemId) 
	{
		TaobaokeItem ite = null;
		
		List<TbkItem> items = TaobaoRequestUtil.getTbkItems(itemId);
		if (null != items && items.size() > 0)
		{
			TbkItem tbkItem = items.get(0);
			
			ite = new TaobaokeItem();
			
			ite.setNumIid(tbkItem.getNumIid());
			ite.setTitle(tbkItem.getTitle().replaceAll("\"", "").replaceAll("'", ""));
			ite.setPrice(tbkItem.getPrice());
			ite.setPicUrl(tbkItem.getPicUrl()); 
			ite.setClickUrl("http://a.m.taobao.com/i" + tbkItem.getNumIid() + ".htm");
		}
		else 
		{
			ite = null;
		}
		
		return ite;
	}
	
	/**
	 * 访问淘宝接口批量获取商品
	 * @param itemIds
	 * @return
	 */
	public static ArrayList<TaobaokeItem> getItemList(String itemIds) 
	{
		ArrayList<TaobaokeItem> resultList = new ArrayList<TaobaokeItem>();
		
		TbkItemsDetailGetRequest req = new TbkItemsDetailGetRequest();
		req.setFields("num_iid,title,price,pic_url,seller_id");
		req.setNumIids(itemIds);
		
		try 
		{
			TaobaoClient client = new DefaultTaobaoClient(SysConf.taobao_url, SysConf.taobao_appkey, SysConf.taobao_secret);
			TbkItemsDetailGetResponse response = client.execute(req);
			
			Admin_Tag_Item_DAO.insertAddItemLogs(itemIds, "713", "tbkbody"+response.getBody() ,0);
			List<TbkItem> items = response.getTbkItems();
			
			if (null != items && items.size() > 0)
			{
				for (TbkItem item : items)
				{
					if(item != null)
					{
						TaobaokeItem ite = new TaobaokeItem();
						
						ite.setNumIid(item.getNumIid());
						ite.setTitle(item.getTitle().replaceAll("\"", "").replaceAll("'", ""));
						ite.setPrice(item.getPrice());
						ite.setPicUrl(item.getPicUrl()); 
						ite.setClickUrl("http://a.m.taobao.com/i"+item.getNumIid()+".htm");
						
						boolean flag = isFanliGoods(item.getNumIid(),item.getSellerId());
						if(flag) 
						{
							resultList.add(ite);
						}
						else
						{
							continue;
						}
					}
				}
			}
		} 
		catch (ApiException e) 
		{
			Admin_Tag_Item_DAO.insertAddItemLogs(itemIds, "713", "tbksdk error = "+e.getMessage() ,0);
			logger.error("TaobaoAPI_DAO.getItemList error,itemsID:" + itemIds, e);
			return null;
		}
		
		return resultList;
	}
	
	/**
	 * 访问淘宝接口批量获取商品(未使用SDK)
	 * @param itemIds
	 * @return
	 */
	public static ArrayList<TaobaokeItem> getItemListWithOutSDK(String itemIds) 
	{
		ArrayList<TaobaokeItem> resultList = new ArrayList<TaobaokeItem>();
		
		List<TbkItem> items = TaobaoRequestUtil.getTbkItems(itemIds);
			
		if (null != items && items.size() > 0)
		{
			for (TbkItem item : items)
			{
				if (item != null)
				{
					TaobaokeItem ite = new TaobaokeItem();
					
					ite.setNumIid(item.getNumIid());
					ite.setTitle(item.getTitle().replaceAll("\"", "").replaceAll("'", ""));
					ite.setPrice(item.getPrice());
					ite.setPicUrl(item.getPicUrl()); 
					ite.setClickUrl("http://a.m.taobao.com/i"+item.getNumIid()+".htm");
					
					resultList.add(ite);
				}
			}
		}
		
		return resultList;
	}
	
	public static TaobaokeItem searchCommItem(String numIid)
	{     
		TaobaokeMobileItemsConvertRequest req=new TaobaokeMobileItemsConvertRequest();
		req.setFields("num_iid,click_url,iid,commission,commission_rate,commission_num,commission_volume");
		req.setNumIids(numIid);
		TaobaokeMobileItemsConvertResponse  response = null;
		try 
		{
			TaobaoClient client = new DefaultTaobaoClient(SysConf.taobao_url, SysConf.taobao_appkey, SysConf.taobao_secret);
			response = client.execute(req);
		} 
		catch (ApiException e) 
		{
			logger.error("TaobaoAPI_DAO.searchCommItem error", e);
		}
		List<TaobaokeItem> list = response.getTaobaokeItems();
		if (list != null && list.size() == 1)
		{
			list.get(0).setClickUrl(list.get(0).getClickUrl() + "&sche=suishou");
			return list.get(0);
		}
		else
		{
			return null;
		}
	}
	
	public static List<TaobaokeItem> searchCommItemList(String numIid)
	{     
		TaobaokeMobileItemsConvertRequest req=new TaobaokeMobileItemsConvertRequest();
		req.setFields("num_iid,click_url,iid,commission,commission_rate,commission_num,commission_volume");
		req.setNumIids(numIid);
		TaobaokeMobileItemsConvertResponse  response = null;
		try 
		{
			TaobaoClient client = new DefaultTaobaoClient(SysConf.taobao_url, SysConf.taobao_appkey, SysConf.taobao_secret);
			response = client.execute(req);
		} 
		catch (ApiException e)
		{
			logger.error("TaobaoAPI_DAO.searchCommItem error", e);
		}
		List<TaobaokeItem> list = response.getTaobaokeItems();
		if(list != null)
		{
			return list;
		}
		else
		{
			return null;
		}
	}
	
	
	/**
	 * 获取商品的价格
	 * @param item_id
	 * @return
	 */
	public static String getPromoPrice(String item_id) 
	{
		String promoPrice = null;
		
		String content = NetManager.getContent("http://fetch.b17.cn/price?itemids=" + item_id);
		try 
		{
			if (content != null && !content.equals(""))
			{
				JSONObject jso = new JSONObject(content);
				if (jso.has(item_id + "")) 
				{
					Double d = jso.getDouble(item_id + "");
					if (d > 0)
					{
						promoPrice = d + "";
					}
				}
			}
		} 
		catch (JSONException e) 
		{
			logger.error("TaobaoAPI_DAO.getPromoPrice", e);
		}
		
		return promoPrice;
	}
	
	/**
	 * 获取商品的分类
	 * @param item_id
	 * @return
	 */
	public static String getCatID(String item_id) 
	{
		String catID = "";
		
		String content = NetManager.getContent("http://fetch.b17.cn/catid?itemid=" + item_id+"&baoyou=baoyou");
		try 
		{
			if (content != null && !content.equals(""))
			{
				JSONObject jso = new JSONObject(content);
				if (jso.has("catid")) 
				{
					catID = jso.getString("catid");
				}
				if (jso.has("baoyou")) 
				{
					catID = catID +","+ jso.getString("baoyou");
				}
			}
		} 
		catch (JSONException e) 
		{
			logger.error("TaobaoAPI_DAO.getCatID", e);
			catID = "";
		}
		
		return catID;
	}
	
	public static List<TaobaokeItem> searchItem(String keyword, String startPrice, String endPrice, String startCredit, String endCredit,
			String startCommissionRate, String endCommissionRate, String pageNo, String pageSize, String sort) 
	{
		List<TaobaokeItem> tbkItemList = new ArrayList<TaobaokeItem>();
		Pattern pattern = Pattern.compile("[0-9]+");
		if (pattern.matcher(keyword).matches() == true)
		{
			TaobaokeItem bean = getItemWithOutSDK(keyword);
			if (bean != null)
			{
				tbkItemList.add(bean);
			}
			
			if (tbkItemList != null && tbkItemList.size() > 0)
			{			
				totalResults = tbkItemList.size();
				if(totalResults % 15 == 0) 
				{
					totalResults = totalResults / 15;
				}
				else 
				{
					totalResults = totalResults / 15 + 1;
				}
			}			
			else
			{
				totalResults = 0;
			}
		} 
		else
		{
			TbkItemsGetRequest req = new TbkItemsGetRequest();
			String fields = "num_iid,title,price,pic_url";
			req.setFields(fields);
			
			if (keyword != null && !"".equals(keyword))
			{
				// 查询关键字
				req.setKeyword(keyword);
			}
			if (startPrice != null && !"".equals(startPrice))
			{
				// 起始价格
				req.setStartPrice(startPrice);
			}
			if (endPrice != null && !"".equals(endPrice))
			{
				// 最高价格
				req.setEndPrice(endPrice);
			}
			if (startCredit != null && !"".equals(startCredit))
			{
				// 起始信用度
				req.setStartCredit(startCredit);
			}
			if (endCredit != null && !"".equals(endCredit))
			{
				// 结束信用度
				req.setEndCredit(endCredit);
			}
			if (startCommissionRate != null && !"".equals(startCommissionRate))
			{
				// 起始佣金比例
				req.setStartCommissionRate(startCommissionRate);
			}
			if (endCommissionRate != null && !"".equals(endCommissionRate))
			{
				// 结束佣金比例
				req.setEndCommissionRate(endCommissionRate);
			}
			
			if (pageNo != null && !"".equals(pageNo))
			{
				req.setPageNo(Long.valueOf(pageNo));
			}
			else
			{
				req.setPageNo((long) 1);
			}
			
			if (pageSize != null && !"".equals(pageSize))
			{
				req.setPageSize(Long.valueOf(pageSize));
			}
			else
			{
				req.setPageSize(pageSiz);
			}
			
			if (sort != null && !"".equals(sort))
			{
				req.setSort(sort);
			}
			req.setIsMobile(true);
			
			TbkItemsGetResponse response = null;
			try 
			{
				TaobaoClient client = new DefaultTaobaoClient(SysConf.taobao_url, SysConf.taobao_appkey, SysConf.taobao_secret);
				response = client.execute(req);
			} 
			catch (ApiException e) 
			{
				logger.error("TaobaoAPI_DAO.searchItem error", e);
			}
			if (response != null)
			{
				List<TbkItem> items = response.getTbkItems();
				
				if (null != items && items.size() > 0)
				{
					for (TbkItem item : items)
					{
						if (item != null)
						{
							TaobaokeItem ite = new TaobaokeItem();
							
							ite.setNumIid(item.getNumIid());
							ite.setTitle(item.getTitle().replaceAll("\"", "").replaceAll("'", ""));
							ite.setPrice(item.getPrice());
							ite.setPicUrl(item.getPicUrl()); 
							ite.setClickUrl("http://a.m.taobao.com/i"+item.getNumIid()+".htm");
							
							tbkItemList.add(ite);
						}
					}
				}
				
				if (response.getTotalResults() != null) 
				{
					totalResults = response.getTotalResults();
					
					if(totalResults % 15 == 0) 
					{
						totalResults = totalResults / 15;
					}
					else 
					{
						totalResults = totalResults / 15 + 1;
					}
				} 
				else
				{
					totalResults = 0;
				}
			}
		}
		return tbkItemList;
	}
	
	public static List<TaobaokeItem> couponItems(String keyword, String startCredit, String endCredit,
			String startCommissionRate, String endCommissionRate,String pageNo, String pageSize, String sort,String start_coupon_rate,String end_coupon_rate) 
	{
		List<TaobaokeItem> tbkItemList = new ArrayList<TaobaokeItem>();
		
		TbkItemsGetRequest req = new TbkItemsGetRequest();
		String fields = "num_iid,title,price,pic_url";
		req.setFields(fields);
		
		if (keyword != null && !"".equals(keyword))
		{
			req.setKeyword(keyword);
		}
		if (startCredit != null && !"".equals(startCredit))
		{
			req.setStartCredit(startCredit);
		}
		if (endCredit != null && !"".equals(endCredit))
		{
			req.setEndCredit(endCredit);
		}
		if (startCommissionRate != null && !"".equals(startCommissionRate))
		{
			req.setStartCommissionRate(startCommissionRate);
		}
		if (endCommissionRate != null && !"".equals(endCommissionRate))
		{
			req.setEndCommissionRate(endCommissionRate);
		}
		if (pageNo != null && !"".equals(pageNo))
		{
			req.setPageNo(Long.valueOf(pageNo));
		}
		else
		{
			req.setPageNo((long) 1);
		}
		
		if (pageSize != null && !"".equals(pageSize))
		{
			req.setPageSize(Long.valueOf(pageSize));
		}
		else
		{
			req.setPageSize(pageSiz);
		}
		if (sort != null && !"".equals(sort))
		{
			req.setSort(sort);
		}
		req.setIsMobile(true);
		
		TbkItemsGetResponse response = null;
		try 
		{
			TaobaoClient client = new DefaultTaobaoClient(SysConf.taobao_url, SysConf.taobao_appkey, SysConf.taobao_secret);
			response = client.execute(req);
		} 
		catch (ApiException e)
		{
			logger.error("TaobaoAPI_DAO.couponItems error", e);
		}
		if(response!=null) 
		{
			List<TbkItem> items = response.getTbkItems();
			
			if (null != items && items.size() > 0)
			{
				for (TbkItem item : items)
				{
					if (item != null)
					{
						TaobaokeItem ite = new TaobaokeItem();
						
						ite.setNumIid(item.getNumIid());
						ite.setTitle(item.getTitle().replaceAll("\"", "").replaceAll("'", ""));
						ite.setPrice(item.getPrice());
						ite.setPicUrl(item.getPicUrl()); 
						ite.setClickUrl("http://a.m.taobao.com/i"+item.getNumIid()+".htm");
						
						tbkItemList.add(ite);
					}
				}
			}
			
			if (response.getTotalResults() != null) 
			{
				totalResults = response.getTotalResults();
				
				if(totalResults % 15 == 0) 
				{
					totalResults = totalResults / 15;
				}
				else 
				{
					totalResults = totalResults / 15 + 1;
				}
			} 
			else
			{
				totalResults = 0;
			}
		}
		return tbkItemList;
	}
	
	public static Map<String, TbkShop> ShopConvert(String sid)
	{
		Map<String, TbkShop> map = new HashMap<String, TbkShop>();
		try
		{
			TaobaoClient client = new DefaultTaobaoClient(SysConf.taobao_url, SysConf.taobao_appkey, SysConf.taobao_secret);
			// TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "12347692","c62deb79ce636f9cbac7c074269ab1d6");
			TbkMobileShopsConvertRequest req=new TbkMobileShopsConvertRequest();
			req.setFields("click_url");
			req.setOuterCode(SysConf.taobao_id);
			// req.setOuterCode("allovekid");
			req.setSids(sid);
			TbkMobileShopsConvertResponse response = client.execute(req);
			List<TbkShop> list = response.getTbkShops();
			if(list != null && list.size()>0)
			{
				for(TbkShop shop: list)
				{
					map.put(sid,shop);
				}
			}
		}
		catch(Exception e)
		{
			logger.error("TaobaoAPI_DAO.ShopConvert error", e);
		}
		return map;
	}
	
	public static boolean isFanliGoods(Long itemid, Long seller_id)
	{
		boolean flag = false;
		
		TaobaoClient client = new DefaultTaobaoClient(SysConf.taobao_url, SysConf.taobao_appkey, SysConf.taobao_secret);
		TaobaokeRebateAuthorizeGetRequest req=new TaobaokeRebateAuthorizeGetRequest();
		// req.setNick("hz0799");
		// req.setSellerId(seller_id);
		req.setNumIid(itemid);
		try 
		{
			TaobaokeRebateAuthorizeGetResponse response = client.execute(req);
			flag = response.getRebate();
		} 
		catch (ApiException e) 
		{
			logger.error("TaobaoAPI_DAO.isFanliGoods error", e);
			return false;
		}
		return flag;
	}
	
	public static List<String> getItemImgFromTaobao(String itemid){
		List<String> list = new ArrayList<String>();
		if(itemid == null || "".equals(itemid)){
			return null;
		}
		try {
			String content = NetManager.getContent("http://item.taobao.com/item.htm?id="+itemid);
			System.out.println("hghgh"+content);
//			String content = NetManager.getContent("www.baidu.com");
			Pattern pattern = Pattern.compile("<ul id=\"J_UlThumb\".*?</ul>");
			Matcher matcher = pattern.matcher(content);
			if(matcher.find()){
				String imgs = matcher.group();
				Pattern pattern1 = Pattern.compile("src=.*?/>");
				Matcher matcher1 = pattern1.matcher(imgs);
				while(matcher1.find()){
					String img = matcher1.group();
					img = img.replaceAll("src=", "").replaceAll("/>", "").replaceAll("\"", "");
					img = Admin_Tag_Item_Manager_Action.getSmallImg(img);
					img = img.replaceAll(" ", "");
					list.add(img);
				}
			}
			if(content == null || "".equals(content)){
				content = NetManager.getContent("http://a.m.tmall.com/i"+itemid+".htm");
				pattern = Pattern.compile("class=\"mt\".*?</table>");
				matcher = pattern.matcher(content);
				if(matcher.find()){
					String imgs = matcher.group();
					Pattern pattern1 = Pattern.compile("src=\".*?\"");
					Matcher matcher1 = pattern1.matcher(imgs);
					while(matcher1.find()){
						String img = matcher1.group();
						img = img.replaceAll("src=", "").replaceAll("\"", "");
						img = Admin_Tag_Item_Manager_Action.getSmallImg(img);
						img = img.replaceAll(" ", "");
						list.add(img);
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return list;
		}
		return list;
	}
	
	public static void main(String[] args) throws ApiException
	{
//		Pattern pattern = Pattern.compile("[0-9]+");
//		System.out.println(pattern.matcher("123456,46123,7788995").matches());
//		
		// System.out.println(getItem("35248221264").getPrice());
		
//		TbkItemsDetailGetRequest req = new TbkItemsDetailGetRequest();
//		req.setFields("num_iid,title,price,pic_url,shop_url,seller_id");
//		req.setNumIids("5560677208");
//		
//		try 
//		{
//			TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "12467944", "42e91539c2dbd060b0ecf58a09dc8356");
//			TbkItemsDetailGetResponse response = client.execute(req);
//			System.out.println(response.getBody());
//		} 
//		catch (ApiException e) 
//		{
//			logger.error("TaobaoAPI_DAO.getItemList error", e);
//		}
//		
//		System.out.println(isFanliGoods(5560677208L,null));
//		System.out.println(getItemList("38698361721").get(0).getTitle());
		getItemImgFromTaobao("21242767460");
		String content;
		try {
			content = NetManager.getContentGBK("http://detail.etao.com/21242767460.htm");
			System.out.println(content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(response.getBody());
		// System.out.println(searchCommItem("4359111383,35462757745,22302223366").getCommission());
	}
	
}
