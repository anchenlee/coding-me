package cn.youhuiWeb.api.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.common.Config;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.utils.Encrypt;
import cn.youhui.utils.NetManager;
import cn.youhui.utils.RespStatusBuilder;

/**
 * 获取集分宝提现列表
 * @author liuxj
 * @since 2014-08-20
 */
@WebServlet("/weblogin/jfbtxlist")
public class GetJFBTXList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			String uid = request.getParameter("uid");
			String page = request.getParameter("page");
			
			String data = "#"+uid+"#";			
			data = Encrypt.encode(data);
			
//			String ret = NetManager.getInstance().send("http://localhost:8080/TYHServerV5" + "/tyh3/jfbtxlist", "page="+page+"&data="+URLEncoder.encode(data,"utf-8"));			
			String ret = NetManager.getInstance().send(Config.API_V5_FOR_WEB + "/tyh3/jfbtxlist", "page="+page+"&data="+URLEncoder.encode(data,"utf-8"));
			
			response.getWriter().print(ret);
		} catch (IOException e) {
			e.printStackTrace();
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
		}		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
