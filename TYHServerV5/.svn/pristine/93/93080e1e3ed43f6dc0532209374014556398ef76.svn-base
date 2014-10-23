package cn.youhui.api.servlet3;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.SearchKeywords;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.GetSearchKeywordManager;
import cn.youhui.utils.RespStatusBuilder;

@WebServlet("/tyh3/getsearchkey")
public class YHGetSearchKeywords extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		try {
			
		List<SearchKeywords> list = GetSearchKeywordManager.getSearchKeywordListCategorys("1");
		response.getWriter().write(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, "", fromListToString(list)).toString());
		
//		String result = GetSearchKeywordManager.getSearchKeyword2Result();
//		response.getWriter().write(result);
		} catch (Exception e) {
			response.getWriter().write(RespStatusBuilder.message(ActionStatus.FORBIDDEN).toString());
		}
	}

	public static String fromListToString(List<SearchKeywords> list){
		StringBuffer sb = new StringBuffer();
		sb.append("<cats>");
		if(list != null && list.size() >0){
			for (SearchKeywords bean : list) {
				sb.append(bean.toXML());
			}
		}
		sb.append("</cats>");
		return sb.toString();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		this.doGet(request, response);
	}
	
	

}
