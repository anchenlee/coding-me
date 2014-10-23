package cn.youhui.api.servlet2;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.youhui.api.activity.ActivityClient;
import cn.youhui.api.activity.ActivityConfig;
import cn.youhui.api.activity.ActivityRequest;
import cn.youhui.bean.JFB_Activity_Bean;
import cn.youhui.common.Config;
import cn.youhui.dao.MySQLDBIns;
import cn.youhui.manager.JFB_Act_Manager;
import cn.youhui.manager.UserManager;
import cn.youhui.utils.Encrypt;

/**
 * 集分宝活动用户领取集分宝
 * @author YAOJIANBO
 *
 */
@WebServlet("/tyh2/jfb_act")
public class YHGetJFBAct extends HttpServlet 
{
	public static final Logger log = Logger.getLogger(YHGetJFBAct.class);
	
	private static final long serialVersionUID = 1L;
	
	public YHGetJFBAct() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String act_ID = null;
		String uid = null;
		String nick = null;
		String per_num = null;
		JFB_Activity_Bean act_bean = null;
		
		String param = request.getParameter("param");
		if (param != null && !param.equals(""))
		{
			String paramDec = Encrypt.decode(param);
			String[] params = paramDec.split("&");
			if (params.length == 2)
			{
				act_ID = params[0];
				uid = params[1];
			}
		}
		
		if (act_ID == null || act_ID.equals("") || act_ID.equals("null") 
				|| uid == null || uid.equals("") || uid.equals("null")) 
		{
			response.sendRedirect("http://youhui.cn");
			return;
		}
		
		Connection conn = null;
		try
		{
			conn = MySQLDBIns.getInstance().getConnection();
			
			act_bean = JFB_Act_Manager.getActBeanWithConn(act_ID, conn);
			if (null != act_bean)
			{
				per_num = String.valueOf(act_bean.getPer_num());
			}
			else
			{
				response.sendRedirect("http://youhui.cn");
				return;
			}
			
//			nick = UserManager.getInstance().getUserNickWithConn(uid, conn);
//			if (null == nick)
//			{
//				response.sendRedirect(act_bean.getClick_url());
//				return;
//			}
			
			// log.info("/tyh2/jfb_act param:nick=" + nick + ";uid=" + uid + ";act_ID=" + act_ID);
			
			String ser_num = JFB_Act_Manager.getInstance().checkUserToAct_2(act_ID, uid, conn);
			if (!ser_num.equals("0"))
			{
				
				ActivityRequest actRequest = new ActivityRequest(uid, Config.jfb_activity_JoinKey, ser_num, Integer.parseInt(per_num));
				int re = ActivityClient.execut(actRequest);
				
				if (ActivityConfig.ACTIVITY_JOIN_SUCCESS == re || ActivityConfig.ACTIVITY_UNIQUEID_EXSIT == re )
				{
					int flag = JFB_Act_Manager.updateJFBDetailStatus(uid, ser_num, conn);
					if (flag == 1)
					{
						log.info("join flag = " + re + "|||uid:" + uid + ",actID:" + act_ID + ";ser_num:" + ser_num + ".....has not be committed successfully!");
					}
					response.sendRedirect(act_bean.getClick_url());
					return;
				}
				
			}
		}
		catch (Exception e)
		{
			log.error("YHGetJFBAct.ActivityRequest request error:", e);
		}
		finally 
		{
			MySQLDBIns.getInstance().release(conn);
		}
		
		response.sendRedirect(act_bean.getClick_url());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		doGet(request, response);
	}
	
}
