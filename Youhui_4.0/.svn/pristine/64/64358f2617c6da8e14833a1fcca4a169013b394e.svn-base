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

import com.google.gson.Gson;
import com.netting.bean.AdDown;
import com.netting.cache.dao.AdminAdUpCacheDAO;
import com.netting.conf.SysCache;
import com.netting.dao.admin.Admin_AdUp_DAO;
import com.netting.dao.admin.Admin_Tag_DAO;

/**
 * 首页
 * @author belong
 *
 */

@WebServlet("/ad/Admin_AdUp_Manager_Action")
public class Admin_AdUp_Manager_Action extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Admin_AdUp_Manager_Action() {
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
			if(methodName.equals("showAdUpList")){
				showAdUpList(request, response);
			}else if(methodName.equals("updateAdUpPage")){
				updateAdUpPage(request, response);
			}else if(methodName.equals("updateAdUp")){
				updateAdUp(request, response);
			}else if(methodName.equals("delAdUp")){
				delAdUp(request, response);
			}else if(methodName.equals("movePosition")){
				movePosition(request, response);
			}
		} catch (Exception e){
			response.getWriter().print("<script>alert('server process error！');history.back();</script>");
			return;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	
	public void showAdUpList(HttpServletRequest request, HttpServletResponse response) throws Exception{
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
		List<AdDown> list = Admin_AdUp_DAO.getAdsList(p);
		int totalPage = Admin_AdUp_DAO.getAdsListPage();
		request.setAttribute("ActionTypeMap", SysCache.ActionTypeMap);
		request.setAttribute("list", list);
		request.setAttribute("page", page);
		request.setAttribute("totalpage", totalPage);
		
		request.getRequestDispatcher("/admin/ads_new/ad_up_list.jsp").forward(request, response);
		return;
	}
	
	public void updateAdUpPage(HttpServletRequest request, HttpServletResponse response)throws Exception{
		String id = request.getParameter("id");
		AdDown ad = null;
		if(id != null && !"".equals(id)){
			ad = Admin_AdUp_DAO.getAds(id);
			request.setAttribute("ADBean", ad);
		}
		request.setAttribute("ActionTypeMap", SysCache.ActionTypeMap);
		request.setAttribute("XTYM_Map", SysCache.XTYM_Map);
		ArrayList<HashMap<String, String>> Tag_Type_Value_Map = Admin_Tag_DAO.getTagActionType_Value_Map("538");
		request.setAttribute("Tag_Type_Value_Map", Tag_Type_Value_Map);
		
		request.getRequestDispatcher("/admin/ads_new/ad_up_update.jsp").forward(request, response);
		return;
	}
	
	public void updateAdUp(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String id = request.getParameter("id");
		String img = request.getParameter("img");
		String title = request.getParameter("title");
		String actionType = request.getParameter("action_type");
		String actionValue = request.getParameter("action_value");
		if(title != null && !"".equals(title)){
//			title = new String(title.getBytes("iso-8859-1"), "utf-8");
		}
		AdDown ad = new AdDown(img, title, actionType, actionValue);
		boolean flag = false;
		if(id == null || "".equals(id)){
			id = Admin_AdUp_DAO.addAds(ad);
			if(id != null && !"".equals(id)){
				flag = true;
			}
		}else{
			ad.setId(id);
			flag = Admin_AdUp_DAO.updateAds(ad);
		}
		if(flag){
			ad = Admin_AdUp_DAO.getAds(id);
			Gson g = new Gson();
			String content = g.toJson(ad);
			
			AdminAdUpCacheDAO.delAdUp(ad.getRank()+"");
			AdminAdUpCacheDAO.addAdUp(content, ad.getRank());
			response.getWriter().print(getRespJSONString("0"));
			return;
//			showAdUpList(request, response);
		}else{
			response.getWriter().print(getRespJSONString("1"));
			return;
//			response.getWriter().print("<script>alert('server process error！');history.back();</script>");
//			return;
		}
	}
	
	public void delAdUp(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String id = request.getParameter("id");
		boolean flag = false;
		if(id != null && !"".equals(id)){
			flag = Admin_AdUp_DAO.delAds(id);
		}
		if(flag){
			AdDown ad = Admin_AdUp_DAO.getAds(id);
			AdminAdUpCacheDAO.delAdUp(ad.getRank()+"");
			showAdUpList(request, response);
		}else{
			response.getWriter().print("<script>alert('server process error！');history.back();</script>");
			return;
		}
	}
	
	public void movePosition(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		String rank = request.getParameter("rank");
		if(id == null || "".equals(id) || type == null || "".equals(type) || rank == null || "".equals(rank)){
			response.getWriter().print(getRespJSONString("1"));
			return;
		}
		int rankInt = 0;
		try {		
			rankInt = Integer.parseInt(rank);
		} catch (Exception e) {
			response.getWriter().print(getRespJSONString("1"));
			return;
		}
		boolean flag = false;
		AdDown adDown = Admin_AdUp_DAO.getAds(id);
		if(type.equals("1")){	//上移
			AdDown nextAdDown = Admin_AdUp_DAO.getNextAdDown(rankInt);
			boolean flag1 = Admin_AdUp_DAO.updateRank(adDown.getId(), nextAdDown.getRank());
			boolean flag2 = Admin_AdUp_DAO.updateRank(nextAdDown.getId(), adDown.getRank());
			flag = flag1 && flag2;
			if(flag){
				AdminAdUpCacheDAO.delAdUp(adDown.getRank()+"");
				AdminAdUpCacheDAO.delAdUp(nextAdDown.getRank()+"");
				Gson g = new Gson();
				String content = g.toJson(adDown);
				AdminAdUpCacheDAO.addAdUp(content, nextAdDown.getRank());
				
				content = g.toJson(nextAdDown);
				AdminAdUpCacheDAO.addAdUp(content, adDown.getRank());				
			}
		}else if(type.equals("2")){	//首位
			int max = Admin_AdUp_DAO.getMaxRank();
			flag = Admin_AdUp_DAO.updateRank(id, max);
			if(flag){
				AdminAdUpCacheDAO.delAdUp(adDown.getRank()+"");
				adDown.setRank(max);
				Gson g = new Gson();
				String content = g.toJson(adDown);
				AdminAdUpCacheDAO.addAdUp(content, max);
			}
			
		}else if(type.equals("3")){	//下移
			AdDown preAdDown = Admin_AdUp_DAO.getPreAdDown(rankInt);
			boolean flag1 = Admin_AdUp_DAO.updateRank(adDown.getId(), preAdDown.getRank());
			boolean flag2 = Admin_AdUp_DAO.updateRank(preAdDown.getId(), adDown.getRank());
			
			flag = flag1 && flag2;
			
			if(flag){
				AdminAdUpCacheDAO.delAdUp(adDown.getRank()+"");
				AdminAdUpCacheDAO.delAdUp(preAdDown.getRank()+"");
				Gson g = new Gson();
				String content = g.toJson(adDown);
				AdminAdUpCacheDAO.addAdUp(content, preAdDown.getRank());
				
				content = g.toJson(preAdDown);
				AdminAdUpCacheDAO.addAdUp(content, adDown.getRank());
			}
			
		}else if(type.equals("4")){	//末尾
			int min = Admin_AdUp_DAO.getMinRank();
			flag = Admin_AdUp_DAO.updateRank(id, min);
			if(flag){
				AdminAdUpCacheDAO.delAdUp(adDown.getRank()+"");
				adDown.setRank(min);
				Gson g = new Gson();
				String content = g.toJson(adDown);
				AdminAdUpCacheDAO.addAdUp(content, min);
			}
		}
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
