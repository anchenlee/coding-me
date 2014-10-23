package cn.youhui.manager;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import cn.youhui.bean.TeJiaGoodsBean;
import cn.youhui.bean.KeyCategoryBean.KeywordBean;
import cn.youhui.common.SepaConfig;
import cn.youhui.ramdata.RefreshTimeCacher;
import cn.youhui.ramdata.Tag2ItemCacher;
import cn.youhui.ramdata.Tag2TagCacher;
import cn.youhui.ramdata.TagCacher;
import cn.youhui.ramdata.TagItemCacher;
import cn.youhui.utils.NetManager;

import com.google.gson.Gson;

/**
 * 品牌团数据操作
 * @author belonghu
 *2014/05/21
 *
 */
public class JuhuasuanBrandManager {

	private static final String PinPaiTuanTagId = "609";
	
	private static String cachekey = "jhsbrand";
	
	private static String juBrandKeyId = "010";
	
	/**
	 * 控制更新频率
	 */
	public static void updateBrandByTime(){
		long time = RefreshTimeCacher.getTime(cachekey);
		if(System.currentTimeMillis()-time < 1000*60*60){
			
		}else{			
			updateBrand();
			RefreshTimeCacher.setTime(cachekey);
		}
	}
	
	/**
	 * 更新品牌团数据
	 */
	public static void updateBrand(){
		try {
			long time = System.currentTimeMillis();
			String content = NetManager.getInstance().getContent("http://fetch.b17.cn/ppt");
			System.out.println("brand lasting time :"+(System.currentTimeMillis()-time));
			List<TeJiaGoodsBean> allItemlist = new ArrayList<TeJiaGoodsBean>();
			if(content != null &&!"".equals(content)){
				JSONObject jso = new JSONObject(content);
				if(jso.has("status")){
					String status = jso.getString("status");
					if(status != null && !"".equals(status) && status.equals("0")){
						String keywordList = jso.getString("result");
						JSONArray jsa = new JSONArray(keywordList);															
						String tagids = "";
						for(int i=0 ; i < jsa.length();i++){
							List<TeJiaGoodsBean> itemlist = new ArrayList<TeJiaGoodsBean>();
							JSONObject keywordItemJso = jsa.getJSONObject(i);
							JSONObject keywordItem = keywordItemJso.getJSONObject(i+"");
							String keywordJso = keywordItem.getString("keyword");
							Gson g = new Gson();
							KeywordBean keyword = g.fromJson(keywordJso, KeywordBean.class);
							TagCacher.getInstance().addTag(keyword);
							String itemListStr = keywordItem.getString("itemList");
							JSONArray itemJsa = new JSONArray(itemListStr);
							for(int j = 0; j < itemJsa.length();j++){
								String itemJson = itemJsa.getString(j);
								TeJiaGoodsBean item = g.fromJson(itemJson, TeJiaGoodsBean.class);
								itemlist.add(item);
							}
							allItemlist.addAll(itemlist);
							TagItemCacher.getInstance().addProducts(itemlist);
							   // tag.tag2item
							   Tag2ItemCacher.getInstance().refreshTag2Item(keyword.getId(), itemlist);
							   
							   tagids += keyword.getId() + SepaConfig.TAG_SEPA;
						}
						if(!"".equals(tagids))
						{
							Tag2ItemCacher.getInstance().refreshTag2Item(juBrandKeyId, allItemlist);
							Tag2TagCacher.getInstance().refreshTag2Tag(PinPaiTuanTagId, tagids);
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
		updateBrand();
	}
	
}
