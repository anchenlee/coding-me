package cn.youhui.api.servlet3;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.PushMessage;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.PushMsgManager;
import cn.youhui.utils.RespStatusBuilder;



@WebServlet("/tyh3/pushmsglist")
public class YHGetPushMsgList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String access = request.getParameter("access_token");
		String page = request.getParameter("page");
		String uid = request.getParameter("uid");
		String platform = request.getParameter("platform");
		String version_code = request.getParameter("version_code");
		
		if(uid == null)
		{
			uid = "";
		}
		if(access == null)
		{
			access = "";
		}
		if(page == null || "".equals(page))
		{
			page = "1";
		}
		int pageInt = Integer.parseInt(page);
		int pageCount = PushMsgManager.getInstance().getPageNum(uid, platform);
		if(pageCount == 0) 
		{
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());
			return;
		}
		if(pageInt < 1) 
		{
			pageInt = 1;
		}
		else if(pageInt > pageCount) 
		{
			pageInt = pageCount;
		}
		
		List<PushMessage> list = PushMsgManager.getInstance().getPushMsgList(uid, pageInt, platform, version_code);
		
		if(list != null && list.size() > 0)
		{
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, access, pageCount, pageInt, changetoXml(list)));
		}
		else
		{
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());
		}
	}
	
	private String changetoXml(List<PushMessage> list)
	{
		StringBuffer sb = new StringBuffer();
		if(list != null && list.size() > 0)
		{
			sb.append("<pushmsgs>");
			for(PushMessage bean : list)
			{
				sb.append(bean.toXMLNew());
			}
			sb.append("</pushmsgs>");
		}
		return sb.toString();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
