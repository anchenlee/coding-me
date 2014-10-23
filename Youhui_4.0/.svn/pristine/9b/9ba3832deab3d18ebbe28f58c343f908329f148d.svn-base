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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.netting.bean.AdDown;
import com.netting.conf.SysCache;
import com.netting.dao.admin.Admin_AdDown_DAO;
import com.netting.dao.admin.Admin_Tag_DAO;

@WebServlet("/ad/Admin_Ads_Manager_Action_New")
public class Admin_AdDown_Manager_Action extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
public static Log logger = LogFactory.getLog( Admin_AdDown_Manager_Action.class );
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String methodName = request.getParameter("actionmethod");
		
		if (null == methodName || "".equals(methodName) || "null".equals(methodName))
		{
			response.getWriter().print("<script>alert('Param actionmethod can not be null！');history.back();</script>");
			return;
		}
		
		try {
			if(methodName.equals("showAdsList")){
				showAdsList(request, response);
			}else if(methodName.equals("updateAdsPage")){
				updateAdsPage(request, response);
			}else if(methodName.equals("updateAds")){
				updateAds(request, response);
			}else if(methodName.equals("delAds")){
				delAds(request, response);
			}
		} catch (Exception e){
			response.getWriter().print("<script>alert('server process error！');history.back();</script>");
			return;
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		this.doGet(req, resp);
	}
	
	public void showAdsList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String page = request.getParameter("page");
		if(page == null || page.equals("")){
			page = "1";
		}
		int p = 1;
		try {
			p = Integer.parseInt(page);
		} catch (Exception e) {
			p = 1;
		}
		List<AdDown> list = Admin_AdDown_DAO.getAdsList(p);
		int totalPage = Admin_AdDown_DAO.getAdsListPage();
		request.setAttribute("ActionTypeMap", SysCache.ActionTypeMap);
		request.setAttribute("list", list);
		request.setAttribute("page", page);
		request.setAttribute("totalpage", totalPage);
		
		request.getRequestDispatcher("/admin/ads_new/ads_new_list.jsp").forward(request, response);
		return;
		
	}
	
	public void updateAdsPage(HttpServletRequest request, HttpServletResponse response)throws Exception{
		String id = request.getParameter("id");
		AdDown ad = null;
		if(id != null && !"".equals(id)){
			ad = Admin_AdDown_DAO.getAds(id);
			request.setAttribute("ADBean", ad);
		}
		request.setAttribute("ActionTypeMap", SysCache.ActionTypeMap);
		request.setAttribute("XTYM_Map", SysCache.XTYM_Map);
		ArrayList<HashMap<String, String>> Tag_Type_Value_Map = Admin_Tag_DAO.getTagActionType_Value_Map("538");
		request.setAttribute("Tag_Type_Value_Map", Tag_Type_Value_Map);
		
		request.getRequestDispatcher("/admin/ads_new/ads_new_update.jsp").forward(request, response);
		return;
	}
	
	public void updateAds(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String id = request.getParameter("id");
		String img = request.getParameter("img");
		String title = request.getParameter("title");
		String actionType = request.getParameter("action_type");
		String actionValue = request.getParameter("action_value");
		if(title != null && !"".equals(title)){
			title = new String(title.getBytes("iso-8859-1"), "utf-8");
		}
		AdDown ad = new AdDown(img, title, actionType, actionValue);
		boolean flag = false;
		if(id == null || "".equals(id)){
			flag = Admin_AdDown_DAO.addAds(ad);
		}else{
			ad.setId(id);
			flag = Admin_AdDown_DAO.updateAds(ad);
		}
		if(flag){
			showAdsList(request, response);
		}else{
			response.getWriter().print("<script>alert('server process error！');history.back();</script>");
			return;
		}
	}
	
	public void delAds(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String id = request.getParameter("id");
		boolean flag = false;
		if(id != null && !"".equals(id)){
			flag = Admin_AdDown_DAO.delAds(id);
		}
		if(flag){
			showAdsList(request, response);
		}else{
			response.getWriter().print("<script>alert('server process error！');history.back();</script>");
			return;
		}
	}
}
