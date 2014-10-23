package cn.youhui.itemadd.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.admin.dao.GetItemUtil;
import cn.youhui.bean.ProfRecom;

/**
 * Servlet implementation class DetailFromRedis2Phone
 */
@WebServlet("/DetailFromRedis2Phone")
public class DetailFromRedis2Phone extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailFromRedis2Phone() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ProfRecom> l1=GetItemUtil.getRecoItems();
		List<ProfRecom> l2=GetItemUtil.getBigRecoItems();
		request.setAttribute("l1",l1);
		request.setAttribute("l2", l2);
		request.getRequestDispatcher("/detailFromRedis2Phone.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
