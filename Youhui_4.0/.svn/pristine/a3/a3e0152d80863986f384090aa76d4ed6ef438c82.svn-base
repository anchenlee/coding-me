package com.netting.action.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.netting.bean.HotKeywordBean;
import com.netting.dao.admin.AdminHotKeywordDAO;

@WebServlet("/ad/hot_keyword_manager")
public class AdminHotKeywordAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminHotKeywordAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String methodName = request.getParameter("actionmethod");
		if (null == methodName || "".equals(methodName) || "null".equals(methodName)){
			response.getWriter().print("<script>alert('Param actionmethod can not be null！');history.back();</script>");
			return;
		}
		try {
			if (methodName.equals("showHotKeywordList")){
				showHotKeywordList(request, response);
			}else if (methodName.equals("showAddHotKeyword")){
				showAddHotKeyword(request, response);
			}else if (methodName.equals("addHotKeyword")){
				addHotKeyword(request, response);
			}else if (methodName.equals("delHotKeyword")){
				delHotKeyword(request, response);
			}
			
		} catch (Exception e) {
			response.getWriter().print("<script>alert('server process error！');history.back();</script>");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	public void showHotKeywordList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String page = request.getParameter("page");
		int pageInt = 1;
		try {
			pageInt = Integer.parseInt(page);
		} catch (Exception e) {
			pageInt = 1;
		}
		List<HotKeywordBean> list = AdminHotKeywordDAO.getHotKeywordList(pageInt);
		int totalPage = AdminHotKeywordDAO.getHotKeywordListPage();
		request.setAttribute("page", pageInt);
		request.setAttribute("totalpage", totalPage);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/admin/hot_keyword/hot_keyword_list.jsp").forward(request, response);
		return;
	}
	
	public void showAddHotKeyword(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.getRequestDispatcher("/admin/hot_keyword/hot_keyword_add.jsp").forward(request, response);
		return;
	}
	
	public void addHotKeyword(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String keyword = request.getParameter("keyword");
		if(keyword == null || "".equals(keyword)){
			response.getWriter().print(getRespJSONString("1"));
    		return;
		}
		boolean flag = AdminHotKeywordDAO.addHotKeyword(keyword);
		if(flag){
			response.getWriter().print(getRespJSONString("0"));
    		return;
		}else{
			response.getWriter().print(getRespJSONString("1"));
    		return;
		}
	}

	public void delHotKeyword(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String id = request.getParameter("id");
		if(id == null || "".equals(id)){
			response.getWriter().print(getRespJSONString("1"));
    		return;
		}
		boolean flag = AdminHotKeywordDAO.delHotKeyWord(id);
		if(flag){
			response.getWriter().print(getRespJSONString("0"));
    		return;
		}else{
			response.getWriter().print(getRespJSONString("1"));
    		return;
		}
	}
	
	public void movePosition(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		String rank = request.getParameter("rank");
		
		if(type == null || "".equals(type) || id == null || "".equals(id) || rank == null || "".equals(rank)){
			response.getWriter().print(getRespJSONString("1"));
    		return;
		}
		
		
	}
	
    public static String getRespJSONString(String result) throws JSONException
    {
    	JSONObject respOBJ = new JSONObject();
		respOBJ.put("result",  result);
		return respOBJ.toString();
    }
}
