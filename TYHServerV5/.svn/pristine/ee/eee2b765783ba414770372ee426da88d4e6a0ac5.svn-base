package cn.youhui.api.servlet2;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.Sale;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.ramdata.RecomCacher;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhui.utils.StringUtils;


/**
 * 推荐
 * @author leejun
 * @since 2013-04-28
 */
@WebServlet("/tyh2/recom")
public class YHGetRecommendList extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String page = request.getParameter("page");
		String uid = request.getParameter("uid");
		String platform = request.getParameter("platform");
		String version_code = request.getParameter("version_code");
		if(uid == null)
		{
			uid = "";
		}
		if(platform == null || platform.equals(""))
		{
			platform = "android";
		}
		int pageint = 1;
		if(StringUtils.isNumeric(page))
		{
			pageint = Integer.parseInt(page);
		}
		List<Sale> list = RecomCacher.getInstance().getRecomList(pageint, uid, platform, version_code);
		int pageCount = RecomCacher.getInstance().getPageNum();
		StringBuffer data = new StringBuffer();
		if(list != null && list.size() > 0)
		{
			data.append("<sales>");
			for(Sale sale : list)
			{
				data.append(sale.toXML());
			}
			data.append("</sales>");
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED ,"",pageCount,pageint,data.toString()).toString());
		}
		else
		{
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT,"").toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
