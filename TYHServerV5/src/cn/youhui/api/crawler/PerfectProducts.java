package cn.youhui.api.crawler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.ramdata.Tag2ItemCacher;
import cn.youhui.utils.ParamUtil;
import cn.youhui.utils.RespStatusBuilder;

/**
 * Servlet implementation class PerfectProducts
 */
@WebServlet("/PerfectProducts")
public class PerfectProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PerfectProducts() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = ParamUtil.getParameter(request, "data");
		//System.out.println("======================>"+data);
		Tag2ItemCacher.getInstance().perfTag2Items(data);
		response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED).toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
