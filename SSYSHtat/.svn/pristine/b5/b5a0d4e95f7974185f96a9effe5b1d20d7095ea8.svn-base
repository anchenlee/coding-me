package cn.youhui.stat.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.stat.model.StatMySqlModel;
import cn.youhui.stat.util.ParamUtil;

@WebServlet("/FetchRealTimeStat")
public class FetchRealTimeStat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = ParamUtil.getParameter(request, "pid");
		String tag = ParamUtil.getParameter(request, "tag");
		String ret = StatMySqlModel.getInstance().FetchRealTimePidStat(pid,tag);
		response.getWriter().print(ret);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
