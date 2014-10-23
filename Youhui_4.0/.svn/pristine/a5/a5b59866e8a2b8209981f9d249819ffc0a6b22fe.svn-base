package com.netting.action.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;

import com.netting.bean.AD_Bean;
import com.netting.bean.IPAD_Ad_Bean;
import com.netting.cache.dao.AdminAdCacheDAO;
import com.netting.conf.SysCache;
import com.netting.conf.SysConf;
import com.netting.dao.admin.Admin_AD_DAO;
import com.netting.dao.admin.Admin_OPT_Log_DAO;
import com.netting.dao.admin.Admin_Tag_DAO;
import com.netting.util.CodeUtil;
import com.netting.util.NetManager;
import com.netting.util.OuterCode;

/**
 * 广告模块管理
 * @author YAOJIANBO
 * @since 2013-10-24
 */
@WebServlet("/ad/ad_manager")
public class Admin_AD_Manager_Action extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 初始化日志
	 */
	public static Log logger = LogFactory.getLog( Admin_AD_Manager_Action.class );
	
	@Override
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
			if (methodName.equals("showUpdateADJSP"))
			{
				showUpdateADJSP(request, response);
			}
			else if (methodName.equals("updateAD"))
			{
				updateAD(request, response);
			}
			else if (methodName.equals("showAddADJSP"))
			{
				showAddADJSP(request, response);
			}
			else if (methodName.equals("addAD"))
			{
				addAD(request, response);
			}
			else if (methodName.equals("showADList"))
			{
				showADList(request, response);
			}
			else if (methodName.equals("delAD"))
			{
				delAD(request, response);
			}
			else if (methodName.equals("movePosition"))
			{
				movePosition(request, response);
			}
			else if (methodName.equals("urlConvert"))
			{
				urlConvert(request, response);
			}
			else if (methodName.equals("show_IPAD_AD_List"))
			{
				show_IPAD_AD_List(request, response);
			}
			else if (methodName.equals("del_IPAD_AD"))
			{
				del_IPAD_AD(request, response);
			}
			else if (methodName.equals("show_IPAD_AD_Update_JSP"))
			{
				show_IPAD_AD_Update_JSP(request, response);
			}
			else if (methodName.equals("update_IPAD_AD"))
			{
				update_IPAD_AD(request, response);
			}
			else if (methodName.equals("show_IPAD_AD_ADD_JSP"))
			{
				show_IPAD_AD_ADD_JSP(request, response);
			}
			else if (methodName.equals("add_IPAD_AD"))
			{
				add_IPAD_AD(request, response);
			}
			else if (methodName.equals("update_IPAD_AD_Rank"))
			{
				update_IPAD_AD_Rank(request, response);
			}
		}
		catch (Exception e)
		{
			response.getWriter().print("<script>alert('server process error！');history.back();</script>");
			return;
		}
		
		return;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		this.doGet(req, resp);
	}
	
	/**
	 * 访问编辑页面
	 * @throws Exception
	 */
	public void showUpdateADJSP(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String ad_id = request.getParameter("ad_id");
		String page = request.getParameter("page");
		String status = request.getParameter("status");
		String platform = request.getParameter("platform");
		
		AD_Bean bean = Admin_AD_DAO.getADBean(ad_id);
		
		request.setAttribute("ADBean", bean);
		request.setAttribute("page", page);
		request.setAttribute("status", status);
		request.setAttribute("platform", platform);
		request.setAttribute("ADPlatformMap", SysCache.ADPlatformMap);
		request.setAttribute("ActionTypeMap", SysCache.ActionTypeMap);
		request.setAttribute("XTYM_Map", SysCache.XTYM_Map);
		ArrayList<HashMap<String, String>> Tag_Type_Value_Map = Admin_Tag_DAO.getTagActionType_Value_Map("538");
		request.setAttribute("Tag_Type_Value_Map", Tag_Type_Value_Map);
		
		request.getRequestDispatcher("/admin/ad_manager/ad_update.jsp").forward(request, response);
		return;
	}
	
	/**
	 * 编辑广告提交
	 * @throws Exception
	 */
	public void updateAD(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String ad_id = request.getParameter("ad_id");
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String platform = request.getParameter("platform");
		String actionType = request.getParameter("action_type");
		String actionValue = request.getParameter("action_value");
		String start_time = request.getParameter("start_time");
		String end_time = request.getParameter("end_time");
		String img = request.getParameter("img");
		String img1 = request.getParameter("img1");
		String img2 = request.getParameter("img2");
		
		String type = "tag";
		
		try
		{
			start_time = String.valueOf(CodeUtil.getTimeMillis_2(start_time));
			end_time = String.valueOf(CodeUtil.getTimeMillis_2(end_time));
		}
		catch (Exception e)
		{
			// 开始日期和结束日期格式错误，返回：2
    		response.getWriter().print(getRespJSONString("2"));
    		return;
		}
		
		if(actionType.endsWith("tagStyleWeb") || actionType.equals("systemWeb")) 
		{
			type = "url";
			try 
			{
				actionValue = NetManager.convertToPageViewUrl(actionValue, title, description, "ad_" + ad_id);
				if (actionValue.indexOf(SysConf.hostUrl) < 0)
				{
					// 地址转换失败，操作错误，返回：1
		    		response.getWriter().print(getRespJSONString("1"));
		    		return;
				}
			} 
			catch (Exception e) 
			{
				// 地址转换失败，操作错误，返回：1
	    		response.getWriter().print(getRespJSONString("1"));
	    		return;
			}
		}
		
		AD_Bean bean = new AD_Bean();
		bean.setId(ad_id);
		bean.setTitle(title);
		bean.setDescription(description);
		bean.setPlatform(platform);
		bean.setContent(convertoJson(img, img1, img2, actionValue, description));
		bean.setUpdatetime(String.valueOf(System.currentTimeMillis()));
		bean.setActionType(actionType);
		bean.setActionValue(actionValue);
		bean.setStart_time(start_time);
		bean.setEnd_time(end_time);
		bean.setImg(img);
		bean.setType(type);
		
		boolean flag = Admin_AD_DAO.updateAD(bean);
		if (flag)
		{
			bean = Admin_AD_DAO.getADBeanNew(ad_id);
			AdminAdCacheDAO.delAd(bean);
			AdminAdCacheDAO.addAd(bean);
			Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "5", 
					"修改广告[<a target=\"_blank\" style=\"color: #1F92FF\" href=\"" + request.getContextPath() + "/ad/ad_manager?actionmethod=showUpdateADJSP&ad_id=" + ad_id + "\">" + title + "</a>]", 
					"3");
			// 更新成功，返回：0
    		response.getWriter().print(getRespJSONString("0"));
    		return;
		}
		else
		{
			// 数据库更新失败，返回：1
    		response.getWriter().print(getRespJSONString("1"));
    		return;
		}
		
	}
	
	/**
	 * 访问新增广告页面
	 * @throws Exception
	 */
	public void showAddADJSP(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String page = request.getParameter("page");
		String status = request.getParameter("status");
		String platform = request.getParameter("platform");
		
		request.setAttribute("page", page);
		request.setAttribute("status", status);
		request.setAttribute("platform", platform);
		request.setAttribute("ADPlatformMap", SysCache.ADPlatformMap);
		request.setAttribute("ActionTypeMap", SysCache.ActionTypeMap);
		request.setAttribute("XTYM_Map", SysCache.XTYM_Map);
		ArrayList<HashMap<String, String>> Tag_Type_Value_Map = Admin_Tag_DAO.getTagActionType_Value_Map("538");
		request.setAttribute("Tag_Type_Value_Map", Tag_Type_Value_Map);
		
		request.getRequestDispatcher("/admin/ad_manager/ad_add.jsp").forward(request, response);
		return;
	}
	
	/**
	 * 新增广告提交
	 * @throws Exception
	 */
	public void addAD(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String ad_id = Admin_AD_DAO.getADNextID();
		
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String platform = request.getParameter("platform");
		String actionType = request.getParameter("action_type");
		String actionValue = request.getParameter("action_value");
		String start_time = request.getParameter("start_time");
		String end_time = request.getParameter("end_time");
		String img = request.getParameter("img");
		String img1 = request.getParameter("img1");
		String img2 = request.getParameter("img2");
		
		String type = "tag";
		
		try
		{
			start_time = String.valueOf(CodeUtil.getTimeMillis_2(start_time));
			end_time = String.valueOf(CodeUtil.getTimeMillis_2(end_time));
		}
		catch (Exception e)
		{
			// 开始日期和结束日期格式错误，返回：2
    		response.getWriter().print(getRespJSONString("2"));
    		return;
		}
		
		if(actionType.endsWith("tagStyleWeb") || actionType.equals("systemWeb")) 
		{
			type = "url";
			try 
			{
				actionValue = NetManager.convertToPageViewUrl(actionValue, title, description, "ad_" + ad_id);
				if (actionValue.indexOf(SysConf.hostUrl)< 0 || (actionValue.replaceAll (SysConf.hostUrl,"")).length() >10)
				{
					// 地址转换失败，操作错误，返回：1
		    		response.getWriter().print(getRespJSONString("1"));
		    		return;
				}
			} 
			catch (Exception e) 
			{
				// 地址转换失败，操作错误，返回：1
	    		response.getWriter().print(getRespJSONString("1"));
	    		return;
			}
		}
		
		AD_Bean bean = new AD_Bean();
		bean.setId(ad_id);
		bean.setTitle(title);
		bean.setDescription(description);
		bean.setPlatform(platform);
		bean.setContent(convertoJson(img, img1, img2, actionValue, description));
		bean.setUpdatetime(String.valueOf(System.currentTimeMillis()));
		bean.setActionType(actionType);
		bean.setActionValue(actionValue);
		bean.setStart_time(start_time);
		bean.setEnd_time(end_time);
		bean.setImg(img);
		bean.setType(type);
		
		boolean flag = Admin_AD_DAO.addAD(bean);
		if (flag)
		{
			bean = Admin_AD_DAO.getADBeanNew(ad_id);
			AdminAdCacheDAO.addAd(bean);
			Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "5", 
					"新增广告[<a target=\"_blank\" style=\"color: #1F92FF\" href=\"" + request.getContextPath() + "/ad/ad_manager?actionmethod=showUpdateADJSP&ad_id=" + ad_id + "\">" + title + "</a>]", 
					"1");
			// 更新成功，返回：0
    		response.getWriter().print(getRespJSONString("0"));
    		return;
		}
		else
		{
			// 数据库更新失败，返回：3
    		response.getWriter().print(getRespJSONString("3"));
    		return;
		}
	}

    /**
     * 管理员访问广告列表页面
     */
    public void showADList(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String keyword = request.getParameter("keyword");
    	String page_str = request.getParameter("page");
    	String status_str = request.getParameter("status");
    	String platform = request.getParameter("platform");
    	
    	// 默认第一页
    	int page = 1;
    	if (page_str != null && !"".equals(page_str))
    	{
    		page = Integer.parseInt(page_str);
    		if (page < 0)
    		{
    			page = 1;
    		}
    	}
    	
    	int status = 4;
    	if (status_str != null && !"".equals(status_str))
    	{
    		status = Integer.parseInt(status_str);
    	}
    	if (platform == null)
    	{
    		platform = "quanbu";
    	}
    	ArrayList<AD_Bean> adList = Admin_AD_DAO.getADList(page, keyword, status, platform);
    	int totalPage = Admin_AD_DAO.getADListTotal(keyword, status, platform);
    	
    	request.setAttribute("ADList", adList);
    	request.setAttribute("platform", platform);
    	request.setAttribute("status", status);
		request.setAttribute("ADPlatformMap", SysCache.ADPlatformMap);
		request.setAttribute("ADStatusMap", SysCache.ADStatusMap);
		request.setAttribute("ActionTypeMap", SysCache.ActionTypeMap);
		request.setAttribute("page", page);
		request.setAttribute("totalpage", totalPage);
		
		request.getRequestDispatcher("/admin/ad_manager/ad_list.jsp").forward(request, response);
		return;
    }
    
    /**
     * 管理员访问IPAD广告列表页面
     */
    public void show_IPAD_AD_List(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	ArrayList<IPAD_Ad_Bean> ipad_ad_list = Admin_AD_DAO.getIPAD_ADList();
    	
    	request.setAttribute("IPAD_AD_LIST", ipad_ad_list);
		request.setAttribute("IPAD_AD_STYLE_MAP", SysCache.IPAD_AD_STYLE_MAP);
		
		request.getRequestDispatcher("/admin/ad_manager/ipad_ad_list.jsp").forward(request, response);
		return;
    }
    
    /**
     * 修改IPAD广告顺序
     * @throws Exception
     */
    public void update_IPAD_AD_Rank(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String ipad_ad_id = request.getParameter("ipad_ad_id");
    	String position = request.getParameter("position");
    	String ad_id = request.getParameter("ad_id");
    	
    	IPAD_Ad_Bean IPAD_AD_BEAN = Admin_AD_DAO.get_IPAD_AD_BEAN(ipad_ad_id);
    	String ad_ids = IPAD_AD_BEAN.getAdids();
    	if (ad_ids != null && !ad_ids.equals("") && !ad_ids.equalsIgnoreCase("null"))
    	{
    		String adids[] = ad_ids.split(",");
    		if (adids.length > 0)
    		{
    			for (int i = 0; i < adids.length; i++)
    			{
    				String adid = adids[i];
    				if (adid != null && adid.equals(ad_id))
    				{
    					if (position.equals("1") && i > 0)
    					{
    						String temp_adid = adids[i - 1];
    						adids[i - 1] = adid;
    						adids[i] = temp_adid;
    					}
    					else if (position.equals("2") && i < (adids.length - 1))
    					{
    						String temp_adid = adids[i + 1];
    						adids[i + 1] = adid;
    						adids[i] = temp_adid;
    					}
    					else if (position.equals("3") && i > 0)
    					{
    						String temp_adid = adids[0];
    						adids[0] = adid;
    						adids[i] = temp_adid;
    					}
    					else if (position.equals("4") && i < (adids.length - 1))
    					{
    						String temp_adid = adids[adids.length - 1];
    						adids[adids.length - 1] = adid;
    						adids[i] = temp_adid;
    					}
    				}
    			}
    			
    			String new_ad_ids = "";
    			for (String a : adids)
    			{
    				if (a != null && !a.equals(""))
    				{
    					new_ad_ids = new_ad_ids + a + ",";
    				}
    			}
    			
    			new_ad_ids = new_ad_ids.substring(0, new_ad_ids.length() - 1);
    			
    			boolean flag = Admin_AD_DAO.update_ipad_AD_rank(ipad_ad_id, new_ad_ids);
    	    	
    	    	if (flag)
    	    	{
    	    		Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "5", "修改IPAD广告[" + ipad_ad_id + "]顺序", "3");
    	    		// 成功
    	    		response.getWriter().print(getRespJSONString("0"));
    	    		return;
    	    	}
    	    	else
    	    	{
    	    		// 失败
    	    		response.getWriter().print(getRespJSONString("1"));
    	    		return;
    	    	}
    		}
    		else
        	{
        		// 失败
        		response.getWriter().print(getRespJSONString("1"));
        		return;
        	}
    	}
    	else
    	{
    		// 失败
    		response.getWriter().print(getRespJSONString("1"));
    		return;
    	}
    }
    
    /**
     * 管理员访问IPAD广告修改编辑页面
     */
    public void show_IPAD_AD_Update_JSP(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String ipad_ad_id = request.getParameter("ipad_ad_id");
    	IPAD_Ad_Bean IPAD_AD_BEAN = Admin_AD_DAO.get_IPAD_AD_BEAN(ipad_ad_id);
    	ArrayList<AD_Bean> USED_IPAD_AD_LIST = null;
    	ArrayList<AD_Bean> IPAD_AD_LIST = null;
    	
    	if (IPAD_AD_BEAN != null && IPAD_AD_BEAN.getAdids() != null && !IPAD_AD_BEAN.getAdids().equals(""))
    	{
    		String adids = IPAD_AD_BEAN.getAdids();
    		USED_IPAD_AD_LIST = Admin_AD_DAO.get_used_IPAD_AD_List(adids);
    		IPAD_AD_LIST = Admin_AD_DAO.get_IPAD_AD_List(adids);
    	}
    	else
    	{
    		show_IPAD_AD_List(request, response);
    	}
    	
    	request.setAttribute("IPAD_AD_BEAN", IPAD_AD_BEAN);
		request.setAttribute("IPAD_AD_STYLE_MAP", SysCache.IPAD_AD_STYLE_MAP);
		
		
		ArrayList<AD_Bean> USED_IPAD_AD_LIST_New = new ArrayList<AD_Bean>();
    	ArrayList<AD_Bean> IPAD_AD_LIST_new = new ArrayList<AD_Bean>();
		if(USED_IPAD_AD_LIST != null && USED_IPAD_AD_LIST.size() >0){
			long now = System.currentTimeMillis();
			for(AD_Bean bean : USED_IPAD_AD_LIST){
				long startTime = 0;
				long endTime = 0;
				if(bean.getStart_time() != null && !"".equals(bean.getStart_time())){
					try {
						startTime = CodeUtil.getUnixTimestemp(bean.getStart_time());
					} catch (Exception e) {
						startTime = 0;
					}
				}
				if(bean.getEnd_time() != null && !"".equals(bean.getEnd_time())){
					try {
						endTime = CodeUtil.getUnixTimestemp(bean.getEnd_time());
					} catch (Exception e) {
						endTime = 0;
					}
				}
				
				if(endTime > now){
					USED_IPAD_AD_LIST_New.add(bean);
				}else{
					IPAD_AD_LIST_new.add(bean);
				}
			}
		}
		IPAD_AD_LIST_new.addAll(IPAD_AD_LIST);
		
		request.setAttribute("USED_IPAD_AD_LIST", USED_IPAD_AD_LIST_New);
		request.setAttribute("IPAD_AD_LIST", IPAD_AD_LIST_new);
		
		request.getRequestDispatcher("/admin/ad_manager/ipad_ad_update.jsp").forward(request, response);
		return;
    }
    
    /**
     * 编辑IPAD广告并提交
     * @throws Exception
     */
    public void update_IPAD_AD(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String ipad_ad_id = request.getParameter("ipad_ad_id");
    	String ad_ids = request.getParameter("ad_ids");
    	String bgcolor = request.getParameter("bgcolor");
    	String ipad_ad_style = request.getParameter("ipad_ad_style");
    	
    	boolean flag = Admin_AD_DAO.update_ipad_AD(ipad_ad_id, ad_ids, bgcolor, ipad_ad_style);
    	
    	if (flag)
    	{
    		Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "5", "编辑IPAD广告[" + ipad_ad_id + "]", "3");
    		// 成功
    		response.getWriter().print(getRespJSONString("0"));
    		return;
    	}
    	else
    	{
    		// 失败
    		response.getWriter().print(getRespJSONString("1"));
    		return;
    	}
    }
    
    /**
     * 管理员访问IPAD广告修改编辑页面
     */
    public void show_IPAD_AD_ADD_JSP(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String _row = request.getParameter("_row");
    	String _col = request.getParameter("_col");
    	String _width = request.getParameter("_width");
    	String _height = request.getParameter("_height");
    	String _classnames = request.getParameter("_classnames");
    	
    	ArrayList<AD_Bean> IPAD_AD_LIST = Admin_AD_DAO.get_IPAD_AD_List(null);
    	
		request.setAttribute("IPAD_AD_STYLE_MAP", SysCache.IPAD_AD_STYLE_MAP);
		request.setAttribute("IPAD_AD_LIST", IPAD_AD_LIST);
		
		request.setAttribute("_row", _row);
		request.setAttribute("_col", _col);
		request.setAttribute("_width", _width);
		request.setAttribute("_height", _height);
		request.setAttribute("_classnames", _classnames);
		
		request.getRequestDispatcher("/admin/ad_manager/ipad_ad_add.jsp").forward(request, response);
		return;
    }
    
    /**
     * 新增IPAD广告并提交
     * @throws Exception
     */
    public void add_IPAD_AD(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String ad_ids = request.getParameter("ad_ids");
    	String bgcolor = request.getParameter("bgcolor");
    	String ipad_ad_style = request.getParameter("ipad_ad_style");
    	
    	String _row = request.getParameter("_row");
    	String _col = request.getParameter("_col");
    	String _width = request.getParameter("_width");
    	String _height = request.getParameter("_height");
    	String _classnames = request.getParameter("_classnames");
    	
    	IPAD_Ad_Bean ipad_ad_bean = new IPAD_Ad_Bean();
    	ipad_ad_bean.setAdids(ad_ids);
    	ipad_ad_bean.setBgcolor(bgcolor);
    	ipad_ad_bean.setStyle(ipad_ad_style);
    	ipad_ad_bean.setRow(Integer.parseInt(_row));
    	ipad_ad_bean.setCol(Integer.parseInt(_col));
    	ipad_ad_bean.setWidth(Integer.parseInt(_width));
    	ipad_ad_bean.setHeight(Integer.parseInt(_height));
    	ipad_ad_bean.setClassnames(_classnames);
    	ipad_ad_bean.setStatus("1");
    	
    	boolean flag = Admin_AD_DAO.add_ipad_AD(ipad_ad_bean);
    	
    	if (flag)
    	{
    		Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "5", "新增IPAD广告[" + ad_ids + "]", "1");
    		// 成功
    		response.getWriter().print(getRespJSONString("0"));
    		return;
    	}
    	else
    	{
    		// 失败
    		response.getWriter().print(getRespJSONString("1"));
    		return;
    	}
    }
    
    /**
     * 删除IPAD广告
     * @throws Exception
     */
    public void del_IPAD_AD(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String ipad_ad_id = request.getParameter("ipad_ad_id");
    	boolean flag = Admin_AD_DAO.delete_ipad_AD(ipad_ad_id);
    	
    	if (flag)
    	{
    		Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "5", "删除IPAD广告[" + ipad_ad_id + "]", "2");
    		// 删除成功
    		response.getWriter().print(getRespJSONString("0"));
    		return;
    	}
    	else
    	{
    		// 删除失败
    		response.getWriter().print(getRespJSONString("1"));
    		return;
    	}
    }
    
    /**
     * 删除广告
     * @throws Exception
     */
    public void delAD(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String ad_id = request.getParameter("ad_id");
    	String ad_title = request.getParameter("ad_title");
    	boolean flag = Admin_AD_DAO.deleteAD(ad_id);
    	
    	if (flag)
    	{
    		AD_Bean bean = Admin_AD_DAO.getADBean(ad_id);
			AdminAdCacheDAO.delAd(bean);
    		Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "5", "删除广告[" + ad_title + "]", "2");
    		// 删除成功
    		response.getWriter().print(getRespJSONString("0"));
    		return;
    	}
    	else
    	{
    		// 删除失败
    		response.getWriter().print(getRespJSONString("1"));
    		return;
    	}
    }
    
    /**
     * 移动广告位置
     * @throws Exception
     */
    public void movePosition(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String id = request.getParameter("id");
		String movetype = request.getParameter("movetype");
		String position = request.getParameter("position");
    	String status = request.getParameter("status");
    	String platform = request.getParameter("platform");
    	boolean flag = false;
    	
		if(id == null||"".equals(id)||movetype == null||"".equals(movetype)||status == null||"".equals(status)||position == null||"".equals(position))
		{
			response.getWriter().print(getRespJSONString("1"));
    		return;
		}
		if(movetype.equals("1"))	//上移
		{
			AD_Bean bean = Admin_AD_DAO.getPreAD(status, platform, position);
			if(bean == null)
			{
				
				response.getWriter().print(getRespJSONString("1"));
	    		return;
			}
			flag = Admin_AD_DAO.updateRank(id, bean.getRank());
			flag = Admin_AD_DAO.updateRank(bean.getId(), Integer.parseInt(position));
		}
		else if(movetype.equals("2"))	//下移
		{
			AD_Bean bean = Admin_AD_DAO.getNextAD(status, platform, position);
			if(bean == null)
			{
				response.getWriter().print(getRespJSONString("1"));
	    		return;
			}
			flag = Admin_AD_DAO.updateRank(id, bean.getRank());
			flag = Admin_AD_DAO.updateRank(bean.getId(),  Integer.parseInt(position));
		}
		else if(movetype.equals("3"))	//移到首位
		{
			int minRank = Admin_AD_DAO.getMinRank(status, platform);
			flag = Admin_AD_DAO.updateRank(id, minRank);
		}
		else if(movetype.equals("4"))	//移到末尾
		{
			int maxRank = Admin_AD_DAO.getMaxRank(status, platform);
			flag = Admin_AD_DAO.updateRank(id, maxRank);
		}
		
		if (flag)
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
    
    public void urlConvert(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	JSONObject data = new JSONObject();
    	String iphone_url = "";
    	String android_url = "";
    	String ipad_url = "";
    	String all_url = "";
		try
		{
			String from_channel = request.getParameter("from_channel");
			String item_id = request.getParameter("item_id");
			
			if(from_channel != null && from_channel.equals("address")){				
				String actionValue = NetManager.convertToPageViewUrl(item_id, "Url Convert", "Url Convert", "urlConvert_110");
				if (actionValue.indexOf(SysConf.hostUrl)< 0 || (actionValue.replaceAll (SysConf.hostUrl,"")).length() >10){
						// 地址转换失败，操作错误，返回：1
					data.put("status", "400");
					response.getWriter().print(data.toString());
					return;
				}
				iphone_url = actionValue;
				android_url = actionValue;
				ipad_url = actionValue;
				all_url = actionValue;
				
			}else{			
				String iphone_outerCode = OuterCode.getOuterCode("", "iphone", null, from_channel, null);
				iphone_url = SysConf.hostUrl + "skip/item?itemid=" + item_id + "&tyh_outer_code=" + iphone_outerCode;
				
				String android_outerCode = OuterCode.getOuterCode("", "android", null, from_channel, null);
				android_url = SysConf.hostUrl + "skip/item?itemid=" + item_id + "&tyh_outer_code=" + android_outerCode;
				
				String ipad_outerCode = OuterCode.getOuterCode("", "ipad", null, from_channel, null);
				ipad_url = SysConf.hostUrl + "skip/item?itemid=" + item_id + "&tyh_outer_code=" + ipad_outerCode;
				
				String all_outerCode = OuterCode.getOuterCode("", "all", null, from_channel, null);
				all_url = SysConf.hostUrl + "skip/item?itemid=" + item_id + "&tyh_outer_code=" + all_outerCode;
				
			}
			
			/*
			if(type.equals("shop"))
			{
				 Map<String, TbkShop> map = TaobaoAPI_DAO.ShopConvert(id);
				 if(map.get(id)!=null)
				 {
					 url = map.get(id).getClickUrl();
				 }
			}
			*/
			
			data.put("status", "1000");
			data.put("iphone_url", iphone_url);
			data.put("android_url", android_url);
			data.put("ipad_url", ipad_url);
			data.put("all_url", all_url);
		}
		catch (Exception e) 
		{
			logger.error("urlConvert error:", e);
			try 
			{
				data.put("status", "400");
			} 
			catch (JSONException e1) 
			{
				logger.error("urlConvert json error:", e);
			}
		}
		response.getWriter().print(data.toString());
    }
    
    public static String getRespJSONString(String result) throws JSONException
    {
    	JSONObject respOBJ = new JSONObject();
		respOBJ.put("result",  result);
		return respOBJ.toString();
    }
    
    public static String convertoJson(String img, String img1, String img2, String click, String description)
    {
		String json = "{\"img\":\""+img+"\",\"img1\":\""+img1+"\",\"img2\":\""+img2+"\","+"\"click\":\""+click+"\","+"\"desc\":\""+description+"\","+"\"tagId\":\"\"}";
		return json;
	}
}
