package com.netting.action.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.suishou.systemauth.sso.SSO;

import com.netting.dao.admin.AdminMMixDAO;
import com.netting.dao.admin.UpdatePriceTest;


@WebServlet("/ServletTest")
public class ServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ServletTest() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
//		long time = System.currentTimeMillis();
//		List<String> list = AdminMMixDAO.getAllChildrenId("1");
//		System.out.println(list.size());
//		System.out.println(System.currentTimeMillis()-time);
//		AdminMMixDAO.delMMixBean("1");
////		SSO.Check("userauth", "test login", request,response);
		UpdatePriceTest.excute();
		response.getWriter().print("sysysys");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
