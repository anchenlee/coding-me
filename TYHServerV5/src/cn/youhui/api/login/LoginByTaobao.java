package cn.youhui.api.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.UserBean;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.dao.AdminUmengDeviceStatusDao;
import cn.youhui.manager.TaobaoManager;
import cn.youhui.manager.UserManager;
import cn.youhui.tuiguang.ParamBean;
import cn.youhui.tuiguang.TuiGuangThread;
import cn.youhui.utils.BadParameterException;
import cn.youhui.utils.ParamUtil;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhui.utils.TPool;
import cn.youhui.utils.WebUtil;

/**
 * 淘宝帐号登录
 * @author lijun
 * @since 2014-4-11
 */
@WebServlet("/login/logintb")
public class LoginByTaobao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String taobaoUid = ParamUtil.getParameter(request, "taobao_uid");
			String token = ParamUtil.getParameter(request, "token", true);
			String taobaoNick = ParamUtil.getParameter(request, "taobao_nick", true);
			String imei = ParamUtil.getParameter(request, "imei");
			String imsi = ParamUtil.getParameter(request, "imsi");
			String platform = ParamUtil.getParameter(request, "platform");
			String channel = ParamUtil.getParameter(request, "channel");
			String version = ParamUtil.getParameter(request, "version");
			String openudid = ParamUtil.getParameter(request, "openudid");
			
			taobaoNick = TaobaoManager.decodeTBNick(taobaoNick);
			if("-1".equals(taobaoNick) || !taobaoNick.equals(TaobaoManager.getNickByToken(token))){
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
				return;
			}
			
			UserBean user = new UserBean("", taobaoNick, taobaoUid, "", "", imei, imsi, channel, platform);
			
			UserManager.getInstance().saveUser(user);
			System.out.println("user+++++++++++++++++:" + user.getUid());
			if(user == null || null == user.getUid() || "".equals(user.getUid())){
				response.getWriter().println(RespStatusBuilder.message(ActionStatus.REGISTER_FAIL).toString());
			}else{
				if(user.isNewUser()){
					ParamBean bean = new ParamBean();
					bean.setActivateip(WebUtil.getIpAddr(request));
					bean.setIdfa(ParamUtil.getParameter(request, "idfa"));
					bean.setCode(ParamUtil.getParameter(request, "version"));
					bean.setOpenudid(ParamUtil.getParameter(request, "openudid"));
					TPool.getInstance().execute(new TuiGuangThread(bean,System.currentTimeMillis(), user.getUid(),2));
					TPool.getInstance().execute(new PushAfRegisterThread(user.getUid(), openudid, version));
				}
				response.getWriter().println(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, user.toXML()).toString());
			}
		}catch(BadParameterException e){
			e.printStackTrace();
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}