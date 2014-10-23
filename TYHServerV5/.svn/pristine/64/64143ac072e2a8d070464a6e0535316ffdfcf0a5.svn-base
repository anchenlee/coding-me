package cn.youhui.manager;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.youhui.bean.SuiShouAction;
import cn.youhui.bean.TeJiaGoodsBean;
import cn.youhui.bean.TianTianDaZheBean;
import cn.youhui.bean.KeyCategoryBean.KeywordBean;
import cn.youhui.common.Config;
import cn.youhui.common.TPool;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.ramdata.TTTeJiaCacher;
import cn.youhui.ramdata.TagCacher;
import cn.youhui.ramdata.Tagid2TagnameCacher;
import cn.youhui.ramdata.TeJiaCateogry2Cacher;
import cn.youhui.utils.NetManager;
import cn.youhui.utils.OuterCode;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhui.utils.SuiShouActionUtil;

public class TianTianDazhe {
//	public static String taobaoid = "yaoyuewudi";
//	private static String akey = "12347692";  //"12415289"; 
//	private static String asecret = "c62deb79ce636f9cbac7c074269ab1d6";    //"8a381fcabc1b74003c689550a95a0f0a";  
//	public static String url = "http://gw.api.taobao.com/router/rest";
//	public static String appkey =  akey;
//	public static String secret = asecret;
	static int page_count = 12;
	public static Long lastupdatetime = 0L;

//	public static Hashtable<String, Hashtable<String, ArrayList<TianTianDaZheBean>>> channelTable = new Hashtable<String, Hashtable<String, ArrayList<TianTianDaZheBean>>>();
	public static Hashtable<String, ArrayList<TianTianDaZheBean>> cachetable = new Hashtable<String, ArrayList<TianTianDaZheBean>>();
	public static Hashtable<String, String> category = null;
	public static ArrayList<TianTianDaZheBean> cache = new ArrayList<TianTianDaZheBean>();
	public static String getCategory() {
		if (category == null) {
			category = new Hashtable<String, String>();

			category.put("9910", "10点开抢");
			category.put("9914", "14点开抢");
			category.put("9920", "20点开抢");
			category.put("1", "时尚女装");
			category.put("2", "流行男装");
			category.put("3", "男鞋女鞋");
			category.put("4", "包包配饰");
			category.put("5", "美容美发");
			category.put("7", "家具生活");
			category.put("9", "母婴用品");
			category.put("8", "美食特产");
			category.put("6", "数码家电");
		}

//		if (System.currentTimeMillis() - lastupdatetime > 30 * 60 * 1000) {
//			lastupdatetime = System.currentTimeMillis();
//			
//					Runnable run = new Runnable() {
//						
//						@Override
//						public void run() {
//							// TODO Auto-generated method stub
//							initFromWebsite();
//						}
//					};
//					TPool.getInstance().doit(run);
//	
//		}

			StringBuffer sb = new StringBuffer();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			sb.append("<resp>");
			sb.append("<status>1000</status>");
			sb.append("<desc><![CDATA[ OK ]]></desc>");
			sb.append("<data>");
			sb.append("<top>");

			ArrayList<TianTianDaZheBean> list = dosearch("", "9910");
			sb.append("<act name=\"10点开抢\">");

			for (TianTianDaZheBean bean : list) {
				sb.append("<item>");
				sb.append("<img><![CDATA[" + bean.getPicurl() + "]]></img>");
				sb.append("<click><![CDATA[" + bean.getClickUrl()+ "]]></click>");
				sb.append("<title><![CDATA[" + bean.getTitle() + "]]></title>");
				sb.append("<productname><![CDATA[" + bean.getProductname().replaceAll("[.*?]", "").replaceAll("【.*?】", "").replaceAll(" ", "") + "]]></productname>");


				sb.append("<itemid >" + bean.getItemid() + "</itemid >");
				sb.append("<newprice>" + bean.getNewprice() + "</newprice>");
				sb.append("<originalPrice>" + bean.getOriginalPrice() + "</originalPrice>");
				sb.append("<discount>" + bean.getDiscount() + "</discount>");
				sb.append("</item>");
			}
			sb.append("</act>");
			list = dosearch("", "9914");
			sb.append("<act name=\"14点开抢\">");

			for (TianTianDaZheBean bean : list) {
				sb.append("<item>");
				sb.append("<img><![CDATA[" + bean.getPicurl() + "]]></img>");
				sb.append("<click><![CDATA[" + bean.getClickUrl()+ "]]></click>");
				sb.append("<title><![CDATA[" + bean.getTitle() + "]]></title>");
				sb.append("<productname><![CDATA[" + bean.getProductname().replaceAll("[.*?]", "").replaceAll("【.*?】", "").replaceAll(" ", "") + "]]></productname>");


				sb.append("<itemid >" + bean.getItemid() + "</itemid >");
				sb.append("<newprice>" + bean.getNewprice() + "</newprice>");
				sb.append("<originalPrice>" + bean.getOriginalPrice() + "</originalPrice>");
				sb.append("<discount>" + bean.getDiscount() + "</discount>");
				sb.append("</item>");
			}
			sb.append("</act>");
			list = dosearch("", "9920");
			sb.append("<act name=\"20点开抢\">");

			for (TianTianDaZheBean bean : list) {
				sb.append("<item>");
				sb.append("<img><![CDATA[" + bean.getPicurl() + "]]></img>");
				sb.append("<click><![CDATA[" + bean.getClickUrl()+ "]]></click>");	
				sb.append("<title><![CDATA[" + bean.getTitle() + "]]></title>");
				sb.append("<productname><![CDATA[" + bean.getProductname().replaceAll("[.*?]", "").replaceAll("【.*?】", "").replaceAll(" ", "")+ "]]></productname>");


				sb.append("<itemid >" + bean.getItemid() + "</itemid >");
				sb.append("<newprice>" + bean.getNewprice() + "</newprice>");
				sb.append("<originalPrice>" + bean.getOriginalPrice() + "</originalPrice>");
				sb.append("<discount>" + bean.getDiscount() + "</discount>");
				sb.append("</item>");
			}
			sb.append("</act></top>");

			sb.append("<cats>");
				for(int i = 1;i<=9;i++){
					list = dosearch("", ""+i);
					TianTianDaZheBean ttdzb = new TianTianDaZheBean();
					if(list.size()>0)ttdzb = list.get(0);
					sb.append("<cat id=\"" + i + "\" name=\"" + category.get(""+i)
							+ "\">");
					sb.append("<desc><![CDATA[" + ttdzb.getTitle() + "]]></desc>");
					sb.append("<img><![CDATA[" +ttdzb.getPicurl() + "]]></img>");
					sb.append("</cat>");
				}
			sb.append("</cats>");
			sb.append("</data>");
			sb.append("</resp>");

			//cateogryCacher.pushCategoryXml(channel, sb.toString());
			return sb.toString();

	}

	public static String getResult(String page, String uid, String keyword,String type, String platform) {

		if (category == null) {
			
			category = new Hashtable<String, String>();

			category.put("9910", "10点开抢");
			category.put("9914", "14点开抢");
			category.put("9920", "20点开抢");
			category.put("1", "时尚女装");
			category.put("2", "流行男装");
			category.put("3", "男鞋女鞋");
			category.put("4", "包包配饰");
			category.put("5", "美容美发");
			category.put("7", "家具生活");
			category.put("9", "母婴用品");
			category.put("8", "美食特产");
			category.put("6", "数码家电");
		}

//		if (System.currentTimeMillis() - lastupdatetime > 30 * 60 * 1000) {
//			lastupdatetime = System.currentTimeMillis();
//
//			Runnable run = new Runnable() {
//				
//				public void run() {
//					initFromWebsite();
//				}
//			};
//
//			TPool.getInstance().doit(run);
//	
//		}
		StringBuffer sb = new StringBuffer();
		if (page == null) {
			page = "1";
		}
		int a = 0;
		try {
			a = Integer.parseInt(page);
		} catch (Exception e) {
			page = "1";
		}
		ArrayList<TianTianDaZheBean> searchResult = dosearch(keyword, type);
		
		if (a > searchResult.size() / page_count + 1 || a <= 0) {
			page = "1";
		}
		int totalpage = 1;
		if (searchResult.size() % page_count == 0) {
			totalpage = searchResult.size() / page_count;
		} else {
			totalpage = searchResult.size() / page_count + 1;
		}

		int s = (Integer.parseInt(page) - 1) * page_count;

		int e = s + page_count;

		// String ttTagId = Tagid2TagnameCacher.getInstance().getIdbyName("天天特价");
		String ttTagId = "590";
		KeywordBean ttbean = TagCacher.getInstance().getTag(ttTagId);
		
		if(ttbean != null && ttbean.getChaye_icon() != null && !"".equals(ttbean.getChaye_icon()) && ttbean.getChayeAction() != null){
			sb.append("<chaye>");
			sb.append("<icon><![CDATA[").append(ttbean.getChaye_icon()).append("]]></icon>");
			sb.append("<wh_ratio><![CDATA[").append(ttbean.getChayeIconSize()).append("]]></wh_ratio>");
			sb.append(ttbean.getChayeAction().toXML());
			sb.append("</chaye>");
		}
		sb.append("<tiantiandazhe>");
		String id = "";
		for (int i = s; i < e && i < searchResult.size(); i++) {
			id = id + searchResult.get(i).getItemid() + ",";
			String outerCode = OuterCode.getOuterCode(uid, platform, "0", "7", "590");
			searchResult.get(i).setClickUrl(Config.SKIP_URL + "tyh_outer_code=" + outerCode + "&itemid=" + searchResult.get(i).getItemid());
			sb.append(searchResult.get(i).getContent());
		}
		sb.append("</tiantiandazhe>");
		return RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, "", totalpage, a, sb.toString()).toString();
	}

	public static String getResult3(String page, String uid, String keyword,String type, String platform) {

		if (category == null) {
			
			category = new Hashtable<String, String>();

			category.put("9910", "10点开抢");
			category.put("9914", "14点开抢");
			category.put("9920", "20点开抢");
			category.put("1", "时尚女装");
			category.put("2", "流行男装");
			category.put("3", "男鞋女鞋");
			category.put("4", "包包配饰");
			category.put("5", "美容美发");
			category.put("7", "家具生活");
			category.put("9", "母婴用品");
			category.put("8", "美食特产");
			category.put("6", "数码家电");
		}

//		if (System.currentTimeMillis() - lastupdatetime > 30 * 60 * 1000) {
//			lastupdatetime = System.currentTimeMillis();
//
//			Runnable run = new Runnable() {
//				
//				public void run() {
//					initFromWebsite();
//				}
//			};
//
//			TPool.getInstance().doit(run);
//	
//		}
		StringBuffer sb = new StringBuffer();
		if (page == null) {
			page = "1";
		}
		int a = 0;
		try {
			a = Integer.parseInt(page);
		} catch (Exception e) {
			page = "1";
		}
		ArrayList<TianTianDaZheBean> searchResult = dosearch(keyword, type);
		
		if (a > searchResult.size() / page_count + 1 || a <= 0) {
			page = "1";
		}
		int totalpage = 1;
		if (searchResult.size() % page_count == 0) {
			totalpage = searchResult.size() / page_count;
		} else {
			totalpage = searchResult.size() / page_count + 1;
		}

		int s = (Integer.parseInt(page) - 1) * page_count;

		int e = s + page_count;

		// String ttTagId = Tagid2TagnameCacher.getInstance().getIdbyName("天天特价");
		String ttTagId = "590";
		KeywordBean ttbean = TagCacher.getInstance().getTag(ttTagId);
		
		if(ttbean != null && ttbean.getChaye_icon() != null && !"".equals(ttbean.getChaye_icon()) && ttbean.getChayeAction() != null){
			sb.append("<chaye>");
			sb.append("<icon><![CDATA[").append(ttbean.getChaye_icon()).append("]]></icon>");
			sb.append("<wh_ratio><![CDATA[").append(ttbean.getChayeIconSize()).append("]]></wh_ratio>");
			sb.append(new SuiShouAction(SuiShouActionUtil.getSuiShouActionUrl(platform,ttbean.getChayeAction())).toXML());
			sb.append("</chaye>");
		}
		sb.append("<tiantiandazhe>");
		String id = "";
		for (int i = s; i < e && i < searchResult.size(); i++) {
			id = id + searchResult.get(i).getItemid() + ",";
			String outerCode = OuterCode.getOuterCode(uid, platform, "0", "7", "590");
			searchResult.get(i).setClickUrl(Config.SKIP_URL + "tyh_outer_code=" + outerCode + "&itemid=" + searchResult.get(i).getItemid());
			sb.append(searchResult.get(i).getContent());
		}
		sb.append("</tiantiandazhe>");
		return RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, "", totalpage, a, sb.toString()).toString();
	}
	
	private static ArrayList<TianTianDaZheBean> dosearch(String keyword,String type) {
		
		TTTeJiaCacher cacher = TTTeJiaCacher.getInstance();

		if (cacher.exists(type)) {
					return cacher.getList(type);

			} else {
				return new ArrayList<TianTianDaZheBean>();

			}
	}



//	public static Hashtable<String, String> getUrlsByids(String num_id) {
//		Hashtable<String, String> urls = new Hashtable<String, String>();
//		if(num_id != null){
//			String itemIds[] = num_id.split(",");
//			for(String itemId : itemIds){
//				String url = getUrlbyId(itemId);
//				urls.put(itemId, url);
//			}
//		}
//		return urls;
//	}

	private static TianTianDaZheBean getTTDZBean(String content) {
		
		TianTianDaZheBean ttdz = new TianTianDaZheBean();

		Matcher m_item_url = Pattern.compile("<a.*?[ ]href=\".*?\"").matcher(content);
		if (m_item_url.find()) {
			
			String url = m_item_url.group().replaceAll("<a.*?[ ]href=\"", "").replaceAll("\"", "");
			ttdz.setClickUrl(url);
			
			Matcher m_item_id = Pattern.compile("id=.*[0-9]").matcher(url);
			if (m_item_id.find()) {
				ttdz.setItemid(m_item_id.group().replaceAll("[^0-9]", ""));
			}
		}

		/*
		 * 获取新旧价格 *
		 */
		//原价
		Matcher m_old_price = Pattern.compile("<p class=\"pro-primary\">原价：.*?</p>").matcher(content);
		if (m_old_price.find()) {
			ttdz.setOriginalPrice((int) (Double.parseDouble(m_old_price.group().replaceAll("[^0-9.]", "")) * 100) + "");

		}
		
		//新价
		Matcher m_new_price = Pattern.compile("<p class=\"pro-price\">.*?</p>").matcher(content);
		
		if (m_new_price.find()) {
			ttdz.setNewprice((int) (Double.parseDouble(m_new_price.group().replaceAll("[^0-9.]", "")) * 100) + "");
		}
		
		ttdz.setDiscount(((int) (1000 * Float.parseFloat(ttdz.getNewprice()) / (Float.parseFloat(ttdz.getOriginalPrice())))) + "");
		
		/*
		 *获取天天特价图片 
		 */
		Matcher m_item_pic = Pattern.compile("<img data-ks-lazyload=(\"|').*?(\"|')").matcher(content);
		if(m_item_pic.find()){
			ttdz.setPicurl(m_item_pic.group().replaceAll("<img data-ks-lazyload=", "").replaceAll("(\"|')", ""));
		}
		
		//获取商品名称
		Matcher m_item_title = Pattern.compile("target=\"_blank\" >.*?</a>").matcher(content);
		if(m_item_title.find()){
			ttdz.setTitle(m_item_title.group().replaceAll("target=\"_blank\" >", "").replaceAll("</a>", ""));
		}
		
		ttdz.setStartdate("");
		ttdz.setEnddate("");
		ttdz.setProductname(ttdz.getTitle());
		if(ttdz.getItemid()!=null&&!ttdz.getItemid().equals("")){
//			ttdz = getNameByid(ttdz);
		}
	
		return ttdz;

	}

	private static void getItemsByCategoryId(String pid ,int p) {
		Hashtable<String, TianTianDaZheBean> list = new Hashtable<String, TianTianDaZheBean>();
             while(!empty(pid,p)) {
            	 p=p-1;
             } 

             int i=1;
             while (i<=p) {
             
		     String content = "";
			try {
				content = new NetManager().getContentGBK("http://tejia.taobao.com/tejiaList.htm?pid="+ pid +"&p="+i);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		     
		     content = content.replaceAll(">[ \n\t\r]*<", "><");
			 Pattern pattern=Pattern.compile("<ul class=\"list.*?</ul>");
			 Matcher m_content=pattern.matcher(content); 
			 
		 if(m_content.find()){
			 content = m_content.group();
			 String content7 = content;
			 Pattern pattern1=Pattern.compile("<li.{1,2}class=\"lists.*?</li>");
			 
			 Matcher m_item =pattern1.matcher(content7);
		 while (m_item.find()) {
				TianTianDaZheBean ttdzb = getTTDZBean(m_item.group());
				if(!"".equals(ttdzb.getTitle()))
					list.put(ttdzb.getItemid(), ttdzb);
				
	     }
		}
		i++;
        }

		cache = getTTDZB(list);
		//添加到缓存
		TTTeJiaCacher.getInstance().setList(cache, pid);
		cachetable.put(category.get(pid), cache);
	}

	public static void initFromWebsite() {
		Hashtable<String, TianTianDaZheBean> list = new Hashtable<String, TianTianDaZheBean>();

		String reg_99_10 = "<div class=\"t-ten tcon\".*?</ul></div>", reg_99_14 = "<div class=\"t-fourteen tcon\".*?</ul></div>", reg_99_20 = "<div class=\"t-twenty tcon\".*?</ul></div>";

		Pattern p_99_10 = Pattern.compile(reg_99_10);
		Pattern p_99_14 = Pattern.compile(reg_99_14);
		Pattern p_99_20 = Pattern.compile(reg_99_20);		
		cache.clear();
		String content = "";
		try {
			content = new NetManager().getContentGBK("http://tejia.taobao.com/");
//			System.out.println(content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		content = content.replaceAll(">[ \n\t\r]*<", "><");

		Matcher m_99_10 = p_99_10.matcher(content);

		while (m_99_10.find()) {
			Matcher m_item = Pattern.compile("<li.*?</li>").matcher(m_99_10.group());
			list = new Hashtable<String, TianTianDaZheBean>();
			while (m_item.find()) {
               
				TianTianDaZheBean ttdzb = getTTDZBean(m_item.group());
				if(isidExit(m_item.group()) && !"".equals(ttdzb.getTitle())) {
				list.put(ttdzb.getItemid(), ttdzb);
				}
			}
			cache = getTTDZB(list);
		    
			//添加到缓存
			TTTeJiaCacher.getInstance().setList(cache, "9910");
			cachetable.put(category.get("9910"), cache);
		}

		Matcher m_99_14 = p_99_14.matcher(content);
		while (m_99_14.find()) {
			Matcher m_item = Pattern.compile("<li.*?</li>").matcher(m_99_14.group());
			list = new Hashtable<String, TianTianDaZheBean>();
			while (m_item.find()) {
				
				TianTianDaZheBean ttdzb = getTTDZBean(m_item.group());

				
				if(isidExit(m_item.group())&& !"".equals(ttdzb.getTitle())) {
				list.put(ttdzb.getItemid(), ttdzb);
				}
			}
			cache = getTTDZB(list);
			
			
			//添加到缓存
			TTTeJiaCacher.getInstance().setList(cache, "9914");
			cachetable.put(category.get("9914"), cache);

		}

		Matcher m_99_20 = p_99_20.matcher(content);
		while (m_99_20.find()) {
			Matcher m_item = Pattern.compile("<li.*?</li>").matcher(m_99_20.group());
			list = new Hashtable<String, TianTianDaZheBean>();
			while (m_item.find()) {
				TianTianDaZheBean ttdzb = getTTDZBean(m_item.group());
				if(isidExit(m_item.group())&& !"".equals(ttdzb.getTitle())) {
				list.put(ttdzb.getItemid(), ttdzb);
				}
			}
			cache = getTTDZB(list);
			
			//添加到缓存
			TTTeJiaCacher.getInstance().setList(cache, "9920");
			cachetable.put(category.get("9920"), cache);
		}
		getItemsByCategoryId("1",10);
		getItemsByCategoryId("2",10);
		getItemsByCategoryId("3",10);
		getItemsByCategoryId("4",10);
		getItemsByCategoryId("5",10);
		getItemsByCategoryId("6",10);
		getItemsByCategoryId("7",10);
		getItemsByCategoryId("8",10);
		getItemsByCategoryId("9",10);

		//channelTable.put("", cachetable);
		TeJiaCateogry2Cacher cacher = new TeJiaCateogry2Cacher();
		cacher.clearCategoryXml();

	}

	public static TianTianDaZheBean getNameByid(TianTianDaZheBean tb) {
			try {
				TeJiaGoodsBean i = TaobaoManager.getItem(tb.getItemid());
				if (i != null) {
					tb.setOriginalPrice((int) (Float.parseFloat(i.getPrice_low()) * 100)+ "");
					if(tb.getPicurl()==null||tb.getPicurl().equals("")){
						tb.setPicurl(i.getPic_url());
					}
					tb.setDiscount((int) (10 * Float.parseFloat(tb.getNewprice()) / (Float.parseFloat(i.getPrice_low())))+ "");
					if (i.getTitle() != null && !i.getTitle().equals("")) {
						tb.setTitle(i.getTitle());
						tb.setProductname(i.getTitle());
					} else {
						tb.setProductname(tb.getTitle());
					}

					if (i.getClickURL() != null&& !i.getClickURL().equals(""))
						tb.setClickUrl(i.getClickURL());
					
					tb.setStartime("");
					tb.setEnddate("");
					tb.setStartdate("");
					return tb;
				} else {
					return tb;
				}
		} catch (Exception e) {
			return tb;
		}

	}
	
	public static boolean empty(String pid , int p ) {
		
		String content = "";
		try {
			content = new  NetManager().getContentGBK("http://tejia.taobao.com/tejiaList.htm?pid="+ pid +"&p="+ p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 content = content.replaceAll(">[ \n\t\r]*<", "><");
		 Pattern pattern=Pattern.compile("<ul class=\"list.*?</ul>");
		 Matcher m_content=pattern.matcher(content);
		 if(m_content.find()){
		return true;
		 }
		 return  false;
	}
		 
    public static boolean isidExit(String s) {
    	Pattern pattern=Pattern.compile("<a  href=\"http://item.taobao.com.*?</a>");
		 Matcher m_content=pattern.matcher(s); 
		 
	 if(m_content.find()) {
		 return true;
	 }
		 return false;
    }
    
       public static ArrayList<TianTianDaZheBean> getTTDZB(Hashtable<String, TianTianDaZheBean> list) {
			ArrayList<TianTianDaZheBean> cache = new ArrayList<TianTianDaZheBean>();
//			String ids = ",";
			Enumeration<String> e = list.keys();
			
			
//			while(e.hasMoreElements()){
//				int i = 0;
//				ids = ",";
//				ArrayList<String> keys = new ArrayList<String>();
//				while(i<10&&e.hasMoreElements()){
//					
//					String key = e.nextElement();
//					i++;
//					ids = ids + key + ",";
//					keys.add(key);
//				}
//				Hashtable<String, String> urls = getUrlsByids(ids);
//				
//				for (String key : keys) {
//					TianTianDaZheBean tem = list.get(key);
//					if (urls.containsKey(tem.getItemid())) {
//						tem.setClickUrl(urls.get(tem.getItemid()));
//					}else { 
//						tem.setClickUrl("http://a.m.tmall.com/i"+tem.getItemid()+".htm");
//					}
//					cache.add(tem);
//			   }
//				
//			}
			
			while(e.hasMoreElements()){
				TianTianDaZheBean tem = list.get(e.nextElement());
				tem.setClickUrl(Config.SKIP_URL + "itemid=" + tem.getItemid());  
				cache.add(tem);
			}
			
            ArrayList<TianTianDaZheBean> cache_temp = new ArrayList<TianTianDaZheBean>();
			
			while (cache.size() != 0) {
				Random r = new Random();
				int s = (int) (r.nextDouble() * cache.size());
				cache_temp.add(cache.get(s));
				cache.remove(s);
			}
			cache = cache_temp;
			return cache;
	 }
    
    
/*	public static ArrayList<TianTianDaZheBean> getTTDZB(Hashtable<String, TianTianDaZheBean> list) {
		ArrayList<TianTianDaZheBean> cache = new ArrayList<TianTianDaZheBean>();
		String ids = ",";
		Enumeration<String> e = list.keys();
		while (e.hasMoreElements()) {
			ids = ids + e.nextElement() + ",";
		}
		Hashtable<String, String> urls = getUrlByid(ids, "", "");
		Enumeration<String> e1 = list.keys();
		while (e1.hasMoreElements()) {
			TianTianDaZheBean tem = list.get(e1.nextElement());
			if (urls.containsKey(tem.getItemid())) {
				tem.setClickUrl(urls.get(tem.getItemid()));
			}else { 
				tem.setClickUrl("http://a.m.tmall.com/i"+tem.getItemid()+".htm");
			}
			cache.add(tem);
		}
		ArrayList<TianTianDaZheBean> cache_temp = new ArrayList<TianTianDaZheBean>();
		while (cache.size() != 0) {
			Random r = new Random();
			int s = (int) (r.nextDouble() * cache.size());
			cache_temp.add(cache.get(s));
			cache.remove(s);
		}
		cache = cache_temp;
		return cache;
	}

*/
    
       public static void main(String[] args) {
	   }
      
	
}
