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
 * Servlet implementation class getSourceClick
 */
@WebServlet("/getSourceClick")
public class getSourceClick extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getSourceClick() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse reponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String source = ParamUtil.getParameter(request, "source");
		String start = ParamUtil.getParameter(request, "start");//2014-08-25
		String end = ParamUtil.getParameter(request, "end");//2014-08-25
		int size = ParamUtil.getParameterInt(request, "pagesize", 10);
		int page = ParamUtil.getParameterInt(request, "page", 1);
		String channel = ParamUtil.getParameter(request, "channel", ""); 
		String platform = ParamUtil.getParameter(request, "platform", ""); 
		response.getWriter().print(StatMongoModel.getInstance().getSourceClickMongo(source, start, end, size, page, channel, platform));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
