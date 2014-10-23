package cn.youhui.admin.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.google.gson.Gson;

import cn.youhui.bean.ProfRecom;
import cn.youhui.bean.Searchkeyword;
import cn.youhui.bean.SuiShouAction;
import cn.youhui.bean.TagBean;
import cn.youhui.bean.TeJiaGoodsBean;
import cn.youhui.cache.dao.RecoItemCacheDAO;
import cn.youhui.cache.dao.TagItemCacheDAO;
import cn.youhui.itemadd.dataadapter.ItemBean;
import cn.youhui.platform.db.SQL;
import cn.youhui.platform.util.SuiShouActionUtil;


public class GetItemUtil {

	/**
	 * 获取标签下商品信息
	 * @param tagId
	 * @param page
	 * @return
	 */
	public static List<TeJiaGoodsBean> getTejiaGoods(String tagId,int page){
		List<TeJiaGoodsBean> list = TagItemCacheDAO.getItemsByTagid(tagId, page, 20);
		return list;
	}
	
	/**
	 * 获取标签下商品页数
	 * @param tagId
	 * @return
	 */
	public static int getItemPage(String tagId){
		
		int total = Integer.parseInt(TagItemCacheDAO.getTotalPage(tagId)+"");
		
		return total;
	}
	
	/**
	 * 获取推荐商品
	 * @return
	 */
	public static List<ProfRecom> getRecoItems(){
		List<ProfRecom> list = new ArrayList<ProfRecom>();
		Set<String> set = RecoItemCacheDAO.getAllReco();
		Iterator<String> it = set.iterator();  
		Gson g = new Gson();  
		while (it.hasNext()) {  
		  String str = it.next();    
		  ProfRecom bean = g.fromJson(str, ProfRecom.class);
		  list.add(bean);
		} 
		return list;				
	}
	
	/**
	 * 获取bigcache的商品
	 * @return
	 */
	public static List<ProfRecom> getBigRecoItems(){
		List<ProfRecom> list = new ArrayList<ProfRecom>();
		Set<String> set = RecoItemCacheDAO.getAllBigReco();
		Iterator<String> it = set.iterator();  
		Gson g = new Gson();  
		while (it.hasNext()) {  
		  String str = it.next();   
		  ProfRecom bean = g.fromJson(str, ProfRecom.class);
		  list.add(bean);
		}   
		return list;
	}
	
	
	public boolean ifInPhone(String itemId1,String itemId2){
		List<ProfRecom> l=getBigRecoItems();
		for(int i=0;i<l.size();i++){
			if(i>200&&i<l.size()-20){
				if(l.get(i).getItemId().equals(itemId1)||l.get(i).getItemId().equals(itemId2)){
					return true;
				}
			}
		}
		return false;
	}
	
	
	/**
	 * 根据itemid获取tag信息
	 * @param itemid
	 * @return
	 */
	public static List<TagBean> getTagBeanList(String itemid){
		List<TagBean> list = AdminTagItemDAO.getTagItemListByItemID(itemid);
		
		return list;
	}
	
	public static List<TagBean> getTagBeanList(String itemid,Connection conn){
		List<TagBean> list = AdminTagItemDAO.getTagItemListByItemID(itemid,conn);
		
		return list;
	}
	
	/**
	 * 根据itemid获取keyword信息
	 * @param itemid
	 * @return
	 */
	public static List<Searchkeyword> getKeywordList(String itemid){
		List<Searchkeyword> list = AdminSearchKeyWordDAO.getKeywordByItemid(itemid);
		
		return list;
	}
	
	public static List<Searchkeyword> getKeywordList(String itemid,Connection conn){
		List<Searchkeyword> list = AdminSearchKeyWordDAO.getKeywordByItemid(itemid,conn);
		
		return list;
	}
	
	/**
	 * 获取关键词信息
	 * @return
	 */
	public static List<Searchkeyword> getAllKeywordList(){
		List<Searchkeyword> list = AdminSearchKeyWordDAO.getKeywordList( "1",  "0","0");
		List<Searchkeyword> newList = new ArrayList<Searchkeyword>();
		for(Searchkeyword sk : list){
			List<Searchkeyword> cList = AdminSearchKeyWordDAO.getKeywordList( "1",  "0",sk.getId());
			List<Searchkeyword> cList_1 = new ArrayList<Searchkeyword>();
			if(cList != null && cList.size() > 0){
				for(Searchkeyword sk1 : cList){
					List<Searchkeyword> cList1 = AdminSearchKeyWordDAO.getKeywordList( "1",  "0",sk1.getId());
					List<Searchkeyword> cList1_1 = new ArrayList<Searchkeyword>();
					if(cList1 != null && cList1.size() > 0){
						
						for(Searchkeyword sk2 : cList1){
							List<Searchkeyword> cList2 = AdminSearchKeyWordDAO.getKeywordList( "1",  "0",sk2.getId());
							List<Searchkeyword> cList2_1 = new ArrayList<Searchkeyword>();
							if(cList2 != null && cList2.size() > 0){
								for(Searchkeyword sk3 : cList2){
									List<Searchkeyword> cList3 = AdminSearchKeyWordDAO.getKeywordList( "1",  "0",sk3.getId());
//									List<Searchkeyword> cList3_1 = new ArrayList<Searchkeyword>();
									if(cList3 != null && cList3.size() > 0){
										sk3.setcList(cList3);
									}
									cList2_1.add(sk3);
								}
								sk2.setcList(cList2_1);
							}
							cList1_1.add(sk2);
						}
						sk1.setcList(cList1);
					}
					cList_1.add(sk1);
				}
				sk.setcList(cList);
			}
			newList.add(sk);
		}
		return newList;
	}
	
	/**
	 * 获取tag信息
	 * @return
	 */
	public static List<TagBean> getTagList(){
		List<TagBean> list = AdminTagDAO.getTags("538");
		
		return list;
	}
	
	public static String ListToString(){
		Connection conn = SQL.getInstance().getConnection();
		StringBuffer sb = new StringBuffer();
		List<TagBean> tagList = AdminTagDAO.getTags("538");
		List<Searchkeyword> list = AdminSearchKeyWordDAO.getKeywordList( "1",  "0","0",conn);
		if((tagList == null || tagList.size() == 0 ) && (list == null || list.size() == 0) ){
			sb.append("{").append("\"status\":").append("\"1009\",");
			sb.append("\"desc\":").append("\"Did not return a result\"}");
		}else{
			sb.append("{").append("\"status\":").append("\"1000\",");
			sb.append("\"data\":{");
			sb.append("\"tags\":[");
			for(TagBean bean : tagList){
				sb.append("{");
				String key = bean.getTag_id();

					String value = bean.getTag_name();
					sb.append("\"id\":").append("\""+key+"\"").append(",").append("\"name\":").append("\""+value+"\"");
					sb.append("},");
			}
			sb.deleteCharAt(sb.lastIndexOf(","));
			sb.append("]");
			sb.append(",\"keywords\":[");
			for(Searchkeyword sk : list){
				List<Searchkeyword> cList = AdminSearchKeyWordDAO.getKeywordList( "1",  "0",sk.getId(),conn);
				sb.append("{");
				sb.append("\"id\":").append("\""+sk.getId()+"\"").append(",").append("\"name\":").append("\""+sk.getName()+"\"");
				if(cList != null && cList.size() > 0){
					sb.append(",\"cKeywords\":[");
					for(Searchkeyword sk1 : cList){
						sb.append("{");
						sb.append("\"id\":").append("\""+sk1.getId()+"\"").append(",").append("\"name\":").append("\""+sk1.getName()+"\"");
						
						List<Searchkeyword> cList1 = AdminSearchKeyWordDAO.getKeywordList( "1",  "0",sk1.getId(),conn);
						if(cList1 != null && cList1.size() > 0){
							sb.append(",\"cKeywords\":[");
							for(Searchkeyword sk2 : cList1){
								sb.append("{");
								sb.append("\"id\":").append("\""+sk2.getId()+"\"").append(",").append("\"name\":").append("\""+sk2.getName()+"\"");
								
								List<Searchkeyword> cList2 = AdminSearchKeyWordDAO.getKeywordList( "1",  "0",sk2.getId(),conn);
								if(cList2 != null && cList2.size() > 0){
									sb.append(",\"cKeywords\":[");
									for(Searchkeyword sk3 : cList2){
										sb.append("{");
										sb.append("\"id\":").append("\""+sk3.getId()+"\"").append(",").append("\"name\":").append("\""+sk3.getName()+"\"");
										
//										List<Searchkeyword> cList3 = Admin_SearchKeyWord_DAO.getKeywordList( "1",  "0",sk3.getId());
										
									}
									sb.deleteCharAt(sb.lastIndexOf(","));
									sb.append("]");
								}
								sb.append("},");
							}
							sb.deleteCharAt(sb.lastIndexOf(","));
							sb.append("]");
						}
						sb.append("},");
					}
					sb.deleteCharAt(sb.lastIndexOf(","));
					sb.append("]");
				}
				sb.append("},");
			}			
			sb.deleteCharAt(sb.lastIndexOf(","));
			sb.append("]}");
			sb.append("}");
			
		}
		SQL.getInstance().release(null, conn);
		return sb.toString();
	}
	
	
	/**
	 * 商品格式转换TeJiaGoodsBean转换成ItemBean
	 * @param tjBean
	 * @return
	 */
	public static ItemBean tejiaGoodsBeanToItemBean(TeJiaGoodsBean tjBean){
		ItemBean bean = new ItemBean();
		bean.setItemid(tjBean.getItem_id());
		bean.setImgurl(tjBean.getPic_url());
		bean.setTitle(tjBean.getTitle());
		bean.setRate(tjBean.getRate());
		bean.setPrice(Double.parseDouble(tjBean.getPrice_high()));
		bean.setZkPrice(Double.parseDouble(tjBean.getPrice_low()));
		//点击地址
		/**
		 *if(jso.has("reco_reason")){
				bean.setRecoReason(jso.getString("reco_reason"));
			}
		 */
		return bean;
	}
	
	/**
	 * 商品格式转换ItemBean转换成TeJiaGoodsBean
	 * @param bean
	 * @return
	 */
	public static TeJiaGoodsBean itemBeanToTeJiaGoodsBean(ItemBean bean){
		TeJiaGoodsBean tjBean = new TeJiaGoodsBean();		
		tjBean.setItem_id(bean.getItemid());
		tjBean.setTitle(bean.getTitle());
		tjBean.setRate(bean.getRate());
		tjBean.setRecoReason(bean.getRecom());
		tjBean.setPic_url(bean.getOriginalImgUrl());
		tjBean.setPrice_high(bean.getPrice()+"");
		tjBean.setPrice_low(bean.getZkPrice()+"");
		tjBean.setClickURL("a.m.taobao.com/i"+bean.getItemid()+".html");
		tjBean.setPicCutUrl(bean.getImgurl());
		tjBean.setCatID(bean.getCatId());
		tjBean.setBaoyou(bean.getBaoyou());
		if(bean.getImgurl()== null || "".equals(bean.getImgurl())){
			tjBean.setPicCutUrl(bean.getOriginalImgUrl());
		}
		if(bean.getOriginalImgUrl() == null || "".equals(bean.getOriginalImgUrl())){
			tjBean.setPic_url(bean.getImgurl());
		}
		if(bean.getImgSize() != null){			
			String[] imgSize = bean.getImgSize().split("x");
			if(imgSize.length>1){
				try {					
					tjBean.setWidth_b(Integer.parseInt(imgSize[0]));
					tjBean.setHeight_b(Integer.parseInt(imgSize[1]));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		return tjBean;
	}
	
	public static ProfRecom itemBeanToProfRecom(ItemBean bean){
		long time = System.currentTimeMillis();
		ProfRecom profRecom = new ProfRecom();
		profRecom.setItemId(bean.getItemid());
		profRecom.setItemTitle(bean.getTitle());
		profRecom.setItemImg(bean.getImgurl());
		profRecom.setItemPrice(bean.getPrice());
		profRecom.setItemPromPrice(bean.getZkPrice());
		profRecom.setRecommendReaSon(bean.getRecom());
		profRecom.setImgSize(bean.getImgSize());
		profRecom.setBaoyou(bean.getBaoyou());
		long rank = AdminRecoItemDAO.getItemRank(bean.getItemid());
		if(rank != 0){
			profRecom.setRank(rank);
		}else{
			profRecom.setRank(time);
		}
		profRecom.setAction(new SuiShouAction(SuiShouActionUtil.getSuiShouActionUrl("tagStyleItem",bean.getItemid() )));
		profRecom.setTimestamp(time);
		profRecom.setImgSize(bean.getImgSize());
		Random r = new Random();
		int favNum = r.nextInt(1000)+100;
		profRecom.setFavNum(favNum);
		return profRecom;
	}
	
	
	public static void main(String[] args) {
		Random r = new Random();
		int favNum = r.nextInt(1);
		System.out.println(favNum);
	}
	public static ProfRecom itemBeanToProfRecom(ItemBean bean,Connection conn){
		long time = System.currentTimeMillis();
		ProfRecom profRecom = new ProfRecom();
		profRecom.setItemId(bean.getItemid());
		profRecom.setItemTitle(bean.getTitle());
		profRecom.setItemImg(bean.getImgurl());
		profRecom.setItemPrice(bean.getPrice());
		profRecom.setItemPromPrice(bean.getZkPrice());
		profRecom.setRecommendReaSon(bean.getRecom());
		profRecom.setImgSize(bean.getImgSize());
		long rank = AdminRecoItemDAO.getItemRank(bean.getItemid(),conn);
		if(rank != 0){
			profRecom.setRank(rank);
		}else{
			profRecom.setRank(time);
		}
		profRecom.setAction(new SuiShouAction(SuiShouActionUtil.getSuiShouActionUrl("tagStyleItem",bean.getItemid() )));
		profRecom.setActionType("tagStyleItem");
		profRecom.setActionValue(bean.getItemid());
		profRecom.setTimestamp(time);
		profRecom.setImgSize(bean.getImgSize());
		Random r = new Random();
		int favNum = r.nextInt(1000);
		profRecom.setFavNum(favNum);
		return profRecom;
	}
	
}
