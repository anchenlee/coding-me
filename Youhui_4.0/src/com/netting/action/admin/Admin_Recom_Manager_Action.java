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

import com.netting.bean.Action;
import com.netting.bean.Sale;
import com.netting.cache.dao.Admin_Recom_Cache_DAO;
import com.netting.conf.SysCache;
import com.netting.conf.SysConf;
import com.netting.dao.admin.Admin_OPT_Log_DAO;
import com.netting.dao.admin.Admin_Recom_DAO;
import com.netting.dao.admin.Admin_Tag_DAO;
import com.netting.util.CodeUtil;
import com.netting.util.NetManager;

/**
 * 推荐管理
 * @author YAOJIANBO
 * @since 2013-10-15
 */
@WebServlet("/ad/recom_manager")
public class Admin_Recom_Manager_Action extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 初始化日志
	 */
	public static Log logger = LogFactory.getLog( Admin_Recom_Manager_Action.class );
	
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
			if (methodName.equals("showUpdateRecomJSP"))
			{
				showUpdateRecomJSP(request, response);
			}
			else if (methodName.equals("updateRecom"))
			{
				updateRecom(request, response);
			}
			else if (methodName.equals("showAddRecomJSP"))
			{
				showAddRecomJSP(request, response);
			}
			else if (methodName.equals("addRecom"))
			{
				addRecom(request, response);
			}
			else if (methodName.equals("showRecomList"))
			{
				showRecomList(request, response);
			}
			else if (methodName.equals("delRecom"))
			{
				delRecom(request, response);
			}
			else if (methodName.equals("MovePosition"))
			{
				MovePosition(request, response);
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
	
	public void showUpdateRecomJSP(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String recom_id = request.getParameter("recom_id");
		String page = request.getParameter("page");
		Sale recom = Admin_Recom_DAO.getRecomBean(recom_id);
		
		request.setAttribute("RecomBean", recom);
		request.setAttribute("page", page);
		
		request.setAttribute("SmallShowMap", SysCache.TagSmallShowMap);
		request.setAttribute("ActionTypeMap", SysCache.ActionTypeMap);
		request.setAttribute("XTYM_Map", SysCache.XTYM_Map);
		ArrayList<HashMap<String, String>> Tag_Type_Value_Map = Admin_Tag_DAO.getTagActionType_Value_Map("538");
		request.setAttribute("Tag_Type_Value_Map", Tag_Type_Value_Map);
		
		request.getRequestDispatcher("/admin/recom_manager/recom_update.jsp").forward(request, response);
		return;
	}
	
	/**
	 * 编辑推荐提交
	 * @throws Exception
	 */
	public void updateRecom(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String recom_id = request.getParameter("recom_id");
		String rank = request.getParameter("rank");
		String date = request.getParameter("date");
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String memo = request.getParameter("memo");
		String small_show = request.getParameter("small_show");
		String start_time = request.getParameter("start_time");
		String end_time = request.getParameter("end_time");
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
		
		String recom_icon = request.getParameter("recom_icon");
		String action_type = request.getParameter("action_type");
		String action_value = request.getParameter("action_value");
		
		if(action_type.endsWith("tagStyleWeb") || action_type.equals("systemWeb")) 
		{
			try 
			{
				action_value = NetManager.convertToPageViewUrl(action_value, title, title, "sale_"+recom_id);
				if (action_value.indexOf(SysConf.hostUrl) < 0)
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
		
		Sale recom = new Sale();
		recom.setId(recom_id);
		recom.setRank(Long.parseLong(rank));
		recom.setDate(date);
		recom.setTitle(title);
		recom.setDescription(description);
		recom.setMemo(memo);
		recom.setShow(small_show);
		recom.setStartTime(start_time);
		recom.setEndTime(end_time);
		recom.setIcon(recom_icon);
		recom.setAction(new Action(action_type, action_value));
		
		boolean flag = Admin_Recom_DAO.updateRecom(recom);
		if (flag)
		{
			try
			{
				Admin_Recom_Cache_DAO.updateRecomRank(recom);
				Admin_Recom_Cache_DAO.setRecom(recom);
			}
			catch (Exception e)
			{
				// 缓存更新失败，操作错误，返回：1
	    		response.getWriter().print(getRespJSONString("3"));
	    		return;
			}
			
			Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "2", 
					"修改推荐 [<a target=\"_blank\" style=\"color: #1F92FF\" href=\"" + request.getContextPath() + "/ad/recom_manager?actionmethod=showUpdateRecomJSP&recom_id=" + recom_id + "\">" + title + "</a>]",
					"3");
			
			// 更新成功，返回：0
    		response.getWriter().print(getRespJSONString("0"));
    		return;
		}
		else
		{
			// 数据库更新失败，返回：1
    		response.getWriter().print(getRespJSONString("3"));
    		return;
		}
		
	}
	
	/**
	 * 访问新增推荐页面
	 * @throws Exception
	 */
	public void showAddRecomJSP(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String page = request.getParameter("page");
		
		request.setAttribute("page", page);
		request.setAttribute("SmallShowMap", SysCache.TagSmallShowMap);
		request.setAttribute("ActionTypeMap", SysCache.ActionTypeMap);
		request.setAttribute("XTYM_Map", SysCache.XTYM_Map);
		ArrayList<HashMap<String, String>> Tag_Type_Value_Map = Admin_Tag_DAO.getTagActionType_Value_Map("538");
		request.setAttribute("Tag_Type_Value_Map", Tag_Type_Value_Map);
		
		request.getRequestDispatcher("/admin/recom_manager/recom_add.jsp").forward(request, response);
		return;
	}
	
	/**
	 * 新增推荐提交
	 * @throws Exception
	 */
	public void addRecom(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String memo = request.getParameter("memo");
		String small_show = request.getParameter("small_show");
		String start_time = request.getParameter("start_time");
		String end_time = request.getParameter("end_time");
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
		
		String recom_icon = request.getParameter("recom_icon");
		String action_type = request.getParameter("action_type");
		String action_value = request.getParameter("action_value");
		
		String recom_id = Admin_Recom_DAO.getRecomNextID();
		
		if(action_type.endsWith("tagStyleWeb") || action_type.equals("systemWeb")) 
		{
			try 
			{
				action_value = NetManager.convertToPageViewUrl(action_value, title, title, "sale_" + recom_id);
				if (action_value.indexOf(SysConf.hostUrl) < 0)
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
		
		Sale recom = new Sale();
		recom.setId(recom_id);
		recom.setTitle(title);
		recom.setDescription(description);
		recom.setMemo(memo);
		recom.setShow(small_show);
		recom.setStartTime(start_time);
		recom.setEndTime(end_time);
		recom.setIcon(recom_icon);
		recom.setAction(new Action(action_type, action_value));
		recom.setParentId("2");
		recom.setDate(CodeUtil.getDateStr());
		
		recom = Admin_Recom_DAO.addRecom(recom);
		if (recom != null)
		{
			try
			{
				Admin_Recom_Cache_DAO.updateRecomRank(recom);
				Admin_Recom_Cache_DAO.setRecom(recom);
			}
			catch (Exception e)
			{
				// 缓存更新失败，操作错误，返回：1
	    		response.getWriter().print(getRespJSONString("3"));
	    		return;
			}
			
			Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "2", 
					"新增推荐 [<a target=\"_blank\" style=\"color: #1F92FF\" href=\"" + request.getContextPath() + "/ad/recom_manager?actionmethod=showUpdateRecomJSP&recom_id=" + recom_id + "\">" + title + "</a>]",
					"1");
			
			// 新增成功，返回：0
    		response.getWriter().print(getRespJSONString("0"));
    		return;
		}
		else
		{
			// 数据库插入失败，返回：1
    		response.getWriter().print(getRespJSONString("4"));
    		return;
		}
		
	}

    /**
     * 管理员访问推荐列表信息
     */
    public void showRecomList(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String keyword = request.getParameter("keyword");
    	String page_str = request.getParameter("page");
    	String status_str = request.getParameter("status");
    	
    	// 默认第一页
    	int page = 1;
    	if (page_str != null && !"".equals(page_str))
    	{
    		page = Integer.parseInt(page_str);
    	}
    	
    	int status = 4;
    	if (status_str != null && !"".equals(status_str))
    	{
    		status = Integer.parseInt(status_str);
    	}
    	
    	ArrayList<Sale> recomList = Admin_Recom_DAO.getRecomList(page, keyword, status);
    	int totalPage = Admin_Recom_DAO.getRecomListTotal(keyword,status);
    	
    	request.setAttribute("RecomList", recomList);
    	request.setAttribute("status", status);
    	request.setAttribute("RecomStatusMap", SysCache.RecomStatusMap);
    	request.setAttribute("ActionTypeMap", SysCache.ActionTypeMap);
		request.setAttribute("page", page);
		request.setAttribute("totalpage", totalPage);
		
		request.getRequestDispatcher("/admin/recom_manager/recom_list.jsp").forward(request, response);
		return;
    }
    
    /**
     * 删除推荐
     * @throws Exception
     */
    public void delRecom(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String recom_id = request.getParameter("recom_id");
    	String recom_title = request.getParameter("recom_title");
    	boolean flag = Admin_Recom_DAO.delRecom(recom_id);
    	
    	if (flag)
    	{
    		try
			{
				Admin_Recom_Cache_DAO.delRecomRank(recom_id);
				Admin_Recom_Cache_DAO.delRecom(recom_id);
			}
			catch (Exception e)
			{
				// 缓存更新失败，返回：1
	    		response.getWriter().print(getRespJSONString("1"));
	    		return;
			}
			
    		Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "2", 
					"删除推荐 [" + recom_title + "]", "2");
    		
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
     * 移动推荐位置
     * @throws Exception
     */
    public void MovePosition(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String id = request.getParameter("id");
		String movetype = request.getParameter("movetype");
		String position = request.getParameter("position");
    	String parent_id = request.getParameter("parent_id");
    	String state = request.getParameter("state");
    	boolean flag = false;
    	
		if(id == null||"".equals(id)||movetype == null||"".equals(movetype)||position == null||"".equals(position))
		{
			response.getWriter().print(getRespJSONString("1"));
    		return;
		}
		
		Sale nowBean = Admin_Recom_DAO.getRecomBean(id);
		if(nowBean == null)
		{
			response.getWriter().print(getRespJSONString("1"));
    		return;
		}
		nowBean.setStartTime(CodeUtil.getTimeMillis_2(nowBean.getStartTime())+"");
		nowBean.setEndTime(CodeUtil.getTimeMillis_2(nowBean.getEndTime())+"");
		
		if(movetype.equals("2"))	
		{
			Sale bean = Admin_Recom_DAO.getPreSale(state, parent_id, position);
			if(bean == null)
			{
				response.getWriter().print(getRespJSONString("1"));
	    		return;
			}
			flag = Admin_Recom_DAO.updateRank(id, bean.getRank());
			flag = Admin_Recom_DAO.updateRank(bean.getId(), Integer.parseInt(position));
			if(flag)
			{
				long rank0 = bean.getRank();
				nowBean.setRank(rank0);
				bean.setRank(Long.parseLong(position));
				Admin_Recom_Cache_DAO.updateRecomRank(nowBean);
				Admin_Recom_Cache_DAO.setRecom(nowBean);
				Admin_Recom_Cache_DAO.updateRecomRank(bean);
				Admin_Recom_Cache_DAO.setRecom(bean);
				response.getWriter().print(getRespJSONString("0"));
	    		return;
			}
		}
		else if(movetype.equals("1"))	
		{
			Sale bean = Admin_Recom_DAO.getNextSale(state, parent_id, position);
			if(bean == null)
			{
				response.getWriter().print(getRespJSONString("1"));
	    		return;
			}
			flag = Admin_Recom_DAO.updateRank(id, bean.getRank());
			flag = Admin_Recom_DAO.updateRank(bean.getId(),  Integer.parseInt(position));
			if(flag)
			{
				long rank0 = bean.getRank();
				nowBean.setRank(rank0);
				bean.setRank(Long.parseLong(position));
				Admin_Recom_Cache_DAO.updateRecomRank(nowBean);
				Admin_Recom_Cache_DAO.setRecom(nowBean);
				Admin_Recom_Cache_DAO.updateRecomRank(bean);
				Admin_Recom_Cache_DAO.setRecom(bean);
				response.getWriter().print(getRespJSONString("0"));
	    		return;
			}
		}
		else if(movetype.equals("4"))	
		{
			int maxRank = Admin_Recom_DAO.getMaxRank(state, parent_id);
			flag = Admin_Recom_DAO.updateRank(id, maxRank);
			if(flag)
			{
				nowBean.setRank(maxRank);
				Admin_Recom_Cache_DAO.updateRecomRank(nowBean);
				Admin_Recom_Cache_DAO.setRecom(nowBean);
				response.getWriter().print(getRespJSONString("0"));
	    		return;
			}
		}
		else if(movetype.equals("3"))	
		{
			int minRank = Admin_Recom_DAO.getMinRank(state, parent_id);
			flag = Admin_Recom_DAO.updateRank(id, minRank);
			if(flag)
			{
				nowBean.setRank(minRank);
				Admin_Recom_Cache_DAO.updateRecomRank(nowBean);
				Admin_Recom_Cache_DAO.setRecom(nowBean);
				response.getWriter().print(getRespJSONString("0"));
	    		return;
			}
		}

    		response.getWriter().print(getRespJSONString("1"));
    		return;
    }
    
    public static String getRespJSONString(String result) throws JSONException
    {
    	JSONObject respOBJ = new JSONObject();
		respOBJ.put("result",  result);
		return respOBJ.toString();
    }
}
