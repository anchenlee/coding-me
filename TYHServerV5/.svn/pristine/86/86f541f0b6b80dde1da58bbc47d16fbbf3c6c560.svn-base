package cn.youhui.api.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.dao.view.ViewRecordMongo;
import cn.youhui.utils.ParamUtil;

/**
 * Servlet implementation class ObtainViewRecord
 */
@WebServlet("/tyh3/viewrecord")
public class ObtainViewRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObtainViewRecord() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = ParamUtil.getParameter(request, "uid");
		String phoneId = ParamUtil.getParameter(request, "imei");
		int page = ParamUtil.getParameterInt(request, "page");
		response.getWriter().print(ViewRecordMongo.obtainViewRecord(userId, phoneId, page));
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
