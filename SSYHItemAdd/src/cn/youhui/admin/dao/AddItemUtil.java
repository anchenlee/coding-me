package cn.youhui.admin.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import cn.youhui.bean.KeywordToItem;
import cn.youhui.bean.ProfRecom;
import cn.youhui.bean.TagBean;
import cn.youhui.bean.TeJiaGoodsBean;
import cn.youhui.cache.dao.RecoItemCacheDAO;
import cn.youhui.cache.dao.SearchKeywordCacheDAO;
import cn.youhui.cache.dao.TagItemCacheDAO;
import cn.youhui.itemadd.dataadapter.ItemBean;



public class AddItemUtil {

	private static int recoTotalNum = 200;
	
	/**
	 * 添加商品
	 * @param bean
	 * @param id
	 * @param type
	 * @return
	 */
//	public static boolean addItem(ItemBean item,String id,String type){
//		
//		
//		if(id == null || "".equals(id) || item == null){
//			return false;
//		}
//		
//		TeJiaGoodsBean bean = GetItemUtil.itemBeanToTeJiaGoodsBean(item);
//		
//		String[] ids = id.split(",");
//		
//		for(String ida : ids){
//			if(ida == null || ida.equals("") || "null".equals(ida)){
//				continue;
//			}
//			boolean flag = false;
//			//图片size 都需要
//			if(type.equals("tagItem")){		
//				TagBean tagBean = AdminTagDAO.getTagBean(ida);
//				flag = addTagItem(tagBean, bean);
//			}else if(type.equals("keywordItem")){
//				KeywordToItem keywordItem = new KeywordToItem();
//				keywordItem.setId(ida);
//				keywordItem.setItemId(bean.getItem_id());
//				flag = addKeywordItem(bean, keywordItem);
//			}else if(type.equals("recoItem")){
//				ProfRecom profReco = GetItemUtil.itemBeanToProfRecom(item);
//				flag = addRecom(bean, profReco);
//			}else{
//				return false;
//			}
//		}

//	return true;
//
//	}
	
	
	public static boolean addItem(ItemBean item,String id,String type,Connection conn){
		
		System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFKKK::::::"+item.getBaoyou());
		
		if(id == null || "".equals(id) || item == null){
			return false;
		}
		
		TeJiaGoodsBean bean = GetItemUtil.itemBeanToTeJiaGoodsBean(item);
		
		String[] ids = id.split(",");
		
		for(String ida : ids){
			if(ida == null || ida.equals("") || "null".equals(ida)){
				continue;
			}
			boolean flag = false;
			//图片size 都需要
			if(type.equals("tagItem")){	
				TagBean tagBean = AdminTagDAO.getTagBean(ida,conn);
				flag = addTagItem(tagBean, bean,conn);
			}else if(type.equals("keywordItem")){
				KeywordToItem keywordItem = new KeywordToItem();
				keywordItem.setId(ida);
				keywordItem.setItemId(bean.getItem_id());
				flag = addKeywordItem(bean, keywordItem);
			}else if(type.equals("recoItem")){
				ProfRecom profReco = GetItemUtil.itemBeanToProfRecom(item,conn);
				flag = addRecom(bean, profReco);
			}else{
				return false;
			}
	}

	return true;

	}
	
	
	/**
	 * 更新或者添加商品信息
	 * @param tjBean
	 * @return
	 */
	public static boolean refreshItem(TeJiaGoodsBean tjBean){
		boolean flag = false;
		
		if(tjBean == null){
			return false;
		}
		// 如果是新进商品(或者之前的商品已经被删除)，记录商品的集分宝比例历史记录(如果该商品之前就已经存在，不作此操作)
//		TeJiaGoodsBean bean = AdminTagItemDAO.getDiscountProduct(tjBean.getItem_id());

//		if (bean!= null && bean.getRate() != tjBean.getRate()){							
//		}
//		AdminTagItemDAO.addItemJfbRateHistory(tjBean.getItem_id(), tjBean.getRate()+"");
		
		AdminTagItemDAO.addItem(tjBean);
		tjBean.setUpdate_time(System.currentTimeMillis()+"");
		TagItemCacheDAO.addOrUpdateItem(tjBean);
		return flag;
	}
	
	/**
	 * 添加商品至关键词
	 * @param tjBean
	 * @param keywordItem
	 * @return
	 */
	public static boolean addKeywordItem(TeJiaGoodsBean tjBean,KeywordToItem keywordItem){
		boolean flag = false;
		try {
			// 添加(更新)商品至数据库和REDIS
			refreshItem(tjBean);
			
			
			// 添加商品与该关键词的关系归属
			int rank = AdminSearchKeyWordDAO.addKeywordItem(keywordItem);
			SearchKeywordCacheDAO.addItem2Keyword(keywordItem.getId(), keywordItem.getItemId(), rank);
			flag = true;
		} catch (Exception e) {
			return false;
		}
		return flag;
	}
	
	/**
	 * 添加商品至标签
	 * @param tagBean
	 * @param tjBean
	 * @return
	 */
	public static boolean addTagItem(TagBean tagBean , TeJiaGoodsBean tjBean,Connection conn){
		boolean flag = false;
		try {
			long l1=System.currentTimeMillis();
			// 添加(更新)商品至数据库和REDIS
			refreshItem(tjBean);
			long l2=System.currentTimeMillis();			
			// 添加商品与该标签的关系归属
			int rank =  AdminTagItemDAO.addItem2Tag(tjBean.getItem_id(), tagBean.getTag_id(),conn);
			TagItemCacheDAO.addItem2Tag(tagBean.getTag_id(), tjBean.getItem_id(), rank);
			TagItemCacheDAO.reLockHeaderItem(tagBean,conn);
			
			long l3=System.currentTimeMillis();
			
			//添加商品至子标签后，同步商品归属至父标签
			String parent_tag_id = tagBean.getParent_tag_id();
			if (parent_tag_id != null && !parent_tag_id.equals("") && !parent_tag_id.equalsIgnoreCase("null")&& !parent_tag_id.equals("538")){
				int parent_rank =  AdminTagItemDAO.addItem2Tag(tjBean.getItem_id(), parent_tag_id,conn);
				TagBean parent_tag_bean = AdminTagDAO.getTagBean(parent_tag_id,conn);
				TagItemCacheDAO.addItem2Tag(parent_tag_id, tjBean.getItem_id(), parent_rank);
				TagItemCacheDAO.reLockHeaderItem(parent_tag_bean,conn);
			}
			
			long l4=System.currentTimeMillis();
			
			System.out.println("aaaaaaaaaaaaa::::"+(l2-l1));
			System.out.println("bbbbbbbbbbbbb::::"+(l3-l2));
			System.out.println("ccccccccccccc::::"+(l4-l3));
			flag = true;
			} catch (Exception e) {
				flag = false;
			}
		return flag;
	}
	
	public static boolean addRecom(TeJiaGoodsBean tjBean,ProfRecom bean){
		boolean flag = false;
		refreshItem(tjBean);
		if(bean.getRank() != bean.getTimestamp()){
			Map<String ,Double> map = RecoItemCacheDAO.getMap(bean.getRank(), bean.getRank()+1);
			String member = "";
			Iterator<Entry<String, Double>> iter = map.entrySet().iterator();

			Entry<String, Double> entry;

			if (iter.hasNext()) {
				entry = iter.next();
				member = entry.getKey();
				RecoItemCacheDAO.delRecoItem(member);
			}
		}
		bean.setRank(bean.getTimestamp());
		flag = AdminRecoItemDAO.addRecoItem(bean);
		if(flag){
			RecoItemCacheDAO.addHomePage(bean);
			int size = RecoItemCacheDAO.getSize();
			if(size > recoTotalNum){			
				RecoItemCacheDAO.delRecoItemByRank(0,size-recoTotalNum);//删除300个之外
			}
		}
		return flag;
	}
	
	public static String getRespJSONString(String result, String content){
		try{
			JSONObject respOBJ = new JSONObject();
			respOBJ.put("result",  result);
			if (content != null){
				respOBJ.put("content",  content);
			}
			return respOBJ.toString();
		}catch (Exception e){
			return "{\"content\":\"execute fail.....\",\"result\":\"3\"}";
		}
    }
	
	public static List<TeJiaGoodsBean> stringToList(String content){
		List<TeJiaGoodsBean> list = new ArrayList<TeJiaGoodsBean>();
		try {			
			JSONObject jso = new JSONObject(content);
			JSONArray jsa = jso.getJSONArray("item_list");
			for(int i = 0 ; i < jsa.length(); i ++){
				TeJiaGoodsBean bean = new TeJiaGoodsBean();
				bean = stringToBean(jsa.getString(i));
				if(bean != null){
					list.add(bean);
				}
			}
		} catch (Exception e) {
			return null;
		}
		return list;
	}
	
	public static TeJiaGoodsBean stringToBean(String content){
		TeJiaGoodsBean bean = null;
		try {
			JSONObject jso = new JSONObject(content);
			bean = new TeJiaGoodsBean();
			bean.setItem_id(jso.getString("item_id"));
			bean.setTitle(jso.getString("title"));
			bean.setPrice_high(jso.getString("price_high"));
			bean.setPrice_low(jso.getString("price_low"));
			bean.setClickURL(jso.getString("click_url"));
			bean.setPic_url(jso.getString("pic_url"));
			bean.setRate(jso.getDouble("jfb_rate"));
			if(jso.has("reco_reason")){
				bean.setRecoReason(jso.getString("reco_reason"));
			}
//			bean.setDiscount(jso.getString("discount"));
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return bean;
	}
	
	
	public static void main(String[] args) {
//		ItemBean item = new ItemBean();
//		TeJiaGoodsBean tjBean = new TeJiaGoodsBean();		
//		ProfRecom bean = new ProfRecom();
//		bean.setItemId("123456");
//		bean.setRank(1234560);
//		addRecom(tjBean, bean);
		
		
//		ItemBean item = new ItemBean();
//		item.setImgSize("460x460");
//		item.setItemid("37994219106");
//		item.setPrice(269.00);
//		item.setZkPrice(79.0);
//		item.setImgurl("http://gi3.md.alicdn.com/bao/uploaded/i3/T1EB0fFNldXXXXXXXX_!!0-item_pic.jpg_460x460q90.jpg");
//		item.setOriginalImgUrl("http://gi3.md.alicdn.com/bao/uploaded/i3/T1EB0fFNldXXXXXXXX_!!0-item_pic.jpg_300x300q90.jpg");		
//		item.setRate(2);
//		item.setTitle("包邮 华伦杜男装新款夏装大码t恤男 修身韩版潮半袖男士短袖T恤");
//		item.setRecom("折扣");
//		addItem(item, "569", "recoItem");
		
//		System.out.println(Class.class.getClass().getResource("/").getPath());
		System.out.println(System.getProperty("user.dir"));
		
	}
	
}
