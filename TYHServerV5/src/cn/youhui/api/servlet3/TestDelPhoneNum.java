package cn.youhui.api.servlet3;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.manager.UserManager;
import cn.youhui.utils.StringUtils;


/**
 * 用于测试删除手机号码
 * @author lijun
 * @since 2014-4-7
 */
@WebServlet("/delphone")
public class TestDelPhoneNum extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phoneNum = request.getParameter("phone_num");
		if(!StringUtils.isEmpty(phoneNum)){
			if(UserManager.getInstance().delPhone(phoneNum)){
				response.getWriter().print("success");
			}else{
				response.getWriter().print("fail");
			}
		}else{
			response.getWriter().print("params error");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
