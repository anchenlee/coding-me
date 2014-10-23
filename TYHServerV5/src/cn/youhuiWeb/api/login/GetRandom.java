package cn.youhuiWeb.api.login;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.common.Config;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.utils.AES256;
import cn.youhui.utils.MD5;
import cn.youhui.utils.NetManager;
import cn.youhui.utils.ParamUtil;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhuiWeb.util.StringUtil;

@WebServlet("/youhuiWebLogin/getrand")
public class GetRandom extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String uid = ParamUtil.getParameter(request, "uid");
			String phoneNum = ParamUtil.getParameter(request, "phone_num");
			String type = ParamUtil.getParameter(request, "type", true);

			if(phoneNum == null){
				phoneNum = "";
			}
			if(uid == null){
				uid = "";
			}
			if(!"".equals(phoneNum)){
				phoneNum = AES256.encrypt(Config.AES_PWD, phoneNum);
			}
			
			String time = System.currentTimeMillis() + "";
			Map<String, String[]> paramMap = new HashMap<String, String[]>();
			paramMap.put("uid", new String[]{uid});
			paramMap.put("phone_num", new String[]{phoneNum});
			paramMap.put("type", new String[]{type});
			paramMap.put("t", new String[]{time});			
			
			String sign = MD5.digest(Config.SIGN_KEY + StringUtil.paramsToStr(paramMap) + Config.SIGN_KEY);			
			
	//		String ret = NetManager.getInstance().send("http://localhost:8080/TYHServerV5" + "/login/getrand", "uid="+uid+"&phone_num="+URLEncoder.encode(phoneNum,"utf-8")+"&type="+type+"&t="+time+"&sign="+sign);
			String ret = NetManager.getInstance().send(Config.API_V5_FOR_WEB + "/login/getrand", "uid="+uid+"&phone_num="+URLEncoder.encode(phoneNum,"utf-8")+"&type="+type+"&t="+time+"&sign="+sign); //URLEncoder.encode 将"+"号转成"2B%"
			
			response.getWriter().print(ret);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
		}
	}	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
