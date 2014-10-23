package com.netting.action.admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.json.JSONException;
import org.json.JSONObject;

import com.netting.bean.Activity_Bean;
import com.netting.bean.TaoBaoUserBean;
import com.netting.conf.SysCache;
import com.netting.dao.admin.Admin_Activity_DAO;
import com.netting.dao.admin.Admin_OPT_Log_DAO;
import com.netting.dao.admin.Admin_SendJFB_DAO;
import com.netting.util.CodeUtil;

/**
 * 活动模块管理
 * @author YAOJIANBO
 */
@WebServlet("/ad/activity_manager")
public class Admin_Activity_Manager_Action extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 初始化日志
	 */
	public static Log logger = LogFactory.getLog( Admin_Activity_Manager_Action.class );
	
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
			if (methodName.equals("showUpdateActivityJSP"))
			{
				showUpdateActivityJSP(request, response);
			}
			else if (methodName.equals("updateActivity"))
			{
				updateActivity(request, response);
			}
			else if (methodName.equals("showAddActivityJSP"))
			{
				showAddActivityJSP(request, response);
			}
			else if (methodName.equals("addActivity"))
			{
				addActivity(request, response);
			}
			else if (methodName.equals("showActivityList"))
			{
				showActivityList(request, response);
			}
			else if (methodName.equals("showSendJFBJSP"))
			{
				showSendJFBJSP(request, response);
			}
			else if (methodName.equals("sendJFB"))
			{
				sendJFB(request, response);
			}
			else if (methodName.equals("sendListJFB"))
			{
				sendListJFB(request, response);
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
	 * 访问编辑页面
	 * @throws Exception
	 */
	public void showUpdateActivityJSP(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String activity_id = request.getParameter("activity_id");
		String page = request.getParameter("page");
		Activity_Bean bean = Admin_Activity_DAO.getActivityBean(activity_id);
		
		request.setAttribute("Activity_bean", bean);
		request.setAttribute("page", page);
		
		request.setAttribute("ActivityRuleMap", SysCache.ActivityRuleMap);
		request.setAttribute("ActivityFrequencyMap", SysCache.ActivityFrequencyMap);
		
		request.getRequestDispatcher("/admin/activity_manager/activity_update.jsp").forward(request, response);
		return;
	}
	
	/**
	 * 编辑活动提交
	 * @throws Exception
	 */
	public void updateActivity(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String activity_id = request.getParameter("activity_id");
		String key = request.getParameter("key");
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String jfbNum = request.getParameter("jfbNum");
		String frequency = request.getParameter("frequency");
		String rule = request.getParameter("rule");
		String start_time = request.getParameter("start_time");
		String end_time = request.getParameter("end_time");
		
		try
		{
			start_time = String.valueOf(CodeUtil.getTimeMillis_2(start_time));
			end_time = String.valueOf(CodeUtil.getTimeMillis_2(end_time));
		}
		catch (Exception e)
		{
			// 开始日期和结束日期格式错误，返回：2
    		response.getWriter().print(getRespJSONString("2", null));
    		return;
		}
		
		Activity_Bean bean = new Activity_Bean();
		
		bean.setId(activity_id);
		bean.setKey(key);
		bean.setName(name);
		bean.setDescription(description);
		bean.setJfbNum(Integer.parseInt(jfbNum));
		bean.setFrequency(Integer.parseInt(frequency));
		bean.setRule(rule);
		bean.setStartTime(start_time);
		bean.setEndTime(end_time);
		bean.setTimestamp(String.valueOf(System.currentTimeMillis()));
		
		
		boolean flag = Admin_Activity_DAO.updateActivity(bean);
		if (flag)
		{
			Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "6", 
					"修改活动，名称[<a target=\"_blank\" style=\"color: #1F92FF\" href=\"" + request.getContextPath() + "/ad/activity_manager?actionmethod=showUpdateActivityJSP&activity_id=" + activity_id + "\">" + name + "</a>]", 
					"3");
			// 更新成功，返回：0
    		response.getWriter().print(getRespJSONString("0", null));
    		return;
		}
		else
		{
			// 数据库更新失败，返回：1
    		response.getWriter().print(getRespJSONString("1", null));
    		return;
		}
		
	}
	
	/**
	 * 访问新增活动页面
	 * @throws Exception
	 */
	public void showAddActivityJSP(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String page = request.getParameter("page");
		
		request.setAttribute("page", page);
		request.setAttribute("ActivityRuleMap", SysCache.ActivityRuleMap);
		request.setAttribute("ActivityFrequencyMap", SysCache.ActivityFrequencyMap);
		
		request.getRequestDispatcher("/admin/activity_manager/activity_add.jsp").forward(request, response);
		return;
	}
	
	/**
	 * 新增活动提交
	 * @throws Exception
	 */
	public void addActivity(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String key = Admin_Activity_DAO.getKey();
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String jfbNum = request.getParameter("jfbNum");
		String frequency = request.getParameter("frequency");
		String rule = request.getParameter("rule");
		String start_time = request.getParameter("start_time");
		String end_time = request.getParameter("end_time");
		
		try
		{
			start_time = String.valueOf(CodeUtil.getTimeMillis_2(start_time));
			end_time = String.valueOf(CodeUtil.getTimeMillis_2(end_time));
		}
		catch (Exception e)
		{
			// 开始日期和结束日期格式错误，返回：2
    		response.getWriter().print(getRespJSONString("2", null));
    		return;
		}
		
		Activity_Bean bean = new Activity_Bean();
		
		String activity_id = Admin_Activity_DAO.getActivityNextID();
		bean.setId(activity_id);
		bean.setKey(key);
		bean.setName(name);
		bean.setDescription(description);
		bean.setJfbNum(Integer.parseInt(jfbNum));
		bean.setFrequency(Integer.parseInt(frequency));
		bean.setRule(rule);
		bean.setStartTime(start_time);
		bean.setEndTime(end_time);
		bean.setTimestamp(String.valueOf(System.currentTimeMillis()));
		
		boolean flag = Admin_Activity_DAO.addActivity(bean);
		if (flag)
		{
			Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "6",
					"新增活动，名称[<a target=\"_blank\" style=\"color: #1F92FF\" href=\"" + request.getContextPath() + "/ad/activity_manager?actionmethod=showUpdateActivityJSP&activity_id=" + activity_id + "\">" + name + "</a>]", 
					"1");
			
			// 更新成功，返回：0
    		response.getWriter().print(getRespJSONString("0", null));
    		return;
		}
		else
		{
			// 数据库更新失败，返回：1
    		response.getWriter().print(getRespJSONString("1", null));
    		return;
		}
	}

    /**
     * 管理员访问活动列表页面
     */
    public void showActivityList(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String keyword = request.getParameter("keyword");
    	String page_str = request.getParameter("page");
    	
    	// 默认第一页
    	int page = 1;
    	if (page_str != null && !"".equals(page_str))
    	{
    		page = Integer.parseInt(page_str);
    	}
    	
    	ArrayList<Activity_Bean> ActivityList = Admin_Activity_DAO.getActivityList(page, keyword);
    	int totalPage = Admin_Activity_DAO.getActivityListTotal(keyword);
    	
    	request.setAttribute("ActList", ActivityList);
    	request.setAttribute("ActivityFrequencyMap", SysCache.ActivityFrequencyMap);
		request.setAttribute("page", page);
		request.setAttribute("totalpage", totalPage);
		
		request.getRequestDispatcher("/admin/activity_manager/activity_list.jsp").forward(request, response);
		return;
    }
    
    /**
	 * 访问发送集分宝页面
	 * @throws Exception
	 */
	public void showSendJFBJSP(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String activity_id = request.getParameter("activity_id");
		String page = request.getParameter("page");
		
		request.setAttribute("page", page);
		request.setAttribute("activity_id", activity_id);
		
		request.getRequestDispatcher("/admin/activity_manager/send_jfb.jsp").forward(request, response);
		return;
	}
	
	/**
	 * 发送集分宝
	 * @throws Exception
	 */
	public void sendJFB(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String taobao_nick = request.getParameter("taobao_nick");
		String activity_id = request.getParameter("activity_id");
		String activity_name = request.getParameter("activity_name");
		String jfb_num = request.getParameter("jfb_num");
		
		Activity_Bean activity = new Activity_Bean();
		activity.setId(activity_id);
		activity.setName(activity_name);
		activity.setJfbNum(Integer.parseInt(jfb_num));
		
		String respContent = "";
		String notExistTaobaoNick = "";
		String failTaobaoNick = "";
		String nicks[] = taobao_nick.split(",");
		for (String nick : nicks)
		{
			String uid = null;
			TaoBaoUserBean user = Admin_SendJFB_DAO.getTaoBaoUserBean(nick);
			if (null != user)
			{
				uid = user.getId();
			}
			if (uid != null && !"".equals(uid))
			{
				int checkResp = Admin_SendJFB_DAO.checkUserToActivity(uid, activity_id);
				
				if(checkResp == 5)
				{
					boolean flag = Admin_SendJFB_DAO.joinActivity(activity, uid, 0);
    		    	if(!flag)
    		    	{
    		    		failTaobaoNick = failTaobaoNick + "," + nick;
    		    	}
				}
				else if(checkResp == 1)
				{
					respContent = "活动还未开始;";
					break;
				}
				else if(checkResp == 2)
				{
					respContent = "活动已结束;";
					break;
				}
				else if(checkResp == 3)
				{
					respContent = respContent + ";" + nick + " 已经参加过此活动";
					continue;
				}
				else if(checkResp == 4)
				{
					respContent = respContent + ";" + nick + " 不被允许参加此活动";
					continue;
				}
			}
			else
			{
				notExistTaobaoNick = notExistTaobaoNick + "," + nick;
				continue;
			}
		}
		
		if (!failTaobaoNick.equals(""))
		{
			respContent = respContent + ";发送失败的淘宝用户:" + failTaobaoNick + ";";
		}
		if (!notExistTaobaoNick.equals(""))
		{
			respContent = respContent + "不存在的淘宝用户:" + notExistTaobaoNick + ";";
		}
		
		Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "6", 
				"发送集分宝，淘宝用户名[" + taobao_nick + "]，活动名称[<a target=\"_blank\" style=\"color: #1F92FF\" href=\"" + request.getContextPath() + "/ad/activity_manager?actionmethod=showUpdateActivityJSP&activity_id=" + activity_id + "\">" + activity_name + "</a>],集分宝数[" + jfb_num + "]", 
				"4");
		// 更新成功，返回：0
		response.getWriter().print(getRespJSONString("0", respContent));
		return;
	}
	
	/**
	 * 批量发送集分宝
	 * @throws Exception
	 */
	public void sendListJFB(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String respContent = "";
		
		DiskFileItemFactory dfif = new DiskFileItemFactory(1000, new File("D://tmp"));
		ServletFileUpload upload = new ServletFileUpload(dfif);
		List<FileItem> fileList = upload.parseRequest((RequestContext) request);
		Iterator<FileItem> it = fileList.iterator();
		while (it.hasNext())
		{
			FileItem fi = it.next();
			if (!fi.isFormField())
			{
				respContent = Admin_SendJFB_DAO.sendMultJFB(fi.getInputStream());
			}
		}
		
		Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "6", 
				"批量发送集分宝", "4");
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(respContent);
		return;
	}
    
    public static String getRespJSONString(String result, String content) throws JSONException
    {
    	JSONObject respOBJ = new JSONObject();
		respOBJ.put("result",  result);
		if (content != null)
		{
			respOBJ.put("content",  content);
		}
		return respOBJ.toString();
    }
}
