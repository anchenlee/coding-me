package cn.youhui.itemadd.servlet.shareItem;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.itemDAO.ShareItemDAO;

/**
 * Servlet implementation class ShareItemServet
 */
@WebServlet("/ShareItemServet")
public class ShareItemServet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShareItemServet() {
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
			String title=request.getParameter("title");
			String desc=request.getParameter("desc");
			String discountId=request.getParameter("discount_id");
			if(method.equals("add")){
				System.out.println(title+"::"+desc+"::::"+discountId);
				if(title==null||"".equals(title)||desc==null||"".equals(desc)){
					response.getWriter().print("ParamException");
					return;
				}
				if(ShareItemDAO.getInstance().add(title, desc, Integer.parseInt(discountId))){
					response.getWriter().print("success");
				}else{
					response.getWriter().print("fail");
				}
			}else if(method.equals("update")){
				if(title==null||"".equals(title)||desc==null||"".equals(desc)){
					response.getWriter().print("ParamException");
					return;
				}
				if(ShareItemDAO.getInstance().update(title, desc, Integer.parseInt(discountId))){
					response.getWriter().print("success");
				}else{
					response.getWriter().print("fail");
				}
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
