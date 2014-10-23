package com.netting.action.admin;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.netting.bean.AdminUser;
import com.netting.bean.KeyCategoryBean.KeywordBean;
import com.netting.bean.MMixBean;
import com.netting.bean.MixPageStyleBean;
import com.netting.bean.MixStylePageBean;
import com.netting.bean.SuiShouAction;
import com.netting.bean.TagToItemBean;
import com.netting.bean.Tag_Bean;
import com.netting.bean.TeJiaGoodsBean;
import com.netting.bean.TongjiBean;
import com.netting.cache.dao.Admin_Tag_Cache_DAO;
import com.netting.cache.dao.Admin_Tag_Item_Cache_DAO;
import com.netting.conf.SysCache;
import com.netting.conf.SysConf;
import com.netting.dao.admin.AdminMMixDAO;
import com.netting.dao.admin.Admin_OPT_Log_DAO;
import com.netting.dao.admin.Admin_Tag_DAO;
import com.netting.dao.admin.Admin_Tag_Item_DAO;
import com.netting.dao.admin.TaobaoAPI_DAO;
import com.netting.job.AddTagItemJob;
import com.netting.job.JobExecutor;
import com.netting.job.RefreshTagItemJob;
import com.netting.job.RefreshTopItem;
import com.netting.job.RestoreTagItemRateJob;
import com.netting.job.UpdateTagItemPriceJob;
import com.netting.util.ChaojiHuiUtils;
import com.netting.util.CodeUtil;
import com.netting.util.GetTagItemFromRedis;
import com.netting.util.NetManager;
import com.taobao.api.domain.TaobaokeItem;

/**
 * 标签下的商品管理
 * @author YAOJIANBO
 * @since 2013-09-24
 */
@WebServlet("/ad/tag_item_manager")
public class Admin_Tag_Item_Manager_Action extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private static Map<String,List<TaobaokeItem>> map = new HashMap<String, List<TaobaokeItem>>();
	private static  String NewUrl = "http://b17.cn/skip/";
	private static String reg = "^(\\d+,)*\\d+$";
	private static String mixTagId = "569";
	
	/**
	 * 初始化日志
	 */
	public static Log logger = LogFactory.getLog( Admin_Tag_Item_Manager_Action.class );
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String methodName = request.getParameter("actionmethod");

		if (null == methodName || "".equals(methodName) || "null".equals(methodName))
		{
			response.getWriter().print("<script>alert('Param actionmethod can not be null！');history.back();</script>");
			return;
		}
		try
		{
			if (methodName.equals("showTagItemList"))
			{
				showTagItemList(request, response);
			}
			else if(methodName.equals("showTopTradeItemList")){
				showTopTradeItemList(request, response);
			}
			else if (methodName.equals("delTagItem"))
			{
				delTagItem(request, response);
			}
			else if (methodName.equals("addTagItem"))
			{
				addTagItem(request, response);
			}
			else if (methodName.equals("addTagItemByURL"))
			{
				addTagItemByURL(request, response);
			}
			else if (methodName.equals("addTagItemNet"))
			{
				addTagItemNet(request, response);
			}
			else if (methodName.equals("refreshTagItem"))
			{
				refreshTagItem(request, response);
			}
			else if (methodName.equals("updateTagItemPrice"))
			{
				updateTagItemPrice(request, response);
			}
			else if (methodName.equals("showTagItemListFromTaobao"))
			{
				showTagItemListFromTaobao(request, response);
			}
			else if (methodName.equals("getMixStyleItemList"))
			{
				getMixStyleItemList(request, response);
			}
			else if (methodName.equals("addMixStyleItemList"))
			{
				addMixStyleItemList(request, response);
			}
			else if (methodName.equals("addMixStyleItemListNew"))
			{
				addMixStyleItemListNew(request, response);
			}
			else if (methodName.equals("updateMixStyleItemList"))
			{
				updateMixStyleItemList(request, response);
			}
			else if (methodName.equals("addMixStyleItem"))
			{
				addMixStyleItem(request, response);
			}
			else if (methodName.equals("updateMixStyleItem"))
			{
				updateMixStyleItem(request, response);
			}
			else if (methodName.equals("delMixStyleItem"))
			{
				delMixStyleItem(request, response);
			}
			else if (methodName.equals("MixStyleItemMovePosition"))
			{
				MixStyleItemMovePosition(request, response);
			}
			else if (methodName.equals("ReloadMixStyleItem"))
			{
				ReloadMixStyleItem(request, response);
			}
			else if (methodName.equals("LockItem"))
			{
				LockItem(request, response);
			}
			else if (methodName.equals("MovePosition"))
			{
				MovePosition(request, response);
			}
			else if (methodName.equals("checkClickNum"))
			{
				checkClickNum(request, response);
			}
			else if (methodName.equals("additemtotag"))
			{
				additemtotag(request, response);
			}
			else if (methodName.equals("delitemtotag"))
			{
				delitemtotag(request, response);
			}
			else if (methodName.equals("updateItemImg"))
			{
				updateItemImg(request, response);
			}
			else if (methodName.equals("restoreTagItemRate"))
			{
				restoreTagItemRate(request, response);
			}
			else if (methodName.equals("delTagItemAB"))
			{
				delTagItemAB(request, response);
			}else if (methodName.equals("addItemMixStyleNew"))
			{
				addItemMixStyleNew(request, response);
			}else if (methodName.equals("upSetTodayTagItemPosition"))
			{
				upSetTodayTagItemPosition(request, response);
			}else if (methodName.equals("addMixStylePage"))
			{
				addMixStylePage(request, response);
			}else if (methodName.equals("showMixStylePage"))
			{
				showMixStylePage(request, response);
			}else if (methodName.equals("showMixItemPage"))
			{
				showMixItemPage(request, response);
			}else if (methodName.equals("delMixItem"))
			{
				delMixItem(request, response);
			}else if (methodName.equals("getPageItemList"))
			{
				getPageItemList(request, response);
			}else if (methodName.equals("addMixStyleItemPage"))
			{
				addMixStyleItemPage(request, response);
			}else if (methodName.equals("updateMixStyleItemPage"))
			{
				updateMixStyleItemPage(request, response);
			}else if (methodName.equals("getItemImgsFromTaobao"))
			{
				getItemImgsFromTaobao(request, response);
			}else if (methodName.equals("addTagItemByURLByForm"))
			{
				addTagItemByURLByForm(request, response);
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			response.getWriter().print("<script>alert('server process error！');history.back();</script>");
			return;
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		this.doGet(req, resp);
	}

    /**
     * 管理员访问标签下商品列表信息
     */
    public static void showTagItemList(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String parent_str = request.getParameter("parent");
    	String tag_id = request.getParameter("tag_id");
    	String tag_name = request.getParameter("tag_name");
    	String page_str = request.getParameter("page");
//    	long time = System.currentTimeMillis();
    	
    	if(tag_name != null && !"".equals(tag_name)) 
    	{
    		tag_name = new String(tag_name.getBytes("iso-8859-1"), "utf-8");
    	}
    	
    	if(parent_str == null || "".equals(parent_str)) 
    	{
    		parent_str = "538";
    	}
    	int parent_id = Integer.parseInt(parent_str);
    	
    	// 默认第一页
    	int page = 1;
    	if (page_str != null && !"".equals(page_str))
    	{
    		page = Integer.parseInt(page_str);
    	}
    	
//    	List<TeJiaGoodsBean> itemList = Admin_Tag_Item_DAO.getTagItemList(page, tag_id);
    	List<TeJiaGoodsBean> itemList = GetTagItemFromRedis.getItemsByTagid(tag_id, page, 20);
//    	System.out.println(System.currentTimeMillis()-time);
//    	List<KeywordBean> ctaglist = Admin_Tag_Item_DAO.getKeywords(tag_id);
    	List<KeywordBean> ctaglist = Admin_Tag_Cache_DAO.getTagsByParentId(tag_id);
//    	System.out.println(System.currentTimeMillis()-time);
//    	int totalPage = Admin_Tag_Item_DAO.getTagItemListTotal(tag_id);
    	int totalPage = Integer.parseInt(Admin_Tag_Item_Cache_DAO.getTotalPage(tag_id)+"");
//    	System.out.println(System.currentTimeMillis()-time+"begin");
//    	int totalPage = GetTagItemFromRedis.getTotalPage(tag_id, 20);
    	String statue = Admin_Tag_Item_DAO.isLocked(tag_id);
//    	System.out.println(System.currentTimeMillis()-time);
    	ArrayList<HashMap<String, String>> headTagList_1 = Admin_Tag_DAO.getHeaderTagList(1, parent_id);
//    	System.out.println(System.currentTimeMillis()-time);
    	ArrayList<HashMap<String, String>> headTagList_2 = Admin_Tag_DAO.getHeaderTagList(2, parent_id);
    	
    	/**
    	 * 用来存储超级惠数据
    	 */
    	
    	
//    	System.out.println(System.currentTimeMillis()-time+"jogjoigoijo");
    	if (headTagList_1 != null && headTagList_1.size() > 0)
    	{
    		request.setAttribute("headTagList_1", headTagList_1);
    	}
    	if (headTagList_2 != null && headTagList_2.size() > 0)
    	{
    		ArrayList<HashMap<String, String>> chaojiHuiList = ChaojiHuiUtils.getChaojihuiList(headTagList_2);
    		ArrayList<HashMap<String, String>> yuList = ChaojiHuiUtils.getYuList(headTagList_2);
    		request.setAttribute("yuList", yuList);
    		request.setAttribute("chaojiHuiList", chaojiHuiList);
    		request.setAttribute("headTagList_2", headTagList_2);
    	}
    	
		request.setAttribute("parent", parent_str);
    	request.setAttribute("ItemList", itemList);
    	request.setAttribute("ctaglist", ctaglist);
    	request.setAttribute("tag_id", tag_id);
    	request.setAttribute("tag_name", tag_name);
		request.setAttribute("page", page);
		request.setAttribute("totalpage", totalPage);
		request.setAttribute("statue", statue);
//		System.out.println(System.currentTimeMillis()-time+"end");
		request.getRequestDispatcher("/admin/tag_manager/tag_item_list.jsp").forward(request, response);
    }
    
    /**
     * 删除标签下商品
     * @throws Exception
     */
    public void delTagItem(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String item_ids = request.getParameter("item_ids");
    	item_ids = item_ids.trim();
    	
    	String tag_id = request.getParameter("tag_id");
    	String tag_name = request.getParameter("tag_name");
    	String parentId = request.getParameter("parentId");
    	
    	//获取标签下的子标签或者父标签，同时删除父标签或子标签与商品的关系
    	List<String> tagList = Admin_Tag_Item_DAO.getKeywordsForPaixu(tag_id);
    	tagList.add(parentId);
    	tagList.add(tag_id);
    	/*
    	if(tag_name != null && !"".equals(tag_name))
    	{ 		
    		tag_name = new String(tag_name.getBytes("iso-8859-1"), "utf-8");
    	}
    	*/
    	
    	String[] itemids = item_ids.split(",");
    	for (String id : itemids)
    	{
    		if (null == id || "".equals(id))
    		{
    			continue;
    		}
    		//删除操作时，将m_discount_products 集分宝比例修改为0，同时将item_jfb_rate当前比例修改为0
    		Admin_Tag_Item_DAO.addItemJfbRateHistory(id, "0");
    		Admin_Tag_Item_Cache_DAO.delTagFaceItem(tag_name, id);
    		for(String ctagid : tagList){
    			
    			Admin_Tag_Item_DAO.delItem2Tag(id, ctagid);
    			// Admin_Tag_Item_DAO.delItem(id);
    			// Admin_Tag_Item_Cache_DAO.delItem(id);
    			Admin_Tag_Item_Cache_DAO.delItem2Tag(ctagid, id);
    			logger.info("删除商品与标签对应关系：标签<" + tag_id + ">,商品<" + id + ">");
    			Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "1", 
    					"删除标签下商品，标签ID[" + ctagid + "]，标签名称[" + tag_name + "], 商品ID[<a target=\"_blank\" style=\"color: #1F92FF\" href=\"http://b17.cn/item?itemid=" + id + "\">" + id + "</a>]", 
    					"2");
    		}
		}
    	
    	// 修改成功
		response.getWriter().print(getRespJSONString("0"));
		return;
    }
    
    /**
     * 将商品完全删除(删除与所有标签的归属关系)
     * @throws Exception
     */
    public void delTagItemAB(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String item_ids = request.getParameter("item_ids");
    	item_ids = item_ids.trim();
    	
    	String[] itemids = item_ids.split(",");
    	for (String id : itemids)
    	{
    		if (null == id || "".equals(id))
    		{
    			continue;
    		}
    		ArrayList<String> tagid_list = Admin_Tag_Item_DAO.getTagListByItemID(id);
    		//删除操作时，将m_discount_products 集分宝比例修改为0，同时将item_jfb_rate当前比例修改为0
    		Admin_Tag_Item_DAO.addItemJfbRateHistory(id, "0");
    		if (null != tagid_list && tagid_list.size() > 0)
    		{
    			for (String tagID : tagid_list)
        		{
        			if (tagID == null || tagID.equals("") || tagID.equalsIgnoreCase("null"))
        			{
        				continue;
        			}
        			logger.info("删除商品与标签对应关系：标签<" + tagID + ">,商品<" + id + ">");
        			Admin_Tag_Item_Cache_DAO.delItem2Tag(tagID, id);
        		}
    		}
    		
    		Admin_Tag_Item_DAO.delItem(id);
    		Admin_Tag_Item_Cache_DAO.delItem(id);
    		
    		Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "1", 
    				"完全删除商品，商品ID[<a target=\"_blank\" style=\"color: #1F92FF\" href=\"http://b17.cn/item?itemid=" + id + "\">" + id + "</a>]", 
    				"2");
		}
    	
    	// 修改成功
		response.getWriter().print(getRespJSONString("0"));
		return;
    }
    
    /**
     * 通过输入itemid添加标签下商品
     * @throws Exception
     */
    public void addTagItem(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String item_ids = request.getParameter("item_ids");
    	item_ids = item_ids.trim();
    	String tag_id = request.getParameter("tag_id");
    	String tag_name = request.getParameter("tag_name");
    	
    	String admin_username = AdminLoginAction.getAdminUserFromCookie(request).getUsername();
    	JobExecutor.JobExecutor.execute(new AddTagItemJob(item_ids, tag_name, tag_id, admin_username));
    	
    	// 添加成功
		response.getWriter().print(getRespJSONString("0"));
		return;
    }
    
    /**
     * 通过输入商品链接添加标签下商品
     * @throws Exception
     */
    public void addTagItemByURL(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	long time = System.currentTimeMillis();

    	
    	String item_url = request.getParameter("item_url");
    	String tag_id = request.getParameter("tag_id");
    	// 截取商品ID
    	String item_id = null;
    	Pattern pat = Pattern.compile("id=\\d+");
    	Matcher ma = pat.matcher(item_url);
    	if (ma.find())
    	{
    		item_id = ma.group();
    		item_id = item_id.replaceAll("id=", "").replaceAll("&", "");
    	}
    	
    	
    	
    	if (null != item_id && !"".equals(item_id))
    	{
    		Tag_Bean tag_bean = Admin_Tag_DAO.getTagBean(tag_id);  		
    		
    		
    		Admin_Tag_Item_DAO.insertAddItemLogs(item_id, tag_id, "get tag bean" , (System.currentTimeMillis()-time));

    		ArrayList<TaobaokeItem> items = TaobaoAPI_DAO.getItemList(item_id);
    		
    		
    		Admin_Tag_Item_DAO.insertAddItemLogs(item_id, tag_id, "get item list size = "+(items!=null?items.size():-1) , (System.currentTimeMillis()-time));

    		
    		
    		if (items != null && items.size() > 0)
    		{
    			for (int i = 0; i < items.size(); i++)
    			{
    				TaobaokeItem item = items.get(i);
    				AddTagItemJob.processTbkItem(item, tag_bean);
    			}
    		}else
    		{
    			// 无法获取到淘宝客商品或者该商品没有返利
    			try {
    				String content = NetManager.getContent("http://fetch.b17.cn/item?itemid="+item_id);
    				JSONObject jso = new JSONObject(content);
    				if(jso.has("title") && jso.has("pic_url") && jso.has("orgin_price")){
    					TaobaokeItem item = new TaobaokeItem();
    					item.setClickUrl("a.m.taobao.com/i"+item_id+".html");
    					item.setTitle(jso.getString("title"));
    					item.setPrice(jso.getString("orgin_price"));
    					item.setNumIid(Long.parseLong(item_id));
    					item.setPicUrl(jso.getString("pic_url"));
    					item.setCommission("");
    					item.setCommissionRate("");
    					AddTagItemJob.processTbkItem(item, tag_bean);
    				}else{
    					//获取不到商品信息
    					response.getWriter().print(getRespJSONString("4"));
    	        		return;
    				}
//    				TaobaokeItem item
				} catch (Exception e) {
					//获取商品信息异常
					response.getWriter().print(getRespJSONString("2"));
	        		return;
				}
    			  			
        		
    		}
    		Admin_Tag_Item_DAO.insertAddItemLogs(item_id, tag_id, "after AddItem", (System.currentTimeMillis()-time));
//    		logger.error("addTagItemUrl"+(System.currentTimeMillis()-time));
    		AddTagItemJob.reLockHeaderItem(tag_bean);
    		Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "1", 
    				"输入URL添加标签下商品，标签ID[" + tag_id + "]，标签名称[" + tag_bean.getTag_name() + "], 商品ID[<a target=\"_blank\" style=\"color: #1F92FF\" href=\"http://b17.cn/item?itemid=" + item_id + "\">" + item_id + "</a>]",
    				"1");
    		
        	// 添加成功
//    		System.out.println(System.currentTimeMillis()-time);
    		response.getWriter().print(getRespJSONString("0"));
    		return;
    	}
    	else
    	{
    		// 添加失败
    		response.getWriter().print(getRespJSONString("1"));
    		return;
    	}
    }
    
    public void addTagItemByURLByForm(HttpServletRequest request, HttpServletResponse response) throws Exception{
    	long time = System.currentTimeMillis();
    	String item_url = request.getParameter("item_url");
    	String tag_id = request.getParameter("tag_id");
    	String parent = request.getParameter("parent");
    	String tagName = request.getParameter("tag_name");
    	StringBuffer sb = new StringBuffer();
    	// 截取商品ID
    	String item_id = null;
    	Pattern pat = Pattern.compile("id=\\d+");
    	Matcher ma = pat.matcher(item_url);
    	if (ma.find())
    	{
    		item_id = ma.group();
    		item_id = item_id.replaceAll("id=", "").replaceAll("&", "");
    	}
    	
    	if (null != item_id && !"".equals(item_id))
    	{
    		Tag_Bean tag_bean = Admin_Tag_DAO.getTagBean(tag_id);
//    		String admin_username = AdminLoginAction.getAdminUserFromCookie(request).getUsername();
//        	JobExecutor.JobExecutor.execute(new AddTagItemJob(item_id, tag_bean.getTag_name(), tag_id, admin_username));
    		
    		
    		
    		ArrayList<TaobaokeItem> items = TaobaoAPI_DAO.getItemListWithOutSDK(item_id);
    		Admin_Tag_Item_DAO.insertAddItemLogs(item_id, tag_id, "before addItem", (System.currentTimeMillis()-time));
//    		logger.error("getItemFromTaobao"+(System.currentTimeMillis()-time));
    		if (items != null && items.size() > 0)
    		{
    			for (int i = 0; i < items.size(); i++)
    			{
    				TaobaokeItem item = items.get(i);
    				AddTagItemJob.processTbkItem(item, tag_bean);
    			}
    		}
    		else
    		{
    			sb.append("<script>");
    			sb.append("alert('无法获取到该淘宝客商品 或者 该商品没有返利!');history.back();");
    			sb.append("</script>");
    			response.getWriter().print(sb.toString());
        		return;
    			// 无法获取到淘宝客商品或者该商品没有返利
//        		response.getWriter().print(getRespJSONString("2"));
//        		return;
    		}
    		Admin_Tag_Item_DAO.insertAddItemLogs(item_id, tag_id, "after AddItem", (System.currentTimeMillis()-time));
//    		logger.error("addTagItemUrl"+(System.currentTimeMillis()-time));
    		AddTagItemJob.reLockHeaderItem(tag_bean);
    		Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "1", 
    				"输入URL添加标签下商品，标签ID[" + tag_id + "]，标签名称[" + tag_bean.getTag_name() + "], 商品ID[<a target=\"_blank\" style=\"color: #1F92FF\" href=\"http://b17.cn/item?itemid=" + item_id + "\">" + item_id + "</a>]",
    				"1");
    		
        	// 添加成功
//    		System.out.println(System.currentTimeMillis()-time);
//    		response.getWriter().print(getRespJSONString("0"));
//    		return;
    		sb.append("<script>");
    		sb.append("location.href='"+request.getContextPath()+"/ad/tag_item_manager?actionmethod=showTagItemList&parent="+parent+"&tag_id="+tag_id+"&tag_name="+tagName+"';");
    		sb.append("</script>");
    		response.getWriter().print(sb.toString());
    		return;
    	}
    	else
    	{
    		sb.append("<script>");
			sb.append("alert('商品链接不合法，请检查或者将链接发至开发人员检查!');history.back();");
			sb.append("</script>");
			response.getWriter().print(sb.toString());
			return;
//    		// 添加失败
//    		response.getWriter().print(getRespJSONString("1"));
//    		return;
    	}
//    	request.getRequestDispatcher("/ad/tag_item_manager?actionmethod=showTagItemList&parent="+parent+"&tag_id="+tag_id+"&tag_name="+tagName+"").forward(request, response);
//    	var url = "<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=showTagItemList&parent=${parent}&tag_id=${tag_id}&tag_name=${tag_name}";
//		location.href=url;
    }
    
    /**
     * 通过关键词搜索到的商品，进行添加
     * @throws Exception
     */
    public void addTagItemNet(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String items = request.getParameter("selGoods");               
		String tagid = request.getParameter("tagid");
		
		String keyword = request.getParameter("keyword");
		String startPrice = request.getParameter("startPrice");
		String endPrice = request.getParameter("endPrice");
		String startCredit = request.getParameter("startCredit");
		String endCredit = request.getParameter("endCredit");
		String startCommissionRate = request.getParameter("startCommissionRate");
		String endCommissionRate = request.getParameter("endCommissionRate");
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");
		String sort = request.getParameter("sort");
		
		if(sort == null)  sort = "";
		if(startPrice == null)  startPrice = "";
		if(endPrice == null)  endPrice = "";
		if(startCommissionRate == null) startCommissionRate ="";
		if(endCommissionRate == null) endCommissionRate = "";
		if(startCredit == null) startCredit = "";
		if(endCredit == null) endCredit = "";
		if(pageSize == null)  pageSize = "40";
		if(pageNo == null)  pageNo = "1";
		if (keyword != null) 
		{  
			keyword = new String(keyword.getBytes("iso-8859-1"), "utf-8");
		} 
		else 
		{
			keyword = "";
		} 
		
		try 
		{
			String username = "";
			AdminUser admin =  AdminLoginAction.getAdminUserFromCookie(request);
       		if(admin!=null)
   			{
   				username = admin.getUsername();
   			}
			List<TaobaokeItem> itemList = map.get(username);  
			if (itemList != null) 
			{
				for (TaobaokeItem tbkbean : itemList) 
				{
					if (items.contains(tbkbean.getNumIid()+"")) 
					{
						TeJiaGoodsBean bean = tbkitemtoTejiaGoods(tbkbean,keyword);
		    			int rank =  Admin_Tag_Item_DAO.addItem2Tag(bean.getItem_id(), tagid);
		    			Admin_Tag_Item_DAO.addItem(bean);
		    			Admin_Tag_Item_Cache_DAO.add_update_Item(bean);
		    			Admin_Tag_Item_Cache_DAO.addItem2Tag(tagid, bean.getItem_id(), rank);
		    			
		    			Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "1", 
		    					"通过关键词搜索添加标签下商品，标签ID[" + tagid + "]，关键字[" + keyword + "], 商品ID[<a target=\"_blank\" style=\"color: #1F92FF\" href=\"http://b17.cn/item?itemid=" + bean.getItem_id() + "\">" + bean.getItem_id() + "</a>]",
		    					"1");
					}
				}
			}
			
			List<TagToItemBean> list1 = Admin_Tag_Item_DAO.getLockedItemList(tagid);
			if(list1!=null&&list1.size()>0) 
			{
				for(int i=list1.size()-1;i>=0;i--) 
				{
					Admin_Tag_Item_Cache_DAO.addItem2Tag(tagid, list1.get(i).getItemid(), list1.get(i).getRank());
				}
			}
		} 
		catch (Exception e)
		{
			response.getWriter().print(getRespJSONString("1"));
			return;
		}
		
    	// 添加成功
		response.getWriter().print(getRespJSONString("0"));
		return;
    }
    
    /**
     * 刷新商品列表(删除已经不存在,下架的,商家不支持集分宝返利的商品)
     * @throws Exception
     */
    public void refreshTagItem(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String tag_id = request.getParameter("tag_id");
    	String tag_name = request.getParameter("tag_name");
    	
    	String admin_username = AdminLoginAction.getAdminUserFromCookie(request).getUsername();
    	JobExecutor.JobExecutor.execute(new RefreshTagItemJob(tag_id, tag_name, admin_username));
    	
    	Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "1", "刷新标签 [" + tag_name + "] 下的商品列表", "3");
    	
    	// 修改成功
		response.getWriter().print(getRespJSONString("0"));
		return;
    }
    
    /**
     * 还原标签下商品列表的集分宝比例
     * @throws Exception
     */
    public void restoreTagItemRate(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String tag_id = request.getParameter("tag_id");
    	String tag_name = request.getParameter("tag_name");
    	
    	// 旧的集分宝比例
    	String tag_jfb_rate = Admin_Tag_DAO.getTagJfbRate(tag_id);
    	if (tag_jfb_rate == null || tag_jfb_rate.equals("") || tag_jfb_rate.equalsIgnoreCase("null"))
    	{
    		tag_jfb_rate = "0";
    	}
    	
    	JobExecutor.JobExecutor.execute(new RestoreTagItemRateJob(tag_id, tag_jfb_rate));
    	
    	Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "1", "还原标签 [" + tag_name + "] 下的商品的集分宝比例", "3");
    	
    	// 修改成功
		response.getWriter().print(getRespJSONString("0"));
		return;
    }
    
    /**
     * 更新商品的价格
     * @throws Exception
     */
    public void updateTagItemPrice(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String tag_id = request.getParameter("tag_id");
    	String tag_name = request.getParameter("tag_name");
    	
    	JobExecutor.JobExecutor.execute(new UpdateTagItemPriceJob(tag_id));
    	
    	Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "1", "刷新标签 [" + tag_name + "] 下的商品价格", "3");
    	// 修改成功
		response.getWriter().print(getRespJSONString("0"));
		return;
    }
    
    /**
     * 从淘宝获取的商品用来添加
     * @throws Exception
     */
    public void showTagItemListFromTaobao(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String status = request.getParameter("status");
     	String parent = request.getParameter("parent");

     	if (status == null) 
     	{
     		status = "new";
     	}
     	String coup = request.getParameter("coup");
     	if(coup == null||"".equals(coup)) 
 		{
 			coup = "0";
 		}
     	String startCouponRate = request.getParameter("startCouponRate") == null ? "" : request.getParameter("startCouponRate");
     	String endCouponRate = request.getParameter("endCouponRate")==null?"":request.getParameter("endCouponRate");
    	String keyword = request.getParameter("keyword");
    	String tagid = request.getParameter("tagid")==null?"":request.getParameter("tagid");
    	String startPrice = request.getParameter("startPrice")==null?"":request.getParameter("startPrice");
    	String endPrice = request.getParameter("endPrice");
    	String startCredit = request.getParameter("startCredit");
    	String endCredit = request.getParameter("endCredit");
    	String startCommissionRate = request.getParameter("startCommissionRate");
    	String endCommissionRate = request.getParameter("endCommissionRate");
    	String page = request.getParameter("page");
    	String pageSize = request.getParameter("pageSize");
    	String sort = request.getParameter("sort");
    	String catId = request.getParameter("catId");
    	LinkedHashMap<String,String> sortMap = SysCache.ItemSort;
    	LinkedHashMap<String, String> xinyongMap = SysCache.xinyongdengji;
    	LinkedHashMap<String, String> xinyongIconMap = SysCache.xinyongIcon;
    	
    	if(sort == null)  sort = "";
    	if(startPrice == null)  startPrice = "";
    	if(endPrice == null)  endPrice = "";
    	if(startCommissionRate == null) startCommissionRate ="";
    	if(endCommissionRate == null) endCommissionRate = "";
    	if(pageSize == null)  pageSize = "15";
    	if(page == null||"".equals(page))  page = "1";
    	if(keyword!=null&&!"".equals(keyword)) 	
    	{
    		keyword = new String(keyword.getBytes("iso-8859-1"), "utf-8");
    	}
    	
    	long totalResults = 0;
		List<TaobaokeItem> list = null;
		if(coup == null || "".equals(coup) || coup.equals("0")) 
		{			
			list = TaobaoAPI_DAO.searchItem(keyword, startPrice, endPrice, startCredit, endCredit, startCommissionRate, endCommissionRate, page, pageSize, sort);	
			totalResults = TaobaoAPI_DAO.totalResults;
		}
		else 
		{
			list = TaobaoAPI_DAO.couponItems(keyword, startCredit, endCredit, startCommissionRate, endCommissionRate, page, pageSize, sort, startCouponRate, endCouponRate);
			totalResults = TaobaoAPI_DAO.totalCoupon;
		}
		
		request.setAttribute("sortMap", sortMap);
		request.setAttribute("xinyongMap", xinyongMap);
		request.setAttribute("xinyongIconMap", xinyongIconMap);
		request.setAttribute("parent", parent);
		request.setAttribute("tagid", tagid);
		request.setAttribute("keyword", keyword);
		request.setAttribute("status", status);
		request.setAttribute("coup", coup);
		request.setAttribute("parent", parent);
		request.setAttribute("catId", catId);
		request.setAttribute("startCouponRate", startCouponRate);
		request.setAttribute("endCouponRate", endCouponRate);
		request.setAttribute("sort", sort);
		request.setAttribute("startPrice", startPrice);
		request.setAttribute("endPrice", endPrice);
		request.setAttribute("startCredit", startCredit);
		request.setAttribute("endCredit", endCredit);
		request.setAttribute("startCommissionRate", startCommissionRate);
		request.setAttribute("endCredit", endCredit);
		request.setAttribute("startCommissionRate", startCommissionRate);
		request.setAttribute("endCommissionRate", endCommissionRate);
		request.setAttribute("ItemList", list);
		request.setAttribute("page", page);
		request.setAttribute("totalpage", totalResults);
		
		String username = "";
		AdminUser admin =  AdminLoginAction.getAdminUserFromCookie(request);
   		if(admin!=null)
		{
			username = admin.getUsername();
		}
		map.put(username, list);
		
		request.getRequestDispatcher("/admin/tag_manager/tag_item_add.jsp").forward(request, response);
		return;
    }
    
    /**
     * 混排页面商品
     * @throws Exception
     */
    public void getMixStyleItemList(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String keyword = request.getParameter("keyword"); 
    	String tag_id = request.getParameter("tagid");
    	String page = request.getParameter("page");
    	if(keyword!=null&&!"".equals(keyword))
    	{
    		keyword = new String(keyword.getBytes("iso-8859-1"), "utf-8");
    	}
    	
    	if(page == null|| "".equals(page))
    	{
    		page = "1" ;
    	}
    	
    	if(tag_id == null) 
		{
			tag_id = "";
		}
    	int size = 0;
		
		if(!"".equals(tag_id))
		{
			size = Admin_Tag_Item_DAO.getProductPageStyleListSizeByTagid(tag_id);
		}
		else if(!keyword.equals("")) 
		{
			size = Admin_Tag_Item_DAO.getProductPageStyleListSize(keyword);
		}
		else 
		{
			tag_id = "";
			size = Admin_Tag_Item_DAO.getProductPageStyleListSizeByTagid(tag_id);
		}
		int p_total =  getTotalPage(size,15) ;

		List<MixStylePageBean> list = new ArrayList<MixStylePageBean>();
		if(!"".equals(tag_id)) 
		{
			list = Admin_Tag_Item_DAO.getMixStylePageByTagid(tag_id, page);
		}
		else if(!keyword.equals(""))
		{
			list = Admin_Tag_Item_DAO.getMixStylePageByKeyword(keyword, page);
		}
		else 
		{
			tag_id = "";
			list = Admin_Tag_Item_DAO.getMixStylePageByTagid(tag_id, page);
		}
		
		request.setAttribute("totalpage", p_total);
		request.setAttribute("page", page);
		request.setAttribute("ItemList", list);
		request.setAttribute("tagid", tag_id);
		request.setAttribute("keyword", keyword);
		request.getRequestDispatcher("/admin/tag_manager/tag_item_mixstyle_list.jsp").forward(request, response);
		return;
		
    }
    
    /**
     * 混排页面添加商品
     * @throws Exception
     */
    public void addMixStyleItemList(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String tagid = request.getParameter("tagid");
        String keyword = request.getParameter("keyword");

        if(keyword != null && ! "".equals(keyword))
        {
        	keyword = new String(keyword.getBytes("iso-8859-1"), "utf-8");
        }
        
        if(tagid == null)
        {
        	tagid = "";
        }
    	
    	List<TeJiaGoodsBean> list = new ArrayList<TeJiaGoodsBean>();
    	if(!"".equals(tagid)) 
    	{
    		list = Admin_Tag_Item_DAO.getTagItemByTagID(tagid);
    	}
//    	System.out.println(list.size());
    	request.setAttribute("ItemList", list);
		request.setAttribute("tagid", tagid);
		request.setAttribute("keyword", keyword);
		request.getRequestDispatcher("/admin/tag_manager/tag_item_mixstyle_add.jsp").forward(request, response);
		return;
    	
    }
    
    
    public void addMixStyleItemListNew(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String tagid = request.getParameter("tagid");
        String keyword = request.getParameter("keyword");
        String styleId = request.getParameter("style_id");
        String content = "";
        String jsonData = "";
        if(styleId != null && !"".equals(styleId)){
        	content = AdminMMixDAO.getMixPageStyle(styleId);
        	jsonData = AdminMMixDAO.getMixPageStyleJson(styleId);
        }
        
        if(keyword != null && ! "".equals(keyword))
        {
        	keyword = new String(keyword.getBytes("iso-8859-1"), "utf-8");
        }
        
        if(tagid == null)
        {
        	tagid = "";
        }
    	int total = Admin_Tag_Item_DAO.getTagItemByTagIDPage(tagid);
    	List<TeJiaGoodsBean> list = new ArrayList<TeJiaGoodsBean>();
    	if(!"".equals(tagid)) 
    	{
    		list = Admin_Tag_Item_DAO.getTagItemByTagID(tagid,1);
    	}
//    	System.out.println("conten:"+content);
//    	System.out.println(list.size());
    	
    	List<MixPageStyleBean> styleList = AdminMMixDAO.getMMixPageStyle(1);
//    	int total = AdminMMixDAO.getMMixPageStylePage();
    	request.setAttribute("styleList", styleList);
    	request.setAttribute("keyword", keyword);
    	request.setAttribute("list", list);
    	request.setAttribute("page", 1);
    	request.setAttribute("totalpage", total);
    	request.setAttribute("ItemList", list);
		request.setAttribute("tagid", tagid);
		request.setAttribute("keyword", keyword);
		if(content != null && !"".equals(content)){
			content = content.replace("#tagName#", keyword).replace("#tagid#", tagid);
			request.setAttribute("content", content);
			request.setAttribute("jsonData", jsonData);
			request.getRequestDispatcher("/admin/tag_manager/tag_item_mix_choose_add.jsp").forward(request, response);
			return;
		}else{
			request.getRequestDispatcher("/admin/tag_manager/tag_item_mixstyle_new_add.jsp").forward(request, response);
			return;
		}
    	
    }
    
    
    /**
     * 混排页面修改
     * @throws Exception
     */
    public void updateMixStyleItemList(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String tagid = request.getParameter("tagid");
        String keyword = request.getParameter("keyword");
        String id = request.getParameter("id");
        MixStylePageBean ppsBean = Admin_Tag_Item_DAO.getProductPageStyleBean(id);
        
        if(keyword != null && ! "".equals(keyword))
        {
        	keyword = new String(keyword.getBytes("iso-8859-1"), "utf-8");
        }
        
        if(tagid == null)
        {
        	tagid = "";
        }
    	
    	List<TeJiaGoodsBean> list = new ArrayList<TeJiaGoodsBean>();
    	if(!"".equals(tagid)) 
    	{
    		list = Admin_Tag_Item_DAO.getTagItemByTagID(tagid);
    	}
//    	System.out.println(list.size());
    	request.setAttribute("ItemList", list);
    	request.setAttribute("ppsBean", ppsBean);
    	List<TeJiaGoodsBean> tjList =  ppsBean.getTjGoodsBeanList();
    	request.setAttribute("tjList", tjList);
		request.setAttribute("tagid", tagid);
		request.setAttribute("keyword", keyword);
		request.setAttribute("id", id);
		request.getRequestDispatcher("/admin/tag_manager/tag_item_mixstyle_update.jsp").forward(request, response);
		return;
    }
    
    /**
     * 标签下混排方式添加
     * @throws Exception
     */
    public void addMixStyleItem(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	
		String pids = request.getParameter("pids");
		String style = request.getParameter("style");
		String tagid = request.getParameter("tagid");

		// 验证参数
		boolean isreg = isValidParam(pids, style);
		try 
		{
			if(!isreg) 
			{
				response.getWriter().print(getRespJSONString("1"));
				return;
			}
			else
			{
				MixStylePageBean bean = new MixStylePageBean();
				bean.setTag_id(tagid);
				bean.setTypeid(style);
				bean.setItem_ids(pids);

				boolean result = Admin_Tag_Item_DAO.addMixStyleItem(bean);
				if (result) 
				{
					// 写入缓存
					Admin_Tag_Item_Cache_DAO.addStyleProduct(bean);
					
					Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "1", 
							"添加混排样式，标签ID[" + tagid + "]，商品ID[" + pids + "]", "1");
					
					response.getWriter().print(getRespJSONString("0"));
					return;
				}
				response.getWriter().print(getRespJSONString("1"));
			}
		} 
		catch (Exception e)
		{
			response.getWriter().print(getRespJSONString("1"));
		}
    }
    
    public void updateMixStyleItem(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		String pids = request.getParameter("pids");
		String style = request.getParameter("style");
		String id = request.getParameter("id");
		String position = request.getParameter("position");
		String oldpids = request.getParameter("oldpids");
		String tagid = request.getParameter("tagid");

		// 验证参数
		boolean isreg = isValidParam(pids, style);

		try 
		{		
			if(!isreg) 
			{
				response.getWriter().print(getRespJSONString("1"));
				return;
			} 
			else 
			{
				MixStylePageBean bean = new MixStylePageBean();
				bean.setTag_id(tagid);
				bean.setTypeid(style);
				bean.setItem_ids(pids);
				bean.setId(id);
				bean.setRank(position);
				ArrayList<TeJiaGoodsBean> tjGoodsBeanList = Admin_Tag_Item_DAO.getPPSBean(pids);
				bean.setTjGoodsBeanList(tjGoodsBeanList);
				boolean result = Admin_Tag_Item_DAO.updateProductPageStyle(bean);
				if (result) 
				{
					// 更新缓存
					Admin_Tag_Item_Cache_DAO.updateStyleProduct(bean, oldpids);
					
					Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "1", 
							"修改混排样式，标签ID[" + tagid + "]，商品ID[" + pids + "]", "3");
					
					response.getWriter().print(getRespJSONString("0"));
					return;
				}
				response.getWriter().print(getRespJSONString("1"));
			}
		} 
		catch (Exception e)
		{
			response.getWriter().print(getRespJSONString("1"));
		}
    }
    
    /**
     * 标签下混排删除
     * @throws Exception
     */
    public void delMixStyleItem(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String id = request.getParameter("id");
		String pids = request.getParameter("pids");
		String tagid = request.getParameter("tagid");

		boolean result = Admin_Tag_Item_DAO.delProductPageStyle(id);
		if (result) 
		{
			// 删除缓存
			Admin_Tag_Item_Cache_DAO.delStyleProduct(tagid, pids);
			
			Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "1", 
					"删除混排样式，标签ID[" + tagid + "]，混排商品ID[" + pids + "]", "2");
			
			response.getWriter().print(getRespJSONString("0"));
			return;
		}
		response.getWriter().print(getRespJSONString("1"));
    }
    
    /**
     * 混排下商品位置调整
     */
    public void MixStyleItemMovePosition(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String id = request.getParameter("id");
		String movetype = request.getParameter("movetype");
		String position = request.getParameter("position");
		String tag_id = request.getParameter("tag_id");
		boolean flag = false;

		if(id == null||"".equals(id)||tag_id == null||"".equals(tag_id)||position == null||"".equals(position)) 
		{
			response.getWriter().print(getRespJSONString("1"));
			return;
		}
		MixStylePageBean nowbean = Admin_Tag_Item_DAO.getProductPageStyleBean(id);
		
		if ("1".equals(movetype)) // 上移
		{
			MixStylePageBean prebean = Admin_Tag_Item_DAO.getPreBean(position, tag_id);
//			System.out.println(prebean == null);
			if (null != prebean && !prebean.getRank() .equals("0"))
			{
				String rank0 = prebean.getRank();
				flag = Admin_Tag_Item_DAO.updatePosition(Integer.parseInt(id),  Integer.parseInt(prebean.getRank()));
				flag = Admin_Tag_Item_DAO.updatePosition(Integer.parseInt(prebean.getId()), Integer.parseInt(position));
					
				prebean.setRank(position);
				nowbean.setRank(rank0);
					
				Admin_Tag_Item_Cache_DAO.addStyleProduct(nowbean);
				Admin_Tag_Item_Cache_DAO.addStyleProduct(prebean);					
			}
		} 
		else if ("2".equals(movetype)) // 下移
		{
			MixStylePageBean nextbean = Admin_Tag_Item_DAO.getNextBean(position, tag_id);
			if (null != nextbean && !nextbean.getRank().equals("0")) {
				String rank0 = nextbean.getRank();
					
				flag = Admin_Tag_Item_DAO.updatePosition(Integer.parseInt(id),Integer.parseInt( nextbean.getRank()));
				flag = Admin_Tag_Item_DAO.updatePosition(Integer.parseInt(nextbean.getId()), Integer.parseInt(position));
					
				nextbean.setRank(position);
				nowbean.setRank(rank0);
					
				Admin_Tag_Item_Cache_DAO.addStyleProduct(nowbean);
				Admin_Tag_Item_Cache_DAO.addStyleProduct(nextbean);
					
				}
			} 
		else if ("3".equals(movetype))// 移至顶部,修改当前position为最大值+1
		{
			int maxrank = Admin_Tag_Item_DAO.getMaxPosition();				
			flag = Admin_Tag_Item_DAO.updatePosition(Integer.parseInt(id),maxrank);
				
			nowbean.setRank(maxrank+"");
				
			Admin_Tag_Item_Cache_DAO.addStyleProduct(nowbean);
				
		} 
		else if ("4".equals(movetype)) // 移至尾部,修改当前position为最小值-1
		{
			int minrank = Admin_Tag_Item_DAO.getMinPosition();
				
			flag = Admin_Tag_Item_DAO.updatePosition(Integer.parseInt(id), minrank);
				
			nowbean.setRank(minrank+"");
				
			Admin_Tag_Item_Cache_DAO.addStyleProduct(nowbean);
		}
		if(flag) 
		{
			response.getWriter().print(getRespJSONString("0"));
			return;
		}
		response.getWriter().print(getRespJSONString("1"));

    }
    
    /**
     * 刷新混排缓存
     * @throws Exception
     */
    public void ReloadMixStyleItem(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String tagid = request.getParameter("tagid");
    	boolean flag = false;
    	if(tagid == null||"".equals(tagid)) 
    	{
    		response.getWriter().print(getRespJSONString("1"));
    		return;
    	}
    	
    	flag = Admin_Tag_Item_Cache_DAO.reloadCache(tagid);
    	if(flag) 
		{
    		Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "1", 
					"刷新混排样式缓存，标签ID[" + tagid + "]", "3");
    		
			response.getWriter().print(getRespJSONString("0"));
			return;
		}
		response.getWriter().print(getRespJSONString("1"));
    }
    
    /**
     * 锁定与解锁商品
     * @throws Exception
     */
    public void LockItem(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String tagid = request.getParameter("tag_id");
    	String statue = request.getParameter("statue");
    	
    	boolean flag = false;
    	
    	if (tagid == null || "".equals(tagid)) 
    	{
    		response.getWriter().print(getRespJSONString("1"));
    		return;
    	}
    	else
    	{
    		flag = Admin_Tag_Item_DAO.unlock(tagid, statue);
    	}
    	
    	if (flag)
    	{
    		Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "1", 
					"锁定标签下商品，标签ID[" + tagid + "]，数量[" + statue + "]", "3");
    		
    		response.getWriter().print(getRespJSONString("0"));
    		return;
    	}
    	else 
    	{
    		response.getWriter().print(getRespJSONString("1"));
    		return;
    	}
    }
    
    /**
     * 标签商品位置调整
     * @throws Exception
     */
    public void MovePosition(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String tag_id = request.getParameter("tag_id");
    	String itemid0 = request.getParameter("itemid0");
    	String rank0 = request.getParameter("rank0");
    	String itemid1 = "";
    	String rank1 = "";
    	String type = request.getParameter("type");
    	boolean flag = false;
//    	System.out.println(rank0+itemid0);
		if(itemid0 == null ||rank0 == null ||tag_id == null)
		{
			response.getWriter().print(getRespJSONString("1"));
			return;
		}
		TagToItemBean bean = null;
			
		if(type.equals("1"))	//上移
		{
			bean = Admin_Tag_Item_DAO.getNextRankBean(rank0,tag_id);				
		}
		else if(type.equals("2")) 	//下移
		{
			bean = Admin_Tag_Item_DAO.getPreRankBean(rank0,tag_id);
		}
		else if(type.equals("3")) 	//首位
		{
			bean = Admin_Tag_Item_DAO.getMinRankBean(tag_id);
		}
		else 	if(type.equals("4"))	//末尾
		{
			bean = Admin_Tag_Item_DAO.getMaxRankBean(tag_id);
		}
		else if(type.equals("5"))	//移动到本页首位
		{
			itemid1 = request.getParameter("itemid1");
			rank1 = request.getParameter("rank1");
			bean = new TagToItemBean();
			bean.setItemid(itemid1);
			bean.setRank(Integer.parseInt(rank1));
		}
		else if(type.equals("6"))	//移动到本页末
		{
			itemid1 = request.getParameter("itemid1");
			rank1 = request.getParameter("rank1");
			bean = new TagToItemBean();
			bean.setItemid(itemid1);
			bean.setRank(Integer.parseInt(rank1));
		}
		if(bean!=null) 
		{
			itemid1 = bean.getItemid();
			rank1 = bean.getRank()+"";
		}	
		else if(bean == null)
		{
			response.getWriter().print(getRespJSONString("1"));
	    	return;
		}
		flag = Admin_Tag_Item_DAO.moveProduct(tag_id, itemid0, Integer.parseInt(rank0), itemid1,Integer.parseInt(rank1), type);
		if(flag) 
		{
			if(type.equals("3"))	//首位
			{
				Admin_Tag_Item_Cache_DAO.addItem2Tag(tag_id, itemid0, Integer.parseInt(rank1)-1);
			}
			else if(type.equals("4"))	//末尾
			{
				Admin_Tag_Item_Cache_DAO.addItem2Tag(tag_id, itemid0, Integer.parseInt(rank1)+1);
			}
			else
			{
				Admin_Tag_Item_Cache_DAO.addItem2Tag(tag_id, itemid0, Integer.parseInt(rank1));
				Admin_Tag_Item_Cache_DAO.addItem2Tag(tag_id, itemid1, Integer.parseInt(rank0));
			}
		}
			
    if(flag)
    {
    	response.getWriter().print(getRespJSONString("0"));
    	return;
    }
    else
    {
    	response.getWriter().print(getRespJSONString("1"));
    	return;
    }
    	
    }
    
    /**
     * 查看点击量
     * @throws Exception
     */
    public void checkClickNum(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String adid = request.getParameter("id");
		String title = request.getParameter("title");
		if(title == null) 
		{
			title="";
		}
		
		if(adid == null||"".equals(adid))
		{			
			request.setAttribute("content", "['',]");
			request.setAttribute("title", title);
			request.getRequestDispatcher("/admin/tongji/click_num.jsp").forward(request, response);
			return;
		}

		String strUrl = NewUrl+"GetAdViewServlet?id="+adid+"";

		String content =  NetManager.getContent(strUrl);			
	
		if(content==null)
		{
			content = "['',]";
		}
//		content = "[{\"time\":\"2013-07-29\",\"count\":\"10\"},{\"time\":\"2013-07-30\",\"count\":\"10\"},{\"time\":\"2013-07-31\",\"count\":\"10\"}]";
		List<TongjiBean> list = getListFromJson(content);
		request.setAttribute("list", list);
		content = TongjiJsonConvert(content);
		request.setAttribute("title", title);
		request.setAttribute("content", content);
		request.getRequestDispatcher("/admin/tongji/click_num.jsp").forward(request, response);
		return;
		
    }
    
    /**
     * 子标签增加
     * @throws Exception
     */
    public void additemtotag(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String ctagid = request.getParameter("ctagid");
		String itemid = request.getParameter("itemid");  
		String rank = request.getParameter("rank");
		String flag = "";
		
		flag = Admin_Tag_Item_DAO.addTagtoItem(ctagid, itemid, rank);

		if(!"".equals(flag)) 
		{			
			Admin_Tag_Item_Cache_DAO.addItem2Tag(ctagid, itemid, Integer.parseInt(flag));
			
			Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "1", 
					"添加商品至子标签，子标签ID[" + ctagid + "]，商品ID[<a target=\"_blank\" style=\"color: #1F92FF\" href=\"http://b17.cn/item?itemid=" + itemid + "\">" + itemid + "</a>]",
					"1");
			
			response.getWriter().print(getRespJSONString("0"));
    	}
    	else
    	{
    		response.getWriter().print(getRespJSONString("1"));
    	}

		
    }
    
    /**
     * 子标签删除
     * @throws Exception
     */
    public void delitemtotag(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		String ctagid = request.getParameter("ctagid");
		String itemid = request.getParameter("itemid");    
		boolean flag = false;
		
		flag = Admin_Tag_Item_DAO.delTagtoItem(ctagid, itemid);
		
		if(flag) 
		{
			Admin_Tag_Item_Cache_DAO.delItem2Tag(ctagid, itemid);
			
			Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "1", 
					"从子标签移除商品，子标签ID[" + ctagid + "]，商品ID[<a target=\"_blank\" style=\"color: #1F92FF\" href=\"http://b17.cn/item?itemid=" + itemid + "\">" + itemid + "</a>]", 
					"2");
			
			response.getWriter().print(getRespJSONString("0"));
    	}
    	else
    	{
    		response.getWriter().print(getRespJSONString("1"));
    	}
		
    }
    
    /**
     * 商品 图片，价格，集分宝比例 修改
     * @throws Exception
     */
    public void updateItemImg(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String newprice = request.getParameter("newprice");
		String img = request.getParameter("img");
		String jfb_rate = request.getParameter("jfb_rate");
		String itemId = request.getParameter("itemid"); 
		String title = request.getParameter("title");
		String clickUrl = request.getParameter("click_url");
		String biaoti = request.getParameter("biaoti");
		TeJiaGoodsBean bean = Admin_Tag_Item_DAO.getDiscountProduct(itemId);
		if(img != null && !"".equals(img) && !img.equals(bean.getPic_url()))
		{
			img = getSmallImg(img);
			img = img.replaceAll(" ", "");
			bean.setPic_url(img);
		}
		if(newprice != null && !"".equals(newprice) && !"null".equalsIgnoreCase(newprice) && !newprice.equals(bean.getPrice_low()))
		{
			bean.setPrice_low(newprice);
		}
		if(title != null && !"".equals(title)){
			bean.setTitle(title);
		}
		if(clickUrl != null && !"".equals(clickUrl) && !"null".equals(clickUrl)){
			try 
			{
				clickUrl = NetManager.convertToPageViewUrl(clickUrl, "", "", "meili");
				if (clickUrl.indexOf(SysConf.hostUrl)< 0 || (clickUrl.replaceAll (SysConf.hostUrl,"")).length() >10)
				{
					// 地址转换失败，操作错误，返回：1
		    		response.getWriter().print(getRespJSONString("2"));
		    		return;
				}
			} 
			catch (Exception e) 
			{
				// 地址转换失败，操作错误，返回：1
	    		response.getWriter().print(getRespJSONString("2"));
	    		return;
			}
			bean.setClickURL(clickUrl);
		}
		if(biaoti != null && !"".equals(biaoti) && !"null".equals(biaoti)){
			bean.setKeyword(biaoti);
		}
		
		
		// 如果集分宝比例被修改，增加集分宝比例的修改历史记录
		if(jfb_rate != null && !"".equals(jfb_rate) && !"null".equalsIgnoreCase(jfb_rate))
		{
			// 检查该商品所属分类的集分宝比例是否为0
			String catID = bean.getCatID();
			if (catID != null && !catID.equals("") && !"null".equals(catID))
			{
				double catRate = Admin_Tag_Item_DAO.getCatRate(bean.getCatID());
				if (catRate == 0)
				{
					jfb_rate = String.valueOf(0);
				}
			}
			
			if (Double.parseDouble(jfb_rate) != bean.getRate())
			{
				bean.setRate(Double.parseDouble(jfb_rate));
			}
			Admin_Tag_Item_DAO.addItemJfbRateHistory(itemId, String.valueOf(Double.parseDouble(jfb_rate)));
		}
		
		// 更新商品表数据
		boolean flag = Admin_Tag_Item_DAO.updateDiscountProductImg(bean);
		if(flag)
		{
			// 更新REDIS缓存数据
			bean.setUpdate_time(System.currentTimeMillis()+"");
			Admin_Tag_Item_Cache_DAO.add_update_Item(bean);
		}

		if(flag)
		{
			Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "1", 
					"修改商品的图片,价格,集分宝比例，商品ID[" + itemId + "]，新价格[" + newprice + "]，新图片[" + img + "]，新集分宝比例[" + jfb_rate + "]", "3");
			
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("jfb_rate", jfb_rate);
			response.getWriter().print(getRespJSONStringWithParam("0", params));
    	}
    	else
    	{
    		response.getWriter().print(getRespJSONString("1"));
    	}
		return;
	}
	
    /**
     * 获取购买总量最多的商品
     * @param request
     * @param response
     * @throws Exception
     */
    public void showTopTradeItemList(HttpServletRequest request, HttpServletResponse response) throws Exception{
    	request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
    	String p = request.getParameter("page");
    	String startTime = request.getParameter("start_time");
    	String endTime = request.getParameter("end_time");
    	Date dt = new Date();   
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");  
	    String nowDate = sdf.format(dt); 
	    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");  
	    String nowDate1 = sdf1.format(dt); 
	    long startTimestamp = 0;
	    long endTimestamp  = 0;
    	if(startTime == null || "".equals(startTime)){
    		startTimestamp = CodeUtil.getTimeMillis_2(nowDate+"-01 00:00:00")/1000 ; 
    	}else {
    		startTimestamp = CodeUtil.getTimeMillis_2(startTime.substring(0, 10)+" 00:00:00")/1000;
    	}
    	if(endTime == null || "".equals(endTime)){
    		endTimestamp = CodeUtil.getTimeMillis_2(nowDate1+" 23:59:59")/1000;
    	}else {
    		endTimestamp = CodeUtil.getTimeMillis_2(endTime.substring(0, 10)+" 23:59:59")/1000;
    	}
    	int page = 1;
    	try {
    		page = Integer.parseInt(p);
		} catch (Exception e) {
			page = 1;
		}
    	if(page < 1) page = 1;
    	if(page >10) page = 10;
//    	startTime = "1391184000000";
//    	endTime= "1393603200000";
    	List<TeJiaGoodsBean> list = Admin_Tag_Item_DAO.getTopTradeItem(startTimestamp, endTimestamp, page);
    	if(list == null || list.size() == 0){
//    		long time = System.currentTimeMillis();
//    		JobExecutor.JobExecutor.execute(new RefreshTopItem(startTime, endTime));
    		RefreshTopItem.updateHotItem(startTimestamp, endTimestamp);
    		list = Admin_Tag_Item_DAO.getTopTradeItem(startTimestamp, endTimestamp,page);
//    		System.out.println(System.currentTimeMillis()-time);
//    		request.setAttribute("type", "update");
//    		request.getRequestDispatcher("/admin/tag_manager/hot_trade_item_list.jsp").forward(request, response);
//    		return;
    	}
    	int total = Admin_Tag_Item_DAO.getTopTradeItemPage(startTimestamp, endTimestamp);
    	String startDate = CodeUtil.getDateStr(startTimestamp*1000);
    	String endDate = CodeUtil.getDateStr(endTimestamp*1000);
//    	int total = Admin_Tag_Item_DAO.getTopTradeItemPage(startTime, endTime);
    	request.setAttribute("startDate", startDate);
    	request.setAttribute("endDate", endDate);
    	request.setAttribute("totalpage", total);
		request.setAttribute("page", page);
		request.setAttribute("ItemList", list);
    	
    	
    	request.getRequestDispatcher("/admin/tag_manager/hot_trade_item_list.jsp").forward(request, response);
		return;
    }
    
    /**
     * 将标签下前N个商品重新排序
     * @param request
     * @param response
     * @throws Exception
     */
    public void upSetTodayTagItemPosition(HttpServletRequest request, HttpServletResponse response) throws Exception{
    	String tagid = request.getParameter("tagid");
    	String totalStr = request.getParameter("upsetNum");
    	if(tagid == null || "".equals(tagid)){
    		response.getWriter().print("<script>alert('parameter error！');history.back();</script>");
			return;
    	}
    	int total = 30;
    	try {
    		total = Integer.parseInt(totalStr);			
		} catch (Exception e) {
			total = 30;
		}
    	
    	List<TagToItemBean> list = GetTagItemFromRedis.getTagItemList(tagid, total);
//    	List<TagToItemBean> list = Admin_Tag_Item_DAO.getTopTagItem(tagid, total);   
    	if(list == null || list.size() == 0){
    		response.getWriter().print(getRespJSONString("1"));
    		return;
    	}
    	List<Integer> rankList = new ArrayList<Integer>();
    	List<KeywordBean> ctagList = Admin_Tag_Cache_DAO.getTagsByParentId(tagid);
    	Map<String ,List<TagToItemBean>> map = new HashMap<String, List<TagToItemBean>>();
    	if(ctagList != null && ctagList.size() > 0){
    		for(KeywordBean ctag : ctagList){
    			List<TagToItemBean> cTagItemList = new ArrayList<TagToItemBean>();
    			map.put(ctag.getId(), cTagItemList);
    		}
    	}
    	List<String> itemList = new ArrayList<String>();
    	for(TagToItemBean bean : list){
    		for(KeywordBean keyword : ctagList){
    			if(keyword.getItemids().contains(bean.getItemid()) && !itemList.contains(bean.getItemid())){
    				itemList.add(bean.getItemid());
    				String tagId = keyword.getId();
    				List<TagToItemBean> value = map.get(tagId);
    				value.add(bean);
    				map.put(tagId, value);
    			}
    		}
    		
    		rankList.add(bean.getRank());

    	}
    	int min = 1000;
    	List<Integer> totalNumList = new ArrayList<Integer>();
    	
    	Iterator<String> iter = map.keySet().iterator();
    	
    	int to = 0;
    	while (iter.hasNext()) {
    		
    		String key = iter.next();
    		
    		int cTagSize = map.get(key).size();
//    		System.out.println(cTagSize);
    		if(cTagSize > 0){    			
    			to ++;
    		}
    		if(cTagSize < min && cTagSize > 0){
    			min = cTagSize;
    		}
    		totalNumList.add(cTagSize);
    	}
//    	System.out.println(to+"hhhhhhhshenm"+min);
    	List<TagToItemBean> cTagItemListDuoyu = new ArrayList<TagToItemBean>();
    	Map<Integer,TagToItemBean> endMap = new HashMap<Integer, TagToItemBean>();
//    	List<TagToItemBean> listAll = new ArrayList<TagToItemBean>();
    	
    	Iterator<String> iter1 = map.keySet().iterator();
    	try {
			int k = 0;
    		while (iter1.hasNext()) {
    			
    			String key = iter1.next();
    			
    			List<TagToItemBean> cTagItemList = map.get(key);
    			if(cTagItemList !=null && cTagItemList.size() >= min){    				
    				for(int i = 0 ; i < min ; i ++){
//    					System.out.println(i+"jjjj"+cTagItemList.get(i));
    					endMap.put(i*to+k, cTagItemList.get(i));
//    					listAll.add(i*to+k,cTagItemList.get(i));
    				}
    			}
    			if(cTagItemList.size() > min){
    				cTagItemListDuoyu.addAll(cTagItemList.subList(min, cTagItemList.size()));
    			}
    			k++;
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	int t = cTagItemListDuoyu.size();
    	int endSize = endMap.size();
    	int xunhuan = endSize - 1;
    	if(cTagItemListDuoyu.size() > 0){
    		
    		for(int j = 0 ; j < t ; j ++){
    			Random r = new Random();
        		int rank = r.nextInt(cTagItemListDuoyu.size());
        		endMap.put(xunhuan, cTagItemListDuoyu.get(rank));
//        		listAll.add(cTagItemListDuoyu.get(rank));
        		cTagItemListDuoyu.remove(rank);
        		xunhuan++;
    		}
    	}
    	int r = 0;
    	Iterator<Integer> iterEnd = endMap.keySet().iterator();
    	while (iterEnd.hasNext()) {
			
			int key = iterEnd.next();
			TagToItemBean bean = endMap.get(key);
//			System.out.println(bean.getTagid() +"     gfgfgfg   "+ bean.getItemid()+"          ffffffffffffff  "+rankList.get(r));
			int rank = rankList.get(r);
			bean.setRank((rank));
			Admin_Tag_Item_DAO.updateTagItemRank(bean);
			Admin_Tag_Item_Cache_DAO.addItem2Tag(bean.getTagid(), bean.getItemid(), rank);
			r++;
    	}
    	
//    	for(TagToItemBean bean : listAll){
//    		System.out.println(bean.getItemid()+"          ffffffffffffff  "+rankList.get(r));
//		int rank = rankList.get(r);
//		bean.setRank((rank));
//		Admin_Tag_Item_DAO.updateTagItemRank(bean);
//		Admin_Tag_Item_Cache_DAO.addItem2Tag(bean.getTagid(), bean.getItemid(), rank);
//		r++;
//	}
    	
    	
    	
    	
    	
    	
    	
//    	Collections.sort(totalNumList);
//    	int i = 0;
//    	List<String> ctagStrList = new ArrayList<String>();
//    	while (iter.hasNext()) {
//    		cTagTotal = totalNumList.size();
//    		String key = iter.next();
//    		List<TagToItemBean> ctag = map.get(key);
//    		int begin = 0;
//    		for(int k = 0 ;k < cTagTotal ; k++){
//    			
//    			for(int j = begin ; j < min ; j ++){  					
//    					ctagStrList.add(i*min,ctag.get(j).getId());
//    			}
//    			List<Integer> nowList = new ArrayList<Integer>();
//    			for(int m = 0 ; m < totalNumList.size() ; m ++){
//    				if(totalNumList.get(m) > min){
//    					nowList.add(totalNumList.get(m));
//    				}
//    			}
//    			if(nowList != null && nowList.size() != 0){
//    				begin = min;
//    				min = nowList.get(0);
//    			}
//    			totalNumList = nowList;
//    		}
//    		
//    		i++;
//    	}
    	
//    	for(TagToItemBean bean : list){
//    		Random r = new Random();
//    		int rank = r.nextInt(rankList.size());
//    		bean.setRank(rankList.get(rank));
//    		Admin_Tag_Item_DAO.updateTagItemRank(bean);
//    		Admin_Tag_Item_Cache_DAO.addItem2Tag(bean.getTagid(), bean.getItemid(), rankList.get(rank));
//    		rankList.remove(rank);
//    	}
    	response.getWriter().print(getRespJSONString("0"));
    }
    
    /**
     * 新增混排商品
     * @param request
     * @param response
     * @throws Exception
     */
    public void addItemMixStyleNew(HttpServletRequest request, HttpServletResponse response) throws Exception{
    	String fenge = request.getParameter("fenge");
    	String tagId = request.getParameter("tagid");
    	String width = request.getParameter("width");
    	String height = request.getParameter("height");
    	String tagName = request.getParameter("keyword");
    	if(tagName!= null && !"".equals(tagName)){
    		tagName = new String(tagName.getBytes("iso-8859-1"), "utf-8");
    	}
    	boolean flag = false;
    	if(fenge == null || "".equals(fenge) || tagId == null || "".equals(tagId) || width == null || "".equals(width) || height == null || "".equals(height)){
    		response.getWriter().print("<script>alert('Parameter error！');history.back();</script>");
			return;
    	}
    	String whole_up = request.getParameter("whole_up");
		String whole_left = request.getParameter("whole_left");
		String whole_up_up = request.getParameter("whole_up_up");
		String whole_up_left = request.getParameter("whole_up_left");
		String whole_down_up = request.getParameter("whole_down_up");
		String whole_down_left = request.getParameter("whole_down_left");
		String whole_left_up = request.getParameter("whole_left_up");
		String whole_left_left = request.getParameter("whole_left_left");
		String whole_right_up = request.getParameter("whole_right_up");
		String whole_right_left = request.getParameter("whole_right_left");	
		JSONObject jso = new JSONObject();
		jso.put("is_levels", 0);
		jso.put("item_id", "");
		jso.put("url", "");
		jso.put("type", "whole");
		jso.put("pic", "");
		jso.put("width", width);
		jso.put("height",height);
    	if(fenge.equals("1")){//无分割
    		String pic = request.getParameter("wholeimg");
    		String itemId = request.getParameter("wholeitemtid");
    		String url = request.getParameter("wholeclickUrl");
    		jso.put("is_levels", 1);
    		jso.put("item_id", itemId);
    		jso.put("url", url);
    		jso.put("type", "whole");
    		jso.put("pic", pic);
    	}else if(fenge.equals("2")){//最小分割到1/2
    		
    		JSONArray jsa = new JSONArray();
    		if(whole_up != null && whole_up.equals("2")){	//上下分
    			jsa = getEighthJsa("wholeUpitemtid","wholeUpimg","wholeUpclickUrl","wholeDownitemtid","wholeDownimg","wholeDownclickUrl","up",request);
				
    		}else if(whole_left != null && whole_left.equals("2")){	//左右分
    			jsa = getEighthJsa("wholeLeftitemtid","wholeLeftimg","wholeLeftclickUrl","wholeRightitemtid","wholeRightimg","wholeRightclickUrl","left",request);
				
    		}
    		jso.put("half", jsa);
    	}else if(fenge.equals("3")){//最小分割到1/4
    		JSONArray halfJsa = new JSONArray();
    		JSONObject halfJso = new JSONObject();
    		if(whole_up != null && whole_up.equals("2")){	//上下分
    			halfJso.put("is_levels", 0);
    			halfJso.put("item_id", "");
    			halfJso.put("url", "");
    			halfJso.put("type", "up");
    			halfJso.put("pic", "");
    			if(whole_up_up != null && whole_up_up.equals("2")){	//上半部分上下分
        			JSONArray jsa = new JSONArray();
        			jsa = getEighthJsa("wholeUpUpitemtid","wholeUpUpimg","wholeUpUpclickUrl","wholeUpDownitemtid","wholeUpDownimg","wholeUpDownclickUrl","up",request);
					
        			halfJso.put("quarter", jsa);
    			}else if(whole_up_left != null && whole_up_left.equals("2")){	//上半部分左右分
        			JSONArray jsa = new JSONArray();
        			jsa = getEighthJsa("wholeUpLeftitemtid","wholeUpLeftimg","wholeUpLeftclickUrl","wholeUpRightitemtid","wholeUpRightimg","wholeUpRightclickUrl","left",request);
					
        			halfJso.put("quarter", jsa);
    			}else {	//上半部分不分
    				String wholeUpitemtid = request.getParameter("wholeUpitemtid");
        			String wholeUpimg = request.getParameter("wholeUpimg");
        			String wholeUpclickUrl = request.getParameter("wholeUpclickUrl");
        			halfJso.put("is_levels", 1);
        			halfJso.put("item_id", wholeUpitemtid);
        			halfJso.put("url", wholeUpclickUrl);
        			halfJso.put("type", "up");
        			halfJso.put("pic", wholeUpimg);
    			}
    			halfJsa.put(0,halfJso);
    			halfJso = new JSONObject();
    			halfJso.put("is_levels", 0);
    			halfJso.put("item_id", "");
    			halfJso.put("url", "");
    			halfJso.put("type", "up");
    			halfJso.put("pic", "");
    			if(whole_down_up != null && whole_down_up.equals("2")){	//下半部分上下分
        			JSONArray jsa = new JSONArray();
        			jsa = getEighthJsa("wholeDownUpitemtid","wholeDownUpimg","wholeDownUpclickUrl","wholeDownDownitemtid","wholeDownDownimg","wholeDownDownclickUrl","left",request);
					
        			halfJso.put("quarter", jsa);
    			}else if(whole_down_left != null && whole_down_left.equals("2")){	//下半部分左右分
        			JSONArray jsa = new JSONArray();
        			jsa = getEighthJsa("wholeDownLeftitemtid","wholeDownLeftimg","wholeDownLeftclickUrl","wholeDownRightitemtid","wholeDownRightimg","wholeDownRightclickUrl","left",request);
					
        			halfJso.put("quarter", jsa);
    			}else{	//下半部分不分
    				String wholeDownitemtid = request.getParameter("wholeDownitemtid");
        			String wholeDownimg = request.getParameter("wholeDownimg");
        			String wholeDownclickUrl = request.getParameter("wholeDownclickUrl");
        			halfJso.put("is_levels", 1);
        			halfJso.put("item_id", wholeDownitemtid);
        			halfJso.put("url", wholeDownclickUrl);
        			halfJso.put("type", "up");
        			halfJso.put("pic", wholeDownimg);
    			}
    			halfJsa.put(1,halfJso);
    		}else if(whole_left != null && whole_left.equals("2")){	//左右分
    			halfJso.put("is_levels", 0);
    			halfJso.put("item_id", "");
    			halfJso.put("url", "");
    			halfJso.put("type", "left");
    			halfJso.put("pic", "");
    			if(whole_left_up != null && whole_left_up.equals("2")){	//左半部分上下分
        			JSONArray jsa = new JSONArray();
        			jsa = getEighthJsa("wholeLeftUpitemtid","wholeLeftUpimg","wholeLeftUpclickUrl","wholeLeftDownitemtid","wholeLeftDownimg","wholeLeftDownclickUrl","up",request);
					
        			halfJso.put("quarter", jsa);
    			}else if(whole_left_left != null && whole_left_left.equals("2")){	//左半部分左右分        			
        			JSONArray jsa = new JSONArray();
        			jsa = getEighthJsa("wholeLeftLeftitemtid","wholeLeftLeftimg","wholeLeftLeftclickUrl","wholeLeftRightitemtid","wholeLeftRightimg","wholeLeftRightclickUrl","left",request);
					
        			halfJso.put("quarter", jsa);
    			}else{//左半部分不分
    				String wholeLeftitemtid = request.getParameter("wholeLeftitemtid");
        			String wholeLeftimg = request.getParameter("wholeLeftimg");
        			String wholeLeftclickUrl = request.getParameter("wholeLeftclickUrl");
        			halfJso.put("is_levels", 1);
        			halfJso.put("item_id", wholeLeftitemtid);
        			halfJso.put("url", wholeLeftclickUrl);
        			halfJso.put("type", "left");
        			halfJso.put("pic", wholeLeftimg);
    			}
    			halfJsa.put(0,halfJso);
    			halfJso = new JSONObject();
    			halfJso.put("is_levels", 0);
    			halfJso.put("item_id", "");
    			halfJso.put("url", "");
    			halfJso.put("type", "left");
    			halfJso.put("pic", "");
    			if(whole_right_up != null && whole_right_up.equals("2")){	//右半部分上下分       			
        			JSONArray jsa = new JSONArray();
        			jsa = getEighthJsa("wholeRightUpitemtid","wholeRightUpimg","wholeRightUpclickUrl","wholeRightDownitemtid","wholeRightDownimg","wholeRightDownclickUrl","up",request);
					
        			halfJso.put("quarter", jsa);
    			}else if(whole_right_left != null && whole_right_left.equals("2")){	//右半部分左右分
        			
        			JSONArray jsa = new JSONArray();
        			jsa = getEighthJsa("wholeRightLeftitemtid","wholeRightLeftimg","wholeRightLeftclickUrl","wholeRightRightitemtid","wholeRightRightimg","wholeRightRightclickUrl","up",request);
					
        			halfJso.put("quarter", jsa);
    			}else{//右半部分不分
    				String wholeRightitemtid = request.getParameter("wholeRightitemtid");
        			String wholeRightimg = request.getParameter("wholeRightimg");
        			String wholeRightclickUrl = request.getParameter("wholeRightclickUrl");
        			halfJso.put("is_levels", 1);
        			halfJso.put("item_id", wholeRightitemtid);
        			halfJso.put("url", wholeRightclickUrl);
        			halfJso.put("type", "left");
        			halfJso.put("pic", wholeRightimg);
    			}
    			halfJsa.put(1,halfJso);
    		}
    		jso.put("half", halfJsa);
    	}else if(fenge.equals("4")){//最小分割到1/8
    		JSONArray halfJsa = new JSONArray();
    		JSONObject halfJso = new JSONObject();
    		if(whole_up != null && whole_up.equals("2")){	//上下分
    			halfJso.put("is_levels", 0);
    			halfJso.put("item_id", "");
    			halfJso.put("url", "");
    			halfJso.put("type", "up");
    			halfJso.put("pic", "");
				
    			JSONArray jsa = new JSONArray();
    			if(whole_up_up != null && whole_up_up.equals("2")){	//上半部分上下分
    				JSONObject quarterJso = new JSONObject();
    				quarterJso.put("is_levels", 0);
    				quarterJso.put("item_id", "");
    				quarterJso.put("url", "");
    				quarterJso.put("type", "up");
    				quarterJso.put("pic", "");
					
    				String whole_up_up_up = request.getParameter("whole_up_up_up");
    				String whole_up_up_left = request.getParameter("whole_up_up_left");
    				String whole_up_down_up = request.getParameter("whole_up_down_up");
    				String whole_up_down_left = request.getParameter("whole_up_down_left");
    				if(whole_up_up_up != null && whole_up_up_up.equals("2")){	//上半部分的上半部分上下分
    					JSONArray eighthJsa = new JSONArray();
    					eighthJsa = getEighthJsa("wholeUpUpUpitemtid","wholeUpUpUpimg","wholeUpUpUpclickUrl","wholeUpUpDownitemtid","wholeUpUpDownimg","wholeUpUpDownclickUrl","up",request);
    					
    					quarterJso.put("eighth", eighthJsa);
    				}else if(whole_up_up_left != null && whole_up_up_left.equals("2")){	//上半部分的上半部分左右分
    					JSONArray eighthJsa = new JSONArray();
    					eighthJsa = getEighthJsa("wholeUpUpLeftitemtid","wholeUpUpLeftimg","wholeUpUpLeftclickUrl","wholeUpUpRightitemtid","wholeUpUpRightimg","wholeUpUpRightclickUrl","left",request);
    					
    					quarterJso.put("eighth", eighthJsa);
    				}else{	//上半部分的上半部分不分
    					String wholeUpUpitemtid = request.getParameter("wholeUpUpitemtid");
            			String wholeUpUpimg = request.getParameter("wholeUpUpimg");
            			String wholeUpUpclickUrl = request.getParameter("wholeUpUpclickUrl");
            			
    					quarterJso.put("is_levels", 1);
    					quarterJso.put("item_id", wholeUpUpitemtid);
    					quarterJso.put("url", wholeUpUpclickUrl);
    					quarterJso.put("type", "up");
    					quarterJso.put("pic", wholeUpUpimg);
    				}
    				jsa.put(0,quarterJso);
    				quarterJso = new JSONObject();
    				quarterJso.put("is_levels", 0);
    				quarterJso.put("item_id", "");
    				quarterJso.put("url", "");
    				quarterJso.put("type", "up");
    				quarterJso.put("pic", "");
    				if(whole_up_down_up != null && whole_up_down_up.equals("2")){	//上半部分的下部分上下分
    					JSONArray eighthJsa = new JSONArray();
    					eighthJsa = getEighthJsa("wholeUpDownUpitemtid","wholeUpDownUpimg","wholeUpDownUpclickUrl","wholeUpDownDownitemtid","wholeUpDownDownimg","wholeUpDownDownclickUrl","up",request);
    					
    					quarterJso.put("eighth", eighthJsa);
    				}else if(whole_up_down_left != null && whole_up_down_left.equals("2")){	//上半部分的下部分左右分
    					JSONArray eighthJsa = new JSONArray();
    					eighthJsa = getEighthJsa("wholeUpDownLeftitemtid","wholeUpDownLeftimg","wholeUpDownLeftclickUrl","wholeUpDownRightitemtid","wholeUpDownRightimg","wholeUpDownRightclickUrl","left",request);
    					
    					quarterJso.put("eighth", eighthJsa);
    				}else{//上半部分的下部分不分
    					String wholeUpDownitemtid = request.getParameter("wholeUpDownitemtid");
            			String wholeUpDownimg = request.getParameter("wholeUpDownimg");
            			String wholeUpDownclickUrl = request.getParameter("wholeUpDownclickUrl");
            			
    					quarterJso.put("is_levels", 1);
    					quarterJso.put("item_id", wholeUpDownitemtid);
    					quarterJso.put("url", wholeUpDownclickUrl);
    					quarterJso.put("type", "up");
    					quarterJso.put("pic", wholeUpDownimg);
    				}
    				jsa.put(1,quarterJso);
    			}else if(whole_up_left != null && whole_up_left.equals("2")){	//上半部分左右分
    				JSONObject quarterJso = new JSONObject();
    				quarterJso.put("is_levels", 0);
    				quarterJso.put("item_id", "");
    				quarterJso.put("url", "");
    				quarterJso.put("type", "left");
    				quarterJso.put("pic", "");
    				String whole_up_left_up = request.getParameter("whole_up_left_up");
    				String whole_up_left_left = request.getParameter("whole_up_left_left");
    				String whole_up_right_up = request.getParameter("whole_up_right_up");
    				String whole_up_right_left = request.getParameter("whole_up_right_left");
    				
    				if(whole_up_left_up != null && whole_up_left_up.equals("2")){	//上半部分的左半部分上下分
    					JSONArray eighthJsa = new JSONArray();
    					eighthJsa = getEighthJsa("wholeUpLeftUpitemtid","wholeUpLeftUpimg","wholeUpLeftUpclickUrl","wholeUpLeftDownitemtid","wholeUpLeftDownimg","wholeUpLeftDownclickUrl","up",request);
    					
    					quarterJso.put("eighth", eighthJsa);
    				}else if(whole_up_left_left != null && whole_up_left_left.equals("2")){	//上半部分的左半部分左右分
    					JSONArray eighthJsa = new JSONArray();
    					eighthJsa = getEighthJsa("wholeUpLeftLeftitemtid","wholeUpLeftLeftimg","wholeUpLeftLeftclickUrl","wholeUpLeftRightitemtid","wholeUpLeftRightimg","wholeUpLeftRightclickUrl","left",request);
    					quarterJso.put("eighth", eighthJsa);
    				}else{	//上半部分的左半部分不分
    					String wholeUpLeftitemtid = request.getParameter("wholeUpLeftitemtid");
            			String wholeUpLeftimg = request.getParameter("wholeUpLeftimg");
            			String wholeUpLeftclickUrl = request.getParameter("wholeUpLeftclickUrl");
    					quarterJso.put("is_levels", 1);
    					quarterJso.put("item_id", wholeUpLeftitemtid);
    					quarterJso.put("url", wholeUpLeftclickUrl);
    					quarterJso.put("type", "left");
    					quarterJso.put("pic", wholeUpLeftimg);
    				}
    				jsa.put(0,quarterJso);
    				quarterJso = new JSONObject();
    				quarterJso.put("is_levels", 0);
        			quarterJso.put("item_id", "");
        			quarterJso.put("url", "");
        			quarterJso.put("type", "left");
        			quarterJso.put("pic", "");
    				if(whole_up_right_up != null && whole_up_right_up.equals("2")){	//上半部分的右部分上下分
    					JSONArray eighthJsa = new JSONArray();
    					eighthJsa = getEighthJsa("wholeUpRightUpitemtid","wholeUpRightUpimg","wholeUpRightUpclickUrl","wholeUpRightDownitemtid","wholeUpRightDownimg","wholeUpRightDownclickUrl","up",request);
    					
    					quarterJso.put("eighth", eighthJsa);
    				}else if(whole_up_right_left != null && whole_up_right_left.equals("2")){	//上半部分的右部分左右分
    					JSONArray eighthJsa = new JSONArray();
    					eighthJsa = getEighthJsa("wholeUpRightLeftitemtid","wholeUpRightLeftimg","wholeUpRightLeftclickUrl","wholeUpRightRightitemtid","wholeUpRightRightimg","wholeUpRightRightclickUrl","left",request);
    					quarterJso.put("eighth", eighthJsa);
    				}else{//上半部分的右部分不分
    					String wholeUpRightitemtid = request.getParameter("wholeUpRightitemtid");
            			String wholeUpRightimg = request.getParameter("wholeUpRightimg");
            			String wholeUpRightclickUrl = request.getParameter("wholeUpRightclickUrl");
            			quarterJso.put("is_levels", 1);
            			quarterJso.put("item_id", wholeUpRightitemtid);
            			quarterJso.put("url", wholeUpRightclickUrl);
            			quarterJso.put("type", "left");
            			quarterJso.put("pic", wholeUpRightimg);
    				}
    				jsa.put(1,quarterJso);
    				halfJso.put("quarter", jsa);
//    				halfJsa.put(0,halfJso)	;
    			}
    			else {	//上半部分不分
    				String wholeUpitemtid = request.getParameter("wholeUpitemtid");
        			String wholeUpimg = request.getParameter("wholeUpimg");
        			String wholeUpclickUrl = request.getParameter("wholeUpclickUrl");
    				halfJso.put("is_levels", 1);
    				halfJso.put("item_id", wholeUpitemtid);
    				halfJso.put("url", wholeUpclickUrl);
    				halfJso.put("type", "up");
    				halfJso.put("pic", wholeUpimg);	
    			}
    			halfJsa.put(0,halfJso);
    			halfJso = new JSONObject();
    			if(whole_down_up != null && whole_down_up.equals("2")){	//下半部分上下分
    				jsa = new JSONArray();
    				JSONObject quarterJso = new JSONObject();
    				quarterJso.put("is_levels", 0);
    				quarterJso.put("item_id", "");
    				quarterJso.put("url", "");
    				quarterJso.put("type", "up");
    				quarterJso.put("pic", "");
    				String whole_down_up_up = request.getParameter("whole_down_up_up");
    				String whole_down_up_left = request.getParameter("whole_down_up_left");
    				String whole_down_down_up = request.getParameter("whole_down_down_up");
    				String whole_down_down_left = request.getParameter("whole_down_down_left");
    				if(whole_down_up_up != null && whole_down_up_up.equals("2")){	//下半部分上半部分上下分
    					JSONArray eighthJsa = new JSONArray();
    					eighthJsa = getEighthJsa("wholeDownUpUpitemtid","wholeDownUpUpimg","wholeDownUpUpclickUrl","wholeDownUpDownitemtid","wholeDownUpDownimg","wholeDownUpDownclickUrl","up",request);   					
    					quarterJso.put("eighth", eighthJsa);
    				}else if(whole_down_up_left != null && whole_down_up_left.equals("2")){	//下半部分上半部分左右分
    					JSONArray eighthJsa = new JSONArray();
    					eighthJsa = getEighthJsa("wholeDownUpLeftitemtid","wholeDownUpLeftimg","wholeDownUpLeftclickUrl","wholeDownUpRightitemtid","wholeDownUpRightimg","wholeDownUpRightclickUrl","left",request);   					
    					quarterJso.put("eighth", eighthJsa);
    				}else{//下半部分上半部分不分
    					String wholeDownUpitemtid = request.getParameter("wholeDownUpitemtid");
            			String wholeDownUpimg = request.getParameter("wholeDownUpimg");
            			String wholeDownUpclickUrl = request.getParameter("wholeDownUpclickUrl");
            			quarterJso.put("is_levels", 1);
            			quarterJso.put("item_id", wholeDownUpitemtid);
            			quarterJso.put("url", wholeDownUpclickUrl);
            			quarterJso.put("type", "up");
            			quarterJso.put("pic", wholeDownUpimg);
    				}
    				jsa.put(0,quarterJso);
    				
    				if(whole_down_down_up != null && whole_down_down_up.equals("2")){	//下半部分下半部分上下分
    					JSONArray eighthJsa = new JSONArray();
    					eighthJsa = getEighthJsa("wholeDownDownUpitemtid","wholeDownDownUpimg","wholeDownDownUpclickUrl","wholeDownDownDownitemtid","wholeDownDownDownimg","wholeDownDownDownclickUrl","up",request);   					
    					quarterJso.put("eighth", eighthJsa);
    				}else if(whole_down_down_left != null && whole_down_down_left.equals("2")){	//下半部分下半部分左右分
    					JSONArray eighthJsa = new JSONArray();
    					eighthJsa = getEighthJsa("wholeDownDownLeftitemtid","wholeDownDownLeftimg","wholeDownDownLeftclickUrl","wholeDownDownRightitemtid","wholeDownDownRightimg","wholeDownDownRightclickUrl","left",request);   					
    					quarterJso.put("eighth", eighthJsa);
    				}else{//下半部分下半部分不分
    					String wholeDownDownitemtid = request.getParameter("wholeDownDownitemtid");
            			String wholeDownDownimg = request.getParameter("wholeDownDownimg");
            			String wholeDownDownclickUrl = request.getParameter("wholeDownDownclickUrl");
            			
            			quarterJso.put("is_levels", 1);
            			quarterJso.put("item_id", wholeDownDownitemtid);
            			quarterJso.put("url", wholeDownDownclickUrl);
            			quarterJso.put("type", "up");
            			quarterJso.put("pic", wholeDownDownimg);
    				}
    				jsa.put(1,quarterJso);
    			}else if(whole_down_left != null && whole_down_left.equals("2")){	//下半部分左右分
    				jsa = new JSONArray();
    				JSONObject quarterJso = new JSONObject();
    				quarterJso.put("is_levels", 0);
    				quarterJso.put("item_id", "");
    				quarterJso.put("url", "");
    				quarterJso.put("type", "left");
    				quarterJso.put("pic", "");
    				String whole_down_left_up = request.getParameter("whole_down_left_up");
    				String whole_down_left_left = request.getParameter("whole_down_left_left");
    				String whole_down_right_up = request.getParameter("whole_down_right_up");
    				String whole_down_right_left = request.getParameter("whole_down_right_left");
    				if(whole_down_left_up != null && whole_down_left_up.equals("2")){	//下半部分左半部分上下分
    					JSONArray eighthJsa = new JSONArray();
    					eighthJsa = getEighthJsa("wholeDownLeftUpitemtid","wholeDownLeftUpimg","wholeDownLeftUpclickUrl","wholeDownLeftDownitemtid","wholeDownLeftDownimg","wholeDownLeftDownclickUrl","up",request);    					
    					quarterJso.put("eighth", eighthJsa);
    				}else if(whole_down_left_left != null && whole_down_left_left.equals("2")){	//下半部分左半部分左右分
    					JSONArray eighthJsa = new JSONArray();
    					eighthJsa = getEighthJsa("wholeDownLeftLeftitemtid","wholeDownLeftLeftimg","wholeDownLeftLeftclickUrl","wholeDownLeftRightitemtid","wholeDownLeftRightimg","wholeDownLeftRightclickUrl","left",request);  					
    					quarterJso.put("eighth", eighthJsa);
    				}else{//下半部分左半部分不分
    					String wholeDownLeftitemtid = request.getParameter("wholeDownLeftitemtid");
            			String wholeDownLeftimg = request.getParameter("wholeDownLeftimg");
            			String wholeDownLeftclickUrl = request.getParameter("wholeDownLeftclickUrl");
    					quarterJso.put("is_levels", 1);
    					quarterJso.put("item_id", wholeDownLeftitemtid);
    					quarterJso.put("url", wholeDownLeftclickUrl);
    					quarterJso.put("type", "left");
    					quarterJso.put("pic", wholeDownLeftimg);
    				}
    				jsa.put(0,quarterJso);
    				quarterJso = new JSONObject();
    				quarterJso.put("is_levels", 0);
    				quarterJso.put("item_id", "");
    				quarterJso.put("url", "");
    				quarterJso.put("type", "left");
    				quarterJso.put("pic", "");
    				if(whole_down_right_up != null && whole_down_right_up.equals("2")){	//下半部分右半部分上下分
    					JSONArray eighthJsa = new JSONArray();
    					eighthJsa = getEighthJsa("wholeDownRightUpitemtid","wholeDownRightUpimg","wholeDownRightUpclickUrl","wholeDownRightDownitemtid","wholeDownRightDownimg","wholeDownRightDownclickUrl","up",request);   					
    					quarterJso.put("eighth", eighthJsa);
    				}else if(whole_down_right_left != null && whole_down_right_left.equals("2")){	//下半部分右半部分左右分
    					JSONArray eighthJsa = new JSONArray();
    					eighthJsa = getEighthJsa("wholeDownRightLeftitemtid","wholeDownRightLeftimg","wholeDownRightLeftclickUrl","wholeDownRightRightitemtid","wholeDownRightRightimg","wholeDownRightRightclickUrl","left",request);   					
    					quarterJso.put("eighth", eighthJsa);
    				}else{//下半部分右半部分不分
    					String wholeDownRightitemtid = request.getParameter("wholeDownRightitemtid");
            			String wholeDownRightimg = request.getParameter("wholeDownRightimg");
            			String wholeDownRightclickUrl = request.getParameter("wholeDownRightclickUrl");
    					quarterJso.put("is_levels", 1);
    					quarterJso.put("item_id", wholeDownRightitemtid);
    					quarterJso.put("url", wholeDownRightclickUrl);
    					quarterJso.put("type", "left");
    					quarterJso.put("pic", wholeDownRightimg);
    				}
    				jsa.put(1,quarterJso);
    				halfJso.put("quarter", jsa);
    				halfJso.put("is_levels", 0);
    				halfJso.put("item_id", "");
    				halfJso.put("url", "");
    				halfJso.put("type", "up");
    				halfJso.put("pic", "");
    			}else{	//下半部分不分
    				String wholeDownitemtid = request.getParameter("wholeDownitemtid");
        			String wholeDownimg = request.getParameter("wholeDownimg");
        			String wholeDownclickUrl = request.getParameter("wholeDownclickUrl");
    				halfJso.put("is_levels", 1);
    				halfJso.put("item_id", wholeDownitemtid);
    				halfJso.put("url", wholeDownclickUrl);
    				halfJso.put("type", "up");
    				halfJso.put("pic", wholeDownimg);
    			}
    			halfJsa.put(1,halfJso);
    			jso.put("half", halfJsa);
    		}	
    		if(whole_left != null && whole_left.equals("2")){	//左右分
    			halfJso.put("is_levels", 0);
    			halfJso.put("item_id", "");
    			halfJso.put("url", "");
    			halfJso.put("type", "left");
    			halfJso.put("pic", "");
				
    			JSONArray jsa = new JSONArray();
				
    			if(whole_left_up != null && whole_left_up.equals("2")){	//左半部分上下分
    				JSONObject quarterJso = new JSONObject();
    				quarterJso.put("is_levels", 0);
    				quarterJso.put("item_id", "");
    				quarterJso.put("url", "");
    				quarterJso.put("type", "up");
    				quarterJso.put("pic", "");
    				String whole_left_up_up = request.getParameter("whole_left_up_up");
    				String whole_left_up_left = request.getParameter("whole_left_up_left");
    				String whole_left_down_up = request.getParameter("whole_left_down_up");
    				String whole_left_down_left = request.getParameter("whole_left_down_left");
    				if(whole_left_up_up != null && whole_left_up_up.equals("2")){	//左半部分上半部分上下分
    					JSONArray eighthJsa = new JSONArray();
    					eighthJsa = getEighthJsa("wholeLeftUpUpitemtid","wholeLeftUpUpimg","wholeLeftUpUpclickUrl","wholeLeftUpDownitemtid","wholeLeftUpDownimg","wholeLeftUpDownclickUrl","up",request);
    					
    					quarterJso.put("eighth", eighthJsa);
    				}else if(whole_left_up_left != null && whole_left_up_left.equals("2")){	//左半部分上半部分左右分
    					JSONArray eighthJsa = new JSONArray();
    					eighthJsa = getEighthJsa("wholeLeftUpLeftitemtid","wholeLeftUpLeftimg","wholeLeftUpLeftclickUrl","wholeLeftUpRightitemtid","wholeLeftUpRightimg","wholeLeftUpRightclickUrl","left",request);
    					
    					quarterJso.put("eighth", eighthJsa);
    				}else{	////左半部分上半部分不分
    					String wholeLeftUpitemtid = request.getParameter("wholeLeftUpitemtid");
            			String wholeLeftUpimg = request.getParameter("wholeLeftUpimg");
            			String wholeLeftUpclickUrl = request.getParameter("wholeLeftUpclickUrl");
    					quarterJso.put("is_levels", 1);
    					quarterJso.put("item_id", wholeLeftUpitemtid);
    					quarterJso.put("url", wholeLeftUpclickUrl);
    					quarterJso.put("type", "up");
    					quarterJso.put("pic", wholeLeftUpimg);
    				}
    				jsa.put(0,quarterJso);
    				quarterJso = new JSONObject();
    				quarterJso.put("is_levels", 0);
    				quarterJso.put("item_id", "");
    				quarterJso.put("url", "");
    				quarterJso.put("type", "up");
    				quarterJso.put("pic", "");
    				if(whole_left_down_up != null && whole_left_down_up.equals("2")){	//左半部分下半部分上下分
    					JSONArray eighthJsa = new JSONArray();
    					eighthJsa = getEighthJsa("wholeLeftDownUpitemtid","wholeLeftDownUpimg","wholeLeftDownUpclickUrl","wholeLeftDownDownitemtid","wholeLeftDownDownimg","wholeLeftDownDownclickUrl","up",request);
    					
    					quarterJso.put("eighth", eighthJsa);
    				}else if(whole_left_down_left != null && whole_left_down_left.equals("2")){	//左半部分下半部分左右分
    					JSONArray eighthJsa = new JSONArray();
    					eighthJsa = getEighthJsa("wholeLeftDownLeftitemtid","wholeLeftDownLeftimg","wholeLeftDownLeftclickUrl","wholeLeftDownRightitemtid","wholeLeftDownRightimg","wholeLeftDownRightclickUrl","left",request);
    					
    					quarterJso.put("eighth", eighthJsa);
    				}else{	//左半部分下半部分不分
    					String wholeLeftDownitemtid = request.getParameter("wholeLeftDownitemtid");
            			String wholeLeftDownimg = request.getParameter("wholeLeftDownimg");
            			String wholeLeftDownclickUrl = request.getParameter("wholeLeftDownclickUrl");
    					quarterJso.put("is_levels", 1);
    					quarterJso.put("item_id", wholeLeftDownitemtid);
    					quarterJso.put("url", wholeLeftDownclickUrl);
    					quarterJso.put("type", "up");
    					quarterJso.put("pic", wholeLeftDownimg);
    				}
    				jsa.put(1,quarterJso);
    				halfJso.put("quarter", jsa);
    				halfJsa.put(0,halfJso)	;
    			}else if(whole_left_left != null && whole_left_left.equals("2")){	//左半部分左右分
    				JSONObject quarterJso = new JSONObject();
    				quarterJso.put("is_levels", 0);
    				quarterJso.put("item_id", "");
    				quarterJso.put("url", "");
    				quarterJso.put("type", "left");
    				quarterJso.put("pic", "");
    				String whole_left_left_up = request.getParameter("whole_left_up_up");
    				String whole_left_left_left = request.getParameter("whole_left_up_left");
    				String whole_left_right_up = request.getParameter("whole_left_down_up");
    				String whole_left_right_left = request.getParameter("whole_left_down_left");
    				if(whole_left_left_up != null && whole_left_left_up.equals("2")){	//左半部分左半部分上下分
    					JSONArray eighthJsa = new JSONArray();
    					eighthJsa = getEighthJsa("wholeLeftLeftUpitemtid","wholeLeftLeftUpimg","wholeLeftLeftUpclickUrl","wholeLeftLeftDownitemtid","wholeLeftLeftDownimg","wholeLeftLeftDownclickUrl","up",request);
    					
    					quarterJso.put("eighth", eighthJsa);
    				}else if(whole_left_left_left != null && whole_left_left_left.equals("2")){//左半部分左半部分左右分    					
    					JSONArray eighthJsa = new JSONArray();
    					eighthJsa = getEighthJsa("wholeLeftLeftLeftitemtid","wholeLeftLeftLeftimg","wholeLeftLeftLeftclickUrl","wholeLeftLeftRightitemtid","wholeLeftLeftRightimg","wholeLeftLeftRightclickUrl","left",request);
    					
    					quarterJso.put("eighth", eighthJsa);
    				}else{//左半部分左半部分不分
    					String wholeLeftLeftitemtid = request.getParameter("wholeLeftLeftitemtid");
            			String wholeLefLeftimg = request.getParameter("wholeLefLeftimg");
            			String wholeLeftLeftclickUrl = request.getParameter("wholeLeftLeftclickUrl");
    					quarterJso.put("is_levels", 1);
    					quarterJso.put("item_id", wholeLeftLeftitemtid);
    					quarterJso.put("url", wholeLeftLeftclickUrl);
    					quarterJso.put("type", "up");
    					quarterJso.put("pic", wholeLefLeftimg);
    				}
    				jsa.put(0,quarterJso);
    				if(whole_left_right_up != null && whole_left_right_up.equals("2")){	//左半部分右半部分上下分    					
    					JSONArray eighthJsa = new JSONArray();
    					eighthJsa = getEighthJsa("wholeLeftRightUpitemtid","wholeLeftRightUpimg","wholeLeftRightUpclickUrl","wholeLeftRightDownitemtid","wholeLeftRightDownimg","wholeLeftRightDownclickUrl","up",request);
    					
    					quarterJso.put("eighth", eighthJsa);
    				}else if(whole_left_right_left != null && whole_left_right_left.equals("2")){//左半部分右半部分左右分
    					JSONArray eighthJsa = new JSONArray();
    					eighthJsa = getEighthJsa("wholeLeftRightLeftitemtid","wholeLeftRightLeftimg","wholeLeftRightLeftclickUrl","wholeLeftRightRightitemtid","wholeLeftRightRightimg","wholeLeftRightRightclickUrl","left",request);
    					
    					quarterJso.put("eighth", eighthJsa);
    				}else{//左半部分右半部分不分
    					String wholeLeftRightitemtid = request.getParameter("wholeLeftRightitemtid");
            			String wholeLeftRightimg = request.getParameter("wholeLeftRightimg");
            			String wholeLeftRightclickUrl = request.getParameter("wholeLeftRightclickUrl");
            			quarterJso.put("is_levels", 1);
            			quarterJso.put("item_id", wholeLeftRightitemtid);
            			quarterJso.put("url", wholeLeftRightclickUrl);
            			quarterJso.put("type", "left");
            			quarterJso.put("pic", wholeLeftRightimg);
    				}
    				jsa.put(1,quarterJso);
    				halfJso.put("quarter", jsa);
    				halfJsa.put(0,halfJso)	;
    			}else{//左半部分不分
    				String wholeLeftitemtid = request.getParameter("wholeLeftitemtid");
        			String wholeLeftimg = request.getParameter("wholeLeftimg");
        			String wholeLeftclickUrl = request.getParameter("wholeLeftclickUrl");
    				halfJso.put("is_levels", 1);
    				halfJso.put("item_id", wholeLeftitemtid);
    				halfJso.put("url", wholeLeftclickUrl);
    				halfJso.put("type", "left");
    				halfJso.put("pic", wholeLeftimg);	
    			}
    			halfJsa.put(0,halfJso);
    			halfJso = new JSONObject();
    			if(whole_right_up != null && whole_right_up.equals("2")){	//右半部分上下分
    				jsa = new JSONArray();
    				JSONObject quarterJso = new JSONObject();
    				quarterJso.put("is_levels", 0);
    				quarterJso.put("item_id", "");
    				quarterJso.put("url", "");
    				quarterJso.put("type", "up");
    				quarterJso.put("pic", "");
    				String whole_right_up_up = request.getParameter("whole_right_up_up");
    				String whole_right_up_left = request.getParameter("whole_right_up_left");
    				String whole_right_down_up = request.getParameter("whole_right_down_up");
    				String whole_right_down_left = request.getParameter("whole_right_down_left");
    				if(whole_right_up_up != null && whole_right_up_up.equals("2")){	//右半部分上半部分上下分   					
    					JSONArray eighthJsa = new JSONArray();
    					eighthJsa = getEighthJsa("wholeRightUpUpitemtid","wholeRightUpUpimg","wholeRightUpUpclickUrl","wholeRightUpDownitemtid","wholeRightUpDownimg","wholeRightUpDownclickUrl","up",request);
    					
    					quarterJso.put("eighth", eighthJsa);
    				}else if(whole_right_up_left != null && whole_right_up_left.equals("2")){	//右半部分上半部分左右分    					
    					JSONArray eighthJsa = new JSONArray();
    					eighthJsa = getEighthJsa("wholeRightUpLeftitemtid","wholeRightUpLeftimg","wholeRightUpLeftclickUrl","wholeRightUpRightitemtid","wholeRightUpRightimg","wholeRightUpRightclickUrl","left",request);
    					
    					quarterJso.put("eighth", eighthJsa);
    				}else{	//右半部分上半部分不分
    					String wholeRightUpitemtid = request.getParameter("wholeRightUpitemtid");
            			String wholeRightUpimg = request.getParameter("wholeRightUpimg");
            			String wholeRightUpclickUrl = request.getParameter("wholeRightUpclickUrl");
            			quarterJso.put("is_levels", 1);
            			quarterJso.put("item_id", wholeRightUpitemtid);
            			quarterJso.put("url", wholeRightUpclickUrl);
            			quarterJso.put("type", "left");
            			quarterJso.put("pic", wholeRightUpimg);
    				}
    				jsa.put(0,quarterJso);
    				if(whole_right_down_up != null && whole_right_down_up.equals("2")){		//右半部分下半部分上下分    					
    					JSONArray eighthJsa = new JSONArray();
    					eighthJsa = getEighthJsa("wholeRightDownUpitemtid","wholeRightDownUpimg","wholeRightDownUpclickUrl","wholeRightDownDownitemtid","wholeRightDownDownimg","wholeRightDownDownclickUrl","up",request);
    					
    					quarterJso.put("eighth", eighthJsa);
    				}else if(whole_right_down_left != null && whole_right_down_left.equals("2")){	//右半部分下半部分左右分    					
    					JSONArray eighthJsa = new JSONArray();
    					eighthJsa = getEighthJsa("wholeRightDownLeftitemtid","wholeRightDownLeftimg","wholeRightDownLeftclickUrl","wholeRightDownRightitemtid","wholeRightDownRightimg","wholeRightDownRightclickUrl","left",request);
    					
    					quarterJso.put("eighth", eighthJsa);
    				}else{	//右半部分下半部分不分
    					String wholeRightDownitemtid = request.getParameter("wholeRightDownitemtid");
            			String wholeRightDownimg = request.getParameter("wholeRightDownimg");
            			String wholeRightDownclickUrl = request.getParameter("wholeRightDownclickUrl");
    					quarterJso.put("is_levels", 1);
    					quarterJso.put("item_id", wholeRightDownitemtid);
    					quarterJso.put("url", wholeRightDownclickUrl);
    					quarterJso.put("type", "left");
    					quarterJso.put("pic", wholeRightDownimg);
    				}
    				jsa.put(1,quarterJso);
    				halfJso.put("quarter", jsa);
    				halfJso.put("is_levels", 0);
    				halfJso.put("item_id", "");
    				halfJso.put("url", "");
    				halfJso.put("type", "left");
    				halfJso.put("pic", "");
    			}else if(whole_right_left != null && whole_right_left.equals("2")){	//右半部分左右分
    				JSONObject quarterJso = new JSONObject();
    				quarterJso.put("is_levels", 0);
    				quarterJso.put("item_id", "");
    				quarterJso.put("url", "");
    				quarterJso.put("type", "up");
    				quarterJso.put("pic", "");
    				String whole_right_left_up = request.getParameter("whole_right_left_up");
    				String whole_right_left_left = request.getParameter("whole_right_left_left");
    				String whole_right_right_up = request.getParameter("whole_right_right_up");
    				String whole_right_right_left = request.getParameter("whole_right_right_left");
    				if(whole_right_left_up != null && whole_right_left_up.equals("2")){//右半部分左半部分上下分    					
    					JSONArray eighthJsa = new JSONArray();
    					eighthJsa = getEighthJsa("wholeRightLeftUpitemtid","wholeRightLeftUpimg","wholeRightLeftUpclickUrl","wholeRightLeftDownitemtid","wholeRightLeftDownimg","wholeRightLeftDownclickUrl","up",request);
    					
    					quarterJso.put("eighth", eighthJsa);
    				}else if(whole_right_left_left != null && whole_right_left_left.equals("2")){	//右半部分左半部分左右分   					
    					JSONArray eighthJsa = new JSONArray();
    					eighthJsa = getEighthJsa("wholeRightLeftLeftitemtid","wholeRightLeftLeftimg","wholeRightLeftLeftclickUrl","wholeRightLeftRightitemtid","wholeRightLeftRightimg","wholeRightLeftRightclickUrl","left",request);
    					
    					quarterJso.put("eighth", eighthJsa);
    				}else{//右半部分左半部分不分
    					String wholeRightLeftitemtid = request.getParameter("wholeRightLeftitemtid");
            			String wholeRightLeftimg = request.getParameter("wholeRightLeftimg");
            			String wholeRightLeftclickUrl = request.getParameter("wholeRightLeftclickUrl");
            			
    					quarterJso.put("is_levels", 1);
    					quarterJso.put("item_id", wholeRightLeftitemtid);
    					quarterJso.put("url", wholeRightLeftclickUrl);
    					quarterJso.put("type", "up");
    					quarterJso.put("pic", wholeRightLeftimg);
    				}
    				jsa.put(0,quarterJso);
    				quarterJso = new JSONObject();
    				quarterJso.put("is_levels", 0);
    				quarterJso.put("item_id", "");
    				quarterJso.put("url", "");
    				quarterJso.put("type", "up");
    				quarterJso.put("pic", "");
    				if(whole_right_right_up != null && whole_right_right_up.equals("2")){//右半部分右半部分上下分
    					
    					JSONArray eighthJsa = new JSONArray();
    					eighthJsa = getEighthJsa("wholeRightRightUpitemtid","wholeRightRightUpimg","wholeRightRightUpclickUrl","wholeRightRightDownitemtid","wholeRightRightDownimg","wholeRightRightDownclickUrl","up",request);
    					
    					quarterJso.put("eighth", eighthJsa);
    				}else if(whole_right_right_left != null && whole_right_right_left.equals("2")){//右半部分右半部分左右分
    					
    					JSONArray eighthJsa = new JSONArray();
    					eighthJsa = getEighthJsa("wholeRightRightLeftitemtid","wholeRightRightLeftimg","wholeRightRightLeftclickUrl","wholeRightRightRightitemtid","wholeRightRightRightimg","wholeRightRightRightclickUrl","left",request);
    					
    					quarterJso.put("eighth", eighthJsa);
    				}else{//右半部分右半部分不分
    					String wholeRightRightitemtid = request.getParameter("wholeRightRightitemtid");
            			String wholeRightRightimg = request.getParameter("wholeRightRightimg");
            			String wholeRightRightclickUrl = request.getParameter("wholeRightRightclickUrl");
            			
    					quarterJso.put("is_levels", 1);
    					quarterJso.put("item_id", wholeRightRightitemtid);
    					quarterJso.put("url", wholeRightRightclickUrl);
    					quarterJso.put("type", "left");
    					quarterJso.put("pic", wholeRightRightimg);
    				}
    				jsa.put(1,quarterJso);
    				halfJso.put("quarter", jsa);
    				halfJso.put("is_levels", 0);
    				halfJso.put("item_id", "");
    				halfJso.put("url", "");
    				halfJso.put("type", "left");
    				halfJso.put("pic", "");
    			}else{//右半部分不分
    				String wholeRightitemtid = request.getParameter("wholeRightitemtid");
        			String wholeRightimg = request.getParameter("wholeRightimg");
        			String wholeRightclickUrl = request.getParameter("wholeRightclickUrl");
    				halfJso.put("is_levels", 1);
    				halfJso.put("item_id", wholeRightitemtid);
    				halfJso.put("url", wholeRightclickUrl);
    				halfJso.put("type", "left");
    				halfJso.put("pic", wholeRightimg);
    			}
    			halfJsa.put(1,halfJso);
    			jso.put("half", halfJsa);
    		}
    	}
    	System.out.println(jso.toString());
    	try {
    		String title = request.getParameter("title");
    		String wholeContent = request.getParameter("wholeContent");
    		if(title != null && !"".equals(title)){
    			title = new String(title.getBytes("iso-8859-1"), "utf-8");
    		}
    		if(wholeContent == null){
    			wholeContent = "";
    		}
    		flag = AdminMMixDAO.MMixConvert(jso.toString(), tagId,wholeContent,title,"ffffff");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
    	
    	if(flag){
    		response.getWriter().print("<script>alert('success！');location.href='tag_item_manager?actionmethod=showMixItemPage&tagid="+tagId+"&keyword="+tagName+"';;</script>");
    		return;   		
    	}else{
    		response.getWriter().print("<script>alert('fail！');history.back();</script>");
			return;
    	}
    }
    
    public void getPageItemList(HttpServletRequest request, HttpServletResponse response) throws Exception{
    	String p = request.getParameter("page");
    	String tagid = request.getParameter("tagid"); 
    	String total = request.getParameter("totalPage");
    	List<TeJiaGoodsBean> list = new ArrayList<TeJiaGoodsBean>();
    	int pInt = 1;
    	try{
    		pInt = Integer.parseInt(p);
    	}catch (Exception e) 
		{
    		pInt = 1;
		}
    	if(tagid != null && !"".equals(tagid)){
    		list = Admin_Tag_Item_DAO.getTagListItem(tagid, pInt);
    		total = Admin_Tag_Item_DAO.getTagListItemPage(tagid)+"";
    	}else{
    		list = Admin_Tag_Item_DAO.getTagItemByTagID(tagid,pInt);
    		total = Admin_Tag_Item_DAO.getTagItemByTagIDPage(tagid)+"";
    		
    	}
    	if(list == null || list.size() == 0){
    		response.getWriter().print("");
    		return;
    	}
    	JSONObject jso = new JSONObject();
    	jso.put("totalPage", total);
    	JSONArray jsa = new JSONArray();
    	int i = 0;
    	for(TeJiaGoodsBean bean : list){
    		jsa.put(i,bean.toJson1());
    		i++;
    	}
    	if(tagid == null || "null".equals(tagid)){
    		tagid = "";
    	}
    	jso.put("tagid", tagid);
    	jso.put("page", pInt);
    	jso.put("itemList", jsa);
    	response.getWriter().print(jso.toString());
			
    }
    public void addMixStyleItemPage(HttpServletRequest request, HttpServletResponse response) throws Exception{
    	String data = request.getParameter("data");
		String tagId = request.getParameter("tagid");
		String tilte = request.getParameter("title");
		String content = request.getParameter("content");
		String id = request.getParameter("id");
		String bgColor = request.getParameter("bg_color");
		if(bgColor == null || "".equals(bgColor)){
			bgColor = "";
		}
		if(id != null && !"".equals(id)){
			AdminMMixDAO.delMMixBean(id);
		}
		if(tagId == null || "".equals(tagId)){
			tagId = mixTagId;
		}
//		String keyword = request.getParameter("keyword");
		boolean flag = AdminMMixDAO.MMixConvert(data, tagId,content,tilte,bgColor);
		if(flag){
    		response.getWriter().print(getRespJSONString("0"));
    	}else{
    		response.getWriter().print(getRespJSONString("1"));
    	}
    
    }
    
    public void updateMixStyleItemPage(HttpServletRequest request, HttpServletResponse response) throws Exception{
    	String id = request.getParameter("id");
    	MMixBean bean = AdminMMixDAO.getItemJson(id);
    	if(bean == null){
    		response.getWriter().print("<script>alert('item is not EXIT！');history.back();</script>");
			return;
    	}   	
    	String tagid = request.getParameter("tagid");
        String keyword = request.getParameter("keyword");
        
        if(keyword != null && ! "".equals(keyword))
        {
        	keyword = new String(keyword.getBytes("iso-8859-1"), "utf-8");
        }
        
        if(tagid == null)
        {
        	tagid = "";
        }
    	int total = Admin_Tag_Item_DAO.getTagItemByTagIDPage(tagid);
    	List<TeJiaGoodsBean> list = new ArrayList<TeJiaGoodsBean>();
    	if(!"".equals(tagid)) 
    	{
    		list = Admin_Tag_Item_DAO.getTagItemByTagID(tagid,1);
    	}
//    	System.out.println("conten:"+content);
//    	System.out.println(list.size());
    	
    	List<MixPageStyleBean> styleList = AdminMMixDAO.getMMixPageStyle(1);
//    	int total = AdminMMixDAO.getMMixPageStylePage();
    	request.setAttribute("styleList", styleList);
    	request.setAttribute("keyword", keyword);
    	request.setAttribute("list", list);
    	request.setAttribute("page", 1);
    	request.setAttribute("totalpage", total);
    	request.setAttribute("ItemList", list);
		request.setAttribute("tagid", tagid);
		request.setAttribute("keyword", keyword);
    	
    	
    	String content = bean.getContent();
    	String jsonData = bean.getJsonData();
    	request.setAttribute("content", content);
		request.setAttribute("jsonData", jsonData);
		request.setAttribute("id", id);
		request.getRequestDispatcher("/admin/tag_manager/tag_item_mix_choose_add.jsp").forward(request, response);
		return;
    }
    
    public void addMixStylePage(HttpServletRequest request, HttpServletResponse response) throws Exception{
    	String content = request.getParameter("content");
    	String biaoshi = request.getParameter("biaoshi");
    	String data = request.getParameter("data");
    	boolean isExit = AdminMMixDAO.isExistStyle(data);
    	if(isExit){
    		response.getWriter().print(getRespJSONString("2"));
    		return;
    	}
    	content = content.replaceAll("\\.5.0", ".5");
    	boolean flag = AdminMMixDAO.addMixStylePage(content, biaoshi,data);
    	if(flag){
    		response.getWriter().print(getRespJSONString("0"));
    	}else{
    		response.getWriter().print(getRespJSONString("1"));
    	}
    	
    }
    
    public void delMixItem(HttpServletRequest request, HttpServletResponse response) throws Exception{
    	String id = request.getParameter("id");
    	boolean flag = AdminMMixDAO.delMMixBean(id);
    	if(flag){
    		response.getWriter().print(getRespJSONString("0"));
    	}else{
    		response.getWriter().print(getRespJSONString("1"));
    	}
    	
    }
    
    public void showMixStylePage(HttpServletRequest request, HttpServletResponse response) throws Exception{
    	String tagId = request.getParameter("tagid");
    	String keyword = request.getParameter("keyword");
    	if(keyword != null && !"".equals(keyword)) 
    	{
    		keyword = new String(keyword.getBytes("iso-8859-1"), "utf-8");
    	}
    	String p = request.getParameter("page");
    	request.setAttribute("tagid", tagId);
    	int page = 1;
    	try {
    		page = Integer.parseInt(p);
		} catch (Exception e) {
			page = 1;
		}
    	List<MixPageStyleBean> list = AdminMMixDAO.getMMixPageStyle(page);
    	int total = AdminMMixDAO.getMMixPageStylePage();
    	request.setAttribute("keyword", keyword);
    	request.setAttribute("list", list);
    	request.setAttribute("totalpage", total);
		request.setAttribute("page", page);
    	request.getRequestDispatcher("/admin/tag_manager/tag_item_mix_page_style.jsp").forward(request, response);
		return;
    	
    }
    
    public void showMixItemPage(HttpServletRequest request, HttpServletResponse response) throws Exception{
    	String tagId = request.getParameter("tagid");
    	String keyword = request.getParameter("keyword");
    	if(keyword != null && !"".equals(keyword)) 
    	{
    		keyword = new String(keyword.getBytes("iso-8859-1"), "utf-8");
    	}
    	String p = request.getParameter("page");
    	int page = 1;
    	try {
    		page = Integer.parseInt(p);
		} catch (Exception e) {
			page = 1;
		}
    	if(tagId == null || "".equals(tagId)){
    		tagId = mixTagId;
    	}
    	List<MMixBean> list = AdminMMixDAO.getMMixBeanList(tagId,page);
    	int total = AdminMMixDAO.getMMixBeanListPage(tagId);
    	request.setAttribute("tagid", tagId);
    	request.setAttribute("keyword", keyword);
    	request.setAttribute("list", list);
    	request.setAttribute("totalpage", total);
		request.setAttribute("page", page);
    	request.getRequestDispatcher("/admin/tag_manager/tag_item_mix_page_list.jsp").forward(request, response);
		return;
    	
    }
    
    public void getItemImgsFromTaobao(HttpServletRequest request, HttpServletResponse response) throws Exception{
    	String id = request.getParameter("id");
    	String content = NetManager.getContent("http://fetch.b17.cn/itemimgs?itemid="+id);
    	
//    	List<String> imgs = TaobaoAPI_DAO.getItemImgFromTaobao(id);
    	List<String> imgs = new ArrayList<String>();
    	JSONArray jsa = new JSONArray();
    	
		try {
			JSONObject jso = new JSONObject(content);
			String status = jso.getString("status");
			if(status.equals("0")){
				String result = jso.getString("result");
				JSONArray imgsJsa = new JSONArray(result);
				for(int i = 0 ; i < imgsJsa.length() ; i ++){
					imgs.add(imgsJsa.getString(i).replaceAll("\"", ""));
//					System.out.println(imgsJsa.get(i));
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if(imgs != null && imgs.size() > 0){
    		for(String img : imgs){   			
    			jsa.put(img);
    		}
    	}
    	response.getWriter().print(jsa.toString());
    }
    
    public static JSONArray getEighthJsa(String itemid1,String img1,String clickUrl1,String itemid2,String img2,String clickUrl2,String position ,HttpServletRequest request) throws Exception{
    	String itemid1Par = request.getParameter(itemid1);
		String img1Par = request.getParameter(img1);
		String clickUrl1Par = request.getParameter(clickUrl1);
		
		String itemid2Par = request.getParameter(itemid2);
		String img2Par = request.getParameter(img2);
		String clickUrl2Par = request.getParameter(clickUrl2);
		JSONObject eighthJso = new JSONObject();
		JSONArray eighthJsa = new JSONArray();
		eighthJso.put("is_levels", 1);
		eighthJso.put("item_id", itemid1Par);
		eighthJso.put("url", clickUrl1Par);
		eighthJso.put("type", position);
		eighthJso.put("pic", img1Par);
		eighthJsa.put(0,eighthJso);
		
		eighthJso = new JSONObject();
		eighthJso.put("is_levels", 1);
		eighthJso.put("item_id", itemid2Par);
		eighthJso.put("url", clickUrl2Par);
		eighthJso.put("type", position);
		eighthJso.put("pic", img2Par);
		eighthJsa.put(1,eighthJso);
    	return eighthJsa;
    }
	/**
	 * 获取小图片
	 * */
	public static String getSmallImg(String pic_url) 
	{
		if(pic_url.indexOf("taobaocdn.com") < 0 && pic_url.indexOf("taobao.com") < 0  && pic_url.indexOf("alicdn.com") < 0)
		{
			return pic_url;
		}
		pic_url = pic_url == null ? "" : pic_url;
		
		String reg_1 = "png_.*?.jpg";
		String reg_2 = "jpg_.*?.jpg";
		
		Pattern p_1 = Pattern.compile(reg_1);
		Pattern p_2 = Pattern.compile(reg_2);
		
		Matcher m_1 = p_1.matcher(pic_url);
		if (m_1.find())
		{
			pic_url = pic_url.replaceAll(reg_1, "");
			if (!pic_url.equals("")) 
			{
				pic_url += "jpg_m.jpg";
			}
			return pic_url;
		}
		Matcher m_2 = p_2.matcher(pic_url);
		if (m_2.find())
		{
			pic_url = pic_url.replaceAll(reg_2, "");
			if (!pic_url.equals("")) 
			{
				pic_url += "jpg_m.jpg";
			}
			return pic_url;
		}
		pic_url = pic_url.replaceAll(".jpg", ".jpg_m.jpg");
		pic_url = pic_url.replaceAll(" ", "");
		return pic_url;
	}
    
    public static String getRespJSONString(String result) throws JSONException
    {
    	JSONObject respOBJ = new JSONObject();
		respOBJ.put("result",  result);
		return respOBJ.toString();
    }
    
    public static String getRespJSONStringWithParam(String result, HashMap<String, String> params) throws JSONException
    {
    	JSONObject respOBJ = new JSONObject();
		respOBJ.put("result",  result);
		if (params != null && params.size() > 0)
		{
			Set<Entry<String, String>> set = params.entrySet();
			for (Entry<String, String> entry : set)
			{
				respOBJ.put(entry.getKey(), entry.getValue());
			}
		}
		return respOBJ.toString();
    }
    
    public static String getRespJSONString(String result,List<TeJiaGoodsBean> list) throws JSONException
    {
    	JSONObject respOBJ = new JSONObject();
		respOBJ.put("result",  result);
		JSONObject jso = new JSONObject();
		for(TeJiaGoodsBean bean:list) 
		{
			jso.put("item_id", bean.getItem_id());
			jso.put("title", bean.getTitle());
			jso.put("pic_url", bean.getPic_url());
			jso.put("price_low", bean.getPrice_low());
			respOBJ.put("", jso);
		}
		return respOBJ.toString();
    }
    
    
    /**
     * 淘宝接口获取数据转化为TeJiaGoodsBean
     * @param itemList
     * @param parent
     * @return
     */
	public static TeJiaGoodsBean tbkitemtoTejiaGoods(TaobaokeItem item, String parent)
    {
		TeJiaGoodsBean bean = new TeJiaGoodsBean();
		
		bean.setItem_id(item.getNumIid() + "");
		bean.setKeyword(parent);
		bean.setClickURL(item.getClickUrl());
		bean.setPrice_high(item.getPrice());
		String promoPrice = TaobaoAPI_DAO.getPromoPrice(item.getNumIid()+""); 
		if(promoPrice == null) 
		{
			promoPrice = item.getPrice();   
		}
		bean.setPrice_low(promoPrice); 
		bean.setDiscount("" + Float.valueOf(promoPrice)/Float.valueOf(item.getPrice())*100);  
		bean.setPic_url(item.getPicUrl());
		bean.setCommission(item.getCommission());
		bean.setCommission_rate(item.getCommissionRate());
		bean.setTitle(item.getTitle());
		return bean;
	}
    
    /**
     * 统计的数据格式转换
     * @param content
     * @return
     * @throws JSONException 
     */
    public static String TongjiJsonConvert(String content)
    {
    	StringBuffer data = new StringBuffer();
    	JSONArray jsa;
		try {
			jsa = new JSONArray(content);
			if(jsa.length()>0) 
			{
				for(int i=0;i<jsa.length();i++) 
				{
					JSONObject jso = jsa.getJSONObject(i);
					data.append("['"+jso.getString("time")+"',  "+jso.getString("count")+"],");
				}
				data.deleteCharAt(data.length()-1);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
    	return data.toString();
    }
    
    public static List<TongjiBean> getListFromJson(String content) 
    {
    	List<TongjiBean> list = new ArrayList<TongjiBean>();
    	JSONArray jsa;
		try {
			jsa = new JSONArray(content);
			if(jsa.length()>0) 
			{
				for(int i=0;i<jsa.length();i++) 
				{
					JSONObject jso = jsa.getJSONObject(i);
					TongjiBean bean = new TongjiBean();
					bean.setTitle(jso.getString("time"));
					bean.setValue(jso.getString("count"));
					list.add(bean);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return list;
    }
    
    /**
     * 
     * @param pids
     * @param style
     * @return
     */
    public boolean isValidParam(String pids,String style) 
    {
		//1.验证style和pids的一致性
		boolean isreg = true;
		String []pidArr = pids.split(",");
		if("1".equals(style)) 
		{
			if(pidArr.length!=2) 
			{
				isreg = false;
			}
		} 
		else if("2".equals(style) || "3".equals(style) || "4".equals(style) || "5".equals(style)) 
		{
			if(pidArr.length!=3) 
			{
				isreg = false;
			}
		} 
		else if("6".equals(style)) 
		{
			if(pidArr.length!=4) 
			{
				isreg = false;
			}
		} 
		else 
		{
			isreg = false;
		}
		//2.验证pids必须是以逗号隔开的数字
		if(!pids.matches(reg)) 
		{
			isreg = false;
		}
		//3.验证pids中数字没有重复值
		if(!checkRepeat(pids.split(","))) 
		{
			isreg = false;
		}
		return isreg;
	}
    
    /**
     *  判断数组中是否有重复值
     * @param array
     * @return
     */
 	public boolean checkRepeat(String[] array) 
 	{
 		Set<String> set = new HashSet<String>();
 		for (String str : array) 
 		{
 			set.add(str);
 		}
 		if (set.size() != array.length) 
 		{
 			return false;// 有重复
 		} 
 		else 
 		{
 			return true;// 不重复
 		}
 	}
    
 	public static int getTotalPage(int size, int pageNum)
 	{
 		return (size % pageNum == 0) ? (int) (size / pageNum) : (int) (size / pageNum) + 1;
 	}
}
