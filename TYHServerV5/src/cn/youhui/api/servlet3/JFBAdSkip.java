package cn.youhui.api.servlet3;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.jfbad.JFBAdManager;
import cn.youhui.utils.Encrypt;
import cn.youhui.utils.ParamUtil;
import cn.youhui.utils.UrlUtil;

/**
 * 集分宝广告跳转
 * @author lijun
 * @since 2014-02-25
 */
@WebServlet("/jfbskip")
public class JFBAdSkip extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String data = ParamUtil.getParameter(request, "data", true);
			String tyhuid = ParamUtil.getParameter(request, "tyh_web_uid");
			String tyhversion = ParamUtil.getParameter(request, "tyh_web_version");
			String tyhplatform = ParamUtil.getParameter(request, "tyh_web_platform");
			data = data.replaceAll(" ", "+");
			data = Encrypt.decode(data);
			String param[] = data.split("#");
			if(param.length !=5){
				response.getWriter().print("params error");
				return;
			}
			String uid = param[1];
			String adId = param[2];
			String isFromSign = param[3];
			String url = JFBAdManager.getInstance().skip(uid, adId, Integer.parseInt(isFromSign));
			if(url != null && !"".equals(url)){
				String datastr = "";
				if (url.indexOf("jfbmidskip?data=") > -1) {
					String furl = url.split("&")[0];
					int dataIndex = furl.indexOf("data=");
					datastr = furl.substring(dataIndex);
					url = url.substring(0, dataIndex);					
				}
				url = UrlUtil.addParams(url, "tyh_web_uid=" + tyhuid + "&tyh_web_version=" + tyhversion + "&tyh_web_platform=" + tyhplatform + "&" + datastr);
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
