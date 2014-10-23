package cn.youhui.api.servlet3;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.youhui.bean.SearchKeywords;
import cn.youhui.bean.SuiShouAction;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.GetSearchKeywordManager;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhui.utils.SuiShouActionUtil;


@WebServlet("/tyh3/getsearchkeychild")
public class YHGetSearchKeywordChildren extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String catId = request.getParameter("catid");
		String platform = request.getParameter("platform");
		if ("".equals(platform) || platform == null) {
			platform = "andriod";
		}
		try {
			
		List<SearchKeywords> list = GetSearchKeywordManager.getSearchKeywordsList(catId);
		response.getWriter().write(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, "", listToString(list,platform)).toString());
		return;
		} catch (Exception e) {
			response.getWriter().write(RespStatusBuilder.message(ActionStatus.FORBIDDEN).toString());
		}
		
		
//		String result = GetSearchKeywordManager.getSearchKeywordChildrenResult(catId);
//		response.getWriter().write(result);
	}

	public static String listToString(List<SearchKeywords> list,String platform){
		StringBuffer sb = new StringBuffer();
		sb.append("<cats>");
		if(list != null){
			for(SearchKeywords tag : list)
			{		
				sb.append("<cat>")
				.append("<name><![CDATA["+tag.getName()+"]]></name>")
				.append("<icon><![CDATA["+tag.getIcon()+"]]></icon>");
				List<SearchKeywords> clist = GetSearchKeywordManager.getSearchKeywordsList(tag.getId());
				if(clist != null){
					for(SearchKeywords keyword : clist)
					{		
						sb.append("<keyword>")
						.append("<name><![CDATA["+keyword.getName()+"]]></name>")
						.append("<icon><![CDATA["+keyword.getIcon()+"]]></icon>")
						.append(new SuiShouAction(SuiShouActionUtil.getSuiShouActionUrl(platform,keyword.getAction().getActionType(),new String[]{keyword.getAction().getActionValue(),keyword.getName()})).toXML())
						.append("</keyword>");
					}
				}
				sb.append("</cat>");
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
