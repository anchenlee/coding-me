package cn.suishou.some.luckac;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.suishou.some.util.WebUtil;

/**
 * 抽奖
 * @author lijun
 * @since 2014-6-10
 */
@WebServlet("/luckaccj")
public class LuckAcCJServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("uid");
		String ip = WebUtil.getIpAddr(request);
		int ret = 0;
		if(System.currentTimeMillis() < 1402534800000l){   //2014.06.12 09:00 之前不让参加
			ret = 10;
		}else{
			ret = McdActivityManager.joinMcdLuckAc(uid, ip);
		}
		
		response.getWriter().print(ret);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
