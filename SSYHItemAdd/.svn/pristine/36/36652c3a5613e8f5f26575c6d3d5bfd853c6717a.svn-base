package cn.youhui.itemadd.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.platform.db.DBManager;

/**
 * 登录
 * @since 2014-03-18
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("t");
		if("login".equals(type)){
			
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			
			int flag = DBManager.Login(username, password);         //登录验证
			
			if(flag>=0){
				Cookie cookie = new Cookie("login", username); 
				cookie.setMaxAge(60*60*1000);
				response.addCookie(cookie);
				response.getWriter().write("success");
			}else{
				response.getWriter().write("fail");
			}
		}else if("logout".equals(type)){   //登出
			Cookie cookie = new Cookie("login", null); 
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			response.sendRedirect("/login.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
