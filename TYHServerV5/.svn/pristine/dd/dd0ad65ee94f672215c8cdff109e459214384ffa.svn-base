package cn.youhui.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.util.oufeicz.OfPayUtil;

@WebServlet("/CZhuafei")
public class CZhuafei extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CZhuafei() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data=request.getParameter("data");
		String phon=data.split(",")[0];
		int num=Integer.parseInt(data.split(",")[1]);
		System.out.println(OfPayUtil.huafeiCZ(phon,num));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
