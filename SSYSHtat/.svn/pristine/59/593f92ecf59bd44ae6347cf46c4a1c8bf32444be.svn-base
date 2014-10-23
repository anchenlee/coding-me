package cn.youhui.stat.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.stat.model.MongoOperate;
import cn.youhui.stat.util.ConvertCode;
import cn.youhui.stat.util.ParamUtil;

/**
 * Servlet implementation class InsertSuggestItem
 */
@WebServlet("/InsertSuggestItem")
public class InsertSuggestItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertSuggestItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = ParamUtil.getParameter(request, "pid");
		String keys = ParamUtil.getParameter(request, "keys");
		keys = ConvertCode.convert(keys);
		System.out.println(keys);
		long time = ParamUtil.getParameterLong(request, "time");
		int status = ParamUtil.getParameterInt(request, "status");
		response.getWriter().print(MongoOperate.getInstance().insertSugItem(pid, keys, time, status));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
