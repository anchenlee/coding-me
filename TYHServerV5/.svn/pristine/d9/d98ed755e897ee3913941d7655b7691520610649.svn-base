package cn.youhui.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.youhui.bean.Action;
import cn.youhui.bean.KeyCategoryBean;
import cn.youhui.bean.KeyCategoryBean.KeywordBean;
import cn.youhui.bean.TeJiaGoodsBean;
import cn.youhui.common.Config;
import cn.youhui.common.SepaConfig;
import cn.youhui.ramdata.Tag2ItemCacher;
import cn.youhui.ramdata.Tag2TagCacher;
import cn.youhui.ramdata.TagCacher;
import cn.youhui.ramdata.TagItemCacher;
import cn.youhui.utils.NetManager;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.ItemData;
import com.taobao.api.request.JuCatitemidsGetRequest;
import com.taobao.api.request.JuItemsGetRequest;
import com.taobao.api.response.JuCatitemidsGetResponse;
import com.taobao.api.response.JuItemsGetResponse;

/**
 * 查询聚划算相关数据
 * @author leejun
 * @since 2013-05-28
 */
public class JuHuaSuanManager {
	private static final String juBrandTagId = "607";
	private static final String PinPaiTuanTagId = "609";
	private static final String brandUrl = "http://ju.taobao.com/tg/ju_brand_list.htm";
	private static String actionType = "tagStyleGrid";
	private static final String itemListUrl = "http://wtm.m.taobao.com/jhs/ajax/item_list.htm?jtype=6&brandCode=#brandcode#&pageNo=#page#&pageSize=100";
	private static final String itemListUrlNew = "http://ju.taobao.com/json/tg/ajaxGetJsonItems.htm?type=0&reverse=down&";
	
	public static void updateBrandOld()
	{
		List<KeywordBean> taglist = getBrandListFromNet();
		if(taglist != null)
		{
			String tagids = "";
			for(KeywordBean keyword : taglist)
			{
			   String itemids = getItemIds(keyword.getId());
			   if(itemids != null)
			   {
				   // tag.all_tags
				   TagCacher.getInstance().addTag(keyword);
				
				   List<TeJiaGoodsBean> itemlist = getJuhuasuanItem(itemids);
				   // youhui.cn.tag.products.
				   TagItemCacher.getInstance().addProducts(itemlist);
				   // tag.tag2item
				   Tag2ItemCacher.getInstance().refreshTag2Item(keyword.getId(), itemlist);
				   
				   tagids += keyword.getId() + SepaConfig.TAG_SEPA;
			   }
			}
			
			if(!"".equals(tagids))
			{
				Tag2TagCacher.getInstance().refreshTag2Tag(PinPaiTuanTagId, tagids);
			}
		}
	}
	
	
	/**
	 * 获取聚划算下商品
	 * @param itemIds
	 * @return
	 */	
	public static List<TeJiaGoodsBean> getJuhuasuanItem(String itemIds){
		List<TeJiaGoodsBean> list = new ArrayList<TeJiaGoodsBean>();
		TaobaoClient client = new DefaultTaobaoClient(Config.Fanli_url, Config.Fanli_appkey, Config.Fanli_secret);
		JuItemsGetRequest req=new JuItemsGetRequest();
		String[] itemids = itemIds.split(",");
		List<String> itemidslist = new ArrayList<String>();
		int i = 0;
		String itemidss = "";
		for(String id : itemids){
			itemidss += id + ",";
			if(i++ >= 10){
				itemidslist.add(itemidss);
				itemidss = "";
			}
		}
		itemidslist.add(itemidss);
		try {
			for(String ids : itemidslist){
				req.setIds(ids);
				JuItemsGetResponse response = client.execute(req);
				if(response != null){
					List<ItemData> items = response.getItemList();
					if(items != null ){
						for(ItemData item : items){
							TeJiaGoodsBean bean = new TeJiaGoodsBean();
							bean.setItem_id(item.getItemId() + "");
							bean.setTitle(item.getShortName());
							bean.setPic_url("http://img.taobaocdn.com/bao/uploaded/" + item.getPicUrl());
							bean.setPrice_low(item.getActivityPrice()/100 + "");
							bean.setPrice_high(item.getOriginalPrice()/100 + "");
							bean.setDiscount( "" + (Double.parseDouble(bean.getPrice_low()) / Double.parseDouble(bean.getPrice_high())) * 100);
							bean.setClickURL("http://a.m.taobao.com/i" + item.getItemId() + ".html");
							list.add(bean);
						}
					}
				}
			}
		} catch (ApiException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 获取品牌下商品id
	 */
	public static String getItems(String brandId){
		String items = "";
		String brandmat = "<div class=\"ju-itemlist J_JuHomeList\".*?</ul> *</div>";
		Pattern pat = Pattern.compile(brandmat);
		try {
			String response = NetManager.getInstance().getContent(brandUrl + "?id=" + brandId);
			if(response != null){
				Matcher matcher = pat.matcher(response);
				if(matcher.find()){
					String brands = matcher.group();
					String bran = "<li class=\"\" data-rank=.*?</li>";
					Pattern patt = Pattern.compile(bran);
					Matcher mat = patt.matcher(brands);
					while(mat.find()){
						String brandstr = mat.group();
						String itemstr = "href=\".*?\"";
						Pattern patitem = Pattern.compile(itemstr);
						Matcher matitem = patitem.matcher(brandstr);
						if(matitem.find()){
							String itemurl = matitem.group();
							String idstr = "^[0-9]{10,}$";
							Pattern patid = Pattern.compile(idstr);
							Matcher matid = patid.matcher(itemurl);
							if(matid.find()){
								String id = matid.group();
								items += id + ",";
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return items;
	}
	
	public static List<TeJiaGoodsBean> getItemList(String brandId){
		try {
			String response = NetManager.getInstance().getContent(itemListUrl.replaceAll("#brandcode#", brandId).replaceAll("#page#", 1+""));
			response = response.substring(1, response.length()-1);
			return analyJson(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getItemIds(String brandId){
		try {
			String response = NetManager.getInstance().getContent(itemListUrl.replaceAll("#brandcode#", brandId).replaceAll("#page#", 1+""));
			response = response.substring(1, response.length()-1);
			return getIds(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	private static List<TeJiaGoodsBean> analyJson(String str){
		List<TeJiaGoodsBean> list = new ArrayList<TeJiaGoodsBean>();
		try {
			JSONObject resp = new JSONObject(str);
			if(resp.has("data")){
				JSONArray rates = resp.getJSONArray("data");
				int size = rates.length();
				if(size == 0)
					return null;
				for(int i = 0; i<size; i++){
					JSONObject item = rates.getJSONObject(i);
					if(item != null){
						TeJiaGoodsBean bean = new TeJiaGoodsBean();
						if(item.has("itemId")){
//							bean.setId(item.getString("itemId"));
							bean.setItem_id(item.getString("itemId"));
							bean.setClickURL("http://a.m.taobao.com/i" + item.getString("itemId") + ".html");
						}
						if(item.has("subName")){
							bean.setTitle(item.getString("subName"));
						}
						if(item.has("picWideUrl")){
							bean.setPic_url(item.getString("picWideUrl"));
						}
						if(item.has("originalPrice")){
							bean.setPrice_high(item.getString("originalPrice"));
						}
						if(item.has("activityPrice")){
							bean.setPrice_low(item.getString("activityPrice"));
						}
						if(bean.getItem_id() != null && !"".equals(bean.getItem_id())){
							list.add(bean);
						}
					}
				}
			}else{
				return null;
			}	
			} catch (JSONException e) {
				e.printStackTrace();
			}
		return list;
		}
	
	private static String getIds(String str){
		String itemIds = "";
		try 
		{
			JSONObject resp = new JSONObject(str);
			if(resp.has("data"))
			{
				JSONArray rates = resp.getJSONArray("data");
				int size = rates.length();
				if(size == 0)
				{
					return null;
				}
				for(int i = 0; i<size; i++)
				{
					JSONObject item = rates.getJSONObject(i);
					if(item != null)
					{
						if(item.has("itemId"))
						{
							itemIds += item.getString("itemId") + ",";
						}
					}
				}
			}else{
				return null;
			}	
			} catch (JSONException e) {
				e.printStackTrace();
			}
		return itemIds;
	}
	
	/**
	 * 解析网页得到品牌团数据
	 * @return
	 */
	public static List<KeywordBean> getBrandListOld()
	{
		List<KeywordBean> list = new ArrayList<KeywordBean>();
		String branddiv ="<div class=\"slide-content clearfix.*?\">.*?</div>";
		String brandlist = "<ul class=\"clearfix J_One.*?\">.*?</ul>";
		
		String brandmat = "<li.*?>.*?</li>";
		Pattern p = Pattern.compile(branddiv);
		Pattern pa = Pattern.compile(brandlist);
		Pattern pat = Pattern.compile(brandmat);
		try 
		{
			String response = NetManager.getInstance().getContentGBK(brandUrl);
			if(response != null)
			{
				Matcher m = p.matcher(response);
				if(m.find()) {
					String div = m.group();
					Matcher ma = pa.matcher(div);
					while(ma.find()) {
						String ul = ma.group();
						Matcher matcher = pat.matcher(ul);
						while (matcher.find())
						{
							String li = matcher.group();
							KeywordBean bean = parsing(li);
//							System.out.println(bean.getIcon());
							list.add(bean);
						}
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 解析得到品牌数据
	 * @return
	 */
	private static KeywordBean parsing(String str)
	{
//		System.out.println(str);
		KeywordBean bean = new KeyCategoryBean().new KeywordBean();
		String bran = "asid=\".*?\"";
		Pattern patt = Pattern.compile(bran);
		Matcher mat = patt.matcher(str);
		if(mat.find())
		{
			String id = mat.group();
			
			id = id.replaceAll("asid=", "").replaceAll("\"", "");
			if (null == id || "".equals(id))
			{
				return null;
			}
			bean.setId(id);
		}
		else
		{
			return null;
		}
		bran = "title=\".*?\"";
		patt = Pattern.compile(bran);
		mat = patt.matcher(str);
		if(mat.find())
		{
			String title = mat.group();
			title = title.replaceAll("title=", "").replaceAll("\"", "");
			if (null == title || "".equals(title))
			{
				return null;
			}
			title = title.replaceAll("&amp;", "&");
			title = title.replaceAll("&#39;", "'");
			bean.setKeyword(title);
			bean.setDescription(title);
		}
		else
		{
			return null;
		}
		bran = "<img.*?data-ks-lazyload-custom=\".*?\"";
		String bran1 = "<img.*?src=\".*?\"";
		patt = Pattern.compile(bran);
		Pattern patt1 = Pattern.compile(bran1);
		mat = patt.matcher(str);
		Matcher mat1 = patt1.matcher(str);
		if(mat.find())
		{
			String pic = mat.group();
			
			pic = pic.replaceAll(".*?data-ks-lazyload-custom=", "").replaceAll("\"", "");
			if (null == pic || "".equals(pic))
			{
				return null;
			}
			pic = pic.replaceAll("90x90", "250x250");
			bean.setIcon(pic);
		}
		else if(mat1.find()) {
			String pic = mat1.group();
			
			pic = pic.replaceAll(".*?src=", "").replaceAll("\"", "");
			if (null == pic || "".equals(pic))
			{
				return null;
			}
			pic = pic.replaceAll("90x90", "250x250");
			bean.setIcon(pic);
		}
		else
		{
			return null;
		}
//		System.out.println("pic   "+bean.getIcon());
		bean.setAction(new Action(actionType, bean.getId()));
		bean.setShow("zk");
		return bean;
	}
	
	public static void getJuhuasuanCatitemids(long paCatId){
		TaobaoClient client = new DefaultTaobaoClient(Config.Fanli_url, Config.Fanli_appkey, Config.Fanli_secret);
		JuCatitemidsGetRequest req=new JuCatitemidsGetRequest();
		req.setTerminalType("WAP");
		req.setPlatformId(1001L);
		req.setParentCategoryid(paCatId);
		req.setPageNo(1L);
		req.setPageSize(40L);
		try {
			JuCatitemidsGetResponse response = client.execute(req);
			if(response != null){
			}
		} catch (ApiException e) {
			e.printStackTrace();
		}
	}
	
	public static List<KeywordBean> getBrandListNew()
	{
		List<KeywordBean> list = null;
		KeywordBean bean = null;
		String brandmat = "<ul class=\"clearfix J_One\">.*?</ul>";
		Pattern pat = Pattern.compile(brandmat);
		try {
			String content = NetManager.getInstance().getContentGBK(brandUrl);
			if(content!=null) {
				Matcher matcher = pat.matcher(content);
				while(matcher.find()) {
					String ul = matcher.group();
					Pattern pa = Pattern.compile("<li>.*?</li>");
					Matcher ma = pa.matcher(ul);
					list = new ArrayList<KeywordBean>();
					while(ma.find()) {
						bean = new KeyCategoryBean().new KeywordBean();
						String li = ma.group();
						bean = getKeywordBean(li);
						list.add(bean)
;					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}
	
	public static KeywordBean getKeywordBean(String li) {
		KeywordBean bean = new KeyCategoryBean().new KeywordBean();
		String bran = "asid=\".*?\"";
		Pattern patt = Pattern.compile(bran);
		Matcher mat = patt.matcher(li);
		if(mat.find())
		{
			String id = mat.group();
			
			id = id.replaceAll("asid=", "").replaceAll("\"", "");
			
			if (null == id || "".equals(id))
			{
				return null;
			}
			bean.setId(id);
		}
		else
		{
			return null;
		}
		bran = "<a title=\".*?\"";
		patt = Pattern.compile(bran);
		mat = patt.matcher(li);
		if(mat.find()) {
			String keyword = mat.group();
			keyword = keyword.replaceAll("<a title=", "").replaceAll("\"", "");
			bean.setKeyword(keyword);
			bean.setDescription(keyword);
		}		
		else
		{
			return null;
		}
		bran = "src=\".*?\"";
		patt = Pattern.compile(bran);
		mat = patt.matcher(li);
		if(mat.find()) {
			String img = mat.group();
			img = img.replaceAll("src=", "").replaceAll("\"", "");
			bean.setIcon(img);
		}
		else
		{
			return null;
		}
		bean.setAction(new Action(actionType, bean.getId()));
		bean.setShow("zk");
		return bean;
	}
	
	public static List<TeJiaGoodsBean> getitemids(String act_sign_id ,int page) {
		String content = "";
		List<TeJiaGoodsBean> list = new ArrayList<TeJiaGoodsBean>();;
		try {

			content = NetManager.getInstance().getContentGBK(itemListUrlNew+"act_sign_id="+act_sign_id+"&page="+page);
			if(content!=null&&!"".equals(content)) {
				content = content.substring(1, content.length()-1);
				JSONObject jso = new JSONObject(content);
				JSONArray jsa = null;
				if(jso.has("itemList")) {
					jsa = jso.getJSONArray("itemList");
				}
				if(jsa!=null&&jsa.length()>0) {
					for(int i = 0;i<jsa.length();i++) {
						TeJiaGoodsBean bean = new TeJiaGoodsBean();
						JSONObject jso1 = jsa.getJSONObject(i);
						if(jso1.has("itemId")) {					
							bean.setItem_id(jso1.has("itemId")?jso1.getString("itemId"):"");
							bean.setTitle(jso1.has("shortName")?jso1.getString("shortName"):"");
							bean.setPic_url("http://img.taobaocdn.com/bao/uploaded/" +jso1.getString("picUrlFromIc"));
							bean.setPrice_low(jso1.getDouble("realActicityPrice")/100 + "");
							bean.setPrice_high( jso1.getDouble("originalPrice")/100 + "");
							bean.setDiscount( jso1.getDouble("discount")*10+"");
							bean.setClickURL(jso1.getString("itemUrl"));
							list.add(bean);
						}
												
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static void updateBrand() {
		List<KeywordBean> taglist = getBrandListFromNet();
		if(taglist != null && taglist.size() > 0)
		{
			String tagids = "";
			for(KeywordBean keyword : taglist)
			{
			   String ids = keyword.getId();
			   if(ids != null && !ids.equals(""))
			   {
				   // tag.all_tags
				   TagCacher.getInstance().addTag(keyword);
				   List<TeJiaGoodsBean> itemlist = new ArrayList<TeJiaGoodsBean>();
				   int totalPage = getTotalPage(ids);
				   if(totalPage>1) {
					   for(int i=1;i<=totalPage;i++) {
						   itemlist.addAll(getitemids(ids, i));
					   }
				   }
				   else itemlist = getitemids(ids, 1);
				   // youhui.cn.tag.products.
				   TagItemCacher.getInstance().addProducts(itemlist);
				   // tag.tag2item
				   Tag2ItemCacher.getInstance().refreshTag2Item(keyword.getId(), itemlist);
				   
				   tagids += keyword.getId() + SepaConfig.TAG_SEPA;
			   }
			}
			
			if(!"".equals(tagids))
			{
				Tag2TagCacher.getInstance().refreshTag2Tag(PinPaiTuanTagId, tagids);
			}
		}
	}
	
	public static int getTotalPage(String act_sign_id) {
		int total = 0;
		String content = "";
		try {
			content = NetManager.getInstance().getContentGBK(itemListUrlNew+"act_sign_id="+act_sign_id);
			if(content!=null&&!"".equals(content)) {
				content = content.substring(1, content.length()-1);
				JSONObject jso = new JSONObject(content);
				total = jso.getInt("totalPage");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
	}
	
	
	
	/**
	 * 解析网页得到品牌团数据 2013/11/26
	 * @return
	 */
	public static int getBrandPage()
	{
		int total = 0;
		String content = "";
		try {
			content = NetManager.getInstance().getContentGBK("http://ju.taobao.com/json/tg/ajaxGetData.htm?type=brands");
			if(content!=null&&!"".equals(content)) {
				content = content.substring(1, content.length()-1);
				JSONObject jso = new JSONObject(content);
				total = jso.getInt("totalPage");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
	}
	
	/**
	 * 解析网页得到品牌团数据 2013/11/26
	 * @return
	 */
	public static List<KeywordBean> getBrandList()
	{
		int total = getBrandPage();
		if(total == 0)
		{
			return null;
		}
		String url = "http://ju.taobao.com/json/tg/ajaxGetData.htm?type=brands&page=";
		 List<KeywordBean> list = new ArrayList<KeywordBean>();
			for(int i = 1 ; i <= total ; i++)
			{
				String content = "";
				try {
					content = NetManager.getInstance().getContentGBK(url+i);
					if(content!=null&&!"".equals(content)) {
						content = content.substring(1, content.length()-1);
						List<KeywordBean> pageList = getKeywordBeanFromLi(content);
						if(pageList != null && pageList.size() > 0)
						{
							list.addAll(pageList);
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		 return list;
	}
	
	/**
	 * 从网页获取品牌数据
	 * @return
	 */
	public static List<KeywordBean> getBrandListFromNet(){
		List<KeywordBean> list = new ArrayList<KeywordBean>();
		String url = "http://ju.taobao.com/tg/brand.htm?spm=608.2291429.4004.2.PoFWiN";
	
		String content = "";
		try {
			content = NetManager.getInstance().getContentGBK(url);
			if(content!=null&&!"".equals(content)) {
				String divli = "<div class=\"slide-content clearfix\".*?</div>";
				Pattern pattern = Pattern.compile(divli);
				Matcher matcher = pattern.matcher(content);
				if(matcher.find()){
					String div = matcher.group();
					String bran = "<ul class=\"clearfix J_One.*?</ul>";
					Pattern patt = Pattern.compile(bran);
					Matcher mat = patt.matcher(div);
					while(mat.find()){
						String ul = mat.group();
//					System.out.println(ul);
						
						String branli = "<li.*?</li>";
						Pattern patt1 = Pattern.compile(branli);
						Matcher mat1 = patt1.matcher(ul);
						while(mat1.find()){
							KeywordBean bean = new KeyCategoryBean().new KeywordBean();
							String li = mat1.group();
//							System.out.println(li);
							String titleli = "title=\".*?\"";
							Pattern patt2 = Pattern.compile(titleli);
							Matcher mat2 = patt2.matcher(li);
							if(mat2.find()){
								String title = mat2.group();
								title = title.replaceAll("title=\"", "").replaceAll("\"", "");
								bean.setKeyword(title);
								bean.setDescription(title);
							}
							
							String codeli = "asid=\".*?\"";
							patt2 = Pattern.compile(codeli);
							mat2 = patt2.matcher(li);
							if(mat2.find()){
								String code = mat2.group();
								code = code.replaceAll("asid=\"", "").replaceAll("\"", "");
								bean.setId(code);
							}
							
							String juLogoli = "data-ks-lazyload-custom=\".*?\"";
							patt2 = Pattern.compile(juLogoli);
							mat2 = patt2.matcher(li);
							if(mat2.find()){
								String juLogo = mat2.group();
								juLogo = juLogo.replaceAll("data-ks-lazyload-custom=\"", "").replaceAll("\"", "").replaceAll("90x90", "250x250");
								bean.setIcon(juLogo);
								bean.setShow("zk");
								bean.setAction(new Action(actionType, bean.getId()));
							}
							
							String icon = "src=\".*?\"";
							patt2 = Pattern.compile(icon);
							mat2 = patt2.matcher(li);
							if(mat2.find()){
								String juLogo = mat2.group();
								juLogo = juLogo.replaceAll("src=\"", "").replaceAll("\"", "").replaceAll("90x90", "250x250");
								bean.setIcon(juLogo);
								bean.setShow("zk");
								bean.setAction(new Action(actionType, bean.getId()));
							}
							
							
							if(bean != null && bean.getId() != null && !"".equals(bean.getIcon()) && bean.getIcon() !=null && !"".equals(bean.getIcon()))
							list.add(bean);
//							if(bean.getIcon() == null || "".equals(bean.getIcon())){
//								System.out.println(li);
//							}
						}
						
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 解析网页得到品牌团数据 2013/11/26
	 * @return
	 */
	private static List<KeywordBean> getKeywordBeanFromLi(String str)
	{
		List<KeywordBean> list = new ArrayList<KeyCategoryBean.KeywordBean>();
		try {
			JSONObject jso = new JSONObject(str);
			JSONArray jsa = jso.getJSONArray("data");
			if(jsa != null && jsa.length() > 0)
			{
				for(int i = 0 ; i < jsa.length() ; i ++)
				{					
					JSONObject item = jsa.getJSONObject(i);
					KeywordBean bean = new KeyCategoryBean().new KeywordBean();
					bean.setId(item.getString("code"));
					String icon = item.getString("juLogo");
					icon = icon.replaceAll("160x160", "250x250");
					bean.setIcon(icon);
					bean.setKeyword(item.getString("name"));
					bean.setDescription(item.getString("juSlogo"));
					bean.setAction(new Action(actionType, bean.getId()));
					bean.setShow("zk");
					list.add(bean);
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static void main(String[] args)
	{
//		System.out.println(getBrandList().get(0).getId());
		List<KeywordBean> list = getBrandListFromNet();
		int m = 0;
		for(KeywordBean k : list)
		{
			m++;
			System.out.println(m);
			System.out.println(k.getIcon()+"  "+k.getId()+" "+k.getKeyword());
		}
//		getBrandListNew().size();
//		System.out.println(getItems("54867"));
//		System.out.println(getitemids("54867",1).size());
//		updateBrand();
//		getBrandListFromNet();
	}
}
