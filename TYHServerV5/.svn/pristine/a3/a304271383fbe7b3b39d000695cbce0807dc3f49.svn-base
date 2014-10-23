package cn.youhui.api.servlet2.professor;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.ProfCategory;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.ProfCategoryDAO;
import cn.youhui.utils.RespStatusBuilder;

/**
 * 随手达人 达人分类接口
 * @author YAOJIANBO
 *
 */
@WebServlet("/tyh2/profcats")
public class YHGetCategory extends HttpServlet 
{
	// private static final Logger log = Logger.getLogger(YHGetGift.class);
	
	private static final long serialVersionUID = 1L;

	public YHGetCategory() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		int page = 1;
		String page_str = request.getParameter("page");
		if(page_str != null && !page_str.equals("") && !page_str.equalsIgnoreCase("null"))
		{
			try{
				page = Integer.parseInt(page_str);
			}catch (Exception e){
				page = 1;
			}
		}
		
		ArrayList<ProfCategory> resultList = ProfCategoryDAO.getCategoryList(page);
		int totalPage = ProfCategoryDAO.getCategoryTotal();
		
		if(resultList != null && resultList.size() > 0){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED , "", totalPage, page ,toResult(resultList)));
		}else{
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		doGet(request, response);
	}
	
	private String toResult(ArrayList<ProfCategory> resultList){
		StringBuffer sb = new StringBuffer();
		if(resultList != null && resultList.size() > 0){
			sb.append("<prof_categorys>");
			for(ProfCategory bean : resultList){
				sb.append(bean.toXML());
			}
			sb.append("</prof_categorys>");
		}
		return sb.toString();
	}
}
