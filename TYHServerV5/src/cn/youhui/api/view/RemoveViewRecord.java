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
 * Servlet implementation class RemoveViewRecord
 */
@WebServlet("/tyh3/removeviewrecord")
public class RemoveViewRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = ParamUtil.getParameter(request, "uid");
		String phoneId = ParamUtil.getParameter(request, "imei");
		Integer type = ParamUtil.getParameterInt(request, "type");
		if (type == 1) {
			response.getWriter().print(ViewRecordMongo.removeAllViewRecord(userId, phoneId));
		}else {
			String productId = ParamUtil.getParameter(request, "item_id");
			response.getWriter().print(ViewRecordMongo.removeViewRecord(userId, phoneId, productId));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
