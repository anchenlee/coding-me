package com.netting.action;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 原始Action，其他Servelt的父类，利用反射调用模拟Structs的方法调用
 * @author YAOJIANBO
 *
 */
public class BaseAction extends HttpServlet 
{

	private static final long serialVersionUID = -2553643592647901686L;
	
	/**
	 * 初始化日志
	 */
	private static Log logger = LogFactory.getLog( BaseAction.class );
	
	protected HttpServletRequest request = null;
	
	protected HttpServletResponse response = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		this.request = req;
		this.response = resp;
		
		this.request.setCharacterEncoding("UTF-8");
		this.response.setCharacterEncoding("UTF-8");
		
		String methodName = request.getParameter("actionmethod");
		if (null == methodName || "".equals(methodName) || "null".equals(methodName))
		{
			response.getWriter().print("<script>alert('Param actionmethod can not be null！');history.back();</script>");
			return;
		}
		
		try 
		{
			invoke(methodName);
		} 
		catch (Exception e) 
		{
			logger.error("BaseAction反射调用错误", e);
			response.getWriter().print("<script>alert('Sorry!!! Invoke Error！');history.back();</script>");
			return;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		this.doGet(req, resp);
	}
	
	protected void invoke(String methodName) throws Exception
	{
		Method method = this.getClass().getMethod(methodName);
		
		method.invoke(this.getClass().cast(this));
	}
}
