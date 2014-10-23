package com.netting.action.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;

import com.netting.bean.JobDetialBean;
import com.netting.dao.admin.Admin_AdminUser_DAO;
import com.netting.dao.admin.Admin_JobDetail_DAO;

/**
 * 后台任务执行模块管理
 * @author YAOJIANBO
 */
@WebServlet("/ad/jobdetail_manager")
public class Admin_JobDetail_Manager_Action extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 初始化日志
	 */
	public static Log logger = LogFactory.getLog( Admin_JobDetail_Manager_Action.class );
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String methodName = request.getParameter("actionmethod");
		if (null == methodName || "".equals(methodName) || "null".equals(methodName))
		{
			response.getWriter().print("<script>alert('Param actionmethod can not be null！');history.back();</script>");
			return;
		}
		try
		{
			if (methodName.equals("showJobDetailList"))
			{
				showJobDetailList(request, response);
			}
			if (methodName.equals("showJobDetail"))
			{
				showJobDetail(request, response);
			}
			else if (methodName.equals("delJobDetail"))
			{
				delJobDetail(request, response);
			}
		}
		catch (Exception e)
		{
			response.getWriter().print("<script>alert('server process error！');history.back();</script>");
			return;
		}
		
		return;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		this.doGet(req, resp);
	}
	
    /**
     * 管理员访问后台任务执行列表页面
     */
    public void showJobDetailList(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String page_str = request.getParameter("page");
    	String job_username = request.getParameter("job_username");
    	
    	// 默认第一页
    	int page = 1;
    	if (page_str != null && !"".equals(page_str))
    	{
    		page = Integer.parseInt(page_str);
    	}
    	
    	if (job_username == null || "".equals(job_username))
    	{
    		job_username = "all";
    	}
    	
    	ArrayList<JobDetialBean> JobDetailList = Admin_JobDetail_DAO.getJobDetialBeanList(job_username, page);
    	int totalPage = Admin_JobDetail_DAO.getJobDetialBeanListTotalPage(job_username);
    	
    	HashMap<String, String> AdminUserMap = Admin_AdminUser_DAO.getAdminUserMap();
    	AdminUserMap.put("all", "全部");
    	
    	request.setAttribute("AdminUserMap", AdminUserMap);
    	
    	request.setAttribute("JobDetailList", JobDetailList);
    	request.setAttribute("page", page);
		request.setAttribute("totalpage", totalPage);
		
    	request.setAttribute("job_username", job_username);
		
		request.getRequestDispatcher("/admin/job_detail/job_detail_list.jsp").forward(request, response);
		return;
    }
    
    /**
     * 管理员访问后台任务详细页面
     */
    public void showJobDetail(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String page_str = request.getParameter("page");
    	String job_username = request.getParameter("job_username");
    	String job_id = request.getParameter("job_id");
    	
    	if (job_username == null || "".equals(job_username))
    	{
    		job_username = "all";
    	}
    	
    	JobDetialBean JobDetialBean = Admin_JobDetail_DAO.getJobDetialBean(job_id);
    	
    	request.setAttribute("JobDetialBean", JobDetialBean);
    	request.setAttribute("page", page_str);
    	request.setAttribute("job_username", job_username);
		
		request.getRequestDispatcher("/admin/job_detail/job_detail.jsp").forward(request, response);
		return;
    }
    
    /**
     * 管理员访问后台任务执行列表页面
     */
    public void delJobDetail(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String job_id = request.getParameter("job_id");
    	Admin_JobDetail_DAO.delJobDetial(job_id);
    	
    	// 删除成功
		response.getWriter().print(getRespJSONString("0"));
		return;
    }
    
    public static String getRespJSONString(String result) throws JSONException
    {
    	JSONObject respOBJ = new JSONObject();
		respOBJ.put("result",  result);
		return respOBJ.toString();
    }
}
