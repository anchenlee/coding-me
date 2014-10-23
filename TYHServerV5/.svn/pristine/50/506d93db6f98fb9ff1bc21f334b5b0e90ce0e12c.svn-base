package cn.youhui.manager;


import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;
import cn.youhui.bean.JuhuasuanBean;
import cn.youhui.bean.KeyCategoryBean.KeywordBean;
import cn.youhui.common.Config;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.ramdata.JuHuaSuanCacher;
import cn.youhui.ramdata.TagCacher;
import cn.youhui.utils.NetManager;
import cn.youhui.utils.RespStatusBuilder;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.ItemData;
import com.taobao.api.request.JuGroupGetRequest;
import com.taobao.api.request.JuGroupidsGetRequest;
import com.taobao.api.response.JuGroupGetResponse;
import com.taobao.api.response.JuGroupidsGetResponse;

public class JUhuasuan_newGetTB {
	//聚划算淘宝接口
	public static String url = "http://gw.api.taobao.com/router/rest";
	private static String akey = "12347692";  //"12415289"; 
	private static String asecret = "c62deb79ce636f9cbac7c074269ab1d6";    //"8a381fcabc1b74003c689550a95a0f0a";  
	public static String appkey =  akey;
	public static String secret = asecret;
	public static String ju_url = "http://gw.api.taobao.com/router/rest";
	public static String ju_appkey = akey;
	public static String ju_secret = asecret;
//	public static String ju_taobaoid = "yaoyuewudi";
//	public static String taobaoid = "yaoyuewudi";
	Hashtable<String, String> category = new Hashtable<String, String>();
	static int page_count = 12;
	public static Long lastupdatetime = 0L;
	public static String juhuansuan_url = "http://ju.taobao.com/index.htm?frontCatId=";
	public static String catList_url = "http://skip.ju.taobao.com/json/tg/juSkip.htm";
	/**
	 * @return
	 */
	public static String getResult(String page ,String uid,String keyword,final String category,String channel) {
		StringBuffer sb = new StringBuffer();
//		if (System.currentTimeMillis() - lastupdatetime > 30 * 60 * 1000) {
//			lastupdatetime = System.currentTimeMillis();
//			new Thread() {
//				public void run() {
//					new JUhuasuan_newGetTB();
//					JUhuasuan_newGetTB.getJuhuasuanBeanFromNet();
//				}
//			}.start();
//		}
		if (page == null) {
			page = "1";
		}
		int a = 0;
		try {
			a = Integer.parseInt(page);
		} catch (Exception e) {
			a = 1;
		}
		
		List<JuhuasuanBean> searchResult = JuHuaSuanCacher.getInstance().getList(category, a , page_count);
		
		long total = JuHuaSuanCacher.getInstance().getTotal(category);
		
		int totalpage =(int) total / page_count; 
		if(total % page_count > 0){
			totalpage ++;
		}
		
		// String jhsTagId = Tagid2TagnameCacher.getInstance().getIdbyName("聚划算");
		String jhsTagId = "589";
		KeywordBean jhsbean = TagCacher.getInstance().getTag(jhsTagId);
		if(jhsbean != null && jhsbean.getChaye_icon() != null && !"".equals(jhsbean.getChaye_icon()) && jhsbean.getChayeAction() != null){
			sb.append("<chaye>");
			sb.append("<icon><![CDATA[").append(jhsbean.getChaye_icon()).append("]]></icon>");
			sb.append("<wh_ratio><![CDATA[").append(jhsbean.getChayeIconSize()).append("]]></wh_ratio>");
			sb.append(jhsbean.getChayeAction().toXML());
			sb.append("</chaye>");
		}
		sb.append("<juhuasuan>");
		for (JuhuasuanBean bean : searchResult) {
			sb.append(bean.getContent());
		}
		sb.append("</juhuasuan>");
		return RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, "", totalpage, a, sb.toString()).toString();
	}
	
	public static List<JuhuasuanBean> getResult(String page,final String category) {
//		if (System.currentTimeMillis() - lastupdatetime > 30 * 60 * 1000) {
//			lastupdatetime = System.currentTimeMillis();
//			new Thread() {
//				public void run() {
//					new JUhuasuan_newGetTB();
//					JUhuasuan_newGetTB.getJuhuasuanBeanFromNet();
//				}
//			}.start();
//		}
		if (page == null) {
			page = "1";
		}
		int a = 0;
		try {
			a = Integer.parseInt(page);
		} catch (Exception e) {
			a = 1;
		}
		
		List<JuhuasuanBean> searchResult = JuHuaSuanCacher.getInstance().getList(category, a , page_count);		
		return searchResult;

	}


	public static ArrayList<JuhuasuanBean> getJuhuasuanFromNet(String page_no)
			throws ApiException {
		Hashtable<String, JuhuasuanBean> tempList = new Hashtable<String, JuhuasuanBean>();
		ArrayList<JuhuasuanBean> list = new ArrayList<JuhuasuanBean>();
		TaobaoClient client = new DefaultTaobaoClient(ju_url, ju_appkey, ju_secret);
		JuGroupidsGetRequest req = new JuGroupidsGetRequest();
		req.setTerminalType("WAP");
		req.setPageNo(Long.parseLong(page_no));
		req.setPageSize(40L);

		JuGroupidsGetResponse response = client.execute(req);
		if (response.getGroupIds() != null) {
			for (Long groupsId : response.getGroupIds()) {
				JuGroupGetRequest req2 = new JuGroupGetRequest();

				req2.setGroupId(groupsId);
				JuGroupGetResponse response2 = client.execute(req2);
				if (response2 == null) {
				}
				if (response2.getGroup() == null) {
				}
				if (response2.getGroup().getItemList() == null) {
				}
				if (response2.getGroup().getItemList() != null) {
					for (int j = 0; j < response2.getGroup().getItemList()
							.size(); j++) {
						JuhuasuanBean jb = new JuhuasuanBean();
						ItemData id = response2.getGroup().getItemList().get(j);
						jb.setCantuanrenshu(id.getSoldCount() + "");// 参团人数
						jb.setClickurl("http://a.m.taobao.com/i"
								+ id.getItemId() + ".htm?tg_key=small_index_");// 点击地址未转换
						jb.setDiscount((int) (Float.parseFloat(id.getDiscount()) * 100)
								+ "");// 商品折扣
						jb.setEnddate(id.getOnlineEndTime() + "");// 团购结束时间
						jb.setItemid(id.getItemId() + "");// 商品Id
						jb.setNewprice(id.getActivityPrice() + "");// 聚划算价格
						jb.setOriginalPrice(id.getOriginalPrice() + "");// 聚划算原价
						jb.setPicurl("http://img.taobaocdn.com/bao/uploaded/"
								+ id.getPicUrl());
						jb.setProductname(id.getShortName());
						jb.setStartime(id.getOnlineStartTime() + "");
						jb.setTitle(id.getLongName());//
						jb.setParentCat(id.getParentCategory());
						tempList.put(jb.getItemid(), jb);

					}
				}
			}
		}

		ArrayList<String> ids = new ArrayList<String>();
		Enumeration<String> e = tempList.keys();

		while (e.hasMoreElements()) {
			ids.add(e.nextElement());
		}

	//	Hashtable<String, String> hsa = JUhuasuan_newGetTB.getUrlByid(ids, "");
		Enumeration<String> e1 = tempList.keys();
		while (e1.hasMoreElements()) {

			JuhuasuanBean temp = tempList.get(e1.nextElement());
//			if (hsa.containsKey(temp.getItemid())) {
//				temp.setClickurl(hsa.get(temp.getItemid()));
//			}
			list.add(temp);
		}

		return list;
	}

	
	
	 public static ArrayList<JuhuasuanBean> getJuhuasuanbean(Hashtable<String, JuhuasuanBean> list) {
			ArrayList<JuhuasuanBean> cache = new ArrayList<JuhuasuanBean>();
			Enumeration<String> e = list.keys();
						
			while(e.hasMoreElements()){
				JuhuasuanBean tem = list.get(e.nextElement());
				tem.setClickurl(Config.SKIP_URL + "from_channel=7&from_value=589&itemid=" + tem.getItemid());
				cache.add(tem);
			}
		
			ArrayList<JuhuasuanBean> cache_temp = new ArrayList<JuhuasuanBean>();
			
			
			while (cache.size() != 0) {
				Random r = new Random();
				int s = (int) (r.nextDouble() * cache.size());
				cache_temp.add(cache.get(s));
				cache.remove(s);
			}
			cache = cache_temp;
			return cache;
	 }
	 
	 
	   public static  void getJuhuasuanBeanFromNet()
	   {
		   List<String> frontCatIdList = getCatList() ;
//		   List<String> frontCatIdList = new ArrayList<String>();
//		   frontCatIdList.add("1000");
		   ArrayList<JuhuasuanBean> allList = new ArrayList<JuhuasuanBean>();
		   ArrayList<JuhuasuanBean> juList = new ArrayList<JuhuasuanBean>();
		   ArrayList<JuhuasuanBean> fuList = new ArrayList<JuhuasuanBean>();
		   ArrayList<JuhuasuanBean> shiList = new ArrayList<JuhuasuanBean>();
		   ArrayList<JuhuasuanBean> dianList = new ArrayList<JuhuasuanBean>();
		   ArrayList<JuhuasuanBean> otherList = new ArrayList<JuhuasuanBean>();

		   
		   String brandPar = "<ul class=\"clearfix\".*?</ul>";
		   for(String frontCatId : frontCatIdList)
		   {		
			   List<JuhuasuanBean> list = new ArrayList<JuhuasuanBean>();
			   try 
			   {
				   String content = NetManager.getInstance().getContentGBK(juhuansuan_url+frontCatId);
				   Pattern pattern = Pattern.compile(brandPar);
				   Matcher matcher = pattern.matcher(content);
				   if(matcher.find())
				   {
					   String brand = matcher.group();
					   String liPar = "<li class=\"brand.*?</li>";
					   pattern = Pattern.compile(liPar);
					   matcher = pattern.matcher(brand);						   
					   while(matcher.find())
						{
							 String li = matcher.group();
//							 System.out.println(li);
							 String url = "<a href=\".*? target";
							 Pattern pattern1 = Pattern.compile(url);
							 Matcher matcher1 = pattern1.matcher(li);
							 if(matcher1.find())
							 {								 
								 String u = matcher1.group();
								 String juItemUrl = u.replaceAll("<a href=\"", "").replaceAll("\" target", "");
								 String itemContent = NetManager.getInstance().getContentGBK(juItemUrl);
								 List<JuhuasuanBean> itemList = getJuhuasuanList(itemContent);
								 list.addAll(itemList);
							 }
						 }
					   List<JuhuasuanBean> itemList1 = getJuhuasuanList(brand);
					   list.addAll(itemList1);
				   } 
			   } 
			   catch (Exception e) 
			   {
				   e.printStackTrace();
			   }
			   
			   
			   /**
			    * 百货 37000
			    * 车品 36000
			    *  1 服饰 7000男装 1000女装 4000 内衣 38000 运动
			    *  2 时尚 2000 美妆 42000 饰品
			    * 3 鞋包  3000
			    * 4 电器 34000家电 43000 数码
			    * 5 食品 5000
			    * 6 母婴 6000
			    * 7 居家 35000
			    * 8 其他 
			    * 9 居家装
			    * 10 聚名品 
			    * -1,quanbu 
			    * 0,quanbu
			 **/
			   Hashtable<String, JuhuasuanBean> list1 = new Hashtable<String, JuhuasuanBean>();
			   for(int i=0;i<list.size();i++) {
					JuhuasuanBean Juhuasuan = list.get(i);
					if (Juhuasuan.getTitle().indexOf("聚家装") > -1) 
					{
						juList.add(Juhuasuan);
					}
					list1.put(Juhuasuan.getItemid(), Juhuasuan);
				}
			   allList.addAll(list);
			   if(list1 != null &&list1.size()>0)
			   {					   				   
				   ArrayList<JuhuasuanBean> cache = getJuhuasuanbean(list1);
				   if(frontCatId.equals("7000")||frontCatId.equals("1000")||frontCatId.equals("4000")||frontCatId.equals("38000"))
				   {
					   fuList.addAll(list);
//					   JuHuaSuanCacher.getInstance().setList(cache, "1");
				   }
				   else if(frontCatId.equals("2000")||frontCatId.equals("42000"))
				   {
					   shiList.addAll(list);
//					   JuHuaSuanCacher.getInstance().setList(cache, "2");
				   }
				   else if(frontCatId.equals("3000"))
				   {
					   JuHuaSuanCacher.getInstance().setList(cache, "3");
				   }
				   else if(frontCatId.equals("34000")||frontCatId.equals("43000"))
				   {
					   dianList.addAll(list);
//					   JuHuaSuanCacher.getInstance().setList(cache, "4");
				   }
				   else if(frontCatId.equals("5000"))
				   {
					   JuHuaSuanCacher.getInstance().setList(cache, "5");
				   }
				   else if(frontCatId.equals("6000"))
				   {
					   JuHuaSuanCacher.getInstance().setList(cache, "6");
				   }
				   else if(frontCatId.equals("35000"))
				   {
					   JuHuaSuanCacher.getInstance().setList(cache, "7");
				   }
				   else 
				   {
					   otherList.addAll(list);
//					   JuHuaSuanCacher.getInstance().setList(cache, "8");
				   }
				   cache.clear();
			   }
		   }
		   //聚家装
		   Hashtable<String, JuhuasuanBean> map = new Hashtable<String, JuhuasuanBean>();
		   for(JuhuasuanBean Juhuasuan : juList) 
		   {
			   map.put(Juhuasuan.getItemid(), Juhuasuan);
			}
		   if(map != null && map.size()>0)
		   {				   
			   ArrayList<JuhuasuanBean> mixList = getJuhuasuanbean(map);
			   JuHuaSuanCacher.getInstance().setList(mixList, "9");
		   }
		   
		   //服饰
		   Hashtable<String, JuhuasuanBean> fuMap = new Hashtable<String, JuhuasuanBean>();
		   for(JuhuasuanBean Juhuasuan : fuList) 
		   {
			   fuMap.put(Juhuasuan.getItemid(), Juhuasuan);
		   }
		   if(fuMap != null && fuMap.size()>0)
		   {				   
			   ArrayList<JuhuasuanBean> allMixList = getJuhuasuanbean(fuMap);
			   JuHuaSuanCacher.getInstance().setList(allMixList, "1");
		   }
		   
		   //时尚
		   Hashtable<String, JuhuasuanBean> shiMap = new Hashtable<String, JuhuasuanBean>();
		   for(JuhuasuanBean Juhuasuan : shiList) 
		   {
			   shiMap.put(Juhuasuan.getItemid(), Juhuasuan);
		   }
		   if(shiMap != null && shiMap.size()>0)
		   {				   
			   ArrayList<JuhuasuanBean> allMixList = getJuhuasuanbean(shiMap);
			   JuHuaSuanCacher.getInstance().setList(allMixList, "2");
		   }
		   
		   //电器
		   Hashtable<String, JuhuasuanBean> dianMap = new Hashtable<String, JuhuasuanBean>();
		   for(JuhuasuanBean Juhuasuan : dianList) 
		   {
			   dianMap.put(Juhuasuan.getItemid(), Juhuasuan);
		   }
		   if(dianMap != null && dianMap.size()>0)
		   {				   
			   ArrayList<JuhuasuanBean> allMixList = getJuhuasuanbean(dianMap);
			   JuHuaSuanCacher.getInstance().setList(allMixList, "4");
		   }
		   
		   //其他
		   Hashtable<String, JuhuasuanBean> otherMap = new Hashtable<String, JuhuasuanBean>();
		   for(JuhuasuanBean Juhuasuan : otherList) 
		   {
			   otherMap.put(Juhuasuan.getItemid(), Juhuasuan);
		   }
		   if(otherMap != null && otherMap.size()>0)
		   {				   
			   ArrayList<JuhuasuanBean> allMixList = getJuhuasuanbean(otherMap);
			   JuHuaSuanCacher.getInstance().setList(allMixList, "8");
		   }
		   
		   //所有的
		   Hashtable<String, JuhuasuanBean> allMap = new Hashtable<String, JuhuasuanBean>();
		   for(JuhuasuanBean Juhuasuan : allList) 
		   {
			   allMap.put(Juhuasuan.getItemid(), Juhuasuan);
		   }
		   if(allMap != null && allMap.size()>0)
		   {				   
			   ArrayList<JuhuasuanBean> allMixList = getJuhuasuanbean(allMap);
			   JuHuaSuanCacher.getInstance().setList(allMixList, "-1");
		   }
		   
		   //手机专享
		   allMap = new Hashtable<String, JuhuasuanBean>();
		   for(JuhuasuanBean Juhuasuan : allList) 
		   {
			   allMap.put(Juhuasuan.getItemid(), Juhuasuan);
		   }
		   if(allMap != null && allMap.size()>0)
		   {				   
			   ArrayList<JuhuasuanBean> allMixList = getJuhuasuanbean(allMap);
			   JuHuaSuanCacher.getInstance().setList(allMixList, "0");
		   }
	   }
	   
	   /**
	    * 
	    * @return
	    */
	   public static List<String> getCatList()
	   {
		   List<String> list = new ArrayList<String>();
			try {
				String content = NetManager.getInstance().getContentGBK(catList_url);
				JSONObject jso = new JSONObject(content);
				JSONArray itemCategories = jso.getJSONArray("itemCategories");
				if(itemCategories != null &&itemCategories.length()>0)
				{
					for(int i = 0 ; i < itemCategories.length() ; i ++)
					{
						JSONObject catidJso = itemCategories.getJSONObject(i);
						String catid = catidJso.getString("catId");
						list.add(catid);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
	   }
	   
	   
	   
	   public static List<JuhuasuanBean> getJuhuasuanList(String li)
	   {
		   List<JuhuasuanBean> list = new ArrayList<JuhuasuanBean>();
		   String liPar = "<li class=\"notbegin.*?</li>";
		   Pattern pattern = Pattern.compile(liPar);
		   Matcher matcher = pattern.matcher(li);					
//		   System.out.println(li);
		   while(matcher.find())
		   {
			   String item = matcher.group();
			   JuhuasuanBean jhs = getJuhuasuanBean(item);
			   if(jhs != null)
			   {				   
				   list.add(jhs);
			   }
		   }
		   liPar = "<li class data-rank.*?</li>";
		   pattern = Pattern.compile(liPar);
		   matcher = pattern.matcher(li);
		   while(matcher.find())
		   {
			   String item = matcher.group();
			   JuhuasuanBean jhs = getJuhuasuanBean(item);
			   if(jhs != null)
			   {				   
				   list.add(jhs);
			   }
		   }
		   liPar = "<li class=\"\\s*\" data-rank.*?</li>";
		   pattern = Pattern.compile(liPar);
		   matcher = pattern.matcher(li);
		   while(matcher.find())
		   {
			   String item = matcher.group();
			   JuhuasuanBean jhs = getJuhuasuanBean(item);
			   if(jhs != null)
			   {				   
				   list.add(jhs);
			   }
		   }
		   
		 //第四种格式
		   liPar = "<li class=\"item-small-.*?</li>";
		   pattern = Pattern.compile(liPar);
		   matcher = pattern.matcher(li);
		   while(matcher.find())
		   {
			   String item = matcher.group();
			   JuhuasuanBean jhs = getJuhuasuanBean(item);
			   if(jhs != null)
			   {				   
				   list.add(jhs);
			   }
		   }
		   return list;
	   }
	   
	   public static JuhuasuanBean getJuhuasuanBean(String item)
	   {
		   //clickurl itemid
		   JuhuasuanBean jhs = new JuhuasuanBean();
		   String par = "href=\".*?data-spm";
		   Pattern pattern1 = Pattern.compile(par);
		   Matcher matcher1 = pattern1.matcher(item);
		   if(matcher1.find())
		   {
			   String url = matcher1.group();
			   url = url.replaceAll("href=\"", "").replaceAll("\"  data-spm", "");
			   jhs.setClickurl(url);
			   par = "item_id=.*?&amp";
			   pattern1 = Pattern.compile(par);
			   matcher1 = pattern1.matcher(item);
			   if(matcher1.find())
			   {
				   String item_id = matcher1.group();
				   item_id = item_id.replaceAll("item_id=", "").replaceAll("&amp", "");
				   if(item_id.matches( "[0-9]{1,}"))
				   {						   
					   jhs.setItemid(item_id);
				   }
				   else 
				   {
					   return null;
				   }
			   }
		   }
		   //原价
		   par = "<del class=\"orig-price.*?</del>";
		   pattern1 = Pattern.compile(par);
		   matcher1 = pattern1.matcher(item);
		   if(matcher1.find())
		   {
			   String orign_price = matcher1.group();
			   orign_price = orign_price.replaceAll("<del class=\"orig-price", "").replaceAll("</del>", "").replaceAll("\">&yen;", "");
			   try
			   {					
				   orign_price = Double.parseDouble(orign_price)*100+"";
			   } 
			   catch (Exception e) 
			   {
				   return null;
			   }
			   jhs.setOriginalPrice(orign_price);
		   }
		   
		   //现价
		   par = "<span class=\"price.*?</span>";
		   pattern1 = Pattern.compile(par);
		   matcher1 = pattern1.matcher(item);
		   if(matcher1.find())
		   {
			   String new_price = matcher1.group();
			   new_price = new_price.replaceAll("<span class=\"price\">", "").replaceAll("<i>&yen;</i><em>", "").replaceAll("</em></span>", "").replaceAll("</em>", "").replaceAll("</span>", "");
			   try 
			   {
				   new_price = Double.parseDouble(new_price)*100+"";
			   } 
			   catch (Exception e) 
			   {
				   return null;
			   }
			   jhs.setNewprice(new_price);
		   }
		   
		   //折扣
		   par = "<span class=\"discount.*?</span>";
		   pattern1 = Pattern.compile(par);
		   matcher1 = pattern1.matcher(item);
		   if(matcher1.find())
		   {
			   String discount = matcher1.group();
			   discount = discount.replaceAll("<span class=\"discount\"><em>", "").replaceAll("</em>折</span>", "");
			   try 
			   {
				   discount = Double.parseDouble(discount)*100+"";
			   } 
			   catch (Exception e) 
			   {
				   return null;
			   }
			   jhs.setDiscount(discount);
		   }
		   
		   //图片地址
		   par = "<img class=\"item-pic.*?jpg\"";
		   pattern1 = Pattern.compile(par);
		   matcher1 = pattern1.matcher(item);
		   if(matcher1.find())
		   {
			   String pic = matcher1.group();
			   pic = pic.replaceAll("<img class=\"item-pic\"  data-ks-lazyload  =\"", "").replaceAll("\" />", "").replaceAll("\"", "");
			   pic = pic.replaceAll("<img class=item-pic data-ks-lazyload=", "");
//			   System.out.println(pic);
			   jhs.setPicurl(pic);
		   }
		   
		   //title			   
		   par = "<h3 title=\".*?>";
		   pattern1 = Pattern.compile(par);
		   matcher1 = pattern1.matcher(item);
		   if(matcher1.find())
		   {
			   String title = matcher1.group();
			   title = title.replaceAll("<h3 title=\"", "").replaceAll("\">", "");
			   jhs.setTitle(title);
			   jhs.setProductname(title);
		   }
		   
		   //参团人数
		   par = "<span class=\"sold-num.*?</span>";
		   pattern1 = Pattern.compile(par);
		   matcher1 = pattern1.matcher(item);
		   if(matcher1.find())
		   {
			   String renshu = matcher1.group();
			   renshu = renshu.replaceAll("<span class=\"sold-num\">", "").replaceAll("</span>", "").replaceAll("[^0-9]", "");
//			   System.out.println(renshu);
			   jhs.setCantuanrenshu(renshu);
		   }
		   if(jhs.getCantuanrenshu() == null || "".equals(jhs.getCantuanrenshu())){
			   jhs.setCantuanrenshu("0");
		   }
//			jb.setEnddate(id.getOnlineEndTime() + "");// 团购结束时间
//			jb.setStartime(id.getOnlineStartTime() + "");
//			jb.setParentCat(id.getParentCategory());
//		   System.out.println(jhs.getItemid()+"  "+jhs.getPicurl()+"  "+jhs.getClickurl()+"  "+jhs.getOriginalPrice()+"  "+jhs.getNewprice());
		   return jhs;
	   }
		
	   public static void main(String[] str) throws Exception {
//			// init();
//			lastupdatetime = System.currentTimeMillis();
			
//			getJuhuasuan();
//		   getJuhuasuanBeanFromNet();
			System.out.println("oooooooooooooookkkkkkkkkkkkkkkkk");
//			System.out.println(getUrlByid("26683860081"));
//			getJuhuasuanFromNet("1");
			// convertCacheUrl();
		}
//		public static Hashtable<String, String> getUrlByid(
//				ArrayList<String> num_id, String channel) {
//			Hashtable<String, String> urls = new Hashtable<String, String>();
//			String ids = "";
//			while (num_id.size() > 0) {
//				if (num_id.size() > 30) {
//					for (int i = 0; i < 30; i++) {
//						ids += "," + num_id.get(i);
//					}
//					for (int i = 0; i < 30; i++) {
//						num_id.remove(0);
//					}
	//
//				} else {
//					for (int i = 0; i < num_id.size(); i++) {
//						ids += "," + num_id.get(i);
//					}
//					int j = num_id.size();
//					for (int i = 0; i < j; i++) {
//						num_id.remove(0);
//					}
//				}
	//
//				TaobaoClient client = new DefaultTaobaoClient(Config.url,
//						Config.appkey, Config.secret);
//				TaobaokeItemsConvertRequest req = new TaobaokeItemsConvertRequest();
//				req.setFields("title,click_url,num_iid,commission,commission_rate,commission_num,commission_volume");
//				req.setNick(Config.taobaoid);
//				req.setNumIids(ids);
//				if (!channel.equals(""))
//					req.setOuterCode(channel);
//				req.setIsMobile(true);
//				try {
//					TaobaokeItemsConvertResponse response = client.execute(req);
//					List<TaobaokeItem> items = response.getTaobaokeItems();
//					if (items != null) {
//						for (TaobaokeItem item : items) {
	//
//							String id = item.getNumIid() + "";
//							String url = item.getClickUrl();
//							if (url != null && !url.equals("")) {
//								urls.put(id,url.replace(".jhtml?", ".jhtml?tg_key=small_index_&"));
//							}
//						}
//					}
//				} catch (ApiException e) {
//					e.printStackTrace();
//				}
//			}
//			;
	//
//			return urls;
	//
//		}
}

