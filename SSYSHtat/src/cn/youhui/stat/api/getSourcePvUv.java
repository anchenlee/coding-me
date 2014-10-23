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
 * Servlet implementation class getSourcePvUv
 */
@WebServlet("/getSourcePvUv")
public class getSourcePvUv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getSourcePvUv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String source = ParamUtil.getParameter(request, "source");
		String type = ParamUtil.getParameter(request, "type");
		String channel = ParamUtil.getParameter(request, "channel", ""); 
		String platform = ParamUtil.getParameter(request, "platform", ""); 
		if (type.equals("HISTORY")) {
			String start = ParamUtil.getParameter(request, "start");//2014-08-25
			String end = ParamUtil.getParameter(request, "end");//2014-08-25
			response.getWriter().print(StatMongoModel.getInstance().getSourcePvUvMongo(source, start, end, channel, platform));
		}else {
			String day = ParamUtil.getParameter(request, "day");//2014-08-25
			response.getWriter().print(StatMongoModel.getInstance().getSourcePvUvMongo(source, day, channel, platform));
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
