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
import cn.youhui.dao.AdminUmengDeviceStatusDao;
import cn.youhui.manager.UserManager;
import cn.youhui.utils.AES256;
import cn.youhui.utils.BadParameterException;
import cn.youhui.utils.MD5;
import cn.youhui.utils.ParamUtil;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhui.utils.TPool;


/**
 * 手机号登录
 * @author lijun
 *
 */
@WebServlet("/login/loginphone")
public class LoginByPhone extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String phoneNum = ParamUtil.getParameter(request, "phone_num", true);
			String password = ParamUtil.getParameter(request, "password", true);
			
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
			if(!UserManager.getInstance().isPhoneExist(phoneNum)){
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.PHONENUM_NOT_EXIST).toString());
				return;
			}
			password = MD5.digest(password);
			UserBean user = UserManager.getInstance().login(phoneNum, password);
			if(user == null || user.getUid() == null || "".equals(user.getUid())){
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.PASSWORD_ERROR).toString());
			}else{
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, user.toXML()).toString());
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
