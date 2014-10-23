package com.netting.action.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.netting.bean.Tag_Bean;
import com.netting.conf.ipadSysCache;
import com.netting.dao.admin.Admin_Ipad_Tag_DAO;
import com.netting.dao.admin.Admin_Tag_DAO;

@WebServlet("/tags/ipad_tags_action")
public class AdminIpadTagsAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminIpadTagsAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String method = request.getParameter("method");
		if (null == method || "".equals(method) || "null".equals(method))
		{
			response.getWriter().print("<script>alert('Param actionmethod can not be null！');history.back();</script>");
			return;
		}
		try {
			if(method.equals("addIpadTags")){
				addIpadTags(request, response);
			}else if (method.equals("updateIpadTags")) {
				updateIpadTags(request, response);
			}else if (method.equals("delIpadTags")) {
				delIpadTags(request, response);
			}else if (method.equals("getTagsList")) {
				getTagsList(request, response);
			}else if(method.equals("getAllMartixLayout")){
				getAllMartixLayout(request, response);
			}else if(method.equals("addMartix")){
				addMartix(request, response);
			}else if(method.equals("delMartix")){
				delMartix(request, response);
			}else if(method.equals("getTagsById")){
				getTagsById(request, response);
			}
			
		} catch (Exception e) {
			response.getWriter().print("<script>alert('server process error！');history.back();</script>");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	public void addIpadTags(HttpServletRequest request, HttpServletResponse response) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		long regularStartTime = 0;
		long regularEndTime = 0;
		String actionType = request.getParameter("action_type");
		String actionValue = request.getParameter("action_value");
		String martix = request.getParameter("martix");
		String divIds = request.getParameter("div_ids");
		String img = request.getParameter("img");
		String regularTimeImg = request.getParameter("regular_time_img");
		String regularStartTimeStr = request.getParameter("regular_start_time");
		String regularEndTimeStr = request.getParameter("regular_end_time");
		if(!"".equals(regularStartTimeStr)){
			regularStartTime = sdf.parse(regularStartTimeStr).getTime();
		}
		if(!"".equals(regularEndTimeStr)){
			regularEndTime = sdf.parse(regularEndTimeStr).getTime();
		}
			
		String x = request.getParameter("x");
		String y = request.getParameter("y");
		Tag_Bean tb = Admin_Tag_DAO.getTagBean(actionValue);
		if ("".equals(img) && tb == null) {
			img = "";
		}else if ("".equals(img) ) {
			if (!"".equals(tb.getPhone_icon())) {
				img = tb.getPhone_icon();
			}
		}
		for (int i = 0; i < divIds.length(); i++) {
			String divs = divIds.substring(i, i+1);
			Admin_Ipad_Tag_DAO.delIpadTag(divs, martix);
		}
		boolean flag = Admin_Ipad_Tag_DAO.addIpadTag(actionType,actionValue, x, y, martix,divIds,img,regularTimeImg,regularStartTime,regularEndTime);
		String id = Admin_Ipad_Tag_DAO.getInstanceInsertId();
		JSONObject jo = new JSONObject();
		jo.put("id", id);
		jo.put("img", img);
		if (flag) {
			response.getWriter().print(jo);
		}else {
			response.getWriter().print("false");
		}
	}
	
	public void updateIpadTags(HttpServletRequest request, HttpServletResponse response) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		long regularStartTime = 0;
		long regularEndTime = 0;
		String actionType = request.getParameter("action_type");
		String actionValue = request.getParameter("action_value");
		String martix = request.getParameter("martix");
		String divIds = request.getParameter("div_ids");
		String img = request.getParameter("img");
		String regularTimeImg = request.getParameter("regular_time_img");
		String regularStartTimeStr = request.getParameter("regular_start_time");
		String regularEndTimeStr = request.getParameter("regular_end_time");
		if(!"".equals(regularStartTimeStr)){
			regularStartTime = sdf.parse(regularStartTimeStr).getTime();
		}
		if(!"".equals(regularEndTimeStr)){
			regularEndTime = sdf.parse(regularEndTimeStr).getTime();
		}
		Tag_Bean tb = Admin_Tag_DAO.getTagBean(actionValue);
		if ("".equals(img) && tb == null) {
			img = "";
		}else if ("".equals(img) ) {
			if (!"".equals(tb.getPhone_icon())) {
				img = tb.getPhone_icon();
			}
		}
		Admin_Ipad_Tag_DAO.updateIpadTag(actionType,actionValue,divIds, martix,img,regularTimeImg,regularStartTime,regularEndTime);
		PrintWriter out  = response.getWriter();
		out.print(img);
	}
	
	public void delIpadTags(HttpServletRequest request, HttpServletResponse response) throws Exception{
	
			String martix = request.getParameter("martix");
			String divIds = request.getParameter("divIds");
			boolean flag = Admin_Ipad_Tag_DAO.delIpadTag(divIds, martix);
			if (flag) {
				response.getWriter().print("success");
			}else {
				response.getWriter().print("false");
			}
		}
	
	public void getTagsList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		JSONArray ja = new JSONArray();
		Map<String, String> ActionTypeMap = ipadSysCache.ActionTypeMap;
		Map<String, String> XTYM_Map = ipadSysCache.XTYM_Map;
		List<HashMap<String, String>> Tag_Type_Value_Map = Admin_Tag_DAO.getTagActionType_Value_Map("538");
		ja.put(ActionTypeMap);
		ja.put(XTYM_Map);
		ja.put(Tag_Type_Value_Map);
		response.getWriter().print(ja.toString());
	}
	
	public void getAllMartixLayout(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String jsonStr = Admin_Ipad_Tag_DAO.getAllMartixLayout();
		response.getWriter().print(jsonStr);
	}
	
	public void addMartix(HttpServletRequest request, HttpServletResponse response) throws Exception{
		boolean flag = true;
		String maxMartixId = Admin_Ipad_Tag_DAO.getMaxMartixId();
		System.out.println(maxMartixId);
		for (int i = 0; i < 6; i++) {
			if (!Admin_Ipad_Tag_DAO.addDefaultDiv(Integer.parseInt(maxMartixId) +1, i + 1)) {
				flag = false;
			}
		}
		if (flag) {
			response.getWriter().print("success");
		}else {
			response.getWriter().print("false");
		}
		
	}
	
	public void delMartix(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String ids = request.getParameter("martixIds");
		String martixIds [] =  ids.split(",");
		boolean flag = true;
		for (int i = 0; i < martixIds.length; i++) {
			String martixId = martixIds[i].substring(3);
			boolean f = Admin_Ipad_Tag_DAO.delMartix(martixId);
			if (!f) {
				flag = false;
			}
		}
		if (flag) {
			response.getWriter().print("success");
		}else {
			response.getWriter().print("false");
		}
	}
	
	public void getTagsById(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String id = request.getParameter("id");
		String jsonStr = Admin_Ipad_Tag_DAO.getTagById(id);
		response.getWriter().print(new JSONObject(jsonStr));
	}
}
