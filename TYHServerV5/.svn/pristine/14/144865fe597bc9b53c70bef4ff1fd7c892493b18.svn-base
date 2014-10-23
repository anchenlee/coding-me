package cn.youhui.api.servlet2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.manager.JUhuasuan_newGetTB;



@WebServlet("/JuHuaSuanServlet")
public class JuHuaSuanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private static final String String = null;
 
	
	public JuHuaSuanServlet() {
		super();
		
	}


	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String page = request.getParameter("page");
		String channel = request.getParameter("channel");
		
		if(channel==null){
			channel = "";
		}

		// String keyword = new String(request.getParameter("keyword").getBytes(
		// "ISO-8859-1"), "utf-8");
		String keyword = request.getParameter("keyword");
		String category = request.getParameter("category");
		if(category==null){
			category = "-1";
		}
//response.getWriter().println(
	//	JuhuasuanManager.getResult(page, uid, keyword, category,channel));
		String uid = request.getParameter("uid");
		// Connection conn = SQL.getInstance().getConnection();
		// String uid = new UserManager().checkuser(request, conn);
		// SQL.getInstance().release(conn);
//		String uid = "";
		response.getWriter().println(JUhuasuan_newGetTB.getResult(page, uid, keyword, category,channel));

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		this.doGet(request, response);
	}

}
