package cn.youhui.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import org.json.JSONArray;
import org.json.JSONObject;
import cn.youhui.bean.SuiShouAction;
import cn.youhui.bean.TianTianDaZheBean;
import cn.youhui.bean.KeyCategoryBean.KeywordBean;
import cn.youhui.common.Config;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.ramdata.RefreshTimeCacher;
import cn.youhui.ramdata.TTTeJiaCacher;
import cn.youhui.ramdata.TagCacher;
import cn.youhui.ramdata.TeJiaCateogry2Cacher;
import cn.youhui.utils.NetManager;
import cn.youhui.utils.OuterCode;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhui.utils.SuiShouActionUtil;

import com.google.gson.Gson;

/**
 * 天天特价数据操作
 * @author belonghu
 *2014/05/21
 *
 */
public class TianTianTeJiaManager {

	public static Hashtable<String, ArrayList<TianTianDaZheBean>> cachetable = new Hashtable<String, ArrayList<TianTianDaZheBean>>();
	
	private static int page_count = 12;
	
	private static String cachekey = "tttj";
	
	public static HashMap<String,String> map = new HashMap<String,String>();
	
	static{		
		map.put("1", "时尚女装");
		map.put("2", "品质男装");
		map.put("3", "男鞋女鞋");
		map.put("4", "包包配饰");
		map.put("5", "美容护肤");
		map.put("6", "数码家电");
		map.put("7", "家具生活");
		map.put("8", "美食特产");
		map.put("9", "母婴用品");
		map.put("10", "车品户外");
		map.put("11", "舒适内衣");
	}
	
	/**
	 * 获取天天特价数据结果
	 * @param page
	 * @param uid
	 * @param keyword
	 * @param type
	 * @param platform
	 * @return
	 */
	public static String getResult(String page, String uid, String keyword,String type, String platform){
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
	
	/**
	 * 获取天天特价数据方法
	 * @param keyword
	 * @param type
	 * @return
	 */
	private static ArrayList<TianTianDaZheBean> dosearch(String keyword,String type) {
		
		TTTeJiaCacher cacher = TTTeJiaCacher.getInstance();

		if (cacher.exists(type)) {
					return cacher.getList(type);

			} else {
				return new ArrayList<TianTianDaZheBean>();

			}
	}
	
	/**
	 * 控制更新频率
	 */
	public static void updateTejiaByTime(){
		long time = RefreshTimeCacher.getTime(cachekey);
		if(System.currentTimeMillis()-time < 1000*60*60){
			
		}else{			
			updateTejia();
			RefreshTimeCacher.setTime(cachekey);
		}
	}
	
	/**
	 * 更新天天特价数据
	 */
	public static void updateTejia(){

		try {
			long time = System.currentTimeMillis();
			String content = NetManager.getInstance().getContent("http://fetch.b17.cn/tttj");
			System.out.println("tiantiantejia update"+(System.currentTimeMillis()-time));
			if(content != null &&!"".equals(content)){
				JSONObject jso = new JSONObject(content);
				if(jso.has("status")){
					String status = jso.getString("status");
					if(status != null && !"".equals(status) && status.equals("0")){
						String keywordList = jso.getString("result");
						JSONArray jsa = new JSONArray(keywordList);															
						for(int i=0 ; i < jsa.length();i++){
							ArrayList<TianTianDaZheBean> itemlist = new ArrayList<TianTianDaZheBean>();
							String ItemListStr = jsa.getString(i);
							JSONObject itemListJso = new JSONObject(ItemListStr);
							String itemList = itemListJso.getString((i+1)+"");
							JSONArray itemListJsa = new JSONArray(itemList);
							Gson g = new Gson();
							for(int k = 0; k < itemListJsa.length(); k ++){
								String itemJson = itemListJsa.getString(k);
								TianTianDaZheBean item = g.fromJson(itemJson, TianTianDaZheBean.class);
								itemlist.add(item);
							}
							//添加到缓存
							TTTeJiaCacher.getInstance().setList(itemlist, (i+1)+"");
							cachetable.put(map.get((i+1)+""), itemlist);
						}
						TeJiaCateogry2Cacher cacher = new TeJiaCateogry2Cacher();
						cacher.clearCategoryXml();

						System.out.println(jsa.length());
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	public static void main(String[] args) {
		new JedisHashManager("refreshtime.refreshcontrol").add("tttj", 0+"");
		new JedisHashManager("refreshtime.refreshcontrol").add("jhs", 0+"");
		new JedisHashManager("refreshtime.refreshcontrol").add("jhsbrand", 0+"");
//		updateTejia();
	}
}
