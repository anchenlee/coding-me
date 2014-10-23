package cn.youhui.api.login;

import java.io.IOException;
import java.net.URLDecoder;

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
import cn.youhui.utils.ParamUtil;
import cn.youhui.utils.RespStatusBuilder;

/**
 * 重设手机号
 * @author lijun
 * @since 2013-11-19
 */
@WebServlet("/login/resetphone")
public class ResetPhoneNum extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String uid = ParamUtil.getParameter(request, "uid", true);
			String randomOld = ParamUtil.getParameter(request, "old_random", true);
			String randomNew = ParamUtil.getParameter(request, "new_random", true);
			String newPhoneNum = ParamUtil.getParameter(request, "new_phone_num", true);
			String oldPhoneNum = UserManager.getInstance().getPhone(uid);
			
			try{
				newPhoneNum =newPhoneNum.replaceAll(" ", "+");
				newPhoneNum = AES256.decrypt(Config.AES_PWD, newPhoneNum);
			}catch (Exception e) {
				e.printStackTrace();
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
				return;
			}
			
			if(oldPhoneNum == null || "".equals(oldPhoneNum)){
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.PHONENUM_NOT_EXIST).toString());
				return;
			}
			String randomNCa = SMSRandomCacher.getInstance().getRandomNum(oldPhoneNum);
			if(!(randomNCa != null && (randomNCa.equals(randomOld) ))){
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.AUTH_CODE_ERROR).toString());
				return;
			}
			String randomNCaNew = SMSRandomCacher.getInstance().getRandomNum(newPhoneNum);
			if(!(randomNCaNew != null && (randomNCaNew.equals(randomNew) ))){
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.AUTH_CODE_ERROR).toString());
				return;
			}
			if(UserManager.getInstance().resetPhoneNum(newPhoneNum, uid)){
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED).toString());
			}else {
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.SERVER_ERROR).toString());
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
