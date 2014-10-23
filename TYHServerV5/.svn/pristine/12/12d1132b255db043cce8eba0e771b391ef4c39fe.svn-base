package cn.youhui.api.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.youhui.common.Enums;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.ramdata.ClientMenuCacher;
import cn.youhui.utils.RespStatusBuilder;

/**
 * @category 获取客户端菜单
 * @author leejun
 * @since 2012-11-06
 */
@WebServlet("/tyh/menu")
public class ClientMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ClientMenuServlet.class);
	
    public ClientMenuServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String version = request.getParameter("version");
		String result = "";
		String nowVersion = ClientMenuCacher.getInstance().getVersion();
		try {
			if(nowVersion != null && nowVersion.equals(version)){
				response.getWriter().println(RespStatusBuilder.message(Enums.ActionStatus.FORBIDDEN.inValue(), "your menu is newest!"));
				return;
			}
//			if(nowVersion == null){
//			    ClientMenuCacher.getInstance().reload();	
//			}
		result = ClientMenuCacher.getInstance().getMenuxml();
		} catch (Exception e) {
			log.error(e, e);
			e.printStackTrace();
			response.getWriter().println(RespStatusBuilder.message(ActionStatus.SERVER_ERROR.inValue(), e.toString()));
		}
		response.getWriter().println(RespStatusBuilder.message(Enums.ActionStatus.NORMAL_RETURNED.inValue(), Enums.ActionStatus.NORMAL_RETURNED.getDescription(), result, nowVersion, false));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
