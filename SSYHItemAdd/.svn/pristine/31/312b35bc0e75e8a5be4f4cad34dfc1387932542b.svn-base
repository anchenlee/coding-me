package cn.youhui.itemadd.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.itemDAO.ItemDAO;
import cn.youhui.itemDAO.MUserDAO;

/**
 * Servlet implementation class SearchAddNum
 */
@WebServlet("/addNum")
public class SearchAddNum extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchAddNum() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("username")==null||"".equals(request.getParameter("username"))){
			response.getWriter().write("paramException");
			return;
		}
		String username=request.getParameter("username");
		String date=request.getParameter("date");
		int uid=MUserDAO.getInstance().getIdByUsername(username);
		
		
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 
		 long timestamp=0;
		 try{
		 timestamp=sdf.parse(sdf.format(new Date().getTime())).getTime();
		 if(date!=null&&!date.equals("")){
			 timestamp=sdf.parse(date).getTime();
		 }
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		int num=ItemDAO.getInstance().getNum(uid, timestamp);
		response.getWriter().write(String.valueOf(num));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public static void main(String[] args) {
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 try{
			 long str=sdf.parse(sdf.format(new Date().getTime())).getTime();
			 System.out.println(str);
		 }catch(Exception e){
			 
		 }
	}
	
}
