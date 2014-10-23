package com.netting.action.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.netting.bean.Announcement;
import com.netting.bean.TeJiaGoodsBean;
import com.netting.conf.SysCache;
import com.netting.dao.admin.AdminAnnouncementDAO;
import com.netting.dao.admin.Admin_Tag_DAO;
import com.netting.util.CodeUtil;

@WebServlet("/ad/announcement_action")
public class AdminAnnouncementAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminAnnouncementAction() {
        super();
        // TODO Auto-generated constructor stub
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
			if(methodName.equals("showAnnouncementList")){
				showAnnouncementList(request, response);
			}else if(methodName.equals("showUpdateAnnouncementJSP")){
				showUpdateAnnouncementJSP(request, response);
			}else if(methodName.equals("showAddAnnouncementJSP")){
				showAddAnnouncementJSP(request, response);
			}else if(methodName.equals("updateAnnouncement")){
				updateAnnouncement(request, response);
			}else if(methodName.equals("addAnnouncement")){
				addAnnouncement(request, response);
			}else if(methodName.equals("delAnnouncement")){
				delAnnouncement(request, response);
			}
			
		} catch (Exception e) {
			response.getWriter().print("<script>alert('server process error！');history.back();</script>");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	public void showAnnouncementList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String pageStr = request.getParameter("page");
		String statusStr = request.getParameter("status");
		String type = request.getParameter("type");
		if(type == null || "".equals(type)){
			type = "1";
		}
		int page = 1; int status = 0;
		try {
			page = Integer.parseInt(pageStr);
			status = Integer.parseInt(statusStr);
		} catch (Exception e) {
			page = 1;
			status = 0;
		}
		List<Announcement> list = AdminAnnouncementDAO.getAnnouncementList(page, status,type);
		int count = AdminAnnouncementDAO.getAnnouncementListPage(page, status,type);
		request.setAttribute("list", list);
		request.setAttribute("page", page);
		request.setAttribute("totalpage", count);
		request.setAttribute("status", status);
		request.setAttribute("type", type);
		request.getRequestDispatcher("/admin/announcement_manager/announcement_list.jsp").forward(request, response);
    	return;
	}
	
	public void showUpdateAnnouncementJSP(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String id = request.getParameter("id");
		Announcement bean = AdminAnnouncementDAO.getAnnouncement(id);
		request.setAttribute("bean", bean);
		request.setAttribute("ActionTypeMap", SysCache.ActionTypeMap);
		request.setAttribute("Tag_XTYM_Map", SysCache.XTYM_Map);
		
		ArrayList<HashMap<String, String>> Tag_Type_Value_Map = Admin_Tag_DAO.getTagActionType_Value_Map("538");
		request.setAttribute("Tag_Type_Value_Map", Tag_Type_Value_Map);
		request.setAttribute("ButtonTypeMap", SysCache.ButtonTypeMap);
		request.setAttribute("ButtonCloseTypeMap", SysCache.ButtonCloseTypeMap);
		
		request.getRequestDispatcher("/admin/announcement_manager/announcement_update.jsp").forward(request, response);
    	return;
	}
	
	public void showAddAnnouncementJSP(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setAttribute("ActionTypeMap", SysCache.ActionTypeMap);
		request.setAttribute("Tag_XTYM_Map", SysCache.XTYM_Map);
		
		ArrayList<HashMap<String, String>> Tag_Type_Value_Map = Admin_Tag_DAO.getTagActionType_Value_Map("538");
		request.setAttribute("Tag_Type_Value_Map", Tag_Type_Value_Map);
		request.getRequestDispatcher("/admin/announcement_manager/announcement_add.jsp").forward(request, response);
    	return;
	}
	
	public void updateAnnouncement(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String contentUrl = request.getParameter("content_url");
		String showTypeStr = request.getParameter("show_type");
		String startDate = request.getParameter("start_time");
		String endDate = request.getParameter("end_time");
		String leftActionType = request.getParameter("action_type");
		String leftActionValue = request.getParameter("action_value");
		String rightActionType = request.getParameter("chaye_action_type");
		String rightActionValue = request.getParameter("chaye_action_value");
		String delay_time = request.getParameter("delay_time");
		String leftName = request.getParameter("left_button_name");
		String rightName = request.getParameter("right_button_name");
		long startTime = CodeUtil.getTimeMillis_2(startDate);
		long endTime = CodeUtil.getTimeMillis_2(endDate);
		long delayTime = Long.parseLong(delay_time);
		int showType = Integer.parseInt(showTypeStr);
		String delTypeStr = request.getParameter("del_type");
		int delType = Integer.parseInt(delTypeStr);
		
		Announcement bean = new Announcement(title,contentUrl,showType,leftName,leftActionType,leftActionValue,rightName,rightActionType,rightActionValue,startTime,endTime,delayTime);
		bean.setDelType(delType);
		bean.setId(id);
		boolean flag = AdminAnnouncementDAO.updateAnnouncement(bean);
		if(flag){
			response.getWriter().print(getRespJSONString("0"));
			return;
		}else{
			response.getWriter().print(getRespJSONString("1"));
			return;
		}
	}
	
	public void addAnnouncement(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String title = request.getParameter("title");
		String contentUrl = request.getParameter("content_url");
		String showTypeStr = request.getParameter("show_type");
		String startDate = request.getParameter("start_time");
		String endDate = request.getParameter("end_time");
		String leftActionType = request.getParameter("action_type");
		String leftActionValue = request.getParameter("action_value");
		String rightActionType = request.getParameter("chaye_action_type");
		String rightActionValue = request.getParameter("chaye_action_value");
		String delay_time = request.getParameter("delay_time");
		String leftName = request.getParameter("left_button_name");
		String rightName = request.getParameter("right_button_name");
		long startTime = CodeUtil.getTimeMillis_2(startDate);
		long endTime = CodeUtil.getTimeMillis_2(endDate);
		long delayTime = Long.parseLong(delay_time);
		int showType = Integer.parseInt(showTypeStr);
		String delTypeStr = request.getParameter("del_type");
		int delType = Integer.parseInt(delTypeStr);
		
		Announcement bean = new Announcement(title,contentUrl,showType,leftName,leftActionType,leftActionValue,rightName,rightActionType,rightActionValue,startTime,endTime,delayTime);
		bean.setDelType(delType);
		boolean flag = AdminAnnouncementDAO.addAnnouncement(bean);
		if(flag){
			response.getWriter().print(getRespJSONString("0"));
			return;
		}else{
			response.getWriter().print(getRespJSONString("1"));
			return;
		}
	}
	
	public void delAnnouncement(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String id = request.getParameter("id");
		boolean flag = AdminAnnouncementDAO.delAnnouncement(id);
		if(flag){
			response.getWriter().print(getRespJSONString("0"));
			return;
		}else{
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
