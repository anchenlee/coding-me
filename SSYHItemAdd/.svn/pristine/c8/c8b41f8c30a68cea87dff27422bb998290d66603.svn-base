package cn.youhui.itemadd.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.admin.dao.DelItemUtil;
import cn.youhui.admin.dao.GetItemUtil;
import cn.youhui.bean.TagBean;

/**
 * Servlet implementation class DelTagOrKey
 */
@WebServlet("/deltok")
public class DelTagOrKey extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelTagOrKey() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("type")==null||"".equals(request.getParameter("type"))||request.getParameter("itemId")==null||"".equals(request.getParameter("itemId"))){
			response.getWriter().write("paramException");
			return;
		}
		String id=request.getParameter("id");
		String type=request.getParameter("type");
		String itemId=request.getParameter("itemId");
		if(type.equals("tag")){
			if(DelItemUtil.delTagItem(id, itemId)){
				response.getWriter().write("success");
			}else{
				response.getWriter().write("fail");
			}
		}else if(type.equals("keyword")){
			if(DelItemUtil.delKeywordItem(itemId, id)){
				response.getWriter().write("success");
			}else{
				response.getWriter().write("fail");
			}
		}else if(type.equals("home")){
			if(DelItemUtil.delRecoItem(itemId)){
				response.getWriter().write("success");
			}else{
				response.getWriter().write("fail");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
