package cn.youhui.manager;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

import cn.youhui.bean.JuhuasuanBean;
import cn.youhui.bean.SuiShouAction;
import cn.youhui.bean.KeyCategoryBean.KeywordBean;
import cn.youhui.ramdata.JuHuaSuanCacher;
import cn.youhui.ramdata.RefreshTimeCacher;
import cn.youhui.ramdata.TagCacher;
import cn.youhui.utils.NetManager;
import cn.youhui.utils.SuiShouActionUtil;

/**
 *聚划算数据操作
 * @author belonghu
 *2014/05/21
 *
 */
public class JuhuasuanNewManager {

	private static int page_count = 12;
	
	private static String cachekey = "jhs";
	
	/**
	 * 获取聚划算列表
	 * @param page
	 * @param category
	 * @return
	 */
	public static String getResult(String page,String category,String platform){
		
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
		String result = listToString(searchResult,platform);
		return result;
	}
	
	/**
	 * 聚划算列表转换成String
	 * @param list
	 * @return
	 */
	
	private static String listToString(List<JuhuasuanBean> list,String platform){
		StringBuffer sb = new StringBuffer();
		String jhsTagId = "589";
		KeywordBean jhsbean = TagCacher.getInstance().getTag(jhsTagId);
		if(jhsbean != null && jhsbean.getChaye_icon() != null && !"".equals(jhsbean.getChaye_icon()) && jhsbean.getChayeAction() != null){
			sb.append("<chaye>");
			sb.append("<icon><![CDATA[").append(jhsbean.getChaye_icon()).append("]]></icon>");
			sb.append("<wh_ratio><![CDATA[").append(jhsbean.getChayeIconSize()).append("]]></wh_ratio>");
			sb.append(new SuiShouAction(SuiShouActionUtil.getSuiShouActionUrl(platform,jhsbean.getChayeAction())).toXML());
			sb.append("</chaye>");
		}
		sb.append("<juhuasuan>");
		for (JuhuasuanBean bean : list) {
			sb.append(bean.getContent());
		}
		sb.append("</juhuasuan>");
		return sb.toString();
	}
	
	/**
	 * 控制更新频率
	 */
	public static void updateJuhuasuanByTime(){
		
		long time = RefreshTimeCacher.getTime(cachekey);
		if(System.currentTimeMillis()-time < 1000*60*60){
			
		}else{			
			updateJuhuasuan();
			RefreshTimeCacher.setTime(cachekey);
		}
	}
	
	/**
	 * 更新聚划算列表
	 */
	public static void updateJuhuasuan(){
		try {
			long time = System.currentTimeMillis();
			String content = NetManager.getInstance().getContent("http://fetch.b17.cn/jhs");
			System.out.println(System.currentTimeMillis()-time+" juhuasuan lasting time");
			if(content != null &&!"".equals(content)){
				JSONObject jso = new JSONObject(content);
				if(jso.has("status")){
					String status = jso.getString("status");
					if(status != null && !"".equals(status) && status.equals("0")){
						String keywordList = jso.getString("result");
						JSONArray jsa = new JSONArray(keywordList);															
						for(int i=0 ; i < jsa.length();i++){
							ArrayList<JuhuasuanBean> itemlist = new ArrayList<JuhuasuanBean>();
							String ItemListStr = jsa.getString(i);
							JSONObject itemListJso = new JSONObject(ItemListStr);
							int cat = 0;
							String itemList = "";
							for(int k = -1 ; k < 10;k++){
								if(itemListJso.has(k+"")){
									itemList = itemListJso.getString((k+""));
									cat = k;
								}
							}
							JSONArray itemListJsa = new JSONArray(itemList);
							Gson g = new Gson();
							for(int k = 0; k < itemListJsa.length(); k ++){
								String itemJson = itemListJsa.getString(k);
								JuhuasuanBean item = g.fromJson(itemJson, JuhuasuanBean.class);
								itemlist.add(item);
//								System.out.println(i +"  "+item.getTitle());
							}
							//添加到缓存
							System.out.println(cat+"   kjhgfda  "+ itemlist.size());
							JuHuaSuanCacher.getInstance().setList(itemlist, cat+"");
							System.out.println(JuHuaSuanCacher.getInstance().getTotal(cat+""));
//							TTTeJiaCacher.getInstance().setList(itemlist, (i+1)+"");
//							cachetable.put(map.get((i+1)+""), itemlist);
						}

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
		updateJuhuasuan();
		System.out.println("okkk");
	}
	
	
}
