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

import com.netting.bean.AdminUser;
import com.netting.conf.SysCache;
import com.netting.dao.admin.Admin_AdminUser_DAO;
import com.netting.dao.admin.Admin_OPT_Log_DAO;

/**
 * 后台管理用户模块管理
 * @author YAOJIANBO
 * @since 2013-11-05
 */
@WebServlet("/ad/admin_user_manager")
public class Admin_AdminUser_Manager_Action extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 初始化日志
	 */
	public static Log logger = LogFactory.getLog( Admin_AdminUser_Manager_Action.class );
	
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
			if (methodName.equals("showUpdateAdminUserJSP"))
			{
				showUpdateAdminUserJSP(request, response);
			}
			else if (methodName.equals("updateAdminUser"))
			{
				updateAdminUser(request, response);
			}
			else if (methodName.equals("showAddAdminUserJSP"))
			{
				showAddAdminUserJSP(request, response);
			}
			else if (methodName.equals("addAdminUser"))
			{
				addAdminUser(request, response);
			}
			else if (methodName.equals("showAdminUserList"))
			{
				showAdminUserList(request, response);
			}
			else if (methodName.equals("delAdminUser"))
			{
				delAdminUser(request, response);
			}
			else if (methodName.equals("showUpdateAdminUserMenuListJSP"))
			{
				showUpdateAdminUserMenuListJSP(request, response);
			}
			else if (methodName.equals("addMenuToUser"))
			{
				addMenuToUser(request, response);
			}
			else if (methodName.equals("delMenuToUser"))
			{
				delMenuToUser(request, response);
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
	public void showUpdateAdminUserJSP(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String user_id = request.getParameter("user_id");
		String page = request.getParameter("page");
		AdminUser bean = Admin_AdminUser_DAO.getAdminUserBean(user_id);
		
		request.setAttribute("UpdateAdminUser", bean);
		request.setAttribute("page", page);
		request.setAttribute("AdminUserGroupMap", SysCache.AdminUserGroupMap);
		
		request.getRequestDispatcher("/admin/admin_user_manager/user_update.jsp").forward(request, response);
		return;
	}
	
	/**
	 * 编辑提交
	 * @throws Exception
	 */
	public void updateAdminUser(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");
		String realname = request.getParameter("realname");
		
		AdminUser bean = new AdminUser();
		
		bean.setId(user_id);
		bean.setPassword(password);
		bean.setRealname(realname);
		
		boolean flag = Admin_AdminUser_DAO.updateAdminUser(bean);
		if (flag)
		{
			Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "9", 
					"修改后台用户[<a target=\"_blank\" style=\"color: #1F92FF\" href=\"" + request.getContextPath() + "/ad/admin_user_manager?actionmethod=showUpdateAdminUserJSP&user_id=" + user_id + "\">" + realname + "</a>]", 
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
	public void showAddAdminUserJSP(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String page = request.getParameter("page");
		request.setAttribute("page", page);
		request.getRequestDispatcher("/admin/admin_user_manager/user_add.jsp").forward(request, response);
		return;
	}
	
	/**
	 * 新增提交
	 * @throws Exception
	 */
	public void addAdminUser(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String admin_user_id = Admin_AdminUser_DAO.getAdminUserNextID();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String realname = request.getParameter("realname");
		
		boolean usernameRepeat = Admin_AdminUser_DAO.checkAdminUsername(username);
		if (usernameRepeat)
		{
			// 用户名重复，返回：2
    		response.getWriter().print(getRespJSONString("2"));
    		return;
		}
		
		AdminUser bean = new AdminUser();
		
		bean.setId(admin_user_id);
		bean.setUsername(username);
		bean.setPassword(password);
		bean.setRealname(realname);
		bean.setGroup(2);
		
		boolean flag = Admin_AdminUser_DAO.addAdminUser(bean);
		if (flag)
		{
			Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "9", 
					"新增后台用户[<a target=\"_blank\" style=\"color: #1F92FF\" href=\"" + request.getContextPath() + "/ad/admin_user_manager?actionmethod=showUpdateAdminUserJSP&user_id=" + admin_user_id + "\">" + realname + "</a>]",
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
    public void showAdminUserList(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String keyword = request.getParameter("keyword");
    	String page_str = request.getParameter("page");
    	
    	// 默认第一页
    	int page = 1;
    	if (page_str != null && !"".equals(page_str))
    	{
    		page = Integer.parseInt(page_str);
    	}
    	
    	ArrayList<AdminUser> adminUserList = Admin_AdminUser_DAO.getAdminUserList(page, keyword);
    	int totalPage = Admin_AdminUser_DAO.getAdminUserListTotal(keyword);
    	
    	request.setAttribute("AdminUserList", adminUserList);
		request.setAttribute("page", page);
		request.setAttribute("totalpage", totalPage);
		request.setAttribute("AdminUserGroupMap", SysCache.AdminUserGroupMap);
		
		request.getRequestDispatcher("/admin/admin_user_manager/user_list.jsp").forward(request, response);
		return;
    }
    
    /**
     * 删除
     * @throws Exception
     */
    public void delAdminUser(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String user_id = request.getParameter("user_id");
    	String realname = request.getParameter("realname");
    	boolean flag = Admin_AdminUser_DAO.deleteAdminUser(user_id);
    	
    	if (flag)
    	{
    		
    		flag = Admin_AdminUser_DAO.deleteAdminUserToMenu(user_id);
    		if (flag)
    		{
    			Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "9", 
    					"删除后台用户[" + realname + "]", 
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
    	else
    	{
    		// 删除失败
    		response.getWriter().print(getRespJSONString("1"));
    		return;
    	}
    }
    
    /**
	 * 访问编辑菜单访问权限页面
	 * @throws Exception
	 */
	public void showUpdateAdminUserMenuListJSP(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String user_id = request.getParameter("user_id");
		String realname = request.getParameter("realname");
		String page = request.getParameter("page");
		
		HashMap<String, String> allMenuMap = Admin_AdminUser_DAO.getAllMenuMap();
		String userMenu = Admin_AdminUser_DAO.getUserMenuList(user_id);
		
		request.setAttribute("AllMenuMap", allMenuMap);
		request.setAttribute("UserMenu", userMenu);
		request.setAttribute("admin_user_id", user_id);
		request.setAttribute("admin_user_realname", realname);
		request.setAttribute("page", page);
		
		request.getRequestDispatcher("/admin/admin_user_manager/user_menu_update.jsp").forward(request, response);
		return;
	}
	
	/**
     * 用户增加菜单权限
     * @throws Exception
     */
    public void addMenuToUser(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String user_id = request.getParameter("user_id");
    	String user_realname = request.getParameter("user_realname");
    	if (user_realname != null) 
		{  
    		user_realname = new String(user_realname.getBytes("iso-8859-1"), "utf-8");
		} 
    	String menu_id = request.getParameter("menu_id");
    	String menu_title = request.getParameter("menu_title");
    	
    	boolean flag = Admin_AdminUser_DAO.checkUserToMenu(user_id, menu_id);
    	if (flag)
		{
			// 该权限已经存在
    		response.getWriter().print(getRespJSONString("2"));
    		return;
		}
    	else
    	{
    		flag = Admin_AdminUser_DAO.addUserToMenu(user_id, menu_id);
    		if (flag)
    		{
    			Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "9", 
    					"添加后台 用户 [<a target=\"_blank\" style=\"color: #1F92FF\" href=\"" + request.getContextPath() + "/ad/admin_user_manager?actionmethod=showUpdateAdminUserMenuListJSP&user_id=" + user_id + "&realname=" + user_realname + "\">" + user_realname + "</a>] 权限 [" + menu_title + "]",
    					"1");
    			// 添加成功
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
    	
    }
    
    /**
     * 删除用户菜单权限
     * @throws Exception
     */
    public void delMenuToUser(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String user_id = request.getParameter("user_id");
    	String user_realname = request.getParameter("user_realname");
    	if (user_realname != null) 
		{  
    		user_realname = new String(user_realname.getBytes("iso-8859-1"), "utf-8");
		} 
    	String menu_id = request.getParameter("menu_id");
    	String menu_title = request.getParameter("menu_title");
    	
    	boolean flag = Admin_AdminUser_DAO.delUserToMenu(user_id, menu_id);
    	if (flag)
		{
    		Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "9", 
    				"删除后台 用户 [<a target=\"_blank\" style=\"color: #1F92FF\" href=\"" + request.getContextPath() + "/ad/admin_user_manager?actionmethod=showUpdateAdminUserMenuListJSP&user_id=" + user_id + "&realname=" + user_realname + "\">" + user_realname + "</a>] 权限 [" + menu_title + "]",
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
    
    public static String getRespJSONString(String result) throws JSONException
    {
    	JSONObject respOBJ = new JSONObject();
		respOBJ.put("result",  result);
		return respOBJ.toString();
    }
}
