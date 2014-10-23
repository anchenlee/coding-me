package cn.youhui.api.servlet2.professor;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.Professor;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.ProfCategoryDAO;
import cn.youhui.utils.RespStatusBuilder;

/**
 * 随手达人 根据达人分类获取该分类下的达人列表接口
 * @author YAOJIANBO
 *
 */
@WebServlet("/tyh2/professorbycat")
public class YHGetCateProfessor extends HttpServlet 
{
	// private static final Logger log = Logger.getLogger(YHGetGift.class);
	
	private static final long serialVersionUID = 1L;

	public YHGetCateProfessor() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String catID = request.getParameter("catid");
		if (catID == null || "".equals(catID) || "null".equalsIgnoreCase(catID))
		{
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
			return;
		}
		
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
		
		ArrayList<Professor> resultList = ProfCategoryDAO.getProfessorListByCat(page, catID);
		int totalPage = ProfCategoryDAO.getProfessorListTotalByCat(catID);
		
		if(resultList != null && resultList.size() > 0){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED , "", totalPage, page, toResult(resultList, catID)));
		}else{
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		doGet(request, response);
	}
	
	private String toResult(ArrayList<Professor> list, String catID){
		StringBuffer sb = new StringBuffer();
		
		if(catID != null && !"".equals(catID)){
			sb.append("<category>").append(catID).append("</category>");
		}
		if(list != null && list.size() > 0){
			sb.append("<professors>");
			for(Professor bean : list){
				sb.append(bean.toXML());
			}
			sb.append("</professors>");
		}
		return sb.toString();
	}
}
