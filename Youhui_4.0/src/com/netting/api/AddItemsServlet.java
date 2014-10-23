package com.netting.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.netting.bean.KeywordToItem;
import com.netting.bean.Searchkeyword;
import com.netting.bean.Tag_Bean;
import com.netting.bean.TeJiaGoodsBean;
import com.netting.cache.dao.AdminSearchKeywordCacheDAO;
import com.netting.cache.dao.Admin_Tag_Item_Cache_DAO;
import com.netting.dao.admin.Admin_SearchKeyWord_DAO;
import com.netting.dao.admin.Admin_Tag_DAO;
import com.netting.dao.admin.Admin_Tag_Item_DAO;
import com.netting.job.AddTagItemJob;

@WebServlet("/addcategoryitems")
public class AddItemsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddItemsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String type = request.getParameter("type");
		if(type == null || "".equals(type)){
			response.getWriter().print(getRespJSONString("1", "params error"));
			return;
		}
		String content = request.getParameter("content");
		String id = request.getParameter("id");
		if(id == null || "".equals(id)){
			// 参数错误，返回：1
			response.getWriter().print(getRespJSONString("1", "params error"));
			return;
		}
		if (content == null || content.equals("") || content.equalsIgnoreCase("null")){
			// 参数错误，返回：1
			response.getWriter().print(getRespJSONString("1", "params error"));
			return;
		}
//		System.out.println(content);
//		content = new String(content.getBytes("iso-8859-1"), "utf-8");
//		
//		System.out.println(content);
		List<TeJiaGoodsBean> list = stringToList(content);
//		System.out.println(list == null);
		if(list == null || list.size() == 0){
			response.getWriter().print(getRespJSONString("1", "params error"));
			return;
		}
		int i = 0;
//		String failItem = "";
		String[] ids = id.split(",");
		
		for(String ida : ids){
			if(ida == null || ida.equals("") || "null".equals(ida)){
				continue;
			}
		for(TeJiaGoodsBean bean : list){
			boolean flag = false;
			if(type.equals("tagItem")){					
				Tag_Bean tag_bean = Admin_Tag_DAO.getTagBean(ida);
				flag = addTagItem(tag_bean, bean);
			}else if(type.equals("keywordItem")){
				KeywordToItem keywordItem = new KeywordToItem();
				keywordItem.setId(ida);
				keywordItem.setItemId(bean.getItem_id());
				flag = addKeywordItem(bean, keywordItem);
			}else{					
				response.getWriter().print(getRespJSONString("1", "params error"));
				return;
			}
			if(flag){
				i++;
			}else{
//				failItem = failItem+ bean.getItem_id()+",";
			}
		}
	}		
			// 执行成功，返回：0
//			String result = "execute successfullyNum："+ i;
//			if(!"".equals(failItem)){
//				result = result+"   ,failItem："+failItem;
//			}
			response.getWriter().print(getRespJSONString("0", i+""));
			return;		
	}

	public static boolean addTagItem(Tag_Bean tag_bean , TeJiaGoodsBean tjBean){
		boolean flag = false;
		try {
			
		// 添加(更新)商品至数据库和REDIS

		Admin_Tag_Item_DAO.addItem(tjBean);
		tjBean.setUpdate_time(System.currentTimeMillis()+"");
		Admin_Tag_Item_Cache_DAO.add_update_Item(tjBean);
					
		// 添加商品与该标签的关系归属
		int rank =  Admin_Tag_Item_DAO.addItem2Tag(tjBean.getItem_id(), tag_bean.getTag_id());
		Admin_Tag_Item_Cache_DAO.addItem2Tag(tag_bean.getTag_id(), tjBean.getItem_id(), rank);
		AddTagItemJob.reLockHeaderItem(tag_bean);
		
					//添加商品至子标签后，同步商品归属至父标签
		String parent_tag_id = tag_bean.getParent_tag_id();
		if (parent_tag_id != null && !parent_tag_id.equals("") && !parent_tag_id.equalsIgnoreCase("null")&& !parent_tag_id.equals("538")){
			int parent_rank =  Admin_Tag_Item_DAO.addItem2Tag(tjBean.getItem_id(), parent_tag_id);
			Tag_Bean parent_tag_bean = Admin_Tag_DAO.getTagBean(parent_tag_id);
			Admin_Tag_Item_Cache_DAO.addItem2Tag(parent_tag_id, tjBean.getItem_id(), parent_rank);
			AddTagItemJob.reLockHeaderItem(parent_tag_bean);
		}
					
					// 如果是新进商品(或者之前的商品已经被删除)，记录商品的集分宝比例历史记录(如果该商品之前就已经存在，不作此操作)
		TeJiaGoodsBean bean = Admin_Tag_Item_DAO.getDiscountProduct(tjBean.getItem_id());

		if (tjBean == null || bean.getRate() != tjBean.getRate()){							
			Admin_Tag_Item_DAO.addItemJfbRateHistory(tjBean.getItem_id(), tjBean.getRate()+"");
		}
		flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	
	public static boolean addKeywordItem(TeJiaGoodsBean tjBean,KeywordToItem keywordItem){
		boolean flag = false;
		try {
			// 添加(更新)商品至数据库和REDIS

			Admin_Tag_Item_DAO.addItem(tjBean);
			tjBean.setUpdate_time(System.currentTimeMillis()+"");
			Admin_Tag_Item_Cache_DAO.add_update_Item(tjBean);
			// 添加商品与该关键词的关系归属
			int rank = Admin_SearchKeyWord_DAO.addKeywordItem(keywordItem);
			AdminSearchKeywordCacheDAO.addItem2Keyword(keywordItem.getId(), keywordItem.getItemId(), rank);
			
			//同步商品至关键词父关键词
			String parentId = Admin_SearchKeyWord_DAO.getKeywordParentId(keywordItem.getId());
			while(parentId != null && !parentId.equals("") && !parentId.equals("0")){
				KeywordToItem parentKeyword = new KeywordToItem();
				parentKeyword.setId(parentId);
				parentKeyword.setItemId(keywordItem.getItemId());
				int parRank = Admin_SearchKeyWord_DAO.addKeywordItem(parentKeyword);
				AdminSearchKeywordCacheDAO.addItem2Keyword(parentKeyword.getId(), parentKeyword.getItemId(), parRank);
				parentId = Admin_SearchKeyWord_DAO.getKeywordParentId(parentKeyword.getId());
			}
			
			// 如果是新进商品(或者之前的商品已经被删除)，记录商品的集分宝比例历史记录(如果该商品之前就已经存在，不作此操作)
			TeJiaGoodsBean bean = Admin_Tag_Item_DAO.getDiscountProduct(tjBean.getItem_id());

			if (tjBean == null || bean.getRate() != tjBean.getRate()){							
				Admin_Tag_Item_DAO.addItemJfbRateHistory(tjBean.getItem_id(), tjBean.getRate()+"");
			}
			flag = true;
		} catch (Exception e) {
			return false;
		}
		return flag;
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
