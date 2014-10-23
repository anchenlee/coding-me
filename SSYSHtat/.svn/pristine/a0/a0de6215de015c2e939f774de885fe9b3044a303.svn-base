package cn.youhui.stat.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.stat.model.StatMongoModel;
import cn.youhui.stat.util.ParamUtil;

/**
 * Servlet implementation class FetchKeywordStat
 */
@WebServlet("/FetchKeywordStat")
public class FetchKeywordStat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchKeywordStat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = ParamUtil.getParameter(request, "keyword");
		String day = ParamUtil.getParameter(request, "day");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String ret = StatMongoModel.getInstance().FetchKeywordStatMongo(keyword, day);
		response.getWriter().print(ret);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		this.doGet(request, response);
	}

}
