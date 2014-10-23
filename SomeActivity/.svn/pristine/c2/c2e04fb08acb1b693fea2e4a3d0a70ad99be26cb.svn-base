package cn.suishou.some.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.suishou.some.bean.UserChooseBean;
import cn.suishou.some.dao.UserChooseDao;




@WebServlet("/userchoose")
public class AddUserChooseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public AddUserChooseServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String openId=request.getParameter("openid");
		String nickName=request.getParameter("nickname");
		String choose=request.getParameter("choose");
		String ip=getRemoteAddress(request);
		if(openId!=null&&nickName!=null&&choose!=null&&ip!=null){
			UserChooseBean ucBean=new UserChooseBean();
			ucBean.setOpenId(openId);
			ucBean.setChoose(choose);
			ucBean.setNickName(nickName);
			ucBean.setIp(ip);
			if(UserChooseDao.getUserChooseDao().addUserChoose(ucBean)){
			}
		}
		response.getWriter().println("success");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	/**
	 * 得到客户端IP地址
	 * 
	 * 使用"request.getRemoteAddr()"只能获得上一次请求机器的地址。
	 	在实际的系统使用时，你的项目服务器前端可以还会有一些防火墙、WEB服务器缓存等等，
		如是使用request.getRemoteAddr()就只能获得防火墙或是WEB服务器的IP了。
	 * 
	 * @param request
	 * @return
	 */
	public String getRemoteAddress(HttpServletRequest request) {  
        String ip = request.getHeader("x-forwarded-for");  
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
            ip = request.getHeader("Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
            ip = request.getRemoteAddr();  
        }  
        return ip;  
    }  

}
