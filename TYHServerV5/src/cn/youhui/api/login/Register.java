package cn.youhui.api.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.UserBean;
import cn.youhui.common.Config;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.UserManager;
import cn.youhui.ramdata.SMSRandomCacher;
import cn.youhui.tuiguang.ParamBean;
import cn.youhui.tuiguang.TuiGuangThread;
import cn.youhui.utils.AES256;
import cn.youhui.utils.BadParameterException;
import cn.youhui.utils.MD5;
import cn.youhui.utils.ParamUtil;
import cn.youhui.utils.PasswordUtil;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhui.utils.TPool;
import cn.youhui.utils.WebUtil;

/**
 * 注册
 * @author lijun
 * @since 2013-09-07
 */
@WebServlet("/login/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String imei = ParamUtil.getParameter(request, "imei");
			String imsi = ParamUtil.getParameter(request, "imsi");
			String platform = ParamUtil.getParameter(request, "platform");
			String channel = ParamUtil.getParameter(request, "channel");
			String openudid = ParamUtil.getParameter(request, "openudid");
			String version = request.getParameter("version");
			String phoneNum = ParamUtil.getParameter(request, "phone_num", true);
			String password = ParamUtil.getParameter(request, "password", true);
			String randomNum = ParamUtil.getParameter(request, "random", true);
			
			try{
				phoneNum = phoneNum.replaceAll(" ", "+");
				password = password.replaceAll(" ", "+");
				phoneNum = AES256.decrypt(Config.AES_PWD, phoneNum);
				password = AES256.decrypt(Config.AES_PWD, password);
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
				return;
			}
			String randomNCa = SMSRandomCacher.getInstance().getRandomNum(phoneNum);
			
			if(!PasswordUtil.checkPass(password)){
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.PASSWORD_FORMAT_ERROR).toString());
				return;
			}
			if(!(randomNCa != null && (randomNCa.equals(randomNum) ))){
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.AUTH_CODE_ERROR).toString());
				return;
			}
			
			if(UserManager.getInstance().isPhoneExist(phoneNum)){
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.PHONENUM_EXIST).toString());
				return;
			}
			password = MD5.digest(password);
			
			UserBean user = new UserBean("", "" , "" , phoneNum, password, imei, imsi, channel, platform);
			UserManager.getInstance().registerByPhone(user);        //注册
			if(user == null || null == user.getUid() || "".equals(user.getUid())){
				response.getWriter().println(RespStatusBuilder.message(ActionStatus.REGISTER_FAIL).toString());
			}else{
				ParamBean bean = new ParamBean();
				bean.setActivateip(WebUtil.getIpAddr(request));
				bean.setIdfa(ParamUtil.getParameter(request, "idfa"));
				bean.setCode(ParamUtil.getParameter(request, "version"));
				bean.setOpenudid(ParamUtil.getParameter(request, "openudid"));
				TPool.getInstance().execute(new TuiGuangThread(bean,System.currentTimeMillis(), user.getUid(),2));
				TPool.getInstance().execute(new PushAfRegisterThread(user.getUid(), openudid, version));
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
