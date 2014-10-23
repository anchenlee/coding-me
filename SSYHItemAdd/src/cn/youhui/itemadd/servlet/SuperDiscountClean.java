package cn.youhui.itemadd.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.cache.dao.SuperDiscountCleanRedis;
import cn.youhui.itemDAO.SecondKillDAO;
import cn.youhui.itemDAO.ShareItemDAO;
import cn.youhui.itemDAO.SuperDiscountDAO;

/**
 * Servlet implementation class SuperDiscountClean
 */
@WebServlet("/SuperDiscountClean")
public class SuperDiscountClean extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuperDiscountClean() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SuperDiscountCleanRedis.cleanAll();
		response.getWriter().print("ok");
		SuperDiscountDAO.getInstance().clean();
		ShareItemDAO.getInstance().clean();
		SecondKillDAO.getInstance().clean();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
