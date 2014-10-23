package cn.youhui.api.servlet2;

import java.io.IOException;
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
import cn.youhui.utils.RespStatusBuilder;

/**
 * @category 提供app参数
 * @author leejun
 * @since 2012-12-2
 */
@WebServlet("/tyh2/appconfig4iphone")
public class YHAppConfigServlet4IPhone extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(YHAppConfigServlet4IPhone.class);
       
    public YHAppConfigServlet4IPhone(){
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
			String appconfig = AppConfigCacher.getInstance().getIPhoneConfigData();
			if(appconfig == null)
			{
				AppConfigCacher.getInstance().reload();
				appconfig = AppConfigCacher.getInstance().getIPhoneConfigData();
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

}
