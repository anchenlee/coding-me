package cn.youhui.acapi.newuser;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.util.oufeicz.PhoneNumAttributionUtil;

/**
 * 手机归属地同步
 * @author 
 * @since 2014-7-31
 */
@WebServlet("/phonenum/synchronize")
public class PhoneNumAttSynchronizedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Map<String ,String>> list = HuafeiManager.getInstance().getAllNullAttrRecords();
		for (int i = 0; i < list.size(); i++) {
			String attr = PhoneNumAttributionUtil.getPhoneNumAttribution(list.get(i).get("phoneNum"));
			boolean flag = HuafeiManager.getInstance().updateAttrRecords(list.get(i).get("id"), attr);
			if (!flag) {
				response.getWriter().print("exits error!");
				return;
			}
		}
		response.getWriter().print("success");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
