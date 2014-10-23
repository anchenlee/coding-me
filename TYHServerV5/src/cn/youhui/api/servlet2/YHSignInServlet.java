package cn.youhui.api.servlet2;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.SignInBean;
import cn.youhui.bean.UserJFBAccount;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.SignInManager;
import cn.youhui.manager.UserManager;
import cn.youhui.ramdata.UserJFBAccountCacher;
import cn.youhui.utils.Encrypt;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhui.utils.VersionUtil;

/**
 * @category 签到得集分宝
 * @author leejun
 * @since 2013-2-26
 */
@WebServlet("/tyh2/signin")
public class YHSignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = request.getParameter("data");
		String platform = request.getParameter("platform");
		String version = request.getParameter("version");
		
		if(data == null || "".equals(data)){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
			return;
		}
		data = data.replaceAll(" ", "+");
		data = Encrypt.decode(data);
		String param[] = data.split("#");
		if(param.length !=4){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
			return;
		}
		String uid = param[1];
		String taobaoNick = param[2];
		if(uid == null || "".equals(uid) || !UserManager.getInstance().isUserExist(uid, taobaoNick)){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.USER_NOT_LOGIN).toString());
			return;
		}
//		String uid = request.getParameter("uid");
		String type = request.getParameter("type");
		SignInBean retdetail = null;
		int jfbNum = 0;
		if(isIphoneLowVersion(platform, version) && System.currentTimeMillis() > 1399910400000l){   //5.13开始  iphone 3.4.0一下版本停止签到
			retdetail = new SignInBean();
			retdetail.setDesc("该版本已停止签到，请升级至最新版,");
		}else if(!isInTime()){
			retdetail = new SignInBean();
			retdetail.setStatus(4);
		}else{
			if("query".equals(type)){
				retdetail = SignInManager.getInstance().getSignInDetail(uid);
			}else if("sign".equals(type)){
				
				SignInBean ret = SignInManager.getInstance().signIn(uid);
				if(ret == null){
					response.getWriter().print(RespStatusBuilder.message(ActionStatus.SIGN_FAIL).toString());
					return;
				}
				retdetail = SignInManager.getInstance().getSignInDetail(uid);
				if(ret != null && ret.getStatus() == 2){            //签到成功
					retdetail.setStatus(ret.getStatus());
					jfbNum = ret.getJifenbaoNum();
				}
			}else{
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
				return;
			}
			
		}
		
		if(retdetail == null){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.SERVER_ERROR).toString());
		}else{
//			retdetail.setIphoneNew(isIphone340(platform, version));
			if(isIphoneLowVersion(platform, version)){
				retdetail.setDesc("请升级至最新版,该版本5月13日开始将停止签到");
			}
			int[] jfb = change(UserJFBAccountCacher.getInstance().get(uid));
					//ActivityMingxiManager.getInstance().getAll(uid);
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, "", retdetail.toXmlDetail(jfbNum, jfb).toString()));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
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
	
	/**
	 * iphone 的3.4.0 以下版本
	 * @param platform
	 * @param version
	 * @return
	 */
	private boolean isIphoneLowVersion(String platform, String version){
		if(("iphone".equalsIgnoreCase(platform) || "ipad".equalsIgnoreCase(platform)) && VersionUtil.isLowVersion(platform, version)){
			return true;
		}
		return false;
	}
	
	/**
	 * 是否iphone3.4.0版本签到送两倍
	 * @param platform
	 * @param version
	 * @return
	 */
	private boolean isIphone340(String platform, String version){
		long now = System.currentTimeMillis();
		if(now > 1398700800000l && now < 1399219200000l && ("iphone".equalsIgnoreCase(platform) || "ipad".equalsIgnoreCase(platform)) && "3.4.0".equals(version)){
			return true;
		}
		return false;
	}
	
	private int[] change(UserJFBAccount uja){
		int[] jfb = {0,0,0,0};
		if(uja != null){
			jfb[0] = uja.getTxNum();
			jfb[1] = uja.getGainNum();
			jfb[2] = uja.getChkTxNum();
			jfb[3] = uja.getGainNum() - uja.getTxNum() - uja.getChkTxNum();
		}
		return jfb;
	}
	
	public static void main(String[] args) {
		System.out.println(new YHSignInServlet().isIphoneLowVersion("ipad","3.3.2"));
	}
}
