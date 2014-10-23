package cn.youhui.itemadd.servlet.checkCode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.cache.dao.CheckCodeManager;

/**
 * Servlet implementation class CheckCodeServlet
 */
@WebServlet("/CheckCodeServlet")
public class CheckCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckCodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String method=request.getParameter("method");
		if(method!=null){
			String val=request.getParameter("val");
			if(method.equals("add")){
				if(val==null||"".equals(val)){
					response.getWriter().print("paramException");
					return;
				}
				CheckCodeManager.add(val);
				response.getWriter().print("success");
			}else if(method.equals("get")){
				response.getWriter().print(CheckCodeManager.getAll());
			}else if(method.equals("del")){
				if(val==null||"".equals(val)){
					response.getWriter().print("paramException");
					return;
				}
				CheckCodeManager.del(val);
				response.getWriter().print("success");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
