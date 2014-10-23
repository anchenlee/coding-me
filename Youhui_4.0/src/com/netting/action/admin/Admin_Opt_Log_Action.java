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

import com.netting.bean.Opt_Log;
import com.netting.conf.SysCache;
import com.netting.dao.admin.Admin_AdminUser_DAO;
import com.netting.dao.admin.Admin_OPT_Log_DAO;
import com.netting.util.CodeUtil;

/**
 * 操作日志
 * @author YAOJIANBO
 *
 */
@WebServlet("/ad/opt_log_manager")
public class Admin_Opt_Log_Action extends HttpServlet 
{
private static final long serialVersionUID = 1L;
	
	/**
	 * 初始化日志
	 */
	public static Log logger = LogFactory.getLog( Admin_Opt_Log_Action.class );
	
	
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
			if (methodName.equals("showlogList"))
			{
				showlogList(request, response);
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
	 * 查询操作日志
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showlogList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String page_str = request.getParameter("page");
		String keyword = request.getParameter("keyword");
		String username = request.getParameter("username");
		String menu_id = request.getParameter("menu_id");
		String opt_type = request.getParameter("opt_type");
		String start_time = request.getParameter("start_time");
		String end_time = request.getParameter("end_time");
		
		int page = 1;
		if (page_str != null && !page_str.equals(""))
		{
			page = Integer.parseInt(page_str);
		}
		if (keyword == null)
		{
			keyword = "";
		}
		if (username == null)
		{
			username = "all";
		}
		if (menu_id == null)
		{
			menu_id = "all";
		}
		if (opt_type == null)
		{
			opt_type = "all";
		}
		
		String start_opt_time = "";
		String end_opt_time = "";
		try
		{
			if (start_time == null)
			{
				start_opt_time = "";
			}
			if (!start_time.equals(""))
			{
				start_opt_time = String.valueOf(CodeUtil.getTimeMillis_2(start_time));
			}
		}
		catch (Exception e)
		{
			start_opt_time = "";
		}
		try
		{
			if (end_time == null)
			{
				end_opt_time = "";
			}
			if (!end_time.equals(""))
			{
				end_opt_time = String.valueOf(CodeUtil.getTimeMillis_2(end_time));
			}
		}
		catch (Exception e)
		{
			end_opt_time = "";
		}
		
		HashMap<String, String> MenuMap = Admin_AdminUser_DAO.getAllMenuMap();
		MenuMap.put("all", "全部");
		HashMap<String, String> AdminUserMap = Admin_AdminUser_DAO.getAdminUserMap();
		AdminUserMap.put("all", "全部");
		ArrayList<Opt_Log> logList = Admin_OPT_Log_DAO.getOptLogList(page, username, menu_id, opt_type, start_opt_time, end_opt_time, keyword);
		int count = Admin_OPT_Log_DAO.getOptLogListTotal(username, menu_id, opt_type, start_opt_time, end_opt_time, keyword);
		
		int totalPage = 0;
		totalPage = count / Admin_OPT_Log_DAO.pageSize;
		int ys = count % Admin_OPT_Log_DAO.pageSize;
		if(ys > 0)
		{
			totalPage++;
		}
		
		request.setAttribute("LogList", logList);
		request.setAttribute("page", page);
		request.setAttribute("totalpage", totalPage);
		request.setAttribute("all_log_count", count);
		
    	request.setAttribute("MenuMap", MenuMap);
    	request.setAttribute("AdminUserMap", AdminUserMap);
    	request.setAttribute("OptTypeMap", SysCache.OptTypeMap);
    	
    	request.setAttribute("keyword", keyword);
    	request.setAttribute("username", username);
    	request.setAttribute("menu_id", menu_id);
    	request.setAttribute("opt_type", opt_type);
    	request.setAttribute("start_time", start_time);
    	request.setAttribute("end_time", end_time);
    	
		request.getRequestDispatcher("/admin/opt_log/opt_log_list.jsp").forward(request, response);
		return;
	}
}
