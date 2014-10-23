package cn.youhui.api.servlet2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cn.youhui.common.Enums;
import cn.youhui.manager.FanliManager;
import cn.youhui.utils.RespStatusBuilder;


@WebServlet("/tyh2/allorder")
public class YHFanLiManagerServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		try 
		{
			String data = request.getParameter("data");
			String type = request.getParameter("type");
			String page = request.getParameter("page");
			String platform = request.getParameter("platform");

			if (data == null || data.equals("")
					|| type == null || type.equals("")
					|| page == null || page.equals(""))
			{
				response.getWriter().print(RespStatusBuilder.message(Enums.ActionStatus.REQUEST_TYPE_ERROR));
				return;
			} 
			else 
			{
				int pageNum = Integer.parseInt(page);
				String result = FanliManager.getResultStr(type, data, pageNum, platform);
				response.getWriter().println(result);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			response.getWriter().print(RespStatusBuilder.message(Enums.ActionStatus.SERVER_ERROR));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		this.doGet(request, response);
	}
}
