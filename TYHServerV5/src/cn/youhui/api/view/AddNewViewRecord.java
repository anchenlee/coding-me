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
 * Servlet implementation class AddNewViewRecord
 */
@WebServlet("/AddNewViewRecord")
public class AddNewViewRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewViewRecord() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = ParamUtil.getParameter(request, "uid");
		String phoneId = "test";
		String productId = ParamUtil.getParameter(request, "pid");
		String title = "test";
		String img = "xxx.jpg";
		String price = "0.0";
		long time = System.currentTimeMillis();
		response.getWriter().print(ViewRecordMongo.addNewViewRecord(userId, phoneId, productId, title, img, price, time));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
