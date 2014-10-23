package cn.youhuiWeb.api.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Encoder;
import cn.youhui.common.Config;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.utils.NetManager;
import cn.youhui.utils.ParamUtil;
import cn.youhui.utils.RespStatusBuilder;

@WebServlet("/weblogin/accountInfo")
public class AccountInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{	
			String uid = ParamUtil.getParameter(request, "uid", true);
			String data = URLEncoder.encode(encode("#" + uid), "utf-8");
			
//			String ret = NetManager.getInstance().send("http://localhost:8080/Youhui_3.0.0" + "/FanLiManagerServlet", "type=allaccountinfo&data="+data);
			String ret = NetManager.getInstance().send(Config.API_3_0_FOR_WEB + "/FanLiManagerServlet", "type=allaccountinfo&data="+data);
			response.getWriter().print(ret);
			


			
		}catch(Exception e){
			e.printStackTrace();
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	public static String encode(String content) {
		try {
			BASE64Encoder base64 = new BASE64Encoder();
			content = content + "#0011";
			content = base64.encode(content.getBytes());
			content = "00" + content + "11";
			content = base64.encode(content.getBytes());
		} catch (Exception e) {
		}
		return content;
	}

}
