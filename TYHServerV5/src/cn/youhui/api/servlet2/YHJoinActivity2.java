package cn.youhui.api.servlet2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.api.activity.ActivityClient;
import cn.youhui.api.activity.ActivityConfig;
import cn.youhui.api.activity.ActivityRequest;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.UserManager;
import cn.youhui.utils.Encrypt;
import cn.youhui.utils.RespStatusBuilder;

/**
 * 参加活动，集分宝数可变
 * @author lijun
 * @since 2013-07-30
 */
@WebServlet("/tyh2/joinactwj")
public class YHJoinActivity2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String data = request.getParameter("data");
			if(data == null || "".equals(data)){
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
				return;
			}
			data = data.replaceAll(" ", "+");
			data = Encrypt.decode(data);
			String param[] = data.split("#");
			if(param.length != 7){
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
				return;
			}
			String uid = param[1];
			String taobaoNick = param[2];
			String jfbNum = param[3];
			String key = param[4];
			String uniqueId = param[5];
			
			if(uid == null || "".equals(uid) || !UserManager.getInstance().isUserExist(uid, taobaoNick)){
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.USER_NOT_LOGIN).toString());
				return;
			}
			
			try {
				ActivityRequest acrequest = new ActivityRequest(uid, key, uniqueId, Integer.parseInt(jfbNum), 0);
				int ret = ActivityClient.execut(acrequest);
				if(ret == ActivityConfig.ACTIVITY_JOIN_SUCCESS){
					response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED).toString());	
				}else if(ret == ActivityConfig.ACTIVITY_NOT_START){
					response.getWriter().print(RespStatusBuilder.message(ActionStatus.ACTIVITY_NOT_START).toString());
				}else if(ret == ActivityConfig.ACTIVITY_HAS_END){
					response.getWriter().print(RespStatusBuilder.message(ActionStatus.ACTIVITY_HAS_END).toString());
				}else if(ret == ActivityConfig.ACTIVITY_HAS_JOIN){
					response.getWriter().print(RespStatusBuilder.message(ActionStatus.ACTIVITY_HAS_JOIN).toString());
				}else if(ret == ActivityConfig.ACTIVITY_NOT_ALLOW){
					response.getWriter().print(RespStatusBuilder.message(ActionStatus.ACTIVITY_NOT_ALLOW).toString());
				}else if(ret == ActivityConfig.ACTIVITY_UNIQUEID_EXSIT){
					response.getWriter().print(RespStatusBuilder.message(ActionStatus.ACTIVITY_UNIQUEID_EXSIT).toString());
				}else{
					response.getWriter().print(RespStatusBuilder.message(ActionStatus.DATABASE_ERROR).toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.DATABASE_ERROR).toString());
			}
		}catch(Exception e){
			e.printStackTrace();
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
