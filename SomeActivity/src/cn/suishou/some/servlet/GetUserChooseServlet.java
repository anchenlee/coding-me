package cn.suishou.some.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.suishou.some.dao.UserChooseDao;

/**
 * 得到用户选择
 * @author hufan
 * @since 2014-9-19
 */
@WebServlet("/getchoose")
public class GetUserChooseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public GetUserChooseServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String openid=request.getParameter("openid");
		int userChoose=UserChooseDao.getUserChooseDao().getUserChoose(openid);
		response.getWriter().print(userChoose);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
