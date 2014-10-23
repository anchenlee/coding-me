package cn.youhui.ramdata;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.youhui.bean.SearchItem;
import cn.youhui.bean.SearchKeywords;
import cn.youhui.bean.TeJiaGoodsBean;
import cn.youhui.manager.GetSearchKeywordManager;
import cn.youhui.manager.JedisSortedSetManager;
import cn.youhui.manager.TaobaoManager;

/**
 * 搜索关键词商品
 * @author belonghu
 *
 */
public class Keyword2ItemCacher {

	private static String cacheKey = "keyword.keyword2item";
	
	private static int pageCount = 30;
	
	private static String sellerIdFanli = "189355143";	//返利的sellerid
	
	private static String sellerIdNot = "000";//不返利的sellerid
	
	/**
	 * 获取添加的搜索关键词商品
	 * @param keywordid
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public static List<TeJiaGoodsBean> getItemsByKeywordid(String keywordid, int page, int pageSize)
	{
		List<TeJiaGoodsBean> ret = new ArrayList<TeJiaGoodsBean>();
		Set<String> idset = new JedisSortedSetManager(cacheKey + keywordid).
				zrange((page - 1)*pageSize, (page * pageSize - 1)); 
		
		if(idset != null && idset.size() > 0)
		{
			for(String id : idset)
			{
				TeJiaGoodsBean bean = TagItemCacher.getInstance().getProduct(id);
				if(bean.getRate() !=0){
					bean.setSellerNick(sellerIdFanli);
				}else{
					bean.setSellerNick(sellerIdNot);
				}
				System.out.println(bean.getRate()+"  jfb rante");
				if(bean != null)
				{
					ret.add(bean);
				}
			}
			return ret;
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * 第一页添加的数据不够时，同时请求获取数据
	 * @param keywordid
	 * @return
	 */
	public static List<TeJiaGoodsBean> getFirstPage(String keywordName,String keywordid,int pageSize){
		List<TeJiaGoodsBean> list = new ArrayList<TeJiaGoodsBean>();
		list = getItemsByKeywordid(keywordid, 1, pageSize);
		if(list == null){
			list = new ArrayList<TeJiaGoodsBean>();
		}
		String content = TaobaoManager.searchItem(keywordName, "", "", "", "", "", "1", pageSize+"", "", "json");
		try {
			JSONObject jso = new JSONObject(content);
			JSONObject tbk_items_get_response = jso.getJSONObject("tbk_items_get_response");
			JSONObject tbk_items = tbk_items_get_response.getJSONObject("tbk_items");
			JSONArray tbk_item = tbk_items.getJSONArray("tbk_item");
			for(int i = 0; i< tbk_item.length() ; i++){
				TeJiaGoodsBean bean = new TeJiaGoodsBean();
				JSONObject itemJso = tbk_item.getJSONObject(i);
				bean.setItem_id(itemJso.getString("num_iid"));
				bean.setPic_url(itemJso.getString("pic_url"));
				bean.setPrice_low(itemJso.getString("price"));
				bean.setTitle(itemJso.getString("title"));
				bean.setSellerNick(itemJso.getString("seller_id"));
				bean.setCommission(itemJso.getString("volume"));
				list.add(bean);
			}
		} catch (JSONException e) {
			return null;
		}
		return list;
	}
	
	/**
	 * 获取添加的搜索关键词商品数量
	 * @param keywordid
	 * @return
	 */
	public static long getSize(String keywordid){
		long size = new JedisSortedSetManager(cacheKey + keywordid).size();
		return size;
		
	}
	
	
	public static String getSearch(String keywordName,String pageNo,String format){
		SearchKeywords searchKeyword = GetSearchKeywordManager.getKeywordIdByKeyword(keywordName);
		if(searchKeyword != null){
			String keywordId = searchKeyword.getId();
			int size = (int)Keyword2ItemCacher.getSize(keywordId);
			System.out.println("hghg "+size);
			if(size == 0){
				return "";
			}
			int count = size /pageCount;
			int curPage = 1;
			try {				
				curPage = Integer.parseInt(pageNo);
			} catch (Exception e) {
				curPage = 1;
			}
			List<TeJiaGoodsBean> list = new ArrayList<TeJiaGoodsBean>();
			if(count == 0 && curPage ==1){	//如果redis第一页有数据而且不够时
				list = getFirstPage(keywordName, keywordId, pageCount);
			}else if(curPage == count && size % pageCount != 0){	//redis 最后一页不足的数据直接添加到前一页	
				list = getItemsByKeywordid(keywordId, curPage, pageCount);
				list.addAll(getItemsByKeywordid(keywordId, curPage+1, pageCount));
			}else if(count < curPage){	//如果页数超出了redis数据页数
				return "";
			}else{				
				list = getItemsByKeywordid(keywordId, curPage, pageCount);
			}
			if(format.equals("json")){
				return searchItemToJson(list,keywordName);
			}else{
				return searchItemToxml(list,keywordName);
			}
		}
		return "";
	}
	
	/**
	 * 转换json格式
	 * @param list
	 * @return
	 */
	public static String searchItemToJson(List<TeJiaGoodsBean> list,String keyword){
		long size = getSearchNum(keyword, "", "", "", "", "", "", "", "", "json");
		StringBuffer sb = new StringBuffer();
		sb.append("{\"tbk_items_get_response\":{")
		.append("\"tbk_items\":{")
		.append("\"tbk_item\": [");
		for(TeJiaGoodsBean bean : list){
			SearchItem searchItem = TejiaGoodsToSearchItem(bean);
			sb.append(searchItem.toJson()+",");
		}
		if(list != null && list.size() >0){
			sb.deleteCharAt(sb.length()-1);
		}
		sb.append(" ]},\"total_results\": "+size+"}}");
		
		return sb.toString();
	}
	
	/**
	 * 转换XML格式
	 * @param list
	 * @return
	 */
	public static String searchItemToxml(List<TeJiaGoodsBean> list,String keyword){
		long size = getSearchNum(keyword, "", "", "", "", "", "", "", "", "json");
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?><tbk_items_get_response><tbk_items list=\"true\">");
		
		for(TeJiaGoodsBean bean : list){
			SearchItem searchItem = TejiaGoodsToSearchItem(bean);
			
			sb.append(searchItem.toXML());
		}
		sb.append("</tbk_items><total_results>"+size+"</total_results></tbk_items_get_response>");
		return sb.toString();
		
	}
	
	public static long getSearchNum(String keyword, String mallItem,String startPrice,String endPrice, String startCredit, String endCredit,String pageNo, String pageSize, String sort, String format){
		String content = TaobaoManager.searchItem(keyword, mallItem, startPrice, endPrice, startCredit, endCredit, pageNo, pageSize, sort, "json");
		try {
			JSONObject jso = new JSONObject(content);
			JSONObject tbk_items_get_response = jso.getJSONObject("tbk_items_get_response");
			long size = tbk_items_get_response.getLong("total_results");
			return size;
		} catch (JSONException e) {
			return 0;
		}
	}
	
	/**
	 * 商品格式转换成搜索关键词商品
	 * @param bean
	 * @return
	 */
	public static SearchItem TejiaGoodsToSearchItem(TeJiaGoodsBean bean){
		SearchItem searchItem = new SearchItem();
		searchItem.setItemUrl("http://item.taobao.com/item.htm?id="+bean.getItem_id());
		searchItem.setNick("");
		searchItem.setNumiid(bean.getItem_id());
		searchItem.setPicUrl(bean.getPic_url());
		searchItem.setPrice(bean.getPrice_low());
		searchItem.setSellerId(bean.getSellerNick());
		searchItem.setTitle(bean.getTitle());
		searchItem.setVolume(bean.getCommission());
		return searchItem;
	}
	
	/**
	 * 将搜索页数减去添加的商品页数
	 * @param keywordName
	 * @param pageNo
	 * @return
	 */
	public static String getTruePage(String keywordName,String pageNo){
		SearchKeywords searchKeyword = GetSearchKeywordManager.getKeywordIdByKeyword(keywordName);
		if(searchKeyword != null){
			String keywordId = searchKeyword.getId();
			int size = (int)Keyword2ItemCacher.getSize(keywordId);
			if(size == 0){
				return pageNo;
			}
			int count = size /pageCount;
			String pageTrue = (Integer.parseInt(pageNo) - count)+"";
			return pageTrue;
		}
		return pageNo;
	}
	
	public static void main(String[] args) {
//		System.out.println(TagItemCacher.getInstance().getProduct("37131549687").getPic_url());
//		System.out.println(getSearchNum("T", "", "", "", "", "", "", "", "", "json"));
		System.out.println(getFirstPage("女雪纺衫", "392", 30));
//		System.out.println("grgjtyj  "+getSearch("雪纺衫", "2", "xml"));
	}
}
