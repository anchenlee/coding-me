package cn.youhui.api.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.ExchOrder;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.ExchOrderManager;
import cn.youhui.utils.RespStatusBuilder;
/**
 * 
 * @author leejun
 * @since 2012-11-26
 */
@WebServlet("/tyh/orderhistory")
public class YHGetExchOrderHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public YHGetExchOrderHistory() {
        super();
    }  
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid");
		String page = request.getParameter("page");
		String accessToken = request.getParameter("access_token");
		accessToken = accessToken == null ? "" : accessToken;
		if(page == null || "".equals(page))
			page="1";
		int pageCount =  ExchOrderManager.getInstance().getTotalPage(uid);
		int pa = Integer.parseInt(page);
		if(pa>pageCount) pa = pageCount;
		
		List<ExchOrder> list = ExchOrderManager.getInstance().getbyUid(uid, pa);
		StringBuffer sb = new StringBuffer();
		if(list != null && list.size()>0){
			sb.append("<orders>");
		for(ExchOrder order : list){
			sb.append(order.toXML());
		}
			sb.append("</orders>");
		response.getWriter().write(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, accessToken, pageCount, pa, sb.toString()).toString());
		}else{
			response.getWriter().write(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());

		} 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
