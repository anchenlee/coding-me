package cn.suishou.some.talk.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.suishou.some.exception.BadParameterException;
import cn.suishou.some.response.HeaderBean;
import cn.suishou.some.response.ResponseBean;
import cn.suishou.some.response.Enums.ActionStatus;
import cn.suishou.some.talk.cache.OperationMsgCacher;
import cn.suishou.some.util.ParamUtil;

/**
 * Servlet implementation class CacheReload
 */
@WebServlet("/cache_reload")
public class CacheReload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String format = "json";
		String loadType = ParamUtil.getParameter(req, "type");
		try {
			if ("m2r".equals(loadType)){
				OperationMsgCacher.getInstance().reloadM2R();
			} else if("r2m".equals(loadType)){
				OperationMsgCacher.getInstance().reloadR2M();
			}
			resp.getWriter().print(new ResponseBean(new HeaderBean(ActionStatus.NORMAL_RETURNED),format));
		} catch(BadParameterException e){
			resp.getWriter().print(new ResponseBean(new HeaderBean(ActionStatus.PARAMAS_ERROR),format));
		} 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
