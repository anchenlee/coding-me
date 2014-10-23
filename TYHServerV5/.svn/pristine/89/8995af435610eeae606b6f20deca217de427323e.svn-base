package cn.youhui.api.servlet2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.manager.TianTianDazhe;






@WebServlet("/tyh2/ttdazhe")
public class TianTianDazheServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		String keyword = request.getParameter("keyword");
		String type =  request.getParameter("type");
		String channel =request.getParameter("channel");
		String refresh = request.getParameter("refresh");
		String platform = request.getParameter("platform");
		String uid = request.getParameter("uid");
		if(refresh == null){
			refresh = "";
		}
		if(type==null||type.equals("")){
			type= "1";
		}
		if(channel == null){
			channel = "";
		}
		if(keyword==null){
			keyword = "";
		}
		if(page ==null||page.equals("")){
			page = "1";
		}
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		if(!refresh.equals("")){

		}else{	
		response.getWriter().println(TianTianDazhe.getResult(page, uid, keyword,type,platform));

		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		this.doGet(request, response);
	}
	
	public static void main(String[] args) {
	}
}
