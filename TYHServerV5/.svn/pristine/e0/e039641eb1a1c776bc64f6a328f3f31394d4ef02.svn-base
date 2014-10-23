package cn.youhui.api.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.manager.JedisHashManager;
import cn.youhui.ramdata.AndroidPushMessageCacher;
/**
 * Servlet implementation class TestDevToken
 */
@WebServlet("/test/get")
public class TestUidMsg extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public TestUidMsg() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key = request.getParameter("key");
		String field = request.getParameter("field");
		String ret = "";
		if(field != null && !"".equals(field))
		    ret = new JedisHashManager(key).get(field);
		else {
			Map<String, String> map = new JedisHashManager(key).getAll();
			for(Map.Entry<String, String> m : map.entrySet()){
				ret += m.getKey();
				ret += ":";
				ret += m.getValue();
				ret += ",";
			}
		}
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(ret);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
