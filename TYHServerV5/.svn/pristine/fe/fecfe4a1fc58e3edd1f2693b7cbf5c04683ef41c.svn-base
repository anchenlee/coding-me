package cn.youhui.api.superdiscount;

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
import cn.youhui.bean.SuperDiscountBean;
import cn.youhui.dao.Admin_Assess_DAO;
import cn.youhui.dao.SuperDiscountDAO;



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
			
			long completeTime = 0 ; 
			long superDiscountDate = 0 ; 
			String ct = request.getParameter("completeTime");
			String sd = request.getParameter("date");//超级惠日期
			if (!"".equals(ct)) {
				completeTime = Long.parseLong(ct);
			}
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
			as.setCompleteTime(String.valueOf(completeTime));
			as.setGoodsTitle(goodsTitle);
			as.setSuperDiscountDate(superDiscountDate);
			
			Boolean flag = Admin_Assess_DAO.addAssessBean(as);
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
			List<Assess>  list = Admin_Assess_DAO.getAssessListByItemIdAndDate(itemId, date);
			Gson gs = new Gson();
			JSONArray ja = new JSONArray();
			for (int i = 0; i < list.size(); i++) {
				String value = gs.toJson(list.get(i), Assess.class);
				ja.put(new JSONObject(value));
			}
			response.getWriter().print(ja.toString());
			
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
