package com.netting.action.admin;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;

import com.netting.bean.Searchkeyword;
import com.netting.conf.SysCache;
import com.netting.dao.admin.Admin_OPT_Log_DAO;
import com.netting.dao.admin.Admin_SearchKeyWord_DAO;
import com.netting.dao.admin.Admin_Tag_DAO;
import com.netting.util.CodeUtil;
import com.netting.util.NetManager;

/**
 * 搜索关键字模块管理
 * @author YAOJIANBO
 * @since 2013-10-28
 */
@WebServlet("/ad/search_keyword_manager")
public class Admin_SearchKeyword_Manager_Action extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 初始化日志
	 */
	public static Log logger = LogFactory.getLog( Admin_SearchKeyword_Manager_Action.class );
	
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
			if(methodName.equals("showUpdateSearchKeywordJSP"))
			{
				showUpdateSearchKeywordJSP(request,response);
			}
			else if(methodName.equals("updateSearchKeyword"))
			{
				updateSearchKeyword(request,response);
			}
			else if(methodName.equals("showAddSearchKeywordJSP"))
			{
				showAddSearchKeywordJSP(request,response);
			}
			else if(methodName.equals("addSearchKeyword"))
			{
				addSearchKeyword(request,response);
			}
			else if(methodName.equals("showSearchKeywordList"))
			{
				showSearchKeywordList(request,response);
			}
			else if(methodName.equals("delSearchKeyword"))
			{
				delSearchKeyword(request,response);
			}
			else if(methodName.equals("updateSearchKeywordStatus"))
			{
				updateSearchKeywordStatus(request,response);
			}
			else if(methodName.equals("movePosition"))
			{
				movePosition(request,response);
			}
			else if(methodName.equals("showLastLevelList"))
			{
				showLastLevelList(request,response);
			}else if(methodName.equals("showHotSearchKeywordsList"))
			{
				showHotSearchKeywordsList(request,response);
			}
		}
		catch (Exception e)
		{
			response.getWriter().print("<script>alert('server process error！');history.back();</script>");
			return;
		}
		
		return;
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException 
	{
		this.doGet(req, resp);
	}

	/**
	 * 访问编辑页面
	 * @throws Exception
	 */
	public void showUpdateSearchKeywordJSP(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String keyword_id = request.getParameter("id");
		String parent_id = request.getParameter("parent_id");
		
		Searchkeyword bean = Admin_SearchKeyWord_DAO.getKeywordBean(keyword_id);
		
		request.setAttribute("SearchkeywordBean", bean);
		request.setAttribute("parent_id", parent_id);
		
		request.setAttribute("ActionTypeMap", SysCache.ActionTypeMap);
		request.setAttribute("XTYM_Map", SysCache.XTYM_Map);
		ArrayList<HashMap<String, String>> Tag_Type_Value_Map = Admin_Tag_DAO.getTagActionType_Value_Map("538");
		request.setAttribute("Tag_Type_Value_Map", Tag_Type_Value_Map);
		
		request.getRequestDispatcher("/admin/search_keyword_manager/search_keyword_update.jsp").forward(request, response);
		return;
	}
	
	/**
	 * 编辑提交
	 * @throws Exception
	 */
	public void updateSearchKeyword(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String parent_id = request.getParameter("parent_id");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		
		boolean name_repeat = Admin_SearchKeyWord_DAO.checkSearchKeywordRepeat(name, id, parent_id, 2);
		if (name_repeat)
		{
			// 已存在与该名称相同的其他关键字，返回：2
    		response.getWriter().print(getRespJSONString("2"));
    		return;
		}
		
		String action_type = request.getParameter("action_type");
		String action_value = request.getParameter("action_value");
		if (action_value == null)
		{
			action_value = "";
		}
		String img = request.getParameter("img");
		if (img == null)
		{
			img = "";
		}
		
		if(action_type.endsWith("tagStyleWeb") || action_type.equals("systemWeb")) 
		{
			try 
			{
				action_value = NetManager.convertToPageViewUrl(action_value, name, name, "keyword_" + id);
			} 
			catch (Exception e) 
			{
				// 地址转换失败，操作错误，返回：1
	    		response.getWriter().print(getRespJSONString("3"));
	    		return;
			}
		}
		
		Searchkeyword bean = new Searchkeyword();
		bean.setId(id);
		bean.setName(name);
		bean.setAction_type(action_type);
		bean.setAction_value(action_value);
		bean.setIcon(img);
		
		boolean flag = Admin_SearchKeyWord_DAO.updateKeyword(bean);
		if (flag)
		{
			Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "5", 
					"修改搜索关键字 [<a target=\"_blank\" style=\"color: #1F92FF\" href=\"" + request.getContextPath() + "/ad/search_keyword_manager?actionmethod=showUpdateSearchKeywordJSP&parent_id=" + parent_id + "&id=" + id + "\">" + name + "</a>]",
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
	 * 访问新增页面
	 * @throws Exception
	 */
	public void showAddSearchKeywordJSP(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String parent_id = request.getParameter("parent_id");
		
		request.setAttribute("parent_id", parent_id);
		
		request.setAttribute("ActionTypeMap", SysCache.ActionTypeMap);
		request.setAttribute("XTYM_Map", SysCache.XTYM_Map);
		ArrayList<HashMap<String, String>> Tag_Type_Value_Map = Admin_Tag_DAO.getTagActionType_Value_Map("538");
		request.setAttribute("Tag_Type_Value_Map", Tag_Type_Value_Map);
		
		request.getRequestDispatcher("/admin/search_keyword_manager/search_keyword_add.jsp").forward(request, response);
		return;
	}
	
	/**
	 * 新增提交
	 * @throws Exception
	 */
	public void addSearchKeyword(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String parent_id = request.getParameter("parent_id");
		String name = request.getParameter("name");
		boolean name_repeat = Admin_SearchKeyWord_DAO.checkSearchKeywordRepeat(name, null, parent_id, 1);
		if (name_repeat)
		{
			// 已存在与该名称相同的其他关键字，返回：2
    		response.getWriter().print(getRespJSONString("2"));
    		return;
		}
		
		String img = request.getParameter("img");
		if (img == null)
		{
			img = "";
		}
		
		String id = Admin_SearchKeyWord_DAO.getNextID();
		String action_type = request.getParameter("action_type");
		String action_value = request.getParameter("action_value");
		if (action_value == null)
		{
			action_value = "";
		}
		if(action_type.endsWith("tagStyleWeb") || action_type.equals("systemWeb")) 
		{
			try 
			{
				action_value = NetManager.convertToPageViewUrl(action_value, name, name, "keyword_" + id);
			} 
			catch (Exception e) 
			{
				// 地址转换失败，操作错误，返回：1
	    		response.getWriter().print(getRespJSONString("3"));
	    		return;
			}
		}
		
		Searchkeyword bean = new Searchkeyword();
		
		bean.setId(id);
		bean.setParent_id(parent_id);
		bean.setName(name);
		bean.setAction_type(action_type);
		bean.setAction_value(action_value);
		bean.setIcon(img);
		bean.setVersion("1");
		
		boolean flag = Admin_SearchKeyWord_DAO.addKeyword(bean);
		if (flag)
		{
			Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "5", 
					"新增搜索关键字 [<a target=\"_blank\" style=\"color: #1F92FF\" href=\"" + request.getContextPath() + "/ad/search_keyword_manager?actionmethod=showUpdateSearchKeywordJSP&parent_id=" + parent_id + "&id=" + id + "\">" + name + "</a>]",
					"1");
			
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
     * 管理员访问列表页面
     */
    public void showSearchKeywordList(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String parent_id = request.getParameter("parent_id");
    	String page_str = request.getParameter("page");
    	String status = request.getParameter("status");
    	if (status == null || "".equals(status))
    	{
    		status = "0";
    	}
    	
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
    	
    	ArrayList<Searchkeyword> list = Admin_SearchKeyWord_DAO.getKeywordList(page, "1", parent_id, status);
    	int totalPage = Admin_SearchKeyWord_DAO.getKeywordListTotal("1", parent_id, status);
    	
    	request.setAttribute("SearchKeywordList", list);
		request.setAttribute("parent_id", parent_id);
		request.setAttribute("status", status);
		request.setAttribute("SearchKeywordStatusMap", SysCache.SearchKeywordStatusMap);
		request.setAttribute("page", page);
		request.setAttribute("totalpage", totalPage);
		
		request.getRequestDispatcher("/admin/search_keyword_manager/search_keyword_list.jsp").forward(request, response);
		return;
    }
    
    /**
     * 删除
     * @throws Exception
     */
    public void delSearchKeyword(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String id = request.getParameter("id");
    	String name = request.getParameter("name");
    	boolean flag = Admin_SearchKeyWord_DAO.deleteKeyword(id);
    	
    	if (flag)
    	{
    		Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "5", 
					"删除搜索关键字 [" + name + "]", 
					"2");
    		
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
     * 修改搜索关键字状态
     * @throws Exception
     */
    public void updateSearchKeywordStatus(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String id = request.getParameter("id");
    	String name = request.getParameter("name");
    	String status = request.getParameter("status");
    	
    	boolean flag = Admin_SearchKeyWord_DAO.updateKeywordStatus(id, status);
    	
    	if (flag)
    	{
    		Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "5", 
					"修改搜索关键字状态 [" + name + "]", 
					"3");
    		
    		// 操作成功
    		response.getWriter().print(getRespJSONString("0"));
    		return;
    	}
    	else
    	{
    		// 操作失败
    		response.getWriter().print(getRespJSONString("1"));
    		return;
    	}
    }
    
    public void movePosition(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String id = request.getParameter("id");
    	String parent_id = request.getParameter("parent_id");
    	String movetype = request.getParameter("movetype");
		String position = request.getParameter("position");
		String status = request.getParameter("status");
		if(id==null||"".equals(id)||movetype==null||"".equals(movetype)||position==null||"".equals(movetype)||parent_id==null||"".equals(parent_id))
		{
			response.getWriter().print(getRespJSONString("1"));
    		return;
		}
		Searchkeyword bean = Admin_SearchKeyWord_DAO.getKeywordBean(id);
		
		if(movetype.equals("1"))	//上移
		{
			Searchkeyword newbean = Admin_SearchKeyWord_DAO.gerPreSearchkeyword(parent_id, position, "1",status);
			if(newbean==null) 
			{
				response.getWriter().print(getRespJSONString("1"));
	    		return;
			}
			else
			{
				boolean flag = Admin_SearchKeyWord_DAO.updateRank(id, newbean.getRank());
				flag = Admin_SearchKeyWord_DAO.updateRank(newbean.getId(), bean.getRank());
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
		}
		else if(movetype.equals("2"))	//下移
		{
			Searchkeyword newbean = Admin_SearchKeyWord_DAO.gerNextSearchkeyword(parent_id, position, "1",status);
			if(newbean==null) 
			{
				response.getWriter().print(getRespJSONString("1"));
	    		return;
			}
			else
			{
				boolean flag = Admin_SearchKeyWord_DAO.updateRank(id, newbean.getRank());
				flag = Admin_SearchKeyWord_DAO.updateRank(newbean.getId(), bean.getRank());
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
		}
		else if(movetype.equals("3"))	//移到首位
		{
			int rank = Admin_SearchKeyWord_DAO.getMaxRank();
			boolean flag = Admin_SearchKeyWord_DAO.updateRank(id, rank+"");
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
		else if(movetype.equals("4"))	//移到末尾
		{
			int rank = Admin_SearchKeyWord_DAO.getMinRank();
			boolean flag = Admin_SearchKeyWord_DAO.updateRank(id, rank+"");
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
    }
    
    public void showLastLevelList(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String id = request.getParameter("parent_id");
    	Searchkeyword bean = Admin_SearchKeyWord_DAO.getKeywordBean(id);
    	String lastLevelParentID = bean.getParent_id();
    	request.getRequestDispatcher("/ad/search_keyword_manager?actionmethod=showSearchKeywordList&parent_id=" + lastLevelParentID).forward(request, response);
    	return;
    }
    public void showHotSearchKeywordsList(HttpServletRequest request, HttpServletResponse response) throws Exception{
    	String p = request.getParameter("page");
    	String startTime = request.getParameter("start_time");
    	String endTime = request.getParameter("end_time");
    	Date dt = new Date();   
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");  
	    String nowDate = sdf.format(dt); 
	    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");  
	    String nowDate1 = sdf1.format(dt); 
	    
    	if(startTime == null || "".equals(startTime)){
    		startTime = CodeUtil.getTimeMillis_2(nowDate+"-01 00:00:00") +""; 
    	}else {
    		startTime = CodeUtil.getTimeMillis_2(startTime.substring(0, 10)+" 00:00:00")+"";
    	}
    	if(endTime == null || "".equals(endTime)){
    		endTime = CodeUtil.getTimeMillis_2(nowDate1+" 23:59:59")+"";
    	}else {
    		endTime = CodeUtil.getTimeMillis_2(endTime.substring(0, 10)+" 23:59:59")+"";
    	}
    	int page = 1;
    	try {
			page = Integer.parseInt(p);
		} catch (Exception e) {
			page = 1;
			// TODO: handle exception
		}
    	List<Searchkeyword> list = Admin_SearchKeyWord_DAO.getHotSearchKeyword(page,startTime,endTime);
//    	List<Searchkeyword> list = new ArrayList<Searchkeyword>();
    	int totalPage = 1;
    	request.setAttribute("page", page);
		request.setAttribute("totalpage", totalPage);
		request.setAttribute("list", list);
    	request.getRequestDispatcher("/admin/search_keyword_manager/search_keyword_hot_list.jsp").forward(request, response);
		return;
    }
    public static String getRespJSONString(String result) throws JSONException
    {
    	JSONObject respOBJ = new JSONObject();
		respOBJ.put("result",  result);
		return respOBJ.toString();
    }
}
