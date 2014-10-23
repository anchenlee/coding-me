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
import cn.youhui.utils.MD5;
import cn.youhui.utils.ParamUtil;
import cn.youhui.utils.PasswordUtil;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhui.utils.StringUtils;


/**
 * 重设密码
 * @author lijun
 * @since 2013-11-18
 */
@WebServlet("/login/resetpass")
public class ResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String uid = ParamUtil.getParameter(request, "uid");
			String random = ParamUtil.getParameter(request, "random", true);
			String newPass = ParamUtil.getParameter(request, "password", true);
			String phoneNum = ParamUtil.getParameter(request, "phone_num");
			if(StringUtils.isEmpty(phoneNum)){
				phoneNum = UserManager.getInstance().getPhone(uid);
			}else{
				phoneNum = phoneNum.replaceAll(" ", "+");
				phoneNum = AES256.decrypt(Config.AES_PWD, phoneNum);
			}
				newPass = newPass.replaceAll(" ", "+");
				newPass = AES256.decrypt(Config.AES_PWD, newPass);
			
			if(!PasswordUtil.checkPass(newPass)){
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.PASSWORD_FORMAT_ERROR).toString());
				return;
			}
			if(phoneNum == null || "".equals(phoneNum)){
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.PHONENUM_NOT_EXIST).toString());
				return;
			}
			String randomNCa = SMSRandomCacher.getInstance().getRandomNum(phoneNum);
			if(!(randomNCa != null && (randomNCa.equals(random)))){
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.AUTH_CODE_ERROR).toString());
				return;
			}
			newPass = MD5.digest(newPass);
			if(UserManager.getInstance().resetPassword(newPass, phoneNum)){
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED).toString());
			}else {
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.SERVER_ERROR).toString());
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
