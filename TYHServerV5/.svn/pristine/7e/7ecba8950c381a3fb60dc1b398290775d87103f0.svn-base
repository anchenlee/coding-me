package cn.youhui.api.servlet2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.TradeManager;
import cn.youhui.utils.RespStatusBuilder;

@WebServlet("/tyh2/hidetrade")
public class YHHideOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("uid");
		String tradeId = request.getParameter("tradeid");
		if(uid == null || "".equals(uid) || tradeId == null || "".equals(tradeId)){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
			return;
		}
		if(TradeManager.getInstance().hideTrade(uid, tradeId)){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED).toString());
		}else{
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.DATABASE_ERROR).toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
