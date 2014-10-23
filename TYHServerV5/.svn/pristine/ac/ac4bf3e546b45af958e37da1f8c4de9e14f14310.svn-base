package cn.youhui.api.servlet2;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import sun.misc.BASE64Encoder;

import cn.youhui.common.Enums;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.ramdata.AppConfigCacher;
import cn.youhui.ramdata.ClientMenuCacher;
import cn.youhui.ramdata.IphoneDevTokenCacher;
import cn.youhui.utils.Base64;
import cn.youhui.utils.RespStatusBuilder;

/**
 * @category 提供app参数
 * @author leejun
 * @since 2012-12-2
 */
@WebServlet("/tyh2/appconfig")
public class YHAppConfigServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(YHAppConfigServlet.class);
       
    public YHAppConfigServlet(){
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid");
		String platform = request.getParameter("platform");
		String devtoken = request.getParameter("devtoken");
		String result = "";
		try 
		{
			if("ios".equals(platform))
			{
				if(devtoken != null && !"".equals(devtoken))
				{
					IphoneDevTokenCacher.getInstance().addDevToken(devtoken);
					if(uid != null && !"".equals(uid))
					{
						IphoneDevTokenCacher.getInstance().add(uid, devtoken);
					}
				}
			}
			String appconfig = AppConfigCacher.getInstance().getData();
			if(appconfig == null)
			{
				AppConfigCacher.getInstance().reload();
				appconfig = AppConfigCacher.getInstance().getData();
			}
			result = "{" + appconfig + ",\"menuversion\":\"" + ClientMenuCacher.getInstance().getVersion() +"\"}";
			BASE64Encoder base = new BASE64Encoder();
			result = base.encode(result.getBytes());
			result = "\"" + result + "\""; 
		} 
		catch (Exception e) 
		{
			log.error(e, e);
			e.printStackTrace();
			response.getWriter().println(RespStatusBuilder.messageJson(ActionStatus.SERVER_ERROR, e.toString()));
		}
		
		if(result != null && !"".equals(result) && !"null".equals(result))
		{
			response.getWriter().println(RespStatusBuilder.messageJson(Enums.ActionStatus.NORMAL_RETURNED, "", result));	
	   	}
		else 
		{
			response.getWriter().println(RespStatusBuilder.messageJson(Enums.ActionStatus.DATABASE_ERROR, ""));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		String s = "eyAgICAiYXBwY29uZmlnIjogew0KICAgICAgICAiYXBwa2V5IjogIjEyNDE1Mjg5IiwNCiAgICAgICAgImFwcHNlY3JldCI6ICI4YTM4MWZjYWJjMWI3NDAwM2M2ODk1NTBhOTVhMGYwYSIsDQogICAgICAgICJhcHBjYWxiYWNrIjogInN1aXNob3V5b3VodWk6Ly9hdXRocmVzdWx0IiwNCiAgICAgICAgImFwcGZhbmxpcmF0ZSI6ICJHIiwNCiAgICAgICAgImFwcG1vbmV5dHlwZSI6ICIwIiwNCiJzaG93dHRpZCI6ICIwIiwNCiAgICAgICAgImNvbGxlY3R2aWV3X3VybCI6ICJodHRwOi8vYmNzLmR1YXBwLmNvbS90YW95b3VodWlmaWxlcy9Db2xsZWN0Vmlldy5hcGsiLA0KICAgICAgICAiY29sbGVjdHZpZXdfdmVyIjogIi0xIiwNCiJoZWFkX2JsYWNrbGlzdCI6IFtdLCJqZmJfcmF0ZSI6ICIyIiwiaXNmYW5qZmIiOiIxIiwiaXNsb2NhbHNlYXJjaCI6IjAiLA0KICAid2ViX3RpcHMiOiAiIiwNCiAgICAid2ViX3RpcHNfd2hyYXRpbyI6ICI1IiwNCiAgICAid2ViX3RpcHNfY2xpY2tfdXJsIjogImh0dHA6Ly9zdWlzaG91LmNuIiwNCiAgICAid2ViX3RpcHNfc2hvd190aW1lIjogMCwiZG93bl9jbGljayI6IiINCiAgICB9LCJtZW51dmVyc2lvbiI6Im51bGwifQ==";
		System.out.println(new String(Base64.decode(s)));
	}

}
