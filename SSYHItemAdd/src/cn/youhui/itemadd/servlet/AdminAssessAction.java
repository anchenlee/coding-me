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

import cn.youhui.bean.Assess;
import cn.youhui.bean.AssessOption;
import cn.youhui.bean.SuperDiscountBean;
import cn.youhui.itemDAO.AdminAssessDAO;
import cn.youhui.itemDAO.AdminAssessOptionDAO;
import cn.youhui.itemDAO.SuperDiscountDAO;



/**
 * 首页
 * @author 
 *
 */

@WebServlet("/superDiscount/AssessAction")
public class AdminAssessAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminAssessAction() {
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
			if(methodName.equals("addAssess")){
				addAssess(request, response);
			}else if(methodName.equals("getAssessListByItemIdAndDate")){
				getAssessListByItemIdAndDate(request, response);
			}else if(methodName.equals("getSuperDiscountByKeywordAndDate")){
				getSuperDiscountByKeywordAndDate(request, response);
			}else if(methodName.equals("getAssessListByAssessOptionId")){
				getAssessListByAssessOptionId(request, response);
			}else if(methodName.equals("getAssessListByReadStatus")){
				getAssessListByReadStatus(request, response);
			}else if(methodName.equals("changeAssessReadStatusById")){
				changeAssessReadStatusById(request, response);
			}
		} catch (Exception e){
			response.getWriter().print("<script>alert('server process error！');history.back();</script>");
			return;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	public void addAssess(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			
			String UID = "0";
			String orderId = request.getParameter("orderId");
			String itemId = request.getParameter("itemId");
			String assess = request.getParameter("assess");
			String goodsTitle = request.getParameter("goodsTitle");
			String type = request.getParameter("type");  // "0" : 确认收货   "1": 未收货  "3"：超级惠
			
			String completeTime = request.getParameter("completeTime");
			String sd = request.getParameter("date");//超级惠日期
			long superDiscountDate = 0;
			if (!"".equals(sd)) {
				superDiscountDate = Long.parseLong(sd);
			}
			String assessList = request.getParameter("assessList");
			String userContact = request.getParameter("userContact");
			
			Assess as = new Assess();
			as.setAssess(assess);
			as.setAssessList(assessList);
			as.setItemId(itemId);
			as.setOrderId(orderId);
			as.setUID(UID);
			as.setUserContact(userContact);
			as.setType(type);
			as.setCreateTime(System.currentTimeMillis());
			as.setUpdateTime(System.currentTimeMillis());
			as.setCompleteTime(completeTime);
			as.setGoodsTitle(goodsTitle);
			as.setSuperDiscountDate(superDiscountDate);
			
			Boolean flag = AdminAssessDAO.addAssessBean(as);
			if (flag) {
				response.getWriter().print("success");
			}else {
				response.getWriter().print("false");
			}
	}
	
	public void getAssessListByItemIdAndDate(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			String itemId = request.getParameter("itemId");
			String date = request.getParameter("date");
			String type = request.getParameter("type");
			List<Assess>  list = AdminAssessDAO.getAssessListByItemIdAndDate(itemId, date,type);
			List<AssessOption> aos = AdminAssessOptionDAO.getAssessOptionList();
			Gson gs = new Gson();
			JSONArray ja = new JSONArray();
			for (int i = 0; i < list.size(); i++) {
				String ao = list.get(i).getAssessList();
				String assessDetail = "";
				if (!"".equals(ao)) {
					String ids []  = ao.split(",");
					for (int k = 0; k < ids.length; k++) {
						for (int j = 0; j < aos.size(); j++) {
							if (aos.get(j).getId() == Integer.parseInt(ids[k])) {
								assessDetail += aos.get(j).getContent() + ";";
							}
						}
					}
				}
				list.get(i).setAssessList(assessDetail);
				String value = gs.toJson(list.get(i), Assess.class);
				ja.put(new JSONObject(value));
			}
			response.getWriter().print(ja.toString());
			
	}
	
	public void getAssessListByAssessOptionId(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			String id = request.getParameter("id");
			String type = request.getParameter("type");
			List<Assess>  list = AdminAssessDAO.getAllAssessList();
			List<AssessOption> aos = AdminAssessOptionDAO.getAssessOptionList();
			Gson gs = new Gson();
			JSONArray ja = new JSONArray();
			for (int i = 0; i < list.size(); i++) {
				String ao = list.get(i).getAssessList();
				String assessDetail = "";
				if (!"".equals(ao)) {
					String ids []  = ao.split(",");
					for (int k = 0; k < ids.length; k++) {
						for (int j = 0; j < aos.size(); j++) {
							if (aos.get(j).getId() == Integer.parseInt(ids[k])) {
								assessDetail += aos.get(j).getContent() + ";";
							}
						}
					}
				}
				list.get(i).setAssessList(assessDetail);
				if ("all_good".equals(id) && "1".equals(list.get(i).getType())) {
					String value = gs.toJson(list.get(i), Assess.class);
					ja.put(new JSONObject(value));
				}else if ("all_app".equals(id) && "2".equals(list.get(i).getType())) {
					String value = gs.toJson(list.get(i), Assess.class);
					ja.put(new JSONObject(value));
				}else if (ao.contains(id) && list.get(i).getType().equals(type)) {
					String value = gs.toJson(list.get(i), Assess.class);
					ja.put(new JSONObject(value));
					}
				}
			response.getWriter().print(ja.toString());
	}
	
	public void getAssessListByReadStatus(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			String isRead = request.getParameter("isRead");//是否查看 0否 1是
			String type = request.getParameter("type");//1：商品 2：app
			List<Assess>  list = AdminAssessDAO.getAllAssessList();
			List<AssessOption> aos = AdminAssessOptionDAO.getAssessOptionList();
			Gson gs = new Gson();
			JSONArray ja = new JSONArray();
			for (int i = 0; i < list.size(); i++) {
				String assessList = list.get(i).getAssessList();
				String assessDetail = "";
				if (!"".equals(assessList)) {
					String ids []  = assessList.split(",");
					for (int k = 0; k < ids.length; k++) {
						for (int j = 0; j < aos.size(); j++) {
							if (aos.get(j).getId() == Integer.parseInt(ids[k])) {
								assessDetail += aos.get(j).getContent() + ";";
							}
						}
					}
				}
				list.get(i).setAssessList(assessDetail);
				if ("all_good".equals(isRead) && "1".equals(list.get(i).getType())) {
					String value = gs.toJson(list.get(i), Assess.class);
					ja.put(new JSONObject(value));
				}else if ("all_app".equals(isRead) && "2".equals(list.get(i).getType())) {
					String value = gs.toJson(list.get(i), Assess.class);
					ja.put(new JSONObject(value));
				}else if (list.get(i).getIsRead().contains(isRead) && list.get(i).getType().equals(type)) {
					String value = gs.toJson(list.get(i), Assess.class);
					ja.put(new JSONObject(value));
					}
				}			
			response.getWriter().print(ja.toString());
	}
	
	public void changeAssessReadStatusById(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			String id = request.getParameter("id");
			String status = request.getParameter("status");
			boolean  flag = AdminAssessDAO.changeAssessReadStatusById(id,status);
			if (flag) {
				response.getWriter().print("success");
			}else{
				response.getWriter().print("false");
			}
			
	}
	
	public void getSuperDiscountByKeywordAndDate(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			String keyword =  request.getParameter("keyword");
			String date = request.getParameter("date");
			List<SuperDiscountBean> list = SuperDiscountDAO.getInstance().getSuperDiscountByKeywordAndDate(keyword, date);
			Gson gs = new Gson();
			JSONArray ja = new JSONArray();
			for (int i = 0; i < list.size(); i++) {
				String showDate = list.get(i).getDiscountDate();
				JSONObject jo = new JSONObject();
				JSONArray value = new JSONArray();
				boolean flag = false;
				int j = 0;
				for (j = 0; j < ja.length(); j++) {
						jo = (JSONObject) ja.get(j);
						if (showDate.equals(jo.get("date").toString())) {	
							 flag = true;
							 value = (JSONArray) jo.get("list");
							 break;
						}
				}
				if (flag) {
					String newsd = gs.toJson(list.get(i), SuperDiscountBean.class);
					value.put(new JSONObject(newsd));
					jo.put("list", value);
					ja.put(j, jo);
				}else{
					jo = new JSONObject();
					jo.put("date", showDate);
					String newsd = gs.toJson(list.get(i), SuperDiscountBean.class);
					value.put(new JSONObject(newsd));
					jo.put("list", value);
					ja.put(jo);
				}
				
			}
			response.getWriter().print(ja.toString());
	}
}
