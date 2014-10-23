package cn.youhui.itemadd.servlet;

import java.io.IOException;
import java.security.interfaces.RSAKey;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.admin.dao.DelItemUtil;
import cn.youhui.itemDAO.ItemDAO;

/**
 * Servlet implementation class DelTKH
 */
@WebServlet("/deltkh")
public class DelTKH extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelTKH() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("itemId")==null||"".equals(request.getParameter("itemId"))){
			response.getWriter().write("paramException");
			return;
		}
		String itemId=request.getParameter("itemId");
		if(DelItemUtil.delItemAll(itemId)&&ItemDAO.getInstance().delInfo(itemId)){
			response.getWriter().write("success");
		}else{
			response.getWriter().write("fail");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
