package cn.youhui.api.servlet3;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import sun.misc.BASE64Encoder;
import cn.youhui.common.Enums;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.ramdata.AppConfigCacher;
import cn.youhui.ramdata.ClientMenuCacher;
import cn.youhui.ramdata.IphoneDevTokenCacher;
import cn.youhui.utils.RespStatusBuilder;

/**
 * appconfig
 * @author lijun
 * @since 2014-9-19
 */
@WebServlet("/tyh3/appconfig4ipad")
public class GetAppconfig4Ipad extends HttpServlet {
	private static final long serialVersionUID = 1L;
 

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
			String appconfig = AppConfigCacher.getInstance().getIPhoneConfigData();
			if(appconfig == null)
			{
				AppConfigCacher.getInstance().reload();
				appconfig = AppConfigCacher.getInstance().getIPhoneConfigData();
			}
			System.out.println("1111111111111::"+appconfig);
			JsonParser jp=new JsonParser();
			JsonObject jo=jp.parse(appconfig.split("\"appconfig\":")[1]).getAsJsonObject();
			jo.remove("islocaldetailget");
			System.out.println("22222222::"+jo.toString());
			result = "{\"appconfig\":" + jo.toString() + ",\"menuversion\":\"" + ClientMenuCacher.getInstance().getVersion() +"\"}";
			BASE64Encoder base = new BASE64Encoder();
			result = base.encode(result.getBytes());
			result = "\"" + result + "\""; 
		}
		catch (Exception e) 
		{
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
}
