package cn.youhui.api.huafei;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.utils.ParamUtil;

/**
 * 
 * @author lujiabin
 * @since 2014-9-3
 */
@WebServlet("/czrecord")
public class CZRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CZRecordServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try{
			String uid = ParamUtil.getParameter(request, "uid", true);
			List<HuafeiCZ> list = HuafeiCZDAO.getInstance().getRecordUid(uid);
			String data = "";
			if(list.size() > 0) {
				StringBuffer sb = new StringBuffer();
				sb.append("{\"records\":[");
				for(HuafeiCZ bean : list) {
					sb.append(bean.toJson()).append(",");
				}
				data = sb.toString();
				data = data.substring(0, data.length()-1);
				data = data+"]}";
			}
			response.getWriter().print(data);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
