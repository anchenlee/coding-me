package cn.youhui.api.servlet3;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.SuiShouAction;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.jfbad.JFBAd;
import cn.youhui.jfbad.SignIn;
import cn.youhui.jfbad.SignInManager;
import cn.youhui.manager.UserManager;
import cn.youhui.tuiguang.ParamBean;
import cn.youhui.tuiguang.TuiGuangThread;
import cn.youhui.utils.BadParameterException;
import cn.youhui.utils.Encrypt;
import cn.youhui.utils.NetManager;
import cn.youhui.utils.ParamUtil;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhui.utils.SuiShouActionUtil;
import cn.youhui.utils.TPool;
import cn.youhui.utils.VersionUtil;
import cn.youhui.utils.WebUtil;

/**
 * 签到接口
 * @author lijun
 * @since 2014-02-24
 */
@WebServlet("/tyh3/sign")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid="";
		try{
			String data = ParamUtil.getParameter(request, "data", true);
			String version = ParamUtil.getParameter(request, "version", true);
			String platform = ParamUtil.getParameter(request, "platform", true);
			data = data.replaceAll(" ", "+");
			data = Encrypt.decode(data);
			String param[] = data.split("#");
			if(param.length !=5){
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
				return;
			}
			uid = param[1];
			String tp = param[2];
			String tbnickorphone = param[3];
			if(uid == null || "".equals(uid)){
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.USER_NOT_LOGIN).toString());
				return;
			}
			if("phone".equals(tp)){
//				if(!UserManager.getInstance().checkUidPhone(tbnickorphone, uid)){
//					response.getWriter().print(RespStatusBuilder.message(ActionStatus.USER_NOT_LOGIN).toString());
//					return;
//				}
			}else if("nick".equals(tp)){
				if(!UserManager.getInstance().isUserExist(uid, tbnickorphone)){
					response.getWriter().print(RespStatusBuilder.message(ActionStatus.USER_NOT_LOGIN).toString());
					return;
				}
			}else{
//				response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
//				return;
//              安卓传的个坑爹的错的 type  
			}
			
			String type = ParamUtil.getParameter(request, "type", true);
			SignIn ret = null;
			if(!isInTime()){
				ret = new SignIn();
				ret.setStatus(4);
			}else{
				if("query".equals(type)){
					ret = SignInManager.getInstance().getSignIn(uid);
				}else if("sign".equals(type)){
					ret = SignInManager.getInstance().sign(uid);
				}
			}
			if(ret == null){
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.SERVER_ERROR).toString());
			}else{
				if(VersionUtil.isHigher("4.0.5", version)){
					JFBAd jfbad = ret.getJfbad();
					if(jfbad != null && jfbad.getAction() != null){
						String oldUrl = jfbad.getAction().getUrl();
						jfbad.setAction(new SuiShouAction(SuiShouActionUtil.getSpecialWebUrl(platform,oldUrl)));
						ret.setJfbad(jfbad);
					}
				}
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, "", ret.toXml()));
			}
		}catch(BadParameterException e){
			e.printStackTrace();
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
		}
		
	}

	/**
	 * 是否在签到时间内
	 * @return
	 */
	private static boolean isInTime(){
		long t0201 = 1391184000000l;
		if(System.currentTimeMillis() < t0201){
			return true;
		}
		if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) > 8){
			return true;
		}
		return false;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
}
