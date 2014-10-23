package cn.youhui.itemadd.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

import cn.youhui.bean.AssessOption;
import cn.youhui.itemDAO.AdminAssessOptionDAO;



/**
 * 
 * @author 
 *
 */

@WebServlet("/superDiscount/AssessOptionAction")
public class AdminAssessOptionAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminAssessOptionAction() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String methodName = request.getParameter("actionmethod");
		
		if (null == methodName || "".equals(methodName) || "null".equals(methodName))
		{
			response.getWriter().print("<script>alert('Param actionmethod can not be null！');history.back();</script>");
			return;
		}
		
		try {
			if(methodName.equals("addAssessOption")){
				addAssessOption(request, response);
			}else if(methodName.equals("updateAssessOption")){
				updateAssessOption(request, response);
			}else if(methodName.equals("getAssessOptionById")){
				getAssessOptionById(request, response);
			}else if(methodName.equals("deleteAssessOptionById")){
				deleteAssessOptionById(request, response);
			}else if(methodName.equals("getAssessOptionList")){
				getAssessOptionList(request, response);
			}
		} catch (Exception e){
			response.getWriter().print("<script>alert('server process error！');history.back();</script>");
			return;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	public void addAssessOption(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			
			String isShow = request.getParameter("is_show");
			String content = request.getParameter("content");
			
			AssessOption ao = new AssessOption();
			ao.setIsShow(isShow);
			ao.setContent(content);
			ao.setEnable("1");
			ao.setCreateTime(System.currentTimeMillis());
			
			Boolean flag = AdminAssessOptionDAO.addAssessOption(ao);
			if (flag) {
				response.getWriter().print("success");
			}else {
				response.getWriter().print("false");
			}
	}
	
	public void updateAssessOption(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			
			String id = request.getParameter("id");
			String isShow = request.getParameter("is_show");
			String content = request.getParameter("content");
			
			AssessOption ao = new AssessOption();
			ao.setId(Integer.parseInt(id));
			ao.setIsShow(isShow);
			ao.setContent(content);
			ao.setEnable("1");
			ao.setUpdateTime(System.currentTimeMillis());
			
			Boolean flag = AdminAssessOptionDAO.updateAssessOption(ao);
			if (flag) {
				response.getWriter().print("success");
			}else {
				response.getWriter().print("false");
			}
	}
	
	public void getAssessOptionById(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			
			String id = request.getParameter("id");
			
			AssessOption ao = AdminAssessOptionDAO.getAssessOptionById(id);
			Gson gs = new Gson();
			response.getWriter().print(new JSONObject(gs.toJson(ao, AssessOption.class)).toString());
	}
	
	public void deleteAssessOptionById(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			
			String id = request.getParameter("id");
			
			AssessOption ao = new AssessOption();
			ao.setId(Integer.parseInt(id));
			ao.setEnable("1");
			ao.setUpdateTime(System.currentTimeMillis());
			
			Boolean flag = AdminAssessOptionDAO.deleteAssessOption(id);
			if (flag) {
				response.getWriter().print("success");
			}else {
				response.getWriter().print("false");
			}
	}
	
	public void getAssessOptionList(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			
			List<AssessOption> list = AdminAssessOptionDAO.getAssessOptionList();
			JSONArray ja = new JSONArray();
			Gson gs = new Gson();
			for (int i = 0; i < list.size(); i++) {
				String str = gs.toJson(list.get(i), AssessOption.class);
				ja.put(new JSONObject(str));
			}
			response.getWriter().print(ja);
	}
}
