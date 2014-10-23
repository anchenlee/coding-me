package cn.youhui.api.servlet3;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.ActivityJoin;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.JiFenBaoMXManager;
import cn.youhui.utils.RespStatusBuilder;

/**
 * ipad集分宝获取列表
 * @author l
 * @since 2014-02-18
 */
@WebServlet("/tyh3/ipadjfblist")
public class GetIpadJFBList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String method = request.getParameter("method");
		if ("getJFBDetailList".equals(method)) {
			try {
				getJFBDetailList(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if ("getJFBShoppingList".equals(method)) {
			try {
				getJFBShoppingList(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if ("getJFBSigninList".equals(method)) {
			try {
				getJFBSigninList(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if ("getAllPayList".equals(method)) {
			try {
				getAllPayList(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
		}
	}
	
	public void getJFBDetailList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String uid = request.getParameter("uid");
		String type = request.getParameter("type");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String page = request.getParameter("page");
		if(page == null || "".equals(page)){
			page = "1";
		}
		
		//集分宝总收入
		int allincomeJfb = 0;
		//总页数
		int pageNum = 0;
		//购物赠送集分宝
		int shopjfb = 0;
		//活动赠送集分宝
		int activityjfb = 0;
		//签到奖励集分宝
		int signinjfb = 0;
		//提现集分宝
		int txjfb=  0;
		//集分宝购买
		int usejfb = 0;
		//集分宝总支出
		int allpayJfb = 0;
		List<ActivityJoin> list = null;
		if ("income".equals(type)) {
			pageNum = JiFenBaoMXManager.getInstance().getIncomePageNum(uid, startTime, endTime);
			allincomeJfb = JiFenBaoMXManager.getInstance().getIncomeJfbNum(uid, startTime, endTime);
			shopjfb = JiFenBaoMXManager.getInstance().getShoppingJfbNum(uid, startTime, endTime);
			activityjfb = JiFenBaoMXManager.getInstance().getActivityJfbNum(uid, startTime, endTime);
			signinjfb = JiFenBaoMXManager.getInstance().getSigninJfbNum(uid, startTime, endTime);
			list = JiFenBaoMXManager.getInstance().getIncomeListByDate(uid, Integer.parseInt(page), startTime, endTime);
		}else if ("pay".equals(type)) {
			pageNum = JiFenBaoMXManager.getInstance().getPayPageNum(uid, startTime, endTime);
			txjfb = JiFenBaoMXManager.getInstance().getTXJfbNum(uid, startTime, endTime) * -1;
			usejfb = JiFenBaoMXManager.getInstance().getuseJfbToPayNum(uid, startTime, endTime)*-1;
			allpayJfb = JiFenBaoMXManager.getInstance().getPayJfbNum(uid, startTime, endTime)*-1;
			list = JiFenBaoMXManager.getInstance().getPayListByDate(uid, Integer.parseInt(page), startTime, endTime);
		}
		
		System.out.println("list.size() :" + list.size() );
		if(list != null && list.size() > 0){
			System.out.println("list.size() > 0" );
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, "", pageNum , Integer.parseInt(page),  changetoXml(list,allincomeJfb,shopjfb,activityjfb,signinjfb,txjfb,usejfb,type,allpayJfb)));
		}else{
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());
		}
	}
	
	public void getJFBShoppingList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String uid = request.getParameter("uid");
		String pageStr = request.getParameter("page");
		
		if(pageStr == null || "".equals(pageStr)){
			pageStr = "1";
		}
		int page = Integer.parseInt(pageStr);
		int pageCount = JiFenBaoMXManager.getInstance().getIpadJFBShoppingTradePageNum(uid);
		if(pageCount == 0){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());
			return;
		}
		if(page < 1){
			page = 1;
		}else if(page > pageCount){
			page = pageCount;
		}
		List<ActivityJoin> list = JiFenBaoMXManager.getInstance().getIpadJFBShoppingTradeList(uid, page);
		if(list != null && list.size() > 0){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, "", pageCount, page, changeIncometoXml(list)));
		}else{
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());
		}
	}
	
	public void getAllPayList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String uid = request.getParameter("uid");
		String pageStr = request.getParameter("page");
		
		if(pageStr == null || "".equals(pageStr)){
			pageStr = "1";
		}
		int page = Integer.parseInt(pageStr);
		int pageCount = JiFenBaoMXManager.getInstance().getIpadAllPayPageNum(uid);
		if(pageCount == 0){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());
			return;
		}
		if(page < 1){
			page = 1;
		}else if(page > pageCount){
			page = pageCount;
		}
		List<ActivityJoin> list = JiFenBaoMXManager.getInstance().getAllPayList(uid, page);
		if(list != null && list.size() > 0){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, "", pageCount, page, changeIncometoXml(list)));
		}else{
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());
		}
	}
	
	public void getJFBSigninList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String uid = request.getParameter("uid");
		String pageStr = request.getParameter("page");
		
		if(pageStr == null || "".equals(pageStr)){
			pageStr = "1";
		}
		int page = Integer.parseInt(pageStr);
		int pageCount = JiFenBaoMXManager.getInstance().getIpadJFBSigninTradePageNum(uid);
		if(pageCount == 0){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());
			return;
		}
		if(page < 1){
			page = 1;
		}else if(page > pageCount){
			page = pageCount;
		}
		List<ActivityJoin> list = JiFenBaoMXManager.getInstance().getIpadJFBSigninTradeList(uid, page);
		if(list != null && list.size() > 0){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, "", pageCount, page, changeIncometoXml(list)));
		}else{
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());
		}
	}
	
	private String changeIncometoXml(List<ActivityJoin> list){
		StringBuffer sb = new StringBuffer();
		if(list != null && list.size() > 0){
			sb.append("<join_list>");
			for(ActivityJoin join : list){
				sb.append(join.toXML());
			}
			sb.append("</join_list>");
		}
		return sb.toString();
	}
	
	private String changetoXml(List<ActivityJoin> list, int allJfb,int shopjfb,int activityjfb,int signinjfb,int txjfb,int usejfb, String type,int allpayJfb){
		StringBuffer sb = new StringBuffer();
 		if ("income".equals(type)) {
 			String shopPercent = "0.0";
 			String activityPercent = "0.0";
 			String signinPercent = "0.0";
 			if (allJfb != 0) {
 				double shop = (shopjfb*1.0)/allJfb;
 				double activity = (activityjfb*1.0)/allJfb;
 				double signin = (signinjfb*1.0)/allJfb;
 				if (shop < 0.001 && shop != 0) {
 					shop = 0.001;
 				}else if (shop == 0.0) {
 					shop = 0.0;
 				}else if (shop == 1.0) {
 					shop = 1.0;
 				}else if (String.valueOf(shop).length() > 5) {
 					shop = Double.parseDouble(String.format("%.3f", shop));
 				}
 				
 				if (activity < 0.001 && activity != 0) {
 					activity = 0.001;
 				}else if (activity == 0.0) {
 					activity = 0.0;
 				}else if (activity == 1.0) {
 					activity = 1.0;
 				}else if (String.valueOf(activity).length() > 5){
 					activity = Double.parseDouble(String.format("%.3f", activity));
 				}
 				
 				if (signin < 0.001 && signin != 0) {
 					signin = 0.001;
 				}else if (signin == 0.0) {
 					signin = 0.0;
 				}else if (signin == 1.0) {
 					signin = 1.0;
 				}else if (String.valueOf(signin).length() > 5){
 					signin = Double.parseDouble(String.format("%.3f", signin));
 				}
 				double sum = shop + activity + signin;
 				if (sum != 1.0) {
 					if (sum > 1.0) {
 						 if (shop != 0.001 && shop != 0.0) {
 							 shop = shop - 0.001;
 						}else if (activity != 0.001 && activity != 0.0) {
 							activity = activity - 0.001;
 						} else if(signin != 0.001 && signin != 0.0){
 							signin = signin - 0.001;
 						}
 					}else {
 						 if (shop != 0.001 && shop != 0.0) {
 							 shop = shop + 0.001;
 						}else if (activity != 0.001 && activity != 0.0) {
 							activity = activity + 0.001;
 						} else if(signin != 0.001 && signin != 0.0){
 							signin = signin + 0.001;
 						}
 					}
 				}
 				shopPercent = String.format("%.1f", shop * 100);
 				activityPercent = String.format("%.1f", activity * 100);
 				signinPercent = String.format("%.1f", signin * 100);
 				}
			sb.append("<jfb><allJfb>"+ allJfb +"</allJfb>");
			sb.append("<jfbtypelist><jtype><name>购物奖励</name><color>#ff503f</color><img>http://static.etouch.cn/suishou/ad_img/nb617vxz40.png</img><count>"+ shopjfb +"</count>");
			sb.append("<percent>"+ shopPercent +"%</percent></jtype>");
			sb.append("<jtype><name>活动奖励</name><color>#ff9f00</color><img>http://static.etouch.cn/suishou/ad_img/nb68tsjuc0.png</img><count>"+ activityjfb +"</count>");
			sb.append("<percent>"+ activityPercent +"%</percent></jtype>");
			sb.append("<jtype><name>签到奖励</name><color>#f29c9f</color><img>http://static.etouch.cn/suishou/ad_img/nb6gijql88.png</img><count>"+ signinjfb +"</count>");
			sb.append("<percent>"+ signinPercent +"%</percent></jtype>");
			sb.append("</jfbtypelist></jfb>");
		}else if ("pay".equals(type)) {
			String txpercent = "0.0";
 			String usepercent = "0.0";
 			if (allpayJfb != 0) {
 				double tx = (txjfb*1.0)/allpayJfb;
 				double use = (usejfb*1.0)/allpayJfb;
 				if (tx < 0.001 && tx != 0) {
 					tx = 0.001;
 				}else if (tx == 0.0) {
 					tx = 0.0;
 				}else if (tx == 1.0) {
 					tx = 1.0;
 				}else if (String.valueOf(tx).length() > 5) {
 					tx = Double.parseDouble(String.format("%.3f", tx));
 				}
 				
 				if (use < 0.001 && use != 0) {
 					use = 0.001;
 				}else if (use == 0.0) {
 					use = 0.0;
 				}else if (use == 1.0) {
 					use = 1.0;
 				}else if (String.valueOf(use).length() > 5){
 					use = Double.parseDouble(String.format("%.3f", use));
 				}
 				double sum = tx + use;
 				if (sum != 1.0) {
 					if (sum > 1.0) {
 						 if (tx != 0.001 && tx != 0.0) {
 							 tx = tx - 0.001;
 						}else if (use != 0.001 && use != 0.0) {
 							use = use - 0.001;
 						}
 					}else {
 						 if (tx != 0.001 && tx != 0.0) {
 							 tx = tx + 0.001;
 						}else if (use != 0.001 && use != 0.0) {
 							use = use + 0.001;
 						}
 					}
 				}
 				txpercent = String.format("%.1f", tx * 100);
 				usepercent = String.format("%.1f", use * 100);
 				}
			sb.append("<jfb><allpayJfb>"+allpayJfb +"</allpayJfb>");
			sb.append("<jfbtypelist><jtype><name>购物支付</name><color>#ff503f</color><img>http://static.etouch.cn/suishou/ad_img/nb617vxz40.png</img><count>"+ txjfb +"</count>");
			sb.append("<percent>"+ txpercent +"%</percent></jtype>");
			sb.append("<jtype><name>活动支付</name><color>#ff9f00</color><img>http://static.etouch.cn/suishou/ad_img/nb68tsjuc0.png</img><count>"+ usejfb +"</count>");
			sb.append("<percent>"+ usepercent +"%</percent></jtype>");
			sb.append("</jfbtypelist></jfb>");
		}
		if(list != null && list.size() > 0){
			sb.append("<join_list>");
			for(ActivityJoin join : list){
				sb.append(join.toXML());
			}
			sb.append("</join_list>");
		}
		return sb.toString();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
