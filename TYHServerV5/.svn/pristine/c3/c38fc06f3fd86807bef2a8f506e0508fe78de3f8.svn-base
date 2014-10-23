package cn.youhui.api.superdiscount;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.dao.SecondKillDAO;
import cn.youhui.utils.ParamUtil;

/**
 * 获取订单列表
 * jiangjun
 */
@WebServlet("/GetSecondKillOrder")
public class GetSecondKillOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetSecondKillOrder() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = ParamUtil.getParameter(request, "user");
		response.setCharacterEncoding("UTF-8");
		
		response.getWriter().print(SecondKillDAO.getInstance().getSecondKillOrder(uid));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
