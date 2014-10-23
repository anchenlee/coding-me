package cn.youhui.itemadd.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.admin.dao.AdminSearchKeyWordDAO;

/**
 * Servlet implementation class ZkKeyword
 * 自定义关键字
 */
@WebServlet("/zkKeyword")
public class ZkKeyword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ZkKeyword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("yes")!=null){
			if(request.getParameter("yes").equals("insert")){
				if(request.getParameter("val")==null||"".equals(request.getParameter("val"))){
					response.getWriter().write("paramException");
					return;
				}
				String val=zm(request.getParameter("val"));
				String id=AdminSearchKeyWordDAO.insertKeyword(val);
				if(id.equals("")){
					System.out.println("kong......");
					response.getWriter().write("fail");
				}else{
					response.getWriter().write(id);
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
	public String zm(String str){
		byte[] b;
		String tmp="";
		try {
			b = str.getBytes("iso8859-1");
			tmp=new String(b,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tmp;
	}
}
