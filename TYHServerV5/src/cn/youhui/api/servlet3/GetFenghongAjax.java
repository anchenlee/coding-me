package cn.youhui.api.servlet3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cn.youhui.bean.FanliBean;
import cn.youhui.manager.TradeManager;

/**
 * Servlet implementation class GetFenghongAjax
 */
@WebServlet("/GetFenghongAjax")
public class GetFenghongAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetFenghongAjax() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid");
		String p = request.getParameter("page");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		int page = 2;
		try {
			page = Integer.parseInt(p);
		} catch (Exception e) {
			page = 2;
		}

		List<FanliBean> list = new ArrayList<FanliBean>();
//		if(year == null || "".equals(year) || month == null || "".equals(month)){		
//			list = TradeManager.getInstance().getMonthTradeHistory(uid,page);
//		}else{
//			list = TradeManager.getInstance().getMonthTradeHistoryByMonth(uid, page, year, month);
//		}
		String result = fromListToJson(list);
		response.getWriter().print(result);
	}

	public static String fromListToJson(List<FanliBean> list){
		String context = "";
		if(list != null && list.size() > 0){
			Gson g = new Gson();
			context = g.toJson(list);
		}
		
		return context;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
