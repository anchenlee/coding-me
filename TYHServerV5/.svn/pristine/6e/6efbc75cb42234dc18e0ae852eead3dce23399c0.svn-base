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
import cn.youhui.common.ParamConfig;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.LogManager;
import cn.youhui.manager.LuckAcTimesManager;
import cn.youhui.manager.UserManager;
import cn.youhui.utils.Encrypt;
import cn.youhui.utils.NetManager;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhui.utils.StringUtils;
import cn.youhui.utils.TPool;

/**
 * 用户参加活动
 * @author leejun
 * @since 2013-05-23
 */
@WebServlet("/tyh2/joinact")
public class YHJoinActivity extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = request.getParameter("data");
		String sUid = request.getParameter("uid");
		String sTBNick = request.getParameter("taobao_nick");
		String sKey = request.getParameter("key");
		
		String uid = "";
		String taobaoNick = "";
		String key = "";
		
		
		
		if(!StringUtils.isEmpty(data)){
			data = data.replaceAll(" ", "+");
			data = Encrypt.decode(data);
			String param[] = data.split("#");
			if(param.length !=5){
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
				return;
			}
			uid = param[1];
			taobaoNick = param[2];
			key = param[3];
		}else if(!StringUtils.isEmpty(sUid) && !StringUtils.isEmpty(sTBNick) && !StringUtils.isEmpty(sKey)){
			uid = sUid;
			taobaoNick = sTBNick;
			key = sKey;
		}else{
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
			return;
		}
		
		System.out.println("------------------------------------------------>>>>>>>>>>>>>>>>>>>"+uid+"//"+key);
		
		if(uid == null || "".equals(uid) || !UserManager.getInstance().isExsitUid(uid)){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.USER_NOT_LOGIN).toString());
			return;
		}
		
		try {
			ActivityRequest acrequest = new ActivityRequest(uid, key, uid + System.currentTimeMillis(), 0, 0);
			int ret = ActivityClient.execut(acrequest);
			
			if ("ugp34xqi".equalsIgnoreCase(key)) {   //麦当劳抽奖活动
				if(ret == ActivityConfig.ACTIVITY_JOIN_SUCCESS){
					LuckAcTimesManager.getInstance().addMcdOneTime(uid);
					response.getWriter().print(RespStatusBuilder.message(ActionStatus.SHARE_MCD_SUCCESS).toString());	
				}else{
					response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED).toString());
				}
				return;
			}
			
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
			}else{
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.DATABASE_ERROR).toString());
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.DATABASE_ERROR).toString());
		}
		
//		int ret = ActivityManager.getInstance().joinActivity(key, uid);
//		if(ret == 5){
//			Activity activity = ActivityManager.getInstance().getActivityByKey(key);
//			
//			if(ActivityJoinManager.getInstance().join(activity, uid)){
//				response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED).toString());
//			}else{
//				response.getWriter().print(RespStatusBuilder.message(ActionStatus.DATABASE_ERROR).toString());
//			}
//		}else if(ret == 1){
//			response.getWriter().print(RespStatusBuilder.message(ActionStatus.ACTIVITY_NOT_START).toString());
//		}else if(ret == 2){
//			response.getWriter().print(RespStatusBuilder.message(ActionStatus.ACTIVITY_HAS_END).toString());
//		}else if(ret == 3){
//			response.getWriter().print(RespStatusBuilder.message(ActionStatus.ACTIVITY_HAS_JOIN).toString());
//		}else if(ret == 4){
//			response.getWriter().print(RespStatusBuilder.message(ActionStatus.ACTIVITY_NOT_ALLOW).toString());
//		}else{
//			response.getWriter().print(RespStatusBuilder.message(ActionStatus.DATABASE_ERROR).toString());
//		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
}
