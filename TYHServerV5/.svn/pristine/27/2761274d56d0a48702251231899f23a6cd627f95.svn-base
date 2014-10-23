package cn.youhui.api.servlet3;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.jfbad.JFBAdManager;
import cn.youhui.utils.ParamUtil;
import cn.youhui.utils.UrlUtil;

/**
 * 集分宝广告中间跳转
 * @author lijun
 * @since 2014-03-24
 */
@WebServlet("/jfbmidskip")
public class JFBAdMidSkip extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String key = ParamUtil.getParameter(request, "data", true);
			String url = JFBAdManager.getInstance().getAdUrl(key);
			Map<String, String[]> params = request.getParameterMap();
			if(url != null && !"".equals(url)){
				url = UrlUtil.addParams(url, params);
				response.sendRedirect(url);
			}else{
				response.sendRedirect("notfind.html");
			}
		}catch(Exception e){
			e.printStackTrace();
			response.sendRedirect("notfind.html");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
