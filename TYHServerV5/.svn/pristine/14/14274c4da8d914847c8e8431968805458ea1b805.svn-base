package cn.youhui.api.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.ramdata.IphoneDevTokenCacher;

@WebServlet("/test/getdevtoken")
public class TestDevToken extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public TestDevToken() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("uid");
		String s = "";
		System.out.println("session_id :::" + request.getSession().getId());
		if(uid != null && !"".equals(uid)){
			System.out.println(System.getProperties());
			s = IphoneDevTokenCacher.getInstance().getDevToken(uid);
		}else{
//		Set<String> set = IphoneDevTokenCacher.getInstance().getAllDevToken();
		s += IphoneDevTokenCacher.getInstance().getAllCount();
		}
		response.getWriter().print(s);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
