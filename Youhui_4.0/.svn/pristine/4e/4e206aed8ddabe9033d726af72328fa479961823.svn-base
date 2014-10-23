package com.netting.action.admin;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台管理的路径过滤器
 */
@WebFilter(filterName="AdminFilter",urlPatterns="/ad/*")
public class AdminFilter implements Filter 
{

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException 
	{
		
	}
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException 
	{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		if (!(AdminLoginAction.check(req))) 
		{
			resp.sendRedirect(req.getContextPath()+"/admin/login.jsp?");
			return;
		}
		
		chain.doFilter(req, resp);
	}
	
	@Override
	public void destroy() 
	{
		
	}
}
