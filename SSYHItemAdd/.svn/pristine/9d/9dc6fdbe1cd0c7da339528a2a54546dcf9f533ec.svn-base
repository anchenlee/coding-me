package cn.youhui.itemadd.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.SuperDiscountBean;
import cn.youhui.cache.dao.superDiscountCacher;
import cn.youhui.itemDAO.SuperDiscountDAO;

/**
 * Servlet implementation class SuperDiscountHide
 */
@WebServlet("/SuperDiscountHide")
public class SuperDiscountHide extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuperDiscountHide() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		String isHide=request.getParameter("is_hide");
		if(id==null||"".equals(id)||isHide==null||"".equals(isHide)){
			response.getWriter().print("paramException");
			return;
		}
		if(SuperDiscountDAO.getInstance().setHide(Integer.parseInt(id), Integer.parseInt(isHide))){
			SuperDiscountBean sd=SuperDiscountDAO.getInstance().getSuperDiscountById(Integer.parseInt(id));
			superDiscountCacher.getInstance().delSuperDiscountById(id+"", sd.getDiscountTimestamp()+"");
			superDiscountCacher.getInstance().addSuperDiscount(id+"", sd);
			response.getWriter().print("success");
			return;
		}
		response.getWriter().print("fail");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
