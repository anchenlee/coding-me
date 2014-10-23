package com.netting.action.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.Gson;
import com.netting.bean.AdminUser;
import com.netting.dao.admin.AdminUser_DAO;
import com.netting.redis.executor.JedisKeyManager;

/**
 * 后台用户登录
 * @author YAOJIANBO
 *
 */
@WebServlet("/AdminLoginAction")
public class AdminLoginAction extends HttpServlet 
{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 初始化日志
	 */
	public static Log logger = LogFactory.getLog( AdminLoginAction.class );
	
	private static String admin_user_key = "youhui_4_admin_user_key_";
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String actionmethod = request.getParameter("actionmethod");
		if (null == actionmethod || "".equals(actionmethod) || "null".equals(actionmethod))
		{
			response.getWriter().print("<script>alert('Param actionmethod can not be null！');history.back();</script>");
			return;
		}
		try
		{
		if(actionmethod.equals("login"))
		{
			login(request,response);
		}
		else if(actionmethod.equals("logout"))
		{
			logout(request,response);
		}
		}
		catch (Exception e)
		{
			response.getWriter().print("<script>alert('server process error！');history.back();</script>");
			return;
		}
	}
	
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/**
	 * 后台管理用户登录
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws Exception
	 */
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
    	String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	
    	if(username == null || "".equals(username) || password == null || "".equals(password)) 
    	{
    		response.sendRedirect("./admin/login.jsp");
    		return;
    	}
    	
    	AdminUser adminUser = AdminUser_DAO.checkAdminUser(username, password);
    	if (adminUser != null) 
    	{
    		AdminUser_DAO.updateUserLoginTime(username);
    		
    		Gson gson = new Gson();
    		
    		Cookie cookie = new Cookie("AdminUserName", adminUser.getUsername()); 
    		cookie.setMaxAge(24 * 60 * 60);
    		cookie.setPath("/");
			response.addCookie(cookie);
			
			JedisKeyManager.set(admin_user_key + adminUser.getUsername(), gson.toJson(adminUser));
			JedisKeyManager.expire(admin_user_key + adminUser.getUsername(), 30 * 60);
			
    		response.sendRedirect("./admin/index.jsp");
    		return;
    	}
    	else 
		{
			response.sendRedirect("./admin/login.jsp");
		}
    }

	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String adminUserNameKey = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) 
		{
			for(int i = 0; i < cookies.length; i++)
			{
				if (cookies[i].getName().equals("AdminUserName"))
				{
					adminUserNameKey = cookies[i].getValue();
				}
			}
		}
		
		if (adminUserNameKey != null && !adminUserNameKey.equals("") && !adminUserNameKey.equals("null"))
		{
			JedisKeyManager.del(admin_user_key + adminUserNameKey);
		}
		
		Cookie adminUserCookie = new Cookie("AdminUserName", null); 
		adminUserCookie.setMaxAge(0);
		adminUserCookie.setPath("/");
		response.addCookie(adminUserCookie);
		
		response.sendRedirect("./admin/login.jsp");
	}
	
	public static boolean check(HttpServletRequest request) 
	{
		boolean flag = false;
		
		String adminUserNameKey = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) 
		{
			for(int i = 0; i < cookies.length; i++)
			{
				if (cookies[i].getName().equals("AdminUserName"))
				{
					adminUserNameKey = cookies[i].getValue();
				}
			}
		}
		else
		{
			return false;
		}
		
		if (adminUserNameKey == null || adminUserNameKey.equals("") || adminUserNameKey.equals("null"))
		{
			return false;
		}
		else
		{
			String admin_user_json = JedisKeyManager.get(admin_user_key + adminUserNameKey);
			if (admin_user_json == null || admin_user_json.equals(""))
			{
				return false;
			}
			else
			{
				Gson gson = new Gson();
				AdminUser adminUser = gson.fromJson(admin_user_json, AdminUser.class);
				request.setAttribute("AdminUser", adminUser);
				
				JedisKeyManager.expire(admin_user_key + adminUser.getUsername(), 30 * 60);
				flag = true;
			}
		}
		
		return flag;
	}
	
	public static AdminUser getAdminUserFromCookie(HttpServletRequest request)
	{
		AdminUser adminUser = null;
		
		String adminUserNameKey = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) 
		{
			for(int i = 0; i < cookies.length; i++)
			{
				if (cookies[i].getName().equals("AdminUserName"))
				{
					adminUserNameKey = cookies[i].getValue();
				}
			}
			
			if (adminUserNameKey == null || adminUserNameKey.equals("") || adminUserNameKey.equals("null"))
			{
				return null;
			}
			else
			{
				String admin_user_json = JedisKeyManager.get(admin_user_key + adminUserNameKey);
				if (admin_user_json == null || admin_user_json.equals(""))
				{
					return null;
				}
				else
				{
					Gson gson = new Gson();
					adminUser = gson.fromJson(admin_user_json, AdminUser.class);
					
					return adminUser;
				}
			}
		}
		else
		{
			return null;
		}
	}
	

}
