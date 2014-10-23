package cn.youhui.api.login;

import java.io.IOException;
import java.util.Random;

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
import cn.youhui.utils.ParamUtil;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhui.utils.SendSMS;


/**
 * 检测电话号码是否已经被注册
 * @author lijun
 * @since 2013-09-07
 */
@WebServlet("/login/getrand")
public class GetRandom extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String uid = ParamUtil.getParameter(request, "uid");
			String phoneNum = ParamUtil.getParameter(request, "phone_num");
			String type = ParamUtil.getParameter(request, "type", true);
			if(phoneNum == null || "".equals(phoneNum)){
				phoneNum = UserManager.getInstance().getPhone(uid);
			}else{
				try{
					phoneNum = phoneNum.replaceAll(" ", "+");
					phoneNum = AES256.decrypt(Config.AES_PWD, phoneNum);
				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
					return;
				}
			}
			if(phoneNum == null || "".equals(phoneNum)){
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
				return;
			}
			if("register".equalsIgnoreCase(type) || "registerForPC".equalsIgnoreCase(type)){
				if(UserManager.getInstance().isPhoneExist(phoneNum)){
					response.getWriter().print(RespStatusBuilder.message(ActionStatus.PHONENUM_EXIST).toString());
					return;
				}
			}else if("forgetpass".equalsIgnoreCase(type) || "forgetpassForPC".equalsIgnoreCase(type)){
				if(!UserManager.getInstance().isPhoneExist(phoneNum)){
					response.getWriter().print(RespStatusBuilder.message(ActionStatus.PHONENUM_NOT_EXIST).toString());
					return;
				}
			}else {
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
				return;
			}
			String randomNum = SMSRandomCacher.getInstance().getRandomNum(phoneNum);
			if(randomNum == null || "".equals(randomNum)){
				randomNum = getRandomStr();
			}
			SMSRandomCacher.getInstance().saveSMSRandomNum(phoneNum, randomNum);
			
			boolean flag = false;
			if("register".equalsIgnoreCase(type)){
				flag = SendSMS.sendRegCode(phoneNum, randomNum);
			}else if("forgetpass".equalsIgnoreCase(type)){
				flag = SendSMS.sendForgCode(phoneNum, randomNum);
			}else if("registerForPC".equalsIgnoreCase(type)){
				flag = SendSMS.sendRegCodeForPC(phoneNum, randomNum);
			}else if("forgetpassForPC".equalsIgnoreCase(type)){
				flag = SendSMS.sendForgCodeForPC(phoneNum, randomNum);
			}
			if(flag){
				String ret = getReturn(changePn(phoneNum));
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, ret).toString());
			}else{
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.SERVER_ERROR).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
		}
	}
	
	private String changePn(String phoneNum){
		if(phoneNum == null || phoneNum.length() != 11){
			return "";
		}else{
			return phoneNum.substring(0, 3) + "****" + phoneNum.substring(7, 11);
		}
	}
	
	private String getReturn(String mp){
		if(mp != null && !"".equals(mp)){
			return "<phone_num>" + mp + "</phone_num>";
		}else{
			return "";
		}
	}
	
	private static String getRandomStr(){
		StringBuffer buffer = new StringBuffer("0123456789");
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		int range = buffer.length();
		for (int i = 0; i < 6; i++) {
			sb.append(buffer.charAt(r.nextInt(range)));
		}
		return sb.toString();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
