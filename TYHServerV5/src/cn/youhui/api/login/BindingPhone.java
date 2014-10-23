package cn.youhui.api.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.common.Config;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.UserManager;
import cn.youhui.ramdata.SMSRandomCacher;
import cn.youhui.utils.AES256;
import cn.youhui.utils.BadParameterException;
import cn.youhui.utils.MD5;
import cn.youhui.utils.ParamUtil;
import cn.youhui.utils.PasswordUtil;
import cn.youhui.utils.RespStatusBuilder;

/**
 * 绑定手机
 * @author lijun
 * @since 2013-11-18
 */
@WebServlet("/login/bindingphone")
public class BindingPhone extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String uid = ParamUtil.getParameter(request, "uid", true);
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
			if(!(randomNCa != null && (randomNCa.equals(randomNum)))){
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.AUTH_CODE_ERROR).toString());
				return;
			}
			if(UserManager.getInstance().isPhoneExist(phoneNum)){
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.PHONENUM_EXIST).toString());
				return;
			}
			password = MD5.digest(password);
			boolean ret = UserManager.getInstance().bindingByPhone(phoneNum, password, uid);      			
			if(ret){
				response.getWriter().println(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED_BINDPHONE).toString());
			}else{
				response.getWriter().println(RespStatusBuilder.message(ActionStatus.REGISTER_FAIL).toString());
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
